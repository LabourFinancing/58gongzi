package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PaymentBatchPayUploadStatus;

import java.util.List;
import java.util.Map;

//import com.qucai.sample.entity.resource;

public interface PaymentBatchPayUploadStatusService {
    
    int deleteByPrimaryKey(String t_batch_paymentUploadStatus_id); // int deleteByPrimaryKey(String t_P_id);

	PaymentBatchPayUploadStatus selectByPrimaryKey(String t_batch_paymentUploadId);
    
	int updateCompanyStaffsCreditOn(String t_batch_paymenetUploadStatus_batchid);
	
	int updateCompanyStaffsCreditOff(String t_batch_paymenetUploadStatus_batchid);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PaymentBatchPayUploadStatus record);
    
    List<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap);
    
    List<PaymentBatchPayUploadStatus> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PaymentBatchPayUploadStatus> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);

	int insertSelective(Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
//
//	List<PersonalInfo> findSearchCompanyDist(Map<String, Object> paramSearchMap);
//
//	List<PersonalInfo> findSubCompany(Map<String, Object> paramMap);
//	
//	List<PersonalInfo> findAgencyCompany(Map<String, Object> paramMap);

}