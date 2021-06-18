package com.qucai.sample.dao;

import com.qucai.sample.entity.PersonalMain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonalMainDao {
    int deleteByPrimaryKey(String t_personal_main_id);

    int insertSelective(PersonalMain record);

    PersonalMain selectByPrimaryKey(String t_personal_main_id, String pid);
    
    int updateByPrimaryKeySelective(PersonalMain record,String pid);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
//
//	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName);
//	
//	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName);
//	
//	List<PersonalMain> findSubCompany(Map<String, Object> paramMap);
	
	List<PersonalMain> findAgencyCompany(Map<String, Object> paramMap);
    
    List<PersonalMain> findAllList(Map<String, Object> paramMap);
    
    List<PersonalMain> findSearchList(Map<String, Object> paramSearchMap);
    
    List<PersonalMain> findSearchCompanyDist(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existPersonalMainName(@Param("t_personal_main_id") String t_personal_main_id,@Param("t_personal_main_pid") String t_personal_main_pid);
    
    int checkPersonalMainMobil(@Param("t_personal_main_id") String t_personal_main_id,@Param("t_personal_main_mobile") String t_personal_main_mobile);

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}