package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationProfile;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface OrganizationProfileService {
    
    int deleteByPrimaryKey(String t_Profile_ID);

    int insertSelective(OrganizationProfile record);

    OrganizationProfile selectByPrimaryKey(String t_Profile_ID);

    OrganizationProfile selectAgencyName(String t_Profile_OrgName);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(OrganizationProfile record);
    
    int updateByPymtswitch(OrganizationProfile record);
    
    List<OrganizationProfile> findAllList(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findAllName(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findOrgName(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<OrganizationProfile> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<OrganizationProfile> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existOrganizationProfileName(String t_Profile_ID, String t_Profile_OrgName, Integer platform);

	List<OrganizationProfile> findOrgNameAgency(Map<String, Object> paramSearchMap);

    
}