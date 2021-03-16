package com.qucai.sample.vo;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.TreasuryDBInfoService;
import com.qucai.sample.service.TreasuryInfoService;
import com.qucai.sample.util.ShiroSessionUtil;

@Controller
public class OverallStatisticRefresh{
	
	@Autowired
    private TreasuryInfoService treasuryInfoService; //申明一个对象
    
    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象
    
//返回总余额	
	public int ReturnOverallCredit(BigDecimal OverAllFee,String TxnID) throws Exception{
    	TreasuryDBInfo entity = null;
		entity = new TreasuryDBInfo();
		String t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
		//返回机构余额
		TreasuryDBInfo treasuryOrgDBInfoUpdate = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
	  	BigDecimal tTreasuryOrgDBBalance = treasuryOrgDBInfoUpdate.getT_TreasuryDB_Balance().add(OverAllFee);
	  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Balance(tTreasuryOrgDBBalance);    
	  	treasuryOrgDBInfoUpdate.setModifier(ShiroSessionUtil.getLoginSession().getId());    
	  	treasuryOrgDBInfoUpdate.setModify_time(new Date());  
	  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Comment("自动加值"); 
	    int RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryOrgDBInfoUpdate);
		//
         if (RS == 1) {         	   
      	   t_TreasuryDB_OrgName = "ALL";
      	   TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
		   BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().add(OverAllFee);
		   treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
		   RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
           return RS;
         } else {
		   return RS;
         }
	}

	
//返回给个人当月剩余余额
//	public String ReturnPersonalCredit(StaffPrepayApplicationPayment staffPrepayApplicationPayment) throws Exception{
////	获取后台失败交易记录
////		StaffPrepayApplicationPayment StaffPrepayApplicationPaymentAll = 
//////	探测支付商返回结果，每条循环执行，不为0003的返回余额
//////  返还企业及个人余额
////	    String retData = QueryOrderDemo.main(staffPrepayApplicationPayment);
////	    
////	    return retData;
//
//	}
}
