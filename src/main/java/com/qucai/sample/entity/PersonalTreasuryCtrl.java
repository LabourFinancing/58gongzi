package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PersonalTreasuryCtrl implements Serializable {
    /**
	 * 
	 */
   private static final long serialVersionUID = -1876948347463745808L;



    private String t_personalewallet_treasuryctrlID ;

    private String t_personalewallet_treasuryctrlProdName;

    private String t_personalewallet_treasuryctrlProdPID;

    private BigDecimal t_personalewallet_treasuryctrlBeneDailyLimit;

    private BigDecimal t_personalewallet_treasuryctrlBeneTxnLimit;

    private BigDecimal t_personalewallet_treasuryctrlBeneTotalLimit;

    private String t_personalewallet_treasuryctrlBeneFee;

    private BigDecimal t_personalewallet_treasuryctrlBeneDailyCntLimit;

    private String t_personalewallet_treasuryctrlBeneStat;

    private BigDecimal t_personalewallet_treasuryctrlPayDailyLimit;

    private BigDecimal t_personalewallet_treasuryctrlPayTxnLimit;

    private BigDecimal t_personalewallet_treasuryctrlPayTotalLimit;

    private BigDecimal t_personalewallet_treasuryctrlPayFee;
    
    private BigDecimal t_personalewallet_treasuryctrlPayDailyCntLimit ;

    private String t_personalewallet_treasuryctrlPayStat;

    private BigDecimal t_personalewallet_treasuryctrlTopupDailyLimit;

    private BigDecimal t_personalewallet_treasuryctrlTopupTxnLimit;

    private BigDecimal t_personalewallet_treasuryctrlTopupTotalLimit;

    private String t_personalewallet_treasuryctrlTopupFee;

    private BigDecimal t_personalewallet_treasuryctrlTopupDailyCntLimit;

    private String t_personalewallet_treasuryctrlTopupStat;

    private BigDecimal t_personalewallet_treasuryctrlCashoutDailyLimit;

    private BigDecimal t_personalewallet_treasuryctrlCashoutTxnLimit;

    private BigDecimal t_personalewallet_treasuryctrlCashoutTotalLimit;

    private String t_personalewallet_treasuryctrlCashoutFee;

    private BigDecimal t_personalewallet_treasuryctrlCashoutDailyCntLimit;

    private String t_personalewallet_treasuryctrlCashoutStat;

    private String t_personalewallet_treasuryctrlRefundStat;

    private String t_personalewallet_treasuryctrlProdSerList;

    private String t_personalewallet_treasuryctrlPersonalCatList;

    private String t_personalewallet_treasuryctrlbkp;

    private BigDecimal t_personalewallet_treasuryctrlAccList;

    private String t_personalewallet_treasuryctrlstatus;

    private String t_personalewallet_treasuryctrlTxt;

    private String t_personalewallet_treasuryctrlCashbackStat;

    private String t_personalewallet_treasuryctrlTxt2;

    private String t_personalewallet_treasuryctrlTxt3;
   /**
    * 创建人
    */
   private String creator;
   /**
    * 修改人
    * @return 
    */
   private String modifier;
   
   private String remark;
   
   private String platform;
   
   private Date create_time;
   
   private Date modify_time;
   


    public String getT_personalewallet_treasuryctrlID() {
        return t_personalewallet_treasuryctrlID;
    }

    public void setT_personalewallet_treasuryctrlID(String t_personalewallet_treasuryctrlID) {
        this.t_personalewallet_treasuryctrlID = t_personalewallet_treasuryctrlID;
    }

    public String getT_personalewallet_treasuryctrlProdName() {
        return t_personalewallet_treasuryctrlProdName;
    }

    public void setT_personalewallet_treasuryctrlProdName(String t_personalewallet_treasuryctrlProdName) {
        this.t_personalewallet_treasuryctrlProdName = t_personalewallet_treasuryctrlProdName;
    }

    public String getT_personalewallet_treasuryctrlProdPID() {
        return t_personalewallet_treasuryctrlProdPID;
    }

    public void setT_personalewallet_treasuryctrlProdPID(String t_personalewallet_treasuryctrlProdPID) {
        this.t_personalewallet_treasuryctrlProdPID = t_personalewallet_treasuryctrlProdPID;
    }

    public BigDecimal getT_personalewallet_treasuryctrlBeneDailyLimit() {
        return t_personalewallet_treasuryctrlBeneDailyLimit;
    }

    public void setT_personalewallet_treasuryctrlBeneDailyLimit(BigDecimal t_personalewallet_treasuryctrlBeneDailyLimit) {
        this.t_personalewallet_treasuryctrlBeneDailyLimit = t_personalewallet_treasuryctrlBeneDailyLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlBeneTxnLimit() {
        return t_personalewallet_treasuryctrlBeneTxnLimit;
    }

    public void setT_personalewallet_treasuryctrlBeneTxnLimit(BigDecimal t_personalewallet_treasuryctrlBeneTxnLimit) {
        this.t_personalewallet_treasuryctrlBeneTxnLimit = t_personalewallet_treasuryctrlBeneTxnLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlBeneTotalLimit() {
        return t_personalewallet_treasuryctrlBeneTotalLimit;
    }

    public void setT_personalewallet_treasuryctrlBeneTotalLimit(BigDecimal t_personalewallet_treasuryctrlBeneTotalLimit) {
        this.t_personalewallet_treasuryctrlBeneTotalLimit = t_personalewallet_treasuryctrlBeneTotalLimit;
    }

    public String getT_personalewallet_treasuryctrlBeneFee() {
        return t_personalewallet_treasuryctrlBeneFee;
    }

    public void setT_personalewallet_treasuryctrlBeneFee(String t_personalewallet_treasuryctrlBeneFee) {
        this.t_personalewallet_treasuryctrlBeneFee = t_personalewallet_treasuryctrlBeneFee;
    }

    public BigDecimal getT_personalewallet_treasuryctrlBeneDailyCntLimit() {
        return t_personalewallet_treasuryctrlBeneDailyCntLimit;
    }

    public void setT_personalewallet_treasuryctrlBeneDailyCntLimit(BigDecimal t_personalewallet_treasuryctrlBeneDailyCntLimit) {
        this.t_personalewallet_treasuryctrlBeneDailyCntLimit = t_personalewallet_treasuryctrlBeneDailyCntLimit;
    }

    public String getT_personalewallet_treasuryctrlBeneStat() {
        return t_personalewallet_treasuryctrlBeneStat;
    }

    public void setT_personalewallet_treasuryctrlBeneStat(String t_personalewallet_treasuryctrlBeneStat) {
        this.t_personalewallet_treasuryctrlBeneStat = t_personalewallet_treasuryctrlBeneStat;
    }

    public BigDecimal getT_personalewallet_treasuryctrlPayDailyLimit() {
        return t_personalewallet_treasuryctrlPayDailyLimit;
    }

    public void setT_personalewallet_treasuryctrlPayDailyLimit(BigDecimal t_personalewallet_treasuryctrlPayDailyLimit) {
        this.t_personalewallet_treasuryctrlPayDailyLimit = t_personalewallet_treasuryctrlPayDailyLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlPayTxnLimit() {
        return t_personalewallet_treasuryctrlPayTxnLimit;
    }

    public void setT_personalewallet_treasuryctrlPayTxnLimit(BigDecimal t_personalewallet_treasuryctrlPayTxnLimit) {
        this.t_personalewallet_treasuryctrlPayTxnLimit = t_personalewallet_treasuryctrlPayTxnLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlPayTotalLimit() {
        return t_personalewallet_treasuryctrlPayTotalLimit;
    }

    public void setT_personalewallet_treasuryctrlPayTotalLimit(BigDecimal t_personalewallet_treasuryctrlPayTotalLimit) {
        this.t_personalewallet_treasuryctrlPayTotalLimit = t_personalewallet_treasuryctrlPayTotalLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlPayFee() {
        return t_personalewallet_treasuryctrlPayFee;
    }

    public void setT_personalewallet_treasuryctrlPayFee(BigDecimal t_personalewallet_treasuryctrlPayFee) {
        this.t_personalewallet_treasuryctrlPayFee = t_personalewallet_treasuryctrlPayFee;
    }

    public BigDecimal getT_personalewallet_treasuryctrlPayDailyCntLimit() {
        return t_personalewallet_treasuryctrlPayDailyCntLimit;
    }

    public void setT_personalewallet_treasuryctrlPayDailyCntLimit(BigDecimal t_personalewallet_treasuryctrlPayDailyCntLimit) {
        this.t_personalewallet_treasuryctrlPayDailyCntLimit = t_personalewallet_treasuryctrlPayDailyCntLimit;
    }

    public String getT_personalewallet_treasuryctrlPayStat() {
        return t_personalewallet_treasuryctrlPayStat;
    }

    public void setT_personalewallet_treasuryctrlPayStat(String t_personalewallet_treasuryctrlPayStat) {
        this.t_personalewallet_treasuryctrlPayStat = t_personalewallet_treasuryctrlPayStat;
    }

    public BigDecimal getT_personalewallet_treasuryctrlTopupDailyLimit() {
        return t_personalewallet_treasuryctrlTopupDailyLimit;
    }

    public void setT_personalewallet_treasuryctrlTopupDailyLimit(BigDecimal t_personalewallet_treasuryctrlTopupDailyLimit) {
        this.t_personalewallet_treasuryctrlTopupDailyLimit = t_personalewallet_treasuryctrlTopupDailyLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlTopupTxnLimit() {
        return t_personalewallet_treasuryctrlTopupTxnLimit;
    }

    public void setT_personalewallet_treasuryctrlTopupTxnLimit(BigDecimal t_personalewallet_treasuryctrlTopupTxnLimit) {
        this.t_personalewallet_treasuryctrlTopupTxnLimit = t_personalewallet_treasuryctrlTopupTxnLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlTopupTotalLimit() {
        return t_personalewallet_treasuryctrlTopupTotalLimit;
    }

    public void setT_personalewallet_treasuryctrlTopupTotalLimit(BigDecimal t_personalewallet_treasuryctrlTopupTotalLimit) {
        this.t_personalewallet_treasuryctrlTopupTotalLimit = t_personalewallet_treasuryctrlTopupTotalLimit;
    }

    public String getT_personalewallet_treasuryctrlTopupFee() {
        return t_personalewallet_treasuryctrlTopupFee;
    }

    public void setT_personalewallet_treasuryctrlTopupFee(String t_personalewallet_treasuryctrlTopupFee) {
        this.t_personalewallet_treasuryctrlTopupFee = t_personalewallet_treasuryctrlTopupFee;
    }

    public BigDecimal getT_personalewallet_treasuryctrlTopupDailyCntLimit() {
        return t_personalewallet_treasuryctrlTopupDailyCntLimit;
    }

    public void setT_personalewallet_treasuryctrlTopupDailyCntLimit(BigDecimal t_personalewallet_treasuryctrlTopupDailyCntLimit) {
        this.t_personalewallet_treasuryctrlTopupDailyCntLimit = t_personalewallet_treasuryctrlTopupDailyCntLimit;
    }

    public String getT_personalewallet_treasuryctrlTopupStat() {
        return t_personalewallet_treasuryctrlTopupStat;
    }

    public void setT_personalewallet_treasuryctrlTopupStat(String t_personalewallet_treasuryctrlTopupStat) {
        this.t_personalewallet_treasuryctrlTopupStat = t_personalewallet_treasuryctrlTopupStat;
    }

    public BigDecimal getT_personalewallet_treasuryctrlCashoutDailyLimit() {
        return t_personalewallet_treasuryctrlCashoutDailyLimit;
    }

    public void setT_personalewallet_treasuryctrlCashoutDailyLimit(BigDecimal t_personalewallet_treasuryctrlCashoutDailyLimit) {
        this.t_personalewallet_treasuryctrlCashoutDailyLimit = t_personalewallet_treasuryctrlCashoutDailyLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlCashoutTxnLimit() {
        return t_personalewallet_treasuryctrlCashoutTxnLimit;
    }

    public void setT_personalewallet_treasuryctrlCashoutTxnLimit(BigDecimal t_personalewallet_treasuryctrlCashoutTxnLimit) {
        this.t_personalewallet_treasuryctrlCashoutTxnLimit = t_personalewallet_treasuryctrlCashoutTxnLimit;
    }

    public BigDecimal getT_personalewallet_treasuryctrlCashoutTotalLimit() {
        return t_personalewallet_treasuryctrlCashoutTotalLimit;
    }

    public void setT_personalewallet_treasuryctrlCashoutTotalLimit(BigDecimal t_personalewallet_treasuryctrlCashoutTotalLimit) {
        this.t_personalewallet_treasuryctrlCashoutTotalLimit = t_personalewallet_treasuryctrlCashoutTotalLimit;
    }

    public String gett_personalewallet_treasuryctrlCashoutFee() {
        return t_personalewallet_treasuryctrlCashoutFee;
    }

    public void sett_personalewallet_treasuryctrlCashoutFee(String t_personalewallet_treasuryctrlCashoutFee) {
        this.t_personalewallet_treasuryctrlCashoutFee = t_personalewallet_treasuryctrlCashoutFee;
    }

    public BigDecimal getT_personalewallet_treasuryctrlCashoutDailyCntLimit() {
        return t_personalewallet_treasuryctrlCashoutDailyCntLimit;
    }

    public void setT_personalewallet_treasuryctrlCashoutDailyCntLimit(BigDecimal t_personalewallet_treasuryctrlCashoutDailyCntLimit) {
        this.t_personalewallet_treasuryctrlCashoutDailyCntLimit = t_personalewallet_treasuryctrlCashoutDailyCntLimit;
    }

    public String getT_personalewallet_treasuryctrlCashoutStat() {
        return t_personalewallet_treasuryctrlCashoutStat;
    }

    public void setT_personalewallet_treasuryctrlCashoutStat(String t_personalewallet_treasuryctrlCashoutStat) {
        this.t_personalewallet_treasuryctrlCashoutStat = t_personalewallet_treasuryctrlCashoutStat;
    }

    public String getT_personalewallet_treasuryctrlRefundStat() {
        return t_personalewallet_treasuryctrlRefundStat;
    }

    public void setT_personalewallet_treasuryctrlRefundStat(String t_personalewallet_treasuryctrlRefundStat) {
        this.t_personalewallet_treasuryctrlRefundStat = t_personalewallet_treasuryctrlRefundStat;
    }

    public String getT_personalewallet_treasuryctrlProdSerList() {
        return t_personalewallet_treasuryctrlProdSerList;
    }

    public void setT_personalewallet_treasuryctrlProdSerList(String t_personalewallet_treasuryctrlProdSerList) {
        this.t_personalewallet_treasuryctrlProdSerList = t_personalewallet_treasuryctrlProdSerList;
    }

    public String getT_personalewallet_treasuryctrlPersonalCatList() {
        return t_personalewallet_treasuryctrlPersonalCatList;
    }

    public void setT_personalewallet_treasuryctrlPersonalCatList(String t_personalewallet_treasuryctrlPersonalCatList) {
        this.t_personalewallet_treasuryctrlPersonalCatList = t_personalewallet_treasuryctrlPersonalCatList;
    }
    public String getT_personalewallet_treasuryctrlbkp() {
        return t_personalewallet_treasuryctrlbkp;
    }

    public void setT_personalewallet_treasuryctrlbkp(String t_personalewallet_treasuryctrlbkp) {
        this.t_personalewallet_treasuryctrlbkp = t_personalewallet_treasuryctrlbkp;
    }

    public BigDecimal gett_personalewallet_treasuryctrlAccList() {
        return t_personalewallet_treasuryctrlAccList;
    }

    public void sett_personalewallet_treasuryctrlAccList(BigDecimal t_personalewallet_treasuryctrlAccList) {
        this.t_personalewallet_treasuryctrlAccList = t_personalewallet_treasuryctrlAccList;
    }

    public String getT_personalewallet_treasuryctrlstatus() {
        return t_personalewallet_treasuryctrlstatus;
    }

    public void setT_personalewallet_treasuryctrlstatus(String t_personalewallet_treasuryctrlstatus) {
        this.t_personalewallet_treasuryctrlstatus = t_personalewallet_treasuryctrlstatus;
    }

    public String getT_personalewallet_treasuryctrlTxt() {
        return t_personalewallet_treasuryctrlTxt;
    }

    public void setT_personalewallet_treasuryctrlTxt(String t_personalewallet_treasuryctrlTxt) {
        this.t_personalewallet_treasuryctrlTxt = t_personalewallet_treasuryctrlTxt;
    }

    public String gett_personalewallet_treasuryctrlCashbackStat() {
        return t_personalewallet_treasuryctrlCashbackStat;
    }

    public void sett_personalewallet_treasuryctrlCashbackStat(String t_personalewallet_treasuryctrlCashbackStat) {
        this.t_personalewallet_treasuryctrlCashbackStat = t_personalewallet_treasuryctrlCashbackStat;
    }

    public String getT_personalewallet_treasuryctrlTxt2() {
        return t_personalewallet_treasuryctrlTxt2;
    }

    public void setT_personalewallet_treasuryctrlTxt2(String t_personalewallet_treasuryctrlTxt2) {
        this.t_personalewallet_treasuryctrlTxt2 = t_personalewallet_treasuryctrlTxt2;
    }

    public String getT_personalewallet_treasuryctrlTxt3() {
        return t_personalewallet_treasuryctrlTxt3;
    }

    public void setT_personalewallet_treasuryctrlTxt3(String t_personalewallet_treasuryctrlTxt3) {
        this.t_personalewallet_treasuryctrlTxt3 = t_personalewallet_treasuryctrlTxt3;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

}