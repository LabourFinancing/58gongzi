//package com.qucai.sample.dao;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.ibatis.annotations.Param;
//import org.springframework.stereotype.Repository;
//
//import com.qucai.sample.entity.QuartzJob;
//
//@Repository
//public interface QuartzJobDao {
//    
//    int updateByPrimaryKeySelective(QuartzJob record);
//    
//    int updateCreditStatus(String t_TreasuryDB_OrgName);
//    
//    int updateCreditRefresh(String t_TreasuryDB_OrgName);
//    
//    int insertSelective(QuartzJob record);
//
//    QuartzJob selectByPrimaryKey(String t_TreasuryDB_ID);
//    
//    QuartzJob findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName);
//    
//	QuartzJob StatisticOverall(String t_TreasuryDB_OrgName);
//    
////    FinanceProduct selectBySearch(String t_FProd_Name, Date create_time);
//    
//    List<QuartzJob> findAllList(Map<String, Object> paramMap);
//    
//    List<QuartzJob> findSearchList(Map<String, Object> paramMap);
//
//	List<QuartzJob> findAgencyAllList(Map<String, Object> paramMap);
//    
//    int existQuartzJobName(@Param("t_TreasuryDB_OrgName") String t_TreasuryDB_OrgName);
//  
//
//    
//    //企业端的资源
////  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
//}  