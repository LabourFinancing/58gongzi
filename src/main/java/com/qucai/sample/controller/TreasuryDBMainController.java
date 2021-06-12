package com.qucai.sample.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.AmtQueryServlet;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.MerBalanceQueryDemo;
import com.qucai.sample.service.*;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;


@Controller
@RequestMapping(value = "/TreasuryDBMainController")
public class TreasuryDBMainController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private TreasuryDBMainService treasuryDBMainService; //申明一个对象
    
    @Autowired
    private PaymentvendormgtService paymentvendormgtService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象
    
    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象
    
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

	String[] query_input= {"00","1.0","RSA","AccQuery",String.valueOf(System.currentTimeMillis()),"872566359655004"};		

    @ModelAttribute
    public TreasuryDBMain get(@RequestParam(required = false) String t_TreasuryDB_ID) {
    	TreasuryDBMain entity = null;
        if (StringUtils.isNotBlank(t_TreasuryDB_ID)) {
            entity = treasuryDBMainService.selectByPrimaryKey(t_TreasuryDB_ID);//用treasuryDBMainService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new TreasuryDBMain();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 测试
     * @throws Exception 
     */
    
    @RequestMapping(value = {"treasuryDBMainList"})
    public String TreasuryDBMainList(TreasuryDBMain TreasuryDBMain, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_TreasuryDB_OrgName,String t_TreasuryDB_ID,
    		String SessionCompanyName, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	
        t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对
//    	TreasuryDBMain TreasuryDBStatisticOverAll = treasuryDBMainService.StatisticOverall(t_TreasuryDB_OrgName);
	    OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
		paramMap.put("t_Pymt_Name", t_TreasuryDB_OrgName);
		List<Paymentvendormgt> paymentvendormgtAllList = paymentvendormgtService.findAllList(paramMap);
	    paramMap.put("t_P_VendorCompany",t_TreasuryDB_OrgName);

    	List<Map<String, Object>> ArrayPaymentBalance = new ArrayList();
		String RCretData = null;
		BigDecimal ebibalance = null;
		
		if ( t_TreasuryDB_OrgName.equals("ALL") ){
	        for(int i=0;i<paymentvendormgtAllList.size();i++){
	     		String arr_paymentCertinfo = paymentvendormgtAllList.get(i).getT_Pymt_CertificationCode();
	  	    	if (arr_paymentCertinfo.equalsIgnoreCase("shdy")) {
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
	  	        for(int t=0;t<arr_paymentinfo.length;t++) {
		        	Map<String, Object> paramSQLmap = new HashMap<String, Object>();
  		  	        OrganizationInfo PayrollChannel = null;
	  		        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
		  		    for(int j=0; j<Org_paymentinfo.length; j++) {
		  		        System.out.print(j);
		  		        System.out.print(Org_paymentinfo[j]);
		  		        String balanceQuery = null;
		  		        if (j==0){
		  		        	paramSQLmap.put("CompanyName", Org_paymentinfo[0]);
		  		        }
		  		        if (j==1){
		  		        	if (Org_paymentinfo[j].equalsIgnoreCase("null")){
			  				    paramSQLmap.put("ChinaebiBalance", "0.00");
		  		        	}else{
			  		        String merchantId = Org_paymentinfo[j];
		  		        	String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
		  		        	//input all orgs Chinaebi acc balance into treasurydb			   System.out.println(retData);
		  				    JSONObject obj = (JSONObject) JSON.parse(ret_Data);
		  				    balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
		  				    ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
		  				    System.out.println("Query Chinaebipay balance:");
		  				    System.out.println(balanceQuery);
		  				    System.out.println("BigDecimal balance:");
		  				    System.out.println(ebibalance);
		  				    paramSQLmap.put("ChinaebiBalance", ebibalance);
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
			  		  	    String CurrentPaymentACC = "0";
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("电银支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("电银支付").append("-").append(ebibalance));
			  		        	paramSQLmap.put("AgencyCompanyBlance", CurrentPaymentACC);
		  		        	}else{
		  		        		paramSQLmap.put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
		  		        	};
		  		        	}
		  		        }
		  		    }
				  	System.out.println(t);
				  	System.out.println("Add-");
			  	    System.out.println(paramSQLmap);
			  	    ArrayPaymentBalance.add(paramSQLmap);
	  	      }
	       }else if (arr_paymentCertinfo.equalsIgnoreCase("shsd")) {
	    	    System.out.println(ArrayPaymentBalance);
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
	  	    	List<Map<String, Object>> ArraySandBalance = new ArrayList();
		  	    int tt=0;
	  	        for(int t=0;t<arr_paymentinfo.length;t++) {
		  	        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
		  	        System.out.println(ArrayPaymentBalance.get(t).get("CompanyName"));
		  	        System.out.println(Org_paymentinfo[0]);
			  	    OrganizationInfo PayrollChannel = null;
 		        if (Org_paymentinfo[0].equals(ArrayPaymentBalance.get(t).get("CompanyName"))){
  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		    for(int k=0; k<Org_paymentinfo.length; k++) {
		  		        System.out.print(k);
		  		        System.out.print(Org_paymentinfo[k]);
		  		        String balanceQuery = null;
		  		        if (k==2){
		  		        	if ( Org_paymentinfo[1].equalsIgnoreCase("null")){
		  		        		ArrayPaymentBalance.get(t).put("SandeBalance", "0.00");
		  		        	}else{
			  		        String merchantId = Org_paymentinfo[1];
			  		        String BalanceData = "0";
				        	Map<String, Object> SandBalArray = new HashMap<String, Object>();
				        	if(tt==0){
				        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
				  		    	BalanceData = (String) JSONretdata.get("balance");
				  		    	SandBalArray.put("merchantId", merchantId);
				  		    	SandBalArray.put("BalanceData", BalanceData);
				  		    	ArraySandBalance.add(SandBalArray);
				  		    	tt = tt+1;
				        	}else{
				        		for(int h=0; h<ArraySandBalance.size();h++){
				        			if(ArraySandBalance.get(h).get("merchantId").toString().equalsIgnoreCase(merchantId)){
				        				BalanceData = ArraySandBalance.get(h).get("BalanceData").toString();
				        				break;
				        			}else{
						        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
						  		    	BalanceData = (String) JSONretdata.get("balance");
						  		    	SandBalArray.put("merchantId", merchantId);
						  		    	SandBalArray.put("BalanceData", BalanceData);
						  		    	ArraySandBalance.add(SandBalArray);
						  		    	break;
				        			}
				        		}
				        	}
			  	
//			  		      	String JSONretdata = QueryBalanceDemo.BalanceQuery(merchantId); // query Sande Balance
		  		        	//input all orgs Sande acc balance into treasurydb
//			  				JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//			  		    	String BalanceData = (String) obj.get("balance");
			  		    	BigDecimal Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
//			  		        balanceQuery = "12000"; // debug using
			  				System.out.println("Query Sande balance:");
			  				System.out.println(balanceQuery);
			  				System.out.println("BigDecimal balance:");
			  				System.out.println(Sandebalance);
			  				ArrayPaymentBalance.get(t).put("SandeBalance", Sandebalance);
		  		        	String CurrentPaymentACC = "0";
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("杉德支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("杉德支付").append("-").append(Sandebalance));
				  				ArrayPaymentBalance.get(t).put("PayrollChannel", CurrentPaymentACC);
		  		        	}else{
		  		        		ArrayPaymentBalance.get(t).put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
			  		        }
		  		        	}
		  		        }
		  		    }
 		        }else{
 		        	continue;
 		        }
	  	       }
 	    	}
	      }
		}else if( AgencyOrgnization.getT_O_listOrg().equalsIgnoreCase("on")){
		    List<PersonalInfo> personalInfo = personalInfoService.findSubCompany(paramMap);
	        for(int i=0;i<paymentvendormgtAllList.size();i++){
	     		String arr_paymentCertinfo = paymentvendormgtAllList.get(i).getT_Pymt_CertificationCode();
	  	    	if (arr_paymentCertinfo.equalsIgnoreCase("shdy")) {
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
			    for(int l=0;l<personalInfo.size();l++){
		        	Map<String, Object> paramSQLmap = new HashMap<String, Object>();
			  	    OrganizationInfo PayrollChannel = organizationInfoService.selectAgencyName(personalInfo.get(l).getT_P_Company());
	  	          for(int t=0;t<arr_paymentinfo.length;t++) {
	  		        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
			  	    if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(Org_paymentinfo[0])){
		  		      for(int j=0; j<Org_paymentinfo.length; j++) {
		  		        System.out.print(j);
		  		        System.out.print(Org_paymentinfo[j]);
		  		        String balanceQuery = null;
		  		        if (j==0){
		  		        	paramSQLmap.put("CompanyName", personalInfo.get(l).getT_P_Company());
		  		        }
		  		        if (j==1){
			  		  	    String CurrentPaymentACC = null;
		  		        	if (Org_paymentinfo[j].equalsIgnoreCase("null")){
			  				    paramSQLmap.put("ChinaebiBalance", "0.00");
		  		        	}else{
				  		        String merchantId = Org_paymentinfo[j];
			  		        	String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
			  		        	//input all orgs Chinaebi acc balance into treasurydb			   System.out.println(retData);
			  				    JSONObject obj = (JSONObject) JSON.parse(ret_Data);
			  				    balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
			  				    ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
			  				    System.out.println("Query Chinaebipay balance:");
			  				    System.out.println(balanceQuery);
			  				    System.out.println("BigDecimal balance:");
			  				    System.out.println(ebibalance);
			  				    paramSQLmap.put("ChinaebiBalance", ebibalance);
		  		        	}
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("电银支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("电银支付").append("-").append(ebibalance));
			  		        	paramSQLmap.put("AgencyCompanyBlance", CurrentPaymentACC);
		  		        	}else{
		  		        		paramSQLmap.put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
		  		        	}
		  		        	if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(ShiroSessionUtil.getLoginSession().getCompany_name())){
//		  					   TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(ebibalance);
		  		        	}
		  		        }
			  	      }
			  	    }else {
		  		    	continue;
		  		    }
		  		   }
				  	System.out.println(i);
				  	System.out.println("-Add-");
				  	System.out.println(l);
				  	System.out.println("-cycle-");
			  	    System.out.println(paramSQLmap);
			  	    ArrayPaymentBalance.add(paramSQLmap);
			      }
	  	    	}else if (arr_paymentCertinfo.equalsIgnoreCase("shsd")) {
		    	    System.out.println(ArrayPaymentBalance);
		  	        System.out.print(i);
			  	    int tt=0;
		  	    	List<Map<String, Object>> ArraySandBalance = new ArrayList();
					for(int l=0;l<ArrayPaymentBalance.size();l++){
		     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
		  	        for(int t=0;t<arr_paymentinfo.length;t++) {
			  	        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
			  	        System.out.println(ArrayPaymentBalance.get(l).get("CompanyName"));
			  	        System.out.println(Org_paymentinfo[0]);
				  	    OrganizationInfo PayrollChannel = null;
				  		if (Org_paymentinfo[0].equals(ArrayPaymentBalance.get(l).get("CompanyName"))){
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		  	    for(int k=0; k<Org_paymentinfo.length; k++) {
			  		        System.out.print(k);
			  		        System.out.print(Org_paymentinfo[k]);
			  		        String balanceQuery = "0";
			  		        if (k==2){
			  		        	String CurrentPaymentACC = "0";
			  		        	BigDecimal Sandebalance = new BigDecimal(0);
			  		        	if ( Org_paymentinfo[1].equalsIgnoreCase("null")){
			  		        		ArrayPaymentBalance.get(l).put("SandeBalance", "0.00");
			  		        	}else{
				  		            String merchantId = Org_paymentinfo[1];
					  		        String BalanceData = "0";
						        	Map<String, Object> SandBalArray = new HashMap<String, Object>();
						        	if(tt==0){
						        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
						  		    	BalanceData = (String) JSONretdata.get("balance");
						  		    	SandBalArray.put("merchantId", merchantId);
						  		    	SandBalArray.put("BalanceData", BalanceData);
						  		    	ArraySandBalance.add(SandBalArray);
						  		    	tt=tt+1;
						        	}else{
						        		for(int h=0; h<ArraySandBalance.size();h++){
						        			if(ArraySandBalance.get(h).get("merchantId").toString().equalsIgnoreCase(merchantId)){
						        				BalanceData = ArraySandBalance.get(h).get("BalanceData").toString();
						        				break;
						        			}else{
								        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
								  		    	BalanceData = (String) JSONretdata.get("balance");
								  		    	SandBalArray.put("merchantId", merchantId);
								  		    	SandBalArray.put("BalanceData", BalanceData);
								  		    	ArraySandBalance.add(SandBalArray);
								  		    	break;
						        			}
						        		}
						        	}
//					  		      	String JSONretdata = QueryBalanceDemo.BalanceQuery(merchantId); // query Sande Balance
//				  		        	//input all orgs Sande acc balance into treasurydb
//					  				JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//					  		    	String BalanceData = (String) obj.get("balance"); // old sdk
					  		    	Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
		//			  		        balanceQuery = "12000";
					  				System.out.println("Query Sande balance:");
					  				System.out.println(balanceQuery);
					  				System.out.println("BigDecimal balance:");
					  				System.out.println(Sandebalance);
					  				ArrayPaymentBalance.get(l).put("SandeBalance", Sandebalance);
			  		        	}
			  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
			  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("杉德支付")){
			  		        		StringBuffer ss =  new StringBuffer();
			  		        		CurrentPaymentACC = String.valueOf(ss.append("杉德支付").append("-").append(Sandebalance));
					  				ArrayPaymentBalance.get(l).put("PayrollChannel", CurrentPaymentACC);
			  		        	}else{
			  		        		ArrayPaymentBalance.get(l).put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
				  		        }
			  		        	if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(ShiroSessionUtil.getLoginSession().getCompany_name())){
//				  					   TreasuryDBStatisticOverAll.setT_TreasuryDB_RiskMargin(ebibalance);
				  		        }
			  		        }
		  		          }
				  		}
			  		    else{
			  		    	continue;
			  		    }
		  		  	}
	            }
	  	     }
		   }
		}
        System.out.print(ArrayPaymentBalance);
