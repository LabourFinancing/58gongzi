package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.TreasuryInfoDao;
import com.qucai.sample.entity.TreasuryInfo;
import com.qucai.sample.service.TreasuryInfoService;

@Service("TreasuryInfoService")
@Transactional	
public class TreasuryInfoServiceImpl implements TreasuryInfoService {

    @Autowired
    private TreasuryInfoDao treasuryInfoDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
       
    @Override
    public int insertSelective(TreasuryInfo record) {
        return treasuryInfoDao.insertSelective(record);
    }
    
    @Override
    public int updateByPrimaryKey(TreasuryInfo record) {
        return treasuryInfoDao.updateByPrimaryKey(record);
    }
    
    @Override
    public int deleteByPrimaryKey(String t_Treasury_ID) {
    	return treasuryInfoDao.deleteByPrimaryKey(t_Treasury_ID);
    }
    
    @Override
    public TreasuryInfo selectByPrimaryKey(String t_Treasury_ID) {
        return treasuryInfoDao.selectByPrimaryKey(t_Treasury_ID);
    }

    @Override
    public List<TreasuryInfo> findAllList(Map<String, Object> paramMap) {
        return treasuryInfoDao.findAllList(paramMap);
    }
    
    @Override
    public List<TreasuryInfo> findSearchList(Map<String, Object> paramSearchMap) {
        return treasuryInfoDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public PageInfo<TreasuryInfo> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryInfo> list = treasuryInfoDao.findAllList(paramMap);
        return new PageInfo<TreasuryInfo>(list);
    }
    
    @Override
    public PageInfo<TreasuryInfo> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<TreasuryInfo> list = treasuryInfoDao.findSearchList(paramSearchMap);
        return new PageInfo<TreasuryInfo>(list);
    }

    /*
    public List<FinanceProduct> findTreetableList(Map<String, Object> paramMap) {
        List<FinanceProduct> rList = financeProductDao.findAllList(paramMap);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList(initGroupMap(rList), "");
        }
    }
    
    @Override
    public List<FinanceProductGrant> findGrantTreetableList(String roleId, Integer platform) {
        List<FinanceProductGrant> rList = financeProductDao.findManagerFinanceProductGrantAllList(roleId, platform);
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
*/
    @Override  
     public boolean existTreasuryInfoName(String t_Treasury_OrgName) {
     return treasuryInfoDao.existTreasuryInfoName(t_Treasury_OrgName) == 1;
    }
}
