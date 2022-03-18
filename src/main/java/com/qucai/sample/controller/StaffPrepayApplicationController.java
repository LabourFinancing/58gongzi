package com.qucai.sample.controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qucai.sample.entity.*;
import com.qucai.sample.service.*;
import com.qucai.sample.util.*;
import com.qucai.sample.vo.MobileEwalletDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.AgentPayDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.MerBalanceQueryDemo;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.AmtQueryServlet;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.PayServlet;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.MerBalanceQueryDemo;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.vo.StaffPrepayApplicationNew;

@Controller
@RequestMapping(value = "/StaffPrepayApplicationController")
public class StaffPrepayApplicationController {

	// 必须把new financeProduct的列进行全面修改, 新建financeProductService

	private static final int GapDate0 = 0;

	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
 
	@Autowired
	private PersonalInfoService PersonalInfoService; // 申明一个对象
	
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

	@Autowired
	private FinanceProductService FinanceProductService; // 申明一个对象
	
	@Autowired
	private TreasuryDBInfoService TreasuryDBInfoService; // 申明一个对象
	
	@Autowired
    private TreasuryInfoService treasuryInfoService; //申明一个对象
    
    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象
    
    @Autowired
    private PaymentvendormgtService paymentvendormgtService;

    @Autowired
    private TreasuryDBMainService treasuryDBMainService;

	private BigDecimal t_FProd_ServiceFee_Total;
	
	//
	@ModelAttribute
	public StaffPrepayApplicationList get(
			@RequestParam(required = false) String t_txn_P_Mobil) {
		StaffPrepayApplicationList entity = null;		
		if (entity == null) {
			entity = new StaffPrepayApplicationList();
		}
		return entity;
	}

	/**
	 * 改动：根据所属平台来确定是哪个平台的资源 , 新建申请
	 * 
	 * @param t_Txn_CreditPrepayCurrentNum
	 * @throws Exception 
	 */

	@RequestMapping(value = {"staffPrepayApplicationNew"})
//    @ResponseBody
    public String staffPrepayApplicationNew(StaffPrepayApplicationList staffPrepayApplication,@RequestParam( defaultValue = "0" ) Integer platform,String operationType,String FPROD_cate,
    		String t_Txn_ID, String t_Txn_PID,String t_Txn_Mobil,String SeesionLoginMobil,Date create_time,BigDecimal t_Txn_CreditPrepayCurrentNum, Integer tTxnPrepayDays,
    		BigDecimal t_P_CreditPrepaySalaryAmount,Integer t_P_PayrollDate,BigDecimal t_FProd_ServiceFee,BigDecimal t_FProd_Poundage,String t_FProd_TierPoundage,String t_Txn_PrepayClear,String t_P_Employmentstatus,
    		String t_FProd_Name,BigDecimal t_FProd_ServiceFee_Percent,BigDecimal t_FProd_Poundage_Percent,BigDecimal t_Txn_CreditPrepayBalanceNum,Integer t_Txn_PrepayCounts,Date t_Txn_PrepayDate,
    		String t_P_EmploymentCategory,BigDecimal t_P_SocialSecurityBaseAmount,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	
		//Verify Company Creditline
    	TreasuryDBInfo entityOverall = null;
    	entityOverall = new TreasuryDBInfo();
		String t_TreasuryDB_OrgName =  ShiroSessionUtil.getLoginSession().getCompany_name();
		String t_O_OrgName =  ShiroSessionUtil.getLoginSession().getCompany_name();
    	TreasuryDBInfo treasuryDBInfoGetStatistic = TreasuryDBInfoService.StatisticOverall(t_TreasuryDB_OrgName);
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
		String PaymentTunnel = AgencyOrgnization.getT_O_OrgPayrollBankaccount();  // get original paymenttunnel
    	String merchantId = null;String PaymentSwitch = null;
    	BigDecimal InitialBalance = null;
		Map<String, Object> rs = new HashMap<String, Object>();

//checking Payment Account Balance
        
    	if(PaymentTunnel.equalsIgnoreCase("电银支付")) {
    		if (!AgencyOrgnization.getT_O_OrgChinaebiAcc().equals(null)){
    			merchantId = AgencyOrgnization.getT_O_OrgChinaebiAcc();
        	    PaymentSwitch = "shdy";
        	    String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
				JSONObject obj = (JSONObject) JSON.parse(ret_Data);
				String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
				BigDecimal ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
				InitialBalance = ebibalance;
				System.out.println("Query Chinaebipay balance:");
				System.out.println(ebibalance);
    		}else {
    			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
    		}
    	}else if (PaymentTunnel.equalsIgnoreCase("杉德支付")){
    		if (!AgencyOrgnization.getT_O_OrgChinaebiAcc().equals(null)){
        	    merchantId = AgencyOrgnization.getT_O_OrgSandeAcc();
        	    PaymentSwitch = "shsd";
        	    JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId);
  		    	String BalanceData = (String) JSONretdata.get("balance");
  		    	BigDecimal Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
//                BigDecimal Sandebalance = new BigDecimal("177.00").setScale(2,BigDecimal.ROUND_DOWN);
				InitialBalance = Sandebalance;
  				System.out.println("Query Sande balance:");
  				System.out.println(Sandebalance);
    		}else {
    			rs.put("retData","-1");
    			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
    		}
    	}else{
    		OrganizationInfo AgencyPaymentOrgnization = organizationInfoService.selectAgencyName(PaymentTunnel);
    		String PaymentAccTunnel = AgencyPaymentOrgnization.getT_O_OrgPayrollBankaccount();
    		switch(PaymentAccTunnel){
    		case "电银支付" :
    			merchantId = AgencyPaymentOrgnization.getT_O_OrgChinaebiAcc();
        	    PaymentSwitch = "shdy";
        	    String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
				JSONObject obj = (JSONObject) JSON.parse(ret_Data);
				String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
				BigDecimal ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
				InitialBalance = ebibalance;
				System.out.println("Query Chinaebipay balance:");
				System.out.println(ebibalance);
				break;
    		case "杉德支付" :
    			merchantId = AgencyPaymentOrgnization.getT_O_OrgSandeAcc();
        	    PaymentSwitch = "shsd";
        	    JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId);
  		    	String BalanceData = (String) JSONretdata.get("balance");
  		    	BigDecimal Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
				InitialBalance = Sandebalance;
  				System.out.println("Query Sande balance:");
  				System.out.println(Sandebalance);
				break;
    		default:
        	    PaymentSwitch = null;
        	    InitialBalance = new BigDecimal("0.00");
    			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
    		}
    	}
    	
