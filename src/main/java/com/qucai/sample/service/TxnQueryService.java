package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.TxnQuery;


public interface TxnQueryService {
    
    int deleteByPrimaryKey(String t_TxnQuery_ID);

    int insertSelective(TxnQuery record); 
    
    TxnQuery selectByPrimaryKey(String t_TxnQuery_ID);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(TxnQuery record);
    
    int findSearchList(StaffPrepayApplicationList record);
    
    List<TxnQuery> findAllList(Map<String, Object> paramMap);
    
    
    PageInfo<TxnQuery> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    

    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    
}