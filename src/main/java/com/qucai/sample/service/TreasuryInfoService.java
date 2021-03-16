package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.TreasuryInfo;
//import com.qucai.sample.entity.resource;

public interface TreasuryInfoService {

    int insertSelective(TreasuryInfo record); 
    
    int deleteByPrimaryKey(String t_Treasury_ID);
    
	int updateByPrimaryKey(TreasuryInfo treasuryInfo);
    
    TreasuryInfo selectByPrimaryKey(String t_Treasury_ID);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
    
    List<TreasuryInfo> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryInfo> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<TreasuryInfo> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<TreasuryInfo> findSearchList(PageParam pp,Map<String, Object>paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existTreasuryInfoName(String t_Treasury_OrgName);


}