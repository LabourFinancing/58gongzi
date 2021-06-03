package com.qucai.sample.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderPayDemo;
import com.qucai.sample.util.Tool;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.entity.Manager;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.security.CaptchaUsernamePasswordToken;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.util.JsonBizTool;

@Controller
@RequestMapping(value = "/oauthController")
public class OauthController {

    @Autowired
    private ManagerService managerService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response, String userName,String pid, String password, String remember,
                        String paymentchannel,String mode,String gid,String from, String form,String method, String phone,String host,
                        String SMSsendcode, String SMSstrret,String type, String API) throws Exception {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
        token.setUsername(userName);
        
        if(method!=null&&method.equals("getUserInfo")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        
        //支付调用
//        if(method!=null&&method.equals("QRcode")&&pid!=null&&userName!=null&&paymentchannel!=null&&mode!=null){
        if(method!=null&&method.equals("QRcode")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
//            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            JSONObject resp = OrderPayDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getJSONObject("body").getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //支付返回
        if(method!=null&&method.equals("QRScanRet")&&pid!=null&&userName!=null&&paymentchannel!=null&&mode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getJSONObject("body").getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        
        if( method!=null&&method.equals("SMSreq")){
            byte[] SMSstr;
            Map<String, Object> rs = new HashMap<String, Object>();
            String mobile = phone;
            String SMSreqcode = HttpJsonExample.SMSreqsend(mobile);
            if (SMSreqcode != null){
                rs.put("rs",0);
                Date now = new Date();
                rs.put("time",now);
                SMSstr = DigestUtils.md5(SMSreqcode);
                String SMScode = DigestUtils.md5Hex(SMSstr);
                System.out.println(SMScode);
                String SMSstring = Tool.StringSeq(SMScode);
                rs.put("SMSstr",SMSstring);
            }else{
                rs.put("rs",-1);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        
        if( method!=null&&method.equals("SMSverify")&&SMSsendcode!=null&&SMSstrret!=null){
            String SMSgetstringseq = Tool.StringSeq(SMSstrret);
            Map<String, Object> rs = new HashMap<String, Object>();
            byte[] SMSsendcodecvt =  DigestUtils.md5(SMSsendcode);
            String SMSsendcodecvti = DigestUtils.md5Hex(SMSsendcodecvt);
            if (SMSsendcodecvti.equalsIgnoreCase(SMSgetstringseq)) {
                System.out.println("MD5验证通过");
                System.out.println(SMSsendcode);
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //Mobile APP 调用个人交易
        if( method!=null&&method.equals("ewalletTXN")&&SMSsendcode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTMobiledashboard";
        }
        
        //Mobile APP 调用个人首页
        if( method!=null&&method.equals("ewalletdashboard")&&SMSsendcode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用钱包成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/Ewalletcontroller/mobile-ewallet";
        }

        //Mobile APP 调用个人信息
        if( method!=null&&method.equals("personalMain")&&SMSsendcode!=null) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return "redirect:/PersonalMaincontroller/personalMMobiledashboard";
        }

        //Mobile APP 调用个人信息
        if( method!=null&&method.equals("personalEwallet")&&SMSsendcode!=null) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return "redirect:/PersonalMaincontroller/personalMMobiledashboard";
        }


        //Mobile APP 调用优惠券
        if( method!=null&&method.equals("vourchar")&&SMSsendcode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify",0);
            }
            return "redirect:/Vourcharcontroller/personalVourcharMobiledashboard";
        }

        //Mobile APP 调用全局系统信息
        if( method!=null&&method.equals("mainboard")&&SMSsendcode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/Mainboardtcontroller/personalMainboard";
        }

        //Mobile APP 调用客服
//        if( method!=null&&method.equals("cspersonal")&&SMSsendcode!=null){
//            Map<String, Object> rs = new HashMap<String, Object>();
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
//            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
//                System.out.println("调用个人信息");
//                rs.put("SMSverify",0);
//            }
//            return "redirect:/cscontroller/personalCSdashboard";
//        }

        //Mobile APP 调用供应商管理
        if( method!=null&&method.equals("vendormgt")&&SMSsendcode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/vendormgtcontroller/personalVDRdashboard";
        }
        
        if (type.equals("resendPWD")) {
            token.setRememberMe(true);
            Manager entity = null;
            Manager manager = managerService.ForgotPWDByPrimaryKeySelective(userName);
            String resendpassword = manager.getPassword();
            String mobil = manager.getMobile();
            String CompanyName = manager.getCompany_name();
            String ResetRS = HttpJsonExample.PasswordResend(resendpassword,mobil,userName,CompanyName);
            System.out.println(ResetRS);
            Map<String, Object> rs = new HashMap<String, Object>();
            if (ResetRS != null){
                rs.put("rs", 0);
                return JsonBizTool.genJson(ExRetEnum.PASSWORD_RESENT, rs);
            }else{
                rs.put("rs", -1);
                return JsonBizTool.genJson(ExRetEnum.PASSWORD_RESENT_FAIL, rs);
            }
        } else {

            token.setPassword(password.toCharArray());
//            token.setRememberMe(true);
            
/*            String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
                    "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod", "oppo",
                    "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "vivo",
                    "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
                    "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
                    "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
                    "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
                    "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
                    "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
                    "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
                    "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
                    "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
                    "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
                    "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
                    "Googlebot-Mobile" };
            if (request.getHeader("User-Agent") != null) {
                String agent=request.getHeader("User-Agent");
                for (String mobileAgent : mobileAgents) {
                    if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
                        if (userName.toLowerCase().contains("admin")) {
                            token.setHost("P");
                            host = "P";
                        } else {
                            token.setHost("M");
                            host = "M" ;
                            break;
                        }
                      }
                   }
                }
*/
/* get remote request IP address
                if (request.getHeader("x-forwarded-for") == null) {
                    return request.getRemoteAddr();
                }
                return request.getHeader("x-forwarded-for");
*/

            if (userName.toLowerCase().contains("admin")) {
                token.setHost("P");
                host = "P";
            } else {
                token.setHost("M");
                host = "M" ;
            }

            Subject subject = SecurityUtils.getSubject();
            Map<String, Object> rs = new HashMap<String, Object>();
            rs.put("host", host);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                return JsonBizTool.genJson(ExRetEnum.LOGIN_ACCOUNT_PASSWORD_ERROR);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS,rs);
        }
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // 先重定向
        try {
            response.sendRedirect(request.getContextPath() + "/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 然后再退出登录，避免sessionid找不到
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
        }
    }
}
