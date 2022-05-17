package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.OrganizationInfoDao;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.service.OrganizationInfoService;

@Service("OrganizationInfoService")
@Transactional	
public class OrganizationInfoServiceImpl implements OrganizationInfoService {

    @Autowired
    private OrganizationInfoDao organizationInfoDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/

   @Override
   public int deleteByPrimaryKey(String t_O_ID) {
       return organizationInfoDao.deleteByPrimaryKey(t_O_ID);
   }

    @Override
    public int deleteByOrgName(String orgName) {
        return organizationInfoDao.deleteByPrimaryKey(orgName);
    }
       
    @Override
    public int insertSelective(OrganizationInfo record) {
        return organizationInfoDao.insertSelective(record);
    }
    

    @Override
    public OrganizationInfo selectByPrimaryKey(String t_O_ID) {
        return organizationInfoDao.selectByPrimaryKey(t_O_ID);
    }
    
    @Override
    public OrganizationInfo selectAgencyName(String t_O_OrgName) {
        return organizationInfoDao.selectAgencyName(t_O_OrgName);
    }

    @Override
    public int updateByPrimaryKeySelective(OrganizationInfo record) {
        return organizationInfoDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateByPymtswitch(OrganizationInfo record) {
        return organizationInfoDao.updateByPymtswitch(record);
    }
    
    @Override
    public List<OrganizationInfo> findAllName(Map<String, Object> paramMap) {
        return organizationInfoDao.findAllName(paramMap);
    }

    @Override
    public List<OrganizationInfo> findAllNameOnly(Map<String, Object> paramMap) {
        return organizationInfoDao.findAllName(paramMap);
    }
    
    @Override
    public List<OrganizationInfo> findOrgName(Map<String, Object> paramMap) {
        return organizationInfoDao.findOrgName(paramMap);
    }
    
    @Override
    public List<OrganizationInfo> findOrgNameAgency(Map<String, Object> paramMap) {
        return organizationInfoDao.findOrgNameAgency(paramMap);
    }


    @Override
    public List<OrganizationInfo> findAllList(Map<String, Object> paramMap) {
        return organizationInfoDao.findAllList(paramMap);
    }
    
    @Override
    public List<OrganizationInfo> findSearchList(Map<String, Object> paramSearchMap) {
        return organizationInfoDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public PageInfo<OrganizationInfo> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<OrganizationInfo> list = organizationInfoDao.findAllList(paramMap);
        return new PageInfo<OrganizationInfo>(list);
    }
    
    @Override
    public PageInfo<OrganizationInfo> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<OrganizationInfo> list = organizationInfoDao.findSearchList(paramSearchMap);
        return new PageInfo<OrganizationInfo>(list);
    }
    
    @Override
    public boolean existOrganizationInfoName(String t_O_ID, String t_O_OrgName, String platform) {
        return organizationInfoDao.existOrganizationInfoName(t_O_ID, t_O_OrgName, platform) == 1;
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

    
}
