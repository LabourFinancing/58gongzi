package com.qucai.sample.controller;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.MerBalanceQueryDemo;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.AmtQueryServlet;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.Paymentvendormgt;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.PaymentvendormgtService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;


@Controller
@RequestMapping(value = "/PaymentvendormgtController")
public class PaymentvendormgtController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private PaymentvendormgtService PaymentvendormgtService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象
    
    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象
    
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

	String[] query_input= {"00","1.0","RSA","AccQuery",String.valueOf(System.currentTimeMillis()),"872566359655004"};		

    @ModelAttribute
    public Paymentvendormgt get(@RequestParam(required = false) String t_TreasuryDB_ID) {
    	Paymentvendormgt entity = null;
        if (StringUtils.isNotBlank(t_TreasuryDB_ID)) {
            entity = PaymentvendormgtService.selectByPrimaryKey(t_TreasuryDB_ID);//用PaymentvendormgtService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new Paymentvendormgt();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     * @throws Exception 
     */
    
    @RequestMapping(value = {"PaymentvendormgtList",""})
    public String PaymentvendormgtList(Paymentvendormgt Paymentvendormgt, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_Pymt_Name,String t_TreasuryDB_ID,
    		String SessionCompanyName, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	
        t_Pymt_Name = ShiroSessionUtil.getLoginSession().getCompany_name();
//      String[] t_P_VendorCompany = null;
        
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对
	    OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_Pymt_Name);
//	    paramMap.put("t_P_VendorCompany",t_Pymt_Name);
	    paramMap.put("t_P_VendorCompany","美团买菜");
	    List<PersonalInfo> personalInfo = personalInfoService.findSubCompany(paramMap);
		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
		String RCretData = null;
		BigDecimal ebibalance = null;
		//get ebi balance
		if (merchantId != null){
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
		}
    	
    	//call sandewebAPI to get the total balance.
//        String BalanceDatanewStr = "1200000"; //local server test using
        JSONObject JSONretdata = MerBalanceQueryDemo.main(merchantId);
    	String BalanceData = (String) JSONretdata.get("balance");
		System.out.print("Chinaebi:");
		System.out.print(JSONretdata);
    	
    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", ""); 
//    	System.out.print("获取值： ");	
//    	System.out.print(BalanceDatanewStr);

//		extension function,credit auth    	
//    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95); //no use code
    	
    	BigDecimal BalanceAmt = new BigDecimal(BalanceDatanewStr);
    	BigDecimal BalanceAmtTotal = BalanceAmt.divide(BigDecimal.valueOf(100.00));
    	System.out.print("Decimal-data :");
    	System.out.print(BalanceAmtTotal);
    	System.out.print(";");

    	BalanceAmtTotal = BalanceAmtTotal.setScale(2,BigDecimal.ROUND_DOWN);
    	
    	Paymentvendormgt TreasuryDBStatisticOverAll = PaymentvendormgtService.StatisticOverall(t_Pymt_Name);	

//    	TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(BalanceAmtTotal);
//    	TreasuryDBStatisticOverAll.setT_TreasuryDB_PoPRatio(BalanceAmtTotal);
    	
//        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_Pymt_Name);
        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
	        String t_TreasuryDB_AgencyName = null;
	        paramMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
	        paramMap.put("t_Pymt_Name", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
	        List<Paymentvendormgt> TreasuryDBStatisticOverAllList = PaymentvendormgtService.findAllList(paramMap);
	        model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
	        model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
        }else {
        	paramMap.put("t_TreasuryDB_AgencyName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
            List<Paymentvendormgt> TreasuryDBStatisticOverAllList = PaymentvendormgtService.findAgencyAllList(paramMap);
            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
        }

    	return "Paymentvendormgt/PaymentvendormgtList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "PaymentvendormgtSearchList")
    public String PaymentvendormgtSearchList(Paymentvendormgt Paymentvendormgt, @RequestParam( defaultValue = "0" )  Integer platform,String t_Pymt_Name,
    		String t_TreasuryDB_ID,String t_TreasuryDB_Comment,String remark,Integer t_TreasuryDB_PayrollDate,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
    	
        t_Pymt_Name = ShiroSessionUtil.getLoginSession().getCompany_name();
//      String[] t_P_VendorCompany = null;
        
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对
	    OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_Pymt_Name);
//       String BalanceDatanewStr = "1200000"; // test using
		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
        JSONObject obj = MerBalanceQueryDemo.main(merchantId);
    	String BalanceData = (String) obj.get("balance");
    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", ""); 
        System.out.print("获取值： ");
    	System.out.print(BalanceDatanewStr);

      
        //extension function,credit auth    	
//    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95);
    	
    	BigDecimal BalanceAmt = new BigDecimal(BalanceDatanewStr);
    	BigDecimal BalanceAmtTotal = BalanceAmt.divide(BigDecimal.valueOf(100.00));
    	
    	model.addAttribute("t_Pymt_Name", t_Pymt_Name);
    	model.addAttribute("remark", remark);
    	model.addAttribute("t_TreasuryDB_Comment",t_TreasuryDB_Comment);
    	model.addAttribute("t_TreasuryDB_PayrollDate",t_TreasuryDB_PayrollDate);
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象

    	if (ShiroSessionUtil.getLoginSession().getCompany_name().equals("ALL")) {
        	Paymentvendormgt TreasuryDBStatisticOverAll = PaymentvendormgtService.StatisticOverall("ALL");	
//        	TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(BalanceAmtTotal);
//        	TreasuryDBStatisticOverAll.setT_TreasuryDB_PoPRatio(BalanceAmtTotal);
        	paramSearchMap.put("t_Pymt_Name", t_Pymt_Name);//添加元素
        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素
            List<Paymentvendormgt> TreasuryDBStatisticOverAllList = PaymentvendormgtService.findSearchList(paramSearchMap);
            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    	} else { 		
    		t_Pymt_Name = ShiroSessionUtil.getLoginSession().getCompany_name();
        	Paymentvendormgt TreasuryDBStatisticOverAll = PaymentvendormgtService.StatisticOverall(t_Pymt_Name);	
    		if (AgencyOrgnization.getT_O_listOrg().equals("off")){
    		String t_TreasuryDB_AgencyName = null;
    		paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
    		paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素        
        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素      
            List<Paymentvendormgt> TreasuryDBStatisticOverAllList = PaymentvendormgtService.findSearchList(paramSearchMap);
            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    		}else{
    			paramSearchMap.put("t_TreasuryDB_AgencyName", t_Pymt_Name);//添加元素
        		paramSearchMap.put("t_Pymt_Name", t_Pymt_Name);//添加元素
            	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
            	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素    
            	paramSearchMap.put("remark", remark);//添加元素       
                List<Paymentvendormgt> TreasuryDBStatisticOverAllList = PaymentvendormgtService.findSearchList(paramSearchMap);
                model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
                model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
    		}
        }
		if(0 == platform) {
     		return "Paymentvendormgt/PaymentvendormgtList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "Paymentvendormgt/PaymentvendormgtList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(Paymentvendormgt Paymentvendormgt,OrganizationInfo organizationInfo,String t_P_id, String operationType, Integer platform,
            HttpServletRequest request, HttpServletResponse response,String t_Pymt_Name,
            Model model) {
       	  model.addAttribute("platform", platform);    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
   
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
        	 Paymentvendormgt.setT_Pymt_Name(ShiroSessionUtil.getLoginSession().getCompany_name());
        	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
        	 model.addAttribute("OrganizationInfo", OrganizationInfo);
        	return "Paymentvendormgt/PaymentvendormgtNewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
             return "Paymentvendormgt/PaymentvendormgtEdit";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "Paymentvendormgt/PaymentvendormgtView";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "Paymentvendormgt/PaymentvendormgtList";	
          } else {
            return "Paymentvendormgt/PaymentvendormgtList";            	
        }
    }
    
    
    @RequestMapping(value = "editPaymentvendormgt")
    @ResponseBody
    public String editPaymentvendormgt(Paymentvendormgt Paymentvendormgt, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	Paymentvendormgt.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	Paymentvendormgt.setModify_time(new Date());
    	PaymentvendormgtService.updateByPrimaryKeySelective(Paymentvendormgt);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }   
    
    @RequestMapping(value = "creditCtrl")
    @ResponseBody
    public String creditCtrl(String operationType,String t_Pymt_Name,HttpServletRequest request,
            HttpServletResponse response, Model model) throws Exception {
    	String t_Pymt_Name_encode = request.getParameter("t_Pymt_Name");
    	String t_Pymt_Name_get = URLDecoder.decode(t_Pymt_Name_encode, "UTF-8");
    	Integer rs = null;
    	if (operationType.equals("on")) {
    		 rs = personalInfoService.updateCompanyStaffsCreditOn(t_Pymt_Name_get);
    	} else if(operationType.equals("off")) {
    		 rs = personalInfoService.updateCompanyStaffsCreditOff(t_Pymt_Name_get);
    	} else if(operationType.equals("Refresh")) {
   		     rs = staffPrepayApplicationService.refreshCredit(t_Pymt_Name_get);
   			if ( rs != 0) {
   		    	rs = PaymentvendormgtService.updateCreditRefresh(t_Pymt_Name_get);
   		    }
   	    }
		if ( rs != 0) {
    	rs = PaymentvendormgtService.updateCreditStatus(t_Pymt_Name_get);
    	}
		if ( rs != 0) {
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    	}else {
            return JsonBizTool.genJson(ExRetEnum.FAIL);
    	}
    }  
}
