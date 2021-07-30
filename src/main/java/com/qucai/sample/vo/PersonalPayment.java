package com.qucai.sample.vo;
/************************************************
 * Personal Ewallet Payment Management
 * Author : Spear Yao
 * Date : 29/07/2021
 ************************************************/

import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
import com.qucai.sample.util.DBConnection;
import com.qucai.sample.util.JsonBizTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.qucai.sample.vo.MobilePersonalEwalletTxnStatistic;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private FinanceProductService financeProductService; //申明一个对象

    private static Map<String,Object> PayerTreasuryRegulatory() {
        System.out.println("PayerTreasuryRegulatory Started");
        Map<String, Object> retRs = new HashMap<>();
        return retRs;
    }

    private static Map<String,Object> ReceiverTreasuryRegulatory() {
        System.out.println("ReceiverTreasuryRegulatory Started");
        Map<String, Object> retRs = new HashMap<>();
        return retRs;
    }

    private static Map<String,Object> PayerEwalletStatisicCheck() {
        System.out.println("PayerEwalletStatisicCheck Started");
        Map<String, Object> retRs = new HashMap<>();
        return retRs;
    };

    private static Map<String,Object> ReceiverEwalletStatisicCheck() {
        System.out.println("ReceiverEwalletStatisicCheck Started");
        Map<String, Object> retRs = new HashMap<>();
        return retRs;
    };
    
    public static Object PersonalTreasuryRegulationCheck(String personalMID, String pid, String realName, String ProdCat, String method,
                                                         String action,String txnCat, BigDecimal txnAmt, String walletTxn_PayerPID, String walletTxn_ReceiverID
    ) throws SQLException {
        Map<String, Object> rs = new HashMap<>();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        Map<String,Object> rsMobileEwalletTxn = new HashMap<>();
        MobilePersonalEwalletTxnStatistic mobilePersonalEwalletTxnStatistic = null;
        System.out.print("Personal Ewallet Transaction Regulatory");
        //get Personal Treasury Controller Info
        ResultSet rsSelect1 = null;
        String sql1 = "select * from t_personal_treasuryctrl where t_personalewallet_treasuryctrlID = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql1);
            ptmt.setString(1, ProdCat);
            rsSelect1 = ptmt.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            rsMobileEwalletTxn.put("SQL-CODE","exception:get Personal Treasury Controller Info failed");
            return rsMobileEwalletTxn;
        } finally {
            if (rsSelect1.next()) {
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTxnLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTotalLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlBeneDailyCntLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneStat(rsSelect1.getString("t_personalewallet_treasuryctrlBeneStat"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayDailyLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlPayDailyCntLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayStat(rsSelect1.getString("t_personalewallet_treasuryctrlPayStat"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupDailyLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTxnLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTotalLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlTopupDailyCntLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupStat(rsSelect1.getString("t_personalewallet_treasuryctrlTopupStat"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutDailyLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTxnLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTxnLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTotalLimit(rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTotalLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyCntLimit(rsSelect1.getInt("t_personalewallet_treasuryctrlCashoutDailyCntLimit"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutStat(rsSelect1.getString("t_personalewallet_treasuryctrlCashoutStat"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlstatus(rsSelect1.getString("t_personalewallet_treasuryctrlstatus"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashbackStat(rsSelect1.getString("t_personalewallet_treasuryctrlCashbackStat"));
            }else {
                rsMobileEwalletTxn.put("retMsg", "Personal Treasury Controller Info not found");
                conn.close();
            }
        }
        //check Payment delegation
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashbackStat().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Cashback is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlstatus().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlCashoutStat().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Cashout is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlBeneStat().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Bene is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlPayStat().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Pay is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(mobilePersonalEwalletTxnStatistic.getPersonalTreasuryctrlTopupStat().equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Topup is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        
        switch (method) {            
            case "58scan-txn-58qr":
                System.out.println("58scan-txn-58qr transit");
                String ewalletTxnType = "c2c 钱包转账";
                BigDecimal txnAmtPayerMinus = txnAmt.negate();
                break;
            case "PersonalEwalletTopup":
                System.out.println("PersonalEwalletTopup transit");
                BigDecimal t_MobileWalletTxn_TopupAmt = txnAmt;
                walletTxn_ReceiverID = walletTxn_PayerPID;
                ewalletTxnType = "c2b 充值";
                break;
            case "PersonalEwalletCashout":
                System.out.println("PersonalEwalletCashout transit");
                txnAmtPayerMinus = txnAmt.negate();
                txnAmt = txnAmtPayerMinus;
                ewalletTxnType = "c2c 提现";
                break;
            case "PersonalEwalletShopping":
                System.out.println("PersonalEwalletShopping transit");
                txnAmtPayerMinus = txnAmt.negate();
                txnAmt = txnAmtPayerMinus;
                ewalletTxnType = "c2b 消费";
                break;
        }
        
        //get Personal Treasury Statistic Info
        ResultSet rsSelect2 = null;
        String sql2 = "select * from t_personal_ewallet_statistics where t_personal_ewallet_statistic_ApplierPID = ? or t_personal_ewallet_statistic_ID = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql2);
            ptmt.setString(1, pid);
            ptmt.setString(2, personalMID);
            rsSelect2 = ptmt.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            rsMobileEwalletTxn.put("SQL-CODE","exception:get Personal Treasury Statistic info failed");
            return rsMobileEwalletTxn;
        } finally {
            if (rsSelect2.next()) {
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlBeneDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayDailyAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayTotalAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlPayDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlPayDailyCnt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlTopupDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutTotalAmt(rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt"));
                mobilePersonalEwalletTxnStatistic.setPersonalTreasuryctrlCashoutDailyCnt(rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt"));
            }else {
                rsMobileEwalletTxn.put("retMsg", "Personal Treasury Statistic info failed not found");
                conn.close();
            }
        }

        conn.close();
        return rsMobileEwalletTxn;
    }

    public static Map<String, Object> PersonalEwalletTxnPayment(String personalMID, String pid, String realName){
        Map<String, Object> retPersonalEwalletTxnPayment = new HashMap<>();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        System.out.print("Personal Ewallet Transaction Regulatory");
        return retPersonalEwalletTxnPayment;
    }
}
