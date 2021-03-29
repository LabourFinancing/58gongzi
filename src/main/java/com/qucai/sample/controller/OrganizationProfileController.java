package com.qucai.sample.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qucai.sample.entity.PasswordReset;
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
import com.qucai.sample.entity.OrganizationProfile;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationProfileService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@Controller
@RequestMapping(value = "/OrganizationProfileController")
public class OrganizationProfileController {


	// 必须把new financeProduct的列进行全面修改, 新建financeProductService

    @Autowired
    private OrganizationProfileService organizationProfileService; //申明一个对象

    @Autowired
    private ManagerService managerService; //申明一个对象

    @ModelAttribute
    public OrganizationProfile get(@RequestParam(required = false) String t_Profile_ID) {
    	OrganizationProfile entity = null;
    	String t_Profile_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        if (StringUtils.isNotBlank(t_Profile_OrgName)) {
            entity = organizationProfileService.selectAgencyName(t_Profile_OrgName);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new OrganizationProfile();
        }
        return entity;
    }

    @RequestMapping(value = {"info"})
    public String showOrganizationProfile(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  Integer platform,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);
        
        OrganizationProfile organizationProfileDetail = organizationProfileService.selectAgencyName(ShiroSessionUtil.getLoginSession().getCompany_name());
        model.addAttribute("organizationProfileDetail", organizationProfileDetail);

        return "organizationProfile/usetInformation";
    }

    @RequestMapping(value = {"Editinfo"})
    @ResponseBody
    public String EditOrganizationProfile(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  Integer platform,
                                       String t_Profile_StatusRptRetAddress,String t_Profile_IPaddress,String t_Profile_Mobile,String t_Profile_Email,String t_Profile_Address,String t_Profile_Contact,
                                       String t_Profile_PostRet,String t_Profile_CurrentAddress,
                                          HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_Profile_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        organizationProfile.setT_Profile_StatusRptRetAddress(t_Profile_StatusRptRetAddress);
        organizationProfile.setT_Profile_IPaddress(t_Profile_IPaddress);
        organizationProfile.setT_Profile_Address(t_Profile_Address);
        organizationProfile.setT_Profile_Contact(t_Profile_Contact);
        organizationProfile.setT_Profile_Mobile(t_Profile_Mobile);
        organizationProfile.setT_Profile_Email(t_Profile_Email);
        organizationProfile.setT_Profile_PostRet(t_Profile_PostRet);
        
        model.addAttribute("organizationProfile",organizationProfile);
        model.addAttribute("t_Profile_StatusRptRetAddress",t_Profile_StatusRptRetAddress);
        model.addAttribute("t_Profile_IPaddress",t_Profile_IPaddress);
        model.addAttribute("t_Profile_Mobile",t_Profile_Mobile);
        model.addAttribute("t_Profile_Email",t_Profile_Email);
        model.addAttribute("t_Profile_Address",t_Profile_Address);
        model.addAttribute("t_Profile_Contact",t_Profile_Contact);
        model.addAttribute("t_Profile_PostRet",t_Profile_PostRet);
        model.addAttribute("t_Profile_CurrentAddress",t_Profile_CurrentAddress);
        int organizationProfileDetail = organizationProfileService.updateByPrimaryKeySelective(organizationProfile);
        model.addAttribute("organizationProfileDetail", organizationProfileDetail);
        if (organizationProfileDetail == 1) {
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
            return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }
}
