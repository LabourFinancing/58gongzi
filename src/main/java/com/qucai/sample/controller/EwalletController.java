//package com.qucai.sample.controller;
//
//import com.github.pagehelper.PageInfo;
//import com.qucai.sample.OperationTypeConstant;
//import com.qucai.sample.common.PageParam;
//import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.entity.OrganizationInfo;
//import com.qucai.sample.entity.PersonalInfo;
//import com.qucai.sample.entity.Ewallet;
//import com.qucai.sample.entity.StaffPrepayApplicationList;
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.service.*;
//import com.qucai.sample.util.JsonBizTool;
//import com.qucai.sample.util.ShiroSessionUtil;
//import com.qucai.sample.util.Tool;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//@Controller
//@RequestMapping(value = "/PersonalInfoController")
//public class EwalletController {
//
//	
//	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
//    @Autowired
//    private EwalletService ewalletService; //申明一个对象
//    
//    @Autowired
//    private PersonalInfoService personalInfoService; //申明一个对象
//    
//    @Autowired
//    private OrganizationInfoService organizationInfoService;
//    
//	@Autowired
//	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
//	
//    @Autowired
//    private FinanceProductService financeProductService; //申明一个对象
//    
//    private Object OrganizationInfo;
//
//	@ModelAttribute
//    public PersonalInfo get(@RequestParam(required = false) String t_P_id,String t_P_Company,String t_P_VendorEmployeeName,String t_O_OrgName,HttpServletRequest request,Model model) {
//    	PersonalInfo entity = null;
//    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
//    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
//    	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//    		t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//            t_P_VendorEmployeeName = null;
//    	 }else{
//    		t_P_Company = null;
//    		t_P_VendorEmployeeName = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//    	 }
//        if (StringUtils.isNotBlank(t_P_id)) {
//               entity = personalInfoService.selectByPrimaryKey(t_P_id);//用PersonalInfoService对象属性方法去调用t_FProd_ID并返回
//               return entity;
//        }
//        if (entity == null) {
//            entity = new PersonalInfo();
//        }
//		return entity;
//    }
//
//    /**
//     *  改动：根据所属平台来确定是哪个平台的资源 
//     */
//    
//    @RequestMapping(value = {"personalInfoList",""})
//    public String personalInfoList(PersonalInfo personalInfo, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_P_Company,
//    		String t_P_Name,String t_P_PID,String t_P_Mobil,String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
//    		HttpServletRequest request, HttpServletResponse response, Model model) {
//    	
//    	model.addAttribute("t_P_Name", t_P_Name); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("t_P_PID", t_P_PID); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("t_P_Mobil", t_P_Mobil); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("t_P_Company", t_P_Company); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("t_P_VendorEmployeeName", t_P_VendorEmployeeName); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
//    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
//    	
//        if (t_P_Name != null | t_P_PID != null | t_P_Mobil != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
//        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
//        	paramSearchMap.put("t_P_Name", t_P_Name);//添加元素
//        	paramSearchMap.put("t_P_PID", t_P_PID);//添加元素
//        	paramSearchMap.put("t_P_Mobil", t_P_Mobil);//添加元素
//        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
//        	paramSearchMap.put("remark", remark);//添加元素
//        	
//        	if (t_O_OrgName.equals("ALL")){
//            	paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
//        	}
//            else {
//            	//Flag on Agency or not
//            	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//                     paramSearchMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
//            		 paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
//            	 }else{
//                     paramSearchMap.put("t_P_Company", t_P_Company);
//            		 paramSearchMap.put("t_P_VendorEmployeeName", t_O_OrgName);
//            	 }
//            	//Agency filter
//        	}
//            PageParam pp = Tool.genPageParam(request);  
//            PageInfo<PersonalInfo> page = personalInfoService.findSearchList(pp, paramSearchMap);
//            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
//    	} else {
//    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
//    		if (t_O_OrgName.equals("ALL")) {
//    			t_P_Company = null;
//    		}else {
//                //Flag on Agency or not
//             	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//             		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
//             		paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
//             	 }else{
//             		paramMap.put("t_P_Company", t_P_Company);
//             		paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
//             	 }
//             	//Agency filter
//    		}
//            PageParam pp = Tool.genPageParam(request);    
//            PageInfo<PersonalInfo> page = personalInfoService.findAllList(paramMap, pp);
//            model.addAttribute("page", page);
//        }
//    	return "personalInfo/personalInfoList";
//    }
//  
//    /*
//     * Search Function
//     */
//    @RequestMapping(value = "personalInfoSearchList")
//    public String personalInfoSearchList(PersonalInfo personalInfo,OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_P_Name,
//    		String t_P_PID,String t_P_Mobil,String t_P_Company,String t_P_VendorEmployeeName,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
//    		HttpServletRequest request, HttpServletResponse response, Model model) {
//    	
//    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
//    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
//    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
//    	
//    	if (t_P_Name != null | t_P_PID != null | t_P_Mobil != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
//        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
//        	paramSearchMap.put("t_P_Name", t_P_Name);//添加元素
//        	paramSearchMap.put("t_P_PID", t_P_PID);//添加元素
//        	paramSearchMap.put("t_P_Mobil", t_P_Mobil);//添加元素
//        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
//        	paramSearchMap.put("remark", remark);//添加元素
//        	if (t_O_OrgName.equals("ALL")){
//            	paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
//        	}
//            else {
//            //Flag on Agency or not
//           	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//            		paramSearchMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
//               		paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
//           	 }else{
//            		paramSearchMap.put("t_P_Company", t_P_Company);
//               		paramSearchMap.put("t_P_VendorEmployeeName", t_O_OrgName);
//           	 }
//           	//Agency filter
//        	}
//            PageParam pp = Tool.genPageParam(request);  
//            PageInfo<PersonalInfo> page = personalInfoService.findSearchList(pp, paramSearchMap);
//            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
//    	} else {
//    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
//            //Flag on Agency or not
//          	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//          		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
//          		paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
//          	 }else{
//          		paramMap.put("t_P_Company", t_P_Company);
//          		paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
//          	 }
//          	//Agency filter
//            PageParam pp = Tool.genPageParam(request);           
//            PageInfo<PersonalInfo> page = personalInfoService.findAllList(paramMap, pp);
//            model.addAttribute("page", page);
//        }
//		if(0 == platform) {
//     		return "personalInfo/personalInfoList";
////    	} else if(1 == platform) {
////    		return "financeProduct/financeProductEntList";
////    	} else if(2 == platform) {
////    		//个人端，暂时不考虑
////    		return "financeProduct/financeProductList";
//    	}else {
//    		return "personalInfo/personalInfoList";
//    	}
//    }
//    
//    
//    @RequestMapping(value = "form")
//    public String form(PersonalInfo personalInfo,OrganizationInfo organizationInfo,String t_P_id, String operationType, Integer platform, 
//            HttpServletRequest request, HttpServletResponse response,String t_P_Company,
//            Model model) {
//       	  model.addAttribute("platform", platform);
//          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
//          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
//          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
//          t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
//         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        
//            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
//         	FinanceProduct financeProduct;
//           if (t_P_Company.equals("ALL")){
//            paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
//         	paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
//         	paramSearchMap.put("t_O_listOrg", "on");
//          	List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
//          	List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
//         	List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
//          	model.addAttribute("FinanceProduct", FinanceProduct);
//          	model.addAttribute("OrganizationInfo", OrganizationInfo);
//          	model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
//           }else {
//        	 paramMap.put("t_P_Company", t_P_Company);//添加元素
//          	 paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
//          	 paramSearchMap.put("t_O_listOrg", "on");
//          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
//          	 List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
//          	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
//          	 model.addAttribute("FinanceProduct", FinanceProduct);
//          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
//          	 model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
//           }
//        	return "personalInfo/personalInfoNewForm";
//          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
//            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
//           	FinanceProduct financeProduct;
//             if (t_P_Company.equals("ALL")){
//            	paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
//              	 paramSearchMap.put("t_O_listOrg", "on");
//            	List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
//            	List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
//             	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
//            	model.addAttribute("FinanceProduct", FinanceProduct);
//            	 model.addAttribute("OrganizationInfo", OrganizationInfo);
//               	model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
//             }else {
//          	 paramMap.put("t_P_Company", t_P_Company);//添加元素
//          	 paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
//          	 paramSearchMap.put("t_O_listOrg", "on");
//            	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
//            	 List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
//             	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
//            	 model.addAttribute("FinanceProduct", FinanceProduct);
//            	 model.addAttribute("OrganizationInfo", OrganizationInfo);
//            	 model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
//             }
//            personalInfo = personalInfoService.selectByPrimaryKey(t_P_id);
//            return "personalInfo/personalInfoEditForm";
//          } else if (OperationTypeConstant.EDITCREDITBALANCE.equals(operationType)) {
//            personalInfo = personalInfoService.selectByPrimaryKey(t_P_id);
//            return "personalInfo/personalInfoEditCredit";
//          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
//        	personalInfo = personalInfoService.selectByPrimaryKey(t_P_id);
//            return "personalInfo/personalInfoViewForm";
//          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
//              return "personalInfo/personalInfoVerifyList";	
//          } else {
//            return "redirect:/personalInfoController/personalInfoList";	
//        }
//    }
//    
//    @RequestMapping(value = "addPersonalInfo")   //当判断页面的行为为add时,返回相应的add页面
//    @ResponseBody
//    public String addPersonalInfo(PersonalInfo personalInfo, HttpServletRequest request,Integer platform,Date modify_time,
//            HttpServletResponse response, Model model) {
//    	model.addAttribute("platform", platform);
//    	personalInfo.setCreator(ShiroSessionUtil.getLoginSession().getId());
//    	personalInfo.setCreate_time(new Date());
//    	personalInfo.setT_P_id(Tool.uuid());
//    	personalInfo.setT_P_SysUpdateDate(new Date());
//     	
//     	if (modify_time == null){
//     		personalInfo.setModify_time(new Date());
//     	};
//     	personalInfoService.insertSelective(personalInfo);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//
//    @RequestMapping(value = "deletePersonalInfo")
//    public String deleteFinanceProduct(String t_P_id, Integer platform, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//    	personalInfoService.deleteByPrimaryKey(t_P_id);
//    	model.addAttribute("platform", platform);
//        return "redirect:/PersonalInfoController/personalInfoList?platform="+platform;
//    }
//
//    @RequestMapping(value = "creditRefreshPersonalInfo")
//    public String creditRefreshPersonalInfo(String t_P_id, Integer platform, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//    	PersonalInfo personalInfo = personalInfoService.selectByPrimaryKey(t_P_id);
//    	String t_Txn_PrepayApplierName = personalInfo.getT_P_Name();
//    	String t_Txn_PrepayApplierPID = personalInfo.getT_P_PID();
//    	String t_Txn_Paystatus = personalInfo.getT_P_Company();
//    	int retdata = staffPrepayApplicationService.deleteTxnPayment(t_Txn_PrepayApplierName, t_Txn_PrepayApplierPID, t_Txn_Paystatus);
//    	model.addAttribute("platform", platform);
//        return "redirect:/PersonalInfoController/personalInfoList?platform="+platform;
//    }
//
//    
//    @RequestMapping(value = "editPersonalInfo")
//    @ResponseBody
//    public String editPersonalInfo(PersonalInfo personalInfo, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//    	personalInfo.setModifier(ShiroSessionUtil.getLoginSession().getId());
//    	personalInfo.setModify_time(new Date());
//    	personalInfoService.updateByPrimaryKeySelective(personalInfo);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//    
//    @RequestMapping(value = "editCreditBalance")
//    @ResponseBody
//    public String editCreditBalance(PersonalInfo personalInfo, HttpServletRequest request,String t_P_Mobil,BigDecimal t_P_NetMonthlyBonusAmount,
//            HttpServletResponse response, Model model) {
//    	personalInfo.setModifier(ShiroSessionUtil.getLoginSession().getId());
//    	personalInfo.setModify_time(new Date());
//    	String OrderCodeUpdate = null;
//    	BigDecimal CreditBalanceAmtRefund = null;
//        StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(t_P_Mobil);
//        int rs = 0;
//        if(staffPrepayApplicationCredit != null){
//	        staffPrepayApplicationCredit.setT_Txn_BalanceCreditNum(t_P_NetMonthlyBonusAmount);
//	        staffPrepayApplicationCredit.setT_Txn_PrepayCounts(staffPrepayApplicationCredit.getT_Txn_CreditPrepayBalanceNum().intValue());
//	        staffPrepayApplicationCredit.setT_Txn_CreditPrepayBalanceNum(t_P_NetMonthlyBonusAmount);
//	        CreditBalanceAmtRefund = t_P_NetMonthlyBonusAmount;
//	        OrderCodeUpdate = staffPrepayApplicationCredit.getT_Txn_Num();
//	        rs = staffPrepayApplicationService.updateCreditBalanceAmt(CreditBalanceAmtRefund, OrderCodeUpdate);
//        }else{
//        	CreditBalanceAmtRefund = t_P_NetMonthlyBonusAmount;
//        	personalInfo.setT_P_CreditPrepaySalaryAmount(CreditBalanceAmtRefund);
//        	rs = personalInfoService.updateByPrimaryKeySelective(personalInfo);
//        }
//        
//
//        
//        if(rs==1){
//        	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//        }else{
//  	        return JsonBizTool.genJson(ExRetEnum.FAIL);
//        }
//    }
//}
