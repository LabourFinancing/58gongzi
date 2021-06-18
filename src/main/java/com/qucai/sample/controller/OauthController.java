package com.qucai.sample.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.*;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.service.PersonalMainService;
import com.qucai.sample.service.PersonalTreasuryCtrlService;
import com.qucai.sample.service.ProductMainService;
import com.qucai.sample.util.Tool;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.security.CaptchaUsernamePasswordToken;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.util.JsonBizTool;

import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobilePersonalMain;

@Controller
@RequestMapping(value = "/oauthController")
public class OauthController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PersonalMainService personalMainService;
    

    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response,String txnAmount,String walletTxn_PayerPID,String walletTxn_ReceiverID,
                        EwalletTxn ewalletTxn, MobileEwalletDashboard ewalletDashboard, MobilePersonalMain mobilePersonalMain,
                        String userName, String pid, String password, String page, String remember, String paymentchannel, String action,
                        String mode, String gid, String method, String phone, String host,
                        String SMSsendcode, String SMSstrret, String type, String API) throws Exception {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
        token.setUsername(userName);
        
        //http://localhost:8080/sample/oauthController/login?method=QRcode
        if(method!=null&&method.equals("getUserInfo")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        
        //支付测试调用
//        if(method!=null&&method.equals("QRcode")&&pid!=null&&userName!=null&&paymentchannel!=null&&mode!=null){
        if(method!=null&&method.equals("QRcode")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
//            JSONObject resp = OrderPayDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getJSONObject("body").getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //支付测试返回
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

        /*
         ************************************************** payment start 收付款 *************************************************
         * mobilepay transmit, 58gongzi - Alipay/wechantpay/unionpay  --- 1st version
         * FX/Remit 58gongzi - Paypal/Swift/visa/master/AE ---- 2nd version
         * Mobile APP 调用个人交易 移动端交易首页二维码扫一扫交易
         ************************************************************************************************************************
         */
        //http://localhost:8080/sample/oauthController/login?method=58scan-txn-58qr&action=transaction&page=mobilepay
        //个人收付款58-58  ( payee - 58,receiver - 58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-txn-58qr")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String[] txnMethod = method.split("-");
            for(int i=0;i<txnMethod.length;i++){
                switch (i) {
                    case 0 : System.out.println("Payee:");
                    case 1 : System.out.println("txn:");
                    case 2 : System.out.println("Receiver:");
                }
                System.out.print(txnMethod[i]);
            }
            // buffer checking
            // general treasury mgt
            // personal treasury mgt
            // personal ewallet and personal evaluation
            // transaction address
            // 3rd party payment call
            // ret checking personal ewallet repo and personal revaluation
            // buffer checking
            System.out.println(txnMethod[0]);
            txnMethod[1].equalsIgnoreCase("58scan");
            txnMethod[3].equalsIgnoreCase("58qr");
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID("31011598308052521X");
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");
            
            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        
        //个人收付款58-wechat
        //个人收付款58qr-wechatscan/alipayscan/unionpayscan ( payee - 58,receiver-wechat )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58qr-txn-wechatscan")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        //个人收付款58scan-wechatqr/aliqr/unionpayqr    ( payee - 58,receiver-wechat )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-txn-wechatqr")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        //个人收付款wechatqr/aliqr/unionpayqr-58  ( payee - wechat,receiver-58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("wechatqr-txn-58scan")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        //个人收付款wechatscan/alipayscan/unionpayscan-58qr  ( payee - wechat,receiver-58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("wechatscan-txn-58qr")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        
        //个人消费  ( payee - 58,payee representer- GFwechat )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("scan-shopping-58qr")&&action.equalsIgnoreCase("payee")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        // ( payee - 58,payee representer- GFwechat )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-shopping-qr")&&action.equalsIgnoreCase("payee")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        
        /*
        //个人消费
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("ewalletTXN")&&action.equalsIgnoreCase("shopping")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        */
        
        //个人支付渠道切换
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("personalpymtswitch")&&action.equalsIgnoreCase("paymentswitch")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        
        //个人充值
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("ewallettoup")&&action.equalsIgnoreCase("topup")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }
        
        /*
        ************************************************ Mobilehome begin 移动端首页开始 ************************************
        * wealthmgt,voucher,topup,   58gongzi  ---1st version 
        * blockchain , supplychian    wo-bank  ---2nd version
        * 钱包管理
        *******************************************************************************************************************
         */
        //财富管理 wealthmgt
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("wealthmgt")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用财富管理成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/Ewalletcontroller/mobileewallet";
        }
        //优惠券voucher
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("voucher")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用钱包成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/Ewalletcontroller/mobileewallet";
        }
        //充值 topup
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("topup")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(10.00));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用钱包成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/Ewalletcontroller/mobileewallet";
        }

        /*
         ************************************************ Mobileme begin 移动端个人信息开始 ************************************
         * Personalinfo , Customer Service , bank card,company 58gongzi  ---1st version
         * Upgrade  --- 2nd version
         ******************************************************************************************************************
         */
        //个人信息 personalinfo
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("MobilePersonalMain")&&action.equalsIgnoreCase("personalinfo")) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return "redirect:/PersonalMaincontroller/personalMMobiledashboard";
        }

        //个人银行卡 private bankcard
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("MobilePersonalMain")&&action.equalsIgnoreCase("bankcard")) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return "redirect:/PersonalMaincontroller/personalMMobiledashboard";
        }
        //个人服务的企业 served firm
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("MobilePersonalMain")&&action.equalsIgnoreCase("company")) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return "redirect:/PersonalMaincontroller/personalMMobiledashboard";
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
