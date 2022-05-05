package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.RoleGrant;
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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.RoleGrant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Controller
@RequestMapping(value = "/registController")
public class RegistController {
    
    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象


    @RequestMapping(value = "form")
    @ResponseBody
    public String form(String type,String mobile,String username,String password,String firmcat,String firmName, String firmSelName,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {

    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        String id = Tool.uuid();
        Integer InsertStat = null;
        StringBuffer FirmName = new StringBuffer();
        String UserName = String.valueOf(FirmName.append("corp").append(username));
        Manager manager = new Manager();
        if (OperationTypeConstant.REGIST.equals(type)) {
            switch (firmcat) {
                case "corp" :
                    manager.setRealName(username.trim()); // 输入的用户名
                    manager.setUserName(UserName.trim()); // 重定义后的登陆用户名
                    manager.setUserName(mobile.trim());
                    manager.setId(id);
                    manager.setPassword(password.trim());
                    manager.setRemark("企业用户，初始设置");
                    manager.setCompany_name("企业用户初始设置");
                    manager.setCreateTime(new Date());
                    manager.setId(Tool.uuid());
                    InsertStat = managerService.insertSelective(manager);
                    break;
                case "agent" : 
                    manager.setRealName(username.trim()); // 输入的用户名
                    manager.setUserName(UserName.trim()); // 重定义后的登陆用户名
                    manager.setUserName(mobile.trim());
                    manager.setId(id);
                    manager.setPassword(password.trim());
                    manager.setRemark("企业用户，初始设置");
                    manager.setCompany_name(firmName.trim());
                    manager.setCreateTime(new Date());
                    manager.setId(Tool.uuid());
                    InsertStat = managerService.insertSelective(manager);
                    break;
                case "registered" :
                    
                    break;
                default: 
            }
        	if( t_P_Company.equals("ALL")){
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
        	else{
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
            return "redirect:/registController/registList";
        }else {
            // return to login 
            return "redirect:/registController/registList";
        } 
    }

    @RequestMapping(value = "userNameDupChk")
    @ResponseBody
    public String userNameDupChk(String userName, Integer platform, HttpServletRequest request,
                                              HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        String id = null;
        Map<String, Object> params = new HashMap<String, Object>();
        String ret = null;
        if(0 != platform && 1 != platform && 2 != platform) {
            params.put("ret","error_transfer");
            return "false";
        }else {
            boolean rs = managerService.existManagerUserName(id, userName.replace(",","").trim());
            if(rs){
                ret = "1";
                return "false";
            }else{
                ret  = "0";
                return "true";
            }
        }
    }
    
    @RequestMapping(value = "firmNameDupChk")
    @ResponseBody
    public String firmNameDupChk(String firmName, HttpServletRequest request,
                                 HttpServletResponse response, Model model) throws UnsupportedEncodingException {String id = null;Integer platform = 1;
        Map<String, Object> params = new HashMap<String, Object>();
        String ret = null;
        if(0 != platform && 1 != platform && 2 != platform) {
            params.put("ret","error_transfer");
            return "false";
        }else {
            String t_O_ID = Tool.uuid();
            String  t_Profile_OrgName = firmName.replace(",","").trim();
            platform = 1; //暂时无用
            boolean rs = organizationInfoService.existOrganizationInfoName(id,t_Profile_OrgName,platform);
            if(rs){
                ret = "1";
                return "false";
            }else{
                String orgInfoRegSucc = "success";
                ret  = "0";
                return "true";
            }
        }
    }
    
    @RequestMapping(value = "isChinaMobileLegal")
    @ResponseBody
    public static String isChinaMobileLegal(String mobile,Integer platform, HttpServletRequest request,
                                            HttpServletResponse response, Model model)
        throws PatternSyntaxException { 
        String regExp = "^((13[0-9])|(15[^4])|(16[5-7])|(17[0-8])|(18[0-9])|(19[0-9])|(14[5-8]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile.replace(",","").trim());
        System.out.println(m.matches());
        if (!m.matches()){
            return "false";
        }else {
            return "true";
        }
    }

    @RequestMapping(value = "firmlistSelect")
    @ResponseBody
    public String firmSelect(String firmName,String Type, HttpServletRequest request,
                            HttpServletResponse response, Model model) {
        OrganizationInfo organizationInfo = new OrganizationInfo();
        String batch_PB_company = null;
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("t_P_VendorEmployeeName",batch_PB_company);
        List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllNameOnly(paramMap);
        rs.put("OrganizationInfo",OrganizationInfo);    
        return JsonBizTool.genJson(rs);
    }

    @RequestMapping(value = "addRegist")
    @ResponseBody
    public String addRegist(Manager manager, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        manager.setCreateTime(new Date());
        manager.setId(Tool.uuid());
        managerService.insertSelective(manager);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "SMSRegist")
    @ResponseBody
    public String SMSRegist(String type,String userName,String mobile,String id, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        boolean userNamers = false,userMobile = true;
        String SMScodeRec = null;
        userNamers = managerService.existManagerUserName(userName, id);
        if(userNamers){
            String retmsg = "existusername";
            SMScodeRec = "1";
        }
        userMobile = Tool.isChinaPhoneLegal(mobile);
        if(!userMobile){
            SMScodeRec = "2";

        }
        //企业注册短信发送
        if(!userNamers && userMobile){
//		String SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	// test using		
//		SMScodeRec =  SMScodeInit.substring(0,SMScodeInit.indexOf(".")); // test using
            SMScodeRec = HttpJsonExample.SMSFirmRegCode(type,mobile).toString();
            if (SMScodeRec.length() == 6){
                return SMScodeRec;
            }else{
                return SMScodeRec;
            }
        }
        return SMScodeRec;
    }


    @RequestMapping(value = "SMSVerify")
    @ResponseBody
    public String SMSverify(String smscode, HttpServletRequest request,
        HttpServletResponse response, Model model) {
//        manager.setCreateTime(new Date());
//        manager.setId(Tool.uuid());
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "grantRole")
    public String grantRole(String registId, String roleIds,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        managerService.grantRole(registId, roleIds);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

}
