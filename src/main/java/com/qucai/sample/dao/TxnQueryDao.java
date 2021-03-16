package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.TxnQuery;



@Repository
public interface TxnQueryDao {
	
    int deleteByPrimaryKey(String t_TxnQuery_ID);

    int insertSelective(TxnQuery record);

    TxnQuery selectByPrimaryKey(String t_TxnQuery_ID);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(TxnQuery record);
    
	int findSearchList(StaffPrepayApplicationList record);
    
    List<TxnQuery> findAllList(Map<String, Object> paramMap);
    
    int ViewByPrimaryKeySelective(TxnQuery record);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existPersonalInfoName(@Param("t_TxnQuery_ID") String ID, @Param("t_TxnQuery_ID") String Name, @Param("platform") Integer platform);

    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}