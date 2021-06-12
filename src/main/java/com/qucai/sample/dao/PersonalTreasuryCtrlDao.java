package com.qucai.sample.dao;

import com.qucai.sample.entity.PersonalTreasuryCtrl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonalTreasuryCtrlDao {
    int deleteByPrimaryKey(String t_personalewallet_treasuryctrlID);

    int insertSelective(PersonalTreasuryCtrl record);

    PersonalTreasuryCtrl selectByPrimaryKey(String t_personalewallet_treasuryctrlID);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalTreasuryCtrl record);
    
    List<PersonalTreasuryCtrl> findAllList(Map<String, Object> paramMap);
    
    List<PersonalTreasuryCtrl> findSearchList(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existPersonalTreasuryCtrlName(@Param("t_personalewallet_treasuryctrlID") String ID, @Param("t_personalewallet_treasuryctrlProdName") String Name, @Param("platform") Integer platform);
    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}