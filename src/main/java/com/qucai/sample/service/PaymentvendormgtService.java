package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Paymentvendormgt;
//import com.qucai.sample.entity.resource;

public interface PaymentvendormgtService {

    int insertSelective(Paymentvendormgt record); 
    
    int updateByPrimaryKeySelective(Paymentvendormgt record);
    
    int updateCreditStatus(String t_Pymt_Name);
    
    int updateCreditRefresh(String t_Pymt_Name);
    
    Paymentvendormgt selectByPrimaryKey(String t_Pymt_CertificationCode);
    
    Paymentvendormgt findOrgTreasuryCurrBalance(String t_Pymt_Name);
    
    Paymentvendormgt StatisticOverall(String t_Pymt_Name);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<Paymentvendormgt> findAllList(Map<String, Object> paramMap);
    
    List<Paymentvendormgt> findSearchList(Map<String, Object> paramMap);
    
    List<Paymentvendormgt> findAgencyAllList(Map<String, Object> paramMap);
    
    PageInfo<Paymentvendormgt> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<Paymentvendormgt> findSearchList(PageParam pp,Map<String, Object>paramMap);
    
    PageInfo<Paymentvendormgt> findAgencyAllList(Map<String, Object> paramMap, PageParam pp);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existPaymentvendormgtName(String t_Pymt_Name);


    
}