package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class StaffPrepayApplicationList implements Serializable {
    /**
	 * 
	 */
   private static final long serialVersionUID = -1876948347463745808L;
   
   private String t_Txn_ID;
   
   private String t_Txn_Num;
   
   private String t_Txn_ClearNum;
   
   private String t_Txn_ClearOrg;
   
   private String t_Txn_PrepayApplierName;
   
   private String t_Txn_PrepayApplierPID;
   
   private String t_Txn_Mobil;
   
   private Date t_Txn_PrepayDate;
   
   private String t_Txn_ProdName;
   
   private Integer t_Txn_PrepayDays;
   
   private BigDecimal t_Txn_CreditPrepayCurrentNum;
   
   private BigDecimal t_Txn_ApplyPrepayAmount;
   
   private BigDecimal t_Txn_CreditPrepayBalanceNum;
   
   private BigDecimal t_Txn_TotalPrepayNum;
   
   private Integer t_Txn_TotalInterestDays;
   
   private Integer t_Txn_PrepayCounts;
   
   private BigDecimal t_Txn_Interest;
   
   private BigDecimal t_Txn_BalanceCreditNum;
   
   private Date t_Txn_OverdueRepaymentDate;
   
   private String t_Txn_PrepayClear;
   
   private String t_Txn_Overdue;
   
   private Integer t_Txn_OverdueDays;
   
   private BigDecimal t_Txn_OverdueTotalAmount;
   
   private BigDecimal t_Txn_FinancialInterest;
   
   private BigDecimal t_Txn_ServiceFee;
   
   private BigDecimal t_Txn_Poundage;
   
   private BigDecimal t_Txn_TierPoundage;
   
   private BigDecimal t_Txn_InterestMargin;
   
   private String t_Txn_TitleName;
   
   private String t_Txn_BankAcc;
   
   private Date t_Txn_SysUpdateDate;
   
   private String t_Txn_Paystatus;
   
   private String t_Txn_SMS;
   
   private String t_Txn_SMSRec;
   
   private String remark;
   
   private String platform;
   
   private String creator;
   
   private String agreement;
   
   private Date create_time;
   
   private Date modify_time;
   
   private String seesionLoginMobil;

public String getT_Txn_ID() {
	return t_Txn_ID;
}

public String getT_Txn_Num() {
	return t_Txn_Num;
}

public String getT_Txn_ClearNum() {
	return t_Txn_ClearNum;
}

public String getT_Txn_ClearOrg() {
	return t_Txn_ClearOrg;
}

public String getT_Txn_PrepayApplierName() {
	return t_Txn_PrepayApplierName;
}

public String getT_Txn_PrepayApplierPID() {
	return t_Txn_PrepayApplierPID;
}

public String getT_Txn_Mobil() {
	return t_Txn_Mobil;
}

public Date getT_Txn_PrepayDate() {
	return t_Txn_PrepayDate;
}

public String getT_Txn_ProdName() {
	return t_Txn_ProdName;
}

public Integer getT_Txn_PrepayDays() {
	return t_Txn_PrepayDays;
}

public BigDecimal getT_Txn_CreditPrepayCurrentNum() {
	return t_Txn_CreditPrepayCurrentNum;
}

public BigDecimal getT_Txn_ApplyPrepayAmount() {
	return t_Txn_ApplyPrepayAmount;
}

public BigDecimal getT_Txn_CreditPrepayBalanceNum() {
	return t_Txn_CreditPrepayBalanceNum;
}

public BigDecimal getT_Txn_TotalPrepayNum() {
	return t_Txn_TotalPrepayNum;
}

public Integer getT_Txn_TotalInterestDays() {
	return t_Txn_TotalInterestDays;
}

public Integer getT_Txn_PrepayCounts() {
	return t_Txn_PrepayCounts;
}

public BigDecimal getT_Txn_Interest() {
	return t_Txn_Interest;
}

public BigDecimal getT_Txn_BalanceCreditNum() {
	return t_Txn_BalanceCreditNum;
}

public Date getT_Txn_OverdueRepaymentDate() {
	return t_Txn_OverdueRepaymentDate;
}

public String getT_Txn_PrepayClear() {
	return t_Txn_PrepayClear;
}

public String getT_Txn_Overdue() {
	return t_Txn_Overdue;
}

public Integer getT_Txn_OverdueDays() {
	return t_Txn_OverdueDays;
}

public BigDecimal getT_Txn_OverdueTotalAmount() {
	return t_Txn_OverdueTotalAmount;
}

public BigDecimal getT_Txn_FinancialInterest() {
	return t_Txn_FinancialInterest;
}

public BigDecimal getT_Txn_ServiceFee() {
	return t_Txn_ServiceFee;
}

public BigDecimal getT_Txn_Poundage() {
	return t_Txn_Poundage;
}

public BigDecimal getT_Txn_TierPoundage() {
	return t_Txn_TierPoundage;
}

public BigDecimal getT_Txn_InterestMargin() {
	return t_Txn_InterestMargin;
}

public String getT_Txn_TitleName() {
	return t_Txn_TitleName;
}

public String getT_Txn_BankAcc() {
	return t_Txn_BankAcc;
}

public Date getT_Txn_SysUpdateDate() {
	return t_Txn_SysUpdateDate;
}

public String getT_Txn_Paystatus() {
	return t_Txn_Paystatus;
}

public String getT_Txn_SMS() {
	return t_Txn_SMS;
}

public String getT_Txn_SMSRec() {
	return t_Txn_SMSRec;
}

public String getRemark() {
	return remark;
}

public String getPlatform() {
	return platform;
}

public String getCreator() {
	return creator;
}

public String getAgreement() {
	return agreement;
}

public Date getCreate_time() {
	return create_time;
}

public Date getModify_time() {
	return modify_time;
}

public String getSeesionLoginMobil() {
	return seesionLoginMobil;
}

public void setT_Txn_ID(String t_Txn_ID) {
	this.t_Txn_ID = t_Txn_ID;
}

public void setT_Txn_Num(String t_Txn_Num) {
	this.t_Txn_Num = t_Txn_Num;
}

public void setT_Txn_ClearNum(String t_Txn_ClearNum) {
	this.t_Txn_ClearNum = t_Txn_ClearNum;
}

public void setT_Txn_ClearOrg(String t_Txn_ClearOrg) {
	this.t_Txn_ClearOrg = t_Txn_ClearOrg;
}

public void setT_Txn_PrepayApplierName(String t_Txn_PrepayApplierName) {
	this.t_Txn_PrepayApplierName = t_Txn_PrepayApplierName;
}

public void setT_Txn_PrepayApplierPID(String t_Txn_PrepayApplierPID) {
	this.t_Txn_PrepayApplierPID = t_Txn_PrepayApplierPID;
}

public void setT_Txn_Mobil(String t_Txn_Mobil) {
	this.t_Txn_Mobil = t_Txn_Mobil;
}

public void setT_Txn_PrepayDate(Date t_Txn_PrepayDate) {
	this.t_Txn_PrepayDate = t_Txn_PrepayDate;
}

public void setT_Txn_ProdName(String t_Txn_ProdName) {
	this.t_Txn_ProdName = t_Txn_ProdName;
}

public void setT_Txn_PrepayDays(Integer t_Txn_PrepayDays) {
	this.t_Txn_PrepayDays = t_Txn_PrepayDays;
}

public void setT_Txn_CreditPrepayCurrentNum(
		BigDecimal t_Txn_CreditPrepayCurrentNum) {
	this.t_Txn_CreditPrepayCurrentNum = t_Txn_CreditPrepayCurrentNum;
}

public void setT_Txn_ApplyPrepayAmount(BigDecimal t_Txn_ApplyPrepayAmount) {
	this.t_Txn_ApplyPrepayAmount = t_Txn_ApplyPrepayAmount;
}

public void setT_Txn_CreditPrepayBalanceNum(
		BigDecimal t_Txn_CreditPrepayBalanceNum) {
	this.t_Txn_CreditPrepayBalanceNum = t_Txn_CreditPrepayBalanceNum;
}

public void setT_Txn_TotalPrepayNum(BigDecimal t_Txn_TotalPrepayNum) {
	this.t_Txn_TotalPrepayNum = t_Txn_TotalPrepayNum;
}

public void setT_Txn_TotalInterestDays(Integer t_Txn_TotalInterestDays) {
	this.t_Txn_TotalInterestDays = t_Txn_TotalInterestDays;
}

public void setT_Txn_PrepayCounts(Integer t_Txn_PrepayCounts) {
	this.t_Txn_PrepayCounts = t_Txn_PrepayCounts;
}

public void setT_Txn_Interest(BigDecimal t_Txn_Interest) {
	this.t_Txn_Interest = t_Txn_Interest;
}

public void setT_Txn_BalanceCreditNum(BigDecimal t_Txn_BalanceCreditNum) {
	this.t_Txn_BalanceCreditNum = t_Txn_BalanceCreditNum;
}

public void setT_Txn_OverdueRepaymentDate(Date t_Txn_OverdueRepaymentDate) {
	this.t_Txn_OverdueRepaymentDate = t_Txn_OverdueRepaymentDate;
}

public void setT_Txn_PrepayClear(String t_Txn_PrepayClear) {
	this.t_Txn_PrepayClear = t_Txn_PrepayClear;
}

public void setT_Txn_Overdue(String t_Txn_Overdue) {
	this.t_Txn_Overdue = t_Txn_Overdue;
}

public void setT_Txn_OverdueDays(Integer t_Txn_OverdueDays) {
	this.t_Txn_OverdueDays = t_Txn_OverdueDays;
}

public void setT_Txn_OverdueTotalAmount(BigDecimal t_Txn_OverdueTotalAmount) {
	this.t_Txn_OverdueTotalAmount = t_Txn_OverdueTotalAmount;
}

public void setT_Txn_FinancialInterest(BigDecimal t_Txn_FinancialInterest) {
	this.t_Txn_FinancialInterest = t_Txn_FinancialInterest;
}

public void setT_Txn_ServiceFee(BigDecimal t_Txn_ServiceFee) {
	this.t_Txn_ServiceFee = t_Txn_ServiceFee;
}

public void setT_Txn_Poundage(BigDecimal t_Txn_Poundage) {
	this.t_Txn_Poundage = t_Txn_Poundage;
}

public void setT_Txn_TierPoundage(BigDecimal t_Txn_TierPoundage) {
	this.t_Txn_TierPoundage = t_Txn_TierPoundage;
}

public void setT_Txn_InterestMargin(BigDecimal t_Txn_InterestMargin) {
	this.t_Txn_InterestMargin = t_Txn_InterestMargin;
}

public void setT_Txn_TitleName(String t_Txn_TitleName) {
	this.t_Txn_TitleName = t_Txn_TitleName;
}

public void setT_Txn_BankAcc(String t_Txn_BankAcc) {
	this.t_Txn_BankAcc = t_Txn_BankAcc;
}

public void setT_Txn_SysUpdateDate(Date t_Txn_SysUpdateDate) {
	this.t_Txn_SysUpdateDate = t_Txn_SysUpdateDate;
}

public void setT_Txn_Paystatus(String t_Txn_Paystatus) {
	this.t_Txn_Paystatus = t_Txn_Paystatus;
}

public void setT_Txn_SMS(String t_Txn_SMS) {
	this.t_Txn_SMS = t_Txn_SMS;
}

public void setT_Txn_SMSRec(String t_Txn_SMSRec) {
	this.t_Txn_SMSRec = t_Txn_SMSRec;
}

public void setRemark(String remark) {
	this.remark = remark;
}

public void setPlatform(String platform) {
	this.platform = platform;
}

public void setCreator(String creator) {
	this.creator = creator;
}

public void setAgreement(String agreement) {
	this.agreement = agreement;
}

public void setCreate_time(Date create_time) {
	this.create_time = create_time;
}

public void setModify_time(Date modify_time) {
	this.modify_time = modify_time;
}

public void setSeesionLoginMobil(String seesionLoginMobil) {
	this.seesionLoginMobil = seesionLoginMobil;
}
	
}