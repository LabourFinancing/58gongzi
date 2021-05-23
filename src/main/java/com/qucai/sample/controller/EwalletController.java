package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.FinanceProduct;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.Ewallet;
import com.qucai.sample.entity.PersonalMain;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
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
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/EwalletController")
public class EwalletController {


	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
    @Autowired
    private EwalletService ewalletService; //申明一个对象

    @Autowired
    private PersonalMainService personalMainService; //申明一个对象

    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象

    @Autowired
    private OrganizationInfoService organizationInfoService;

	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

    @Autowired
    private FinanceProductService financeProductService; //申明一个对象

    private Object OrganizationInfo;

	@ModelAttribute
    public Ewallet get(@RequestParam(required = false) String t_personalewallet_ID,String t_P_Company,String t_P_VendorEmployeeName,String t_O_OrgName,HttpServletRequest request,Model model) {
    	Ewallet entity = null;
    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
//    	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//    		t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//            t_P_VendorEmployeeName = null;
//    	 }else{
//    		t_P_Company = null;
//    		t_P_VendorEmployeeName = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//    	 }
        if (StringUtils.isNotBlank(t_personalewallet_ID)) {
               entity = ewalletService.selectByPrimaryKey(t_personalewallet_ID);//用EwalletService对象属性方法去调用t_FProd_ID并返回
               return entity;
        }
        if (entity == null) {
            entity = new Ewallet();
        }
		return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */

    @RequestMapping(value = {"ewalletList",""})
    public String ewalletList(Ewallet ewallet, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_personalewallet_ApplierName,
    		String t_personalewallet_ApplierID,String t_personalewallet_ApplierPID,String t_P_Mobil,String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	model.addAttribute("t_personalewallet_ApplierName", t_personalewallet_ApplierName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_personalewallet_ApplierPID", t_personalewallet_ApplierPID); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_personalewallet_ApplierID", t_personalewallet_ApplierID); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_Company", t_P_Company); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_VendorEmployeeName", t_P_VendorEmployeeName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

        if (t_personalewallet_ApplierName != null | t_personalewallet_ApplierPID != null | t_personalewallet_ApplierID != null | t_P_Company != "ALL" | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_personalewallet_ApplierName", t_personalewallet_ApplierName);//添加元素
        	paramSearchMap.put("t_personalewallet_ApplierPID", t_personalewallet_ApplierPID);//添加元素
        	paramSearchMap.put("t_personalewallet_ApplierID", t_personalewallet_ApplierID);//添加元素
        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
            paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素

        	if (t_O_OrgName.equals("ALL")){
            	paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
        	}
            else {
            	//Flag on Agency or not
            	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                     paramSearchMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
            		 paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
            	 }else{
                     paramSearchMap.put("t_P_Company", t_P_Company);
            		 paramSearchMap.put("t_P_VendorEmployeeName", t_O_OrgName);
            	 }
            	//Agency filter
        	}
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<Ewallet> page = ewalletService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
            paramMap.put("t_P_Company", t_P_Company);
    		if (t_O_OrgName.equals("ALL")) {
    			t_P_Company = null;
    		}else {
                //Flag on Agency or not
             	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
             		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
             		paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
             	 }else{
             		paramMap.put("t_P_Company", t_P_Company);
             		paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
             	 }
             	//Agency filter
    		}
            PageParam pp = Tool.genPageParam(request);    
            PageInfo<Ewallet> page = ewalletService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
    	return "ewallet/ewalletList";
    }

