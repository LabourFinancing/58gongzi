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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller.AlipayFundTransOrderQueryController;
import com.qucai.sample.alipayDemo_java.src.java.com.alipay.demo.controller.AlipayTxnOrder;
import com.qucai.sample.entity.*;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.DemoBase;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderCreateDemo;
import com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo.OrderPayDemo;
import com.qucai.sample.service.*;
import com.qucai.sample.util.*;
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
                        String personalMID,String walletTxn_PayerPID,String walletTxn_ReceiverID,String txnDetail,String TopupAmount,
                        String paymentChannel,String shoppingAmount, String topupAmount,String CashoutAmount,String realName,String retPaymentMsg,
                        String userName, String pid, String password, String page,String action,String cardAcc,
                        String mode, String gid, String method, String phone, String host,String paymentID,String paymentStatus,String authNum,
                        String SMSsendcode, String SMSstrret, String type, String API) throws Exception {

        CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
        token.setUsername(userName);

        //http://localhost:8080/sample/oauthController/login?method=paymentreturn&retPaymentMsg=%7B%22head%22%3A%7B%22version%22%3A%221.0%22%2C%22respTime%22%3A%2220210818091627%22%2C%22respCode%22%3A%22000000%22%2C%22respMsg%22%3A%22%E6%88%90%E5%8A%9F%22%7D%2C%22body%22%3A%7B%22mid%22%3A%22S2135052%22%2C%22orderCode%22%3A%222021081809161366%22%2C%22tradeNo%22%3A%222021081809161366%22%2C%22clearDate%22%3A%2220210818%22%2C%22totalAmount%22%3A%22000000000100%22%2C%22orderStatus%22%3A%221%22%2C%22payTime%22%3A%2220210818091626%22%2C%22settleAmount%22%3A%22000000000100%22%2C%22buyerPayAmount%22%3A%22000000000100%22%2C%22discAmount%22%3A%22000000000000%22%2C%22txnCompleteTime%22%3A%2220210818091626%22%2C%22payOrderCode%22%3A%2220210818001319800000000000053602%22%2C%22accLogonNo%22%3A%22spe***%40163.com%22%2C%22accNo%22%3A%22%22%2C%22midFee%22%3A%22000000000000%22%2C%22extraFee%22%3A%22000000000000%22%2C%22specialFee%22%3A%22000000000000%22%2C%22plMidFee%22%3A%22000000000000%22%2C%22bankserial%22%3A%22702021081822001417161416028687%22%2C%22externalProductCode%22%3A%2200000006%22%2C%22cardNo%22%3A%22%22%2C%22creditFlag%22%3A%22%22%2C%22bid%22%3A%22%22%2C%22benefitAmount%22%3A%22000000000000%22%2C%22remittanceCode%22%3A%22%22%2C%22extend%22%3A%22com.qucai.sample.vo.MobileEwalletDashboard%4042320cbc%22%7D%7D&
        if(method!=null&&method.equals("paymentreturn")){
            //check payment unique
            DBConnection dao = new DBConnection();
            Connection conn = dao.getConnection();
            System.out.print(retPaymentMsg);
            Map<String, Object> rs = new HashMap<String, Object>();
            String json = retPaymentMsg;
            String returnTxnDetail, returnPaymentCode = null,returnPaymentMsg = null,returnPaymentOrderNum = null,returnPaymentCompleteTime = null;
            JSONObject jsonData = null, returnTxnDetailJson = null;
            try {
                jsonData = (JSONObject) JSON.parse(retPaymentMsg);
            }catch (Exception e){
                e.printStackTrace();
                rs.put("JSONObject parse err","Payment return String JSON parse error");
                return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
            }finally {
                returnPaymentCode = jsonData.getJSONObject("head").getString("respCode");
                returnPaymentMsg =  jsonData.getJSONObject("head").getString("respMsg");
                returnPaymentOrderNum = jsonData.getJSONObject("body").getString("orderCode");
                returnPaymentCompleteTime = jsonData.getJSONObject("body").getString("txnCompleteTime");
                returnTxnDetail = URLDecoder.decode(jsonData.getJSONObject("body").getString("extend"));
            }

            /*
            call 58gongzi payment txn add
             */
            if(retPaymentMsg != null && returnPaymentCode.equals("000000")) {
                Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
                String txnAmt1= null,ActionTime=null, OrderCode = null,PayStatus=null ,PersonalPID = null, txnCat = null,txnID = null;
                JSONObject jsonDataRetTxnDetails = null;
                Date TxnStartDate=null,TxnEndDate=null;
                try {
                    jsonDataRetTxnDetails = JSONObject.parseObject(returnTxnDetail);
                }catch (Exception e){
                    e.printStackTrace();
                    rs.put("JSONObject parse err","Payment return String JSON parse error");
                    return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
                }finally {
                    System.out.print(jsonDataRetTxnDetails);
                    txnAmt1 = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_TxnAmount"); //t_mobileWalletTxn_TotTxnAmount
                    PersonalPID = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_ReceiverPID"); //t_mobileWalletTxn_ReceiverPID
                    paymentID = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_OrderCode"); //t_mobileWalletTxn_Num
                    txnCat = jsonDataRetTxnDetails.getString("t_mobilePersonalEwallet_TxnCat");; //t_mobileWalletTxn_TxnCat
                }
                txnCat = "PersonalEwalletReceive";   // transperency code
                switch (txnCat){
                    case "PersonalEwalletReceive":
                        walletTxn_ReceiverID = jsonDataRetTxnDetails.getString("t_mobileWalletTxn_ReceiverPID");
                        break;
                    case "PersonalEwalletShopping":
                        walletTxn_PayerPID = jsonDataRetTxnDetails.getString("t_mobileWalletTxn_PayerPID");
                        break;
                    case "PersonalEwalletCashout":
                        walletTxn_PayerPID = jsonDataRetTxnDetails.getString("t_mobileWalletTxn_PayerPID");
                        break;
                }
                BigDecimal txnAmt = new BigDecimal(txnAmt1);
                String paymentVendor = "sandpay";   // payment vendor switch
                EwalletTxnController ewalletTxnController = new EwalletTxnController();
                MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard(); // payment & transaction input
                mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnID(txnID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_OrderCode(paymentID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(PersonalPID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp(action);
                mobileEwalletDashboard.setT_MobilePersonalewallet_PaymentVersion(paymentVendor);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID(action);
    // coding   MarsMobileEwalletFind = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn); 
                rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
                MobileEwalletDashboard mobileEwalletDashboardretpayment = new MobileEwalletDashboard();
                if(rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")){
                    String personalEwalletType = "RECEIVERQUERY";
                    String PersonalEwalletPayCat = "Payinput";
                    mobileEwalletDashboardretpayment.setT_mobilePersonalEwallet_PayCat("PAYOUTPUT");
//                    Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn); //check balance
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnAmount(new BigDecimal(txnAmt1).setScale(2,BigDecimal.ROUND_DOWN));
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Interest(new BigDecimal(0.01).setScale(2,BigDecimal.ROUND_DOWN));
                    if(txnCat.equalsIgnoreCase("PersonalEwalletReceive")) {
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                    }else if(txnCat.equalsIgnoreCase("PersonalEwalletCashout") || txnCat.equalsIgnoreCase("PersonalEwalletShopping")) {
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_PayerPID(walletTxn_PayerPID);
                        mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_PayerPID);
                    }
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_Paystatus(returnPaymentCode);
                    mobileEwalletDashboardretpayment.setT_mobilePersonalEwallet_Paystatus("3rd Party Payment Succ");
                }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEUCC")){
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_bkp("ReceiverEwallet Bene account hanging");
                }else if(rsMobileEwalletTxn.get("SQL").equals("SQL-PAYEREWALLETUPDATEFAIL")){
                    mobileEwalletDashboardretpayment.setT_mobilePersonalEwallet_bkp("PayerEwallet Payer account hanging");
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
                    conn.close();
                    return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
                }
            }else {
                conn.close();
                rs.put("errMsg", "3rd party payment vendor pay failed");
                return JsonBizTool.genJson(ExRetEnum.FAIL, rs);
            }
        }

        //http://localhost:8080/sample/oauthController/login?method=getUserInfo - alipay
        if(method!=null&&method.equals("getUserInfo")){
            Map<String, Object> rs = new HashMap<String, Object>();
            StaffPrepayApplicationPayment staffPrepayApplicationPay = null;
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
//            JSONObject resp = OrderCreateDemo.main(staffPrepayApplicationPay,merchantId);
            AlipayFundTransOrderQueryModel alipayModel = null;
            ModelMap modelMap = null;
            rs = AlipayTxnOrder.alipayCreateOrder(mobileEwalletDashboard, null);
            JSONObject resp = AlipayFundTransOrderQueryController.doPost(request,response,null, null);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, resp);
        }

        //58生成二维码收款 等待付款
        //http://localhost:8080/sample/oauthController/login?method=get58qrcode&action=58QRewalletreceive&walletTxn_ReceiverID=430528198502043837&txnAmount=1&paymentChannel=alipay
        if(method!=null&&method.equals("get58qrcode")){
            Map<String, Object> rs = new HashMap<String, Object>();
            String merchantId = "S2135052";
            String txnCat = null;
            MobileEwalletDashboard ApplicationPay = new MobileEwalletDashboard();
            BigDecimal txnAmt = new BigDecimal(txnAmount);
            paymentID = DemoBase.getOrderCode();
            ApplicationPay.setT_mobilePersonalEwallet_OrderCode(paymentID);
            ApplicationPay.setT_mobilePersonalEwallet_TxnAmount(txnAmt);
            ApplicationPay.setT_mobilePersonalEwallet_treasuryID(paymentChannel);
            switch (action) {
                case "58QRewalletshopping":
                    txnCat = "PersonalEwalletShopping";
                    ApplicationPay.setT_mobilePersonalEwallet_TxnCat("PersonalEwalletShopping");  // transperency code
                    ApplicationPay.setT_MobilePersonalewallet_PaymentType("4");
                    break;
                default:
                    txnCat = "PersonalEwalletReceive";
                    ApplicationPay.setT_mobilePersonalEwallet_TxnCat("PersonalEwalletReceive");  // transperency code
                    ApplicationPay.setT_MobilePersonalewallet_PaymentType("5");
                    break;
            }
            
            String PersonalPID = null;
            if(walletTxn_PayerPID == null){
                PersonalPID = walletTxn_ReceiverID;
            }else{
                PersonalPID = walletTxn_PayerPID;
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
        //http://localhost:8080/sample/oauthController/login?method=authcodepay&txnAmount=1&walletTxn_ReceiverID=31011519830805251X&authNum=282719833313574185&action=transactionreceive
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
            staffPrepayApplicationPay.setOrderCode(Tool.PayId()); // debug using
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
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ClearOrg(paymentChannel);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ApplierPID(walletTxn_ReceiverID);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverPID(walletTxn_ReceiverID);
                mobileEwalletDashboard.setT_MobilePersonalewallet_PaymentVersion(paymentVendor);
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ProdType(staffPrepayApplicationPay.getCertType());
//              MarsMobileEwalletFind = PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt,conn);
                rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

                if(rsMobileEwalletTxn.get("SQL").equals("SQL-PERSONALEWALLETTXNHISTORY-SUCC")){
                    personalEwalletType = "RECEIVERQUERY";
                    String PersonalEwalletPayCat = "Payinput";
                    EwalletController ewalletController = new EwalletController();
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_PayCat("CASHIN");
                    Map<String, Object> mobileEwalletDashboardResult1 = ewalletController.findPersonalEwallet(PersonalEwalletPayCat,walletTxn_PayerPID,walletTxn_ReceiverID,personalEwalletType,conn);
                    BigDecimal t_mobile_receiverTotCNYBalance = (BigDecimal) mobileEwalletDashboardResult1.get("T_mobilePersonalEwallet_ReceiverTotCNYBalance");
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ReceiverTotCNYBalance(t_mobile_receiverTotCNYBalance);
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_TxnAmount(txnAmt.setScale(2,BigDecimal.ROUND_DOWN));
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
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

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
            rs = AlipayTxnOrder.alipayCreateOrder(mobileEwalletDashboard,null);

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
        
        //http://localhost:8080/sample/oauthController/login?method=scan-shopping-58qr&action=shopping&page=mobilepay&walletTxn_PayerPID=wechat&walletTxn_ReceiverID=31011519830805251X&paymentChannel=debitcardNum&shoppingAmount=0.1
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
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
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
        //个人钱包充值 http://localhost:8080/sample/oauthController/login?method=ewallettopup&action=topup&page=mobilepay&walletTxn_PayerPID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&topupAmount=100.00&&paymentID=$&paymentStatus=&cardAcc=
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
                mobileEwalletDashboard.setT_mobilePersonalEwallet_treasuryID("alipay");
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
                    rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat,txnAmt,walletTxn_PayerPID,walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);

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


            //个人提现 cashout target account alipayacc/debitcard/creditcard/wechatacc
        //个人钱包提现 http://localhost:8080/sample/oauthController/login?method=ewalletcashout&action=debitcard&page=mobilehome&walletTxn_PayerPID=31011519830805251X&walletTxn_ReceiverID=31011519830805251X&personalMID=7d72156f-3bd8-4e03-a2d0-debcfaab8475&CashoutAmount=100.00&&paymentID=$&paymentStatus=&paymentchannel=debitcard
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
            MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action, txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
            if (rsMobileEwalletTxn.get("UpdatePersonalEwalletSucc").equals("succ")) {
                String PersonalPID = walletTxn_PayerPID;
                Map<String, Object> retPersonalWorthRenew = OverallStatisticRefresh.PersonalEwalletWorthRenew(PersonalPID);
                Map<String, Object> retPersonalEwalletCashoutResult = new HashMap<>();
                System.out.println("调用个人钱包提现成功");
                //Cashout to Personal Bank card
                String PaymentSwitch = "shsd";
                StaffPrepayApplicationPayment personalApplicationPay = null;
                personalApplicationPay.setAccName((String) PersonalTreasuryChk.get("PersonalName"));
                personalApplicationPay.setPhone((String) PersonalTreasuryChk.get("PersonalMobile"));
                personalApplicationPay.setID(paymentID);
                personalApplicationPay.setOrderCode(Tool.PayId());
                personalApplicationPay.setTranAmt(txnAmount);
                personalApplicationPay.setTranTime(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()).toString());
                
                //personalApplicationPay.setTranAmt(t_Txn_TotalPrepayNum.setScale(0,BigDecimal.ROUND_UP).toString());
                //Input all Payment Info into it, coding...
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
                        personalApplicationPay.setAccName(personalApplicationPay.getAccName());
                        personalApplicationPay.setRemark("cashout");
                        personalApplicationPay.setAccType("4");
                        personalApplicationPay.setReturnPic("1");
                        personalApplicationPay.setReqReserved("全渠道");
                        retPersonalEwalletCashoutResult = PaymentCall.PersonalEwalletCashout(PaymentSwitch, personalApplicationPay);
                        if(retPersonalEwalletCashoutResult.get("errCode").equals(null)){
                            rsMobileEwalletTxn.put("paymmentStatus","Payment Succ");
                        }else{
                            rsMobileEwalletTxn.put("respCode",retPersonalEwalletCashoutResult.get("respCode"));
                            rsMobileEwalletTxn.put("RespMsg",retPersonalEwalletCashoutResult.get("RespMsg"));
                            rsMobileEwalletTxn.put("respDesc",retPersonalEwalletCashoutResult.get("respDesc"));
                        }
                        break;
                    case "alipay" :
                        //alipay channel
                        break;
                    case "wechat" :
                        //wechat channel
                        break;
                }
                rsMobileEwalletTxn.put("SMSverify",0);
                conn.close();
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
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
            rsMobileEwalletTxn = ewalletTxnController.addMobileEwalletTxn(mobileEwalletDashboard,action,txnCat, txnAmt, walletTxn_PayerPID, walletTxn_ReceiverID,method,paymentID,paymentStatus,conn);
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
