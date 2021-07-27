package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PersonalInfo implements Serializable {
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
   
   private String t_P_id;

   private String t_P_Name;
   
   private String t_P_Sex;

   private Integer t_P_Age;

   private String t_P_PID;

   private String t_P_Marriage;
	
   private String t_P_HomeAddress;

   private String t_P_Mobil;
   
   private String t_P_Phone;

   private String t_P_Spouse;

   private String t_P_Spouse_Phone;

   private String t_P_Contact1;
   
   private String t_P_Contact1Mobile;

   private String t_P_Payroll;

   private String t_P_Contact2Mobile;

   private String t_P_Company;
	
   private String t_P_CompanyNum;

   private BigDecimal t_P_SocialSecurityBaseAmount;
	
   private String t_P_ProvidentFund;

   private String t_P_Employmentstatus;
   
   private String t_P_EmploymentCategory;

   private Integer t_P_WorkYears;

   private String t_P_Probation;

   private String t_P_VendorEmployment;
   
   private String t_P_VendorEmployeeName;

   private String t_P_PayrollDebitcardBankName;

   private String t_P_PayrollDebitcardNum;

   private String t_P_NetBaseSalary;
	
   private BigDecimal t_P_CreditPrepaySalaryAmount;

   private Integer t_P_PayrollDate;
   
   private BigDecimal t_P_NetMonthlyBonusAmount;

   private Integer t_P_NetBonusPayDate;
	
   private Integer t_P_SocialSecurityDate;

   private Date t_P_SysUpdateDate;
   

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

public String getT_P_id() {
	return t_P_id;
}

public String getT_P_Name() {
	return t_P_Name;
}

public String getT_P_Sex() {
	return t_P_Sex;
}

public Integer getT_P_Age() {
	return t_P_Age;
}

public String getT_P_PID() {
	return t_P_PID;
}

public String getT_P_Marriage() {
	return t_P_Marriage;
}

public String getT_P_HomeAddress() {
	return t_P_HomeAddress;
}

public String getT_P_Mobil() {
	return t_P_Mobil;
}

public String getT_P_Phone() {
	return t_P_Phone;
}

public String getT_P_Spouse() {
	return t_P_Spouse;
}

public String getT_P_Spouse_Phone() {
	return t_P_Spouse_Phone;
}

public String getT_P_Contact1() {
	return t_P_Contact1;
}

public String getT_P_Contact1Mobil() {
	return t_P_Contact1Mobile;
}

public String getT_P_Payroll() {
	return t_P_Payroll;
}

public String getT_P_ContactMobile2() {
	return t_P_Contact2Mobile;
}

public String getT_P_Company() {
	return t_P_Company;
}

public String getT_P_CompanyNum() {
	return t_P_CompanyNum;
}

public BigDecimal getT_P_SocialSecurityBaseAmount() {
	return t_P_SocialSecurityBaseAmount;
}

public String getT_P_ProvidentFund() {
	return t_P_ProvidentFund;
}

public String getT_P_Employmentstatus() {
	return t_P_Employmentstatus;
}

public String getT_P_EmploymentCategory() {
	return t_P_EmploymentCategory;
}

public Integer getT_P_WorkYears() {
	return t_P_WorkYears;
}

public String getT_P_Probation() {
	return t_P_Probation;
}

public String getT_P_VendorEmployment() {
	return t_P_VendorEmployment;
}

public String getT_P_VendorEmployeeName() {
	return t_P_VendorEmployeeName;
}

public String getT_P_PayrollDebitcardBankName() {
	return t_P_PayrollDebitcardBankName;
}

public String getT_P_PayrollDebitcardNum() {
	return t_P_PayrollDebitcardNum;
}

public String getT_P_NetBaseSalary() {
	return t_P_NetBaseSalary;
}

public BigDecimal getT_P_CreditPrepaySalaryAmount() {
	return t_P_CreditPrepaySalaryAmount;
}

public Integer getT_P_PayrollDate() {
	return t_P_PayrollDate;
}

public BigDecimal getT_P_NetMonthlyBonusAmount() {
	return t_P_NetMonthlyBonusAmount;
}

public Integer getT_P_NetBonusPayDate() {
	return t_P_NetBonusPayDate;
}

public Integer getT_P_SocialSecurityDate() {
	return t_P_SocialSecurityDate;
}

public Date getT_P_SysUpdateDate() {
	return t_P_SysUpdateDate;
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

public void setT_P_id(String t_P_id) {
	this.t_P_id = t_P_id;
}

public void setT_P_Name(String t_P_Name) {
	this.t_P_Name = t_P_Name;
}

public void setT_P_Sex(String t_P_Sex) {
	this.t_P_Sex = t_P_Sex;
}

public void setT_P_Age(Integer t_P_Age) {
	this.t_P_Age = t_P_Age;
}

public void setT_P_PID(String t_P_PID) {
	this.t_P_PID = t_P_PID;
}

public void setT_P_Marriage(String t_P_Marriage) {
	this.t_P_Marriage = t_P_Marriage;
}

public void setT_P_HomeAddress(String t_P_HomeAddress) {
	this.t_P_HomeAddress = t_P_HomeAddress;
}

public void setT_P_Mobil(String t_P_Mobil) {
	this.t_P_Mobil = t_P_Mobil;
}

public void setT_P_Phone(String t_P_Phone) {
	this.t_P_Phone = t_P_Phone;
}

public void setT_P_Spouse(String t_P_Spouse) {
	this.t_P_Spouse = t_P_Spouse;
}

public void setT_P_Spouse_Phone(String t_P_Spouse_Phone) {
	this.t_P_Spouse_Phone = t_P_Spouse_Phone;
}

public void setT_P_Contact1(String t_P_Contact1) {
	this.t_P_Contact1 = t_P_Contact1;
}

public void setT_P_Contact1Mobil(String t_P_Contact1Mobile) {
	this.t_P_Contact1Mobile = t_P_Contact1Mobile;
}

public void setT_P_Payroll(String t_P_Payroll) {
	this.t_P_Payroll = t_P_Payroll;
}

public void setT_P_ContactMobile2(String t_P_Contact2Mobile) {
	this.t_P_Contact2Mobile = t_P_Contact2Mobile;
}

public void setT_P_Company(String t_P_Company) {
	this.t_P_Company = t_P_Company;
}

public void setT_P_CompanyNum(String t_P_CompanyNum) {
	this.t_P_CompanyNum = t_P_CompanyNum;
}

public void setT_P_SocialSecurityBaseAmount(
		BigDecimal t_P_SocialSecurityBaseAmount) {
	this.t_P_SocialSecurityBaseAmount = t_P_SocialSecurityBaseAmount;
}

public void setT_P_ProvidentFund(String t_P_ProvidentFund) {
	this.t_P_ProvidentFund = t_P_ProvidentFund;
}

public void setT_P_Employmentstatus(String t_P_Employmentstatus) {
	this.t_P_Employmentstatus = t_P_Employmentstatus;
}

public void setT_P_EmploymentCategory(String t_P_EmploymentCategory) {
	this.t_P_EmploymentCategory = t_P_EmploymentCategory;
}

public void setT_P_WorkYears(Integer t_P_WorkYears) {
	this.t_P_WorkYears = t_P_WorkYears;
}

public void setT_P_Probation(String t_P_Probation) {
	this.t_P_Probation = t_P_Probation;
}

public void setT_P_VendorEmployment(String t_P_VendorEmployment) {
	this.t_P_VendorEmployment = t_P_VendorEmployment;
}

public void setT_P_VendorEmployeeName(String t_P_VendorEmployeeName) {
	this.t_P_VendorEmployeeName = t_P_VendorEmployeeName;
}

public void setT_P_PayrollDebitcardBankName(String t_P_PayrollDebitcardBankName) {
	this.t_P_PayrollDebitcardBankName = t_P_PayrollDebitcardBankName;
}

public void setT_P_PayrollDebitcardNum(String t_P_PayrollDebitcardNum) {
	this.t_P_PayrollDebitcardNum = t_P_PayrollDebitcardNum;
}

public void setT_P_NetBaseSalary(String t_P_NetBaseSalary) {
	this.t_P_NetBaseSalary = t_P_NetBaseSalary;
}

public void setT_P_CreditPrepaySalaryAmount(
		BigDecimal t_P_CreditPrepaySalaryAmount) {
	this.t_P_CreditPrepaySalaryAmount = t_P_CreditPrepaySalaryAmount;
}

public void setT_P_PayrollDate(Integer t_P_PayrollDate) {
	this.t_P_PayrollDate = t_P_PayrollDate;
}

public void setT_P_NetMonthlyBonusAmount(BigDecimal t_P_NetMonthlyBonusAmount) {
	this.t_P_NetMonthlyBonusAmount = t_P_NetMonthlyBonusAmount;
}

public void setT_P_NetBonusPayDate(Integer t_P_NetBonusPayDate) {
	this.t_P_NetBonusPayDate = t_P_NetBonusPayDate;
}

public void setT_P_SocialSecurityDate(Integer t_P_SocialSecurityDate) {
	this.t_P_SocialSecurityDate = t_P_SocialSecurityDate;
}

public void setT_P_SysUpdateDate(Date t_P_SysUpdateDate) {
	this.t_P_SysUpdateDate = t_P_SysUpdateDate;
}


//----------------------------------------------
   
 
}