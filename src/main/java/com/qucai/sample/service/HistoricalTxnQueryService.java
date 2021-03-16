package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.HistoricalTxnQuery;
//import com.qucai.sample.entity.resource;

public interface HistoricalTxnQueryService {
/*
 * Search HistoricalTxnQueryName , Search Other info , - Personal and so on.
 */
    
//    HistoricalTxnQuery selectBySearch(String t_FProd_Name, Date create_time);
	HistoricalTxnQuery selectByPrimaryKey(String t_Txn_ID_his);
	
	HistoricalTxnQuery selectByOrderCode(String OrderCode);
	
    List<HistoricalTxnQuery> findAllList(Map<String, Object> paramMap);
    
    List<HistoricalTxnQuery> findSearchList(Map<String, Object> paramMap);
    
    PageInfo<HistoricalTxnQuery> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<HistoricalTxnQuery> findSearchList(PageParam pp,Map<String, Object>paramMap);
    
    int ClearHisTxnPay(String[] HisTxnSelectedIDs);
    
    int DueHisTxnPay(String[] HisTxnSelectedIDs);
    
	int MarkFailedPayment(String OrderCode);
	
//   List<HistoricalTxnQuery> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<HistoricalTxnQuery> findAuthResourceListByManagerId(String managerId);
    
}