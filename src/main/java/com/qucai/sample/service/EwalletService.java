package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Ewallet;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface EwalletService {

    int deleteByPrimaryKey(String t_personalewallet_ID);

    int insertSelective(Ewallet record);

    Ewallet selectByPrimaryKey(String t_personalewallet_ID);

    /*
     * Search FinanceProductName , Search Other info , - Personal and so on.
     */

//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(Ewallet record);

    List<Ewallet> findAllList(Map<String, Object> paramMap);

    List<Ewallet> findSearchList(Map<String, Object> paramSearchMap);

    PageInfo<Ewallet> findAllList(Map<String, Object> paramMap, PageParam pp);

    PageInfo<Ewallet> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);

    boolean existEwalletName(String t_personalewallet_ID, String t_personalewallet_ApplierPID);


}