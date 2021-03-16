package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class TreasuryInfo implements Serializable {
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
   
   private String t_Treasury_ID;

   private String t_Treasury_OrgName;
   
   private String t_Treasury_OrgNum;

   private String t_Treasury_OrgBankNum;

   private Date t_Treasury_PayDate;

   private BigDecimal t_Treasury_PayAmount;
	
   private BigDecimal t_Treasury_OrgPayAmnt;

   private BigDecimal t_Treasury_AccPayAmt;
   
   private Date t_Treasury_AccPayDate;

   private String t_Treasury_AccBankNum;

   private String t_Treasury_PrepayAccNum;

   private BigDecimal t_Treasury_FinanceAmt;
   
   private String t_Treasury_FinanceOrgName;

   private String t_Treasury_FinanceOrgBankNum;

   private BigDecimal t_Treasury_FinanceInterest;

   private BigDecimal t_Treasury_ProfitSharePct;
	
   private BigDecimal t_Treasury_TotIntMargin;

   private BigDecimal t_Treasury_FinanceMargin;
	
   private String t_Treasury_Remark;

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

public String getT_Treasury_ID() {
	return t_Treasury_ID;
}

public String getT_Treasury_OrgName() {
	return t_Treasury_OrgName;
}

public String getT_Treasury_OrgNum() {
	return t_Treasury_OrgNum;
}

public String getT_Treasury_OrgBankNum() {
	return t_Treasury_OrgBankNum;
}

public Date getT_Treasury_PayDate() {
	return t_Treasury_PayDate;
}

public BigDecimal getT_Treasury_PayAmount() {
	return t_Treasury_PayAmount;
}

public BigDecimal getT_Treasury_OrgPayAmnt() {
	return t_Treasury_OrgPayAmnt;
}

public BigDecimal getT_Treasury_AccPayAmt() {
	return t_Treasury_AccPayAmt;
}

public Date getT_Treasury_AccPayDate() {
	return t_Treasury_AccPayDate;
}

public String getT_Treasury_AccBankNum() {
	return t_Treasury_AccBankNum;
}

public String getT_Treasury_PrepayAccNum() {
	return t_Treasury_PrepayAccNum;
}

public BigDecimal getT_Treasury_FinanceAmt() {
	return t_Treasury_FinanceAmt;
}

public String getT_Treasury_FinanceOrgName() {
	return t_Treasury_FinanceOrgName;
}

public String getT_Treasury_FinanceOrgBankNum() {
	return t_Treasury_FinanceOrgBankNum;
}

public BigDecimal getT_Treasury_FinanceInterest() {
	return t_Treasury_FinanceInterest;
}

public BigDecimal getT_Treasury_ProfitSharePct() {
	return t_Treasury_ProfitSharePct;
}

public BigDecimal getT_Treasury_TotIntMargin() {
	return t_Treasury_TotIntMargin;
}

public BigDecimal getT_Treasury_FinanceMargin() {
	return t_Treasury_FinanceMargin;
}

public String getT_Treasury_Remark() {
	return t_Treasury_Remark;
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

public void setT_Treasury_ID(String t_Treasury_ID) {
	this.t_Treasury_ID = t_Treasury_ID;
}

public void setT_Treasury_OrgName(String t_Treasury_OrgName) {
	this.t_Treasury_OrgName = t_Treasury_OrgName;
}

public void setT_Treasury_OrgNum(String t_Treasury_OrgNum) {
	this.t_Treasury_OrgNum = t_Treasury_OrgNum;
}

public void setT_Treasury_OrgBankNum(String t_Treasury_OrgBankNum) {
	this.t_Treasury_OrgBankNum = t_Treasury_OrgBankNum;
}

public void setT_Treasury_PayDate(Date t_Treasury_PayDate) {
	this.t_Treasury_PayDate = t_Treasury_PayDate;
}

public void setT_Treasury_PayAmount(BigDecimal t_Treasury_PayAmount) {
	this.t_Treasury_PayAmount = t_Treasury_PayAmount;
}

public void setT_Treasury_OrgPayAmnt(BigDecimal t_Treasury_OrgPayAmnt) {
	this.t_Treasury_OrgPayAmnt = t_Treasury_OrgPayAmnt;
}

public void setT_Treasury_AccPayAmt(BigDecimal t_Treasury_AccPayAmt) {
	this.t_Treasury_AccPayAmt = t_Treasury_AccPayAmt;
}

public void setT_Treasury_AccPayDate(Date t_Treasury_AccPayDate) {
	this.t_Treasury_AccPayDate = t_Treasury_AccPayDate;
}

public void setT_Treasury_AccBankNum(String t_Treasury_AccBankNum) {
	this.t_Treasury_AccBankNum = t_Treasury_AccBankNum;
}

public void setT_Treasury_PrepayAccNum(String t_Treasury_PrepayAccNum) {
	this.t_Treasury_PrepayAccNum = t_Treasury_PrepayAccNum;
}

public void setT_Treasury_FinanceAmt(BigDecimal t_Treasury_FinanceAmt) {
	this.t_Treasury_FinanceAmt = t_Treasury_FinanceAmt;
}

public void setT_Treasury_FinanceOrgName(String t_Treasury_FinanceOrgName) {
	this.t_Treasury_FinanceOrgName = t_Treasury_FinanceOrgName;
}

public void setT_Treasury_FinanceOrgBankNum(String t_Treasury_FinanceOrgBankNum) {
	this.t_Treasury_FinanceOrgBankNum = t_Treasury_FinanceOrgBankNum;
}

public void setT_Treasury_FinanceInterest(BigDecimal t_Treasury_FinanceInterest) {
	this.t_Treasury_FinanceInterest = t_Treasury_FinanceInterest;
}

public void setT_Treasury_ProfitSharePct(BigDecimal t_Treasury_ProfitSharePct) {
	this.t_Treasury_ProfitSharePct = t_Treasury_ProfitSharePct;
}

public void setT_Treasury_TotIntMargin(BigDecimal t_Treasury_TotIntMargin) {
	this.t_Treasury_TotIntMargin = t_Treasury_TotIntMargin;
}

public void setT_Treasury_FinanceMargin(BigDecimal t_Treasury_FinanceMargin) {
	this.t_Treasury_FinanceMargin = t_Treasury_FinanceMargin;
}

public void setT_Treasury_Remark(String t_Treasury_Remark) {
	this.t_Treasury_Remark = t_Treasury_Remark;
}



//----------------------------------------------
   
 
}