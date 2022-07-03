package com.qucai.sample.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mchange.v2.c3p0.impl.NewProxyCallableStatement;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderPayDemo;
import com.qucai.sample.service.*;
import com.qucai.sample.util.*;
import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobilePersonalMain;
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
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @author Spear Yao .
 * Date: 1-July,2022
 * Overall Control the Personal Ewallet, Including Ewallet initialization, Txn initialization
 * Ewallet Updating/Refresh/Realtime renew.
 */


@Controller
@RequestMapping(value = "/EwalletTxnController")
public class EwalletTxnController {
    
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
    @Autowired
    private EwalletTxnService ewalletTxnService; //申明一个对象
    
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
    public EwalletTxn get(@RequestParam(required = false) String t_WalletTxn_ID,String t_P_Company,String t_P_VendorEmployeeName,String t_O_OrgName,HttpServletRequest request,Model model) {
    	EwalletTxn entity = null;
    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
    		t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
            t_P_VendorEmployeeName = null;
    	 }else{
    		t_P_Company = null;
    		t_P_VendorEmployeeName = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
    	 }
        if (StringUtils.isNotBlank(t_WalletTxn_ID)) {
               entity = ewalletTxnService.selectByPrimaryKey(t_WalletTxn_ID);//用EwalletTxnService对象属性方法去调用t_FProd_ID并返回
               return entity;
        }
        if (entity == null) {
            entity = new EwalletTxn();
        }
		return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */

    @RequestMapping(value = {"ewalletTxnList",""})
    public String ewalletTxnList(EwalletTxn ewalletTxn, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_P_Company,
    		String t_WalletTxn_PayerName,String t_WalletTxn_PayerPID,String t_WalletTxn_ReceiverName,String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

    	model.addAttribute("t_WalletTxn_PayerName", t_WalletTxn_PayerName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_WalletTxn_PayerPID", t_WalletTxn_PayerPID); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_WalletTxn_ReceiverName", t_WalletTxn_ReceiverName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_Company", t_P_Company); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_VendorEmployeeName", t_P_VendorEmployeeName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

        if (t_WalletTxn_PayerName != null | t_WalletTxn_PayerPID != null | t_WalletTxn_ReceiverName != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_WalletTxn_PayerName", t_WalletTxn_PayerName);//添加元素
        	paramSearchMap.put("t_WalletTxn_PayerPID", t_WalletTxn_PayerPID);//添加元素
        	paramSearchMap.put("t_WalletTxn_ReceiverName", t_WalletTxn_ReceiverName);//添加元素
        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
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
            PageInfo<EwalletTxn> page = ewalletTxnService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
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
            PageInfo<EwalletTxn> page = ewalletTxnService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
    	return "ewalletTxn/ewalletTxnList";
    }

    /*
     * Search Function
     */
    @RequestMapping(value = "ewalletTxnSearchList")
    public String ewalletTxnSearchList(EwalletTxn ewalletTxn,OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_WalletTxn_PayerName,
    		String t_WalletTxn_PayerPID,String t_WalletTxn_ReceiverName,String t_P_Company,String t_P_VendorEmployeeName,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

    	if (t_WalletTxn_PayerName != null | t_WalletTxn_PayerPID != null | t_WalletTxn_ReceiverName != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_WalletTxn_PayerName", t_WalletTxn_PayerName);//添加元素
        	paramSearchMap.put("t_WalletTxn_PayerPID", t_WalletTxn_PayerPID);//添加元素
        	paramSearchMap.put("t_WalletTxn_ReceiverName", t_WalletTxn_ReceiverName);//添加元素
        	paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);//添加元素
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
            PageInfo<EwalletTxn> page = ewalletTxnService.findSearchList(pp, paramSearchMap);
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
            PageInfo<EwalletTxn> page = ewalletTxnService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "ewalletTxn/ewalletTxnList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "ewalletTxn/ewalletTxnList";
    	}
    }


    @RequestMapping(value = "form")
    public String form(EwalletTxn ewalletTxn,OrganizationInfo organizationInfo,String t_P_id, String operationType, Integer platform, 
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
        	return "ewalletTxn/ewalletTxnNewForm";
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
            ewalletTxn = ewalletTxnService.selectByPrimaryKey(t_P_id);
            return "ewalletTxn/ewalletTxnEditForm";
          } else if (OperationTypeConstant.EDITCREDITBALANCE.equals(operationType)) {
            ewalletTxn = ewalletTxnService.selectByPrimaryKey(t_P_id);
            return "ewalletTxn/ewalletTxnEditCredit";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
        	ewalletTxn = ewalletTxnService.selectByPrimaryKey(t_P_id);
            return "ewalletTxn/ewalletTxnViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "ewalletTxn/ewalletTxnVerifyList";	
          } else {
            return "redirect:/ewalletTxnController/ewalletTxnList";	
        }
    }

