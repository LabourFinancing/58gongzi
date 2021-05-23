package com.qucai.sample.service;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.EwalletTxn;

import java.util.List;
import java.util.Map;
//import com.qucai.sample.entity.resource;

public interface EwalletTxnService {

    int deleteByPrimaryKey(String t_WalletTxn_ID);

    int insertSelective(EwalletTxn record);

    EwalletTxn selectByPrimaryKey(String t_WalletTxn_ID);
/*
 * Search FinanceProductName , Search Other info , - Personal and so on.
 */

//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(EwalletTxn record);

    List<EwalletTxn> findAllList(Map<String, Object> paramMap);

    List<EwalletTxn> findSearchList(Map<String, Object> paramSearchMap);

    PageInfo<EwalletTxn> findAllList(Map<String, Object> paramMap, PageParam pp);

    PageInfo<EwalletTxn> findSearchList(PageParam pp, Map<String, Object> paramSearchMap);

//   List<FinanceProduct> findTreetableList(Map<String, Object> paramMap);

//    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);

//    List<FinanceProduct> findAuthResourceListByManagerId(String managerId);

    boolean existEwalletTxnName(String t_WalletTxn_ID, String t_WalletTxn_PayerPID);

    boolean checkEwalletTxnMobil(String t_WalletTxn_ID, String t_WalletTxn_PayerPID);

}