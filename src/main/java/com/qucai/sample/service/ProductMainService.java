package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.ProductMain;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface ProductMainService {

    int deleteByPrimaryKey(String t_Product_ID);

    int insertSelective(ProductMain record);

    ProductMain selectByPrimaryKey(String t_Product_ID);

/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */

//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(ProductMain record);

    List<ProductMain> findAllList(Map<String, Object> paramMap);

    List<ProductMain> findSearchList(Map<String, Object> paramSearchMap);

    PageInfo<ProductMain> findAllList(Map<String, Object> paramMap, PageParam pp);

    PageInfo<ProductMain> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);

//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);

//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);

//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);

    boolean existFinanceProductName(String t_Product_ID, String t_Product_Name, Integer platform);

}