package com.qucai.sample.vo;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.qucai.sample.util.DBConnection;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.TreasuryDBInfoService;
import com.qucai.sample.service.TreasuryInfoService;
import com.qucai.sample.util.ShiroSessionUtil;

@Controller
public class OverallStatisticRefresh{

    @Autowired
    private TreasuryInfoService treasuryInfoService; //申明一个对象

    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    //返回总余额	
    public int ReturnOverallCredit(BigDecimal OverAllFee,String TxnID) throws Exception{
        TreasuryDBInfo entity = null;
        entity = new TreasuryDBInfo();
        String t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        //返回机构余额
        TreasuryDBInfo treasuryOrgDBInfoUpdate = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
        BigDecimal tTreasuryOrgDBBalance = treasuryOrgDBInfoUpdate.getT_TreasuryDB_Balance().add(OverAllFee);
        treasuryOrgDBInfoUpdate.setT_TreasuryDB_Balance(tTreasuryOrgDBBalance);
        treasuryOrgDBInfoUpdate.setModifier(ShiroSessionUtil.getLoginSession().getId());
        treasuryOrgDBInfoUpdate.setModify_time(new Date());
        treasuryOrgDBInfoUpdate.setT_TreasuryDB_Comment("自动加值");
        int RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryOrgDBInfoUpdate);
        //
        if (RS == 1) {
            t_TreasuryDB_OrgName = "ALL";
            TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
            BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().add(OverAllFee);
            treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
            RS = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
            return RS;
        } else {
            return RS;
        }
    }

    public static Map<String, Object> PersonalEwalletStatisticRefresh(String PersonalPID){
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        Map<String,Object> retStatus = new HashMap<>();
        String sql="UPDATE t_personal_ewallet_statistics," +
            "(SELECT t_ewallettxn_his.t_personalewallet_ApplierName_his as P_Name," +
            "t_ewallettxn_his.t_personalewallet_ApplierPID_his as P_PID," +
            "SUM(IF((t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his >= 0 AND t_ewallettxn_his.t_WalletTxn_TxnCat_his = '58scan-txn-58qr'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_INCOME_AMT," +
            "COUNT(IF((t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his >= 0 AND t_ewallettxn_his.t_WalletTxn_TxnCat_his = '58scan-txn-58qr'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_INCOME_CNT," +
            "SUM(IF((t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his < 0 AND t_ewallettxn_his.t_WalletTxn_TxnCat_his = '58scan-txn-58qr'),ABS(t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his),null)) AS P_DAILY_OUTGOING_AMT," +
            "COUNT(IF((t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his < 0 AND t_ewallettxn_his.t_WalletTxn_TxnCat_his = '58scan-txn-58qr'),ABS(t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his),null)) AS P_DAILY_OUTGOING_CNT," +
            "SUM(IF((t_ewallettxn_his.t_WalletTxn_TxnCat_his = 'PersonalEwalletTopup'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_TOPUP_AMT," +
            "COUNT(IF(( t_ewallettxn_his.t_WalletTxn_TxnCat_his = 'PersonalEwalletTopup'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_TOPUP_CNT," +
            "SUM(IF((t_ewallettxn_his.t_WalletTxn_TxnCat_his = 'ewalletcashout'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_CASHOUT_AMT," +
            "COUNT(IF((t_ewallettxn_his.t_WalletTxn_TxnCat_his = 'ewalletcashout'),t_ewallettxn_his.t_WalletTxn_TotTxnAmount_his,null)) AS P_DAILY_CASHOUT_CNT" +
            "from t_ewallettxn_his GROUP BY t_ewallettxn_his.t_personalewallet_ApplierName_his) as a" +
            "SET t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlBeneDailyAmt = a.P_DAILY_INCOME_AMT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt=IF(a.P_DAILY_INCOME_AMT is null,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlBeneTotalAmt+a.P_DAILY_INCOME_AMT)," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlBeneDailyCnt=a.P_DAILY_INCOME_CNT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlPayDailyAmt=a.P_DAILY_OUTGOING_AMT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlPayTotalAmt=IF(a.P_DAILY_OUTGOING_AMT is null,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlPayTotalAmt,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlPayTotalAmt+a.P_DAILY_OUTGOING_AMT)," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlPayDailyCnt=a.P_DAILY_OUTGOING_CNT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlTopupDailyAmt=a.P_DAILY_TOPUP_AMT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt=IF(a.P_DAILY_TOPUP_AMT is null,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlTopupTotalAmt+a.P_DAILY_TOPUP_AMT)," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlTopupDailyCnt=a.P_DAILY_TOPUP_CNT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlCashoutDailyAmt=a.P_DAILY_CASHOUT_AMT," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt=IF(a.P_DAILY_CASHOUT_AMT=null,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt,t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlCashoutTotalAmt+a.P_DAILY_CASHOUT_AMT)," +
            "t_personal_ewallet_statistics.t_personal_ewallet_statistic_treasuryctrlCashoutDailyCnt=a.P_DAILY_CASHOUT_CNT" +
            "WHERE t_personal_ewallet_statistics.t_personal_ewallet_statistic_ApplierPID = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, PersonalPID);
            System.out.println(ptmt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
            retStatus.put("SQL-CODE",String.valueOf(e.getErrorCode()));
        }finally {
            retStatus.put("SQL","SQL-PERSONALEWALLETSTATISTICUPDATESUCC");
        }
        return retStatus;
    }

//返回给个人当月剩余余额
//	public String ReturnPersonalCredit(StaffPrepayApplicationPayment staffPrepayApplicationPayment) throws Exception{
////	获取后台失败交易记录
////		StaffPrepayApplicationPayment StaffPrepayApplicationPaymentAll = 
//////	探测支付商返回结果，每条循环执行，不为0003的返回余额
//////  返还企业及个人余额
////	    String retData = QueryOrderDemo.main(staffPrepayApplicationPayment);
////	    
////	    return retData;
//
//	}
}
