package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PersonalInfoBatchUpload;
import com.qucai.sample.entity.PersonalInfoBatchUploadStatus;

@Repository
public interface PersonalInfoBatchUploadStatusDao {
	
//	PersonalInfoBatchUpload selectByPrimaryKey(String batch_PB_company);
	  
    int deleteByPrimaryKey(String t_batch_perslUploadId);

	PersonalInfoBatchUploadStatus selectByPrimaryKey(String t_batch_perslUploadId);
    
	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get);
	
	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get);
	
	int insertSelective(Map<String, Object> paramSearchMap);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(PersonalInfo record);
    
    List<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap);
    
    List<PersonalInfoBatchUploadStatus> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<PersonalInfoBatchUploadStatus> findSearchList(PageParam pp,Map<String, Object>paramSearchMap);

	int updateByPrimaryKeySelective(PersonalInfoBatchUploadStatus record);
    
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