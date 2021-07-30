package com.qucai.sample.vo;
/************************************************
 * Personal Ewallet Payment Management
 * Author : Spear Yao
 * Date : 29/07/2021
 ************************************************/

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
import com.qucai.sample.util.DBConnection;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.MobileEwalletDashboard;
import com.qucai.sample.vo.MobilePersonalMain;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public static Map<String, Object> PersonalTreasuryRegulationCheck(String personalMID, String pid, String realName,String ProdCat) throws SQLException {
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
        
        Map<String, Object> retPerosnalTreasuryReg = new HashMap<>();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        Map<String,Object> rsPersonalTreasurySelect = new HashMap<>();
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
            rsPersonalTreasurySelect.put("SQL-CODE","exception:get Personal Treasury Controller Info failed");
            return rsPersonalTreasurySelect;
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
                rsPersonalTreasurySelect.put("retMsg", "Personal Treasury Controller Info not found");
                conn.close();
            }
        }
        //check Payment delegation 
        if(personalTreasuryctrlCashbackStat.equalsIgnoreCase("off")){

        }
        if(personalTreasuryctrlstatus.equalsIgnoreCase("off")){

        }
        if(personalTreasuryctrlCashoutStat.equalsIgnoreCase("off")){

        }
        if(personalTreasuryctrlBeneStat.equalsIgnoreCase("off")){
            
        }
        if(personalTreasuryctrlPayStat.equalsIgnoreCase("off")){

        }
        if(personalTreasuryctrlTopupStat.equalsIgnoreCase("off")){

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
            rsPersonalTreasurySelect.put("SQL-CODE","exception:get Personal Treasury Statistic info failed");
            return rsPersonalTreasurySelect;
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
                rsPersonalTreasurySelect.put("retMsg", "Personal Treasury Statistic info failed not found");
                conn.close();
            }
        }

        conn.close();
        return rsPersonalTreasurySelect;
    }

    public static Map<String, Object> PersonalEwalletTxnPayment(String personalMID, String pid, String realName){
        Map<String, Object> retPersonalEwalletTxnPayment = new HashMap<>();
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        System.out.print("Personal Ewallet Transaction Regulatory");
        return retPersonalEwalletTxnPayment;
    }
}
