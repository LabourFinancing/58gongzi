package com.qucai.sample.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.jasper.tagplugins.jstl.Util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.FinanceProduct;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PersonalInfoBatchUpload;
import com.qucai.sample.entity.PersonalInfoBatchUploadStatus;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.FinanceProductService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.PersonalInfoBatchUploadService;
import com.qucai.sample.service.PersonalInfoBatchUploadStatusService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.IdCardUtil;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "/PersonalInfoBatchUploadStatusController")
public class PersonalInfoBatchUploadStatusController {

	
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
    private PersonalInfoBatchUploadService personalInfoBatchUploadService;
	
	@Autowired
    private PersonalInfoBatchUploadStatusService personalInfoBatchUploadStatusService;
	
    private Object OrganizationInfo;
    

  //Controller
	@ModelAttribute
    public PersonalInfoBatchUploadStatus get(@RequestParam(required = false) String t_batch_perslUploadStatus_batchid,String t_P_Company,String t_P_VendorEmployeeName,String t_O_OrgName,HttpServletRequest request,Model model) {
    	PersonalInfoBatchUploadStatus entity = null;
        if (StringUtils.isNotBlank(t_batch_perslUploadStatus_batchid)) {
            entity = personalInfoBatchUploadStatusService.selectByPrimaryKey(t_batch_perslUploadStatus_batchid);//用PersonalInfoService对象属性方法去调用t_FProd_ID并返回
            return entity;
        }
        if (entity == null) {
            entity = new PersonalInfoBatchUploadStatus();
        }
		return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */
    
    @RequestMapping(value = {"personalInfoBatchUploadStatusList"})
    public String personalInfoBatchUploadStatusList(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,
    		String t_batch_perslUploadStatus_batchid,String t_batch_perslUploadEffectStatus,String t_batch_company,String t_batch_vendorCompany,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		String t_batch_persProdName,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("t_batch_perslUploadId", t_batch_perslUploadStatus_batchid); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_perslUploadEffectStatus", t_batch_perslUploadEffectStatus); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_persProdName", t_batch_persProdName); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_company", t_batch_company); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_batch_vendorCompany", t_batch_vendorCompany); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
        if (t_batch_perslUploadStatus_batchid != null | t_batch_perslUploadEffectStatus != null | t_batch_company != null | t_batch_vendorCompany != null | t_batch_persProdName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_batch_perslUploadId", t_batch_perslUploadStatus_batchid);//添加元素
        	paramSearchMap.put("t_batch_perslUploadEffectStatus", t_batch_perslUploadEffectStatus);//添加元素
        	paramSearchMap.put("t_batch_persProdName", t_batch_persProdName);//添加元素
        	paramSearchMap.put("t_batch_vendorCompany", t_batch_vendorCompany);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
        	if (t_O_OrgName.equals("ALL")){
            	paramSearchMap.put("t_batch_company", t_batch_company);//添加元素
        	}
            else {
            	//Flag on Agency or not
            	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                     paramSearchMap.put("t_batch_company", ShiroSessionUtil.getLoginSession().getCompany_name());
            		 paramSearchMap.put("t_batch_vendorCompany", t_batch_vendorCompany);
            	 }else{
                     paramSearchMap.put("t_batch_company", ShiroSessionUtil.getLoginSession().getCompany_name());
            		 paramSearchMap.put("t_batch_vendorCompany", t_O_OrgName);
            	 }
            	//Agency filter
        	}
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<PersonalInfo> page = personalInfoService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
    		if (t_O_OrgName.equals("ALL")) {
    			t_batch_company = null;
    		}else {
                //Flag on Agency or not
             	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
             		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
             		paramMap.put("t_batch_vendorCompany", t_batch_vendorCompany);
             	 }else{
             		paramMap.put("t_batch_company", t_batch_company);
             		paramMap.put("t_batch_vendorCompany", t_O_OrgName);
             	 }
             	//Agency filter
    		}
            PageParam pp = Tool.genPageParam(request);    
            PageInfo<PersonalInfoBatchUploadStatus> page = personalInfoBatchUploadStatusService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
    	return "personalInfoBatchUploadStatus/personalInfoBatchUploadStatusList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "personalInfoBatchUploadStatusSearchList")
    public String personalInfoSearchList(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )  Integer platform,
    		String t_batch_perslUploadId,String t_batch_perslUploadEffectStatus,String t_batch_company,String t_batch_vendorCompany,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		String t_batch_persProdName,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
    	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
    	
        if (t_batch_perslUploadId != null | t_batch_perslUploadEffectStatus != null | t_batch_company != null | t_batch_vendorCompany != null | t_batch_persProdName != null | remark != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_batch_perslUploadId", t_batch_perslUploadId);//添加元素
        	paramSearchMap.put("t_batch_perslUploadEffectStatus", t_batch_perslUploadEffectStatus);//添加元素
        	paramSearchMap.put("t_batch_persProdName", t_batch_persProdName);//添加元素
        	paramSearchMap.put("t_batch_vendorCompany", t_batch_vendorCompany);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
        	if (t_O_OrgName.equals("ALL")){
            	paramSearchMap.put("t_batch_company", t_batch_company);//添加元素
        	}
            else {
            //Flag on Agency or not
           	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                 paramSearchMap.put("t_batch_company", ShiroSessionUtil.getLoginSession().getCompany_name());
        		 paramSearchMap.put("t_batch_vendorCompany", t_batch_vendorCompany);
        	 }else{
                 paramSearchMap.put("t_batch_company", ShiroSessionUtil.getLoginSession().getCompany_name());
        		 paramSearchMap.put("t_batch_vendorCompany", t_O_OrgName);
        	 }
           	//Agency filter
        	}
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<PersonalInfoBatchUploadStatus> page = personalInfoBatchUploadStatusService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
            //Flag on Agency or not
         	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
         		paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
         		paramMap.put("t_batch_vendorCompany", t_batch_vendorCompany);
         	 }else{
         		paramMap.put("t_batch_company", t_batch_company);
         		paramMap.put("t_batch_vendorCompany", t_O_OrgName);
         	 }
          	//Agency filter
             PageParam pp = Tool.genPageParam(request);    
             PageInfo<PersonalInfoBatchUploadStatus> page = personalInfoBatchUploadStatusService.findAllList(paramMap, pp);
             model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "personalInfoBatchUploadStatus/personalInfoBatchUploadStatusList";
    	}else {
    		return "personalInfoBatchUploadStatus/personalInfoBatchUploadStatusList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus,PersonalInfoBatchUpload personalInfoBatchUpload, OrganizationInfo organizationInfo,Integer platform,String operationType,String t_batch_perslUploadStatus_batchid,
    		String t_batch_perslUploadId,String t_batch_perslUploadEffectStatus,String t_batch_company,String t_batch_vendorCompany,String SessionCompanyName,String remark,String t_TreasuryDB_OrgName,
    		String t_batch_persProdName, HttpServletRequest request, HttpServletResponse response, Model model) {

       	  model.addAttribute("platform", platform);
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
  		  paramMap.put("batch_PB_batchID",t_batch_perslUploadStatus_batchid);
          String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
           if (t_P_Company.equals("ALL")){
          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
           }else {
        	 paramMap.put("t_P_Company", t_P_Company);//添加元素
          	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
          	 model.addAttribute("OrganizationInfo", OrganizationInfo);
           }
        	return "personalInfoBatchUploadStatus/personalInfoNewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
      		try {
    			t_batch_perslUploadStatus_batchid = URLDecoder.decode(t_batch_perslUploadStatus_batchid, "UTF-8");
    		} catch (UnsupportedEncodingException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	  personalInfoBatchUploadStatus = personalInfoBatchUploadStatusService.selectByPrimaryKey(t_batch_perslUploadStatus_batchid);
           	  model.addAttribute("personalInfoBatchUploadStatus", personalInfoBatchUploadStatus);
              return "personalInfoBatchUploadStatus/personalInfoBatchUploadEditForm";
          } else if (OperationTypeConstant.LIST.equals(operationType)) {
        		try {
        			t_batch_perslUploadStatus_batchid = URLDecoder.decode(t_batch_perslUploadStatus_batchid, "UTF-8");
        		} catch (UnsupportedEncodingException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
        	  paramMap.put("batch_PB_batchID", t_batch_perslUploadStatus_batchid);
        	  PersonalInfoBatchUpload entity = null;
        	  List<PersonalInfoBatchUpload> PersonalInfoBatchUploadList = personalInfoBatchUploadService.SelectAllBatchList(paramMap);
        	  model.addAttribute("PersonalInfoBatchUpload",PersonalInfoBatchUploadList);
              return "personalInfoBatchUpload/personalInfoBatchUploadList";
          } else if (OperationTypeConstant.CLEAR.equals(operationType)) {
        	  int ClearStatus = personalInfoBatchUploadService.updateBatchPersonalTxnClearing(paramMap);
        	  if( ClearStatus != 0){
          	       return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        	  }else{
        	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        	  }
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "personalInfoBatchUploadStatus/personalInfoVerifyList";	
          } else {
            return "redirect:/personalInfoBatchUploadStatusController/personalInfoBatchUploadStatusList";	
        }
    }
    
    @RequestMapping(value = "deletepersonalInfoBatchUploadStatus")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String deletepersonalInfoBatchUploadStatus(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus,PersonalInfoBatchUpload personalInfoBatchUpload,
    		String t_batch_perslUploadStatus_batchid,Integer platform,
    		HttpServletRequest request,Date modify_time,
            HttpServletResponse response, Model model) {

		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
		String batch_PB_batchID = null;
		try {
			t_batch_perslUploadStatus_batchid = URLDecoder.decode(t_batch_perslUploadStatus_batchid, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paramMap.put("batch_PB_batchID",t_batch_perslUploadStatus_batchid);
		paramMap.put("t_batch_perslUploadStatus_batchid",t_batch_perslUploadStatus_batchid);
    	int deletePerUploadRs = personalInfoBatchUploadService.deleteByRefreshBatchPersonalCredit(paramMap);
    	model.addAttribute("platform", platform);
	  	  if( deletePerUploadRs != 0){
	  		  int deletePerUploadStatus = personalInfoBatchUploadStatusService.deleteByPrimaryKey(t_batch_perslUploadStatus_batchid);
	  		  if (deletePerUploadStatus == 1){
	          	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
	  		  }
		  }else{
		       return JsonBizTool.genJson(ExRetEnum.FAIL);
		  }
        return "redirect:/personalInfoBatchUploadStatusController/personalInfoBatchUploadStatusList?platform="+platform;
    }

    @RequestMapping(value = "batchCreditCtrl")
    @ResponseBody
    public String batchCreditCtrl(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus,PersonalInfoBatchUpload personalInfoBatchUpload,
    		String t_batch_perslUploadStatus_batchid,Integer platform,String operationType,HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	String t_batch_perslUploadStatus_id = null;
    	try {
			t_batch_perslUploadStatus_id = URLDecoder.decode(t_batch_perslUploadStatus_batchid, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        model.addAttribute("platform", platform);
		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
		Map<String, Object> rs = new HashMap<String, Object>();//新建map对象
		
		paramMap.put("batch_PB_batchID",t_batch_perslUploadStatus_id);

    	switch(operationType){
    	case "off" :
    		int offcnt = personalInfoBatchUploadService.updateBatchPersonalStatusClose(paramMap);
            if(offcnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	case "on" :
    		int oncnt = personalInfoBatchUploadService.updateBatchPersonalStatusOpen(paramMap);
            if(oncnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	case "refersh" :
    		int refreshcnt = personalInfoBatchUploadService.updateBatchPersonalInfo(paramMap);
            if(refreshcnt != 0){
    			rs.put("retData","0");
            }else{
      	        return JsonBizTool.genJson(ExRetEnum.FAIL);
            }
            break;
    	}
		return JsonBizTool.genJson(ExRetEnum.SUCCESS,rs);
    }

    
    @RequestMapping(value = "editPersonalInfoBatchUploadStatus")
    @ResponseBody
    public String editPersonalInfoBatchUploadStatus(PersonalInfoBatchUploadStatus personalInfoBatchUploadStatus,Integer platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

    	personalInfoBatchUploadStatus.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	personalInfoBatchUploadStatus.setModify_time(new Date());
    	int rs = personalInfoBatchUploadStatusService.updateByPrimaryKeySelective(personalInfoBatchUploadStatus);
        
        if(rs==1){
        	return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
  	        return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }
}