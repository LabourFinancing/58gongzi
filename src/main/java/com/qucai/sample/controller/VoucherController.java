package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
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
@RequestMapping(value = "/VoucherController")
public class VoucherController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private VoucherService voucherService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService;
    
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
	
    @Autowired
    private FinanceProductService financeProductService; //申明一个对象
    
    private Object OrganizationInfo;

	@ModelAttribute
    public Voucher get(@RequestParam(required = false) String t_Voucher_ID,String t_Voucher_VendorName,String t_Voucher_Name,String t_O_OrgName,HttpServletRequest request,Model model) {
    	Voucher entity = null;
    	String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
    		t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
            String t_P_VendorEmployeeName = null;
    	 }else{
    		t_P_Company = null;
    		String t_P_VendorEmployeeName = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
    	 }
        if (StringUtils.isNotBlank(t_Voucher_ID)) {
               entity = voucherService.selectByPrimaryKey(t_Voucher_ID);//用VoucherService对象属性方法去调用t_FProd_ID并返回
               return entity;
        }
        if (entity == null) {
            entity = new Voucher();
        }
		return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */
    
    @RequestMapping(value = {"voucherList",""})
    public String voucherList(Voucher voucher, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,String t_P_Company,
    		String t_Voucher_Name,String t_Voucher_ProdCat,String t_Voucher_VendorName,String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("t_Voucher_Name", t_Voucher_Name); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_Voucher_ProdCat", t_Voucher_ProdCat); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_Voucher_VendorName", t_Voucher_VendorName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_Company", t_P_Company); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_P_VendorEmployeeName", t_P_VendorEmployeeName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
        if (t_Voucher_Name != null | t_Voucher_ProdCat != null | t_Voucher_VendorName != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_Voucher_Name", t_Voucher_Name);//添加元素
        	paramSearchMap.put("t_Voucher_ProdCat", t_Voucher_ProdCat);//添加元素
        	paramSearchMap.put("t_Voucher_VendorName", t_Voucher_VendorName);//添加元素
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
            PageInfo<Voucher> page = voucherService.findSearchList(pp, paramSearchMap);
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
            PageInfo<Voucher> page = voucherService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
    	return "voucher/voucherList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "voucherSearchList")
    public String voucherSearchList(Voucher voucher,OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_Voucher_Name,
    		String t_Voucher_ProdCat,String t_Voucher_VendorName,String t_P_Company,String t_P_VendorEmployeeName,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
    	if (t_Voucher_Name != null | t_Voucher_ProdCat != null | t_Voucher_VendorName != null | t_P_Company != null | t_P_VendorEmployeeName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_Voucher_Name", t_Voucher_Name);//添加元素
        	paramSearchMap.put("t_Voucher_ProdCat", t_Voucher_ProdCat);//添加元素
        	paramSearchMap.put("t_Voucher_VendorName", t_Voucher_VendorName);//添加元素
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
            PageInfo<Voucher> page = voucherService.findSearchList(pp, paramSearchMap);
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
            PageInfo<Voucher> page = voucherService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "voucher/voucherList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "voucher/voucherList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(Voucher voucher,OrganizationInfo organizationInfo,String t_Voucher_ID, String operationType, Integer platform, 
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
        	return "voucher/voucherNewForm";
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
            voucher = voucherService.selectByPrimaryKey(t_Voucher_ID);
            return "voucher/voucherEditForm";
          } else if (OperationTypeConstant.EDITCREDITBALANCE.equals(operationType)) {
            voucher = voucherService.selectByPrimaryKey(t_Voucher_ID);
            return "voucher/voucherEditCredit";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
        	voucher = voucherService.selectByPrimaryKey(t_Voucher_ID);
            return "voucher/voucherViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "voucher/voucherVerifyList";	
          } else {
            return "redirect:/voucherController/voucherList";	
        }
    }
    
    @RequestMapping(value = "addVoucher")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addVoucher(Voucher voucher, HttpServletRequest request,Integer platform,Date modify_time,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	voucher.setCreator(ShiroSessionUtil.getLoginSession().getId());
    	voucher.setCreate_time(new Date());
    	voucher.setT_Voucher_ID(Tool.uuid());
    	voucher.setT_Voucher_SysupdateDate(new Date());
        voucher.setCreate_time(new Date());
     	
     	if (modify_time == null){
     		voucher.setModify_time(new Date());
     	};
     	voucherService.insertSelective(voucher);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteVoucher")
    public String deleteFinanceProduct(String t_Voucher_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	voucherService.deleteByPrimaryKey(t_Voucher_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/VoucherController/voucherList?platform="+platform;
    }

    @RequestMapping(value = "creditRefreshVoucher")
    public String creditRefreshVoucher(String t_Voucher_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	Voucher voucher = voucherService.selectByPrimaryKey(t_Voucher_ID);
    	String t_Txn_PrepayApplierName = voucher.getT_Voucher_Name();
    	String t_Txn_PrepayApplierPID = voucher.getT_Voucher_ID();
    	String t_Txn_Paystatus = voucher.getT_Voucher_Name();
    	int retdata = staffPrepayApplicationService.deleteTxnPayment(t_Txn_PrepayApplierName, t_Txn_PrepayApplierPID, t_Txn_Paystatus);
    	model.addAttribute("platform", platform);
        return "redirect:/VoucherController/voucherList?platform="+platform;
    }

    
    @RequestMapping(value = "editVoucher")
    @ResponseBody
    public String editVoucher(Voucher voucher, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	voucher.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	voucher.setModify_time(new Date());
    	voucherService.updateByPrimaryKeySelective(voucher);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
}
