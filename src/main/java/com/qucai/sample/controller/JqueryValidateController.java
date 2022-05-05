package com.qucai.sample.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qucai.sample.service.*;
import com.qucai.sample.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/jqueryValidateController")
public class JqueryValidateController {
    
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private ResourceService resourceService;
    
    @Autowired
    private FinanceProductService financeProductService;
    
    @Autowired
    private OrganizationInfoService organizationInfoService;
    
    @Autowired
    private PersonalInfoService personalInfoService;
    
    @Autowired
    private StaffPrepayApplicationService staffPrepayApplicationService;
    
    @Autowired
    private HistoricalTxnQueryService historicalTxnQueryService;

    @Autowired
    private PersonalMainService personalMainService;
    
    @RequestMapping(value = "checkManagerUserName")
    @ResponseBody
    public String checkManagerUserName(String id, String userName, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        boolean rs = managerService.existManagerUserName(id, userName);
        if(rs){
            return "false";
        }
        return "true";
    }

    @RequestMapping(value = "checkPersonalMainName")
    @ResponseBody
    public String checkPersonalMainName(String t_personal_main_pid, String t_personal_main_id,HttpServletRequest request,String platform,
                                       HttpServletResponse response, Model model) {
        boolean rs = personalMainService.existPersonalMainName(t_personal_main_id,t_personal_main_pid);
        if(rs){
            return "false";
        }
        return "true";
    }
    
    @RequestMapping(value = "checkRoleName")
    @ResponseBody
    public String checkRoleName(String id, String name, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
        boolean rs = roleService.existRoleName(id, name, platform);
        if(rs){
            return "false";
        }
        return "true";
    }
    
    @RequestMapping(value = "checkResourceName")
    @ResponseBody
    public String checkResourceName(String id, String name, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
		boolean rs = resourceService.existResourceName(id, name, platform);
        if(rs){
            return "false";
        }
        return "true";
        
        
    }
    @RequestMapping(value = "checkFinanceProductName")
    @ResponseBody
    public String checkFinanceProductName(String t_FProd_ID, String t_FProd_Name, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
		boolean rs = financeProductService.existFinanceProductName(t_FProd_ID, t_FProd_Name, platform);
        if(rs){
            return "false";
        }
        return "true";
        
        
    }
    @RequestMapping(value = "checkOrganizationInfoName")
    @ResponseBody
    public String checkOrganizationInfotName(String t_O_ID, String t_O_OrgName, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
		boolean rs = organizationInfoService.existOrganizationInfoName(t_O_ID, t_O_OrgName, platform);
        if(rs){
            return "false";
        }
        return "true";
        
    }

    //查看Manager UserName重复
    @RequestMapping(value = "checkUserName")
    @ResponseBody
    public String checkUserName(String userName, Integer platform, HttpServletRequest request,
                                        HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        String id = Tool.uuid();
        if(0 != platform && 1 != platform && 2 != platform) {
            return "false";
        }
        boolean rs = managerService.existManagerUserName(id, userName);
        if(rs){
            return "false";
        }
        return "true";
    }

    //查身份证重复
    @RequestMapping(value = "checkPersonalInfoName")
    @ResponseBody
    public String checkPersonalInfoName(String t_P_id,String t_P_PID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
		boolean rs = personalInfoService.existPersonalInfoName(t_P_id, t_P_PID);
        if(rs){
            return "false";
        }
        return "true";        
    }
    
  //查手机号码重复
    @RequestMapping(value = "checkPersonalInfoModil")
    @ResponseBody
    public String checkPersonalInfoMobil(String t_P_id,String t_P_Mobil, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		if(0 != platform && 1 != platform && 2 != platform) {
    		return "false";
    	}
		boolean rs = personalInfoService.checkPersonalInfoMobil(t_P_id, t_P_Mobil);
        if(rs){
            return "false";
        }
        return "true";
    }
    
    @RequestMapping(value = "checkStaffPrepayApplicationName")
    @ResponseBody
    public String checkStaffPrepayApplicationName(String t_Txn_ID, String t_Txn_PrepayApplierName, Integer platform,HttpServletRequest request,
            HttpServletResponse response, Model model) {
        boolean rs = staffPrepayApplicationService.existStaffPrepayApplicationName(t_Txn_ID, t_Txn_PrepayApplierName,platform);
        if(rs){
            return "false";
        }
        return "true";
    }
    
    @RequestMapping(value = "checkPrepayApplicationListName")
    @ResponseBody
    public String checkPrepayApplicationListName(String t_TxnQuery_PID, String t_TxnQuery_Mobil, Integer platform,HttpServletRequest request,
            HttpServletResponse response, Model model) {
        boolean rs = staffPrepayApplicationService.existStaffPrepayApplicationName(t_TxnQuery_PID, t_TxnQuery_Mobil,platform);
        if(rs){
            return "false";
        }
        return "true";
    }

}
