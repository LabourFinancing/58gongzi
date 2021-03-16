package com.qucai.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qucai.sample.entity.Manager;
import com.qucai.sample.service.ManagerService;

@Controller
public class IndexController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("index")
    public String index(@RequestParam(required = false) String gid,HttpServletRequest request, HttpServletResponse response) {
//    	if(gid != null){
//    		System.out.print("Fujian Province Platform:");
//    		System.out.print(gid);
//            try {
//				String Token = HttpJsonPersonalTest.main(); //Call FuJian wechat API
//				if (Token.equals("1")){
//	                return "login";
//				}else{
//					String TokenKey = Token;
//					//generate new url to get user info
//					String userMobilurl = null;
//					StringBuffer TokenUrl =  new StringBuffer();
//					userMobilurl = String.valueOf(TokenUrl.append("https://www.cspower.net/bigcenter/index.php?s=/addon/Bigc/SyAPI/GetInfoByGID/gid/").append(gid).append("/token/").append(TokenKey)).replaceAll("null", "").trim();
//		            String id = (String) SecurityUtils.getSubject().getPrincipal();
//		            String userName;
//		            String password;
//	            if (id != null){
//			            Manager manager = managerService.selectByPrimaryKey(id);
//			            userName = manager.getUserName();
//			            password = manager.getPassword();
//	            }else{
//					    String mobile = HttpJsonPersonalTest.UserMobil(userMobilurl, gid, TokenKey);
//					  	Manager manager = managerService.selectByMobil(mobile);   // get user mobile
//					  	userName = manager.getUserName();
//					  	password = manager.getPassword();
//	            }
//	            if (userName == null) {
//	                return "login";
//	            } else if (userName.contains("Admin") || userName.contains("ADMIN") || userName.contains("admin")) {
//	                return "mainFrame";
//	            } else { //Turn to login page when userid not existing
//	            	String type = "login";
//	            	String API = "Fujian";
//	                CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
//	                Map<String, Object> rs = new HashMap<String, Object>();
//	                token.setPassword(password.toCharArray());             
//	                token.setRememberMe(true);
//	            	String host = "M";
//	                rs.put("host", host);
//                    token.setHost("M");  
//                    token.setUsername(userName);
//	            	try {
//		                Subject subject = SecurityUtils.getSubject();
//	                    subject.login(token);
//	                } catch (AuthenticationException e) {
//	                    return JsonBizTool.genJson(ExRetEnum.LOGIN_ACCOUNT_PASSWORD_ERROR);
//	                }
//	            	return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
//	            }
//			 }
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	}
        if (SecurityUtils.getSubject().isAuthenticated()) {
            String id = (String) SecurityUtils.getSubject().getPrincipal();
            Manager manager = managerService.selectByPrimaryKey(id);
            String userName = manager.getUserName();

            if (userName == null) {
                return "login";
            } else if (userName.toLowerCase().contains("admin")) {
                return "mainFrame";
            } else {
                return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
            }
        }
        return "login";
    }
}