//        int updateNum = treasuryDBMainService.updateByBalanceRefresh(ArrayPaymentBalance);
		
		//get ebi balance
//		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
    	String BalanceData = "0";String merchantId = null;
		if (AgencyOrgnization.getT_O_OrgName().equals("ALL")){
			   merchantId = "872684173615000";
			   String retData = AmtQueryServlet.main(merchantId);
			   System.out.println("Query Chinaebipay String:");
			   System.out.println(retData);
			   JSONObject obj = (JSONObject) JSON.parse(retData);
			   String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
			   ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
			   System.out.println("Query Chinaebipay balance:");
			   System.out.println(balanceQuery);
			   System.out.println("BigDecimal balance:");
			   System.out.println(ebibalance);

//			   TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(ebibalance);
		}
    	
    	//call sandewebAPI to get the total balance.
//        String BalanceDatanewStr = "1200000"; //local server test using

		if (AgencyOrgnization.getT_O_OrgName().equals("ALL")){
		    merchantId = "S2135052";
            JSONObject obj = MerBalanceQueryDemo.main(merchantId);
	    	BalanceData = (String) obj.get("balance");
			System.out.print("sandpay:");
			System.out.print(obj);
//	    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", "");

            BigDecimal BalanceAmtTotal = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
	    	System.out.print("Decimal-data :");
	    	System.out.print(BalanceAmtTotal);
	    	System.out.print(";");

//	    	TreasuryDBStatisticOverAll.setT_TreasuryDB_RiskMargin(BalanceAmtTotal);
		}

