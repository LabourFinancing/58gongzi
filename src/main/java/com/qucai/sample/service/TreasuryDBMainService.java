package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.TreasuryDBMain;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface TreasuryDBMainService {

    int insertSelective(TreasuryDBMain record); 
    
    int updateByPrimaryKeySelective(TreasuryDBMain record);
    
//    int updateCreditStatus(String t_TreasuryDB_OrgName);
    
//    int updateCreditRefresh(String t_TreasuryDB_OrgName);
    
    TreasuryDBMain selectByPrimaryKey(String t_Treasury_ID);
    
 //   TreasuryDBMain findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName);
    
//	TreasuryDBMain StatisticOverall(String t_TreasuryDB_OrgName);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<TreasuryDBMain> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryDBMain> findSearchList(Map<String, Object> paramMap);
    
    List<TreasuryDBMain> findAgencyAllList(Map<String, Object> paramMap);
    
    PageInfo<TreasuryDBMain> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<TreasuryDBMain> findSearchList(PageParam pp, Map<String, Object> paramMap);
    
    PageInfo<TreasuryDBMain> findAgencyAllList(Map<String, Object> paramMap, PageParam pp);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existTreasuryDBMainName(String t_TreasuryDB_OrgName);
//    int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance);


    
}