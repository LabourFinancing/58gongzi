//package com.qucai.sample.util;
///************************************************
// * Personal Ewallet Payment Management
// * Author : Spear Yao
// * Date : 29/07/2021
// ************************************************/
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.PayServlet;
//import com.qucai.sample.entity.StaffPrepayApplicationPayment;
//import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.AgentPayDemo;
//import com.qucai.sample.service.*;
//import com.qucai.sample.vo.MobilePersonalEwalletTxnStatistic;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class PersonalPaymentBkp {
//    
//    // 必须把new financeProduct的列进行全面修改, 新建financeProductService
//    @Autowired
//    private EwalletService ewalletService; //申明一个对象
//
//    @Autowired
//    private PersonalMainService personalMainService; //申明一个对象
//
//    @Autowired
//    private PersonalInfoService personalInfoService; //申明一个对象
//
//    @Autowired
//    private OrganizationInfoService organizationInfoService;
//
//    @Autowired
//    private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
//
//    @Autowired
//    private FinanceProductService financeProductService; //申明一个
//
//    
//
//
//    public static Map<String, Object> ReceiverEwalletStatisicCheck() {
//        System.out.println("ReceiverEwalletStatisicCheck Started");
//        Map<String, Object> retRs = new HashMap<>();
//        return retRs;
//    }
//
//    public static MobilePersonalEwalletTxnStatistic PersonalTreasuryRegulationCheck(String personalMID, String pid, String realName, String ProdCat,
//                                                         String method, String action, String txnCat, BigDecimal txnAmt,
//                                                         String walletTxn_PayerPID, String walletTxn_ReceiverID) throws SQLException {
//        //check Payment delegation
//        DBConnection dao = new DBConnection();
//        Connection conn = dao.getConnection();
//        Map<String, Object> rs = new HashMap<>();
//        String PersonalPID = walletTxn_PayerPID;
//        MobilePersonalEwalletTxnStatistic mobilePersonalEwalletTxnStatistic = (MobilePersonalEwalletTxnStatistic) PersonalValueEst.PersonalTreasuryFind(action,PersonalPID,txnAmt, conn);
//
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlstatus().equalsIgnoreCase("off")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashbackStat().equalsIgnoreCase("off") &&
//            method.equalsIgnoreCase("58scan-txn-58qr")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control Cashback is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//        // cashout
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashoutStat().equalsIgnoreCase("off") &&
//            method.equalsIgnoreCase("PersonalEwalletCashout")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control Cashout is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlBeneStat().equalsIgnoreCase("off") &&
//            method.equalsIgnoreCase("58scan-txn-58qr")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control Bene is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlPayStat().equalsIgnoreCase("off") &&
//            method.equalsIgnoreCase("58scan-txn-58qr")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control Pay is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//        //shopping 
//        if (mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlTopupStat().equalsIgnoreCase("off") &&
//            method.equalsIgnoreCase("PersonalEwalletShopping")) {
//            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury control Topup is Off Status");
//            return mobilePersonalEwalletTxnStatistic;
//        }
//
//        switch (method) {
//            case "58scan-txn-58qr":
//                System.out.println("58scan-txn-58qr transit");
//                String ewalletTxnType = "c2c 钱包转账"; 
//                Map<String,Object> PersonalTreasuryFindEwalletTxn = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
//                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindEwalletTxn.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
//                    mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("out-of-balance");
//                    return mobilePersonalEwalletTxnStatistic;
//                }
//                BigDecimal txnAmtPayerMinus = txnAmt.negate();
//                break;
//            case "PersonalEwalletTopup":
//                System.out.println("PersonalEwalletTopup transit");
//                BigDecimal t_MobileWalletTxn_TopupAmt = txnAmt;
//                walletTxn_ReceiverID = walletTxn_PayerPID;
//                ewalletTxnType = "c2b 充值";
//                //call payment directly and get retrun code
//                break;
//            case "PersonalEwalletCashout":
//                System.out.println("PersonalEwalletCashout transit");
//                txnAmtPayerMinus = txnAmt.negate();
//                txnAmt = txnAmtPayerMinus;
//                ewalletTxnType = "c2c 提现";
//                Map<String,Object> PersonalTreasuryFindCashout = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
//                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindCashout.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
//                    mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("out-of-balance");
//                    return mobilePersonalEwalletTxnStatistic;
//                }else{
//                    //call payment coding...
//                    
//                }
//                break;
//            case "PersonalEwalletShopping":
//                System.out.println("PersonalEwalletShopping transit");
//                txnAmtPayerMinus = txnAmt.negate();
//                txnAmt = txnAmtPayerMinus;
//                ewalletTxnType = "c2b 消费";
//                Map<String,Object> PersonalTreasuryFindSjopping = PersonalValueEst.PersonalTreasuryFind( action, PersonalPID, txnAmt,  conn);
//                if(txnAmt.compareTo((BigDecimal) PersonalTreasuryFindSjopping.get("t_personalewallet_TotCNYBalance")) == 1){  // pay amount bigger than ewallet CNY balace
//                    mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("out-of-balance");
//                    return mobilePersonalEwalletTxnStatistic;
//                }else{
//                    //call payment coding...
//                    
//                }
//                break;
//        }
//        return mobilePersonalEwalletTxnStatistic;
//    }
//
////    3rd party payment call coding...
//    public static Map<String, Object> PersonalEwalletTxnPayment(StaffPrepayApplicationPayment ApplicationPay) throws Exception {
//        Map<String, Object> retPersonalEwalletTxnPayment = new HashMap<>();
//        Map<String, Object> rs = new HashMap<>();
//        DBConnection dao = new DBConnection();
//        Connection conn = dao.getConnection();
//        String merchantId = "S2135052";
//        String PaymentSwitch = "shsd";
//        String RCretData = null;
//        String remark = null;
//        System.out.print("Personal Ewallet Transaction Regulatory");
//        if (PaymentSwitch.equals("shsd")){
//
//            ApplicationPay.setReqReserved(merchantId);
//            ApplicationPay.setVersion("sandpay");
//            //payto goldmanfuks sandpay pub account for cashout preparation
//            JSONObject obj = AgentPayDemo.main(ApplicationPay,merchantId);  // sandpay
//
//            RCretData = (String) obj.get("respCode"); //  sandpay branch
//            remark = (String) obj.get("respDesc"); //  sandpay branch
//            if(RCretData.equals("4001")){
//                ApplicationPay.setRCcode(RCretData);
//                ApplicationPay.setReturnPic("Other");
//                ApplicationPay.setRemark(remark);
//                ApplicationPay.setCompany(merchantId);
////                staffPrepayApplicationService.insertPayment(ApplicationPay);    // call insert Transaction
//                System.out.println("Err txn log shsd:");
//                System.out.println(ApplicationPay.getOrderCode());
//                return retPersonalEwalletTxnPayment;
//            }
//        }else if (PaymentSwitch.equals("shdy")){
//            ApplicationPay.setCompany(merchantId);
//            ApplicationPay.setVersion("Chinaebi");
//            String retData = PayServlet.main(ApplicationPay,merchantId);  // Chinaebipay
//            JSONObject obj = (JSONObject) JSON.parse(retData);
//            RCretData = (String) obj.get("transState"); //  Chinaebipay branch
//            String RespMsg = (String) obj.get("rspCode"); //  Chinaebipay branch
//            remark = (String) obj.get("rspMessage");
//            if(RespMsg.equalsIgnoreCase("ACM20048")){
//                ApplicationPay.setRCcode(RCretData);
//                ApplicationPay.setReturnPic("Other");
//                ApplicationPay.setRemark(remark);
//                ApplicationPay.setCompany(merchantId);
////                staffPrepayApplicationService.insertPayment(ApplicationPay);   // call insert Transaction
//                System.out.println("Err txn log shdy:");
//                System.out.println(ApplicationPay.getOrderCode());
//                return retPersonalEwalletTxnPayment;
//            }
//        }
//        return retPersonalEwalletTxnPayment;
//    }
//
//    private static Map<String, Object> ReceiverTreasuryRegulatory() {
//        System.out.println("ReceiverTreasuryRegulatory Started");
//        Map<String, Object> retRs = new HashMap<>();
//        return retRs;
//    }
//    
////
////    private static MobilePersonalEwalletTxnStatistic PayerTreasuryRegulatory(String personalMID, String pid, String realName, String ProdCat, String method, String action, String txnCat,
////                                                  BigDecimal txnAmt, String walletTxn_payerPID, String walletTxn_receiverID,Connection conn)
////        throws SQLException {
////        System.out.println("PayerTreasuryRegulatory Started");
////        Map<String, Object> retRs = new HashMap<>();
////        MobilePersonalEwalletTxnStatistic mobilePersonalEwalletTxnStatistic = null;
////        System.out.print("Personal Ewallet Transaction Regulatory");
////        //get Personal Treasury Controller Info
////        ResultSet rsSelect1 = null;
////        String sql1 = "select * from t_personal_treasuryctrl where t_personalewallet_treasuryctrlID = ?";
////        try {
////            PreparedStatement ptmt = conn.prepareStatement(sql1);
////            ptmt.setString(1, ProdCat);
////            rsSelect1 = ptmt.executeQuery();
////        } catch (SQLException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("exception:get Personal Treasury Controller Info failed");
////            return mobilePersonalEwalletTxnStatistic;
////        } finally {
////            if (rsSelect1.next()) {
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTxnLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTotalLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlBeneDailyCntLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneStat(rsSelect1.getString("t_personalewallet_treasuryctrlBeneStat"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayDailyLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlPayDailyCntLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayStat(rsSelect1.getString("t_personalewallet_treasuryctrlPayStat"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupDailyLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTxnLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTotalLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlTopupDailyCntLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupStat(rsSelect1.getString("t_personalewallet_treasuryctrlTopupStat"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutDailyLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTxnLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTotalLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlCashoutDailyCntLimit"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutStat(rsSelect1.getString("t_personalewallet_treasuryctrlCashoutStat"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlstatus(rsSelect1.getString("t_personalewallet_treasuryctrlstatus"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashbackStat(rsSelect1.getString("t_personalewallet_treasuryctrlCashbackStat"));
////            } else {
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury Controller Info not found");
////                conn.close();
////            }
////            return  mobilePersonalEwalletTxnStatistic;
////        }
////    }
////    private static MobilePersonalEwalletTxnStatistic PayerEwalletStatisicCheck(String personalMID, String pid, String realName, String ProdCat, String method, String action,
////                                                                 String txnCat, BigDecimal txnAmt, String walletTxn_PayerPID, String walletTxn_ReceiverID, Connection conn
////    ) throws SQLException {
////        System.out.println("PayerEwalletStatisicCheck Started");
////        Map<String, Object> retRs = new HashMap<>();
////        MobilePersonalEwalletTxnStatistic mobilePersonalEwalletTxnStatistic = null;
////        //get Personal Treasury Statistic Info
////        ResultSet rsSelect2 = null;
////        String sql2 = "select * from t_personal_ewallet_statistics where t_personal_ewallet_statistic_ApplierPID = ? or t_personal_ewallet_statistic_ID = ?";
////        try {
////            PreparedStatement ptmt = conn.prepareStatement(sql2);
////            ptmt.setString(1, pid);
////            ptmt.setString(2, personalMID);
////            rsSelect2 = ptmt.executeQuery();
////        } catch (SQLException e) {
////            // TODO Auto-generated catch block
////            e.printStackTrace();
////            mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("exception:get Personal Treasury Statistic info failed");
////            return mobilePersonalEwalletTxnStatistic;
////        } finally {
////            if (rsSelect2.next()) {
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayDailyAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayTotalAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlPayDailyCnt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt"));
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt"));
////            } else {
////                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlRemark("Personal Treasury Statistic info failed not found");
////                conn.close();
////            }
////        }
////
////        conn.close();
////        return mobilePersonalEwalletTxnStatistic;
////    }
//}
