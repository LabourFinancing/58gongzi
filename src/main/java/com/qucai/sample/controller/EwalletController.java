package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
import com.qucai.sample.util.DBConnection;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.MobileEwalletDashboard;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    @RequestMapping(value = "mobile-ewalletquery")
    public String ewalletquery(String t_P_id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ewalletService.deleteByPrimaryKey(t_P_id);
    	model.addAttribute("platform", platform);
        return "redirect:/EwalletController/ewalletList?platform="+platform;
    }

    @RequestMapping(value = "mobile-ewalletbalance")
    public String ewalletbalance(String t_P_id, Integer platform, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        ewalletService.deleteByPrimaryKey(t_P_id);
        model.addAttribute("platform", platform);
        return "redirect:/EwalletController/ewalletList?platform="+platform;
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
    
    /*
    钱包首页 移动端首页
     */
 /*
    移动端我的模块
     */
    public String addMobileEwallet(String personalMID,String pid,String phone,String realName) throws SQLException {
//        ewallet.setModifier(ShiroSessionUtil.getLoginSession().getId());
//        ewallet.setModify_time(new Date());
//        ewallet.setT_personalewallet_TotCNYBalance(new BigDecimal(new BigDecimal("0.00")));

        String rsPersonalEwallet = null;
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        
        MobileEwalletDashboard mobileEwalletDashboard = null; // intial Personal Ewallet
        // set inital value into ewallet
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ID(personalMID);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierID(pid);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(pid);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierName(realName);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Passport("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ScanCode("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_QRcode("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_alipayAcc("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_wechatpayAcc("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_unionpayAcc("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_CryptoC("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Voucher("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_VoucherDigi("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Creditcard("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Debitcard("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearOrg("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDate(new Date());
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdName("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Reciept("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotCNYBalance(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotFXBalance(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotCryptoBalance(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotAssetES(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_DebitPayAmt(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplyPayAmount(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_CreditPrepayBalanceNum(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_CreditPayAmt(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_CreditPayAmtInit(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalPrepayAmt(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Interest(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TotLimit(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Worth(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_DiscountRate(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_BalanceInterest(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueRepaymentDate(null);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_PrepayClear("0");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Overdue("0");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueTotalAmount(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_FinancialInterest(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ServiceFee(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Poundage(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_TierPoundage(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_InterestMargin(new BigDecimal("0.00"));
        mobileEwalletDashboard.setT_mobilePersonalEwallet_BankAccName("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_BankAcc("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_SysUpdateDate(new Date());
        mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Paystatus("1");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_SMS("");
        mobileEwalletDashboard.setT_MobilePersonalewallet_PaymentVersion("");
        mobileEwalletDashboard.setT_MobilePersonalewallet_AccCat("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_eproposal("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Txt3("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Txt4("");
        mobileEwalletDashboard.setT_mobilePersonalEwallet_Txt5("");
        
        
        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplyPayAmount(new BigDecimal("0.00"));
        
        String t_personalewallet_ApplierID = pid;
        String initialPersonalEwallet = "gfshxxkjfwyxgs-v-a";
        String sql="insert into t_personal_ewallet " +
            "(t_personalewallet_ID," +
            "t_personalewallet_ApplierID," +
            "t_personalewallet_ApplierPID," +
            "t_personalewallet_ApplierName," +
            "t_personalewallet_Passport," +
            "t_personalewallet_ScanCode," +
            "t_personalewallet_QRcode," +
            "t_personalewallet_alipayAcc," +
            "t_personalewallet_wechatpayAcc," +
            "t_personalewallet_unionpayAcc," +
            "t_personalewallet_CryptoC," +
            "t_personalewallet_Voucher," +
            "t_personalewallet_VoucherDigi," +
            "t_personalewallet_Creditcard," +
            "t_personalewallet_Debitcard," +
            "t_personalewallet_ClearNum," +
            "t_personalewallet_ClearOrg," +
            "t_personalewallet_PayCat," +
            "t_personalewallet_PayDate," +
            "t_personalewallet_ProdName," +
            "t_personalewallet_PayDays," +
            "t_personalewallet_Reciept," +
            "t_personalewallet_TotCNYBalance," +
            "t_personalewallet_TotFXBalance," +
            "t_personalewallet_TotCryptoBalance," +
            "t_personalewallet_TotAssetES," +
            "t_personalewallet_DebitPayAmt," +
            "t_personalewallet_ApplyPayAmount," +
            "t_personalewallet_CreditPrepayBalanceNum," +
            "t_personalewallet_CreditPayAmt," +
            "t_personalewallet_CreditPayAmtInit," +
            "t_personalewallet_TotalPrepayAmt," +
            "t_personalewallet_TotalInterestDays," +
            "t_personalewallet_PayCounts," +
            "t_personalewallet_Interest," +
            "t_personalewallet_TotLimit," +
            "t_personalewallet_Worth," +
            "t_personalewallet_DiscountRate," +
            "t_personalewallet_BalanceInterest," +
            "t_personalewallet_OverdueRepaymentDate," +
            "t_personalewallet_PrepayClear," +
            "t_personalewallet_Overdue," +
            "t_personalewallet_OverdueTotalAmount," +
            "t_personalewallet_FinancialInterest," +
            "t_personalewallet_ServiceFee," +
            "t_personalewallet_Poundage," +
            "t_personalewallet_TierPoundage," +
            "t_personalewallet_InterestMargin," +
            "t_personalewallet_BankAccName," +
            "t_personalewallet_BankAcc," +
            "t_personalewallet_SysUpdateDate," +
            "t_personalewallet_OverdueDays," +
            "t_personalewallet_Paystatus," +
            "t_personalewallet_SMS," +
            "t_personalewallet_PaymentVersion," +
            "t_personalewallet_AccCat," +
            "t_personalewallet_bkp," +
            "t_personalewallet_treasuryID," +
            "t_personalewallet_eproposal," +
            "t_personalewallet_DigiAddress," +
            "t_personalewallet_Txt4," +
            "t_personalewallet_Txt5," +
            "platform," +
            "modifier," +
            "modify_time" +
            "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setString(1,mobileEwalletDashboard.getT_mobilePersonalEwallet_ID());
            ptmt.setString(2,mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierID());
            ptmt.setString(3,mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID());
            ptmt.setString(4,mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierName());
            ptmt.setString(5,mobileEwalletDashboard.getT_mobilePersonalEwallet_Passport());
            ptmt.setString(6,mobileEwalletDashboard.getT_mobilePersonalEwallet_ScanCode());
            ptmt.setString(7,mobileEwalletDashboard.getT_mobilePersonalEwallet_QRcode());
            ptmt.setString(8,mobileEwalletDashboard.getT_mobilePersonalEwallet_alipayAcc());
            ptmt.setString(9,mobileEwalletDashboard.getT_mobilePersonalEwallet_wechatpayAcc());
            ptmt.setString(10,mobileEwalletDashboard.getT_mobilePersonalEwallet_unionpayAcc());
            ptmt.setString(11,mobileEwalletDashboard.getT_mobilePersonalEwallet_CryptoC());
            ptmt.setString(12,mobileEwalletDashboard.getT_mobilePersonalEwallet_Voucher());
            ptmt.setString(13,mobileEwalletDashboard.getT_mobilePersonalEwallet_VoucherDigi());
            ptmt.setString(14,mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard());
            ptmt.setString(15,mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard());
            ptmt.setString(16,mobileEwalletDashboard.getT_mobilePersonalEwallet_ClearNum());
            ptmt.setString(17,mobileEwalletDashboard.getT_mobilePersonalEwallet_ClearOrg());
            ptmt.setString(18,mobileEwalletDashboard.getT_mobilePersonalEwallet_PayCat());
            ptmt.setDate(19, (java.sql.Date) mobileEwalletDashboard.getT_mobilePersonalEwallet_PayDate());
            ptmt.setString(20,mobileEwalletDashboard.getT_mobilePersonalEwallet_ProdName());
            ptmt.setInt(21,mobileEwalletDashboard.getT_mobilePersonalEwallet_PayDays());
            ptmt.setString(22,mobileEwalletDashboard.getT_mobilePersonalEwallet_Reciept());
            ptmt.setBigDecimal(23,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotCNYBalance());
            ptmt.setBigDecimal(24,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotFXBalance());
            ptmt.setBigDecimal(25,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotCryptoBalance());
            ptmt.setBigDecimal(26,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotAssetES());
            ptmt.setBigDecimal(27,mobileEwalletDashboard.getT_mobilePersonalEwallet_DebitPayAmt());
            ptmt.setBigDecimal(28,mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplyPayAmount());
            ptmt.setBigDecimal(29,mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPrepayBalanceNum());
            ptmt.setBigDecimal(30,mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPayAmt());
            ptmt.setBigDecimal(31,mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPayAmtInit());
            ptmt.setBigDecimal(32,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotalPrepayAmt());
            ptmt.setInt(33,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotalInterestDays());
            ptmt.setInt(34,mobileEwalletDashboard.getT_mobilePersonalEwallet_PayCounts());
            ptmt.setBigDecimal(35,mobileEwalletDashboard.getT_mobilePersonalEwallet_Interest());
            ptmt.setBigDecimal(36,mobileEwalletDashboard.getT_mobilePersonalEwallet_TotLimit());
            ptmt.setBigDecimal(37,mobileEwalletDashboard.getT_mobilePersonalEwallet_Worth());
            ptmt.setBigDecimal(38,mobileEwalletDashboard.getT_mobilePersonalEwallet_DiscountRate());
            ptmt.setBigDecimal(39,mobileEwalletDashboard.getT_mobilePersonalEwallet_BalanceInterest());
            ptmt.setDate(40, (java.sql.Date) mobileEwalletDashboard.getT_mobilePersonalEwallet_OverdueRepaymentDate());
            ptmt.setString(41,mobileEwalletDashboard.getT_mobilePersonalEwallet_PrepayClear());
            ptmt.setString(42,mobileEwalletDashboard.getT_mobilePersonalEwallet_Overdue());
            ptmt.setBigDecimal(43,mobileEwalletDashboard.getT_mobilePersonalEwallet_OverdueTotalAmount());
            ptmt.setBigDecimal(44,mobileEwalletDashboard.getT_mobilePersonalEwallet_FinancialInterest());
            ptmt.setBigDecimal(45,mobileEwalletDashboard.getT_mobilePersonalEwallet_ServiceFee());
            ptmt.setBigDecimal(46,mobileEwalletDashboard.getT_mobilePersonalEwallet_Poundage());
            ptmt.setBigDecimal(47,mobileEwalletDashboard.getT_mobilePersonalEwallet_TierPoundage());
            ptmt.setBigDecimal(48,mobileEwalletDashboard.getT_mobilePersonalEwallet_InterestMargin());
            ptmt.setString(49,mobileEwalletDashboard.getT_mobilePersonalEwallet_BankAccName());
            ptmt.setString(50,mobileEwalletDashboard.getT_mobilePersonalEwallet_BankAcc());
            ptmt.setDate(51,(java.sql.Date) mobileEwalletDashboard.getT_mobilePersonalEwallet_SysUpdateDate());
            ptmt.setInt(52,mobileEwalletDashboard.getT_mobilePersonalEwallet_OverdueDays());
            ptmt.setString(53,mobileEwalletDashboard.getT_mobilePersonalEwallet_Paystatus());
            ptmt.setString(54,mobileEwalletDashboard.getT_mobilePersonalEwallet_SMS());
            ptmt.setString(55,mobileEwalletDashboard.getT_MobilePersonalewallet_PaymentVersion());
            ptmt.setString(56,mobileEwalletDashboard.getT_MobilePersonalewallet_AccCat());
            ptmt.setString(57,mobileEwalletDashboard.getT_mobilePersonalEwallet_bkp()); 
            ptmt.setString(58,mobileEwalletDashboard.getT_mobilePersonalEwallet_treasuryID());
            ptmt.setString(59,mobileEwalletDashboard.getT_mobilePersonalEwallet_eproposal());
            ptmt.setString(60,mobileEwalletDashboard.getT_mobilePersonalEwallet_Txt3());
            ptmt.setString(61,mobileEwalletDashboard.getT_mobilePersonalEwallet_Txt4());
            ptmt.setString(62,mobileEwalletDashboard.getT_mobilePersonalEwallet_Txt5());
            ptmt.setString(63,"mobile");
            ptmt.setString(64,personalMID);
            ptmt.setDate(65,(java.sql.Date) new Date());
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            rsPersonalEwallet = "failed";
            return rsPersonalEwallet;
        }finally {
            conn.close();
        }

//        personalMain.setT_personal_main_id(Tool.uuid());
//        personalMain.setT_personal_main_facialret(facialret);
//        personalMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
//        personalMain.setModify_time(new Date());
//        String OrderCodeUpdate = null;
//        BigDecimal CreditBalanceAmtRefund = null;

        rsPersonalEwallet =  "succ";

        return rsPersonalEwallet;
    }
    public String ewalletList(Ewallet ewallet) {
        System.out.print("succ");
        return "succ";
    }
}
