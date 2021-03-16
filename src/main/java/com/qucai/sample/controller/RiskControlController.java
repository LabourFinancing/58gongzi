//package com.qucai.sample.controller;
//
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.subject.Subject;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.security.CaptchaUsernamePasswordToken;
//import com.qucai.sample.util.JsonBizTool;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.pagehelper.PageInfo;
//import com.qucai.sample.OperationTypeConstant;
//import com.qucai.sample.common.PageParam;
//import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.entity.Resource;
//import com.qucai.sample.entity.StaffPrepayApplication;
//import com.qucai.sample.entity.PersonalInfo;
//import com.qucai.sample.vo.StaffPrepayApplicationNew;
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.service.StaffPrepayApplicationService;
//import com.qucai.sample.service.PersonalInfoService;
//import com.qucai.sample.service.FinanceProductService;
//import com.qucai.sample.util.JsonBizTool;
//import com.qucai.sample.util.ShiroSessionUtil;
//import com.qucai.sample.util.Tool;
//
//
//@Controller
//@RequestMapping(value = "/RiskControlController")
//public class RiskControlController {
//
////    @RequestMapping("/login")
//    @ResponseBody
////    public String login(String userName, String password, String remember) {
////        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
////        token.setUsername(userName);
////        token.setPassword(password.toCharArray());
////        token.setRememberMe(true);
////        Subject subject = SecurityUtils.getSubject();
////        try {
////            subject.login(token);
////        } catch (AuthenticationException e) {
////            return JsonBizTool.genJson(ExRetEnum.LOGIN_ACCOUNT_PASSWORD_ERROR);
////        }
////        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
////
////    }
//    
//    public String RiskControlControllerMain(PersonalInfo personalInfo, String t_P_Employmentstatus,String t_P_EmploymentCategory,String t_P_WorkYears){
//    	
//    }
//
//    @RequestMapping("logout")
//    public String logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return "login";
//    }
//
//}
