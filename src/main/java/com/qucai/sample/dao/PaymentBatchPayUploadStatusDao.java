package com.qucai.sample.dao;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PaymentBatchPayUploadStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PaymentBatchPayUploadStatusDao {
	
//	PaymentBatchPayUpload selectByPrimaryKey(String batch_PB_company);
	  
    int deleteByPrimaryKey(String t_batch_perslUploadId);

	PaymentBatchPayUploadStatus selectByPrimaryKey(String t_batch_perslUploadId);
    
	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get);
	
	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get);
	
	int insertSelective(Map<String, Object> paramSearchMap);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalInfo record);
    
    List<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap);
    
    List<PaymentBatchPayUploadStatus> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PaymentBatchPayUploadStatus> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);

	int updateByPrimaryKeySelective(PaymentBatchPayUploadStatus record);
    
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