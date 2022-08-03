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
import java.net.URLDecoder;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller.AlipayTxnOrder;
import com.qucai.sample.entity.*;
import com.qucai.sample.personal.OverallStatisticRefresh;
import com.qucai.sample.pymt.PaymentRoute;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.DemoBase;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderPayDemo;
import com.qucai.sample.sandpaybackstagefast.main.java.cn.com.sand.pay.sandpay.scm.demo.BindCardServlet;
import com.qucai.sample.service.*;
import com.qucai.sample.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.security.CaptchaUsernamePasswordToken;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;

import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobilePersonalMain;
import com.qucai.sample.personal.PersonalValueEst;


@Controller
@RequestMapping(value = "/oauthController")
public class OauthController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PersonalInfoService personalInfoService;

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
    public Object login(HttpServletRequest request, HttpServletResponse response,String facialret,String txnAmount,String firmRegMobile,
                        String personalMID,String walletTxn_PayerPID,String walletTxn_ReceiverID,String txnDetail,String TopupAmount,
                        String paymentChannel,String shoppingAmount, String topupAmount,String CashoutAmount,String realName,String retPaymentMsg,
                        String userName, String pid, String password, String page,String action,String cardAcc,String signPaymentMsg,String applierMobile,
                        String mode, String gid, String method, String phone, String host,String paymentID,String paymentStatus,String authNum,
                        String SMSsendcode, String SMSstrret, String type, String API) throws Exception {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
        token.setUsername(userName);
        //Personal bankcard bind
        //http://localhost:8080/sample/oauthController/login?method=bankcardbind&paymentChannel=creditcard&cardAcc=6258101661184889&applierMobile=18001869161&userName=姚诚铭  - bankcard
        //http://localhost:8080/sample/oauthController/login?method=bankcardbind&paymentChannel=debitcard&cardAcc=6228480031725525211&applierMobile=18001869161&userName=姚诚铭 - bankcard
        if(method!=null&&method.equals("bankcardbind")){
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierName(userName);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(pid);
            if(paymentChannel.equalsIgnoreCase("creditcard")) {
                mobileEwalletDashboard.setT_mobilePersonalEwallet_Creditcard(cardAcc);
            }
            if(paymentChannel.equalsIgnoreCase("debitcard")) {
                mobileEwalletDashboard.setT_mobilePersonalEwallet_Debitcard(cardAcc);
            }
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierMobile(applierMobile);
            JSONObject rs = BindCardServlet.cardbind( request, response,mobileEwalletDashboard);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        //总回调 http://localhost:8080/sample/oauthController/login?method=paymentreturn&retPaymentMsg=%7B%22head%22%3A%7B%22version%22%3A%221.0%22%2C%22respTime%22%3A%2220210819131753%22%2C%22respCode%22%3A%22000000%22%2C%22respMsg%22%3A%22%E6%88%90%E5%8A%9F%22%7D%2C%22body%22%3A%7B%22mid%22%3A%22S2135052%22%2C%22orderCode%22%3A%222021081913174259%22%2C%22tradeNo%22%3A%222021081913174259%22%2C%22clearDate%22%3A%2220210819%22%2C%22totalAmount%22%3A%22000000000100%22%2C%22orderStatus%22%3A%221%22%2C%22payTime%22%3A%2220210819131753%22%2C%22settleAmount%22%3A%22000000000100%22%2C%22buyerPayAmount%22%3A%22000000000100%22%2C%22discAmount%22%3A%22000000000000%22%2C%22txnCompleteTime%22%3A%2220210819131752%22%2C%22payOrderCode%22%3A%2220210819001333400000000000103941%22%2C%22accLogonNo%22%3A%22spe***%40163.com%22%2C%22accNo%22%3A%22%22%2C%22midFee%22%3A%22000000000000%22%2C%22extraFee%22%3A%22000000000000%22%2C%22specialFee%22%3A%22000000000000%22%2C%22plMidFee%22%3A%22000000000000%22%2C%22bankserial%22%3A%22252021081922001417161417823943%22%2C%22externalProductCode%22%3A%2200000006%22%2C%22cardNo%22%3A%22%22%2C%22creditFlag%22%3A%22%22%2C%22bid%22%3A%22%22%2C%22benefitAmount%22%3A%22000000000000%22%2C%22remittanceCode%22%3A%22%22%2C%22extend%22%3A%22%257B%2522t_mobilePersonalEwallet_ReceiverPID%2522%253A%2522430528198502043837%2522%252C%2522t_mobilePersonalEwallet_TxnID%2522%253A%252220210819131742348500%2522%252C%2522t_mobilePersonalEwallet_TxnCat%2522%253A%2522PersonalEwalletReceive%2522%252C%2522t_mobilePersonalEwallet_TxnAmount%2522%253A1%252C%2522t_mobilePersonalEwallet_OrderCode%2522%253A%25222021081913174259%2522%252C%2522t_MobilePersonalewallet_PaymentType%2522%253A%25225%2522%257D%22%7D%7D&
        //node支付宝充值回调 http://localhost:8080/sample/oauthController/login?method=paymentreturn&paymentChannel=alipay&action=nodetopup&walletTxn_ReceiverID=31011519830805251X&retPaymentMsg=%7B%22from%22%3A%22430528198502043837%22%2C%22to%22%3A%22430528198502043837%22%2C%22value%22%3A%221%22%2C%22orderid%22%3A%223837_1629607850066%22%2C%22mid%22%3A%22e1e5ce20-5d85-4c7d-a6f2-b174c9760438%22%2C%22gmt_create%22%3A%222021-08-22%2012%3A51%3A03%22%2C%22charset%22%3A%22utf-8%22%2C%22seller_email%22%3A%22contact%40goldmanfuks.com%22%2C%22subject%22%3A%22%E5%85%85%E5%80%BC%22%2C%22sign%22%3A%22ch7JfF3L%2BqeVyIEqJp4uw7VouS94kgYLce%2Fvq3De5bhDNWvGI%2B4W57Y9D6bgPzPiOFSsuHxZmVrNs%2BT4qptzGTMoQ%2B98HDRNCSiGmKnCKG0AHFuvyza981B3Ne4dxKtedXQ%2BSReEkB38ptJno%2F4Js01scZvwTurTXsB5R4X7U2ihfA1LF13Yap04sMqQL8q5bzhTaJPme819EMQaTvOfh2NIz8k2Tq5JpgqwYcwbl%2BXh5psTDLogLLfFtpyfjIvHP1QBKcO1wcNLwSHUvWy0DGFAPuDit2CrkcvaIo8TZPVw9NxPW9bSUSMLnAlpFoA2dvK4RC9p0ow%2BmftvSn8Ljg%3D%3D%22%2C%22body%22%3A%22%7B%5C%22from%5C%22%3A%5C%22430528198502043837%5C%22%2C%5C%22to%5C%22%3A%5C%22430528198502043837%5C%22%2C%5C%22value%5C%22%3A%5C%221%5C%22%2C%5C%22subject%5C%22%3A%5C%22%E5%85%85%E5%80%BC%5C%22%2C%5C%22body%5C%22%3A%5C%22%E9%92%B1%E5%8C%85%E5%A2%9E%E5%80%BC%5C%22%2C%5C%22mid%5C%22%3A%5C%22e1e5ce20-5d85-4c7d-a6f2-b174c9760438%5C%22%7D%22%2C%22buyer_id%22%3A%222088702544399694%22%2C%22invoice_amount%22%3A%221.00%22%2C%22notify_id%22%3A%222021082200222125104099691411597139%22%2C%22fund_bill_list%22%3A%22%5B%7B%5C%22amount%5C%22%3A%5C%221.00%5C%22%2C%5C%22fundChannel%5C%22%3A%5C%22PCREDIT%5C%22%7D%5D%22%2C%22notify_type%22%3A%22trade_status_sync%22%2C%22trade_status%22%3A%22TRADE_SUCCESS%22%2C%22receipt_amount%22%3A%221.00%22%2C%22buyer_pay_amount%22%3A%221.00%22%2C%22app_id%22%3A%222021002148608251%22%2C%22sign_type%22%3A%22RSA2%22%2C%22seller_id%22%3A%222088141548340923%22%2C%22gmt_payment%22%3A%222021-08-22%2012%3A51%3A04%22%2C%22notify_time%22%3A%222021-08-22%2012%3A51%3A05%22%2C%22version%22%3A%221.0%22%2C%22out_trade_no%22%3A%223837_1629607850066%22%2C%22total_amount%22%3A%221.00%22%2C%22trade_no%22%3A%222021082222001499691414503778%22%2C%22auth_app_id%22%3A%222021002148608251%22%2C%22buyer_logon_id%22%3A%22136****1489%22%2C%22point_amount%22%3A%220.00%22%7D&page=mobilepay&walletTxn_PayerPID=undefined&personalMID=undefined&paymentID=2021082222001499691414503778&paymentStatus=TRADE_SUCCESS&TopupAmount=1.01&
        if(method!=null&&method.equals("paymentreturn")){
            //check payment unique
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            System.out.print(retPaymentMsg);
            Map<String, Object> rs = new HashMap<String, Object>();
            String json = retPaymentMsg;
            String orderCode = null;
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard(); // payment & transaction input
            String txnAmt1 = null, ActionTime = null, OrderCode = null, PayStatus = null, PersonalPID = null, txnCat = null, txnID = null,returnPaymentCompleteTime=null,
                returnAppID=null,returnApplierLogonID=null,returnApplierMID=null,returnPaymentMsg=null,returnPaymentOrderNum=null;
            String mobileEwalletDashboardJson = null;
            JSONObject jsonDataRetTxnDetails = null, jsonData = null;
            Date TxnStartDate = null, TxnEndDate = null;
            String returnTxnDetail = null, returnPaymentCode = null;
            if(action.equalsIgnoreCase("nodetopup") && paymentChannel.equalsIgnoreCase("alipay")){ //if node payment return
                System.out.print("Alipay payment ret");
                try {
                    jsonData = (JSONObject) JSON.parseObject(retPaymentMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                    rs.put("JSONObject parse err", "Payment return String JSON parse error");
                    return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                } finally {
                    String body = jsonData.getString("body");
                    String jsonArray =  jsonData.getString("body");
                    String returnPaymentSubject = jsonArray.substring(4);
                    String returnPaymentOrderStatus = URLDecoder.decode(jsonData.getString("trade_status"));
                    String returnPaymentApplierID = URLDecoder.decode(jsonData.getString("buyer_id"));
                    txnAmt1 = URLDecoder.decode(jsonData.getString("total_amount"));
                    paymentID = URLDecoder.decode(jsonData.getString("trade_no"));
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(paymentID);
                    returnAppID = URLDecoder.decode(jsonData.getString("auth_app_id"));
                    returnApplierLogonID = jsonData.getString("buyer_logon_id");
                    returnApplierMID = jsonData.getString("mid");
                    PersonalPID = walletTxn_ReceiverID;
                    if(returnPaymentOrderStatus.equalsIgnoreCase("TRADE_SUCCESS")){
                        returnPaymentCode = "000000";
                    }else{
                        returnPaymentCode = "AlipayErr";
                    }
                }
                txnCat = "PersonalEwalletTopup";
            }else {
                try {
                    jsonData = (JSONObject) JSON.parse(retPaymentMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                    rs.put("JSONObject parse err", "Payment return String JSON parse error");
                    return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                } finally {
                    returnPaymentCode = jsonData.getJSONObject("head").getString("respCode");
                    returnPaymentMsg = jsonData.getJSONObject("head").getString("respMsg");
                    returnPaymentOrderNum = jsonData.getJSONObject("body").getString("orderCode");
                    returnPaymentCompleteTime = jsonData.getJSONObject("body").getString("txnCompleteTime");
                    returnTxnDetail = URLDecoder.decode(jsonData.getJSONObject("body").getString("extend"));
                }
            }
            /*
            call 58gongzi payment txn add
             */
            if(retPaymentMsg != null && returnPaymentCode.equals("000000") && action.contains("topup")) {
                if (!paymentChannel.equalsIgnoreCase("alipay")){
                    try {
                        jsonDataRetTxnDetails = JSONObject.parseObject(returnTxnDetail);
                    } catch (Exception e) {
                        e.printStackTrace();
                        rs.put("JSONObject parse err", "Payment return String JSON parse error");
                        return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                    } finally {
                        System.out.print(jsonDataRetTxnDetails);
                        txnAmt1 = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_TxnAmount"); //t_mobileWalletTxn_TotTxnAmount
                        PersonalPID = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_ReceiverPID"); //t_mobileWalletTxn_ReceiverPID
                        paymentID = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_OrderCode"); //t_mobileWalletTxn_Num
                        txnCat = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_TxnCat");
                        ; //t_mobileWalletTxn_TxnCat
                    }
                    txnCat = "PersonalEwalletReceive";   // transperency code
                }

                switch (txnCat) {
                    case "PersonalEwalletReceive":
                        paymentChannel = "PersonalEwalletReceive";
                        walletTxn_ReceiverID = PersonalPID;
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                        break;
                    case "PersonalEwalletShopping":
                    case "PersonalEwalletCashout":
                        walletTxn_PayerPID = jsonDataRetTxnDetails.getString("t_mobileWalletTxn_PayerPID");
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_PayerPID);
                        break;
                }
                
                // Check Txn Status
                Map<String, Object> txnOpen = EwalletTxnController.TxnStatusCheck(paymentID,conn);
                System.out.print(txnOpen);
                
                if (txnOpen == null) {   // if not generated , then new add
                    BigDecimal txnAmt = new BigDecimal(txnAmt1).setScale(2,BigDecimal.ROUND_DOWN);
                    String paymentVendor=null;
                    txnID = Tool.PayId();
                    switch (action){
                        case "nodetopup":
                            paymentVendor = "alipay";   // payment vendor switch
                            break;
                        case "topup":
                            paymentVendor = "sandpay";   // payment vendor switch
                            break;
                    }
                    EwalletTxnController ewalletTxnController = new EwalletTxnController();
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(txnID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearOrg(mobileEwalletDashboard.getT_mobilePersonalEwallet_ApplierPID());
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum(paymentID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(PersonalPID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp(paymentChannel);
                    mobileEwalletDashboard.setT_mobilePersonalewallet_PaymentVersion(paymentVendor);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID(action);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerEwalletID(paymentID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Paystatus(returnPaymentCode);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
                    // coding   MarsMobileEwalletFind = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn); 
                    rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard, action, txnCat,cardAcc, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID, method, paymentID, paymentStatus, conn);
                    MobileEwalletDashboard mobileEwalletDashboardretpayment = new MobileEwalletDashboard();
                    if (rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")) {
                        String personalEwalletType = "RECEIVERQUERY";
                        String PersonalEwalletPayCat = "Payinput";
                        mobileEwalletDashboardretpayment.setT_mobilePersonalEwallet_PayCat("PAYOUTPUT");
//                    Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn); //check balance
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnAmount(new BigDecimal(txnAmt1).setScale(2, BigDecimal.ROUND_DOWN));
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_Interest(new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_DOWN));
                        if (txnCat.equalsIgnoreCase("PersonalEwalletReceive")) {
                            mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
                            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                        } else if (txnCat.equalsIgnoreCase("PersonalEwalletCashout") || txnCat.equalsIgnoreCase("PersonalEwalletShopping")) {
                            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(walletTxn_PayerPID);
                            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_PayerPID);
                        }
                    } else if (rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")) {
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
                    } else if (rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")) {
                        mobileEwalletDashboardretpayment.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
                    }

                    if (!rsMobileEwalletTxn.isEmpty()) {
                        Map<String, Object> retPayerEwalletStatistic = new HashMap<>();
                        Map<String, Object> retReceiverEwalletStatistic = new HashMap<>();
                        //Renew PersonalEwallet Statistic
                        if (walletTxn_PayerPID != null || txnCat.equalsIgnoreCase("PersonalEwalletShopping") || txnCat.equalsIgnoreCase("PersonalEwalletCashout")) {
                            PersonalPID = walletTxn_PayerPID;
                            retPayerEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                            Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                            if (retPayerEwalletStatistic.get("SQL") != "SQL-PERSONALEWALLETSTATISTIC-UPDATE-SUCC") {
                                mobileEwalletDashboard.setRet("-1");
                                rs.put("errMsg", "rsMobileEwalletPayerStatisticRenew failed");
                                return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                            }
                        }
                        if (walletTxn_ReceiverID != null || txnCat.equalsIgnoreCase("PersonalEwalletReceive")) {
                            PersonalPID = walletTxn_ReceiverID;
                            retReceiverEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                            Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                            if (retReceiverEwalletStatistic.get("SQL") != "SQL-PERSONALEWALLETSTATISTIC-UPDATE-SUCC") {
                                mobileEwalletDashboard.setRet("-1");
                                rs.put("errMsg", "rsMobileEwalletReceiverStatisticRenew failed");
                                return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                            }
                        }
                        mobileEwalletDashboard.setRet("0");
                        mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                        return mobileEwalletDashboardJson;
                    } else {
                        rs.put("errMsg", "rsMobileEwalletTxn is Empty");
                        conn.close();
                        return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                    }
                }else{
                    if(txnOpen.get("txnStatus").toString().equalsIgnoreCase("000000")){
                        rs.put("errMsg", "TxnClosed");
                    }else{
                        rs.put("errMsg", "3rd party payment vendor pay failed");
                    }
                    return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
                }
            }else {
                conn.close();
                rs.put("errMsg", "3rd party payment vendor pay failed");
                return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
            }
        }

        //Personal Alipay receive money
        //http://localhost:8080/sample/oauthController/login?method=alipayInternTransfer&walletTxn_ReceiverID=31011519830805251X&xnAmount=1 - alipay
        if(method!=null&&method.equals("alipayInternTransfer")){
            Map<String, Object> rs = new HashMap<String, Object>();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
            rs = AlipayTxnOrder.alipayCreateOrder(mobileEwalletDashboard);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //58生成二维码收款 等待付款
        //http://localhost:8080/sample/oauthController/login?method=get58qrcode&action=58QRewalletreceive&walletTxn_ReceiverID=430528198502043837&txnAmount=1.05&paymentChannel=alipay
        if(method!=null&&method.equals("get58qrcode")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            String PersonalPID = null;
            String txnCat = null;
            MobileEwalletDashboard ApplicationPay = new MobileEwalletDashboard();
            BigDecimal txnAmt = new BigDecimal(txnAmount);
            paymentID = DemoBase.getOrderCode();
            System.out.print("第一次生成订单下传，唯一订单号 Order-number：");
            System.out.print(paymentID);
            ApplicationPay.setT_mobilePersonalEwallet_OrderCode(paymentID);
            ApplicationPay.setT_mobilePersonalEwallet_TxnAmount(txnAmt);
            ApplicationPay.setT_mobilePersonalEwallet_treasuryID(paymentChannel);
            switch (action) {
                case "58QRewalletshopping":
                    txnCat = "PersonalEwalletShopping";
                    PersonalPID = walletTxn_PayerPID;
                    ApplicationPay.setT_mobilePersonalEwallet_TxnCat("PersonalEwalletShopping");  // transperency code
                    ApplicationPay.setT_MobilePersonalewallet_PaymentType("4");
                    break;
                default:
                    txnCat = "PersonalEwalletReceive";
                    PersonalPID = walletTxn_ReceiverID;
                    ApplicationPay.setT_mobilePersonalEwallet_TxnCat("PersonalEwalletReceive");  // transperency code
                    ApplicationPay.setT_MobilePersonalewallet_PaymentType("5");
                    break;
            }
            ApplicationPay.setT_mobilePersonalEwallet_ReceiverPID(PersonalPID);
            JSONObject resp = OrderCreateDemo.main(ApplicationPay,merchantId);
            String QRcodeinit = resp.getJSONObject("body").getString("qrCode");
            if(resp.getJSONObject("head").getString("respCode").equals("000000")){
                rs.put("QRcodeinit", QRcodeinit);
            }else{
                rs.put("errMsg","QRcode generate failed");
                return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
            }
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }

        //支付测试调用58工资扫一扫获取付款码收款
        //http://localhost:8080/sample/oauthController/login?method=authcodepay&txnAmount=1.05&walletTxn_ReceiverID=31011519830805251X&authNum=&action=transactionreceive
        if(method!=null&&method.equals("authcodepay")&&action.equalsIgnoreCase("transactionreceive")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            Map<String, Object> rs = new HashMap<String, Object>();
            Map<String, Object> MarsMobileEwalletFind = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            String merchantId = "S2135052";
            String txnCat = null;
            String paymentChannelCode = authNum.substring(0,2).trim();
            String paymentChannelProduct = null,paymentToolId = null;
            StaffPrepayApplicationPayment staffPrepayApplicationPay = new StaffPrepayApplicationPayment();
            switch (action) {
                case "transctionreceive" :
                    txnCat = "PersonalEwalletReceive";
                    staffPrepayApplicationPay.setCertType("5");
                    break;
                case "transctionshopping" :
                    txnCat = "PersonalEwalletShopping";
                    staffPrepayApplicationPay.setCertType("1");
                    break;
                default:
                    txnCat = "PersonalEwalletReceive";
                    staffPrepayApplicationPay.setCertType("5");
                    break;
            }
            switch (paymentChannelCode) {
                case "13" :
                    paymentChannelProduct = "00000005";
                    paymentToolId = "0402";
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(authNum);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerName("wechatpay");
                    break;
                case "28" :
                    paymentChannelProduct = "00000006";
                    paymentToolId = "0401";
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(authNum);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerName("alipay");
                    break;
                case "62" :
                    paymentChannelProduct = "00000013";
                    paymentToolId = "0403";
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(authNum);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerName("unionpay");
                    break;
                default :
                    paymentChannelProduct = "00000006";
                    paymentToolId = "0401";
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(authNum);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerName("default alipay");
                    break;
            }
            String paymentVendor = "sandpay";   // payment vendor switch
            String TxnID= Tool.PayId();
            paymentID = DemoBase.getOrderCode();
            staffPrepayApplicationPay.setOrderCode(paymentID); // debug using
            staffPrepayApplicationPay.setTranAmt(txnAmount);
            staffPrepayApplicationPay.setRemark(action);
            staffPrepayApplicationPay.setProductId(paymentToolId);
            staffPrepayApplicationPay.setCompany(paymentChannelProduct);
            staffPrepayApplicationPay.setAccAttr(paymentChannelCode);
            staffPrepayApplicationPay.setID(TxnID);
            staffPrepayApplicationPay.setCertNo(authNum);
            staffPrepayApplicationPay.setReqReserved("58扫一扫收款");
            JSONObject resp = OrderPayDemo.main(staffPrepayApplicationPay,merchantId);
            String retCode = resp.getJSONObject("head").getString("respCode");
//            String retCode = "000000";
            if(retCode.equalsIgnoreCase("000000")){
                String PersonalPID = walletTxn_ReceiverID;
                paymentID = staffPrepayApplicationPay.getOrderCode();
                String personalEwalletType = null;
                BigDecimal txnAmt = new BigDecimal(txnAmount);
                EwalletTxnController ewalletTxnController = new EwalletTxnController();
                mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(staffPrepayApplicationPay.getID());
                mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(staffPrepayApplicationPay.getOrderCode());
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum(staffPrepayApplicationPay.getOrderCode());
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearOrg(walletTxn_ReceiverID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
                mobileEwalletDashboard.setT_mobilePersonalewallet_PaymentVersion(paymentVendor);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PaymentTool(paymentToolId);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdType(staffPrepayApplicationPay.getCertType());
                mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnAmount(txnAmt.setScale(2,BigDecimal.ROUND_DOWN));
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
//              MarsMobileEwalletFind = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn);
                rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,cardAcc,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

                if(rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")){
                    personalEwalletType = "RECEIVERQUERY";
                    String PersonalEwalletPayCat = "Payinput";
                    EwalletController ewalletController = new EwalletController();
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("CASHIN");
                    Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                    BigDecimal t_mobile_receiverTotCNYBalance = (BigDecimal) mobileEwalletDashboardResult1.get("T_mobilePersonalEwallet_ReceiverTotCNYBalance");
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverTotCNYBalance(t_mobile_receiverTotCNYBalance);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Interest(new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_DOWN));
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Paystatus("3rd Party Payment Succ");
                }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")){
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
                }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")){
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
                }

                if (!rsMobileEwalletTxn.isEmpty()){
                    Map<String, Object> retPayerEwalletStatistic = new HashMap<>();
                    Map<String, Object> retReceiverEwalletStatistic = new HashMap<>();
                    //Renew PersonalEwallet Statistic
                    if(walletTxn_PayerPID != null) {
                        PersonalPID = walletTxn_PayerPID;
                        retPayerEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                        Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                    }
                    if( walletTxn_ReceiverID != null) {
                        PersonalPID = walletTxn_ReceiverID;
                        retReceiverEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                        Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                    }
                    if(retPayerEwalletStatistic.get("SQL") != null || retReceiverEwalletStatistic.get("SQL") != null) { // successful payment complete done
                        mobileEwalletDashboard.setRet("0");
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
            }
            rs.put("errMsg","3rd party payment vendor pay failed");
            rs.put("PaymentErrCode",retCode);
            rs.put("PaymentErrMsg",resp.getJSONObject("head").getString("respMsg")); // debuging comment out
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }

        //支付测试返回
        //http://localhost:8080/sample/oauthController/login?method=QRCode&pid=31011519830805251X
        if(method!=null&&method.equals("QRScanRet")&pid!=null&userName!=null&mode!=null){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            MobileEwalletDashboard ApplicationPay = new MobileEwalletDashboard();
            JSONObject resp = OrderCreateDemo.main(ApplicationPay,merchantId);
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
//coding...
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

            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();  // payment&transaction input

            String PersonalEwalletPayCat = "Payinput";
            Map<String, Object> mobilePayerOriginFindPersonalEwalletResult = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerOriginTotCNYBalance((BigDecimal) mobilePayerOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_PayerOriginTotCNYBalance"));
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(mobilePayerOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_PayerPID").toString());

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
                Map<String, Object>  mobileReceiverOriginFindPersonalEwalletResult = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverOriginTotCNYBalance((BigDecimal) mobileReceiverOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_ReceiverOriginTotCNYBalance"));
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(mobileReceiverOriginFindPersonalEwalletResult.get("T_mobilePersonalEwallet_ReceiverPID").toString());
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
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_PayerPID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,cardAcc,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

            if(rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")){
                personalEwalletType = "RECEIVERQUERY";
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("PAYOUTPUT");
                Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverTotCNYBalance((BigDecimal) mobileEwalletDashboardResult1.get("T_mobilePersonalEwallet_ReceiverOriginTotCNYBalance"));
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")){
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")){
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
            }

            if (!rsMobileEwalletTxn.isEmpty()){
                Map<String, Object> retPayerEwalletStatistic = new HashMap<>();
                Map<String, Object> retReceiverEwalletStatistic = new HashMap<>();
                //Renew PersonalEwallet Statistic
                if(walletTxn_PayerPID != null) {
                    String PersonalPID = null;
                    if(walletTxn_PayerPID == null){
                        PersonalPID = walletTxn_ReceiverID;
                    }
                    retPayerEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if( walletTxn_ReceiverID != null) {
                    String PersonalPID = walletTxn_ReceiverID;
                    retReceiverEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if(retPayerEwalletStatistic.get("SQL") != null || retReceiverEwalletStatistic.get("SQL") != null) {
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

        //http://localhost:8080/sample/oauthController/login?method=58qr-txn-wechatscan&action=transaction&page=mobilepay&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=wechat&txnAmount=0.1&paymentID=$&paymentStatus=&
        //个人付款58qr-wechatscan/alipayscan/unionpayscan ( payee - 58,receiver-wechat )$
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equalsIgnoreCase("58qr-txn-wechatscan")&&action.equalsIgnoreCase("transaction")){
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
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            rs = AlipayTxnOrder.alipayCreateOrder(mobileEwalletDashboard);

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

        //个人付款58scan-wechatqr/aliqr/unionpayqr    ( payee - 58,receiver-wechat )
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

        //http://localhost:8080/sample/oauthController/login?method=scan-shopping-58qr&action=shopping&page=mobilepay&=wechat&walletTxn_ReceiverID=31011519830805251X&paymentChannel=debitcard&shoppingAmount=0.1
        //个人消费  ( payer - 58,receiver representer- GFwechat )
        if( method!=null&&page.equalsIgnoreCase("ewalletpay")&&method.equals("scan-shopping-58qr")&&action.equalsIgnoreCase("shopping")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            MobileEwalletDashboard ApplicationPay = new MobileEwalletDashboard();
            ApplicationPay.setT_mobilePersonalEwallet_ProdType("4");  // set Transaction Limited type
            //verify personal treasury delegation
            Map<String, Object> PersonalTreasuryChk = new HashMap<>();
            String txnCat = "PersonalEwalletShopping";
            //find Treasury prod alias
            BigDecimal txnAmt = new BigDecimal(shoppingAmount).setScale(25,2);
            PersonalTreasuryChk = PersonalValueEst.PersonalTreasuryChk(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletShoppingTxn =  new HashMap<>();
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            paymentID = DemoBase.getOrderCode();
            String txnID = Tool.PayId();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(txnID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
            walletTxn_PayerPID = paymentID;
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(walletTxn_PayerPID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PaymentTool("403");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID("sandpay");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerEwalletID("");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action, txnCat,cardAcc, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
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
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("58scan-shopping-qr")&&action.equalsIgnoreCase("shopping")){
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

        //个人充值  接收node支付宝支付充值结果回调，更新余额和职场指数 
        //https://api.58gongzi.com.cn/sample_war/oauthController/login?method=ewallettopup&action=topup&page=mobilepay&walletTxn_PayerPID=430528198502043837&personalMID=e1e5ce20-5d85-4c7d-a6f2-b174c9760438&paymentID=2021081722001499691409390369&paymentStatus=TRADE_SUCCESS&TopupAmount=1
        //个人钱包充值 http://localhost:8080/sample/oauthController/login?method=ewallettopup&action=topup&page=mobilepay&walletTxn_PayerPID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&topupAmount=100.00&&paymentID=$&paymentStatus=&paymentChannel=&cardAcc=
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("ewallettopup")&&action.equalsIgnoreCase("topup")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            String TxnID= Tool.PayId();
            Map<String, Object> rs = new HashMap<String, Object>();
            Map<String, Object> MarsMobileEwalletFind = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(TxnID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_PayerPID);
            if(paymentChannel!=null) {
                mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID(paymentChannel);
            }else{
                mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID("alipay");
            }
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
            String merchantId = "S2135052";
            String txnCat = null;
            String paymentChannelCode = authNum.substring(0,2).trim();
            String paymentChannelProduct = null,paymentToolId = null;
            txnCat = "PersonalEwalletTopup";
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdType("5");
            String PersonalPID = walletTxn_ReceiverID;
            String personalEwalletType = null;
            BigDecimal txnAmt = new BigDecimal(TopupAmount);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
//              MarsMobileEwalletFind = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn); // check personal evaluate
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,cardAcc,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

            if(rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")){
                personalEwalletType = "RECEIVERQUERY";
                String PersonalEwalletPayCat = "Payinput";
                EwalletController ewalletController = new EwalletController();
                mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("CASHIN");
                Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverTotCNYBalance((BigDecimal) mobileEwalletDashboardResult1.get("T_mobilePersonalEwallet_ReceiverOriginTotCNYBalance"));
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")){
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
            }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")){
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
            }

            if (!rsMobileEwalletTxn.isEmpty()){
                Map<String, Object> retPayerEwalletStatistic = new HashMap<>();
                Map<String, Object> retReceiverEwalletStatistic = new HashMap<>();
                //Renew PersonalEwallet Statistic
                if(walletTxn_PayerPID != null) {
                    PersonalPID = walletTxn_PayerPID;
                    retPayerEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if( walletTxn_ReceiverID != null) {
                    PersonalPID = walletTxn_ReceiverID;
                    retReceiverEwalletStatistic = OverallStatisticRefresh.PersonalEwalletStatisticRefresh(PersonalPID);
                    Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                }
                if(retPayerEwalletStatistic.get("SQL") != null || retReceiverEwalletStatistic.get("SQL") != null) {
                    mobileEwalletDashboard.setRet("0");
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
        }

//bankcard topup sign
        if( method!=null&&page.equalsIgnoreCase("mobilepay")&&method.equals("sandpaysign")&&action.equalsIgnoreCase("cardtopup")){
//           Map<String, Object> sandpaytopupsign = com.qucai.sample.sandfastpay.main.java.cn.com.sand.pay.sandpay.scm.demo.FastPayServlet.doPost(req, resp);
            Map<String, Object> sandpaytopupsign = new HashMap<>();
            return sandpaytopupsign;
        }
        

        //个人提现 cashout target account alipayacc/debitcard/creditcard/wechatacc
            //个人钱包借记卡提现 http://localhost:8080/sample/oauthController/login?method=ewalletcashout&action=cashout&page=mobilehome&cardAcc=6228480031725525211&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&CashoutAmount=1.01&&paymentID=$&paymentStatus=&paymentChannel=debitcard
        //个人钱包信用卡提现 http://localhost:8080/sample/oauthController/login?method=ewalletcashout&action=cashout&page=mobilehome&cardAcc=6258101661184889&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&CashoutAmount=1.01&&paymentID=$&paymentStatus=&paymentChannel=creditcard
        //个人钱包支付宝提现 转账到支付宝内部账户 http://localhost:8080/sample/oauthController/login?method=ewalletcashout&action=cashout&page=mobilehome&cardAcc=2088002082117160&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&CashoutAmount=1.01&&paymentID=$&paymentStatus=&paymentChannel=alipay
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletcashout")&&action.equalsIgnoreCase("cashout")){
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            //verify personal treasury delegation
            Map<String, Object> PersonalTreasuryChk = new HashMap<>();
            String txnCat = "PersonalEwalletCashout";
            //find Treasury prod alias  
            BigDecimal txnAmt = new BigDecimal(CashoutAmount).setScale(2,BigDecimal.ROUND_DOWN);
            PersonalTreasuryChk = PersonalValueEst.PersonalTreasuryChk(action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            String PersonalPID = walletTxn_ReceiverID;
            Map<String,Object> personalTreasuryFindEwalletTxn = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn);
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            Map<String, Object> rsMobileEwalletCashoutTxn =  new HashMap<>();
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            paymentID = DemoBase.getOrderCode();
            String txnID = Tool.PayId();
            switch (paymentChannel){
                case "debitcard" :
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Creditcard(cardAcc);
                    //charge fee calc
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ServiceFee(new BigDecimal("0.01").setScale(2,BigDecimal.ROUND_UP));
                    break;
                case "creditcard" :
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Debitcard(cardAcc);
                    //charge fee calc
                    break;
                case "alipay" :
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum(cardAcc);
                    mobileEwalletDashboard.setT_MobilePersonalewallet_PaymentType(paymentChannel);
                    //charge fee calc
                    break;
                case "wechatpay" :
                    mobileEwalletDashboard.setT_MobilePersonalewallet_PaymentType(paymentChannel);
                    //charge fee calc
                    break;
            }
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(txnID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverName(personalTreasuryFindEwalletTxn.get("t_personal_ewallet_statistic_ApplierName").toString());
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(walletTxn_PayerPID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("58个人币提现");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PaymentTool("403");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID("sandpay");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearNum(paymentID);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerEwalletID("");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action, txnCat, cardAcc,txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            if (rsMobileEwalletTxn.get("SQL").equals("PersonalEwalletHisTxnInsertSucc")) {
                PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                Map<String, Object> retPersonalEwalletCashoutResult = new HashMap<>();
                System.out.println("调用个人钱包提现成功");
                //Cashout to Personal Bank card
                String PaymentSwitch = "shsd";
                StaffPrepayApplicationPayment personalApplicationPay = new StaffPrepayApplicationPayment();
                personalApplicationPay.setAccName(personalTreasuryFindEwalletTxn.get("t_personal_ewallet_statistic_ApplierName").toString());
                personalApplicationPay.setPhone(personalTreasuryFindEwalletTxn.get("t_personal_main_mobile").toString());
                personalApplicationPay.setID(paymentID);
                personalApplicationPay.setOrderCode(Tool.PayId());
                personalApplicationPay.setTranAmt(txnAmt.toString());
                personalApplicationPay.setTranTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString());
                personalApplicationPay.setAccNo(walletTxn_ReceiverID);

                //personalApplicationPay.setTranAmt(t_Txn_TotalPrepayNum.setScale(0,BigDecimal.ROUND_DOWN).toString());
                //Input all Payment Info into it, coding...
                //Coding....
                //3rd Party Org payment call
                //Personal Treasury control - personal txn vol. trigger
                //Overall Treasury Acc. check
                //deposit acc. switch
                switch (paymentChannel) {
                    case "debitcard" :
                    case "creditcard" :
                        personalApplicationPay.setVersion("01");
                        personalApplicationPay.setProductId("00000004");
                        personalApplicationPay.setName("sandpay");
                        personalApplicationPay.setCurrencyCode("156");
                        personalApplicationPay.setCertType("0101");
                        personalApplicationPay.setCertNo(PersonalPID); //Goldman Fuks shareholder pid
                        personalApplicationPay.setAccAttr("0");
                        personalApplicationPay.setAccNo(cardAcc); // Goldman Fuks treasury mgt
                        personalApplicationPay.setRemark("cashout");
                        personalApplicationPay.setAccType("4");
                        personalApplicationPay.setReturnPic("1");
                        personalApplicationPay.setReqReserved("全渠道");
                        retPersonalEwalletCashoutResult = PaymentRoute.PersonalEwalletCashout(PaymentSwitch, personalApplicationPay);
                        if(retPersonalEwalletCashoutResult.get("respCode").equals("0000")){
                            rsMobileEwalletTxn.put("paymmentStatus","Payment Succ");
                            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                            return mobileEwalletDashboardJson;
                        }else{
                            rsMobileEwalletTxn.put("respCode",retPersonalEwalletCashoutResult.get("respCode"));
                            rsMobileEwalletTxn.put("RespMsg",retPersonalEwalletCashoutResult.get("RespMsg"));
                            rsMobileEwalletTxn.put("respDesc",retPersonalEwalletCashoutResult.get("respDesc"));
                        }
                        break;
                    case "alipay" :
                        //alipay channel
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverEwalletID(cardAcc);
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnAmount(txnAmt);
                        Map<String, Object> rs = AlipayTxnOrder.alipayCreateOrder(mobileEwalletDashboard);
                        if(rs.get("Msg").toString().equalsIgnoreCase("alipayInternTransmitSucc")){
                            mobileEwalletDashboard.setRet("0");
                        }else{
                            mobileEwalletDashboard.setRet("-1");
                        }
                        String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                        return mobileEwalletDashboardJson;
                    case "wechat" :
                        //wechat channel
                        break;
                }
                rsMobileEwalletTxn.put("SMSverify",0);
                conn.close();
                mobileEwalletDashboard.setRet("0");
            }else{
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.FAIL, rsMobileEwalletTxn);
            }
        }

        //个人预支支付或预支消费
        //个人钱包预支支付或预支消费 http://localhost:8080/sample/oauthController/login?method=ewalletcashadvance&action=cashadvance&page=mobilehome&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&topupAmount=100.00&&paymentID=$&paymentStatus=&
        if( method!=null&&page.equalsIgnoreCase("mobilehome")&&method.equals("ewalletcashadvance")){
            System.out.print(action); // action=cashadvance , action=cashadvancepay
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
            String txnCat = "PersonalEwalletCashout";
            BigDecimal txnAmt = new BigDecimal(topupAmount);
//            String SMSsendcodecvt = DigestUtils.md5Hex(SMSstrret);
            EwalletTxnController ewalletTxnController = new EwalletTxnController();
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_TotalInterestDays(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCounts(0);
            mobileEwalletDashboard.setT_mobilePersonalEwallet_OverdueDays(0);
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,cardAcc, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            if (rsMobileEwalletTxn.get("UpdatePersonalEwalletSucc").equals("succ")) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                System.out.println("调用个人提现成功");
                rsMobileEwalletTxn.put("SMSverify",0);
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }else{
                conn.close();
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
            rsUserEwalletbnkCard = ewalletController.UpdatePayerPersonalEwalletBindBnkCard(request,response,personalMID,pid,cardAcc);
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
            token.setRememberMe(true);

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
            response.sendRedirect(request.getContextPath() + "/login.html");  // login before
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

    @RequestMapping(value = "staffPrepayApplicationFirmList")
    public String staffPrepayApplicationFirmList(HttpServletResponse response,String t_Ewallet_titleName,
                                                 HttpServletRequest request,Integer platform, Model model) {
        String t_P_UserName = ShiroSessionUtil.getLoginSession().getUserName();
        Map<String, Object> paramSearchMap = new HashMap<String, Object>();
        String t_P_FirmLists = null;
        paramSearchMap.put("t_P_UserName",t_P_UserName);
        model.addAttribute("platform", platform);
        List<PersonalInfo> PersonalInfoFirmList = personalInfoService.findPersonalFirmList(paramSearchMap);
        if(t_Ewallet_titleName != null){
            model.addAttribute("t_Ewallet_titleName",t_Ewallet_titleName);
            return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
        }
        if(PersonalInfoFirmList.size() == 0){
            return "redirect:/OrganizationDashboardController/dashboard";
        }
        else if (PersonalInfoFirmList.size() == 1){
            return "redirect:/StaffPrepayApplicationController/staffPrepayApplicationNew";
        }else{
            model.addAttribute("t_P_FirmLists",PersonalInfoFirmList);
            return  "staffPrepayApplication/staffPrepayApplicationFirms";
        }
    }
}
