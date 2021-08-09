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

    	List<Map<String, Object>> ArrayPaymentBalance = new ArrayList();
		String RCretData = null;
		BigDecimal ebibalance = null;
		
        System.out.print(ArrayPaymentBalance);
		
		String merchantId = null;
    	//call sandewebAPI to get the total balance.
//        String BalanceDatanewStr = "1200000"; //local server test using

		    merchantId = "S2135052";
            JSONObject obj = MerBalanceQueryDemo.main(merchantId);
	    	String BalanceData = (String) obj.get("balance");
			System.out.print("sandpay:");
			System.out.print(obj);
	    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", "");

            BigDecimal BalanceAmtTotal = (new BigDecimal(BalanceData)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
	    	System.out.print("Decimal-data :");
	    	System.out.print(BalanceAmtTotal);
	    	System.out.print(";");

            List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findAllList(paramMap);
            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);

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
		
        System.out.print(ArrayPaymentBalance);
//        int updateNum = treasuryDBMainService.updateByBalanceRefresh(ArrayPaymentBalance);
		
		//get ebi balance
//		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
    	String BalanceData = null;String merchantId = null;
    	
    	//call sandewebAPI to get the total balance debug.
        String BalanceDatanewStr = "1200000"; //local server test using
        
    	
    	model.addAttribute("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);
    	model.addAttribute("remark", remark);
    	model.addAttribute("t_TreasuryDB_Comment",t_TreasuryDB_Comment);
    	model.addAttribute("t_TreasuryDB_PayrollDate",t_TreasuryDB_PayrollDate);
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象


        paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_OrgName);//添加元素
        paramSearchMap.put("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);//添加元素
        paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
        paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素    
        paramSearchMap.put("remark", remark);//添加元素       
        List<TreasuryDBMain> TreasuryDBStatisticOverAllList = treasuryDBMainService.findSearchList(paramSearchMap);
        model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
        model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
        
    	
		if(0 == platform) {
     		return "treasuryDBMain/treasuryDBMainList";
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
