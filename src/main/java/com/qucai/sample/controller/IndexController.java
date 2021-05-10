package com.qucai.sample.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.util.ShiroSessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qucai.sample.converter.HttpJsonPersonalTest;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.security.CaptchaUsernamePasswordToken;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.*;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.util.JsonBizTool;


@Controller
public class IndexController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("index")
    public String index(@RequestParam(required = false) String gid,String from, String form,String method, String phone,String host,String SMSsendcode,
                        byte[] SMSstr,HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        if((from!=null&&from.equals("wechat")) || form!=null&&form.equals("wechat")){
            System.out.print("from wechat");
            System.out.print(phone);
            String userName;
            String password;
            Manager manager;
            if(phone!=null) {
                manager = managerService.selectByMobil(phone);
            } else {
                manager = managerService.selectByPrimaryKey("749bf3de57e94bd5957ba32835db2a1c");
            }
            
            CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
            userName = manager.getUserName();
            password = manager.getPassword();
            Map<String, Object> rs = new HashMap<String, Object>();
            token.setPassword(password.toCharArray());
            token.setUsername(userName);
            token.setRememberMe(true);
            try {
                Subject subject = SecurityUtils.getSubject();
                subject.login(token);
            } catch (AuthenticationException e) {
                return JsonBizTool.genJson(ExRetEnum.LOGIN_ACCOUNT_PASSWORD_ERROR);
            }
            if(userName != null) {
                return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
            }
        } 
        if(gid != null){
            System.out.print("Fujian Province Platform:");
            System.out.print(gid);
            try {
                String Token = HttpJsonPersonalTest.main(); //Call FuJian wechat API
                if (Token.equals("1")){
                    return "login";
                }else{
                    String TokenKey = Token;
                    //generate new url to get user info
                    String userMobilurl = null;
                    StringBuffer TokenUrl =  new StringBuffer();
                    userMobilurl = String.valueOf(TokenUrl.append("https://www.cspower.net/bigcenter/index.php?s=/addon/Bigc/SyAPI/GetInfoByGID/gid/").append(gid).append("/token/").append(TokenKey)).replaceAll("null", "").trim();
                    String id = (String) SecurityUtils.getSubject().getPrincipal();
                    String userName;
                    String password;
                    if (id != null){
                        Manager manager = managerService.selectByPrimaryKey(id);
                        userName = manager.getUserName();
                        password = manager.getPassword();
                    }else{
                        String mobile = HttpJsonPersonalTest.UserMobil(userMobilurl, gid, TokenKey);
                        Manager manager = managerService.selectByMobil(mobile);   // get user mobile
                        userName = manager.getUserName();
                        password = manager.getPassword();
                    }
                    if (userName == null) {
                        return "login";
                    } else if (userName.contains("Admin") || userName.contains("ADMIN") || userName.contains("admin")) {
                        return "mainFrame";
                    } else { //Turn to login page when userid not existing
                        String type = "login";
                        String API = "Fujian";
                        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
                        Map<String, Object> rs = new HashMap<String, Object>();
                        token.setPassword(password.toCharArray());
                        token.setRememberMe(true);
                        host = "M";
                        rs.put("host", host);
                        token.setHost("M");
                        token.setUsername(userName);
                        try {
                            Subject subject = SecurityUtils.getSubject();
                            subject.login(token);
                        } catch (AuthenticationException e) {
                            return JsonBizTool.genJson(ExRetEnum.LOGIN_ACCOUNT_PASSWORD_ERROR);
                        }
                        return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            if (SecurityUtils.getSubject().isAuthenticated()) {
            String id = (String) SecurityUtils.getSubject().getPrincipal();
            Manager manager = managerService.selectByPrimaryKey(id);
            String userName = manager.getUserName();

            if (userName == null) {
                return "login";
            } else if (userName.contains("Admin") || userName.contains("ADMIN") || userName.contains("admin")) {
                return "redirect:/OrganizationDashboardController/dashboard";
            } else if (host.equals("P")) {
                return "redirect:/OrganizationDashboardController/dashboard";
            } else {
                return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
            }
        }
        return "login";
    }
}
