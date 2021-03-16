package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class TreasuryDBInfo implements Serializable {
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
   
   private String t_TreasuryDB_ID;

   private String t_TreasuryDB_OrgName;
   
   private BigDecimal t_TreasuryDB_Balance;

   private BigDecimal t_TreasuryDB_TotAmtMth;

   private BigDecimal t_TreasuryDB_BoPRatio;

   private BigDecimal t_TreasuryDB_PoPRatio;
	
   private BigDecimal t_TreasuryDB_RiskMargin;
   
   private BigDecimal t_TreasuryDB_Prooffund;

   private BigDecimal t_TreasuryDB_TotAmtDaily;
   
   private BigDecimal t_TreasuryDB_TotAmtDailySucc;

   private BigDecimal t_TreasuryDB_TotAmtDailyFail;

   private BigDecimal t_TreasuryDB_Fee;

   private BigDecimal t_TreasuryDB_OverdueInt;
   
   private Integer t_TreasuryDB_PayrollDate;
   
   private String t_TreasuryDB_Comment;

public String getCreator() {
	return creator;
}

public Date getCreate_time() {
	return create_time;
}

public String getModifier() {
	return modifier;
}

public Date getModify_time() {
	return modify_time;
}

public String getPlatform() {
	return platform;
}

public String getRemark() {
	return remark;
}

public String getT_TreasuryDB_ID() {
	return t_TreasuryDB_ID;
}

public String getT_TreasuryDB_OrgName() {
	return t_TreasuryDB_OrgName;
}

public BigDecimal getT_TreasuryDB_Balance() {
	return t_TreasuryDB_Balance;
}

public BigDecimal getT_TreasuryDB_TotAmtMth() {
	return t_TreasuryDB_TotAmtMth;
}

public BigDecimal getT_TreasuryDB_BoPRatio() {
	return t_TreasuryDB_BoPRatio;
}

public BigDecimal getT_TreasuryDB_PoPRatio() {
	return t_TreasuryDB_PoPRatio;
}

public BigDecimal getT_TreasuryDB_RiskMargin() {
	return t_TreasuryDB_RiskMargin;
}

public BigDecimal getT_TreasuryDB_Prooffund() {
	return t_TreasuryDB_Prooffund;
}

public BigDecimal getT_TreasuryDB_TotAmtDaily() {
	return t_TreasuryDB_TotAmtDaily;
}

public BigDecimal getT_TreasuryDB_TotAmtDailySucc() {
	return t_TreasuryDB_TotAmtDailySucc;
}

public BigDecimal getT_TreasuryDB_TotAmtDailyFail() {
	return t_TreasuryDB_TotAmtDailyFail;
}

public BigDecimal getT_TreasuryDB_Fee() {
	return t_TreasuryDB_Fee;
}

public BigDecimal getT_TreasuryDB_OverdueInt() {
	return t_TreasuryDB_OverdueInt;
}

public Integer getT_TreasuryDB_PayrollDate() {
	return t_TreasuryDB_PayrollDate;
}

public String getT_TreasuryDB_Comment() {
	return t_TreasuryDB_Comment;
}

public void setCreator(String creator) {
	this.creator = creator;
}

public void setCreate_time(Date create_time) {
	this.create_time = create_time;
}

public void setModifier(String modifier) {
	this.modifier = modifier;
}

public void setModify_time(Date modify_time) {
	this.modify_time = modify_time;
}

public void setPlatform(String platform) {
	this.platform = platform;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public void setT_TreasuryDB_ID(String t_TreasuryDB_ID) {
	this.t_TreasuryDB_ID = t_TreasuryDB_ID;
}

public void setT_TreasuryDB_OrgName(String t_TreasuryDB_OrgName) {
	this.t_TreasuryDB_OrgName = t_TreasuryDB_OrgName;
}

public void setT_TreasuryDB_Balance(BigDecimal t_TreasuryDB_Balance) {
	this.t_TreasuryDB_Balance = t_TreasuryDB_Balance;
}

public void setT_TreasuryDB_TotAmtMth(BigDecimal t_TreasuryDB_TotAmtMth) {
	this.t_TreasuryDB_TotAmtMth = t_TreasuryDB_TotAmtMth;
}

public void setT_TreasuryDB_BoPRatio(BigDecimal t_TreasuryDB_BoPRatio) {
	this.t_TreasuryDB_BoPRatio = t_TreasuryDB_BoPRatio;
}

public void setT_TreasuryDB_PoPRatio(BigDecimal t_TreasuryDB_PoPRatio) {
	this.t_TreasuryDB_PoPRatio = t_TreasuryDB_PoPRatio;
}

public void setT_TreasuryDB_RiskMargin(BigDecimal t_TreasuryDB_RiskMargin) {
	this.t_TreasuryDB_RiskMargin = t_TreasuryDB_RiskMargin;
}

public void setT_TreasuryDB_Prooffund(BigDecimal t_TreasuryDB_Prooffund) {
	this.t_TreasuryDB_Prooffund = t_TreasuryDB_Prooffund;
}

public void setT_TreasuryDB_TotAmtDaily(BigDecimal t_TreasuryDB_TotAmtDaily) {
	this.t_TreasuryDB_TotAmtDaily = t_TreasuryDB_TotAmtDaily;
}

public void setT_TreasuryDB_TotAmtDailySucc(
		BigDecimal t_TreasuryDB_TotAmtDailySucc) {
	this.t_TreasuryDB_TotAmtDailySucc = t_TreasuryDB_TotAmtDailySucc;
}

public void setT_TreasuryDB_TotAmtDailyFail(
		BigDecimal t_TreasuryDB_TotAmtDailyFail) {
	this.t_TreasuryDB_TotAmtDailyFail = t_TreasuryDB_TotAmtDailyFail;
}

public void setT_TreasuryDB_Fee(BigDecimal t_TreasuryDB_Fee) {
	this.t_TreasuryDB_Fee = t_TreasuryDB_Fee;
}

public void setT_TreasuryDB_OverdueInt(BigDecimal t_TreasuryDB_OverdueInt) {
	this.t_TreasuryDB_OverdueInt = t_TreasuryDB_OverdueInt;
}

public void setT_TreasuryDB_PayrollDate(Integer t_TreasuryDB_PayrollDate) {
	this.t_TreasuryDB_PayrollDate = t_TreasuryDB_PayrollDate;
}

public void setT_TreasuryDB_Comment(String t_TreasuryDB_Comment) {
	this.t_TreasuryDB_Comment = t_TreasuryDB_Comment;
}

}