package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.TreasuryDBInfo;
//import com.qucai.sample.entity.resource;

public interface TreasuryDBInfoService {

    int insertSelective(TreasuryDBInfo record); 
    
    int updateByPrimaryKeySelective(TreasuryDBInfo record);
    
    int updateCreditStatus(String t_TreasuryDB_OrgName);
    
    int updateCreditRefresh(String t_TreasuryDB_OrgName);
    
    TreasuryDBInfo selectByPrimaryKey(String t_Treasury_ID);
    
    TreasuryDBInfo findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName);
    
	TreasuryDBInfo StatisticOverall(String t_TreasuryDB_OrgName);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<TreasuryDBInfo> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryDBInfo> findSearchList(Map<String, Object> paramMap);
    
    List<TreasuryDBInfo> findAgencyAllList(Map<String, Object> paramMap);
    
    PageInfo<TreasuryDBInfo> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<TreasuryDBInfo> findSearchList(PageParam pp,Map<String, Object>paramMap);
    
    PageInfo<TreasuryDBInfo> findAgencyAllList(Map<String, Object> paramMap, PageParam pp);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existTreasuryDBInfoName(String t_TreasuryDB_OrgName);
    
    int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance);


    
}