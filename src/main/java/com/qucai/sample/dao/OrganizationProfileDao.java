package com.qucai.sample.dao;

import com.qucai.sample.entity.OrganizationProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrganizationProfileDao {
	 
    int deleteByPrimaryKey(String t_Profile_ID);

    int insertSelective(OrganizationProfile record);

    OrganizationProfile selectByPrimaryKey(String t_Profile_ID);

    OrganizationProfile selectAgencyName(@Param("t_Profile_OrgName") String t_Profile_OrgName);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(OrganizationProfile record);
    
    int updateByPymtswitch(OrganizationProfile record);
    
    List<OrganizationProfile> findAllList(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findAllName(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findOrgName(Map<String, Object> paramMap);
    
    List<OrganizationProfile> findSearchList(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);

	List<OrganizationProfile> findOrgNameAgency(Map<String, Object> paramSearchMap);

    int existOrganizationProfileName(@Param("t_Profile_ID") String t_Profile_ID, @Param("t_Profile_OrgName") String t_Profile_OrgName, @Param("platform") Integer platform);

    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}