    /*
     * Search Function
     */
    @RequestMapping(value = "ewalletSearchList")
    public String ewalletSearchList(Ewallet ewallet,OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_personalewallet_ApplierName,
    		String t_personalewallet_ApplierID,String t_personalewallet_ApplierPID,String t_P_Mobil,String t_P_Company,String t_P_VendorEmployeeName,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

    	if (t_personalewallet_ApplierName != null | t_personalewallet_ApplierPID != null | t_personalewallet_ApplierID != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_P_Name", t_personalewallet_ApplierName);//添加元素
        	paramSearchMap.put("t_personalewallet_ApplierPID", t_personalewallet_ApplierPID);//添加元素
            paramSearchMap.put("t_personalewallet_ApplierID", t_personalewallet_ApplierID);//添加元素
        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
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
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<Ewallet> page = ewalletService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
            //Flag on Agency or not
          	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
          		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
          		paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
          	 }else{
          		paramMap.put("t_P_Company", t_P_Company);
          		paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
          	 }
          	//Agency filter
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<Ewallet> page = ewalletService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "ewallet/ewalletList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "ewallet/ewalletList";
    	}
    }


    @RequestMapping(value = "form")
    public String form(Ewallet ewallet,OrganizationInfo organizationInfo,String t_P_id, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,String t_P_Company,
            Model model) {
       	  model.addAttribute("platform", platform);
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
          t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
         	FinanceProduct financeProduct;
           if (t_P_Company.equals("ALL")){
            paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
         	paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
         	paramSearchMap.put("t_O_listOrg", "on");
          	List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
          	List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
         	List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
          	model.addAttribute("FinanceProduct", FinanceProduct);
          	model.addAttribute("OrganizationInfo", OrganizationInfo);
          	model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
           }else {
        	 paramMap.put("t_P_Company", t_P_Company);//添加元素
          	 paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
          	 paramSearchMap.put("t_O_listOrg", "on");
          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
          	 List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
          	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
          	 model.addAttribute("FinanceProduct", FinanceProduct);
          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
          	 model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
           }
        	return "ewallet/ewalletNewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
           	FinanceProduct financeProduct;
             if (t_P_Company.equals("ALL")){
            	paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
              	 paramSearchMap.put("t_O_listOrg", "on");
            	List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
            	List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
             	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
            	model.addAttribute("FinanceProduct", FinanceProduct);
            	 model.addAttribute("OrganizationInfo", OrganizationInfo);
               	model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
             }else {
          	 paramMap.put("t_P_Company", t_P_Company);//添加元素
          	 paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
          	 paramSearchMap.put("t_O_listOrg", "on");
            	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
            	 List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
             	 List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
            	 model.addAttribute("FinanceProduct", FinanceProduct);
            	 model.addAttribute("OrganizationInfo", OrganizationInfo);
            	 model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
             }
            ewallet = ewalletService.selectByPrimaryKey(t_P_id);
            return "ewallet/ewalletEditForm";
          } else if (OperationTypeConstant.EDITCREDITBALANCE.equals(operationType)) {
            ewallet = ewalletService.selectByPrimaryKey(t_P_id);
            return "ewallet/ewalletEditCredit";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
        	ewallet = ewalletService.selectByPrimaryKey(t_P_id);
            return "ewallet/ewalletViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "ewallet/ewalletVerifyList";	
          } else {
            return "redirect:/ewalletController/ewalletList";	
        }
    }

    @RequestMapping(value = "addEwallet")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addEwallet(Ewallet ewallet, HttpServletRequest request,Integer platform,Date modify_time,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	ewallet.setCreator(ShiroSessionUtil.getLoginSession().getId());
    	ewallet.setCreate_time(new Date());
    	ewallet.setT_personalewallet_ID(Tool.uuid());
    	ewallet.setCreate_time(new Date());

     	if (modify_time == null){
     		ewallet.setModify_time(new Date());
     	};
     	ewalletService.insertSelective(ewallet);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteewallet")
    public String deleteFinanceProduct(String t_P_id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ewalletService.deleteByPrimaryKey(t_P_id);
    	model.addAttribute("platform", platform);
        return "redirect:/EwalletController/ewalletList?platform="+platform;
    }

    @RequestMapping(value = "creditRefreshEwallet")
    public String creditRefreshEwallet(String t_P_id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	Ewallet ewallet = ewalletService.selectByPrimaryKey(t_P_id);
    	String t_Txn_PrepayApplierName = ewallet.getT_personalewallet_ApplierName();
    	String t_Txn_PrepayApplierPID = ewallet.getT_personalewallet_ApplierPID();
    	String t_Txn_Paystatus = ewallet.getT_personalewallet_Paystatus();
    	int retdata = staffPrepayApplicationService.deleteTxnPayment(t_Txn_PrepayApplierName, t_Txn_PrepayApplierPID, t_Txn_Paystatus);
    	model.addAttribute("platform", platform);
        return "redirect:/EwalletController/ewalletList?platform="+platform;
    }


    @RequestMapping(value = "editEwallet")
    @ResponseBody
    public String editEwallet(Ewallet ewallet, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ewallet.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	ewallet.setModify_time(new Date());
    	ewalletService.updateByPrimaryKeySelective(ewallet);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "editCreditBalance")
    @ResponseBody
    public String editCreditBalance(Ewallet ewallet, HttpServletRequest request,String t_P_Mobil,BigDecimal t_P_NetMonthlyBonusAmount,
            HttpServletResponse response, Model model) {
    	ewallet.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	ewallet.setModify_time(new Date());
    	String OrderCodeUpdate = null;
    	BigDecimal CreditBalanceAmtRefund = null;
        StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(t_P_Mobil);
        int rs = 0;
        if(staffPrepayApplicationCredit != null){
	        staffPrepayApplicationCredit.setT_Txn_BalanceCreditNum(t_P_NetMonthlyBonusAmount);
	        staffPrepayApplicationCredit.setT_Txn_PrepayCounts(staffPrepayApplicationCredit.getT_Txn_CreditPrepayBalanceNum().intValue());
	        staffPrepayApplicationCredit.setT_Txn_CreditPrepayBalanceNum(t_P_NetMonthlyBonusAmount);
	        CreditBalanceAmtRefund = t_P_NetMonthlyBonusAmount;
	        OrderCodeUpdate = staffPrepayApplicationCredit.getT_Txn_Num();
	        rs = staffPrepayApplicationService.updateCreditBalanceAmt(CreditBalanceAmtRefund, OrderCodeUpdate);
        }else{
        	CreditBalanceAmtRefund = t_P_NetMonthlyBonusAmount;
        	ewallet.setT_personalewallet_TotCNYBalance(CreditBalanceAmtRefund);
        	rs = ewalletService.updateByPrimaryKeySelective(ewallet);
        }
        if(rs==1){
        	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
  	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }
}
