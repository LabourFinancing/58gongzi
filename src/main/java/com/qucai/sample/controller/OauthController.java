/**
 ***********************************   58gongzi API core  ***********************************
 *  Core API Controller,Interface for Internal System Node 58gongzi system upgrade          *
 *  and outface - - ewallet , wechat/alipay integration                                     *
 *  Author: Spear Yao                                                                       *
 *  Date: 06/16/2021                                                                        *
 *  version 1.0                                                                             *
 ********************************************************************************************
 */

package com.qucai.sample.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller.AlipayFundTransOrderQueryController;
import com.qucai.sample.entity.*;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderPayDemo;
import com.qucai.sample.service.*;
import com.qucai.sample.util.*;
import com.qucai.sample.util.OverallStatisticRefresh;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.security.CaptchaUsernamePasswordToken;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;

import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobilePersonalMain;
import com.qucai.sample.util.PersonalValueEst;


@Controller
@RequestMapping(value = "/oauthController")
public class OauthController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PersonalMainService personalMainService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @Autowired
    private PersonalInfoBatchUploadService personalInfoBatchUploadService;


    @RequestMapping("/login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response,String facialret,String txnAmount,
                        String personalMID,String walletTxn_PayerPID,String walletTxn_ReceiverID,
                        String PaymentChannel,String shoppingAmount, String TopupAmount,String CashoutAmount,String realName,
                        String userName, String pid, String password, String page, String paymentchannel, String action,String cardAcc,
                        String mode, String gid, String method, String phone, String host,String paymentID,String paymentStatus,
                        String SMSsendcode, String SMSstrret, String type, String API) throws Exception {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
        token.setUsername(userName);

        //http://localhost:8080/sample/oauthController/login?method=getUserInfo - alipay
        if(method!=null&&method.equals("getUserInfo")){
            Map<String, Object> rs = new HashMap<String, Object>();
//            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
//            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            AlipayFundTransOrderQueryModel alipayModel = null;
            ModelMap modelMap = null;
            JSONObject resp = AlipayFundTransOrderQueryController.doPost(request,response,null, null);
//            String QRcodeinit = resp.getString("qrCode");
//            rs.put("QRcodeinit", QRcodeinit);
//            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, resp);
        }
        
        //http://localhost:8080/sample/oauthController/login?method=get58qrcode
        if(method!=null&&method.equals("get58qrcode")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            String QRcodeinit = resp.getJSONObject("body").getString("qrCode");
            rs.put("QRcodeinit", QRcodeinit);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //支付测试调用
        //http://localhost:8080/sample/oauthController/login?method=QRCode
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

        //支付测试返回
        //http://localhost:8080/sample/oauthController/login?method=QRCode&pid=31011519830805251X
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


        /******************
        New User Register 新用户注册 node发起注册 开通钱包 支付时要求绑定卡 - done
         */
        //http://localhost:8080/sample/oauthController/login?method=NewUser&facialret=0&pid=31011519830805251X&phone=18001869161&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&realName=姚诚铭
        if(method!=null&&method.equalsIgnoreCase("NewUser")){
            Map<String, Object> rs = new HashMap<String, Object>();
            Map<String, Object> rsNewUserEwallet = new HashMap<String, Object>();
            Map<String, Object> rsPersonalMainReg = new HashMap<String, Object>();
            // buffer checking

            // personalMain register
            PersonalMainController personalMainController = new PersonalMainController();
            rsPersonalMainReg = personalMainController.addMobilePersonalMain(personalMID,pid,phone,facialret,realName);
            
            if(rsPersonalMainReg.get("SQL-PersonalMain").equals("0")){
                EwalletController ewalletController = new EwalletController();
                // personalEwallet register
                // personal Product bind - gf-Be-Db 默认绑定产品
                // Personal Treasury Management bind - payment-a-v
                rsNewUserEwallet = ewalletController.addMobileEwallet(personalMID,pid,realName);
                if(rsNewUserEwallet.get("SQL-PersonalEwallet").equals("0")){
                    Map<String, Object> rsNewUserEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticNew(personalMID,pid,realName);
                    if(rsNewUserEwalletStatistic.get("SQL").equals("SQL-PERSONALEWALLETSTATISTICADDSUCC")) {
                        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rsNewUserEwallet);
                    }else{
                        return JsonBizTool.genJson(ExRetEnum.FAIL, rsNewUserEwallet); 
                    }
                }else {
                    return JsonBizTool.genJson(ExRetEnum.FAIL, rsNewUserEwallet);
                }
            }else{
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsNewUserEwallet);
            }
            
            // personal Company Info checking - Company Ops handling - new add Manager/personalinfo
            // personal ewallet and personal evaluation
            // blacklist verify
            // transaction address
            // 3rd party payment call
            // ret checking personal ewallet repo and personal revaluation 
            // buffer checking

        }

        //checking reinfrastructure the payment method
        /*************************************************** payment start 收付款 *******************************************
         * mobilepay transmit, 58gongzi - Alipay/wechantpay/unionpay  --- 1st version
         * FX/Remit 58gongzi - Paypal/Swift/visa/master/AE ---- 2nd version
         * Mobile APP 调用个人交易 移动端交易首页二维码扫一扫交易
         ********************************************************************************************************************/
        
        //http://localhost:8080/sample/oauthController/login?method=58scan-txn-58qr&action=transaction&page=mobilepay&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=430528198502043837&txnAmount=1
        //个人收付款58-58  ( payee - 58,receiver - 58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-txn-58qr")&&action.equalsIgnoreCase("transaction")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            String txnCat = method;
            Map<String,Object> rs = new HashMap<>();
            if(walletTxn_PayerPID.equalsIgnoreCase(walletTxn_ReceiverID)){
                rs.put("58scan-txn-58qr","payerPID can't equals to receiverPID");
                return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
            }
            MobilePersonalMain mobilePersonalMain = null;
            String[] txnMethod = method.split("-");
            Boolean ContainPayer58 = false,ContainReceiver58= false;
            for(int i=0;i < txnMethod.length;i++){
                switch (i) {
                    case 0 : System.out.println("Payer:");
                        ContainPayer58 = txnMethod[i].contains("58");
                        break;
                    case 1 : System.out.println("txn:");
                        break;
                    case 2 : System.out.println("Receiver:");
                         ContainReceiver58 = txnMethod[i].contains("58");
                        break;
                }
                System.out.print(txnMethod[i]);
            }

            System.out.println(txnMethod[0]);
            if(txnMethod[0].equalsIgnoreCase("58scan"));{
                System.out.println("Succ-payee: 58scan");
            };
            if(txnMethod[2].equalsIgnoreCase("58qr")){
                System.out.println("Succ-Bene: 58qr");
            };
            
            BigDecimal txnAmt = new BigDecimal(txnAmount);
            //find personal Ewallet Info
            String personalEwalletType = "payerQuery";
            EwalletController ewalletController = new EwalletController();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            String PersonalEwalletPayCat = "Payinput";
            Map<Object, String> mobilePayerOriginFindPersonalEwalletResult = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerOriginTotCNYBalance(new BigDecimal(mobilePayerOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_PayerOriginTotCNYBalance")));
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerID(mobilePayerOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_PayerPID"));

            if (mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerOriginTotCNYBalance() == null){
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerOriginTotCNYBalance(BigDecimal.valueOf(0.00));
            }
            int BalToAmt = txnAmt.compareTo(mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerOriginTotCNYBalance());
            if(BalToAmt == 1 ){
                rs.put("topup","outOfBalance");
                return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
            }else{
                //正常金额收款人原始余额查询 
                personalEwalletType = "RECEIVERQUERY";
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerTotCNYBalance(mobileEwalletDashboard.getT_mobilePersonalEwallet_PayerOriginTotCNYBalance().subtract(txnAmt));
                Map<Object, String>  mobileReceiverOriginFindPersonalEwalletResult = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverOriginTotCNYBalance(new BigDecimal(mobileReceiverOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_ReceiverOriginTotCNYBalance")));
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverID(mobileReceiverOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_ReceiverID"));
            }
            
            //find Payer personal Info and 
            PersonalMainController personalMainController = new PersonalMainController();
            mobilePersonalMain = (MobilePersonalMain) personalMainController.findPersonalMainInfo(walletTxn_PayerPID,conn);
            System.out.print(mobilePersonalMain);
            
            ProductMainController productMainController = new ProductMainController();
            ProductMain MobileProductMain = (ProductMain) productMainController.findPersonalProduct(mobilePersonalMain.getT_mobilePersonalMain_productCat(),conn);

            System.out.print(MobileProductMain);
            
            //个人资金监控
            if( ContainPayer58 && ContainReceiver58) {
                System.out.println("58-58 transit no limited");
            }else{
                PersonalTreasuryCtrlController personalTreasuryCtrlController = new PersonalTreasuryCtrlController();
                PersonalTreasuryCtrl MobilePersonalTreasuryCtrl = (PersonalTreasuryCtrl) personalTreasuryCtrlController.findPersonalTreasury(MobileProductMain.getT_Product_SeriesID(),conn);
                System.out.print(MobilePersonalTreasuryCtrl);
                //Personal Treasury Regulatory Chk
                Map<String,Object> PersonalTreasuryChk = PersonalValueEst.PersonalTreasuryChk(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            }
            
            //call topup 调用充值
            //personal treasury mgt
            
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(action,txnCat,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            
            if(rsMobileEwalletTxn.get("SQL").equals("SQL-RECEIVEREWALLETUPDATESUCC")){
                personalEwalletType = "RECEIVERQUERY";
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("PAYOUTPUT");
                Map<Object, String> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverTotCNYBalance(mobileEwalletDashboard.getT_mobilePersonalEwallet_ReceiverOriginTotCNYBalance().add(txnAmt));
                conn.close();
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")){
                conn.close();
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")){
                conn.close();
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
            }

            if (!rsMobileEwalletTxn.isEmpty()){
                conn.close();
                Map<String, Object> retPayerEwalletStatistic = new HashMap<>();
                Map<String, Object> retReceiverEwalletStatistic = new HashMap<>();
                //Renew PersonalEwallet Statistic
                if(walletTxn_PayerPID != null) {
                    String PersonalPID = walletTxn_PayerPID;
                    retPayerEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if( walletTxn_ReceiverID != null) {
                    String PersonalPID = walletTxn_ReceiverID;
                    retReceiverEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if(retPayerEwalletStatistic.get("SQL") != null && retReceiverEwalletStatistic.get("SQL") != null) {
                    String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                    return mobileEwalletDashboardJson;
                }else{
                    rs.put("errMsg","rsMobileEwalletStatisticRenew failed");
                    return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
                }
            }else{
                rs.put("errMsg","rsMobileEwalletTxn is Empty");
                return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
            }
            // buffer checking
            // general treasury mgt

            // personal ewallet and personal evaluation
            // transaction address
            // 3rd party payment call
            // ret checking personal ewallet repo and personal revaluation, and PersonalWorthy Renew
            // buffer checking

//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
        }

        //个人收付款58qr-txn-wechatscan/alipayscan/unionpayscan
        //http://localhost:8080/sample/oauthController/login?method=58qr-txn-wechatscan&action=transaction&page=mobilepay&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=wechat&txnAmount=0.1&paymentID=$&paymentStatus=&
        //个人收付款58qr-wechatscan/alipayscan/unionpayscan ( payee - 58,receiver-wechat )$
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&action.equalsIgnoreCase("58qr-receiver")){
            Map<String, Object> rs = new HashMap<String, Object>();
            switch (method.toLowerCase()) {
                case "58qr-txn-alipayscan" :
                    ;
                case "58qr-txn-wechatscan" :
                    ;
                case "58qr-txn-unionpayscan" :
                    ;
            }
            EwalletTxn ewalletTxn = null;
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(txnAmount));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人消费成功");
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        //个人收付款58scan-wechatqr/aliqr/unionpayqr    ( payee - 58,receiver-wechat )
        //http://localhost:8080/sample/oauthController/login?method=58scan-txn-wechatqr&action=transaction&page=mobilepay&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=wechat&txnAmount=0.1
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-txn-wechatqr/alipayqr/unionpayqr")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            EwalletTxn ewalletTxn = null;
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(txnAmount));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        //http://localhost:8080/sample/oauthController/login?method=wechatqr-txn-58scan&action=transaction&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&txnAmount=0.1
        //个人收付款wechatqr/aliqr/unionpayqr-58  ( payer - wechat,receiver-58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("wechatqr/alipayqr/unionpayqr-txn-58scan")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            EwalletTxn ewalletTxn = null;
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(txnAmount));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                String PersonalPID = walletTxn_ReceiverID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        //http://localhost:8080/sample/oauthController/login?method=wechatscan-txn-58qr&action=transaction&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&txnAmount=0.1
        //个人收付款wechatscan/alipayscan/unionpayscan-58qr  ( payer - wechat,receiver-58 )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("wechatscan/alipayscan/unionpayscan-txn-58qr")&&action.equalsIgnoreCase("transaction")){
            Map<String, Object> rs = new HashMap<String, Object>();
            EwalletTxn ewalletTxn = null;
            ewalletTxn.setT_WalletTxn_TotTxnAmount(new BigDecimal(txnAmount));
            ewalletTxn.setT_WalletTxn_ID(Tool.PayId());
            ewalletTxn.setT_WalletTxn_PayerPID(pid);
            ewalletTxn.setT_WalletTxn_ReceiverID("430528198502043837");

            System.out.print(ewalletTxn);
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                String PersonalPID = walletTxn_ReceiverID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        //http://localhost:8080/sample/oauthController/login?method=scan-shopping-58qr&action=shopping&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&PaymentChannel=debitcardNum&shoppingAmount=0.1
        //个人消费  ( payer - 58,receiver representer- GFwechat )
        if( method!=null&&page.equalsIgnoreCase("ewalletpay")&&method.equals("scan-shopping-58qr")&&action.equalsIgnoreCase("shopping")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            //verify personal treasury delegation
            Map<String, Object> PersonalTreasuryChk = new HashMap<>();
            String txnCat = "PersonalEwalletShopping";
            //find Treasury prod alias
            BigDecimal txnAmt = new BigDecimal(shoppingAmount).setScale(25,2);
            PersonalTreasuryChk = PersonalValueEst.PersonalTreasuryChk(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletCashoutTxn =  new HashMap<>();
            Map<String, Object> rsMobileEwalletShoppingTxn =  new HashMap<>();
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            if (rsMobileEwalletTxn.get("UpdatePersonalEwalletSucc").equals("succ")) {
                String PersonalPID = walletTxn_ReceiverID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人钱包消费扣款成功");
                //Pay via Org Wechatpay/alipay acc - call Node Org pay coding...
                rsMobileEwalletTxn.put("SMSverify",0);
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }else{
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
            }
        }
        //http://localhost:8080/sample/oauthController/login?method=58scan-shopping-qr&action=shopping&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&txnAmount=0.1
        // ( payee - 58,payee representer- GFwechat )
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-shopping-qr")&&action.equalsIgnoreCase("payee")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                String PersonalPID = walletTxn_ReceiverID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人消费成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        
        //个人消费退款
        http://localhost:8080/sample/oauthController/login?method=ewalletTXNrefund&action=refund&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&txnNum=123123123123123
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("ewalletTXNrefund")&&action.equalsIgnoreCase("refund")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                String PersonalPID = walletTxn_ReceiverID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人消费退款到钱包成功");
                rs.put("SMSverify",0);
            }
            return "redirect:/EwalletTXNcontroller/personalEWTTxnMobile";
        }

        //个人充值
        //个人钱包充值 http://localhost:8080/sample/oauthController/login?method=ewallettopup&action=topup&page=mobilepay&walletTxn_PayerPID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&TopupAmount=100.00&&paymentID=$&paymentStatus=&
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("ewallettopup")&&action.equalsIgnoreCase("topup")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            String txnCat = "PersonalEwalletTopup";
            BigDecimal txnAmt = new BigDecimal(TopupAmount);
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            conn.close();
            if (rsMobileEwalletTxn.get("SQL").equals("SQL-RECEIVEREWALLETTOPUPSUCC")) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人充值成功");
                rsMobileEwalletTxn.put("SMSverify",0);
                return JsonBizTool.genJson(ExRetEnum.SUCCESS, rsMobileEwalletTxn);
            }else{
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
            }
        }

        //个人提现 cashout target account alipayacc/debitcard/creditcard/wechatacc
        //个人钱包提现 http://localhost:8080/sample/oauthController/login?method=ewalletcashout&action=debitcard&page=mobilehome&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&CashoutAmount=100.00&&paymentID=$&paymentStatus=&
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletcashout")&&action.equalsIgnoreCase("cashout")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            //verify personal treasury delegation
            Map<String, Object> PersonalTreasuryChk = new HashMap<>();
            String txnCat = "PersonalEwalletCashout";
            //find Treasury prod alias
            BigDecimal txnAmt = new BigDecimal(CashoutAmount).setScale(25,2);
            PersonalTreasuryChk = PersonalValueEst.PersonalTreasuryChk(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletCashoutTxn =  new HashMap<>();
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            conn.close();
            if (rsMobileEwalletTxn.get("UpdatePersonalEwalletSucc").equals("succ")) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人钱包提现成功");
                //Cashout to Personal Bank card
                StaffPrepayApplicationPayment PersonalApplicationPay = null;
                //Input all Payment Info into it, coding...
                PersonalApplicationPay.setTranAmt(txnAmount); 
                String PaymentSwitch = "shsd";
                Map<String, Object> retPersonalEwalletCashout =  PaymentCall.PersonalEwalletCashout(PaymentSwitch,PersonalApplicationPay);
//                rsMobileEwalletCashoutTxn = 
                rsMobileEwalletTxn.put("SMSverify",0);
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }else{
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
            }
        }

        //个人预支支付或预支消费
        //个人钱包预支支付或预支消费 http://localhost:8080/sample/oauthController/login?method=ewalletcashadvance&action=cashadvance&page=mobilehome&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&TopupAmount=100.00&&paymentID=$&paymentStatus=&
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletcashadvance")){
            System.out.print(action); // action=cashadvance , action=cashadvancepay
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            String txnCat = "PersonalEwalletCashout";
            BigDecimal txnAmt = new BigDecimal(TopupAmount); 
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(action,txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            conn.close();
            if (rsMobileEwalletTxn.get("UpdatePersonalEwalletSucc").equals("succ")) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人提现成功");
                rsMobileEwalletTxn.put("SMSverify",0);
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }else{
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
            }
        }
        /************************************************* Mobilehome begin 移动端首页开始 ************************************
         * wealthmgt,voucher,topup,   58gongzi  ---1st version
         * blockchain , supplychain    wo-bank  ---2nd version
         * 钱包管理
         ********************************************************************************************************************/
        
        //钱包管理 ewalletmgt  action=ewalletsalary/ewalletrental/ewalletservicesfees/ewalletpurchasepayments
        //个人钱包管理接口 http://localhost:8080/sample/oauthController/login?method=ewalletmgt&action=ewalletmgt&page=mobilehome&&pid=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("wealthmgt")){
            Map<String, Object> rsEwalletSalary = new HashMap<String, Object>();
            switch (method.toLowerCase()) {
                case "ewalletsalary" :
                    System.out.println("ewalletsalary");
                    // call salary dashboard
                case "ewalletrental" :
                    System.out.println("ewalletrental");
                case "ewalletservicesfees" :
                    System.out.println("ewalletservicesfees");
                case "ewalletpurchasepayments" :
                    System.out.println("ewalletpurchasepayments");
            }
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (rsEwalletSalary.get("UpdatePersonalEwalletSucc").equals("succ")) {
                System.out.println("调用个人提现成功");
                rsEwalletSalary.put("SMSverify",0);
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }else{
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsEwalletSalary);
            }
        }

        //营收管理 wealthmgt
        //个人营收管理接口 http://localhost:8080/sample/oauthController/login?method=ewalletdashboard&action=wealthmgt&page=mobilehome&&pid=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("wealthmgt")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用财富管理成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
        
        //优惠券voucher
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletdashboard")&&action.equalsIgnoreCase("voucher")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用钱包成功");
                rs.put("SMSverify",0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }

        /************************************************* Mobileme begin 移动端个人信息开始 ************************************
         * Personalinfo , Customer Service , bank card,company 58gongzi  ---1st version
         * Upgrade  --- 2nd version
         *********************************************************************************************************************/
        
        //个人信息 personalinfo
        //个人主信息接口 http://localhost:8080/sample/oauthController/login?method=MobilePersonalMain&action=personalinfo&page=mobileme&&pid=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("MobilePersonalMain")&&action.equalsIgnoreCase("personalinfo")) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }

        //个人银行卡 private bankcard
        //个人信用卡接口 http://localhost:8080/sample/oauthController/login?method=ewallettbankcard&action=bankcard&page=mobileme&&pid=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&cardAcc=5187188100953387-招商银行-creditcard
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("ewallettbankcard")&&action.equalsIgnoreCase("bankcard")) {
            Map<String, Object> rsUserEwalletbnkCard = new HashMap<String, Object>();
            EwalletController ewalletController = new EwalletController();  
            rsUserEwalletbnkCard = ewalletController.UpdatePayerPersonalEwalletBindBnkCard(personalMID,pid,cardAcc);
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
//            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
//                System.out.println("调用个人信息");
//                rs.put("SMSverify", 0);
//            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rsUserEwalletbnkCard);
        }
        //个人服务的企业 served firm
        //个人企业信息接口 http://localhost:8080/sample/oauthController/login?method=ewalletcompany&action=company&page=mobileme&&pid=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475
        if( method!=null&&page.equalsIgnoreCase("mobileme")&&method.equals("MobilePersonalMain")&&action.equalsIgnoreCase("company")) {
            Map<String, Object> rs = new HashMap<String, Object>();
            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            if (SMSsendcode.equalsIgnoreCase(SMSsendcodecvt)) {
                System.out.println("调用个人信息");
                rs.put("SMSverify", 0);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
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
