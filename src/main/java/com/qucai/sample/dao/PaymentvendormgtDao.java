package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.Paymentvendormgt;

@Repository
public interface PaymentvendormgtDao {
    
    int updateByPrimaryKeySelective(Paymentvendormgt record);
    
    int updateCreditStatus(String t_Pymt_Name);
    
    int updateCreditRefresh(String t_Pymt_Name);
    
    int insertSelective(Paymentvendormgt record);

    Paymentvendormgt selectByPrimaryKey(String t_TreasuryDB_ID);
    
    Paymentvendormgt findOrgTreasuryCurrBalance(String t_Pymt_Name);
    
	Paymentvendormgt StatisticOverall(String t_Pymt_Name);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<Paymentvendormgt> findAllList(Map<String, Object> paramMap);
    
    List<Paymentvendormgt> findSearchList(Map<String, Object> paramMap);

	List<Paymentvendormgt> findAgencyAllList(Map<String, Object> paramMap);
    
    int existPaymentvendormgtName(@Param("t_Pymt_Name") String t_Pymt_Name);
  

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}