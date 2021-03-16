package com.qucai.sample.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qucai.sample.dao.ResourceDao;
import com.qucai.sample.dao.TrRoleResourceDao;
import com.qucai.sample.entity.Resource;
import com.qucai.sample.service.ResourceService;
import com.qucai.sample.vo.ResourceGrant;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        String resourceIdAllStr = resourceDao.findResourceChildId(id);
        trRoleResourceDao.deleteByResourceIdStr(resourceIdAllStr);
        return resourceDao.deleteByPrimaryKeyStr(resourceIdAllStr);
    }

    @Override
    public int insertSelective(Resource record) {
        return resourceDao.insertSelective(record);
    }

    @Override
    public Resource selectByPrimaryKey(String id) {
        return resourceDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Resource record) {
        return resourceDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public List<Resource> findAllList(Map<String, Object> paramMap) {
        return resourceDao.findAllList(paramMap);
    }

    @Override
    public List<Resource> findTreetableList(Map<String, Object> paramMap) {
        List<Resource> rList = resourceDao.findAllList(paramMap);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList(initGroupMap(rList), "");
        }
    }
    
    @Override
    public List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform) {
        List<ResourceGrant> rList = resourceDao.findManagerResourceGrantAllList(roleId, platform);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList2(initGroupMap(rList), "");
        }
    }
    
    @Override
    public List<Resource> findAuthResourceListByManagerId(String managerId) {
        List<Resource> rList = resourceDao.findAuthResourceListByManagerId(managerId);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList(initGroupMap(rList), "");
        }
    }
    
    private <T extends Resource> Map<String, List<T>> initGroupMap(List<T> rList){
        Map<String, List<T>> map = new HashMap<String, List<T>>();
        for (T r : rList) {
            if (StringUtils.isBlank(r.getParentId())) {
                if (map.containsKey("")) {
                    map.get("").add(r);
                } else {
                    List<T> t = new ArrayList<T>();
                    t.add(r);
                    map.put("", t);
                }
                continue;
            } else {
                if (map.containsKey(r.getParentId())) {
                    map.get(r.getParentId()).add(r);
                } else {
                    List<T> t = new ArrayList<T>();
                    t.add(r);
                    map.put(r.getParentId(), t);
                }
            }
        }
        return map;
    }

    private <T extends Resource> List<T> assembleTreeList(Map<String, List<T>> map, String key){
        List<T> rs = new ArrayList<T>();
        for(T r : map.get(key)){
            rs.add(r);
            if(map.containsKey(r.getId())){
                rs.addAll(assembleTreeList(map, r.getId()));
            }
        }
        return rs;
    }
    
    private List<ResourceGrant> assembleTreeList2(Map<String, List<ResourceGrant>> map, String key){
        List<ResourceGrant> rs = new ArrayList<ResourceGrant>();
        for(ResourceGrant r : map.get(key)){
            rs.add(r);
            if(map.containsKey(r.getId())){
                rs.addAll(assembleTreeList2(map, r.getId()));
            } else {
            	r.setIsLeaf(true);
            }
        }
        return rs;
    }

    @Override
    public boolean existResourceName(String id, String name, Integer platform) {
        return resourceDao.existResourceName(id, name, platform) == 1;
    }
    
}