//    	System.out.print("获取值： ");	
//    	System.out.print(BalanceDatanewStr);
//		extension function,credit auth    	
//    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95); //no use code
//        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
		
        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
	        String t_TreasuryDB_AgencyName = null;
	        paramMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
	        paramMap.put("t_TreasuryDB_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
	        List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findAllList(paramMap);
//	        model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
	        model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
        }else {
        	paramMap.put("t_TreasuryDB_AgencyName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
            List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findAgencyAllList(paramMap);
            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
        }
    	return "treasuryDBMain/treasuryDBMainList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "treasuryDBMainSearchList")
    public String TreasuryDBMainSearchList(TreasuryDBMain TreasuryDBMain, @RequestParam( defaultValue = "0" )  Integer platform,String t_TreasuryDB_OrgName,
    		String t_TreasuryDB_ID,String t_TreasuryDB_Comment,String remark,Integer t_TreasuryDB_PayrollDate,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        
    	String t_SearchDB_OrgName = t_TreasuryDB_OrgName;
    	t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//      String[] t_P_VendorCompany = null;
        
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对
//    	TreasuryDBMain TreasuryDBStatisticOverAll = treasuryDBMainService.StatisticOverall(t_TreasuryDB_OrgName);
	    OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
		paramMap.put("t_Pymt_Name", t_TreasuryDB_OrgName);
		List<Paymentvendormgt> paymentvendormgtAllList = paymentvendormgtService.findAllList(paramMap);
	    paramMap.put("t_P_VendorCompany",t_TreasuryDB_OrgName);

    	List<Map<String, Object>> ArrayPaymentBalance = new ArrayList();
		String RCretData = null;
		BigDecimal ebibalance = new BigDecimal(0);
		
		if ( t_TreasuryDB_OrgName.equals("ALL") ){
	        for(int i=0;i<paymentvendormgtAllList.size();i++){
	     		String arr_paymentCertinfo = paymentvendormgtAllList.get(i).getT_Pymt_CertificationCode();
	  	    	if (arr_paymentCertinfo.equalsIgnoreCase("shdy")) {
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
	  	        for(int t=0;t<arr_paymentinfo.length;t++) {
		        	Map<String, Object> paramSQLmap = new HashMap<String, Object>();
  		  	        OrganizationInfo PayrollChannel = null;
	  		        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
		  		    for(int j=0; j<Org_paymentinfo.length; j++) {
		  		        System.out.print(j);
		  		        System.out.print(Org_paymentinfo[j]);
		  		        String balanceQuery = "0";
		  		        if (j==0){
		  		        	paramSQLmap.put("CompanyName", Org_paymentinfo[0]);
		  		        }
		  		        if (j==1){
		  		        	if (Org_paymentinfo[j].equalsIgnoreCase("null")){
			  				    paramSQLmap.put("ChinaebiBalance", "0.00");
		  		        	}else{
			  		        String merchantId = Org_paymentinfo[j];
		  		        	String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
		  		        	//input all orgs Chinaebi acc balance into treasurydb			   System.out.println(retData);
		  				    JSONObject obj = (JSONObject) JSON.parse(ret_Data);
		  				    balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
		  				    ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
		  				    System.out.println("Query Chinaebipay balance:");
		  				    System.out.println(balanceQuery);
		  				    System.out.println("BigDecimal balance:");
		  				    System.out.println(ebibalance);
		  				    paramSQLmap.put("ChinaebiBalance", ebibalance);
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
			  		  	    String CurrentPaymentACC = null;
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("电银支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("电银支付").append("-").append(ebibalance));
			  		        	paramSQLmap.put("AgencyCompanyBlance", CurrentPaymentACC);
		  		        	}else{
		  		        		paramSQLmap.put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
		  		        	};
		  		        	}
		  		        }
		  		    }
				  	System.out.println(t);
				  	System.out.println("Add-");
			  	    System.out.println(paramSQLmap);
			  	    ArrayPaymentBalance.add(paramSQLmap);
	  	      }
	       }else if (arr_paymentCertinfo.equalsIgnoreCase("shsd")) {
	    	    System.out.println(ArrayPaymentBalance);
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
	  	    	List<Map<String, Object>> ArraySandBalance = new ArrayList();
	  	        for(int t=0;t<arr_paymentinfo.length;t++) {
		  	        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
		  	        System.out.println(ArrayPaymentBalance.get(t).get("CompanyName"));
		  	        System.out.println(Org_paymentinfo[0]);
			  	    OrganizationInfo PayrollChannel = null;
		        	int tt = 0;
 		        if (Org_paymentinfo[0].equals(ArrayPaymentBalance.get(t).get("CompanyName"))){
  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		    for(int k=0; k<Org_paymentinfo.length; k++) {
		  		        System.out.print(k);
		  		        System.out.print(Org_paymentinfo[k]);
		  		        String balanceQuery = "0";
		  		        if (k==2){
		  		        	if ( Org_paymentinfo[1].equalsIgnoreCase("null")){
		  		        		ArrayPaymentBalance.get(t).put("SandeBalance", "0.00");
		  		        	}else{
			  		        String merchantId = Org_paymentinfo[1];
			  		        String BalanceData = "0";
				        	Map<String, Object> SandBalArray = new HashMap<String, Object>();
				        	if(tt == 0){
				        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
				  		    	BalanceData = (String) JSONretdata.get("balance");
				  		    	SandBalArray.put("merchantId", merchantId);
				  		    	SandBalArray.put("BalanceData", BalanceData);
				  		    	ArraySandBalance.add(SandBalArray);
				  		    	tt = tt + 1;
				        	}else{
				        		for(int h=0; h<ArraySandBalance.size();h++){
				        			if(ArraySandBalance.get(h).get("merchantId").toString().equalsIgnoreCase(merchantId)){
				        				BalanceData = ArraySandBalance.get(h).get("BalanceData").toString();
				        				break;
				        			}else{
						        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
						  		    	BalanceData = (String) JSONretdata.get("balance");
						  		    	SandBalArray.put("merchantId", merchantId);
						  		    	SandBalArray.put("BalanceData", BalanceData);
						  		    	ArraySandBalance.add(SandBalArray);
						  		    	break;
				        			}
				        		}
				        	}
//			  		      	String JSONretdata = QueryBalanceDemo.BalanceQuery(merchantId); // query Sande Balance
//		  		        	//input all orgs Sande acc balance into treasurydb
//			  				JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//			  		    	String BalanceData = (String) obj.get("balance");
			  		    	BigDecimal Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
//			  		        balanceQuery = "12000"; // debug using
			  				System.out.println("Query Sande balance:");
			  				System.out.println(balanceQuery);
			  				System.out.println("BigDecimal balance:");
			  				System.out.println(Sandebalance);
			  				ArrayPaymentBalance.get(t).put("SandeBalance", Sandebalance);
		  		        	String CurrentPaymentACC = "0";
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("杉德支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("杉德支付").append("-").append(Sandebalance));
				  				ArrayPaymentBalance.get(t).put("PayrollChannel", CurrentPaymentACC);
		  		        	}else{
		  		        		ArrayPaymentBalance.get(t).put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
			  		        }
		  		        	}
		  		        }
		  		    }
 		        }else{
 		        	continue;
 		        }
	  	       }
 	    	}
	      }
		}else if( AgencyOrgnization.getT_O_listOrg().equalsIgnoreCase("on")){
		    List<PersonalInfo> personalInfo = personalInfoService.findSubCompany(paramMap);
	        for(int i=0;i<paymentvendormgtAllList.size();i++){
	     		String arr_paymentCertinfo = paymentvendormgtAllList.get(i).getT_Pymt_CertificationCode();
	  	    	if (arr_paymentCertinfo.equalsIgnoreCase("shdy")) {
	     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
	  	        System.out.print(i);
	  	        System.out.print(arr_paymentinfo[i]);
			    for(int l=0;l<personalInfo.size();l++){
		        	Map<String, Object> paramSQLmap = new HashMap<String, Object>();
			  	    OrganizationInfo PayrollChannel = organizationInfoService.selectAgencyName(personalInfo.get(l).getT_P_Company());
	  	          for(int t=0;t<arr_paymentinfo.length;t++) {
	  		        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
			  	    if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(Org_paymentinfo[0])){
		  		      for(int j=0; j<Org_paymentinfo.length; j++) {
		  		        System.out.print(j);
		  		        System.out.print(Org_paymentinfo[j]);
		  		        String balanceQuery = "0";
		  		        if (j==0){
		  		        	paramSQLmap.put("CompanyName", personalInfo.get(l).getT_P_Company());
		  		        }
		  		        if (j==1){
			  		  	    String CurrentPaymentACC = "0";
		  		        	if (Org_paymentinfo[j].equalsIgnoreCase("null")){
			  				    paramSQLmap.put("ChinaebiBalance", "0.00");
		  		        	}else{
				  		        String merchantId = Org_paymentinfo[j];
			  		        	String ret_Data = AmtQueryServlet.main(merchantId); // query Chinaebi Balance
			  		        	//input all orgs Chinaebi acc balance into treasurydb			   System.out.println(retData);
			  				    JSONObject obj = (JSONObject) JSON.parse(ret_Data);
			  				    balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
			  				    ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
			  				    System.out.println("Query Chinaebipay balance:");
			  				    System.out.println(balanceQuery);
			  				    System.out.println("BigDecimal balance:");
			  				    System.out.println(ebibalance);
			  				    paramSQLmap.put("ChinaebiBalance", ebibalance);
		  		        	}
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("电银支付")){
		  		        		StringBuffer ss =  new StringBuffer();
		  		        		CurrentPaymentACC = String.valueOf(ss.append("电银支付").append("-").append(ebibalance));
			  		        	paramSQLmap.put("AgencyCompanyBlance", CurrentPaymentACC);
		  		        	}else{
		  		        		paramSQLmap.put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
		  		        	}
		  		        	if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(ShiroSessionUtil.getLoginSession().getCompany_name())){
//		  					   TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(ebibalance);
		  		        	}
		  		        }
			  	      }
			  	    }else {
		  		    	continue;
		  		    }
		  		   }
				  	System.out.println(i);
				  	System.out.println("-Add-");
				  	System.out.println(l);
				  	System.out.println("-cycle-");
			  	    System.out.println(paramSQLmap);
			  	    ArrayPaymentBalance.add(paramSQLmap);
			      }
	  	    	}else if (arr_paymentCertinfo.equalsIgnoreCase("shsd")) {
		    	    System.out.println(ArrayPaymentBalance);
		  	        System.out.print(i);
					for(int l=0;l<ArrayPaymentBalance.size();l++){
		     		String[] arr_paymentinfo = paymentvendormgtAllList.get(i).getT_Pymt_OrgInfo().split(";");
		  	    	List<Map<String, Object>> ArraySandBalance = new ArrayList();
		  	        for(int t=0;t<arr_paymentinfo.length;t++) {
			  	        String[] Org_paymentinfo = arr_paymentinfo[t].split("-");
			  	        System.out.println(ArrayPaymentBalance.get(l).get("CompanyName"));
			  	        System.out.println(Org_paymentinfo[0]);
				  	    OrganizationInfo PayrollChannel = null;
			        	int tt = 0;
				  		if (Org_paymentinfo[0].equals(ArrayPaymentBalance.get(l).get("CompanyName"))){
		  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
		  		  	    for(int k=0; k<Org_paymentinfo.length; k++) {
			  		        System.out.print(k);
			  		        System.out.print(Org_paymentinfo[k]);
			  		        String balanceQuery = "0";
			  		        if (k==2){
			  		        	String CurrentPaymentACC = "0";
			  		        	BigDecimal Sandebalance = new BigDecimal(0);
			  		        	if ( Org_paymentinfo[1].equalsIgnoreCase("null")){
			  		        		ArrayPaymentBalance.get(l).put("SandeBalance", "0.00");
			  		        	}else{
				  		          String merchantId = Org_paymentinfo[1];
				  		          String BalanceData = "0";
						        	Map<String, Object> SandBalArray = new HashMap<String, Object>();
						        	if(tt ==0){
						        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
						  		    	BalanceData = (String) JSONretdata.get("balance");
						  		    	SandBalArray.put("merchantId", merchantId);
						  		    	SandBalArray.put("BalanceData", BalanceData);
						  		    	ArraySandBalance.add(SandBalArray);
						  		    	t = t + 1;
						        	}else{
						        		for(int h=0; h<ArraySandBalance.size();h++){
						        			if(ArraySandBalance.get(h).get("merchantId").toString().equalsIgnoreCase(merchantId)){
						        				BalanceData = ArraySandBalance.get(h).get("BalanceData").toString();
						        				break;
						        			}else{
								        		JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId); // query Sande Balance
								  		    	BalanceData = (String) JSONretdata.get("balance");
								  		    	SandBalArray.put("merchantId", merchantId);
								  		    	SandBalArray.put("BalanceData", BalanceData);
								  		    	ArraySandBalance.add(SandBalArray);
								  		    	break;
						        			}
						        		}
						        	}
//					  		      	String JSONretdata = QueryBalanceDemo.BalanceQuery(merchantId); // query Sande Balance
//				  		        	//input all orgs Sande acc balance into treasurydb
//					  				JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//					  		    	String BalanceData = (String) obj.get("balance");
					  		    	Sandebalance = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
		//			  		        balanceQuery = "12000";
					  				System.out.println("Query Sande balance:");
					  				System.out.println(balanceQuery);
					  				System.out.println("BigDecimal balance:");
					  				System.out.println(Sandebalance);
					  				ArrayPaymentBalance.get(l).put("SandeBalance", Sandebalance);
			  		        	}
			  		  	        PayrollChannel = organizationInfoService.selectAgencyName(Org_paymentinfo[0]);
			  		        	if (AgencyOrgnization.getT_O_OrgPayrollBankaccount().equalsIgnoreCase("杉德支付")){
			  		        		StringBuffer ss =  new StringBuffer();
			  		        		CurrentPaymentACC = String.valueOf(ss.append("杉德支付").append("-").append(Sandebalance));
					  				ArrayPaymentBalance.get(l).put("PayrollChannel", CurrentPaymentACC);
			  		        	}else{
			  		        		ArrayPaymentBalance.get(l).put("PayrollChannel", PayrollChannel.getT_O_OrgPayrollBankaccount());
				  		        }
			  		        	if (personalInfo.get(l).getT_P_Company().equalsIgnoreCase(ShiroSessionUtil.getLoginSession().getCompany_name())){
//				  					   TreasuryDBStatisticOverAll.setT_TreasuryDB_RiskMargin(ebibalance);
				  		        }
			  		        }
		  		          }
				  		}
			  		    else{
			  		    	continue;
			  		    }
		  		  	}
	            }
	  	     }
		   }
		}
        System.out.print(ArrayPaymentBalance);
