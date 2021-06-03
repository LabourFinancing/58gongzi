package com.qucai.sample.dao;

import com.qucai.sample.entity.ProductMain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductMainDao {
    int deleteByPrimaryKey(String t_Product_ID);

    int insertSelective(ProductMain record);

    ProductMain selectByPrimaryKey(String t_Product_ID);

//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(ProductMain record);

    List<ProductMain> findAllList(Map<String, Object> paramMap);

    List<ProductMain> findSearchList(Map<String, Object> paramSearchMap);

//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);

//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);

//  String findResourceChildId(String id);

//    int deleteByPrimaryKeyStr(String t_FProd_ID);

//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);

    int existFinanceProductName(@Param("t_Product_ID") String ID, @Param("t_Product_Name") String Name, @Param("platform") Integer platform);

    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}