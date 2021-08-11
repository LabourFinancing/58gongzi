package com.qucai.sample.util;
/************************************************
 * Personal Ewallet Payment Management
 * Author : Spear Yao
 * Date : 29/07/2021
 ************************************************/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.controller.EwalletTxnController;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.PayServlet;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobileEwalletTXN;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.AgentPayDemo;
import com.qucai.sample.service.*;
import com.qucai.sample.vo.MobileEwalletTXN;
import com.qucai.sample.vo.MobilePersonalEwalletTxnStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.qucai.sample.service.OrganizationInfoService;

@Controller
public class PersonalPayment {
    
    // 必须把new financeProduct的列进行全面修改, 新建financeProductService
    @Autowired
    private EwalletService ewalletService; //申明一个对象

    @Autowired
    private PersonalMainService personalMainService; //申明一个对象

    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象

    @Autowired
    private OrganizationInfoService organizationInfoService;

    @Autowired
    private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

    @Autowired
    private FinanceProductService financeProductService; //申明一个
    

    public static Object mobilePersonalEwalletTxnStatistic(String personalMID, String pid, String realName, String ProdCat,
                                                         String method, String action, String txnCat, BigDecimal txnAmt,
                                                         String walletTxn_PayerPID, String walletTxn_ReceiverID) throws SQLException {
        //check Payment delegation
        MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        Map<String, Object> rs = new HashMap<>();
        String PersonalPID = walletTxn_PayerPID;
        MobilePersonalEwalletTxnStatistic mobilePersonalEwalletTxnStatistic = (MobilePersonalEwalletTxnStatistic) PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt, conn);

        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlstatus().equalsIgnoreCase("off")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("您的钱包部分功能受限");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }
        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashbackStat().equalsIgnoreCase("off") &&
            method.equalsIgnoreCase("58scan-txn-58qr")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Cashback is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("退款进行中,请耐心等待");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }
        // cashout
        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashoutStat().equalsIgnoreCase("off") &&
            method.equalsIgnoreCase("PersonalEwalletCashout")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Cashout is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("提现进行中,请耐心等待");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }
        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlBeneStat().equalsIgnoreCase("off") &&
            method.equalsIgnoreCase("58scan-txn-58qr")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Bene is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("您当前收款功能受限,详情请咨询客服");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }
        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlPayStat().equalsIgnoreCase("off") &&
            method.equalsIgnoreCase("58scan-txn-58qr")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Pay is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("您当前付款功能受限,详情请咨询客服");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }
        //shopping 
        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlTopupStat().equalsIgnoreCase("off") &&
            method.equalsIgnoreCase("PersonalEwalletShopping")) {
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Topup is Off Status");
            mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("您当前消费功能受限，详情请咨询客服");
            String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
            return mobileEwalletDashboardJson;
        }

        switch (method) {
            case "58scan-txn-58qr":
                System.out.println("58scan-txn-58qr transit");
                String ewalletTxnType = "c2c 钱包转账"; 
                Map<String,Object> PersonalTreasuryFindEwalletTxn = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindEwalletTxn.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Topup is Off Status");
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("转账 钱包余额不足");
                    String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                    return mobileEwalletDashboardJson;
                }
                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindEwalletTxn.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrCode("Personal Treasury control Topup limited");
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("转账 钱包余额不足");
                    String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                    return mobileEwalletDashboardJson;
                }
                BigDecimal txnAmtPayerMinus = txnAmt.negate();
                break;
            case "PersonalEwalletTopup":
                System.out.println("PersonalEwalletTopup transit");
                BigDecimal t_MobileWalletTxn_TopupAmt = txnAmt;
                walletTxn_ReceiverID = walletTxn_PayerPID;
                ewalletTxnType = "c2b 充值";
                //call payment directly and get retrun code
                break;
            case "PersonalEwalletCashout":
                System.out.println("PersonalEwalletCashout transit");
                txnAmtPayerMinus = txnAmt.negate();
                txnAmt = txnAmtPayerMinus;
                ewalletTxnType = "c2c 提现";
                Map<String,Object> PersonalTreasuryFindCashout = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindCashout.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("提现 钱包余额不足");
                    String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                    return mobileEwalletDashboardJson;
                }else{
                    //call payment coding...
                }
                break;
            case "PersonalEwalletShopping":
                System.out.println("PersonalEwalletShopping transit");
                txnAmtPayerMinus = txnAmt.negate();
                txnAmt = txnAmtPayerMinus;
                ewalletTxnType = "c2b 消费";
                Map<String,Object> PersonalTreasuryFindSjopping = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindSjopping.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
                    mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("消费 钱包余额不足");
                    String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                    return mobileEwalletDashboardJson;
                }else{
                    //call payment coding...
                    
                }
                break;
        }
        return mobilePersonalEwalletTxnStatistic;
    }

//    3rd party payment call coding...
    public static Object PersonalEwalletTxnPayment(MobileEwalletTXN ApplicationPay,String action, String txnCat,BigDecimal txnAmt,
                                                                String walletTxn_PayerPID,String walletTxn_ReceiverID,String method,String paymentID,String paymentStatus,Connection conn) throws Exception {
//        Map<String, Object> retPersonalEwalletTxnPayment = new HashMap<>();
        MobileEwalletDashboard mobileEwalletDashboard = new MobileEwalletDashboard();
        Map<String, Object> rs = new HashMap<>();
        String merchantId = "S2135052";
        String PaymentSwitch = "shsd";
        String RCretData = null;
        String remark = null;
        System.out.print("Personal Ewallet Transaction Regulatory");
        Map<String, Object> rsMobileEwalletTxn = new HashMap<String, Object>();
        rsMobileEwalletTxn = EwalletTxnController.addMobileEwalletTxn( action, txnCat,  txnAmt,  walletTxn_PayerPID,
            walletTxn_ReceiverID, method, paymentID, paymentStatus, conn);
        System.out.println("Err txn log shsd:");
        
        if (PaymentSwitch.equals("shsd")){
            ApplicationPay.setT_mobileWalletTxn_ClearOrg(merchantId);
            ApplicationPay.setT_mobileWalletTxn_Vendor("sandpay");
            //payto goldmanfuks sandpay pub account for cashout preparation
            StaffPrepayApplicationPayment staffPrepayApplicationPay = new StaffPrepayApplicationPayment();
//            staffPrepayApplicationPay.set   coding...
            JSONObject obj = AgentPayDemo.main(staffPrepayApplicationPay,merchantId);  // sandpay
            RCretData = (String) obj.get("respCode"); //  sandpay branch
            remark = (String) obj.get("respDesc"); //  sandpay branch
            if(RCretData.equals("4001")){
                ApplicationPay.setT_mobileWalletTxn_Paystatus(RCretData);
                ApplicationPay.setT_mobileWalletTxn_Txt3("Other");
                ApplicationPay.setT_mobileWalletTxn_Txt4(remark);
                ApplicationPay.setT_mobileWalletTxn_ClearOrg(merchantId);
//                staffPrepayApplicationService.insertPayment(ApplicationPay);    // call insert Transaction
                System.out.println(ApplicationPay.getT_mobileWalletTxn_Num());
                mobileEwalletDashboard.setT_mobilePersonalEwallet_ErrMsg("您当前消费功能受限，详情请咨询客服");
                String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
                return mobileEwalletDashboardJson;
            }
        }
        String mobileEwalletDashboardJson = JsonTool.genByFastJson(mobileEwalletDashboard);
        return mobileEwalletDashboardJson;
    }
    
    public static Object PaymentStatus(){
        System.out.println();
        return null;
    }
}
