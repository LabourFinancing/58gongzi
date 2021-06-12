package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalTreasuryCtrl;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface PersonalTreasuryCtrlService {
    
    int deleteByPrimaryKey(String t_personalewallet_treasuryctrlID);

    int insertSelective(PersonalTreasuryCtrl record);

    PersonalTreasuryCtrl selectByPrimaryKey(String t_personalewallet_treasuryctrlID);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalTreasuryCtrl record);
    
    List<PersonalTreasuryCtrl> findAllList(Map<String, Object> paramMap);
    
    List<PersonalTreasuryCtrl> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PersonalTreasuryCtrl> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PersonalTreasuryCtrl> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existPersonalTreasuryCtrlName(String t_personalewallet_treasuryctrlID, String t_personalewallet_treasuryctrlProdName, Integer platform);
    
}