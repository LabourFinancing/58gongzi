package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.FinanceProduct;

@Repository
public interface MainBoardDao {
    int deleteByPrimaryKey(String t_FProd_ID);

    int insertSelective(FinanceProduct record);

    FinanceProduct selectByPrimaryKey(String t_FProd_ID);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(FinanceProduct record);
    
    List<FinanceProduct> findAllList(Map<String, Object> paramMap);
    
    List<FinanceProduct> findSearchList(Map<String, Object> paramMap);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existFinanceProductName(@Param("t_FProd_ID") String ID, @Param("t_FProd_Name") String Name, @Param("platform") Integer platform);
    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}