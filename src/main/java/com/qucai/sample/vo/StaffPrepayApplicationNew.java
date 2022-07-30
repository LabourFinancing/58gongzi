package com.qucai.sample.vo;
//package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class StaffPrepayApplicationNew implements Serializable {
	
   private static final long serialVersionUID = 1;
   
   private String t_P_Name;
   
   private String t_P_PID;
   
   private String t_P_Company;
   
   private String t_P_CompanyNum;

	private String t_P_VendorEmployeeName;

   private String t_P_Mobil;
   
   private String t_P_PayrollDebitcardBankName;
   
   private BigDecimal t_P_CreditPrepaySalaryAmount;
   
   private Integer t_P_PayrollDate;
   
   private String t_P_Employmentstatus;
   
   private String t_P_EmploymentCategory;
   
   private String t_P_PayrollDebitcardNum;
   
   private String t_P_Probation;

	private String t_P_ProductCode;
   
   private BigDecimal t_P_SalaryBalance;
   
   private String t_FProd_ID;
   
   private String t_FProd_Name;
   
   private BigDecimal t_FProd_Interest; 
   
   private String t_FProd_OverdueInt; 
   
   private BigDecimal t_FProd_ServiceFee; 
   
   private BigDecimal t_FProd_Poundage;
   
   private BigDecimal t_FProd_ETxnAmtLimit;
   
   private BigDecimal t_P_CurrentCreditline;
   
   private String t_FProd_TierPoundage;
   
   private String t_FProd_Status;
   
   private Date t_FProd_SysupdateDate;
   
   private String t_FProd_category;
   
   private String t_FProd_PersPool;
   
   private Integer SMSCode;
   
   private String SMScodeRec;
   
   private String SeesionLoginMobil;
   
   private String t_FProd_Name_Select;

   private String t_FProd_CorpPool;

public String getT_P_Name() {
	return t_P_Name;
}

public String getT_P_PID() {
	return t_P_PID;
}

public String getT_P_Company() {
	return t_P_Company;
}

public String getT_P_CompanyNum() {
	return t_P_CompanyNum;
}

public String getT_P_Mobil() {
	return t_P_Mobil;
}

public String getT_P_PayrollDebitcardBankName() {
	return t_P_PayrollDebitcardBankName;
}

public BigDecimal getT_P_CreditPrepaySalaryAmount() {
	return t_P_CreditPrepaySalaryAmount;
}

public Integer getT_P_PayrollDate() {
	return t_P_PayrollDate;
}

public String getT_P_Employmentstatus() {
	return t_P_Employmentstatus;
}

public String getT_P_EmploymentCategory() {
	return t_P_EmploymentCategory;
}

public String getT_P_PayrollDebitcardNum() {
	return t_P_PayrollDebitcardNum;
}

public String getT_P_Probation() {
	return t_P_Probation;
}

public BigDecimal getT_P_SalaryBalance() {
	return t_P_SalaryBalance;
}

public String getT_FProd_ID() {
	return t_FProd_ID;
}

public String getT_FProd_Name() {
	return t_FProd_Name;
}

public BigDecimal getT_FProd_Interest() {
	return t_FProd_Interest;
}

public String getT_FProd_OverdueInt() {
	return t_FProd_OverdueInt;
}

public BigDecimal getT_FProd_ServiceFee() {
	return t_FProd_ServiceFee;
}

public BigDecimal getT_FProd_Poundage() {
	return t_FProd_Poundage;
}

public BigDecimal getT_FProd_ETxnAmtLimit() {
	return t_FProd_ETxnAmtLimit;
}

public String getT_FProd_TierPoundage() {
	return t_FProd_TierPoundage;
}

public String getT_FProd_Status() {
	return t_FProd_Status;
}

public Date getT_FProd_SysupdateDate() {
	return t_FProd_SysupdateDate;
}

public String getT_FProd_category() {
	return t_FProd_category;
}

public Integer getSMSCode() {
	return SMSCode;
}

public String getSMScodeRec() {
	return SMScodeRec;
}

public String getSeesionLoginMobil() {
	return SeesionLoginMobil;
}

public String getT_FProd_Name_Select() {
	return t_FProd_Name_Select;
}

public String getT_FProd_CorpPool() {
	return t_FProd_CorpPool;
}

public String getT_FProd_PersPool() {
	return t_FProd_PersPool;
}

public void setT_P_Name(String t_P_Name) {
	this.t_P_Name = t_P_Name;
}

public void setT_P_PID(String t_P_PID) {
	this.t_P_PID = t_P_PID;
}

public void setT_P_Company(String t_P_Company) {
	this.t_P_Company = t_P_Company;
}

public void setT_P_CompanyNum(String t_P_CompanyNum) {
	this.t_P_CompanyNum = t_P_CompanyNum;
}

