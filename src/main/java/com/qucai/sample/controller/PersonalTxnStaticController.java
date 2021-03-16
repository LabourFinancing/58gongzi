//package com.qucai.sample.controller;
//
//import java.io.UnsupportedEncodingException;
//import java.math.BigDecimal;
//import java.net.URLDecoder;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.qucai.sample.OperationTypeConstant;
//import com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo.QueryBalanceDemo;
//import com.qucai.sample.entity.OrganizationInfo;
//import com.qucai.sample.entity.TreasuryDBInfo;
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.service.OrganizationInfoService;
//import com.qucai.sample.service.PersonalInfoService;
//import com.qucai.sample.service.StaffPrepayApplicationService;
//import com.qucai.sample.service.TreasuryDBInfoService;
//import com.qucai.sample.util.JsonBizTool;
//import com.qucai.sample.util.ShiroSessionUtil;
//
//
//@Controller
//@RequestMapping(value = "/TreasuryDBInfoController")
//public class PersonalTxnStaticController {
//
//	
//	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
//	
//    @Autowired
//    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象
//    
//    @Autowired
//    private OrganizationInfoService organizationInfoService; //申明一个对象
//    
//    @Autowired
//    private PersonalInfoService personalInfoService; //申明一个对象
//    
//	@Autowired
//	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
//
//    @ModelAttribute
//    public TreasuryDBInfo get(@RequestParam(required = false) String t_TreasuryDB_ID) {
//    	TreasuryDBInfo entity = null;
//        if (StringUtils.isNotBlank(t_TreasuryDB_ID)) {
//            entity = treasuryDBInfoService.selectByPrimaryKey(t_TreasuryDB_ID);//用TreasuryDBInfoService对象属性方法去调用t_FProd_ID并返回
//        }
//        if (entity == null) {
//            entity = new TreasuryDBInfo();
//        }
//        return entity;
//    }
//
//    /**
//     *  改动：根据所属平台来确定是哪个平台的资源 
//     * @throws Exception 
//     */
//    
//    @RequestMapping(value = {"treasuryDBInfoList",""})
//    public String TreasuryDBInfoList(TreasuryDBInfo TreasuryDBInfo, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_TreasuryDB_OrgName,String t_TreasuryDB_ID,
//    		String SessionCompanyName, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//    	
//        t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对
//    	
//    	//call sandewebAPI to get the total balance.
////        String BalanceDatanewStr = "1200000"; //local server test using
//    	String JSONretdata = QueryBalanceDemo.BalanceQuery();
//    	JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//    	String BalanceData = (String) obj.get("balance");
//    	
////    	String BalanceData = QueryBalanceDemo.BalanceQuery().substring(12,25); //no use code
//    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", ""); 
//    	System.out.print("获取值： ");	
//    	System.out.print(BalanceDatanewStr);
//
////		extension function,credit auth    	
////    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95); //no use code
//    	
//    	BigDecimal BalanceAmt = new BigDecimal(BalanceDatanewStr);
//    	BigDecimal BalanceAmtTotal = BalanceAmt.divide(BigDecimal.valueOf(100.00));
//    	System.out.print("Decimal-data :");
//    	System.out.print(BalanceAmtTotal);
//    	System.out.print(";");
//
//    	BalanceAmtTotal = BalanceAmtTotal.setScale(2,BigDecimal.ROUND_DOWN);
//    	
//    	TreasuryDBInfo TreasuryDBStatisticOverAll = treasuryDBInfoService.StatisticOverall(t_TreasuryDB_OrgName);	
//
//    	TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(BalanceAmtTotal);
//    	TreasuryDBStatisticOverAll.setT_TreasuryDB_PoPRatio(BalanceAmtTotal);
//    	
//        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
//        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//	        String t_TreasuryDB_AgencyName = null;
//	        paramMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
//	        paramMap.put("t_TreasuryDB_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
//	        List<TreasuryDBInfo> TreasuryDBStatisticOverAllList = treasuryDBInfoService.findAllList(paramMap);
//	        model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
//	        model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//        }else {
//        	paramMap.put("t_TreasuryDB_AgencyName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
//            List<TreasuryDBInfo> TreasuryDBStatisticOverAllList = treasuryDBInfoService.findAgencyAllList(paramMap);
//            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAllList);
//            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//        }
//
//    	return "treasuryDBInfo/treasuryDBInfoList";
//    }
//  
//    /*
//     * Search Function
//     */
//    @RequestMapping(value = "treasuryDBInfoSearchList")
//    public String TreasuryDBInfoSearchList(TreasuryDBInfo TreasuryDBInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_TreasuryDB_OrgName,
//    		String t_TreasuryDB_ID,String t_TreasuryDB_Comment,String remark,Integer t_TreasuryDB_PayrollDate,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//    	
////       String BalanceDatanewStr = "1200000"; // test using
//    	String JSONretdata = QueryBalanceDemo.BalanceQuery();
//    	JSONObject obj = (JSONObject) JSON.parse(JSONretdata);
//    	String BalanceData = (String) obj.get("balance");
//    	
////    	String BalanceData = QueryBalanceDemo.BalanceQuery().substring(12,25); 
//    	String BalanceDatanewStr = BalanceData.replaceFirst("^0*", ""); 
//        System.out.print("获取值： ");
//    	System.out.print(BalanceDatanewStr);
//
//      
//        //extension function,credit auth    	
////    	String CreditData = QueryBalanceDemo.BalanceQuery().substring(83,95);
//    	
//    	BigDecimal BalanceAmt = new BigDecimal(BalanceDatanewStr);
//    	BigDecimal BalanceAmtTotal = BalanceAmt.divide(BigDecimal.valueOf(100.00));
//    	
//    	model.addAttribute("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);
//    	model.addAttribute("remark", remark);
//    	model.addAttribute("t_TreasuryDB_Comment",t_TreasuryDB_Comment);
//    	model.addAttribute("t_TreasuryDB_PayrollDate",t_TreasuryDB_PayrollDate);
//    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
//    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
//
//    	if (ShiroSessionUtil.getLoginSession().getCompany_name().equals("ALL")) {
//        	TreasuryDBInfo TreasuryDBStatisticOverAll = treasuryDBInfoService.StatisticOverall("ALL");	
//        	TreasuryDBStatisticOverAll.setT_TreasuryDB_BoPRatio(BalanceAmtTotal);
//        	TreasuryDBStatisticOverAll.setT_TreasuryDB_PoPRatio(BalanceAmtTotal);
//        	paramSearchMap.put("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);//添加元素
//        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
//        	paramSearchMap.put("remark", remark);//添加元素
//        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素
//            List<TreasuryDBInfo> TreasuryDBStatisticOverAllList = treasuryDBInfoService.findSearchList(paramSearchMap);
//            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
//            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//    	} else { 		
//    		t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//        	TreasuryDBInfo TreasuryDBStatisticOverAll = treasuryDBInfoService.StatisticOverall(t_TreasuryDB_OrgName);	
//    		OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
//    		if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//    		String t_TreasuryDB_AgencyName = null;
//    		paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_AgencyName);//添加元素
//    		paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
//        	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
//        	paramSearchMap.put("remark", remark);//添加元素        
//        	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素      
//            List<TreasuryDBInfo> TreasuryDBStatisticOverAllList = treasuryDBInfoService.findSearchList(paramSearchMap);
//            model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
//            model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//    		}else{
//    			paramSearchMap.put("t_TreasuryDB_AgencyName", t_TreasuryDB_OrgName);//添加元素
//        		paramSearchMap.put("t_TreasuryDB_OrgName", t_TreasuryDB_OrgName);//添加元素
//            	paramSearchMap.put("t_TreasuryDB_Comment", t_TreasuryDB_Comment);//添加元素
//            	paramSearchMap.put("t_TreasuryDB_PayrollDate", t_TreasuryDB_PayrollDate);//添加元素    
//            	paramSearchMap.put("remark", remark);//添加元素       
//                List<TreasuryDBInfo> TreasuryDBStatisticOverAllList = treasuryDBInfoService.findSearchList(paramSearchMap);
//                model.addAttribute("TreasuryDBStatisticOverAll", TreasuryDBStatisticOverAll);
//                model.addAttribute("TreasuryDBStatisticOverAllList", TreasuryDBStatisticOverAllList);
//    		}
//        }
//		if(0 == platform) {
//     		return "treasuryDBInfo/treasuryDBInfoList";
////    	} else if(1 == platform) {
////    		return "financeProduct/financeProductEntList";
////    	} else if(2 == platform) {
////    		//个人端，暂时不考虑
////    		return "financeProduct/financeProductList";
//    	}else {
//    		return "treasuryDBInfo/treasuryDBInfoList";
//    	}
//    }
//    
//    
//    @RequestMapping(value = "form")
//    public String form(TreasuryDBInfo TreasuryDBInfo,OrganizationInfo organizationInfo,String t_P_id, String operationType, Integer platform,
//            HttpServletRequest request, HttpServletResponse response,String t_TreasuryDB_OrgName,
//            Model model) {
//       	  model.addAttribute("platform", platform);    	
//          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
//          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
//          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
//   
//         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
//        	 TreasuryDBInfo.setT_TreasuryDB_OrgName(ShiroSessionUtil.getLoginSession().getCompany_name());
//        	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
//        	 model.addAttribute("OrganizationInfo", OrganizationInfo);
//        	return "treasuryDBInfo/treasuryDBInfoNewForm";
//          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
//             return "treasuryDBInfo/treasuryDBInfoEdit";
//          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
//            return "treasuryDBInfo/treasuryDBInfoView";
//          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
//              return "treasuryDBInfo/treasuryDBInfoList";	
//          } else {
//            return "treasuryDBInfo/treasuryDBInfoList";            	
//        }
//    }
//    
//    
//    @RequestMapping(value = "editTreasuryDBInfo")
//    @ResponseBody
//    public String edittreasuryDBInfo(TreasuryDBInfo treasuryDBInfo, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//    	treasuryDBInfo.setModifier(ShiroSessionUtil.getLoginSession().getId());
//    	treasuryDBInfo.setModify_time(new Date());
//    	treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfo);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }   
//    
//    @RequestMapping(value = "creditCtrl")
//    @ResponseBody
//    public String creditCtrl(String operationType,String t_TreasuryDB_OrgName,HttpServletRequest request,
//            HttpServletResponse response, Model model) throws Exception {
//    	String t_TreasuryDB_OrgName_encode = request.getParameter("t_TreasuryDB_OrgName");
//    	String t_TreasuryDB_OrgName_get = URLDecoder.decode(t_TreasuryDB_OrgName_encode, "UTF-8");
//    	Integer rs = null;
//    	if (operationType.equals("on")) {
//    		 rs = personalInfoService.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
//    	} else if(operationType.equals("off")) {
//    		 rs = personalInfoService.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
//    	} else if(operationType.equals("Refresh")) {
//   		     rs = staffPrepayApplicationService.refreshCredit(t_TreasuryDB_OrgName_get);
//   			if ( rs != 0) {
//   		    	rs = treasuryDBInfoService.updateCreditRefresh(t_TreasuryDB_OrgName_get);
//   		    }
//   	    }
//		if ( rs != 0) {
//    	rs = treasuryDBInfoService.updateCreditStatus(t_TreasuryDB_OrgName_get);
//    	}
//		if ( rs != 0) {
//            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    	}else {
//            return JsonBizTool.genJson(ExRetEnum.FAIL);
//    	}
//    }  
//}
