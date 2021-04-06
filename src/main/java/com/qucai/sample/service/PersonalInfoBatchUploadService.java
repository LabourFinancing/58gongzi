package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.qucai.sample.entity.PersonalInfoBatchUpload;

//import com.qucai.sample.entity.resource;

public interface PersonalInfoBatchUploadService {
    /**
     * @param targetPath
     * @param orgId
     * @param deptId
     * @param password
     * @param upFile
     * @return
     */
//  public Map<String, Object> uploadOrgUser(String targetPath, Integer orgId, Integer deptId, String password, MultipartFile upFile);
    
//	PersonalInfoBatchUpload selectByPrimaryKey(String batch_PB_company);
//	
//	int insertSelective(PersonalInfoBatchUpload record);
//	
//	Map<String, Object> uploadOrgUser(String targetPath, String orgId,
//			String deptId, String password, MultipartFile upFile);
	
	int insertCustomerMachineByBatch(List<Map<String, Object>> cell);
	
	List<PersonalInfoBatchUpload> duplicateDebitCardChk(String batch_PB_batchID);
	
	int deleteByPrimaryKey(String t_batch_perslUploadStatus_id); // int deleteByPrimaryKey(String batch_PB_batchID);

	List<PersonalInfoBatchUpload> duplicateMobileChkTmanager(String batch_PB_batchID);

	List<PersonalInfoBatchUpload> duplicateMobileChkTperson(String batch_PB_batchID);
	
	List<PersonalInfoBatchUpload> duplicatePIDChk(String batch_PB_batchID);
	
    public ResponseEntity<byte[]> download(String path, String id, HttpServletRequest req);

	List<PersonalInfoBatchUpload> SelectCompanyBatch(Map<String, Object> paramSearchMap);
	
	List<PersonalInfoBatchUpload> SelectAllBatch(Map<String, Object> paramMap);

	int insertManagerInfo(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxn(Map<String, Object> paramMap);

	int insertRoleTrManagerInfoTxnview(Map<String, Object> paramMap);

	int insertBatchPersonalInfo(Map<String, Object> paramMap);
	
	int updateBatchPersonalTxnClearing(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusClose(Map<String, Object> paramMap);
	
	int updateBatchPersonalStatusOpen(Map<String, Object> paramMap);
	
	int updateBatchPersonalInfo(Map<String, Object> paramMap);
	
	int deleteByRefreshBatchPersonalCredit(Map<String, Object> paramMap);

	List<PersonalInfoBatchUpload> checkDuplicateBatchUploadMobil(String batch_PB_batchID);

	List<PersonalInfoBatchUpload> checkDuplicateBatchUploadPID(String batch_PB_batchID);

	List<PersonalInfoBatchUpload> checkDuplicateBatchUploadDebitCard(String batch_PB_batchID);

	List<PersonalInfoBatchUpload> SelectAllBatchList(Map<String, Object> paramMap);


}