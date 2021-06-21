package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.mchange.v2.c3p0.impl.NewProxyCallableStatement;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
import com.qucai.sample.util.DBConnection;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    	ewalletTxn.sett_WalletTxn_TxnTimeout(new Date());

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
    public Map<String, Object> addMobileEwalletTxn(BigDecimal txnAmt,String walletTxn_PayerPID,String walletTxn_ReceiverID,String personalMID) throws SQLException {

        Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
        String ewallet = "58ewallet";
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();


        String sql="insert into t_ewallettxn_info " +
            "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ptmt = null;
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,Tool.uuid());
            ptmt.setString(2,"");
            ptmt.setString(3,Tool.PayId());
            ptmt.setString(4,"");
            ptmt.setString(5,"");
            ptmt.setString(6,"");
            ptmt.setString(7,"");
            ptmt.setString(8,"");
            ptmt.setString(9,"");
            ptmt.setString(10,"");
            ptmt.setString(11,"");
            ptmt.setString(12,"");
            ptmt.setString(13,"");
            ptmt.setString(14,"");
            ptmt.setString(15,"");
            ptmt.setString(16,"");
            ptmt.setString(17,"Goldman Fuks");
            ptmt.setString(18,"");
            ptmt.setTimestamp(19, new java.sql.Timestamp(System.currentTimeMillis()));
            ptmt.setString(20,"");
            ptmt.setInt(21,0);
            ptmt.setString(22,"");
            ptmt.setBigDecimal(23,new BigDecimal("0.00"));
            ptmt.setBigDecimal(24,new BigDecimal("0.00"));
            ptmt.setBigDecimal(25,new BigDecimal("0.00"));
            ptmt.setBigDecimal(26,new BigDecimal("0.00"));
            ptmt.setBigDecimal(27,new BigDecimal("0.00"));
            ptmt.setBigDecimal(28,new BigDecimal("0.00"));
            ptmt.setBigDecimal(29,new BigDecimal("0.00"));
            ptmt.setBigDecimal(30,new BigDecimal("0.00"));
            ptmt.setBigDecimal(31,new BigDecimal("0.00"));
            ptmt.setBigDecimal(32,new BigDecimal("0.00"));
            ptmt.setInt(33,0);
            ptmt.setInt(34,0);
            ptmt.setBigDecimal(35,new BigDecimal("0.00"));
            ptmt.setBigDecimal(36,new BigDecimal("0.00"));
            ptmt.setBigDecimal(37,new BigDecimal("0.00"));
            ptmt.setBigDecimal(38,new BigDecimal("0.00"));
            ptmt.setBigDecimal(39,new BigDecimal("0.00"));
            ptmt.setDate(40, (java.sql.Date) null);
            ptmt.setBigDecimal(41,new BigDecimal("0.00"));
            ptmt.setBigDecimal(42,new BigDecimal("0.00"));
            ptmt.setBigDecimal(43,new BigDecimal("0.00"));
            ptmt.setBigDecimal(44,new BigDecimal("0.00"));
            ptmt.setBigDecimal(45,new BigDecimal("0.00"));
            ptmt.setBigDecimal(46,new BigDecimal("0.00"));
            ptmt.setBigDecimal(47,new BigDecimal("0.00"));
            ptmt.setBigDecimal(48,new BigDecimal("0.00"));
            ptmt.setString(49,"");
            ptmt.setTimestamp(50,null);
            ptmt.setString(51,"");
            ptmt.setString(52,"");
            ptmt.setString(53,"");
            ptmt.setString(54,"");  //b2b,b2c,c2b,c2c 交易类型
            ptmt.setString(55,"no"); //voucher
            ptmt.setString(56,""); //备用字段
            ptmt.setString(57,""); //备用字段
            ptmt.setString(58,""); //备用字段
            ptmt.setString(59,""); //备用字段
            ptmt.setString(60,"mobile"); //平台
            ptmt.setString(61,"");
            ptmt.setString(62,walletTxn_PayerPID);
            ptmt.setTimestamp(63,new java.sql.Timestamp(System.currentTimeMillis()));
            ptmt.setString(64,"");
            ptmt.setTimestamp(65, null);
            System.out.println(ptmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-ErrorCode:",String.valueOf(e.getErrorCode()));
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-SQLstat:",String.valueOf(e.getSQLState()));
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn-SQLcause:",String.valueOf(e.getCause()));
            return rsMobileEwalletTxn;
        }finally {
            conn.close();
            rsMobileEwalletTxn.put("SQL-PersonalEwalletTxn","0");
        }

        return rsMobileEwalletTxn;
    }
}
