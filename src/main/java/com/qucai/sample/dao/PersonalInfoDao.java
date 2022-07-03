package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.PersonalInfo;

@Repository
public interface PersonalInfoDao {
    int deleteByPrimaryKey(String t_P_id);

    int insertSelective(PersonalInfo record);

    PersonalInfo selectByPrimaryKey(String t_P_id);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalInfo record);

	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName);
	
	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName);
	
	List<PersonalInfo> findSubCompany(Map<String, Object> paramMap);
	
	List<PersonalInfo> findAgencyCompany(Map<String, Object> paramMap);
    
    List<PersonalInfo> findAllList(Map<String, Object> paramMap);
    
    List<PersonalInfo> findSearchList(Map<String, Object> paramSearchMap);
    
    List<PersonalInfo> findSearchCompanyDist(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existPersonalInfoName(@Param("t_P_id") String t_P_id,@Param("t_P_PID") String t_P_PID);
    
    int checkPersonalInfoMobil(@Param("t_P_id") String t_P_id, @Param("t_P_Mobil") String t_P_Mobil);

    PersonalInfo findPrepayApplierCreditBalance(Map<String, Object> paramSearchMap);

    boolean updatePrepayApplierCreditBalance(Map<String, Object> paramSearchMap);


    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}