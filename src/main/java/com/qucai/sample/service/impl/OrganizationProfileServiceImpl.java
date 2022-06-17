package com.qucai.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.OrganizationProfileDao;
import com.qucai.sample.entity.OrganizationProfile;
import com.qucai.sample.service.OrganizationProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("OrganizationProfileService")
@Transactional	
public class OrganizationProfileServiceImpl implements OrganizationProfileService {

    @Autowired
    private OrganizationProfileDao organizationProfileDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/

   @Override
   public int deleteByPrimaryKey(String t_Profile_ID) {
       return organizationProfileDao.deleteByPrimaryKey(t_Profile_ID);
   }
       
    @Override
    public int insertSelective(OrganizationProfile record) {
        return organizationProfileDao.insertSelective(record);
    }

    @Override
    public OrganizationProfile selectByPrimaryKey(String t_Profile_ID) {
        return organizationProfileDao.selectByPrimaryKey(t_Profile_ID);
    }
    
    @Override
    public OrganizationProfile selectAgencyName(String t_Profile_OrgName) {
        return organizationProfileDao.selectAgencyName(t_Profile_OrgName);
    }

    @Override
    public int updateByPrimaryKeySelective(OrganizationProfile record) {
        return organizationProfileDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByProfileOrgNameSelective(OrganizationProfile record) {
        return organizationProfileDao.updateByProfileOrgNameSelective(record);
    }
    
    @Override
    public int updateByPymtswitch(OrganizationProfile record) {
        return organizationProfileDao.updateByPymtswitch(record);
    }
    
    @Override
    public List<OrganizationProfile> findAllName(Map<String, Object> paramMap) {
        return organizationProfileDao.findAllName(paramMap);
    }
    
    @Override
    public List<OrganizationProfile> findOrgName(Map<String, Object> paramMap) {
        return organizationProfileDao.findOrgName(paramMap);
    }
    
    @Override
    public List<OrganizationProfile> findOrgNameAgency(Map<String, Object> paramMap) {
        return organizationProfileDao.findOrgNameAgency(paramMap);
    }


    @Override
    public List<OrganizationProfile> findAllList(Map<String, Object> paramMap) {
        return organizationProfileDao.findAllList(paramMap);
    }
    
    @Override
    public List<OrganizationProfile> findSearchList(Map<String, Object> paramSearchMap) {
        return organizationProfileDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public PageInfo<OrganizationProfile> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<OrganizationProfile> list = organizationProfileDao.findAllList(paramMap);
        return new PageInfo<OrganizationProfile>(list);
    }
    
    @Override
    public PageInfo<OrganizationProfile> findSearchList(Map<String, Object>paramSearchMap,PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<OrganizationProfile> list = organizationProfileDao.findSearchList(paramSearchMap);
        return new PageInfo<OrganizationProfile>(list);
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
    public boolean existOrganizationProfileName(String t_Profile_ID, String t_Profile_OrgName, Integer platform) {
        return organizationProfileDao.existOrganizationProfileName(t_Profile_ID, t_Profile_OrgName, platform) == 1;
    }


}
