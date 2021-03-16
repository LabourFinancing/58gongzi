package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationInfo;
//import com.qucai.sample.entity.resource;

public interface OrganizationInfoService {
    
    int deleteByPrimaryKey(String t_O_ID);

    int insertSelective(OrganizationInfo record); 
    
    OrganizationInfo selectByPrimaryKey(String t_O_ID);
    
    OrganizationInfo selectAgencyName(String t_O_OrgName);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(OrganizationInfo record);
    
    int updateByPymtswitch(OrganizationInfo record);
    
    List<OrganizationInfo> findAllList(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findAllName(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findOrgName(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findSearchList(Map<String, Object> paramMap);
    
    PageInfo<OrganizationInfo> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<OrganizationInfo> findSearchList(PageParam pp,Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existOrganizationInfoName(String t_O_ID, String t_O_OrgName, Integer platform);

	List<OrganizationInfo> findOrgNameAgency(Map<String, Object> paramSearchMap);

    
}