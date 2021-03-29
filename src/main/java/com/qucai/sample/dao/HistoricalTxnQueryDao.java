package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import com.qucai.sample.vo.CompanyTxnAmtWlyStatic;
import com.qucai.sample.vo.CompanyTxnStatic;
import com.qucai.sample.vo.PersonalTxnStatic;
import com.qucai.sample.vo.PersonalTxnStatic;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.HistoricalTxnQuery;

@Repository
public interface HistoricalTxnQueryDao {
//    HistoricalTxnQuery selectBySearch(String t_FProd_Name, Date create_time);
	
	HistoricalTxnQuery selectByPrimaryKey(String t_Txn_ID_his);
	
	HistoricalTxnQuery selectByOrderCode(String OrderCode);
	
    List<HistoricalTxnQuery> findAllList(Map<String, Object> paramMap);
    
    List<HistoricalTxnQuery> findSearchList(Map<String, Object> paramMap);
    
    List<PersonalTxnStatic> SearchPersonalTxnStatic(Map<String, Object> paramMap);
    
    int ClearHisTxnPay(String[] hisTxnSelectedIDs);
    
    int DueHisTxnPay(String[] HisTxnSelectedIDs);
    
	int MarkFailedPayment(String OrderCode);

    List<CompanyTxnAmtWlyStatic> SearchCompanyTxnStaticAmtWly(Map<String, Object> paramMap);

    List<CompanyTxnStatic> SearchCompanyTxnStaticAmtDaily(Map<String, Object> paramMap);

//    PageInfo<HistoricalTxnQuery> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<HistoricalTxnQueryGrant> findManagerHistoricalTxnQueryGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<HistoricalTxnQuery> findAuthHistoricalTxnQueryListByManagerId(String managerId);
    
    //企业端的资源
//  List<HistoricalTxnQueryGrant> findEntHistoricalTxnQueryGrantAllList(String roleId);
}