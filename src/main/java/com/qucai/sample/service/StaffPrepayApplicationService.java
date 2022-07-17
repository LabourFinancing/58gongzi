package com.qucai.sample.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.vo.StaffPrepayApplicationNew;
//import com.qucai.sample.entity.resource;

public interface StaffPrepayApplicationService {

    int insertSelective(StaffPrepayApplicationList record); 
    
    int insertPayment(StaffPrepayApplicationPayment record);
    
	int deleteFailedPayment(String OrderCode);
	
	int deleteTxnPayment(String t_Txn_PrepayApplierName,String t_Txn_PrepayApplierPID,String t_Txn_Paystatus);
	
	int paymentSuccCheck(String OrderCode);
	
	int refreshCredit(String t_TreasuryDB_OrgName_get);
	
	int updateFailedPayment(String OrderCode);
	
	int updateCreditBalanceAmt(@Param("CreditBalanceAmtRefund") BigDecimal CreditBalanceAmtRefund,@Param("OrderCodeUpdate")String OrderCodeUpdate);
	
	StaffPrepayApplicationList FindFailedPayment(String OrderCode);
	
	StaffPrepayApplicationList findPrepayApplierCreditBalance(String SeesionLoginMobil);
    
	List<StaffPrepayApplicationPayment> findFailedPaymentList(Map<String, Object> paramMap);
	
	List<StaffPrepayApplicationPayment> findSearchFailedPaymentList(Map<String, Object> paramMap);
    
//    StaffPrepayApplication selectByPrimaryKey(String t_Txn_ID);
    
	StaffPrepayApplicationList findPrepayApplierCredit(String seesionLoginMobil);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    StaffPrepayApplicationNew findSelectedByFProdName(String t_FProd_Name);
    
    List<StaffPrepayApplicationList> findAllNowList(Map<String, Object> paramMap);

    StaffPrepayApplicationNew findAuthPrepayApplier(Map<String, Object> SeesionLoginMobil);
    
    List<StaffPrepayApplicationList> findRealTimeSearchList(Map<String, Object> paramMap);
//    StaffPrepayApplicationNew selectAuthFinanceProd(String t_FProd_Name);
    
    List<StaffPrepayApplicationNew> findAuthFinanceProd(Map<String, Object> paramMap);

    List<StaffPrepayApplicationNew> findAuthSalaryOndemandProd(Map<String, Object> paramMap);
    
    List<StaffPrepayApplicationList> findAllList(Map<String, Object> paramMap);
    
    List<StaffPrepayApplicationList> findSearchList(Map<String, Object> paramMap);
    
    PageInfo<StaffPrepayApplicationList> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<StaffPrepayApplicationList> findSearchList(PageParam pp,Map<String, Object>paramMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
      boolean existStaffPrepayApplicationName(String t_Txn_ID, String t_Txn_PrepayApplierName, Integer platform);




   
}