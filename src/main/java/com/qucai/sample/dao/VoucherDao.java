package com.qucai.sample.dao;

import com.qucai.sample.entity.Voucher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VoucherDao {
    int deleteByPrimaryKey(String t_Voucher_ID);

    int insertSelective(Voucher record);

    Voucher selectByPrimaryKey(String t_Voucher_ID);
    
//    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);

    int updateByPrimaryKeySelective(Voucher record);

	int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName);
	
	int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName);
	
	List<Voucher> findSubCompany(Map<String, Object> paramMap);
	
	List<Voucher> findAgencyCompany(Map<String, Object> paramMap);
    
    List<Voucher> findAllList(Map<String, Object> paramMap);
    
    List<Voucher> findSearchList(Map<String, Object> paramSearchMap);
    
    List<Voucher> findSearchCompanyDist(Map<String, Object> paramSearchMap);
    
//    PageInfo<FinanceProduct> findAllList(Map<String, Object> paramMap, PageParam pp);
    
//  List<FinanceProductGrant> findManagerFinanceProductGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
//  String findResourceChildId(String id);
    
//    int deleteByPrimaryKeyStr(String t_FProd_ID);
    
//    List<FinanceProduct> findAuthFinanceProductListByManagerId(String managerId);
    
    int existVoucherName(@Param("t_Voucher_ID") String t_P_id, @Param("t_Voucher_ProdCat") String t_P_PID);
    
    int checkVoucherMobil(@Param("t_Voucher_ID") String t_P_id, @Param("t_Voucher_ProdCat") String t_Voucher_ProdCat);



    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}