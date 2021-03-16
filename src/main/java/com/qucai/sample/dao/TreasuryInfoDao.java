package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.TreasuryInfo;

@Repository
public interface TreasuryInfoDao {
	
    int deleteByPrimaryKey(String t_Treasury_ID);

    int insertSelective(TreasuryInfo record);
    
    int updateByPrimaryKey(TreasuryInfo record);

    TreasuryInfo selectByPrimaryKey(String t_Treasury_ID);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    
    List<TreasuryInfo> findAllList(Map<String, Object> paramMap);
    
    List<TreasuryInfo> findSearchList(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existTreasuryInfoName(@Param("t_Treasury_OrgName") String t_Treasury_OrgName);
    
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}