    @RequestMapping(value = "addEwalletTxn")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addEwalletTxn(EwalletTxn ewalletTxn, HttpServletRequest request,Integer platform,Date modify_time,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	ewalletTxn.setCreator(ShiroSessionUtil.getLoginSession().getId());
    	ewalletTxn.setCreate_time(new Date());
    	ewalletTxn.setT_WalletTxn_ID(Tool.uuid());
    	ewalletTxn.setT_WalletTxn_TxnTimeout(new Date());

     	if (modify_time == null){
     		ewalletTxn.setModify_time(new Date());
     	};
     	ewalletTxnService.insertSelective(ewalletTxn);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteewalletTxn")
    public String deleteFinanceProduct(String t_P_id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ewalletTxnService.deleteByPrimaryKey(t_P_id);
    	model.addAttribute("platform", platform);
        return "redirect:/ewalletTxnController/ewalletTxnList?platform="+platform;
    }

    @RequestMapping(value = "creditRefreshEwalletTxn")
    public String creditRefreshEwalletTxn(String t_P_id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	EwalletTxn ewalletTxn = ewalletTxnService.selectByPrimaryKey(t_P_id);
    	String t_Txn_PrepayApplierName = ewalletTxn.getT_WalletTxn_PayerName();
    	String t_Txn_PrepayApplierPID = ewalletTxn.getT_WalletTxn_PayerName();
    	String t_Txn_Paystatus = ewalletTxn.getT_WalletTxn_Paystatus();
    	int retdata = staffPrepayApplicationService.deleteTxnPayment(t_Txn_PrepayApplierName, t_Txn_PrepayApplierPID, t_Txn_Paystatus);
    	model.addAttribute("platform", platform);
        return "redirect:/EwalletTxnController/ewalletTxnList?platform="+platform;
    }
    
    @RequestMapping(value = "editEwalletTxn")
    @ResponseBody
    public String editEwalletTxn(EwalletTxn ewalletTxn, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ewalletTxn.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	ewalletTxn.setModify_time(new Date());
    	ewalletTxnService.updateByPrimaryKeySelective(ewalletTxn);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "editCreditBalance")
    @ResponseBody
    public String editCreditBalance(EwalletTxn ewalletTxn, HttpServletRequest request,String t_P_Mobil,BigDecimal t_P_NetMonthlyBonusAmount,
            HttpServletResponse response, Model model) {
    	ewalletTxn.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	ewalletTxn.setModify_time(new Date());
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
        	ewalletTxn.setT_WalletTxn_TotTxnAmount(CreditBalanceAmtRefund);
        	rs = ewalletTxnService.updateByPrimaryKeySelective(ewalletTxn);
        }
        
        if(rs==1){
        	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
  	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }

    /* mobile interaction
   移动端交易模块
    */
    @RequestMapping(value = "personalEWTTxnMobile")
    @ResponseBody
    public String personalEWTTxnMobile(Ewallet ewallet, HttpServletRequest request,String t_P_Mobil,BigDecimal t_P_NetMonthlyBonusAmount,
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
移动端个人
*/
    public static Map<String, Object> TxnStatusCheck(String txnID, Connection conn) throws SQLException {
        if  (conn.isClosed()) {
            DBConnection dao = new DBConnection();
            conn = dao.getConnection();
        }
        Map<String, Object> rsEwalletTxnStatus = new HashMap<String, Object>();
        ResultSet rs = null;
        String sql = "select * from gognzi.t_ewallettxn_info where t_ewallettxn_info.t_WalletTxn_ClearNum = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, txnID);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                rsEwalletTxnStatus.put("txnStatus", rs.getString("t_WalletTxn_Paystatus"));
            }else{
                rsEwalletTxnStatus = null;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            rsEwalletTxnStatus.put("SQL-PersonalEwalletTxnStatus-ErrorCode:", String.valueOf(e.getErrorCode()));
            rsEwalletTxnStatus.put("SQL-PersonalEwalletTxnStatus-SQLstat:", String.valueOf(e.getSQLState()));
            rsEwalletTxnStatus.put("SQL-PersonalEwalletTxnStatus-SQLcause:", String.valueOf(e.getCause()));
            rsEwalletTxnStatus.put("SQL", "SQL-PersonalEwalletTxnStatus-ErrorCode");
            conn.close();
            return rsEwalletTxnStatus;
        } finally {
            return rsEwalletTxnStatus;
        }
    }

    public static Map<String, Object> addMobileEwalletTxn(MobileEwalletDashboard mobileEwalletDashboard,String action,String txnCat,String cardAcc,
                                                          BigDecimal txnAmt, String walletTxn_PayerPID,String walletTxn_ReceiverID,String method,
                                                          String paymentID,String paymentStatus,Connection conn) throws SQLException {
        Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
        BigDecimal t_MobileWalletTxn_TopupAmt = null,txnAmtPayerMinus = null, t_MobileWalletTxn_CashoutAmt = null;
        // Switch conn to Open
        if  (conn.isClosed()) {
            DBConnection dao = new DBConnection();
            conn = dao.getConnection();
        }
        String ewalletTxnType = null,payerCardAcc=null,receiverCardAcc=null;
        String uuid = Tool.uuid();
        switch (txnCat) {
            case "58scan-txn-58qr":
                System.out.println("58scan-txn-58qr transit");
                ewalletTxnType = "c2c 钱包转账";
                txnAmtPayerMinus = txnAmt.negate().setScale(2,BigDecimal.ROUND_DOWN);
                break;
            case "PersonalEwalletTopup":
                System.out.println("PersonalEwalletReceive transit");
                t_MobileWalletTxn_TopupAmt = txnAmt.setScale(2,BigDecimal.ROUND_DOWN);
                System.out.print(walletTxn_ReceiverID);
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard();
                }
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard();
                }
                ewalletTxnType = "c2b 充值";
                break;
            case "PersonalEwalletReceive" :
                System.out.println("PersonalEwalletReceive transit");
                t_MobileWalletTxn_TopupAmt = txnAmt.setScale(2,BigDecimal.ROUND_DOWN);
                System.out.print(walletTxn_ReceiverID);
                ewalletTxnType = "c2c 第三方转账";
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard();
                }
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard();
                }
                if(walletTxn_PayerPID == null){
                    walletTxn_PayerPID = paymentID;
                }
                break;
            case "PersonalEwalletCashout":
                System.out.println("PersonalEwalletCashout transit");
                txnAmtPayerMinus = txnAmt.negate().setScale(2,BigDecimal.ROUND_DOWN);
                txnAmt = txnAmtPayerMinus.setScale(2,BigDecimal.ROUND_DOWN);
                t_MobileWalletTxn_CashoutAmt = txnAmtPayerMinus;
                walletTxn_PayerPID = paymentID;
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Creditcard();
                }
                if(mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard() != null){
                    receiverCardAcc = mobileEwalletDashboard.getT_mobilePersonalEwallet_Debitcard();
                }
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(walletTxn_PayerPID);
                ewalletTxnType = "c2c 提现";
                break;
            case "PersonalEwalletShopping":
                System.out.println("PersonalEwalletShopping transit");
                txnAmtPayerMinus = txnAmt.negate();
                txnAmt = txnAmtPayerMinus.setScale(2,BigDecimal.ROUND_DOWN);
                t_MobileWalletTxn_CashoutAmt = txnAmtPayerMinus;
                ewalletTxnType = "c2b 消费";
                break;
        }

        String sql = "insert into t_ewallettxn_info " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = null;
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, uuid); // uuid t_WalletTxn_ID 交易序列号 
            ptmt.setString(2, method); //QRcode t_WalletTxn_QRcode 二维码内容
            ptmt.setString(3, mobileEwalletDashboard.getT_mobilePersonalEwallet_TxnID());// t_WalletTxn_Num 交易单号-清算号
            ptmt.setString(4, mobileEwalletDashboard.getT_mobilePersonalewallet_PaymentVersion()); // t_WalletTxn_Vendor  支付公司名称
            ptmt.setString(5, paymentID); // t_WalletTxn_ClearNum 支付流水号
            ptmt.setString(6, mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID()); // t_WalletTxn_ClearOrg 交易企业对账号
            ptmt.setString(6, mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID()); // t_WalletTxn_ClearOrg 交易发起人身份ID
            ptmt.setString(7, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerName()); // t_WalletTxn_PayerName 付款人姓名
            ptmt.setString(8, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerEwalletID()); // t_WalletTxn_PayerID 付款人主ID
            ptmt.setString(9, walletTxn_PayerPID); // t_WalletTxn_PayerPID 付款人身份证
            ptmt.setString(10, mobileEwalletDashboard.getT_mobilePersonalEwallet_ReceiverName()); // t_WalletTxn_ReceiverName 收款人姓名
            ptmt.setString(11, mobileEwalletDashboard.getT_mobilePersonalEwallet_ReceiverEwalletID()); // t_WalletTxn_ReceiverID 收款人ID
            ptmt.setString(12, mobileEwalletDashboard.getT_mobilePersonalEwallet_ReceiverPID()); // t_WalletTxn_ReceiverPID 收款人身份证
            ptmt.setString(13, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerMobile()); // t_WalletTxn_Mobile 付款人手机号  
            ptmt.setString(14, txnCat); // t_WalletTxn_TxnCat 充值,支付,收款,消费,退款,提现  t_mobilePersonalEwallet_TxnCat
            ptmt.setString(15, "CNY"); // t_WalletTxn_TxnCurrencyType 币种  //中间字段
            ptmt.setTimestamp(16, new java.sql.Timestamp(System.currentTimeMillis())); // t_WalletTxn_TxnDate 交易时间
            ptmt.setString(17, mobileEwalletDashboard.getT_mobilePersonalEwallet_ProdName()); // t_WalletTxn_ProdName 产品名
            ptmt.setInt(18, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayDays()); // t_WalletTxn_PayDays 支付周期 天数
            ptmt.setBigDecimal(19, mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPayCurrentNum()); // t_WalletTxn_CreditPayCurrentNum 当前可预支额度
            ptmt.setBigDecimal(20, txnAmt); // t_WalletTxn_TotTxnAmount 总支付金额 t_mobilePersonalEwallet_TxnAmount
            ptmt.setBigDecimal(21, t_MobileWalletTxn_TopupAmt); // t_WalletTxn_TopupAmt 收款/充值金额
            ptmt.setBigDecimal(22, new BigDecimal("0.00")); // t_WalletTxn_CryptoTxnAmt 数字币支付金额
            ptmt.setBigDecimal(23, new BigDecimal("0.00")); // t_WalletTxn_DebitTxnAmt 借记卡支付金额
            ptmt.setBigDecimal(24, new BigDecimal("0.00")); // t_WalletTxn_CreditTxnAmt 信用卡支付金额
            ptmt.setBigDecimal(25, mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPrepayBalanceNum()); // t_WalletTxn_CreditBalanceNum 剩余信用额度
            ptmt.setBigDecimal(26, mobileEwalletDashboard.getT_mobilePersonalEwallet_CreditPayAmt()); // t_WalletTxn_CreditTxnAmtInit 信用支付金额
            ptmt.setBigDecimal(27, mobileEwalletDashboard.getT_mobilePersonalEwallet_TotalPrepayAmt()); // t_WalletTxn_TotalPrepayAmt 实付金额
            ptmt.setBigDecimal(28, mobileEwalletDashboard.getT_mobilePersonalEwallet_Voucher()); // t_WalletTxn_TotallvorchourAmt 优惠券金额
            ptmt.setInt(29, mobileEwalletDashboard.getT_mobilePersonalEwallet_TotalInterestDays()); // t_WalletTxn_TotalInterestDays 结算周期 T+0 当天 T+1 隔天
            ptmt.setInt(30, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayCounts()); // t_WalletTxn_TxnCounts 交易次数
            ptmt.setBigDecimal(31, mobileEwalletDashboard.getT_mobilePersonalEwallet_Interest()); // t_WalletTxn_InterestBene 贴现差额
            ptmt.setString(32, mobileEwalletDashboard.getT_mobilePersonalEwallet_treasuryID()); // t_WalletTxn_TreasuryID 对私-0；对公-1
            ptmt.setString(33, mobileEwalletDashboard.getT_mobilePersonalEwallet_RefundAcc()); // t_WalletTxn_RefundAccNo 退款账户号
            ptmt.setBigDecimal(34, mobileEwalletDashboard.getT_mobilePersonalEwallet_TotCNYBalance()); // t_WalletTxn_TotBalance 钱包总余额
            ptmt.setBigDecimal(35, new BigDecimal("0.00")); //  t_WalletTxn_BalancePrepayNum 额度变化(小于一个月工资)
            ptmt.setTimestamp(36, (Timestamp) mobileEwalletDashboard.getT_mobilePersonalEwallet_OverdueRepaymentDate()); // t_WalletTxn_OverdueRepaymentDate 退款时间
            ptmt.setString(37, mobileEwalletDashboard.getT_mobilePersonalEwallet_ClearNum()); // t_WalletTxn_PayClear 结算号-结算状态,0-已结清,1-未结算,2-已逾期
            ptmt.setString(38, mobileEwalletDashboard.getT_mobilePersonalEwallet_Overdue()); // t_WalletTxn_Overdue 超期状态
            ptmt.setInt(39, mobileEwalletDashboard.getT_mobilePersonalEwallet_OverdueDays()); // t_WalletTxn_OverdueDays 逾期天数
            ptmt.setBigDecimal(40, mobileEwalletDashboard.getT_mobilePersonalEwallet_RefundAmt()); // t_WalletTxn_RefundAmt 退款金额
            ptmt.setBigDecimal(41, txnAmtPayerMinus); // 付款人钱包扣款数额
            ptmt.setBigDecimal(42, mobileEwalletDashboard.getT_mobilePersonalEwallet_ServiceFee()); // t_WalletTxn_ServiceFee 服务费费
            ptmt.setBigDecimal(43, mobileEwalletDashboard.getT_mobilePersonalEwallet_Poundage()); // t_WalletTxn_Poundage 区间手续费
            ptmt.setBigDecimal(44, mobileEwalletDashboard.getT_mobilePersonalEwallet_TierPoundage()); // t_WalletTxn_TierPoundage 区间手续费
            ptmt.setBigDecimal(45, mobileEwalletDashboard.getT_mobilePersonalEwallet_DiscountAmt()); // t_WalletTxn_DiscountAmt 优惠金额 扣减数
            ptmt.setBigDecimal(46, t_MobileWalletTxn_CashoutAmt); // t_WalletTxn_DiscountBlanceAmt 结算金额 - 提现/消费金额
            ptmt.setBigDecimal(47, new BigDecimal("0.00")); // t_WalletTxn_DiscountAmtCalc 算法公式
            ptmt.setString(48, payerCardAcc); // t_WalletTxn_PayerBankAcc 支付人银行账号
            ptmt.setString(49, receiverCardAcc); // t_WalletTxn_ReceiverBankAcc 收款人账户号
            ptmt.setTimestamp(50, null); // t_WalletTxn_TxnTimeout 订单超时时间
            ptmt.setString(51, mobileEwalletDashboard.getT_mobilePersonalEwallet_Paystatus()); // t_WalletTxn_Paystatus 查询支付状态
            ptmt.setString(52, ""); // t_WalletTxn_SMS 短信验证
            ptmt.setString(53, mobileEwalletDashboard.getT_MobilePersonalewallet_PaymentType()); // t_WalletTxn_PaymentType 支付类型
            ptmt.setString(54, ewalletTxnType);  // t_WalletTxn_type b2b,b2c,c2b,c2c 交易类型
            ptmt.setString(55, mobileEwalletDashboard.getT_mobilePersonalEwallet_VoucherDigi()); // t_WalletTxn_Voucher voucher
            ptmt.setBigDecimal(56, mobileEwalletDashboard.getT_mobilePersonalEwallet_BalanceInterest()); // t_WalletTxn_TotShareProfit 备用字段
            ptmt.setString(57, mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerEwalletAddress()); // t_WalletTxn_PayerEwalletAddress 备用字段
            ptmt.setString(58, mobileEwalletDashboard.getT_mobilePersonalEwallet_ReceiverEwalletAddress()); // t_WalletTxn_ReceiverEwalletAddress 备用字段
            ptmt.setString(59, mobileEwalletDashboard.getT_mobilePersonalEwallet_PaymentTool()); // t_WalletTxn_PaymentToolID 支付工具: 固定填写0403
            ptmt.setString(60, ""); // agreement 协议条款
            ptmt.setString(61, "mobile"); // platform 平台
            ptmt.setString(62, mobileEwalletDashboard.getT_mobilePersonalEwallet_bkp()); // remark 备注
            ptmt.setString(63, mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID()); // creator 创建人
            ptmt.setTimestamp(64, new java.sql.Timestamp(System.currentTimeMillis())); // create_time 创建时间
            ptmt.setString(65, ""); // modifier 更改账号
            ptmt.setTimestamp(66, null); // modify_time 更改时间
            int updateValue = ptmt.executeUpdate();
            if(updateValue>0){
                rsMobileEwalletTxn.put("SQL", "SQL-PersonalEwalletTxnInsertSucc");
            }else{
                rsMobileEwalletTxn.put("SQL", "SQL-PersonalEwalletTxnInsertsfail");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-ErrorCode:", String.valueOf(e.getErrorCode()));
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-SQLstat:", String.valueOf(e.getSQLState()));
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-SQLcause:", String.valueOf(e.getCause()));
            rsMobileEwalletTxn.put("SQL", "SQL-PersonalEwalletTxn-ErrorCode");
            conn.close();
            return rsMobileEwalletTxn;
        } finally {
            //更新付款人扣款先更新 - 非统计数据不做时间脚本
            Map<String, Object> retUpdatePersonalEwallet = new Hashtable<String, Object>();
            if(rsMobileEwalletTxn.get("SQL").equals("SQL-PersonalEwalletTxnInsertSucc")) {
                if (txnCat.equalsIgnoreCase("PersonalEwalletTopup") || txnCat.equalsIgnoreCase("PersonalEwalletReceive")) {
                    retUpdatePersonalEwallet = EwalletController.UpdatePayeePersonalEwalletBalance(txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                    if (!retUpdatePersonalEwallet.isEmpty()) {
                        rsMobileEwalletTxn.put("SQL", "SQL-EWALLETBALANCEUPDATESUCC");
                    } else {
//                    rsMobileEwalletTxn.put("SQL", "SQL-RECEIVEREWALLETTOPUPFAIL");  // checking others
                        rsMobileEwalletTxn.put("SQL", "SQL-RECEIVEREWALLETUPDATEFAILED");
                    }
                }
                if (txnCat.equalsIgnoreCase("58scan-txn-58qr")) {
                    retUpdatePersonalEwallet = EwalletController.UpdatePayerPersonalEwalletBalance(txnAmtPayerMinus, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                    if (!retUpdatePersonalEwallet.isEmpty()) {
                        if (method.equalsIgnoreCase("58scan-txn-58qr")) {
                            retUpdatePersonalEwallet = EwalletController.UpdatePayeePersonalEwalletBalance(txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                            if (!retUpdatePersonalEwallet.isEmpty()) {
                                rsMobileEwalletTxn.put("SQL", "SQL-EWALLETBALANCEUPDATESUCC");
                            } else {
                                rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEUCC");
                            }
                        } else {
                            rsMobileEwalletTxn.put("SQL", "None 58scan-txn-qr, update ewallet complete");
                        }
                    } else {
                        rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEFAIL");
                    }
                }
                if (txnCat.equalsIgnoreCase("PersonalEwalletCashout")) {
                    walletTxn_PayerPID = mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID();
                    retUpdatePersonalEwallet = EwalletController.UpdatePayerPersonalEwalletBalance(txnAmtPayerMinus, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                    if (!retUpdatePersonalEwallet.isEmpty()) {
                            rsMobileEwalletTxn.put("SQL", "SQL-EWALLETBALANCEUPDATESUCC");
                        } else {
                            rsMobileEwalletTxn.put("SQL", "cashout section");
                        }
                        //Coding....
                        //payment call
                        //Personal Treasury control - personal txn vol. trigger
                        //Overall Treasury Acc. check
                        //deposit acc. switch
                    } else {
                        rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEFAIL");
                }
                

                if (txnCat.equalsIgnoreCase("PersonalEwalletShopping")) {
                    walletTxn_PayerPID = mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID();
                    retUpdatePersonalEwallet = EwalletController.UpdatePayerPersonalEwalletBalance(txnAmtPayerMinus, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                    if (!retUpdatePersonalEwallet.isEmpty()) {
                        if (method.equalsIgnoreCase("Shopping")) {
                            retUpdatePersonalEwallet = EwalletController.UpdatePayeePersonalEwalletBalance(txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID, conn);
                            if (!retUpdatePersonalEwallet.isEmpty()) {
                                rsMobileEwalletTxn.put("SQL", "SQL-EWALLETBALANCEUPDATESUCC");
                            } else {
                                rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEUCC");
                            }
                        } else {
                            rsMobileEwalletTxn.put("SQL", "cashout section");
                        }
                    } else {
                        rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEFAIL");
                    }
                }
            }else{
                rsMobileEwalletTxn.put("SQL", "SQL-PAYEREWALLETUPDATEFAIL");
                return rsMobileEwalletTxn;
            }
            
            //insert PersonalEwalletTxnhistory
            if(rsMobileEwalletTxn.get("SQL").equals("SQL-EWALLETBALANCEUPDATESUCC")) {
                String sql1 = "INSERT INTO gognzi.t_ewallettxn_his select * from gognzi.t_ewallettxn_info INNER JOIN gognzi.t_personal_ewallet on " +
                    "gognzi.t_ewallettxn_info.t_WalletTxn_ClearOrg = gognzi.t_personal_ewallet.t_personalewallet_ApplierPID " +
                    "where gognzi.t_ewallettxn_info.t_WalletTxn_ClearNum = ?";
                try {
                    PreparedStatement ptmt = null;
                    ptmt = conn.prepareStatement(sql1);
                    ptmt.setString(1, paymentID); // uuid t_WalletTxn_ID 交易序列号
                    int hisInsert = ptmt.executeUpdate();
                    if(hisInsert>0){
                        rsMobileEwalletTxn.put("SQL", "PersonalEwalletHisTxnInsertSucc");
                    }else{
                        rsMobileEwalletTxn.put("SQL", "SQL-PersonalEwalletHisTxnInsertfail");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    rsMobileEwalletTxn.put("SQL-PersonalEwalletTxnhistory-ErrorCode:", String.valueOf(e.getErrorCode()));
                    rsMobileEwalletTxn.put("SQL-PersonalEwalletTxnhistory-SQLstat:", String.valueOf(e.getSQLState()));
                    rsMobileEwalletTxn.put("SQL-PersonalEwalletTxnhistory-SQLcause:", String.valueOf(e.getCause()));
                    rsMobileEwalletTxn.put("SQL", "SQL-PersonalEwalletTxnhistory-ErrorCode");
                    conn.close();
                    return rsMobileEwalletTxn;
                } finally {
                    return rsMobileEwalletTxn;
                }
            }else{
                rsMobileEwalletTxn.put("SQL", "SQL-EWALLETBALANCEUPDATEFAIL");
                return rsMobileEwalletTxn;
            }
        }
    }
}
