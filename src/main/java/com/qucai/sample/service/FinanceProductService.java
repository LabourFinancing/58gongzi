package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.entity.resource;

public interface FinanceProductService {
    
    int deleteByPrimaryKey(String t_FProd_ID);

    int insertSelective(FinanceProduct record); 
    
    FinanceProduct selectByPrimaryKey(String t_FProd_ID);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(FinanceProduct record);
    
    List<FinanceProduct> findAllList(Map<String, Object> paramMap);
    
    List<FinanceProduct> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<FinanceProduct> findSearchList(PageParam pp,Map<String, Object>paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existFinanceProductName(String t_FProd_ID, String t_FProd_Name, Integer platform);
    
}