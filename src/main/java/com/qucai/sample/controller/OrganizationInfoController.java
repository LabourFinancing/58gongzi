package com.qucai.sample.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
/**
 * @author Spear Yao .
 */

@Controller
@RequestMapping(value = "/OrganizationInfoController")
public class OrganizationInfoController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象
    
    @Autowired
    private ManagerService managerService; //申明一个对象

    @ModelAttribute
    public OrganizationInfo get(@RequestParam(required = false) String t_O_ID) {
    	OrganizationInfo entity = null;
        if (StringUtils.isNotBlank(t_O_ID)) {
            entity = organizationInfoService.selectByPrimaryKey(t_O_ID);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new OrganizationInfo();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 , 2017/11/14 回家改一下代码 新建产品详细页.
     */
    
    @RequestMapping(value = {"organizationInfoList",""})
    public String organizationInfoList(OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  String platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_M_Company = ShiroSessionUtil.getLoginSession().getCompany_name();

    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);

        if(ShiroSessionUtil.getLoginSession().getPlatform().equals("4")){
            paramMap.put("t_M_Company",null);
            paramMap.put("t_O_VendorOrgName",t_M_Company);
        }
        else if(ShiroSessionUtil.getLoginSession().getPlatform().equals("5")){
            paramMap.put("t_M_Company",t_M_Company);
            paramMap.put("t_O_VendorOrgName",null);
        }
        PageInfo<OrganizationInfo> page = organizationInfoService.findAllList(paramMap, pp);
        model.addAttribute("page", page);

    	return "organizationInfo/organizationInfoList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "organizationInfoSearchList")
    public String organizationInfoSearchList(OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_O_CertificationCode,String t_O_OrgName,
    		String t_O_Category,String t_O_OrgStatus,String remark,Date create_time,HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_M_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	
    	if (t_O_OrgName != "" | t_O_CertificationCode !=  "" | t_O_Category != "" | t_O_OrgStatus != "" | remark != "" | create_time != null ) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_O_OrgName", t_O_OrgName);//添加元素
        	paramSearchMap.put("t_O_Category", t_O_Category);//添加元素
        	paramSearchMap.put("t_O_OrgStatus", t_O_OrgStatus);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
        	paramSearchMap.put("create_time", create_time);//添加元素
            PageParam pp = Tool.genPageParam(request);
            if(ShiroSessionUtil.getLoginSession().getPlatform().equals("4")){
                paramSearchMap.put("t_O_VendorOrgName",t_M_Company);
            }
            PageInfo<OrganizationInfo> page = organizationInfoService.findSearchList(pp, paramSearchMap); //  << new-function
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
            PageParam pp = Tool.genPageParam(request);
            Map<String, Object> paramMap  = new HashMap<String, Object>();
            paramMap.put("t_M_Company",t_M_Company);
            if(ShiroSessionUtil.getLoginSession().getPlatform().equals("4")){
                paramMap.put("t_M_Company",null);
                paramMap.put("t_O_VendorOrgName",t_M_Company);
            }
            else if(ShiroSessionUtil.getLoginSession().getPlatform().equals("5")){
                paramMap.put("t_M_Company",t_M_Company);
                paramMap.put("t_O_VendorOrgName",null);
            }
            PageInfo<OrganizationInfo> page = organizationInfoService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "organizationInfo/organizationInfoList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "organizationInfo/organizationInfoList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(String t_O_ID, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	  model.addAttribute("platform", platform);
//    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
//        List<FinanceProduct> financeProductList = financeProductService.findAllList(paramMap); //上数据库查询的list树的结果,查询结果赋值与parentfinanceProductList
//        model.addAttribute("financeProductList", financeProductList); //返回到页面上
        
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面
        	return "organizationInfo/organizationInfoNewForm";
         } else if (OperationTypeConstant.EDIT.equals(operationType))
            {
            OrganizationInfo organizationInfo = organizationInfoService.selectByPrimaryKey(t_O_ID);
            return "organizationInfo/organizationInfoEditForm";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "organizationInfo/organizationInfoViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "organizationInfo/organizationInfoVerifyList";
          } else {
            return "redirect:/organizationInfoController/organizationInfoList";
        }
    }
    
    @RequestMapping(value = "addOrganizationInfo")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addOrganizationInfo(OrganizationInfo organizationInfo, HttpServletRequest request,Integer platform,Date Modify_time,
            HttpServletResponse response, Model model) {

        String t_O_OrgName =  ShiroSessionUtil.getLoginSession().getCompany_name();
        organizationInfo.setT_O_OrgPending(t_O_OrgName);
    	model.addAttribute("platform", platform);
    	organizationInfo.setCreator(ShiroSessionUtil.getLoginSession().getId());
    	organizationInfo.setCreate_time(new Date());
    	organizationInfo.setT_O_ID(Tool.uuid());
    	organizationInfo.setT_O_SysUpdateDate(new Date());
    	if (Modify_time == null){
    		organizationInfo.setModify_time(new Date()); 	
    	}
    	organizationInfoService.insertSelective(organizationInfo);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteOrganizationInfo")
    public String deleteOrganizationInfo(String t_O_ID,String t_O_OrgName, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	organizationInfoService.deleteByPrimaryKey(t_O_ID,t_O_OrgName);
    	model.addAttribute("platform", platform);
        return "redirect:/OrganizationInfoController/organizationInfoList?platform="+platform;
    }

    
    @RequestMapping(value = "editOrganizationInfo")
    @ResponseBody
    public String editOrganizationInfo(OrganizationInfo organizationInfo, HttpServletRequest request,String t_O_OrgStatus,Integer OrgStaffsStatus,
            HttpServletResponse response, Model model) {
    	organizationInfo.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	organizationInfo.setModify_time(new Date());
    	t_O_OrgStatus = organizationInfo.getT_O_OrgStatus();
    	String t_O_OrgName = organizationInfo.getT_O_OrgName();
    	if (t_O_OrgStatus.equals("off") ) {
    	    managerService.updateAllCompanyStaffsOff(t_O_OrgName);
    	}else if(t_O_OrgStatus.equals("on")) {
    	    managerService.updateAllCompanyStaffsOn(t_O_OrgName);
    	}

    	organizationInfoService.updateByPrimaryKeySelective(organizationInfo);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }


    @RequestMapping(value = "agentfilter")
    @ResponseBody
    public String agentfilter(HttpServletRequest request,HttpServletResponse response, Model model,String t_O_VendorOrgName) {
        model.addAttribute("t_P_VendorEmployeeName",t_O_VendorOrgName);
        Map<String, Object> paramMap =  new HashMap<String, Object>();
        Map<String, Object> rs = new HashMap<String, Object>();
        paramMap.put("t_O_VendorOrgName",t_O_VendorOrgName);
        List<OrganizationInfo> OrganizationInfoList = organizationInfoService.findOrgName(paramMap);
        OrganizationInfo organizationInfo = new OrganizationInfo();
        if (OrganizationInfoList.size() == 0 ) {
            organizationInfo.setT_O_OrgName(t_O_VendorOrgName);
            OrganizationInfoList.add(organizationInfo);
        }
        rs.put("OrganizationInfoList", OrganizationInfoList);
        rs.put("ret", 0);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
    }
}
