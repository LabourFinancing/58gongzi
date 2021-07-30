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

    private Object OrganizationInfo;
    
    public static Object PersonalTreasuryRegulationCheck(String personalMID, String pid, String realName, String ProdCat, String method,
                                                         String action,String txnCat, BigDecimal txnAmt, String walletTxn_PayerPID, String walletTxn_ReceiverID
    ) throws SQLException {
        BigDecimal personalTreasuryctrlBeneDailyLimit = null;
        BigDecimal personalTreasuryctrlBeneTxnLimit = null;
        BigDecimal personalTreasuryctrlBeneTotalLimit = null;
        Integer personalTreasuryctrlBeneDailyCntLimit = null;
        String personalTreasuryctrlBeneStat = null;
        BigDecimal personalTreasuryctrlPayDailyLimit = null;
        BigDecimal personalTreasuryctrlPayTxnLimit = null;
        BigDecimal personalTreasuryctrlPayTotalLimit = null;
        Integer personalTreasuryctrlPayDailyCntLimit = null;
        String personalTreasuryctrlPayStat = null;
        BigDecimal personalTreasuryctrlTopupDailyLimit = null;
        BigDecimal personalTreasuryctrlTopupTxnLimit= null;
        BigDecimal personalTreasuryctrlTopupTotalLimit = null;
        Integer personalTreasuryctrlTopupDailyCntLimit = null;
        String personalTreasuryctrlTopupStat= null;
        BigDecimal personalTreasuryctrlCashoutDailyLimit= null;
        BigDecimal personalTreasuryctrlCashoutTxnLimit = null;
        BigDecimal personalTreasuryctrlCashoutTotalLimit = null;
        Integer personalTreasuryctrlCashoutDailyCntLimit= null;
        String personalTreasuryctrlCashoutStat= null;
        String personalTreasuryctrlstatus = null;
        String personalTreasuryctrlCashbackStat= null;
        
        Map<String, Object> rs = new HashMap<>();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        Map<String,Object> rsMobileEwalletTxn = new HashMap<>();
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
                 personalTreasuryctrlBeneDailyLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyLimit");
                 personalTreasuryctrlBeneTxnLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTxnLimit");
                 personalTreasuryctrlBeneTotalLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlBeneTotalLimit");
                 personalTreasuryctrlBeneDailyCntLimit = rsSelect1.getInt("t_personalewallet_treasuryctrlBeneDailyCntLimit");
                 personalTreasuryctrlBeneStat = rsSelect1.getString("t_personalewallet_treasuryctrlBeneStat");
                 personalTreasuryctrlPayDailyLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayDailyLimit");
                 personalTreasuryctrlPayTxnLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit");
                 personalTreasuryctrlPayTotalLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit");
                 personalTreasuryctrlPayDailyCntLimit = rsSelect1.getInt("t_personalewallet_treasuryctrlPayDailyCntLimit");
                 personalTreasuryctrlPayStat = rsSelect1.getString("t_personalewallet_treasuryctrlPayStat");
                 personalTreasuryctrlTopupDailyLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupDailyLimit");
                 personalTreasuryctrlTopupTxnLimit= rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTxnLimit");
                 personalTreasuryctrlTopupTotalLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlTopupTotalLimit");
                 personalTreasuryctrlTopupDailyCntLimit = rsSelect1.getInt("t_personalewallet_treasuryctrlTopupDailyCntLimit");
                 personalTreasuryctrlTopupStat= rsSelect1.getString("t_personalewallet_treasuryctrlTopupStat");
                 personalTreasuryctrlCashoutDailyLimit= rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutDailyLimit");
                 personalTreasuryctrlCashoutTxnLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTxnLimit");
                 personalTreasuryctrlCashoutTotalLimit = rsSelect1.getBigDecimal("t_personalewallet_treasuryctrlCashoutTotalLimit");
                 personalTreasuryctrlCashoutDailyCntLimit= rsSelect1.getInt("t_personalewallet_treasuryctrlCashoutDailyCntLimit");
                 personalTreasuryctrlCashoutStat= rsSelect1.getString("t_personalewallet_treasuryctrlCashoutStat");
                 personalTreasuryctrlstatus = rsSelect1.getString("t_personalewallet_treasuryctrlstatus");
                 personalTreasuryctrlCashbackStat= rsSelect1.getString("t_personalewallet_treasuryctrlCashbackStat");
            }else {
                rsMobileEwalletTxn.put("retMsg", "Personal Treasury Controller Info not found");
                conn.close();
            }
        }
        //check Payment delegation
        if(personalTreasuryctrlCashbackStat.equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Cashback is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(personalTreasuryctrlstatus.equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(personalTreasuryctrlCashoutStat.equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Cashout is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(personalTreasuryctrlBeneStat.equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Bene is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(personalTreasuryctrlPayStat.equalsIgnoreCase("off")){
            rs.put("alertMsg","Personal Treasury control Pay is Off Status");
            return JsonBizTool.genJson(ExRetEnum.FAIL,rs);
        }
        if(personalTreasuryctrlTopupStat.equalsIgnoreCase("off")){
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
        BigDecimal personalTreasuryctrlBeneDailyAmt = null;
        BigDecimal personalTreasuryctrlBeneTotalAmt = null;
        Integer personalTreasuryctrlBeneDailyCnt = null;
        BigDecimal personalTreasuryctrlPayDailyAmt = null;
        BigDecimal personalTreasuryctrlPayTotalAmt = null;
        Integer personalTreasuryctrlPayDailyCnt = null;
        BigDecimal personalTreasuryctrlTopupDailyAmt = null;
        BigDecimal personalTreasuryctrlTopupTotalAmt = null;
        Integer personalTreasuryctrlTopupDailyCnt = null;
        BigDecimal personalTreasuryctrlCashoutDailyAmt = null;
        BigDecimal personalTreasuryctrlCashoutTotalAmt = null;
        Integer personalTreasuryctrlCashoutDailyCnt= null;
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
                personalTreasuryctrlBeneDailyAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt");
                personalTreasuryctrlBeneTotalAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt");
                personalTreasuryctrlBeneDailyCnt = rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt");
                personalTreasuryctrlPayDailyAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayDailyAmt");
                personalTreasuryctrlPayTotalAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayTotalAmt");
                personalTreasuryctrlPayDailyCnt = rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlPayDailyCnt");
                personalTreasuryctrlTopupDailyAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt");
                personalTreasuryctrlTopupTotalAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt");
                personalTreasuryctrlTopupDailyCnt = rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt");
                personalTreasuryctrlCashoutDailyAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt");
                personalTreasuryctrlCashoutTotalAmt = rsSelect2.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt");
                personalTreasuryctrlCashoutDailyCnt= rsSelect2.getInt("t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt");
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