public void setT_P_Mobil(String t_P_Mobil) {
	this.t_P_Mobil = t_P_Mobil;
}

public void setT_P_PayrollDebitcardBankName(String t_P_PayrollDebitcardBankName) {
	this.t_P_PayrollDebitcardBankName = t_P_PayrollDebitcardBankName;
}

public void setT_P_CreditPrepaySalaryAmount(
		BigDecimal t_P_CreditPrepaySalaryAmount) {
	this.t_P_CreditPrepaySalaryAmount = t_P_CreditPrepaySalaryAmount;
}

public void setT_P_PayrollDate(Integer t_P_PayrollDate) {
	this.t_P_PayrollDate = t_P_PayrollDate;
}

public void setT_P_Employmentstatus(String t_P_Employmentstatus) {
	this.t_P_Employmentstatus = t_P_Employmentstatus;
}

public void setT_P_EmploymentCategory(String t_P_EmploymentCategory) {
	this.t_P_EmploymentCategory = t_P_EmploymentCategory;
}

public void setT_P_PayrollDebitcardNum(String t_P_PayrollDebitcardNum) {
	this.t_P_PayrollDebitcardNum = t_P_PayrollDebitcardNum;
}

public void setT_P_Probation(String t_P_Probation) {
	this.t_P_Probation = t_P_Probation;
}

public void setT_P_SalaryBalance(
		BigDecimal t_P_SalaryBalance) {
	this.t_P_SalaryBalance = t_P_SalaryBalance;
}

public void setT_FProd_ID(String t_FProd_ID) {
	this.t_FProd_ID = t_FProd_ID;
}

public void setT_FProd_Name(String t_FProd_Name) {
	this.t_FProd_Name = t_FProd_Name;
}

public void setT_FProd_Interest(BigDecimal t_FProd_Interest) {
	this.t_FProd_Interest = t_FProd_Interest;
}

public void setT_FProd_OverdueInt(String t_FProd_OverdueInt) {
	this.t_FProd_OverdueInt = t_FProd_OverdueInt;
}

public void setT_FProd_ServiceFee(BigDecimal t_FProd_ServiceFee) {
	this.t_FProd_ServiceFee = t_FProd_ServiceFee;
}

public void setT_FProd_Poundage(BigDecimal t_FProd_Poundage) {
	this.t_FProd_Poundage = t_FProd_Poundage;
}

public void setT_FProd_ETxnAmtLimit(BigDecimal t_FProd_ETxnAmtLimit) {
	this.t_FProd_ETxnAmtLimit = t_FProd_ETxnAmtLimit;
}

public void setT_FProd_TierPoundage(String t_FProd_TierPoundage) {
	this.t_FProd_TierPoundage = t_FProd_TierPoundage;
}

public void setT_FProd_Status(String t_FProd_Status) {
	this.t_FProd_Status = t_FProd_Status;
}

public void setT_FProd_SysupdateDate(Date t_FProd_SysupdateDate) {
	this.t_FProd_SysupdateDate = t_FProd_SysupdateDate;
}

public void setT_FProd_category(String t_FProd_category) {
	this.t_FProd_category = t_FProd_category;
}

public void setSMSCode(Integer sMSCode) {
	SMSCode = sMSCode;
}

public void setSMScodeRec(String sMScodeRec) {
	SMScodeRec = sMScodeRec;
}

public void setSeesionLoginMobil(String seesionLoginMobil) {
	SeesionLoginMobil = seesionLoginMobil;
}

public void setT_FProd_Name_Select(String t_FProd_Name_Select) {
	this.t_FProd_Name_Select = t_FProd_Name_Select;
}

public void setT_FProd_PersPool(String t_FProd_PersPool) {
	this.t_FProd_PersPool = t_FProd_PersPool;
}

public void setT_FProd_CorpPool(String t_FProd_CorpPool) {
	this.t_FProd_CorpPool = t_FProd_CorpPool;
}


	public String getT_P_ProductCode() {
		return this.t_P_ProductCode;
	}

	public void setT_P_ProductCode(final String t_P_ProductCode) {
		this.t_P_ProductCode = t_P_ProductCode;
	}

	public String getT_P_VendorEmployeeName() {
		return this.t_P_VendorEmployeeName;
	}

	public void setT_P_VendorEmployeeName(final String t_P_VendorEmployeeName) {
		this.t_P_VendorEmployeeName = t_P_VendorEmployeeName;
	}


public BigDecimal getT_P_CurrentCreditline() {
	return t_P_CurrentCreditline;
}

public void setT_P_CurrentCreditline(BigDecimal t_P_CurrentCreditline) {
	this.t_P_CurrentCreditline = t_P_CurrentCreditline;
}
   
}