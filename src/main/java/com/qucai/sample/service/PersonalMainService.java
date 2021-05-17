package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalMain;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface PersonalMainService {
    
    int deleteByPrimaryKey(String t_personal_main_id);

    int insertSelective(PersonalMain record);

    PersonalMain selectByPrimaryKey(String t_personal_main_id);
//    
//	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get);
//	
//	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalMain record);
    
    List<PersonalMain> findAllList(Map<String, Object> paramMap);
    
    List<PersonalMain> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PersonalMain> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PersonalMain> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);

//    List<PersonalMain> findSubCompany(Map<String, Object> paramMap);

//    List<PersonalMain> findAgencyCompany(Map<String, Object> paramMap);
    
    boolean existPersonalMainName(String t_personal_main_id,String t_personal_main_pid);
    
    boolean checkPersonalMainMobil(String t_personal_main_id, String t_personal_main_mobile);

	List<PersonalMain> findSearchCompanyDist(Map<String, Object> paramSearchMap);

}