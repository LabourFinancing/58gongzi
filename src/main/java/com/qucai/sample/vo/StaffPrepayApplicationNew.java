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

	private BigDecimal t_P_NetBaseSalary;
   
   private String t_FProd_ID;
   
   private String t_FProd_Name;

   private String t_FProd_OrgInfo;

   private String t_FProd_VendorOrgName;
   
   private BigDecimal t_FProd_Interest; 
   
   private String t_FProd_OverdueInt; 
   
   private BigDecimal t_FProd_ServiceFee; 
   
   private BigDecimal t_FProd_Poundage;
   
   private BigDecimal t_FProd_ETxnAmtLimit;
   
   private BigDecimal t_P_CurrentCreditline;
   
   private String t_FProd_TierPoundage;

   private String t_FProd_MainCat;
   
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
		return this.t_P_Name;
	}

	public void setT_P_Name(final String t_P_Name) {
		this.t_P_Name = t_P_Name;
	}

	public String getT_P_PID() {
		return this.t_P_PID;
	}

	public void setT_P_PID(final String t_P_PID) {
		this.t_P_PID = t_P_PID;
	}

	public String getT_P_Company() {
		return this.t_P_Company;
	}

	public void setT_P_Company(final String t_P_Company) {
		this.t_P_Company = t_P_Company;
	}

	public String getT_P_CompanyNum() {
		return this.t_P_CompanyNum;
	}

	public void setT_P_CompanyNum(final String t_P_CompanyNum) {
		this.t_P_CompanyNum = t_P_CompanyNum;
	}

	public String getT_P_VendorEmployeeName() {
		return this.t_P_VendorEmployeeName;
	}

	public void setT_P_VendorEmployeeName(final String t_P_VendorEmployeeName) {
		this.t_P_VendorEmployeeName = t_P_VendorEmployeeName;
	}

	public String getT_P_Mobil() {
		return this.t_P_Mobil;
	}

	public void setT_P_Mobil(final String t_P_Mobil) {
		this.t_P_Mobil = t_P_Mobil;
	}

	public String getT_P_PayrollDebitcardBankName() {
		return this.t_P_PayrollDebitcardBankName;
	}

	public void setT_P_PayrollDebitcardBankName(final String t_P_PayrollDebitcardBankName) {
		this.t_P_PayrollDebitcardBankName = t_P_PayrollDebitcardBankName;
	}

	public BigDecimal getT_P_CreditPrepaySalaryAmount() {
		return this.t_P_CreditPrepaySalaryAmount;
	}

	public void setT_P_CreditPrepaySalaryAmount(final BigDecimal t_P_CreditPrepaySalaryAmount) {
		this.t_P_CreditPrepaySalaryAmount = t_P_CreditPrepaySalaryAmount;
	}

	public Integer getT_P_PayrollDate() {
		return this.t_P_PayrollDate;
	}

	public void setT_P_PayrollDate(final Integer t_P_PayrollDate) {
		this.t_P_PayrollDate = t_P_PayrollDate;
	}

	public String getT_P_Employmentstatus() {
		return this.t_P_Employmentstatus;
	}

	public void setT_P_Employmentstatus(final String t_P_Employmentstatus) {
		this.t_P_Employmentstatus = t_P_Employmentstatus;
	}

	public String getT_P_EmploymentCategory() {
		return this.t_P_EmploymentCategory;
	}

	public void setT_P_EmploymentCategory(final String t_P_EmploymentCategory) {
		this.t_P_EmploymentCategory = t_P_EmploymentCategory;
	}

	public String getT_P_PayrollDebitcardNum() {
		return this.t_P_PayrollDebitcardNum;
	}

	public void setT_P_PayrollDebitcardNum(final String t_P_PayrollDebitcardNum) {
		this.t_P_PayrollDebitcardNum = t_P_PayrollDebitcardNum;
	}

	public String getT_P_Probation() {
		return this.t_P_Probation;
	}

	public void setT_P_Probation(final String t_P_Probation) {
		this.t_P_Probation = t_P_Probation;
	}

	public String getT_P_ProductCode() {
		return this.t_P_ProductCode;
	}

	public void setT_P_ProductCode(final String t_P_ProductCode) {
		this.t_P_ProductCode = t_P_ProductCode;
	}

	public BigDecimal getT_P_SalaryBalance() {
		return this.t_P_SalaryBalance;
	}

	public void setT_P_SalaryBalance(final BigDecimal t_P_SalaryBalance) {
		this.t_P_SalaryBalance = t_P_SalaryBalance;
	}

	public BigDecimal getT_P_NetBaseSalary() {
		return this.t_P_NetBaseSalary;
	}

	public void setT_P_NetBaseSalary(final BigDecimal t_P_NetBaseSalary) {
		this.t_P_NetBaseSalary = t_P_NetBaseSalary;
	}

	public String getT_FProd_ID() {
		return this.t_FProd_ID;
	}

	public void setT_FProd_ID(final String t_FProd_ID) {
		this.t_FProd_ID = t_FProd_ID;
	}

	public String getT_FProd_Name() {
		return this.t_FProd_Name;
	}

	public void setT_FProd_Name(final String t_FProd_Name) {
		this.t_FProd_Name = t_FProd_Name;
	}

	public String getT_FProd_OrgInfo() {
		return this.t_FProd_OrgInfo;
	}

	public void setT_FProd_OrgInfo(final String t_FProd_OrgInfo) {
		this.t_FProd_OrgInfo = t_FProd_OrgInfo;
	}

	public String getT_FProd_VendorOrgName() {
		return this.t_FProd_VendorOrgName;
	}

	public void setT_FProd_VendorOrgName(final String t_FProd_VendorOrgName) {
		this.t_FProd_VendorOrgName = t_FProd_VendorOrgName;
	}

	public BigDecimal getT_FProd_Interest() {
		return this.t_FProd_Interest;
	}

	public void setT_FProd_Interest(final BigDecimal t_FProd_Interest) {
		this.t_FProd_Interest = t_FProd_Interest;
	}

	public String getT_FProd_OverdueInt() {
		return this.t_FProd_OverdueInt;
	}

	public void setT_FProd_OverdueInt(final String t_FProd_OverdueInt) {
		this.t_FProd_OverdueInt = t_FProd_OverdueInt;
	}

	public BigDecimal getT_FProd_ServiceFee() {
		return this.t_FProd_ServiceFee;
	}

	public void setT_FProd_ServiceFee(final BigDecimal t_FProd_ServiceFee) {
		this.t_FProd_ServiceFee = t_FProd_ServiceFee;
	}

	public BigDecimal getT_FProd_Poundage() {
		return this.t_FProd_Poundage;
	}

	public void setT_FProd_Poundage(final BigDecimal t_FProd_Poundage) {
		this.t_FProd_Poundage = t_FProd_Poundage;
	}

	public BigDecimal getT_FProd_ETxnAmtLimit() {
		return this.t_FProd_ETxnAmtLimit;
	}

	public void setT_FProd_ETxnAmtLimit(final BigDecimal t_FProd_ETxnAmtLimit) {
		this.t_FProd_ETxnAmtLimit = t_FProd_ETxnAmtLimit;
	}

	public BigDecimal getT_P_CurrentCreditline() {
		return this.t_P_CurrentCreditline;
	}

	public void setT_P_CurrentCreditline(final BigDecimal t_P_CurrentCreditline) {
		this.t_P_CurrentCreditline = t_P_CurrentCreditline;
	}

	public String getT_FProd_TierPoundage() {
		return this.t_FProd_TierPoundage;
	}

	public void setT_FProd_TierPoundage(final String t_FProd_TierPoundage) {
		this.t_FProd_TierPoundage = t_FProd_TierPoundage;
	}

	public String getT_FProd_MainCat() {
		return this.t_FProd_MainCat;
	}

	public void setT_FProd_MainCat(final String t_FProd_MainCat) {
		this.t_FProd_MainCat = t_FProd_MainCat;
	}

	public String getT_FProd_Status() {
		return this.t_FProd_Status;
	}

	public void setT_FProd_Status(final String t_FProd_Status) {
		this.t_FProd_Status = t_FProd_Status;
	}

	public Date getT_FProd_SysupdateDate() {
		return this.t_FProd_SysupdateDate;
	}

	public void setT_FProd_SysupdateDate(final Date t_FProd_SysupdateDate) {
		this.t_FProd_SysupdateDate = t_FProd_SysupdateDate;
	}

	public String getT_FProd_category() {
		return this.t_FProd_category;
	}

	public void setT_FProd_category(final String t_FProd_category) {
		this.t_FProd_category = t_FProd_category;
	}

	public String getT_FProd_PersPool() {
		return this.t_FProd_PersPool;
	}

	public void setT_FProd_PersPool(final String t_FProd_PersPool) {
		this.t_FProd_PersPool = t_FProd_PersPool;
	}

	public Integer getSMSCode() {
		return this.SMSCode;
	}

	public void setSMSCode(final Integer SMSCode) {
		this.SMSCode = SMSCode;
	}

	public String getSMScodeRec() {
		return this.SMScodeRec;
	}

	public void setSMScodeRec(final String SMScodeRec) {
		this.SMScodeRec = SMScodeRec;
	}

	public String getSeesionLoginMobil() {
		return this.SeesionLoginMobil;
	}

	public void setSeesionLoginMobil(final String seesionLoginMobil) {
		this.SeesionLoginMobil = seesionLoginMobil;
	}

	public String getT_FProd_Name_Select() {
		return this.t_FProd_Name_Select;
	}

	public void setT_FProd_Name_Select(final String t_FProd_Name_Select) {
		this.t_FProd_Name_Select = t_FProd_Name_Select;
	}

	public String getT_FProd_CorpPool() {
		return this.t_FProd_CorpPool;
	}

	public void setT_FProd_CorpPool(final String t_FProd_CorpPool) {
		this.t_FProd_CorpPool = t_FProd_CorpPool;
	}

}