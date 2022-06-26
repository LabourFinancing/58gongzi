package com.qucai.sample.dao;

import com.qucai.sample.entity.PaymentBatchPayUpload;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Repository
public interface PaymentBatchPayUploadDao {
	
//	PaymentBatchPayUpload selectByPrimaryKey(String batch_PB_company);


	int insertCustomerMachineByBatch(List<Map<String, Object>> cell);
	
	int deleteByPrimaryKey(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> SelectAllBatchList(Map<String, Object> paramMap);
	
	List<PaymentBatchPayUpload> checkDuplicateBatchUploadDebitCard(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> checkDuplicateBatchUploadPID(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> checkDuplicateBatchUploadMobil(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> duplicateMobileChkTperson(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> duplicateMobileChkTmanager(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> duplicateDebitCardChk(String batch_PB_batchID);
    
	List<PaymentBatchPayUpload> duplicatePIDChk(String batch_PB_batchID);
    
//    Map<String, Object> uploadOrgUser(String targetPath, String orgId,
//    		String deptId, String password, MultipartFile upFile);
//
    ResponseEntity<byte[]> download(String path, String id, HttpServletRequest req);
//    PersonalInfo selectByPrimaryKey(String t_P_id);

	List<PaymentBatchPayUpload> SelectAllBatch(Map<String, Object> paramMap);
	
	List<PaymentBatchPayUpload> SelectCompanyBatch(Map<String, Object> paramSearchMap);
	
	int insertManagerInfo(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxn(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxnview(Map<String, Object> paramMap);

	int insertBatchPersonalInfo(Map<String, Object> paramMap);
	
	int updateBatchPersonalTxnClearing(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusClose(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusOpen(Map<String, Object> paramMap);
	
	int updateBatchPersonalInfo(Map<String, Object> paramMap);
	
	int deleteByRefreshBatchPersonalCredit(Map<String, Object> paramMap);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

//    int updateByPrimaryKeySelective(PaymentBatchPayUpload record);
//
//	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName);
//	
//	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName);
    
//    List<PaymentBatchPayUpload> findAllList(Map<String, Object> paramMap);
//    
//    List<PaymentBatchPayUpload> findSearchList(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
//    int existPersonalInfoName(@Param("t_P_id") String t_P_id,@Param("t_P_PID") String t_P_PID);
//    
//    int checkPersonalInfoMobil(@Param("t_P_id") String t_P_id, @Param("t_P_Mobil") String t_P_Mobil);

    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}