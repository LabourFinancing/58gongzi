package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.OrganizationInfo;

@Repository
public interface OrganizationInfoDao {
	 
    int deleteByPrimaryKey(String t_O_ID);

    int insertSelective(OrganizationInfo record);

    OrganizationInfo selectByPrimaryKey(String t_O_ID);
    
	OrganizationInfo selectAgencyName(@Param("t_O_OrgName") String t_O_OrgName);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(OrganizationInfo record);
    
    int updateByPymtswitch(OrganizationInfo record);
    
    List<OrganizationInfo> findAllList(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findAllName(Map<String, Object> paramMap);

    List<OrganizationInfo> findAllNameOnly(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findOrgName(Map<String, Object> paramMap);
    
    List<OrganizationInfo> findSearchList(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existOrganizationInfoName(@Param("t_O_ID") String ID, @Param("t_O_OrgName") String Name, @Param("platform") Integer platform);

	List<OrganizationInfo> findOrgNameAgency(Map<String, Object> paramSearchMap);
    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}