package com.qucai.sample.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.vo.StaffPrepayApplicationNew;


@Repository
public interface StaffPrepayApplicationDao {

    int insertSelective(StaffPrepayApplicationList record);
    
	int insertPayment(StaffPrepayApplicationPayment record);

	int deleteFailedPayment(String orderCode);
	
	int deleteTxnPayment(@Param("t_Txn_PrepayApplierName") String t_Txn_PrepayApplierName,@Param("t_Txn_PrepayApplierPID") String t_Txn_PrepayApplierPID,@Param("t_Txn_Paystatus") String t_Txn_Paystatus);
	
	int paymentSuccCheck(String orderCode);
	
	int refreshCredit(String t_TreasuryDB_OrgName_get);
	
	int updateFailedPayment(String orderCode);
	
	int updateCreditBalanceAmt(@Param("CreditBalanceAmtRefund") BigDecimal CreditBalanceAmtRefund,@Param("OrderCodeUpdate")String OrderCodeUpdate);
	
    StaffPrepayApplicationList FindFailedPayment(String orderCode);
    
	StaffPrepayApplicationList findPrepayApplierCreditBalance(String SeesionLoginMobil);
    
    List<StaffPrepayApplicationList> findAllNowList(Map<String, Object> paramMap);
    
    List<StaffPrepayApplicationList> findRealTimeSearchList(Map<String, Object> paramMap);
    
	List<StaffPrepayApplicationPayment> findFailedPaymentList(Map<String, Object> paramMap);
	
	List<StaffPrepayApplicationPayment> findSearchFailedPaymentList(Map<String, Object> paramMap);
	

//    StaffPrepayApplication selectByPrimaryKey(String t_Txn_ID);
    
    StaffPrepayApplicationList findPrepayApplierCredit(String seesionLoginMobil);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    StaffPrepayApplicationNew findAuthPrepayApplier(String seesionLoginMobil);
    
    StaffPrepayApplicationNew findSelectedByFProdName(String t_FProd_Name);
    
    List<StaffPrepayApplicationNew> findAuthFinanceProd(Map<String, Object> paramMap);
    
    List<StaffPrepayApplicationList> findAllList(Map<String, Object> paramMap);
    
    List<StaffPrepayApplicationList> findSearchList(Map<String, Object> paramMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existStaffPrepayApplicationName(@Param("t_Txn_ID") String ID, @Param("t_Txn_PrepayApplierName") String Name, @Param("platform") Integer platform);

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}