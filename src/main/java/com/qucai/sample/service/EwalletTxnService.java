//package com.qucai.sample.service;
//
//import com.github.pagehelper.PageInfo;
//import com.qucai.sample.common.PageParam;
//import com.qucai.sample.entity.PersonalInfo;
//
//import java.util.List;
//import java.util.Map;
////import com.qucai.sample.entity.resource;
//
//public interface EwalletTxnService {
//    
//    int deleteByPrimaryKey(String t_P_id);
//
//    int insertSelective(PersonalInfo record);
//
//	PersonalInfo selectByPrimaryKey(String t_P_id);
//    
//	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get);
//	
//	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get);
///*
// * Search FinanceProductName , Search Other info , - Personal and so on.
// */
//    
////    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
//
//    int updateByPrimaryKeySelective(PersonalInfo record);
//    
//    List<PersonalInfo> findAllList(Map<String, Object> paramMap);
//    
//    List<PersonalInfo> findSearchList(Map<String, Object> paramSearchMap);
//    
//    PageInfo<PersonalInfo> findAllList(Map<String, Object> paramMap, PageParam pp);
//    
//    PageInfo<PersonalInfo> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);
//    
////   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
//    
////    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
//    
////    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
//    
//    boolean existPersonalInfoName(String t_P_id, String t_P_PID);
//    
//    boolean checkPersonalInfoMobil(String t_P_id, String t_P_Mobil);
//
//	List<PersonalInfo> findSearchCompanyDist(Map<String, Object> paramSearchMap);
//
//	List<PersonalInfo> findSubCompany(Map<String, Object> paramMap);
//	
//	List<PersonalInfo> findAgencyCompany(Map<String, Object> paramMap);
//
//}