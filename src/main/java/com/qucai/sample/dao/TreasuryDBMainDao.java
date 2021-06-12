package com.qucai.sample.dao;

import com.qucai.sample.entity.TreasuryDBMain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TreasuryDBMainDao {
    
    int updateByPrimaryKeySelective(TreasuryDBMain record);
    
    int updateCreditStatus(String t_TreasuryDB_OrgName);
    
    int updateCreditRefresh(String t_TreasuryDB_OrgName);
    
    int insertSelective(TreasuryDBMain record);

    TreasuryDBMain selectByPrimaryKey(String t_TreasuryDB_ID);
    
    TreasuryDBMain findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName);
    
	TreasuryDBMain StatisticOverall(String t_TreasuryDB_OrgName);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<TreasuryDBMain> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryDBMain> findSearchList(Map<String, Object> paramMap);

	List<TreasuryDBMain> findAgencyAllList(Map<String, Object> paramMap);
    
    int existTreasuryDBMainName(@Param("t_TreasuryDB_OrgName") String t_TreasuryDB_OrgName);
    
    int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance);
  

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}