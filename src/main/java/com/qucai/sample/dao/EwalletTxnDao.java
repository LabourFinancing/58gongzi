package com.qucai.sample.dao;

import com.qucai.sample.entity.EwalletTxn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EwalletTxnDao {
    int deleteByPrimaryKey(String t_WalletTxn_ID);

    int insertSelective(EwalletTxn record);

    EwalletTxn selectByPrimaryKey(String t_WalletTxn_ID);

//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(EwalletTxn record);

    List<EwalletTxn> findAllList(Map<String, Object> paramMap);

    List<EwalletTxn> findSearchList(Map<String, Object> paramSearchMap);

//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);

//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);

//  String findResourceChildId(String id);

//    int deleteByPrimaryKeyStr(String t_FProd_ID);

//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);

    int existEwalletTxnName(@Param("t_WalletTxn_ID") String t_P_id, @Param("t_WalletTxn_PayerPID") String t_P_PID);

    int checkEwalletTxnMobil(@Param("t_WalletTxn_ID") String t_P_id, @Param("t_WalletTxn_PayerPID") String t_P_Mobil);


    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}