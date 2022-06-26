package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PaymentBatchPayUpload;
import com.qucai.sample.entity.PaymentBatchPayUploadStatus;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "/PaymentBatchPayUploadStatusController")
public class PaymentBatchPayUploadStatusController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService;
    
    @Autowired
    private FinanceProductService financeProductService; //申明一个对象
    
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
    
	@Autowired
    private PaymentBatchPayUploadService paymentBatchPayUploadService;
	
	@Autowired
    private PaymentBatchPayUploadStatusService paymentBatchPayUploadStatusService;
	
    private Object OrganizationInfo;
    

  //Controller
	@ModelAttribute
    public PaymentBatchPayUploadStatus get(@RequestParam(required = false) String t_batch_perslUploadStatus_batchid,String t_P_Company,String t_P_VendorEmployeeName,String t_O_OrgName,HttpServletRequest request,Model model) {
    	PaymentBatchPayUploadStatus entity = null;
        if (StringUtils.isNotBlank(t_batch_perslUploadStatus_batchid)) {
            entity = paymentBatchPayUploadStatusService.selectByPrimaryKey(t_batch_perslUploadStatus_batchid);//用PersonalInfoService对象属性方法去调用t_FProd_ID并返回
            return entity;
        }
        if (entity == null) {
            entity = new PaymentBatchPayUploadStatus();
        }
		return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */
    
    @RequestMapping(value = {"paymentBatchPayUploadStatusList"})
    public String paymentBatchPayUploadStatusList(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,
    		String t_batch_paymenetUploadStatus_batchid,String t_batch_paymentUploadEffectStatus,String t_batch_paymentcompany,String t_batch_paymentvendorCompany,String SessionCompanyName,String t_batch_paymentremark,String t_TreasuryDB_OrgName,
    		String t_batch_paymentProdName,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("t_batch_paymentUploadId", t_batch_paymenetUploadStatus_batchid); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_paymentUploadEffectStatus", t_batch_paymentUploadEffectStatus); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_paymentProdName", t_batch_paymentProdName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_paymentcompany", t_batch_paymentcompany); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_paymentvendorCompany", t_batch_paymentvendorCompany); //key从数据库查询并返,并索引对应JSP
    	model.addAttribute("t_batch_paymentremark", t_batch_paymentremark); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
        if (t_batch_paymenetUploadStatus_batchid != null || t_batch_paymentUploadEffectStatus != null || t_batch_paymentcompany != null || t_batch_paymentvendorCompany != null || t_batch_paymentProdName != null || t_batch_paymentremark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_batch_perslUploadId", t_batch_paymenetUploadStatus_batchid);//添加元素
        	paramSearchMap.put("t_batch_perslUploadEffectStatus", t_batch_paymentUploadEffectStatus);//添加元素
        	paramSearchMap.put("t_batch_paymentProdName", t_batch_paymentProdName);//添加元素
        	paramSearchMap.put("t_batch_paymentvendorCompany", t_batch_paymentvendorCompany);//添加元素
        	paramSearchMap.put("t_batch_paymentremark", t_batch_paymentremark);//添加元素
        	if (t_O_OrgName.equals("ALL")){
            	paramSearchMap.put("t_batch_paymentcompany", "ALL");//添加元素
				paramSearchMap.put("t_batch_paymentvendorCompany", "ALL");
        	}else {
            	//Flag on Agency or not
            	 if (AgencyOrgnization.getT_O_listOrg().equals("on")){
                     paramSearchMap.put("t_batch_paymentcompany", null);
            		 paramSearchMap.put("t_batch_paymentvendorCompany", ShiroSessionUtil.getLoginSession().getCompany_name());
            	 }else{
                     paramSearchMap.put("t_batch_paymentcompany", ShiroSessionUtil.getLoginSession().getCompany_name());
            		 paramSearchMap.put("t_batch_paymentvendorCompany", null);
            	 }
            	//Agency filter
        	}
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<PersonalInfo> page = personalInfoService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
    		if (t_O_OrgName.equals("ALL")) {
                paramMap.put("t_batch_paymentcompany", "ALL");
                paramMap.put("t_batch_paymentvendorCompany", "ALL");
    		}else {
                //Flag on Agency or not
             	 if (AgencyOrgnization.getT_O_listOrg().equals("on")){
             		paramMap.put("t_batch_paymentcompany", null);
             		paramMap.put("t_batch_paymentvendorCompany", ShiroSessionUtil.getLoginSession().getCompany_name());
             	 }else{
             		paramMap.put("t_batch_paymentcompany", ShiroSessionUtil.getLoginSession().getCompany_name());
             		paramMap.put("t_batch_paymentvendorCompany", null);
             	 }
             	//Agency filter
    		}
            PageParam pp = Tool.genPageParam(request);    
            PageInfo<PaymentBatchPayUploadStatus> page = paymentBatchPayUploadStatusService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
    	return "paymentBatchPayUploadStatus/paymentBatchPayUploadStatusList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "paymentBatchPayUploadStatusSearchList")
    public String personalInfoSearchList(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,
    		String t_batch_paymentUploadId,String t_batch_paymentUploadEffectStatus,String t_batch_paymentcompany,String t_batch_paymentvendorCompany,String SessionCompanyName,String t_batch_paymentremark,String t_TreasuryDB_OrgName,
    		String t_batch_paymentProdName,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

        if (t_batch_paymentUploadId != null || t_batch_paymentUploadEffectStatus != null || t_batch_paymentcompany != null || t_batch_paymentvendorCompany != null || t_batch_paymentProdName != null || t_batch_paymentremark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_batch_paymentUploadId", t_batch_paymentUploadId);//添加元素
        	paramSearchMap.put("t_batch_paymentUploadEffectStatus", t_batch_paymentUploadEffectStatus);//添加元素
        	paramSearchMap.put("t_batch_paymentProdName", t_batch_paymentProdName);//添加元素
        	paramSearchMap.put("t_batch_paymentvendorCompany", t_batch_paymentvendorCompany);//添加元素
        	paramSearchMap.put("t_batch_paymentremark", t_batch_paymentremark);//添加元素
			if (t_O_OrgName.equals("ALL")){
				paramSearchMap.put("t_batch_paymentcompany", "ALL");//添加元素
				paramSearchMap.put("t_batch_paymentvendorCompany", "ALL");
			}else {
				//Flag on Agency or not
				if (AgencyOrgnization.getT_O_listOrg().equals("on")){
					paramSearchMap.put("t_batch_paymentcompany", null);
					paramSearchMap.put("t_batch_paymentvendorCompany", ShiroSessionUtil.getLoginSession().getCompany_name());
				}else{
					paramSearchMap.put("t_batch_paymentcompany", ShiroSessionUtil.getLoginSession().getCompany_name());
					paramSearchMap.put("t_batch_paymentvendorCompany", null);
				}
				//Agency filter
			}
            PageParam pp = Tool.genPageParam(request);
            PageInfo<PaymentBatchPayUploadStatus> page = paymentBatchPayUploadStatusService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
            //Flag on Agency or not
			if (t_O_OrgName.equals("ALL")) {
				paramMap.put("t_batch_paymentcompany", "ALL");
				paramMap.put("t_batch_paymentvendorCompany", "ALL");
			}else {
				//Flag on Agency or not
				if (AgencyOrgnization.getT_O_listOrg().equals("on")){
					paramMap.put("t_batch_paymentcompany", null);
					paramMap.put("t_batch_paymentvendorCompany", ShiroSessionUtil.getLoginSession().getCompany_name());
				}else{
					paramMap.put("t_batch_paymentcompany", ShiroSessionUtil.getLoginSession().getCompany_name());
					paramMap.put("t_batch_paymentvendorCompany", null);
				}
				//Agency filter
			}
          	//Agency filter
             PageParam pp = Tool.genPageParam(request);
             PageInfo<PaymentBatchPayUploadStatus> page = paymentBatchPayUploadStatusService.findAllList(paramMap, pp);
             model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "paymentBatchPayUploadStatus/paymentBatchPayUploadStatusList";
    	}else {
    		return "paymentBatchPayUploadStatus/paymentBatchPayUploadStatusList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus,PaymentBatchPayUpload paymentBatchPayUpload, OrganizationInfo organizationInfo,Integer platform,String operationType,String t_batch_paymenetUploadStatus_batchid,
    		String t_batch_paymentUploadId,String t_batch_paymentUploadEffectStatus,String t_batch_paymentcompany,String t_batch_paymentvendorCompany,String SessionCompanyName,String t_batch_paymentremark,String t_TreasuryDB_OrgName,
    		String t_batch_persProdName, HttpServletRequest request, HttpServletResponse response, Model model) {

       	  model.addAttribute("platform", platform);
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
  		  paramMap.put("t_batch_paymenetUploadStatus_batchid",t_batch_paymenetUploadStatus_batchid);
          String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
           if (t_P_Company.equals("ALL")){
          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
           }else {
        	 paramMap.put("t_O_VendorOrgName", t_P_Company);//添加元素
          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
           }
        	return "paymentBatchPayUploadStatus/personalInfoNewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
      		try {
				t_batch_paymenetUploadStatus_batchid = URLDecoder.decode(t_batch_paymenetUploadStatus_batchid, "UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	  paymentBatchPayUploadStatus = paymentBatchPayUploadStatusService.selectByPrimaryKey(t_batch_paymenetUploadStatus_batchid);
           	  model.addAttribute("paymentBatchPayUploadStatus", paymentBatchPayUploadStatus);
              return "paymentBatchPayUploadStatus/paymentBatchPayUploadEditForm";
          } else if (OperationTypeConstant.LIST.equals(operationType)) {
        		try {
					t_batch_paymenetUploadStatus_batchid = URLDecoder.decode(t_batch_paymenetUploadStatus_batchid, "UTF-8");
        		} catch (UnsupportedEncodingException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	  paramMap.put("t_batch_paymenetUploadStatus_batchid", t_batch_paymenetUploadStatus_batchid);
        	  PaymentBatchPayUpload entity = null;
        	  List<PaymentBatchPayUpload> PaymentBatchPayUploadList = paymentBatchPayUploadService.SelectAllBatchList(paramMap);
        	  model.addAttribute("PaymentBatchPayUpload",PaymentBatchPayUploadList);
              return "paymentBatchPayUpload/paymentBatchPayUploadList";
          } else if (OperationTypeConstant.CLEAR.equals(operationType)) {
        	  int ClearStatus = paymentBatchPayUploadService.updateBatchPersonalTxnClearing(paramMap);
        	  if( ClearStatus != 0){
          	       return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        	  }else{
        	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        	  }
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "paymentBatchPayUploadStatus/personalInfoVerifyList";	
          } else {
            return "redirect:/paymentBatchPayUploadStatusController/paymentBatchPayUploadStatusList";	
        }
    }
    
    @RequestMapping(value = "deletepaymentBatchPayUploadStatus")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String deletepaymentBatchPayUploadStatus(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus,PaymentBatchPayUpload paymentBatchPayUpload,
    		String t_batch_paymenetUploadStatus_batchid,Integer platform,
    		HttpServletRequest request,Date modify_time,
            HttpServletResponse response, Model model) {

		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
		String batch_PB_batchID = null;
		try {
			t_batch_paymenetUploadStatus_batchid = URLDecoder.decode(t_batch_paymenetUploadStatus_batchid, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paramMap.put("batch_PB_batchID",t_batch_paymenetUploadStatus_batchid);
		paramMap.put("t_batch_perslUploadStatus_batchid",t_batch_paymenetUploadStatus_batchid);
    	int deletePerUploadRs = paymentBatchPayUploadService.deleteByRefreshBatchPersonalCredit(paramMap);
    	model.addAttribute("platform", platform);
	  	  if( deletePerUploadRs != 0){
	  		  int deletePerUploadStatus = paymentBatchPayUploadStatusService.deleteByPrimaryKey(t_batch_paymenetUploadStatus_batchid);
	  		  if (deletePerUploadStatus == 1){
	          	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
	  		  }
		  }else{
		       return JsonBizTool.genJson(ExRetEnum.FAIL);
		  }
        return "redirect:/paymentBatchPayUploadStatusController/paymentBatchPayUploadStatusList?platform="+platform;
    }

    @RequestMapping(value = "batchCreditCtrl")
    @ResponseBody
    public String batchCreditCtrl(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus,PaymentBatchPayUpload paymentBatchPayUpload,
    		String t_batch_perslUploadStatus_batchid,Integer platform,String operationType,HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	String t_batch_paymentUploadStatus_id = null;
    	try {
			t_batch_paymentUploadStatus_id = URLDecoder.decode(t_batch_perslUploadStatus_batchid, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("platform", platform);
		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
		Map<String, Object> rs = new HashMap<String, Object>();//新建map对象
		
		paramMap.put("batch_PB_batchID",t_batch_paymentUploadStatus_id);

    	switch(operationType){
    	case "off" :
    		int offcnt = paymentBatchPayUploadService.updateBatchPersonalStatusClose(paramMap);
            if(offcnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	case "on" :
    		int oncnt = paymentBatchPayUploadService.updateBatchPersonalStatusOpen(paramMap);
            if(oncnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	case "refersh" :
    		int refreshcnt = paymentBatchPayUploadService.updateBatchPersonalInfo(paramMap);
            if(refreshcnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	}
		return JsonBizTool.genJson(ExRetEnum.SUCCESS,rs);
    }

    
    @RequestMapping(value = "editPaymentBatchPayUploadStatus")
    @ResponseBody
    public String editPaymentBatchPayUploadStatus(PaymentBatchPayUploadStatus paymentBatchPayUploadStatus,Integer platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

    	paymentBatchPayUploadStatus.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	paymentBatchPayUploadStatus.setModify_time(new Date());
    	int rs = paymentBatchPayUploadStatusService.updateByPrimaryKeySelective(paymentBatchPayUploadStatus);
        
        if(rs==1){
        	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
  	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }
}