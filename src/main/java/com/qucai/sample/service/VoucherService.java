package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Voucher;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface VoucherService {
    
    int deleteByPrimaryKey(String t_Voucher_ID);

    int insertSelective(Voucher record);

    Voucher selectByPrimaryKey(String t_Voucher_ID);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(Voucher record);
    
    List<Voucher> findAllList(Map<String, Object> paramMap);
    
    List<Voucher> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<Voucher> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    PageInfo<Voucher> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);
    
//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);
    
//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);
    
    boolean existVoucherName(String t_Voucher_ID, String t_Voucher_ProdCat);
    
    boolean checkVoucherMobil(String t_Voucher_ID, String t_Voucher_ProdCat);

}