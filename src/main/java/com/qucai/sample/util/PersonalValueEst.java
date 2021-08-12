package com.qucai.sample.util;

import com.qucai.sample.controller.PersonalMainController;
import com.qucai.sample.controller.PersonalTreasuryCtrlController;
import com.qucai.sample.controller.ProductMainController;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.entity.ProductMain;
import com.qucai.sample.vo.MobilePersonalMain;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PersonalValueEst {
    
    public static Map<String, Object>  PersonalEvaluateRenew() {
        Map<String,Object> retPersonalEvalation = new HashMap<>();
        String newPersonalEvaluateNum = "999999.99";
        retPersonalEvalation.put("newPersonalEvaluateNum",new BigDecimal(newPersonalEvaluateNum));
        return retPersonalEvalation;
    }

    public static Map<String, Object> PersonalTreasuryChk(String action, String txnCat, BigDecimal txnAmt, String walletTxn_PayerPID, String walletTxn_ReceiverID,
                                                          String method, String paymentID, String paymentStatus, Connection conn) throws SQLException {
        Map<String,Object> PersonalTreasuryChk = new HashMap<String, Object>();
        PersonalMainController personalMainController = new PersonalMainController();
        
        MobilePersonalMain mobilePersonalMain = (MobilePersonalMain) personalMainController.findPersonalMainInfo(walletTxn_PayerPID,conn);
        ProductMainController productMainController = new ProductMainController();
        ProductMain MobileProductMain = (ProductMain) productMainController.findPersonalProduct(mobilePersonalMain.getT_mobilePersonalMain_productCat(),conn);
        if(MobileProductMain.getT_Product_Txt().equalsIgnoreCase("SQL-FindPersonalProduct-ErrorCode")){
            PersonalTreasuryChk.put("SQL","findPersonalProduct-error");
            PersonalTreasuryChk.put("SQL-CODE",MobileProductMain.getT_Product_Txt1());
            PersonalTreasuryChk.put("SQL-CAUSE",MobileProductMain.getT_Product_Txt2());
            PersonalTreasuryChk.put("PersonalName",mobilePersonalMain.getT_mobilePersonalMain_name());
            PersonalTreasuryChk.put("PersonalMobile",mobilePersonalMain.getT_mobilePersonalMain_mobile());
            return PersonalTreasuryChk;
        }else{
            PersonalTreasuryCtrlController personalTreasuryCtrlController = new PersonalTreasuryCtrlController();
            PersonalTreasuryCtrl MobilePersonalTreasuryCtrl = (PersonalTreasuryCtrl) personalTreasuryCtrlController.findPersonalTreasury(MobileProductMain.getT_Product_SeriesID(),conn);
            if(MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt().equalsIgnoreCase("SQL-FindPersonalProduct-ErrorCode")){
                PersonalTreasuryChk.put("SQL",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt());
                PersonalTreasuryChk.put("SQL-CODE",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt2());
                PersonalTreasuryChk.put("SQL-CAUSE",MobilePersonalTreasuryCtrl.getT_personalewallet_treasuryctrlTxt3());
                return PersonalTreasuryChk;
            }else{
//                MobileProductMain.get  coding
            }
        }
        return PersonalTreasuryChk;
    }

    public static Map<String, Object> PersonalTreasuryFind(String action,String PersonalPID,BigDecimal txnAmt, Connection conn) throws SQLException {
        Map<String, Object> PersonalTreasuryFind = new HashMap<String, Object>();

//四连innner join获取三张表的信息- 拿PID查出个人信息连接个人产品控制信息连接个人资金控制 同时获取个人当前交易资金统计信息
        ResultSet rs;
        String sql = "select * from (t_personal_main INNER JOIN t_personal_ewallet on t_personal_main.t_personal_main_pid = t_personal_ewallet.t_personalewallet_ApplierPID) " +
            "INNER JOIN t_personal_ewallet_statistics on t_personal_ewallet_statistics.t_personal_ewallet_statistic_ApplierPID = t_personal_ewallet.t_personalewallet_ApplierPID " +
            "INNER JOIN t_personal_treasuryctrl on t_personal_main.t_personal_main_productCat = t_personal_treasuryctrl.t_personalewallet_treasuryctrlID " +
            "where t_personal_main.t_personal_main_pid = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, PersonalPID);
            rs = ptmt.executeQuery();
            if (rs.next() && rs.getFetchSize() == 1) {
                PersonalTreasuryFind.put("t_personal_main_id",rs.getString("t_personal_main_id"));
                PersonalTreasuryFind.put("t_personal_main_name",rs.getString("t_personal_main_name"));
                PersonalTreasuryFind.put("t_personal_main_pid",rs.getString("t_personal_main_pid"));
                PersonalTreasuryFind.put("t_personal_main_mobile",rs.getString("t_personal_main_mobile"));
                PersonalTreasuryFind.put("t_personal_main_mobile1",rs.getString("t_personal_main_mobile1"));
                PersonalTreasuryFind.put("t_personal_main_contacts",rs.getString("t_personal_main_contacts"));
                PersonalTreasuryFind.put("t_personal_main_realname",rs.getString("t_personal_main_realname"));
                PersonalTreasuryFind.put("t_personal_main_fingerprint",rs.getString("t_personal_main_fingerprint"));
                PersonalTreasuryFind.put("t_personal_main_facialret",rs.getString("t_personal_main_facialret"));
                PersonalTreasuryFind.put("t_personal_main_securityret",rs.getString("t_personal_main_securityret"));
                PersonalTreasuryFind.put("t_personal_main_passport",rs.getString("t_personal_main_passport"));
                PersonalTreasuryFind.put("t_personal_main_passport1",rs.getString("t_personal_main_passport1"));
                PersonalTreasuryFind.put("t_personal_main_visa",rs.getString("t_personal_main_visa"));
                PersonalTreasuryFind.put("t_personal_main_visa1",rs.getString("t_personal_main_visa1"));
                PersonalTreasuryFind.put("t_personal_main_onlinepaymentcat",rs.getString("t_personal_main_onlinepaymentcat"));
                PersonalTreasuryFind.put("t_personal_main_onlinepayment",rs.getString("t_personal_main_onlinepayment"));
                PersonalTreasuryFind.put("t_personal_main_paymentmethod",rs.getString("t_personal_main_paymentmethod"));
                PersonalTreasuryFind.put("t_personal_main_CNbankcard",rs.getString("t_personal_main_CNbankcard"));
                PersonalTreasuryFind.put("t_personal_main_GLbankcard",rs.getString("t_personal_main_GLbankcard"));
                PersonalTreasuryFind.put("t_personal_main_bankacc",rs.getString("t_personal_main_bankacc"));
                PersonalTreasuryFind.put("t_personal_main_crypto",rs.getString("t_personal_main_crypto"));
                PersonalTreasuryFind.put("t_personal_main_assetcat",rs.getString("t_personal_main_assetcat"));
                PersonalTreasuryFind.put("t_personal_main_voucher",rs.getString("t_personal_main_voucher"));
                PersonalTreasuryFind.put("t_personal_main_creditscore",rs.getString("t_personal_main_creditscore"));
                PersonalTreasuryFind.put("t_personal_main_digiasset",rs.getString("t_personal_main_digiasset"));
                PersonalTreasuryFind.put("t_personal_main_companylist",rs.getString("t_personal_main_companylist"));
                PersonalTreasuryFind.put("t_personal_main_productCat",rs.getString("t_personal_main_productCat"));
                PersonalTreasuryFind.put("t_personal_main_ewalletcat",rs.getString("t_personal_main_ewalletcat"));
                PersonalTreasuryFind.put("t_personal_main_prodlist",rs.getString("t_personal_main_prodlist"));
                PersonalTreasuryFind.put("t_personal_main_paymentmethod1",rs.getString("t_personal_main_paymentmethod1"));
                PersonalTreasuryFind.put("t_personalewallet_ID",rs.getString("t_personalewallet_ID"));
                PersonalTreasuryFind.put("t_personalewallet_ApplierID",rs.getString("t_personalewallet_ApplierID"));
                PersonalTreasuryFind.put("t_personalewallet_ApplierPID",rs.getString("t_personalewallet_ApplierPID"));
                PersonalTreasuryFind.put("t_personalewallet_ApplierName",rs.getString("t_personalewallet_ApplierName"));
                PersonalTreasuryFind.put("t_personalewallet_PayRules",rs.getString("t_personalewallet_PayRules"));
                PersonalTreasuryFind.put("t_personalewallet_ScanCode",rs.getString("t_personalewallet_ScanCode"));
                PersonalTreasuryFind.put("t_personalewallet_QRcode",rs.getString("t_personalewallet_QRcode"));
                PersonalTreasuryFind.put("t_personalewallet_alipayAcc",rs.getString("t_personalewallet_alipayAcc"));
                PersonalTreasuryFind.put("t_personalewallet_wechatpayAcc",rs.getString("t_personalewallet_wechatpayAcc"));
                PersonalTreasuryFind.put("t_personalewallet_unionpayAcc",rs.getString("t_personalewallet_unionpayAcc"));
                PersonalTreasuryFind.put("t_personalewallet_CryptoC",rs.getString("t_personalewallet_CryptoC"));
                PersonalTreasuryFind.put("t_personalewallet_Voucher",rs.getString("t_personalewallet_Voucher"));
                PersonalTreasuryFind.put("t_personalewallet_VoucherDigi",rs.getString("t_personalewallet_VoucherDigi"));
                PersonalTreasuryFind.put("t_personalewallet_Creditcard",rs.getString("t_personalewallet_Creditcard"));
                PersonalTreasuryFind.put("t_personalewallet_Debitcard",rs.getString("t_personalewallet_Debitcard"));
                PersonalTreasuryFind.put("t_personalewallet_ClearNum",rs.getString("t_personalewallet_ClearNum"));
                PersonalTreasuryFind.put("t_personalewallet_ClearOrg",rs.getString("t_personalewallet_ClearOrg"));
                PersonalTreasuryFind.put("t_personalewallet_PayCat",rs.getString("t_personalewallet_PayCat"));
                PersonalTreasuryFind.put("t_personalewallet_PayDate",rs.getDate("t_personalewallet_PayDate"));
                PersonalTreasuryFind.put("t_personalewallet_ProdName",rs.getString("t_personalewallet_ProdName"));
                PersonalTreasuryFind.put("t_personalewallet_PayDays",rs.getInt("t_personalewallet_PayDays"));
                PersonalTreasuryFind.put("t_personalewallet_Reciept",rs.getString("t_personalewallet_Reciept"));
                PersonalTreasuryFind.put("t_personalewallet_TotCNYBalance",rs.getBigDecimal("t_personalewallet_TotCNYBalance"));
                PersonalTreasuryFind.put("t_personalewallet_TotFXBalance",rs.getBigDecimal("t_personalewallet_TotFXBalance"));
                PersonalTreasuryFind.put("t_personalewallet_TotCryptoBalance",rs.getBigDecimal("t_personalewallet_TotCryptoBalance"));
                PersonalTreasuryFind.put("t_personalewallet_TotAssetES",rs.getBigDecimal("t_personalewallet_TotAssetES"));
                PersonalTreasuryFind.put("t_personalewallet_DebitPayAmt",rs.getBigDecimal("t_personalewallet_DebitPayAmt"));
                PersonalTreasuryFind.put("t_personalewallet_ApplyPayAmount",rs.getBigDecimal("t_personalewallet_ApplyPayAmount"));
                PersonalTreasuryFind.put("t_personalewallet_CreditPrepayBalanceNum",rs.getBigDecimal("t_personalewallet_CreditPrepayBalanceNum"));
                PersonalTreasuryFind.put("t_personalewallet_BaselineAdjustment",rs.getBigDecimal("t_personalewallet_BaselineAdjustment"));
                PersonalTreasuryFind.put("t_personalewallet_TotDailyAmt",rs.getBigDecimal("t_personalewallet_TotDailyAmt"));
                PersonalTreasuryFind.put("t_personalewallet_DailyInterestRatio",rs.getBigDecimal("t_personalewallet_DailyInterestRatio"));
                PersonalTreasuryFind.put("t_personalewallet_DayCntEq0",rs.getInt("t_personalewallet_DayCntEq0"));
                PersonalTreasuryFind.put("t_personalewallet_DayCntMorethan0",rs.getInt("t_personalewallet_DayCntMorethan0"));
                PersonalTreasuryFind.put("t_personalewallet_Interest",rs.getBigDecimal("t_personalewallet_Interest"));
                PersonalTreasuryFind.put("t_personalewallet_BalaceYesterDay",rs.getBigDecimal("t_personalewallet_BalaceYesterDay"));
                PersonalTreasuryFind.put("t_personalewallet_Worth",rs.getBigDecimal("t_personalewallet_Worth"));
                PersonalTreasuryFind.put("t_personalewallet_DiscountRate",rs.getBigDecimal("t_personalewallet_DiscountRate"));
                PersonalTreasuryFind.put("t_personalewallet_BalanceInterest",rs.getBigDecimal("t_personalewallet_BalanceInterest"));
                PersonalTreasuryFind.put("t_personalewallet_OverdueRepaymentDate",rs.getDate("t_personalewallet_OverdueRepaymentDate"));
                PersonalTreasuryFind.put("t_personalewallet_PrepayClear",rs.getInt("t_personalewallet_PrepayClear"));
                PersonalTreasuryFind.put("t_personalewallet_Overdue",rs.getInt("t_personalewallet_Overdue"));
                PersonalTreasuryFind.put("t_personalewallet_TotalPrepayAmt",rs.getBigDecimal("t_personalewallet_TotalPrepayAmt"));
                PersonalTreasuryFind.put("t_personalewallet_TotalWorthCal",rs.getBigDecimal("t_personalewallet_TotalWorthCal"));
                PersonalTreasuryFind.put("t_personalewallet_ServiceFee",rs.getBigDecimal("t_personalewallet_ServiceFee"));
                PersonalTreasuryFind.put("t_personalewallet_Poundage",rs.getBigDecimal("t_personalewallet_Poundage"));
                PersonalTreasuryFind.put("t_personalewallet_YesterdayWorthCal",rs.getBigDecimal("t_personalewallet_YesterdayWorthCal"));
                PersonalTreasuryFind.put("t_personalewallet_InterestShareBalance",rs.getBigDecimal("t_personalewallet_InterestShareBalance"));
                PersonalTreasuryFind.put("t_personalewallet_BankAccName",rs.getString("t_personalewallet_BankAccName"));
                PersonalTreasuryFind.put("t_personalewallet_BankAcc",rs.getString("t_personalewallet_BankAcc"));
                PersonalTreasuryFind.put("t_personalewallet_SysUpdateDate",rs.getDate("t_personalewallet_SysUpdateDate"));
                PersonalTreasuryFind.put("t_personalewallet_BalanceCntDays",rs.getInt("t_personalewallet_BalanceCntDays"));
                PersonalTreasuryFind.put("t_personalewallet_ewalletAccStatus",rs.getString("t_personalewallet_ewalletAccStatus"));
                PersonalTreasuryFind.put("t_personalewallet_SMS",rs.getString("t_personalewallet_SMS"));
                PersonalTreasuryFind.put("t_personalewallet_PaymentVersion",rs.getString("t_personalewallet_PaymentVersion"));
                PersonalTreasuryFind.put("t_personalewallet_AccCat",rs.getString("t_personalewallet_AccCat"));
                PersonalTreasuryFind.put("t_personalewallet_MarginShare",rs.getBigDecimal("t_personalewallet_MarginShare"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryID",rs.getBigDecimal("t_personalewallet_treasuryID"));
                PersonalTreasuryFind.put("t_personalewallet_eproposal",rs.getString("t_personalewallet_eproposal"));
                PersonalTreasuryFind.put("t_personalewallet_DigiAddress",rs.getString("t_personalewallet_DigiAddress"));
                PersonalTreasuryFind.put("t_personalewallet_TermPaymentBalance",rs.getString("t_personalewallet_TermPaymentBalance"));
                PersonalTreasuryFind.put("t_personalewallet_TermPaymentBal",rs.getString("t_personalewallet_TermPaymentBal"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_ID",rs.getString("t_personal_ewallet_statistic_ID"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_ApplierID",rs.getString("t_personal_ewallet_statistic_ApplierID"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_ApplierPID",rs.getString("t_personal_ewallet_statistic_ApplierPID"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_ApplierName",rs.getString("t_personal_ewallet_statistic_ApplierName"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt",rs.getInt("t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlPayDailyAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayDailyAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlPayTotalAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlPayTotalAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlPayDailyCnt",rs.getInt("t_personal_ewallet_statistic_treasuryctrlPayDailyCnt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt",rs.getInt("t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt",rs.getBigDecimal("t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt",rs.getInt("t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_Txt1",rs.getString("t_personal_ewallet_statistic_Txt1"));
                PersonalTreasuryFind.put("t_personal_ewallet_statistic_Txt2",rs.getString("t_personal_ewallet_statistic_Txt2"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlID",rs.getString("t_personalewallet_treasuryctrlID"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlProdName",rs.getString("t_personalewallet_treasuryctrlProdName"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlProdPID",rs.getString("t_personalewallet_treasuryctrlProdPID"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneDailyLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneTxnLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlBeneTxnLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneTotalLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlBeneTotalLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneFee",rs.getBigDecimal("t_personalewallet_treasuryctrlBeneFee"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneDailyCntLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyCntLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlBeneStat",rs.getString("t_personalewallet_treasuryctrlBeneStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayDailyLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlPayDailyLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayTxnLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayTotalLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayFee",rs.getBigDecimal("t_personalewallet_treasuryctrlPayFee"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayDailyCntLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlPayDailyCntLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPayStat",rs.getString("t_personalewallet_treasuryctrlPayStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupDailyLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlTopupDailyLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupTxnLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlTopupTxnLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupTotalLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlTopupTotalLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupFee",rs.getBigDecimal("t_personalewallet_treasuryctrlTopupFee"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupDailyCntLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlTopupDailyCntLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTopupStat",rs.getString("t_personalewallet_treasuryctrlTopupStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutDailyLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlCashoutDailyLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutTxnLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlCashoutTxnLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutTotalLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlCashoutTotalLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutFee",rs.getBigDecimal("t_personalewallet_treasuryctrlCashoutFee"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutDailyCntLimit",rs.getBigDecimal("t_personalewallet_treasuryctrlCashoutDailyCntLimit"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashoutStat",rs.getString("t_personalewallet_treasuryctrlCashoutStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlRefundStat",rs.getString("t_personalewallet_treasuryctrlRefundStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlProdSerList",rs.getBigDecimal("t_personalewallet_treasuryctrlProdSerList"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlPersonalCatList",rs.getBigDecimal("t_personalewallet_treasuryctrlPersonalCatList"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlAccList",rs.getBigDecimal("t_personalewallet_treasuryctrlAccList"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlstatus",rs.getString("t_personalewallet_treasuryctrlstatus"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlbkp",rs.getString("t_personalewallet_treasuryctrlbkp"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlbkp1",rs.getString("t_personalewallet_treasuryctrlbkp1"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTxt",rs.getString("t_personalewallet_treasuryctrlTxt"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlCashbackStat",rs.getString("t_personalewallet_treasuryctrlCashbackStat"));
                PersonalTreasuryFind.put("t_personalewallet_treasuryctrlTxt2",rs.getString("t_personalewallet_treasuryctrlTxt2"));
            }else{
                PersonalTreasuryFind.put("SQL-RESULT", "Find None Mutiple Personal Info Exception");
                return PersonalTreasuryFind;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            PersonalTreasuryFind.put("SQL-CODE", String.valueOf(e.getErrorCode()));
            conn.close();
            return PersonalTreasuryFind;
        } finally {
            // switch Treasury delegation limited coding...
            if(action != null) {
                switch (action) {
                    case "cashout":
                        System.out.println("cashout verify");
                        break;
                    case "ewalletcashadvance":
                        System.out.println("ewalletcashadvance verify");
                        break;
                    case "transaction":
                        System.out.println("58-alipay/wechatpay/unionpay transaction verify");
                        break;
                    case "58qr-58scan":
                        System.out.println("58qr-58receiver verify");
                        break;
                    case "shopping":
                        System.out.println("shopping verify");
                        break;
                    case "topup":
                        System.out.println("topup verify");
                        break;
                    case "cashadvance":
                        System.out.println("cashadvance verify");
                        break;
                    case "wealthmgt":
                        System.out.println("wealthmgt verify");
                        break;
                    default:
                        System.out.println("TreasuryChk verify default");
                        break;
                }
            }else{
                PersonalTreasuryFind.put("personalTreasuryData","personalTreasuryDataRet");
                return PersonalTreasuryFind;
            }
            conn.close();
            PersonalTreasuryFind.put("SQL", "SQL-PERSONALEWALLETSTATISTIC-UPDATESUCC");
        }
        return PersonalTreasuryFind;
    }

}
