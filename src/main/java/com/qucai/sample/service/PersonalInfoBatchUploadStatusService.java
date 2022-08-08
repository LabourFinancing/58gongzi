package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalInfo;
//import com.qucai.sample.entity.resource;
import com.qucai.sample.entity.PersonalInfoBatchUploadStatus;

public interface PersonalInfoBatchUploadStatusService {
    
    int deleteByPrimaryKey(String t_batch_perslUploadStatus_id); // int deleteByPrimaryKey(String t_P_id);

	PersonalInfoBatchUploadStatus selectByPrimaryKey(String t_batch_perslUploadId);
    
	int updateCompanyStaffsCreditOn(String t_batch_perslUploadStatus_batchid);
	
	int updateCompanyStaffsCreditOff(String t_batch_perslUploadStatus_batchid);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalInfoBatchUploadStatus record);
    
    List<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap);
    
    List<PersonalInfoBatchUploadStatus> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PersonalInfoBatchUploadStatus> findSearchList(PageParam pp,Map<String, Object>paramSearchMap);

	int insertSelective(Map<String, Object> paramSearchMap);

    int checkBatchPersonalClearStatus(Map<String, Object> paramMap);
    
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