package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.TreasuryDBInfo;

@Repository
public interface TreasuryDBInfoDao {
    
    int updateByPrimaryKeySelective(TreasuryDBInfo record);
    
    int updateCreditStatus(String t_TreasuryDB_OrgName);
    
    int updateCreditRefresh(String t_TreasuryDB_OrgName);
    
    int insertSelective(TreasuryDBInfo record);

    TreasuryDBInfo selectByPrimaryKey(String t_TreasuryDB_ID);
    
    TreasuryDBInfo findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName);
    
	TreasuryDBInfo StatisticOverall(String t_TreasuryDB_OrgName);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<TreasuryDBInfo> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryDBInfo> findSearchList(Map<String, Object> paramMap);

	List<TreasuryDBInfo> findAgencyAllList(Map<String, Object> paramMap);
    
    int existTreasuryDBInfoName(@Param("t_TreasuryDB_OrgName") String t_TreasuryDB_OrgName);
    
    int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance);
  

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}