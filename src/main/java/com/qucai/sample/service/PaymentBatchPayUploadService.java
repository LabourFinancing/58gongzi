package com.qucai.sample.service;

import com.qucai.sample.entity.PaymentBatchPayUpload;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//import com.qucai.sample.entity.resource;

public interface PaymentBatchPayUploadService {

	int insertCustomerMachineByBatch(List<Map<String, Object>> cell);
	
	List<PaymentBatchPayUpload> duplicateDebitCardChk(String batch_PB_batchID);
	
	int deleteByPrimaryKey(String t_batch_perslUploadStatus_id); // int deleteByPrimaryKey(String batch_PB_batchID);

	List<PaymentBatchPayUpload> duplicateMobileChkTmanager(String batch_PB_batchID);

	List<PaymentBatchPayUpload> duplicateMobileChkTperson(String batch_PB_batchID);
	
	List<PaymentBatchPayUpload> duplicatePIDChk(String batch_PB_batchID);
	
    public ResponseEntity<byte[]> download(String path, String id, HttpServletRequest req);

	List<PaymentBatchPayUpload> SelectCompanyBatch(Map<String, Object> paramSearchMap);
	
	List<PaymentBatchPayUpload> SelectAllBatch(Map<String, Object> paramMap);

	int insertManagerInfo(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxn(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxnview(Map<String, Object> paramMap);

	int insertBatchPersonalInfo(Map<String, Object> paramMap);
	
	int updateBatchPersonalTxnClearing(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusClose(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusOpen(Map<String, Object> paramMap);
	
	int updateBatchPersonalInfo(Map<String, Object> paramMap);
	
	int deleteByRefreshBatchPersonalCredit(Map<String, Object> paramMap);

	List<PaymentBatchPayUpload> checkDuplicateBatchUploadMobil(String batch_PB_batchID);

	List<PaymentBatchPayUpload> checkDuplicateBatchUploadPID(String batch_PB_batchID);

	List<PaymentBatchPayUpload> checkDuplicateBatchUploadDebitCard(String batch_PB_batchID);

	List<PaymentBatchPayUpload> SelectAllBatchList(Map<String, Object> paramMap);


}