//        InitialBalance = new BigDecimal("6100.00"); // debug using
        if(InitialBalance.intValue() <= treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue()){
            String RetCode = HttpJsonExample.TreasuryOutBal(InitialBalance.intValue(), treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue(), treasuryDBInfoGetStatistic.getT_TreasuryDB_OrgName());
            StringBuffer OutOfBalance = new StringBuffer();
            StringBuffer RetMsg = OutOfBalance.append(String.valueOf(InitialBalance.intValue())).append("<>").append(treasuryDBInfoGetStatistic.getT_TreasuryDB_OrgName()).append(":")
                .append(treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue());
            return  "staffPrepayApplication/OverCreditLine";
        }
        
    	if (InitialBalance.intValue() <= Integer.valueOf(100) || 
    		InitialBalance.intValue() <= treasuryDBInfoGetStatistic.getT_TreasuryDB_Prooffund().intValue() || 
            AgencyOrgnization.getT_O_OrgStatus().equalsIgnoreCase("off"))
        {   StringBuffer debugger = new StringBuffer();
            System.out.print(String.valueOf(debugger.append(" - InitialVal:").append(InitialBalance.intValue()).append(" - TreasuryInitVal:").append(treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue())
            .append(" - TreasuryProofFund:").append(treasuryDBInfoGetStatistic.getT_TreasuryDB_Prooffund().intValue()).append(" - OrgStatus:").append(AgencyOrgnization.getT_O_OrgStatus())));
   		   return  "staffPrepayApplication/OverCreditLine";
    	}
 //checking Payment Account Balance end	
    	
    	Map<String,Object> paramMap = new HashMap<String, Object>();
    	model.addAttribute("t_FProd_Name", t_FProd_Name);																																																																																																																																																																																																				

    	SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();

        if (SeesionLoginMobil != null) {
        
        StaffPrepayApplicationNew staffPrepayApplicationNew = staffPrepayApplicationService.findAuthPrepayApplier(SeesionLoginMobil);
        StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(SeesionLoginMobil);
        
//* get User Personal info
        staffPrepayApplicationNew.getT_P_Name();
        staffPrepayApplicationNew.getT_P_PID();
        staffPrepayApplicationNew.getT_P_Company();
        staffPrepayApplicationNew.getT_P_CompanyNum();
        staffPrepayApplicationNew.getT_P_Mobil();   
        staffPrepayApplicationNew.getT_P_Employmentstatus();
        staffPrepayApplicationNew.getT_P_PayrollDebitcardBankName();
        staffPrepayApplicationNew.getT_P_PayrollDebitcardNum();

        t_P_CreditPrepaySalaryAmount = staffPrepayApplicationNew.getT_P_CreditPrepaySalaryAmount();
        
        t_P_EmploymentCategory = staffPrepayApplicationNew.getT_P_EmploymentCategory();
        t_P_PayrollDate = staffPrepayApplicationNew.getT_P_PayrollDate();    
        t_P_Employmentstatus = staffPrepayApplicationNew.getT_P_Employmentstatus();
        t_P_SocialSecurityBaseAmount = staffPrepayApplicationNew.getT_P_SocialSecurityBaseAmount();  //作为以后每个人的授额比例用
        paramMap.put("t_P_Probation", staffPrepayApplicationNew.getT_P_Probation());//添加元素
        List<StaffPrepayApplicationNew> StaffPrepayApplicationFPROD = staffPrepayApplicationService.findAuthFinanceProd(paramMap);
        
        for(int i=0;i<StaffPrepayApplicationFPROD.size();i++){
           	if(StaffPrepayApplicationFPROD.get(i).getT_FProd_category().equalsIgnoreCase(t_P_EmploymentCategory)){  
            	continue;
        	  }else {StaffPrepayApplicationFPROD.remove(i);  
            	 i--;
        	  }
           }   
//* get finance product info
        staffPrepayApplicationNew.getT_FProd_Interest();
        t_FProd_ServiceFee = staffPrepayApplicationNew.getT_FProd_ServiceFee();
        t_FProd_Poundage = staffPrepayApplicationNew.getT_FProd_Poundage();
        t_FProd_TierPoundage = staffPrepayApplicationNew.getT_FProd_TierPoundage();
//* get User current credit info       
      
        
        Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // 放入你的日期
		int date = calendar.get(Calendar.DATE);
		int MaxDate = calendar.getMaximum(Calendar.DATE);
		
		
		if (staffPrepayApplicationCredit == null) {
			staffPrepayApplicationCredit = new StaffPrepayApplicationList();	
			}else{
		        t_Txn_CreditPrepayCurrentNum = staffPrepayApplicationCredit.getT_Txn_CreditPrepayCurrentNum();
		        t_Txn_CreditPrepayBalanceNum = staffPrepayApplicationCredit.getT_Txn_CreditPrepayBalanceNum();
		        t_Txn_PrepayCounts = staffPrepayApplicationCredit.getT_Txn_PrepayCounts();
		        t_Txn_PrepayClear = staffPrepayApplicationCredit.getT_Txn_PrepayClear();
		        t_Txn_PrepayDate = staffPrepayApplicationCredit.getT_Txn_PrepayDate();
			}
		if (t_P_EmploymentCategory.equalsIgnoreCase("vendor")) {
	        if (t_P_Employmentstatus.equalsIgnoreCase("expired") || t_P_Employmentstatus.equalsIgnoreCase("pending") || t_P_Employmentstatus.equalsIgnoreCase("resigning")) {
	        	t_Txn_CreditPrepayCurrentNum = new BigDecimal("0.00");
				staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(new BigDecimal("0.00"));
				t_Txn_CreditPrepayBalanceNum = new BigDecimal("0.00");
             } else if(t_Txn_CreditPrepayCurrentNum == null || t_Txn_PrepayClear == "0") {
            	 BigDecimal CommonCreditPerc = t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(1.0)).setScale(2, BigDecimal.ROUND_DOWN);
            	 staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(CommonCreditPerc);
            	 t_Txn_CreditPrepayCurrentNum = CommonCreditPerc;
            	 staffPrepayApplicationCredit.setT_Txn_PrepayCounts(0);
		    	 t_Txn_CreditPrepayBalanceNum = new BigDecimal("0.00"); 
             } else {
            	 staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(t_Txn_CreditPrepayBalanceNum);
            	 if (t_Txn_PrepayCounts == null){
            		 t_Txn_PrepayCounts = 0;
                	 t_Txn_PrepayCounts = t_Txn_PrepayCounts + 1;
            	     staffPrepayApplicationCredit.setT_Txn_PrepayCounts(t_Txn_PrepayCounts);
            	 }else{
            	     t_Txn_PrepayCounts =  t_Txn_PrepayCounts + 1;
            	     staffPrepayApplicationCredit.setT_Txn_PrepayCounts(t_Txn_PrepayCounts);
            	 }
             }
        } else if (t_P_EmploymentCategory.equalsIgnoreCase("parttime") && t_P_CreditPrepaySalaryAmount.intValue() <= 500 ) { // Partime job limited by 500RMB/day
        	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           Calendar c = Calendar.getInstance();
	           c.setTime(new Date());
	           c.add(Calendar.MONTH, -1);
	           Date m = c.getTime();       
	   		   int PrevMaxDate = calendar.getMaximum(c.DATE);
	   		   Calendar d = Calendar.getInstance();
	           
        	 if (t_P_Employmentstatus.equalsIgnoreCase("expired") || t_P_Employmentstatus.equalsIgnoreCase("pending") || t_P_Employmentstatus.equalsIgnoreCase("resigning")) {
        		 t_Txn_CreditPrepayCurrentNum = new BigDecimal("0.00");
 				 staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(new BigDecimal("0.00"));
        	 } else {
			     if (t_Txn_CreditPrepayCurrentNum == null || t_Txn_PrepayClear == "0") {
			    	 if (date < t_P_PayrollDate) {
    				     tTxnPrepayDays = t_P_PayrollDate - date;
    				    int GapDateInit = (PrevMaxDate - t_P_PayrollDate) + date;
   			    	    t_Txn_CreditPrepayCurrentNum = t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(GapDateInit));
   			    	    t_Txn_CreditPrepayBalanceNum = new BigDecimal("0.00");
    				 }else {
    					 tTxnPrepayDays = MaxDate - date + t_P_PayrollDate;
    					 int GapDateInit = date - t_P_PayrollDate + 1;
    			    	 t_Txn_CreditPrepayCurrentNum = t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(GapDateInit));
    				 }
		  //Select latest draw money date,Integer Gap date, Multiply gap days with daily pay + balance figure/credit - pending for User requirement.The creditline increased with date
				     staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(t_Txn_CreditPrepayCurrentNum);
		        	} else {
		 	           d.setTime(t_Txn_PrepayDate);
			           int GetDate = d.get(Calendar.DATE);
		           if (Integer.valueOf(GetDate) >= t_P_PayrollDate && date >= Integer.valueOf(GetDate)) {
		        	   int GapDate = date - Integer.valueOf(GetDate);
		        	   t_Txn_CreditPrepayCurrentNum = t_Txn_CreditPrepayBalanceNum.add(t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(GapDate))).setScale(2,BigDecimal.ROUND_UP);
		           } else if(Integer.valueOf(GetDate) >= t_P_PayrollDate && date <= t_P_PayrollDate) {
		              int GapDate1 = (PrevMaxDate - Integer.valueOf(GetDate)) + date;
		               t_Txn_CreditPrepayCurrentNum = t_Txn_CreditPrepayBalanceNum.add(t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(GapDate1))).setScale(2,BigDecimal.ROUND_UP);
		           } else if(Integer.valueOf(GetDate) <= t_P_PayrollDate && date <= t_P_PayrollDate) {
		        	   int GapDate2 = date - Integer.valueOf(GetDate);
			           t_Txn_CreditPrepayCurrentNum = t_Txn_CreditPrepayBalanceNum.add(t_P_CreditPrepaySalaryAmount.multiply(BigDecimal.valueOf(GapDate2)).setScale(2,BigDecimal.ROUND_UP));
		           } else {
		        	   t_Txn_CreditPrepayCurrentNum = t_Txn_CreditPrepayBalanceNum;
		           }
		           staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(t_Txn_CreditPrepayCurrentNum);
			   }
        	 }
        }else {
      	    t_Txn_CreditPrepayCurrentNum = new BigDecimal("0.00");
			staffPrepayApplicationCredit.setT_Txn_CreditPrepayCurrentNum(new BigDecimal("0.00"));	
        }
		
		if (treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue() <= t_Txn_CreditPrepayBalanceNum.intValue() ||
		    treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue() < t_Txn_CreditPrepayCurrentNum.intValue() ||
		    InitialBalance.intValue() <= t_Txn_CreditPrepayBalanceNum.intValue())  // check balance
    			{
                    StringBuffer debugger2 = new StringBuffer();
                    System.out.print(String.valueOf(debugger2.append("TreasuryBal:").append(treasuryDBInfoGetStatistic.getT_TreasuryDB_Balance().intValue())
                        .append(" - InitialVal2:").append(InitialBalance.intValue()).append(" - TxnBalVal:").append(t_Txn_CreditPrepayBalanceNum.intValue())
                        .append(" - CurrentBal:").append(t_Txn_CreditPrepayCurrentNum.intValue()).append(" - InitialBalance <= TxnBal InitBalVal:").append(InitialBalance.intValue())));
                    return  "staffPrepayApplication/OverCreditLine";
    	}
    	
        model.addAttribute("StaffPrepayApplicationFPROD",StaffPrepayApplicationFPROD);
		model.addAttribute("staffPrepayApplicationCredit",staffPrepayApplicationCredit);
        model.addAttribute("staffPrepayApplicationNew",staffPrepayApplicationNew);
        
        if ( operationType == "add") {
        return "staffPrepayApplication/addStaffPrepayApplication";
        } else {
        	return "staffPrepayApplication/staffPrepayApplicationNew";
        } 
        }else {
           return null;	
        }    
    }	
	@RequestMapping(value = "addStaffPrepayApplication")
	// 当判断页面的行为为add时,返回相应的add页面
	@ResponseBody
	public String addStaffPrepayApplication(
			StaffPrepayApplicationList staffPrepayApplication,
			StaffPrepayApplicationPayment staffPrepayApplicationPay,
			Integer platform, String operationType,String t_FProd_Name,String SeesionLoginMobil,
			Integer t_P_PayrollDate,String t_Txn_PrepayDays,String SMScode,String SMScodeRec,String agreement,
			BigDecimal t_FProd_Interest, BigDecimal t_FProd_ServiceFee,
			BigDecimal t_Txn_ApplyPrepayAmount,Integer t_Txn_PrepayCounts,
			BigDecimal t_Txn_CreditPrepayCurrentNum,
			BigDecimal t_Txn_CreditPrepayBalanceNum,
			BigDecimal t_FProd_Poundage,BigDecimal t_FProd_ETxnAmtLimit, String t_FProd_TierPoundage, Integer tTxnPrepayDays,
			BigDecimal tTxnInterest, HttpServletResponse response,String paymentStatus,
			HttpServletRequest request, Model model) throws Exception {
		
		model.addAttribute("t_Txn_ApplyPrepayAmount", t_Txn_ApplyPrepayAmount);
		model.addAttribute("t_Txn_CreditPrepayCurrentNum",
				t_Txn_CreditPrepayCurrentNum);
		model.addAttribute("t_Txn_CreditPrepayBalanceNum",
				t_Txn_CreditPrepayBalanceNum);
		model.addAttribute("t_P_PayrollDate", t_P_PayrollDate);
		model.addAttribute("t_FProd_Name", t_FProd_Name);
		model.addAttribute("t_Txn_ProdName",t_FProd_Name);
		model.addAttribute("t_Txn_PrepayCounts", t_P_PayrollDate);
		model.addAttribute("tTxnPrepayDays", tTxnPrepayDays);
		model.addAttribute("tTxnInterest", tTxnInterest);
		model.addAttribute("SMScodeRec", SMScodeRec);

		model.addAttribute("Agreement", agreement);

		// return "RiskControlController/RiskControlControllerMain";

		model.addAttribute("platform", platform);
		SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
		
		
		//common function
		Map<String, Object> rs = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        
		StaffPrepayApplicationNew staffPrepayApplicationNew = staffPrepayApplicationService.findSelectedByFProdName(t_FProd_Name);
		StaffPrepayApplicationNew staffPrepayApplicationPNow = staffPrepayApplicationService.findAuthPrepayApplier(SeesionLoginMobil);
		StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(SeesionLoginMobil);
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(ShiroSessionUtil.getLoginSession().getCompany_name());
		
		if (staffPrepayApplicationCredit != null & ShiroSessionUtil.getLoginSession().getRemark() != null){
			if (ShiroSessionUtil.getLoginSession().getRemark().equals(SMScode)){
			String RCretDataF = "0007";
			rs.put("retData",RCretDataF);
			if (t_Txn_CreditPrepayCurrentNum.equals(staffPrepayApplicationCredit.getT_Txn_CreditPrepayCurrentNum())){
			return JsonBizTool.genJson(ExRetEnum.EXPREPAY_WIP,rs);
	          }
			}
		}

		
		long PrepayNowDate  = new Date().getTime();
		if (ShiroSessionUtil.getLoginSession().getModifyTime() != null) {
			long ExprepayTime = ShiroSessionUtil.getLoginSession().getModifyTime().getTime();
				int ExTimeGap = (int)((PrepayNowDate - ExprepayTime) / 1000);
				if (ExTimeGap < 60){
					String RCretDataF = "0007";
					rs.put("retData",RCretDataF);
					return JsonBizTool.genJson(ExRetEnum.PAY_SUCCESS,rs);
				}
		}
		String t_FProd_ChargeFeeType,t_FProd_ChargeFeeType_str = null;
		t_FProd_ChargeFeeType = staffPrepayApplicationNew.getT_FProd_PersPool();
		t_FProd_ChargeFeeType_str = t_FProd_ChargeFeeType.substring(t_FProd_ChargeFeeType.length()-1);
		t_FProd_ServiceFee = staffPrepayApplicationNew.getT_FProd_ServiceFee();
		t_FProd_Poundage = staffPrepayApplicationNew.getT_FProd_Poundage();
		t_FProd_ETxnAmtLimit = staffPrepayApplicationNew.getT_FProd_ETxnAmtLimit();
		t_FProd_TierPoundage = staffPrepayApplicationNew.getT_FProd_TierPoundage();;
		BigDecimal calc_TierPoundage = new BigDecimal(0.00);
		if (t_Txn_ApplyPrepayAmount != null){
			if(t_FProd_ETxnAmtLimit == null){
				t_FProd_ETxnAmtLimit = new BigDecimal(10000);
			}
			int PrepayTxnAmt = t_Txn_ApplyPrepayAmount.compareTo(t_FProd_ETxnAmtLimit);
			int PrepayTxnAmtBlance = t_Txn_ApplyPrepayAmount.compareTo(t_Txn_CreditPrepayCurrentNum);
			if (PrepayTxnAmt == 1 || PrepayTxnAmtBlance == 1){
				rs.put("Amtlimit", t_FProd_ETxnAmtLimit);
				return JsonBizTool.genJson(ExRetEnum.LIMITAMT, rs); 
			}else
			if (t_FProd_TierPoundage != null ){
				calc_TierPoundage = Tool.gent_FProd_TierPoundage(t_FProd_TierPoundage,t_Txn_ApplyPrepayAmount); //calc the tier service fee
			}else {
				calc_TierPoundage = new BigDecimal(0);
			}
		}else{
			return "Salary advance Amount failed";
		}
		t_FProd_Interest = staffPrepayApplicationNew.getT_FProd_Interest();
		BigDecimal t_Txn_InterestFee = null ,t_Txn_ServiceFee = null,t_Prepay_ServiceFee_Total = null, t_Txn_TotalPrepayNum = null;
		t_Txn_InterestFee = t_FProd_Interest.multiply(t_Txn_ApplyPrepayAmount.multiply(BigDecimal.valueOf(tTxnPrepayDays))).setScale(2,BigDecimal.ROUND_UP);
		t_Txn_ServiceFee = t_FProd_ServiceFee.multiply(t_Txn_ApplyPrepayAmount.multiply(BigDecimal.valueOf(tTxnPrepayDays))).setScale(2,BigDecimal.ROUND_UP);
		t_Prepay_ServiceFee_Total = t_Txn_ServiceFee.add(t_FProd_Poundage).add(calc_TierPoundage).add(t_Txn_InterestFee).setScale(2,BigDecimal.ROUND_UP);
		String t_FProd_Name_Conv = staffPrepayApplicationNew.getT_FProd_Name();
		String TxnID = Tool.PayId();
		
		if(t_FProd_ChargeFeeType_str.equalsIgnoreCase("a")){
			t_Txn_TotalPrepayNum = t_Txn_ApplyPrepayAmount.subtract(t_Prepay_ServiceFee_Total).setScale(2,BigDecimal.ROUND_UP);
		}else{
			t_Txn_TotalPrepayNum = t_Txn_ApplyPrepayAmount.setScale(2,BigDecimal.ROUND_UP);
		}
		
		staffPrepayApplication.setCreator(ShiroSessionUtil.getLoginSession().getId());
		staffPrepayApplication.setCreate_time(new Date());
		staffPrepayApplication.setT_Txn_PrepayApplierName(staffPrepayApplicationPNow.getT_P_Name());
		staffPrepayApplication.setT_Txn_PrepayApplierPID(staffPrepayApplicationPNow.getT_P_PID());
		staffPrepayApplication.setT_Txn_ID(Tool.uuid());
		staffPrepayApplication.setT_Txn_Num(TxnID);
		staffPrepayApplication.setT_Txn_ClearNum(Tool.uuid());
		staffPrepayApplication.setT_Txn_SysUpdateDate(new Date());
		staffPrepayApplication.setT_Txn_Mobil(ShiroSessionUtil.getLoginSession().getMobile());
		staffPrepayApplication.setT_Txn_InterestMargin(tTxnInterest);
		staffPrepayApplication.setT_Txn_TotalInterestDays(tTxnPrepayDays);
		staffPrepayApplication.setT_Txn_PrepayDays(tTxnPrepayDays);
		staffPrepayApplication.setT_Txn_ServiceFee(t_Txn_ServiceFee);
		staffPrepayApplication.setT_Txn_Poundage(t_FProd_Poundage);
		staffPrepayApplication.setT_Txn_TierPoundage(calc_TierPoundage);
		staffPrepayApplication.setT_Txn_Interest(t_Txn_InterestFee);
		staffPrepayApplication.setT_Txn_CreditPrepayCurrentNum(t_Txn_CreditPrepayCurrentNum.setScale(2,BigDecimal.ROUND_DOWN));
		staffPrepayApplication.setT_Txn_ApplyPrepayAmount(t_Txn_ApplyPrepayAmount.setScale(2,BigDecimal.ROUND_UP));
		staffPrepayApplication.setT_Txn_TotalPrepayNum(t_Txn_TotalPrepayNum.setScale(2,BigDecimal.ROUND_UP));
		staffPrepayApplication.setT_Txn_CreditPrepayBalanceNum(t_Txn_CreditPrepayBalanceNum.setScale(2,BigDecimal.ROUND_DOWN));
		staffPrepayApplication.setT_Txn_InterestMargin(t_Prepay_ServiceFee_Total);
		staffPrepayApplication.setT_Txn_BankAccName(staffPrepayApplicationPNow.getT_P_PayrollDebitcardBankName());
		staffPrepayApplication.setT_Txn_BankAcc(staffPrepayApplicationPNow.getT_P_PayrollDebitcardNum());
		staffPrepayApplication.setT_Txn_PrepayClear("1");
		staffPrepayApplication.setT_Txn_SMS(SMScode);
		staffPrepayApplication.setT_Txn_SMSRec(SMScodeRec);
		staffPrepayApplication.setRemark(SMScodeRec);
		staffPrepayApplication.setT_Txn_Paystatus(ShiroSessionUtil.getLoginSession().getCompany_name());
		staffPrepayApplication.setT_Txn_ProdName(t_FProd_Name_Conv);
		staffPrepayApplication.setT_Txn_PrepayCounts(t_Txn_PrepayCounts);
		staffPrepayApplication.setT_Txn_PrepayDate(new Date());
		staffPrepayApplication.setAgreement(agreement);
        String salaryAdvanceEwalletAcc = "58gongziewallet";        
        TreasuryDBMain treasuryDBMain = treasuryDBMainService.findTreasuryPlatformAcc(salaryAdvanceEwalletAcc);
        staffPrepayApplication.setPlatform(treasuryDBMain.getT_TreasuryDB_Main_PaymentClearNum()); // checkout to 3rd prty platform acc
		
		String t_Txn_PrepayClear = "No";
		String SessionSMS = ShiroSessionUtil.getLoginSession().getRemark();
		if ( SessionSMS == null) {
			SessionSMS = "";
		}
		if (SMScode.equals(SMScodeRec)){
		    String[] TreasuryMgtOwner = treasuryDBMain.getPlatform().split("-");
            String TreasuryMgtOwnerName = TreasuryMgtOwner[0].trim();
            String TreasuryMgtOwnerPID = TreasuryMgtOwner[1].trim();
//		    Insert PaymentList table
					staffPrepayApplicationPay.setID(Tool.uuid());
					staffPrepayApplicationPay.setOrderCode(TxnID);
					staffPrepayApplicationPay.setVersion("01");
					staffPrepayApplicationPay.setProductId("00000004");
		            staffPrepayApplicationPay.setTranTime(df.format(new Date()).toString());
					staffPrepayApplicationPay.setTranAmt(t_Txn_TotalPrepayNum.setScale(0,BigDecimal.ROUND_UP).toString());
                    staffPrepayApplicationPay.setName(TreasuryMgtOwnerName);
					staffPrepayApplicationPay.setCurrencyCode("156");
					staffPrepayApplicationPay.setCertType("0101");
                    staffPrepayApplicationPay.setCertNo(TreasuryMgtOwnerPID); //Goldman Fuks shareholder pid
					staffPrepayApplicationPay.setAccAttr("0");
					staffPrepayApplicationPay.setAccNo(treasuryDBMain.getT_TreasuryDB_Main_PaymentClearNum()); // Goldman Fuks treasury mgt
					staffPrepayApplicationPay.setAccName(TreasuryMgtOwnerName);
					staffPrepayApplicationPay.setRemark(SMScodeRec);
					staffPrepayApplicationPay.setAccType("4");
					staffPrepayApplicationPay.setReturnPic("1");
					staffPrepayApplicationPay.setPhone(staffPrepayApplicationPNow.getT_P_Mobil());
					staffPrepayApplicationPay.setReqReserved("全渠道");

					String PaymentTunnel = AgencyOrgnization.getT_O_OrgPayrollBankaccount();  // get original paymenttunnel
					
//					System.out.println("SessionSMS:");  //debug using
//					System.out.println(SessionSMS);  //debug using
//					System.out.println("SMScodeRec:");  //debug using
//					System.out.println(SMScodeRec);  //debug using
					
					if (SessionSMS.equals(SMScodeRec)){
						String RCretDataF = "0008";
						rs.put("retData",RCretDataF);
						return JsonBizTool.genJson(ExRetEnum.EXPREPAY_OPEN,rs);
					}else{
						String RCretData = null;
			        	String remark = null;
		        		String merchantId = null;
		        		String PaymentSwitch = null;
// get payment vendor	
			        	if(PaymentTunnel.equalsIgnoreCase("电银支付")) {
			        		if (!AgencyOrgnization.getT_O_OrgChinaebiAcc().equals(null)){
			        			merchantId = AgencyOrgnization.getT_O_OrgChinaebiAcc();
				        	    PaymentSwitch = "shdy";
			        		}else {
			        			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
			        		}
			        	}else if (PaymentTunnel.equalsIgnoreCase("杉德支付")){
			        		if (!AgencyOrgnization.getT_O_OrgChinaebiAcc().equals(null)){
			        			merchantId = AgencyOrgnization.getT_O_OrgSandeAcc();
				        	    PaymentSwitch = "shsd";
			        		}else {
			        			rs.put("retData","-1");
			        			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
			        		}
			        	}else{
			        		OrganizationInfo AgencyPaymentOrgnization = organizationInfoService.selectAgencyName(PaymentTunnel);
			        		String PaymentAccTunnel = AgencyPaymentOrgnization.getT_O_OrgPayrollBankaccount();
			        		switch(PaymentAccTunnel){
			        		case "电银支付" :
			        			merchantId = AgencyPaymentOrgnization.getT_O_OrgChinaebiAcc();
				        	    PaymentSwitch = "shdy";
								break;
			        		case "杉德支付" :
			        			merchantId = AgencyPaymentOrgnization.getT_O_OrgSandeAcc();
				        	    PaymentSwitch = "shsd";
								break;
			        		default:
				        	    PaymentSwitch = null;
			        			return JsonBizTool.genJson(ExRetEnum.PAY_ACC_FAIL,rs);
			        		}
			        	}
			        	
						if (PaymentSwitch.equals("shsd")){
							staffPrepayApplication.setPlatform(merchantId);
							staffPrepayApplicationPay.setVersion("sandpay");
							staffPrepayApplication.setT_Txn_ClearOrg("sandpay");
							//payto goldmanfuks sandpay pub account for cashout preparation
                            JSONObject obj = AgentPayDemo.main(staffPrepayApplicationPay,merchantId);  // sandpay
                            
				        	RCretData = (String) obj.get("respCode"); //  sandpay branch
				        	remark = (String) obj.get("respDesc"); //  sandpay branch
				        	if(RCretData.equals("4001")){
								   staffPrepayApplicationPay.setRCcode(RCretData);
								   staffPrepayApplicationPay.setReturnPic("Other");
								   staffPrepayApplicationPay.setRemark(remark);
								   staffPrepayApplicationPay.setCompany(merchantId);
						    	   staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
						    	   System.out.println("Err txn log:");
						    	   System.out.println(TxnID);
								   return JsonBizTool.genJson(ExRetEnum.PAY_OUTOFBALANCE,rs);
						      }
						}else if (PaymentSwitch.equals("shdy")){
							staffPrepayApplicationPay.setCompany(merchantId);
							staffPrepayApplicationPay.setVersion("Chinaebi");
							staffPrepayApplication.setT_Txn_ClearOrg("Chinaebi");
							String retData = PayServlet.main(staffPrepayApplicationPay,merchantId);  // Chinaebipay
							JSONObject obj = (JSONObject) JSON.parse(retData);
				        	RCretData = (String) obj.get("transState"); //  Chinaebipay branch
				        	String RespMsg = (String) obj.get("rspCode"); //  Chinaebipay branch
				        	remark = (String) obj.get("rspMessage"); 
				        	if(RespMsg.equalsIgnoreCase("ACM20048")){
								   staffPrepayApplicationPay.setRCcode(RCretData);
								   staffPrepayApplicationPay.setReturnPic("Other");
								   staffPrepayApplicationPay.setRemark(remark);
								   staffPrepayApplicationPay.setCompany(merchantId);
						    	   staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
						    	   System.out.println("Err txn log:");
						    	   System.out.println(TxnID);
								   return JsonBizTool.genJson(ExRetEnum.PAY_OUTOFBALANCE,rs);
						      }
						}
						ShiroSessionUtil.getLoginSession().setRemark(SMScodeRec);

//			            String RCretData = "0000";   //testing using
						
						if (RCretData.length()>=4){
							RCretData = RCretData.substring(RCretData.length()-4, RCretData.length());
						}

				      if (RCretData.equals("0000") || RCretData.equals("0001") || RCretData.equals("0002") || RCretData.equalsIgnoreCase("S") || RCretData.equalsIgnoreCase("P") || RCretData.equalsIgnoreCase("U")) {
							int InsertRS = staffPrepayApplicationService.insertSelective(staffPrepayApplication);
							String SMSCompanyName = ShiroSessionUtil.getLoginSession().getCompany_name();
							String mobile = staffPrepayApplicationPay.getPhone();
							String PaidAmt = staffPrepayApplication.getT_Txn_ApplyPrepayAmount().toString();
							String ActPaidAmt = staffPrepayApplicationPay.getTranAmt();
							String ServiceFee = staffPrepayApplication.getT_Txn_Poundage().add(calc_TierPoundage).add(staffPrepayApplication.getT_Txn_ServiceFee().add(staffPrepayApplication.getT_Txn_Interest())).toString();
							String BalanceAmt = staffPrepayApplication.getT_Txn_CreditPrepayBalanceNum().toString();
							String SuccPaid = HttpJsonExample.SuccessPaid(mobile,SMSCompanyName, PaidAmt, ActPaidAmt, ServiceFee, BalanceAmt); // 发送成功支取短信
//check payment Acc balance and update into treasuryDB
							if (InsertRS == 1) {
								//插入异常交易记录
							   if (RCretData.equalsIgnoreCase("0001") || RCretData.equalsIgnoreCase("0002") || RCretData.equalsIgnoreCase("P") || RCretData.equalsIgnoreCase("U")){
								 staffPrepayApplicationPay.setCompany(merchantId);
								 staffPrepayApplicationPay.setRCcode(RCretData);
								 staffPrepayApplicationPay.setRemark("1,2 err");
							     staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
								}
							   //计算总费用
						    	TreasuryDBInfo entity = null;
						    	BigDecimal OverAllFee = null;
								entity = new TreasuryDBInfo();
								if(t_FProd_ChargeFeeType_str.equalsIgnoreCase("a")){
									OverAllFee = staffPrepayApplication.getT_Txn_ApplyPrepayAmount();
								}else {
									OverAllFee = staffPrepayApplication.getT_Txn_ApplyPrepayAmount().add(t_FProd_ServiceFee).add(t_FProd_Poundage).add(calc_TierPoundage).add(t_FProd_Interest);
								}
									//扣除总余额
									String t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
									//扣除机构余额
									TreasuryDBInfo treasuryOrgDBInfoUpdate = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
								  	BigDecimal tTreasuryOrgDBBalance = treasuryOrgDBInfoUpdate.getT_TreasuryDB_Balance().subtract(OverAllFee);
								  	BigDecimal tTreasuryOrgDBPaymentBalance = treasuryOrgDBInfoUpdate.getT_TreasuryDB_BoPRatio().subtract((new BigDecimal(0.5)).add(new BigDecimal(ActPaidAmt)));
								  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Balance(tTreasuryOrgDBBalance);    
								  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_BoPRatio(tTreasuryOrgDBPaymentBalance); 	
									BigDecimal Sandebalance = null;
									BigDecimal ebibalance = null;
									switch(PaymentSwitch){ 
									    case "shdy" :
                                            String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
                                            JSONObject obj = (JSONObject) JSON.parse(ret_Data);
                                            String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
                                            ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
                                            System.out.println("Query Chinaebipay balance:");
                                            System.out.println(ebibalance);
                                            break;
                                        case "shsd" :
                                            JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId);
                                            String BalanceData = (String) JSONretdata.get("balance");
                                            Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
                                            System.out.println("Query Sande balance:");
                                            System.out.println(Sandebalance);
                                            break;
										case "ewallet" :
                                            merchantId = "S2135052";
                                            JSONObject JSONretdata1 = MerBalanceQueryDemo.main(merchantId);
                                            String BalanceData1 = (String) JSONretdata1.get("balance");
                                            Sandebalance = (new BigDecimal(BalanceData1)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
                                            System.out.println("Ewallet - Query Sande balance:");
                                            System.out.println(Sandebalance);	
                                            break;
                                        default:
                                            merchantId = "S2135052";
                                            JSONObject JSONretdata2 = MerBalanceQueryDemo.main(merchantId);
    //						  		      	String JSONretdata1 = QueryBalanceDemo.BalanceQuery(merchantId); // query Goldman Fuks Sande Acc Balance
    //						  				JSONObject obj2 = (JSONObject) JSON.parse(JSONretdata1); // old sdk
                                            String BalanceData2 = (String) JSONretdata2.get("balance");
                                            Sandebalance = (new BigDecimal(BalanceData2)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
                                            System.out.println("Query Sande balance:");
                                            System.out.println(Sandebalance);
									}
									
									if(ebibalance == null){
										ebibalance = new BigDecimal(0.00);
									}
									if(Sandebalance == null){
										Sandebalance = new BigDecimal(0.00);
									}
								  	
			  		        		StringBuffer ss =  new StringBuffer();
			  		        		String CurrentPaymentACC = String.valueOf(ss.append("杉德").append("-").append(Sandebalance.toString()).append("|").append("电银").append("-").append(ebibalance.toString()));
			  		        		
								  	treasuryOrgDBInfoUpdate.setPlatform(CurrentPaymentACC);
								  	treasuryOrgDBInfoUpdate.setModifier(ShiroSessionUtil.getLoginSession().getId());    
								  	treasuryOrgDBInfoUpdate.setModify_time(new Date());  
								  	treasuryOrgDBInfoUpdate.setT_TreasuryDB_Comment("自动加值"); 
								    int RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryOrgDBInfoUpdate);
							        // 扣除总额  	   
							      	 if (RS != 0) {
							      	   t_TreasuryDB_OrgName = "ALL";
							      	   TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
									   BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().subtract(OverAllFee);
									   treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
									   RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
							         }
			// wallet function ******************************
//							  if (RS != 0){
//							      //call ewallet balance update
//                                  //call topup 提款到钱包余额
//                                  // personal treasury mgt
//                                  DBConnection dao = new DBConnection();
//                                  Connection conn = dao.getConnection();
//                                  Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
//                                  String txnCat = "PersonalEwalletTopup";
//                                  BigDecimal txnAmt = t_Txn_TotalPrepayNum;
//                                  String paymentID = TxnID;
//                                  String method = "ewallettopup";
//                                  String walletTxn_PayerPID = staffPrepayApplicationPNow.getT_P_PID();
//                                  String walletTxn_ReceiverID = null;
//                                   
////            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
//                                  EwalletTxnController ewalletTxnController = new EwalletTxnController();
//                                  MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
//                                  String action = "WithdrawToEwallet";
//                                  String cardAcc = null;
//                                  rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,cardAcc,txnAmt,  walletTxn_PayerPID,
//                                       walletTxn_ReceiverID, method, paymentID, paymentStatus, conn);
//                                  conn.close();
//                                  if (rsMobileEwalletTxn.get("SQL").equals("SQL-RECEIVEREWALLETTOPUPSUCC")) {
//                                      System.out.println("调用个人提款到钱包成功");
//                                      rsMobileEwalletTxn.put("SMSverify",0);
//                                      //call personal evaluation 
//                                      return JsonBizTool.genJson(ExRetEnum.SUCCESS, rsMobileEwalletTxn);
//                                  }else{
//                                      return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
//                                  }
//							  } else {
//								  return JsonBizTool.genJson(ExRetEnum.PREPAY_APPFAIL);
//							  }
							  // wallet function complete *****************************
							rs.put("ret","0");
							return JsonBizTool.genJson(ExRetEnum.PAY_SUCCESS,rs);
							}
						    else if(InsertRS != 1) {
							   staffPrepayApplicationPay.setRemark("SQL INSERT ERR");
							   staffPrepayApplicationPay.setCompany(ShiroSessionUtil.getLoginSession().getCompany_name());
					    	   staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
						       return JsonBizTool.genJson(ExRetEnum.PREPAY_APPFAIL);
						     }
				          }
				      else {
						   staffPrepayApplicationPay.setRCcode(RCretData);
						   staffPrepayApplicationPay.setReturnPic("Other");
						   staffPrepayApplicationPay.setRemark(remark);
						   staffPrepayApplicationPay.setCompany(merchantId);
				    	   staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
				    	   System.out.println("Err txn log:");
				    	   System.out.println(TxnID);
						   return JsonBizTool.genJson(ExRetEnum.PAY_FAIL,rs);
					   }	
				     }
				}
				return  "staffPrepayApplication/staffPrepayApplicationNew";
			}

	@RequestMapping(value = "CalcBalanceCredit")
	// 当判断页面的预支数值是否合法时,返回相应的add页面
	@ResponseBody
	public String CalcBalanceCreditflexsible(String t_FProd_Name,
			String SeesionLoginMobil,Date t_Txn_PrepayDate,
			BigDecimal t_Txn_ApplyPrepayAmount,String t_Txn_PrepayClear,
			BigDecimal t_Txn_CreditPrepayCurrentNum, String t_FProd_ID,
			Integer t_P_PayrollDate, BigDecimal t_FProd_Interest,
			BigDecimal t_Txn_CreditPrepayBalanceNum,
			BigDecimal t_FProd_ServiceFee, Integer tTxnPrepayDays,Integer CountPayDays,
			BigDecimal t_FProd_Poundage,BigDecimal t_FProd_ETxnAmtLimit, String t_FProd_TierPoundage, BigDecimal t_Txn_Interest,
			String t_FProd_category, HttpServletResponse response,
			HttpServletRequest request, Model model) {

		model.addAttribute("t_Txn_ApplyPrepayAmount", t_Txn_ApplyPrepayAmount);
		model.addAttribute("t_Txn_CreditPrepayCurrentNum",
				t_Txn_CreditPrepayCurrentNum);
		model.addAttribute("t_Txn_CreditPrepayBalanceNum",
				t_Txn_CreditPrepayBalanceNum);
		model.addAttribute("t_P_PayrollDate", t_P_PayrollDate);
		model.addAttribute("t_FProd_Name", t_FProd_Name);
		SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
		Map<String, Object> rs = new HashMap<String, Object>();
		
		StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService
				.findPrepayApplierCredit(SeesionLoginMobil);
		
		if (staffPrepayApplicationCredit != null){
			String RCretDataF = "0007";
			rs.put("retData",RCretDataF);
			if (t_Txn_CreditPrepayCurrentNum.equals(staffPrepayApplicationCredit.getT_Txn_CreditPrepayCurrentNum())){
			return JsonBizTool.genJson(ExRetEnum.EXPREPAY_OPEN,rs);
	       }
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // 放入你的日期
		int date = calendar.get(Calendar.DATE);
		int MaxDate = calendar.getMaximum(Calendar.DATE);

		StaffPrepayApplicationNew StaffPrepayApplicationNew = staffPrepayApplicationService
				.findSelectedByFProdName(t_FProd_Name);
		t_FProd_Interest = StaffPrepayApplicationNew.getT_FProd_Interest();
		t_FProd_ServiceFee = StaffPrepayApplicationNew.getT_FProd_ServiceFee();
		t_FProd_Poundage = StaffPrepayApplicationNew.getT_FProd_Poundage();
		t_FProd_ETxnAmtLimit = StaffPrepayApplicationNew.getT_FProd_ETxnAmtLimit();
		t_FProd_TierPoundage = StaffPrepayApplicationNew.getT_FProd_TierPoundage();
		String t_FProd_ChargeFeeType,t_FProd_ChargeFeeType_str = null;
		t_FProd_ChargeFeeType = StaffPrepayApplicationNew.getT_FProd_PersPool();
		t_FProd_ChargeFeeType_str = t_FProd_ChargeFeeType.substring(t_FProd_ChargeFeeType.length()-1);
		
		//check Txn amt limited
		BigDecimal calc_TierPoundage = new BigDecimal(0.00);
		if (t_Txn_ApplyPrepayAmount != null){
			if(t_FProd_ETxnAmtLimit == null){
				t_FProd_ETxnAmtLimit = new BigDecimal(10000);
			}
			int PrepayTxnAmt = t_Txn_ApplyPrepayAmount.compareTo(t_FProd_ETxnAmtLimit);
			int PrepayTxnAmtBlance = t_Txn_ApplyPrepayAmount.compareTo(t_Txn_CreditPrepayCurrentNum);
			if (PrepayTxnAmt == 1 || PrepayTxnAmtBlance == 1){
				rs.put("Amtlimit", t_FProd_ETxnAmtLimit);
				return JsonBizTool.genJson(ExRetEnum.LIMITAMT, rs); 
			}else
			if (t_FProd_TierPoundage != null ){
				calc_TierPoundage = Tool.gent_FProd_TierPoundage(t_FProd_TierPoundage,t_Txn_ApplyPrepayAmount); //calc the tier service fee
			}else {
				calc_TierPoundage = new BigDecimal(0);
			}
		}else{
			return "Salary advance Amount failed";
		}
		t_FProd_category = StaffPrepayApplicationNew.getT_FProd_category();

		BigDecimal tTxnInterest = null, tTxnTCreditPrepayBalanceNum = null, t_Prepay_ServiceFee_Total = null;

		if (t_FProd_category.equalsIgnoreCase("vendor")
				|| t_FProd_category.equals(null)) {
			if (date >= t_P_PayrollDate) {
				tTxnPrepayDays = MaxDate - date + t_P_PayrollDate + 1;
			} else {
				tTxnPrepayDays = t_P_PayrollDate - date + 1;
			}
			BigDecimal t_FProd_ServiceFee_Total = null;
			
			/*
			Get PROD charge Fee type
			 */
			t_FProd_ServiceFee_Total = t_Txn_ApplyPrepayAmount.multiply(t_FProd_ServiceFee).multiply(BigDecimal.valueOf(tTxnPrepayDays)).setScale(2, BigDecimal.ROUND_UP);
			tTxnInterest = t_FProd_Interest.multiply(t_Txn_ApplyPrepayAmount)
					.multiply(BigDecimal.valueOf(tTxnPrepayDays))
					.setScale(2, BigDecimal.ROUND_UP);
			//flag advanced charge fee or not
			if(t_FProd_ChargeFeeType_str.equalsIgnoreCase("a")){
				tTxnTCreditPrepayBalanceNum = t_Txn_CreditPrepayCurrentNum
						.subtract(t_Txn_ApplyPrepayAmount).setScale(2,BigDecimal.ROUND_UP);
			}else{
				tTxnTCreditPrepayBalanceNum = t_Txn_CreditPrepayCurrentNum
						.subtract(t_Txn_ApplyPrepayAmount).subtract(tTxnInterest)
						.subtract(t_FProd_Poundage).subtract(t_FProd_ServiceFee_Total).setScale(2,BigDecimal.ROUND_UP);
			}
			t_Txn_CreditPrepayBalanceNum = tTxnTCreditPrepayBalanceNum;
			t_Prepay_ServiceFee_Total = t_FProd_ServiceFee_Total.add(t_FProd_Poundage).add(calc_TierPoundage).add(tTxnInterest).setScale(2,BigDecimal.ROUND_UP);
			
		} else if (t_FProd_category.equalsIgnoreCase("parttime")) {
			   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           Calendar c = Calendar.getInstance();
	           c.setTime(new Date());
	           c.add(Calendar.MONTH, -1);
	           Date m = c.getTime();       
	   		   int PrevMaxDate = calendar.getMaximum(c.DATE);
	   		   Calendar d = Calendar.getInstance();

			 if (t_Txn_CreditPrepayCurrentNum == null || t_Txn_PrepayClear == "0" || staffPrepayApplicationCredit == null) {
				 if (date < t_P_PayrollDate) {
				     tTxnPrepayDays = t_P_PayrollDate - date;
				 }else if (date >= t_P_PayrollDate) {
					 tTxnPrepayDays = MaxDate - date + t_P_PayrollDate;
				 }
			   } else {
					t_Txn_PrepayDate = staffPrepayApplicationCredit.getT_Txn_PrepayDate();
			           d.setTime(t_Txn_PrepayDate);
			           int GetDate = d.get(Calendar.DATE);
				 if (Integer.valueOf(GetDate) >= t_P_PayrollDate && date >= Integer.valueOf(GetDate)) {
					 CountPayDays = date - Integer.valueOf(GetDate);
	        	     tTxnPrepayDays = (MaxDate - date) + t_P_PayrollDate + 1;
	           } else if(GetDate >= t_P_PayrollDate && date <= t_P_PayrollDate) {
	                 CountPayDays = (PrevMaxDate - Integer.valueOf(GetDate)) + date;  
	                 tTxnPrepayDays = t_P_PayrollDate - date + 1;     
	           } else if(GetDate <= t_P_PayrollDate && date <= t_P_PayrollDate) {
	        	     CountPayDays = date - Integer.valueOf(GetDate);
	        	     tTxnPrepayDays = t_P_PayrollDate - date + 1;
	           } else {
	        	     CountPayDays = 0;
	        	     tTxnPrepayDays = 1;
	           }
		      }
			BigDecimal t_FProd_ServiceFee_Total = null;
			t_FProd_ServiceFee_Total = t_Txn_ApplyPrepayAmount.multiply(t_FProd_ServiceFee).multiply(BigDecimal.valueOf(tTxnPrepayDays)).setScale(2, BigDecimal.ROUND_UP);
			tTxnInterest = t_FProd_Interest.multiply(t_Txn_ApplyPrepayAmount)
					.multiply(BigDecimal.valueOf(tTxnPrepayDays))
					.setScale(2, BigDecimal.ROUND_UP);
			if(t_FProd_ChargeFeeType_str.equalsIgnoreCase("a")){
				tTxnTCreditPrepayBalanceNum = t_Txn_CreditPrepayCurrentNum
						.subtract(t_Txn_ApplyPrepayAmount).setScale(2,BigDecimal.ROUND_UP);
			}else{
				tTxnTCreditPrepayBalanceNum = t_Txn_CreditPrepayCurrentNum
						.subtract(t_Txn_ApplyPrepayAmount).subtract(tTxnInterest)
						.subtract(t_FProd_Poundage).subtract(t_FProd_ServiceFee_Total).setScale(2,BigDecimal.ROUND_UP);
			}
			t_Prepay_ServiceFee_Total = t_FProd_ServiceFee_Total.add(t_FProd_Poundage).add(calc_TierPoundage).add(tTxnInterest).setScale(2,BigDecimal.ROUND_UP);
		}

		model.addAttribute("t_Prepay_ServiceFee_Total",t_Prepay_ServiceFee_Total);
		rs.put("t_Prepay_ServiceFee_Total", t_Prepay_ServiceFee_Total);
		rs.put("t_Txn_CreditPrepayBalanceNum", tTxnTCreditPrepayBalanceNum);
		rs.put("t_FProd_Name", t_FProd_Name);
		rs.put("tTxnPrepayDays", tTxnPrepayDays);
		rs.put("tTxnInterest", tTxnInterest);
		rs.put("chargeFeeCat", t_FProd_ChargeFeeType_str);

		return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
	 }
	
	 @RequestMapping(value = "staffPrepayApplicationResult")
	 public String staffPrepayApplicationResult(String t_Txn_id, HttpServletResponse response,
			 HttpServletRequest request,Integer platform, Model model) {
     	model.addAttribute("platform", platform);
		String SessionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
		if (SessionLoginMobil != null){
			StaffPrepayApplicationList staffPrepayApplicationCredit = null;
			staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(SessionLoginMobil);
			model.addAttribute("staffPrepayApplicationCredit",staffPrepayApplicationCredit);
			return  "staffPrepayApplication/staffPrepayApplicationResult";
		}else{
			return  "staffPrepayApplication/staffPrepayApplicationNew";	
		}
	  }
	 
	 @RequestMapping(value = "staffPrepayApplicationDetails")
	 public String staffPrepayApplicationDetails(String t_Txn_id, HttpServletResponse response,
			 HttpServletRequest request,Integer platform, Model model) {
     	model.addAttribute("platform", platform);
		String SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
		if (SeesionLoginMobil != null){
			StaffPrepayApplicationList staffPrepayApplicationCredit = null;
			staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(SeesionLoginMobil);
			model.addAttribute("staffPrepayApplicationCredit",staffPrepayApplicationCredit);
			return  "staffPrepayApplication/staffPrepayApplicationDetails";
		}else{
			return  "staffPrepayApplication/staffPrepayApplicationNew";	
		}
	  }
	 
	 @RequestMapping(value = "SMSReq") //当判断页面的行为为add时,返回相应的add页面
	 @ResponseBody
	 public String SMSReq(String t_Txn_Mobil, HttpServletResponse response, HttpServletRequest request, Model model) {

	 String SMScodeRec,SeesionLoginMobil,SMSMobile,SMSCompanyName;
	 SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();	 
	 StaffPrepayApplicationNew staffPrepayApplicationNew = staffPrepayApplicationService.findAuthPrepayApplier(SeesionLoginMobil); 
         
	 Map<String, Object> rs = new HashMap<String, Object>();
		 model.addAttribute("t_Txn_Mobil",t_Txn_Mobil); 

		 if (SeesionLoginMobil.equals(t_Txn_Mobil)){
			 SMSMobile = staffPrepayApplicationNew.getT_P_Mobil();
		 }else{
			 SMSMobile = null;
		 }
		 
		 SMSCompanyName = staffPrepayApplicationNew.getT_P_Company();
		 
//		String SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	// test using		
//		SMScodeRec =  SMScodeInit.substring(0,SMScodeInit.indexOf(".")); // test using
		 
		 SMScodeRec = HttpJsonExample.SMSReCode(SMSMobile,SMSCompanyName).toString();
		 
		 if (SMScodeRec != null && SMSMobile != null){
			  staffPrepayApplicationNew.setSMScodeRec(SMScodeRec);	
			  rs.put("SMScodeRec", SMScodeRec);
			  return JsonBizTool.genJson(ExRetEnum.SMSSUCCESS, rs);
		 }else {
			  rs.put("SMScodeRec", SMScodeRec);
			  return JsonBizTool.genJson(ExRetEnum.SMSFAIL, rs);
		 }	 
	  }
	
	 
	 @RequestMapping(value = "SMSCheck") //当判断页面的行为为add时,返回相应的add页面
	 @ResponseBody
	 public String SMSVerify(StaffPrepayApplicationNew staffPrepayApplicationNew,String SMScodeRec,String SMScode,String SeesionLoginMobil,
	 HttpServletResponse response, HttpServletRequest request, Model model) {
		 
	 SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();	 
		 
	 Map<String, Object> rs = new HashMap<String, Object>();
         
	     SMScodeRec =  null;
		 SMScodeRec = staffPrepayApplicationNew.getSMScodeRec();
		 String SMSVerified;
		 SMSVerified = "N";
		 
		 if (SMScodeRec.equals(SMScode)){
			 staffPrepayApplicationNew.setSMScodeRec(SMScodeRec);
			 rs.put("SMScodeRec", SMScodeRec);
			 SMSVerified = "Y";
			 rs.put("SMSVerified", SMSVerified);
			 return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
		 }else{
			 rs.put("SMScodeRec", SMScodeRec);
			 SMSVerified = "N";
			 rs.put("SMSVerified", SMSVerified);
			 return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
		 }
	  }
	 
	 @RequestMapping(value = "SessionOut") //当判断页面的行为为add时,返回相应的add页面
	 @SessionAttributes()
	 public class StaffSessionoutController {
		public String removeSession() {SessionStatus sessionStatus = null;
		sessionStatus.setComplete();
		boolean status = sessionStatus.isComplete();
		if (status) {
//		System.out.println("clean session succ");
		return "result";
		}else {
//		System.out.print("clean session failed");
		return "failed";}
		}
			
	}
}
