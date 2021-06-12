package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class TreasuryDBMain implements Serializable {
    /**
	 * 
	 */
  private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */
   private String creator;

   private Date create_time;

   private String modifier;

    private Date modify_time;
   
   private String platform;

   private String remark;

/* biz info char */
   
   private String t_TreasuryDB_Main_ID;

   private String t_TreasuryDB_Main_3rdprtyPaymentVendor;
   
   private String t_TreasuryDB_Main_PaymentClearNum;

   private BigDecimal t_TreasuryDB_Main_TotEwalletBalance;

   private BigDecimal t_TreasuryDB_Main_TotPrepayBalance;

   private BigDecimal t_TreasuryDB_Main_TotTopupAmt;
	
   private BigDecimal t_TreasuryDB_Main_TotCashoutAmt;
   
   private BigDecimal t_TreasuryDB_Main_TotCashinAmt;

   private BigDecimal t_TreasuryDB_Main_TotPymtCost;
   
   private BigDecimal t_TreasuryDB_Main_TotMarginCashinout;

   private BigDecimal t_TreasuryDB_Main_TotIncome1dayAhead;

   private BigDecimal t_TreasuryDB_Main_Interest1dayAhead;

   private BigDecimal t_TreasuryDB_Main_TotIncomeToday;

    private String t_TreasuryDB_Main_InterestToday;

    private String t_TreasuryDB_Main_OpCat;

    private String t_TreasuryDB_Main_ProdSeriesID;

    private String t_TreasuryDB_Main_PersonalCat;

    private BigDecimal t_TreasuryDB_Main_bkp1;

    private BigDecimal t_TreasuryDB_Main_bkp2;

    private BigDecimal t_TreasuryDB_Main_bkp3;

    private String t_TreasuryDB_Main_Txt1;

    private String t_TreasuryDB_Main_Txt2;

    private String t_TreasuryDB_Main_Txt3;


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getT_TreasuryDB_Main_ID() {
        return t_TreasuryDB_Main_ID;
    }

    public void setT_TreasuryDB_Main_ID(String t_TreasuryDB_Main_ID) {
        this.t_TreasuryDB_Main_ID = t_TreasuryDB_Main_ID;
    }

    public String getT_TreasuryDB_Main_3rdprtyPaymentVendor() {
        return t_TreasuryDB_Main_3rdprtyPaymentVendor;
    }

    public void setT_TreasuryDB_Main_3rdprtyPaymentVendor(String t_TreasuryDB_Main_3rdprtyPaymentVendor) {
        this.t_TreasuryDB_Main_3rdprtyPaymentVendor = t_TreasuryDB_Main_3rdprtyPaymentVendor;
    }

    public String getT_TreasuryDB_Main_PaymentClearNum() {
        return t_TreasuryDB_Main_PaymentClearNum;
    }

    public void setT_TreasuryDB_Main_PaymentClearNum(String t_TreasuryDB_Main_PaymentClearNum) {
        this.t_TreasuryDB_Main_PaymentClearNum = t_TreasuryDB_Main_PaymentClearNum;
    }

    public BigDecimal getT_TreasuryDB_Main_TotEwalletBalance() {
        return t_TreasuryDB_Main_TotEwalletBalance;
    }

    public void setT_TreasuryDB_Main_TotEwalletBalance(BigDecimal t_TreasuryDB_Main_TotEwalletBalance) {
        this.t_TreasuryDB_Main_TotEwalletBalance = t_TreasuryDB_Main_TotEwalletBalance;
    }

    public BigDecimal getT_TreasuryDB_Main_TotPrepayBalance() {
        return t_TreasuryDB_Main_TotPrepayBalance;
    }

    public void setT_TreasuryDB_Main_TotPrepayBalance(BigDecimal t_TreasuryDB_Main_TotPrepayBalance) {
        this.t_TreasuryDB_Main_TotPrepayBalance = t_TreasuryDB_Main_TotPrepayBalance;
    }

    public BigDecimal getT_TreasuryDB_Main_TotTopupAmt() {
        return t_TreasuryDB_Main_TotTopupAmt;
    }

    public void setT_TreasuryDB_Main_TotTopupAmt(BigDecimal t_TreasuryDB_Main_TotTopupAmt) {
        this.t_TreasuryDB_Main_TotTopupAmt = t_TreasuryDB_Main_TotTopupAmt;
    }

    public BigDecimal getT_TreasuryDB_Main_TotCashoutAmt() {
        return t_TreasuryDB_Main_TotCashoutAmt;
    }

    public void setT_TreasuryDB_Main_TotCashoutAmt(BigDecimal t_TreasuryDB_Main_TotCashoutAmt) {
        this.t_TreasuryDB_Main_TotCashoutAmt = t_TreasuryDB_Main_TotCashoutAmt;
    }

    public BigDecimal getT_TreasuryDB_Main_TotCashinAmt() {
        return t_TreasuryDB_Main_TotCashinAmt;
    }

    public void setT_TreasuryDB_Main_TotCashinAmt(BigDecimal t_TreasuryDB_Main_TotCashinAmt) {
        this.t_TreasuryDB_Main_TotCashinAmt = t_TreasuryDB_Main_TotCashinAmt;
    }

    public BigDecimal getT_TreasuryDB_Main_TotPymtCost() {
        return t_TreasuryDB_Main_TotPymtCost;
    }

    public void setT_TreasuryDB_Main_TotPymtCost(BigDecimal t_TreasuryDB_Main_TotPymtCost) {
        this.t_TreasuryDB_Main_TotPymtCost = t_TreasuryDB_Main_TotPymtCost;
    }

    public BigDecimal getT_TreasuryDB_Main_TotMarginCashinout() {
        return t_TreasuryDB_Main_TotMarginCashinout;
    }

    public void setT_TreasuryDB_Main_TotMarginCashinout(BigDecimal t_TreasuryDB_Main_TotMarginCashinout) {
        this.t_TreasuryDB_Main_TotMarginCashinout = t_TreasuryDB_Main_TotMarginCashinout;
    }

    public BigDecimal getT_TreasuryDB_Main_TotIncome1dayAhead() {
        return t_TreasuryDB_Main_TotIncome1dayAhead;
    }

    public void setT_TreasuryDB_Main_TotIncome1dayAhead(BigDecimal t_TreasuryDB_Main_TotIncome1dayAhead) {
        this.t_TreasuryDB_Main_TotIncome1dayAhead = t_TreasuryDB_Main_TotIncome1dayAhead;
    }

    public BigDecimal getT_TreasuryDB_Main_Interest1dayAhead() {
        return t_TreasuryDB_Main_Interest1dayAhead;
    }

    public void setT_TreasuryDB_Main_Interest1dayAhead(BigDecimal t_TreasuryDB_Main_Interest1dayAhead) {
        this.t_TreasuryDB_Main_Interest1dayAhead = t_TreasuryDB_Main_Interest1dayAhead;
    }

    public BigDecimal getT_TreasuryDB_Main_TotIncomeToday() {
        return t_TreasuryDB_Main_TotIncomeToday;
    }

    public void setT_TreasuryDB_Main_TotIncomeToday(BigDecimal t_TreasuryDB_Main_TotIncomeToday) {
        this.t_TreasuryDB_Main_TotIncomeToday = t_TreasuryDB_Main_TotIncomeToday;
    }

    public String getT_TreasuryDB_Main_InterestToday() {
        return t_TreasuryDB_Main_InterestToday;
    }

    public void setT_TreasuryDB_Main_InterestToday(String t_TreasuryDB_Main_InterestToday) {
        this.t_TreasuryDB_Main_InterestToday = t_TreasuryDB_Main_InterestToday;
    }

    public String getT_TreasuryDB_Main_OpCat() {
        return t_TreasuryDB_Main_OpCat;
    }

    public void setT_TreasuryDB_Main_OpCat(String t_TreasuryDB_Main_OpCat) {
        this.t_TreasuryDB_Main_OpCat = t_TreasuryDB_Main_OpCat;
    }

    public String getT_TreasuryDB_Main_ProdSeriesID() {
        return t_TreasuryDB_Main_ProdSeriesID;
    }

    public void setT_TreasuryDB_Main_ProdSeriesID(String t_TreasuryDB_Main_ProdSeriesID) {
        this.t_TreasuryDB_Main_ProdSeriesID = t_TreasuryDB_Main_ProdSeriesID;
    }

    public String getT_TreasuryDB_Main_PersonalCat() {
        return t_TreasuryDB_Main_PersonalCat;
    }

    public void setT_TreasuryDB_Main_PersonalCat(String t_TreasuryDB_Main_PersonalCat) {
        this.t_TreasuryDB_Main_PersonalCat = t_TreasuryDB_Main_PersonalCat;
    }

    public BigDecimal getT_TreasuryDB_Main_bkp1() {
        return t_TreasuryDB_Main_bkp1;
    }

    public void setT_TreasuryDB_Main_bkp1(BigDecimal t_TreasuryDB_Main_bkp1) {
        this.t_TreasuryDB_Main_bkp1 = t_TreasuryDB_Main_bkp1;
    }

    public BigDecimal getT_TreasuryDB_Main_bkp2() {
        return t_TreasuryDB_Main_bkp2;
    }

    public void setT_TreasuryDB_Main_bkp2(BigDecimal t_TreasuryDB_Main_bkp2) {
        this.t_TreasuryDB_Main_bkp2 = t_TreasuryDB_Main_bkp2;
    }

    public BigDecimal getT_TreasuryDB_Main_bkp3() {
        return t_TreasuryDB_Main_bkp3;
    }

    public void setT_TreasuryDB_Main_bkp3(BigDecimal t_TreasuryDB_Main_bkp3) {
        this.t_TreasuryDB_Main_bkp3 = t_TreasuryDB_Main_bkp3;
    }

    public String getT_TreasuryDB_Main_Txt1() {
        return t_TreasuryDB_Main_Txt1;
    }

    public void setT_TreasuryDB_Main_Txt1(String t_TreasuryDB_Main_Txt1) {
        this.t_TreasuryDB_Main_Txt1 = t_TreasuryDB_Main_Txt1;
    }

    public String getT_TreasuryDB_Main_Txt2() {
        return t_TreasuryDB_Main_Txt2;
    }

    public void setT_TreasuryDB_Main_Txt2(String t_TreasuryDB_Main_Txt2) {
        this.t_TreasuryDB_Main_Txt2 = t_TreasuryDB_Main_Txt2;
    }

    public String getT_TreasuryDB_Main_Txt3() {
        return t_TreasuryDB_Main_Txt3;
    }

    public void setT_TreasuryDB_Main_Txt3(String t_TreasuryDB_Main_Txt3) {
        this.t_TreasuryDB_Main_Txt3 = t_TreasuryDB_Main_Txt3;
    }
}