//        int updateNum = treasuryDBMainService.updateByBalanceRefresh(ArrayPaymentBalance);
		
		//get ebi balance
//		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
    	String BalanceData = null;String merchantId = null;
		if (AgencyOrgnization.getT_O_OrgName().equals("ALL")){
			   merchantId = "872684173615000";
			   String retData = AmtQueryServlet.main(merchantId);
			   System.out.println("Query Chinaebipay String:");
			   System.out.println(retData);
			   JSONObject obj = (JSONObject) JSON.parse(retData);
			   String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
			   ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
			   System.out.println("Query Chinaebipay balance:");
			   System.out.println(balanceQuery);
			   System.out.println("BigDecimal balance:");
			   System.out.println(ebibalance);

//			   TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(ebibalance);
		}
    	
    	//call sandewebAPI to get the total balance debug.
//        String BalanceDatanewStr = "1200000"; //local server test using

//    	System.out.print("获取值： ");	
//    	System.out.print(BalanceDatanewStr);
//		extension function,credit auth    	
//    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95); //no use code
//        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
		
//        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//	        String t_TreasuryDB_AgencyName = null;
//	        paramMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
//	        paramMap.put("t_TreasuryDB_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
//	        List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findAllList(paramMap);
//	        model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
//	        model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//        }else {
//        	paramMap.put("t_TreasuryDB_AgencyName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
//            List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findAgencyAllList(paramMap);
//            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
//            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//        }
        
    	
    	model.addAttribute("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);
    	model.addAttribute("remark", remark);
    	model.addAttribute("t_TreasuryDB_Comment",t_TreasuryDB_Comment);
    	model.addAttribute("t_TreasuryDB_PayrollDate",t_TreasuryDB_PayrollDate);
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象

    	if (ShiroSessionUtil.getLoginSession().getCompany_name().equals("ALL")) {
		    merchantId = "S2135052";
            JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId);
	    	BalanceData = (String) JSONretdata.get("balance");
			System.out.print("Chinaebi:");
			System.out.print(JSONretdata);
	    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", ""); 
	        
	    	BigDecimal BalanceAmt = new BigDecimal(BalanceDatanewStr);
	    	BigDecimal BalanceAmtTotal = BalanceAmt.divide(BigDecimal.valueOf(100.00)).setScale(2,BigDecimal.ROUND_DOWN);
	    	System.out.print("Decimal-data :");
	    	System.out.print(BalanceAmtTotal);
	    	System.out.print(";");

//	    	TreasuryDBStatisticOverAll.setT_TreasuryDB_RiskMargin(BalanceAmtTotal);
        	paramSearchMap.put("t_TreasuryDB_OrgName", t_SearchDB_OrgName);//添加元素
        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素
            List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findSearchList(paramSearchMap);
//            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    	} else {
    		t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//        	TreasuryDBStatisticOverAll = treasuryDBMainService.StatisticOverall(t_TreasuryDB_OrgName);	
    		if (AgencyOrgnization.getT_O_listOrg().equals("off")){
	    		String t_TreasuryDB_AgencyName = null;
	    		paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
	    		paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
	        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
	        	paramSearchMap.put("remark", remark);//添加元素        
	        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素      
	            List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findSearchList(paramSearchMap);
//	            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
	            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    		}else{
    			paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_OrgName);//添加元素
        		paramSearchMap.put("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);//添加元素
            	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
            	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素    
            	paramSearchMap.put("remark", remark);//添加元素       
                List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findSearchList(paramSearchMap);
                model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
                model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    		}
        }
    	
		if(0 == platform) {
     		return "treasuryDBMain/treasuryDBMainList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "treasuryDBMain/treasuryDBMainList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(TreasuryDBMain TreasuryDBMain,OrganizationInfo organizationInfo,String t_TreasuryDB_ID, String operationType, Integer platform,
            HttpServletRequest request, HttpServletResponse response,String t_TreasuryDB_OrgName,
            Model model) throws UnsupportedEncodingException {
       	  model.addAttribute("platform", platform);    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值

         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
        	 TreasuryDBMain.setT_TreasuryDB_Main_3rdprtyPaymentVendor(ShiroSessionUtil.getLoginSession().getCompany_name());
        	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
        	 model.addAttribute("OrganizationInfo", OrganizationInfo);
        	return "treasuryDBMain/treasuryDBMainNewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
             return "treasuryDBMain/treasuryDBMainEdit";
          } else if (OperationTypeConstant.PYMTSWITCH.equals(operationType)) {
    		String t_TreasuryDB_OrgName_encode = request.getParameter("t_TreasuryDB_OrgName");
    		final String t_TreasuryDB_OrgName_get = URLDecoder.decode(t_TreasuryDB_OrgName_encode, "UTF-8");
    	    paramMap.put("t_P_Company", t_TreasuryDB_OrgName_get);
		    final List<PersonalInfo> personalInfo = personalInfoService.findAgencyCompany(paramMap);
	        List<String> Pymt_Company = new ArrayList<String>(){
	        	{
		        this.add("电银支付");
		        this.add("杉德支付");
		        if(!t_TreasuryDB_OrgName_get.equalsIgnoreCase(personalInfo.get(0).getT_P_VendorEmployeeName())){
			        this.add(personalInfo.get(0).getT_P_VendorEmployeeName());
		        	}
	        	}
	        };
	        model.addAttribute("t_TreasuryDB_OrgName",t_TreasuryDB_OrgName_get);
        	model.addAttribute("PymtCompany", Pymt_Company);
            return "treasuryDBMain/treasuryDBMainPymtswitch";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
              return "treasuryDBMain/treasuryDBMainView";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "treasuryDBMain/treasuryDBMainList";	
          } else {
            return "treasuryDBMain/treasuryDBMainList";            	
        }
    }
    
    
    @RequestMapping(value = "editTreasuryDBMain")
    @ResponseBody
    public String edittreasuryDBMain(TreasuryDBMain treasuryDBMain, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	treasuryDBMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	treasuryDBMain.setModify_time(new Date());
    	treasuryDBMainService.updateByPrimaryKeySelective(treasuryDBMain);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }   
    
    @RequestMapping(value = "pymtswitchTreasuryDBMain")
    @ResponseBody
    public String pymtswitchtreasuryDBMain(TreasuryDBMain treasuryDBMain, HttpServletRequest request,
                                           HttpServletResponse response, Model model, String PymtCompany, String remark, String t_TreasuryDB_OrgName, String platform) {
    	model.addAttribute("platform", platform);  
    	model.addAttribute("t_TreasuryDB_OrgName",t_TreasuryDB_OrgName);
    	model.addAttribute("PymtCompany",PymtCompany);
    	model.addAttribute("remark",remark);
	    OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
	    AgencyOrgnization.setT_O_OrgPayrollBankaccount(PymtCompany);
	    AgencyOrgnization.setRemark(remark);
	    AgencyOrgnization.setT_O_ID(AgencyOrgnization.getT_O_ID());
	    int rs = organizationInfoService.updateByPrimaryKeySelective(AgencyOrgnization);
	    if ( rs==1 ){
          return JsonBizTool.genJson(ExRetEnum.SUCCESS);
	    }else{
	      return JsonBizTool.genJson(ExRetEnum.FAIL);
	    }
    } 
    
    @RequestMapping(value = "creditCtrl")
    @ResponseBody
    public String creditCtrl(String operationType,String t_TreasuryDB_OrgName,HttpServletRequest request,
            HttpServletResponse response, Model model) throws Exception {
    	String t_TreasuryDB_OrgName_encode = request.getParameter("t_TreasuryDB_OrgName");
    	String t_TreasuryDB_OrgName_get = URLDecoder.decode(t_TreasuryDB_OrgName_encode, "UTF-8");
    	Integer rs = null;
    	if (operationType.equals("on")) {
    		 rs = personalInfoService.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
    	} else if(operationType.equals("off")) {
    		 rs = personalInfoService.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
    	} else if(operationType.equals("Refresh")) {
   		     rs = staffPrepayApplicationService.refreshCredit(t_TreasuryDB_OrgName_get);
   			if ( rs != 0) {
//   		    	rs = treasuryDBMainService.updateCreditRefresh(t_TreasuryDB_OrgName_get);
   		    }
   	    }
		if ( rs != 0) {
//    	rs = treasuryDBMainService.updateCreditStatus(t_TreasuryDB_OrgName_get);
    	}
		if ( rs != 0) {
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    	}else {
            return JsonBizTool.genJson(ExRetEnum.FAIL);
    	}
    }  
}
