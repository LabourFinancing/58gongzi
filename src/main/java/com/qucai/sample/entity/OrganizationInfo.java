package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class OrganizationInfo implements Serializable {
    /**
	 */
   private static final long serialVersionUID = -1876948347463745808L;
   /**
    */
   private String t_O_ID;
   /**
    */
   private String t_O_CertificationCode;
   /**
    */
   private String t_O_OrgName;
   
   /*
    */
   private String t_O_EmployeeAmount;
	
   /**
    */
   private String t_O_Category;

   /**
    */
   private String t_O_listOrg;
   /**
    */
   private String t_O_OrgRepresentative;

   /**
    */
   private String t_O_OrgPayrollBankaccount;
   /**
    */
   private String t_O_OrgChinaebiAcc;
   /**
    */
   private String t_O_OrgSandeAcc;

   /**
    */
   private String t_O_OrgStatus;
   /**
    */
   private String t_O_OrgPending;

   /**
    */
   private Date t_O_SysUpdateDate;
   /**
    */
   private String platform;
   /**
    */
   private String remark;
   /**
    */
   private String creator;
   /**
    */
   private Date create_time;
   /**
    */
   private String modifier;
   /**
    */
   private Date modify_time;
   
public String getT_O_ID() {
	return t_O_ID;
}
public String getT_O_CertificationCode() {
	return t_O_CertificationCode;
}
public String getT_O_OrgName() {
	return t_O_OrgName;
}
public String getT_O_EmployeeAmount() {
	return t_O_EmployeeAmount;
}
public String getT_O_Category() {
	return t_O_Category;
}
public String getT_O_listOrg() {
	return t_O_listOrg;
}
public String getT_O_OrgRepresentative() {
	return t_O_OrgRepresentative;
}
public String getT_O_OrgPayrollBankaccount() {
	return t_O_OrgPayrollBankaccount;
}
public String getT_O_OrgStatus() {
	return t_O_OrgStatus;
}
public Date getT_O_SysUpdateDate() {
	return t_O_SysUpdateDate;
}
public String getPlatform() {
	return platform;
}
public String getRemark() {
	return remark;
}
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
public void setT_O_ID(String t_O_ID) {
	this.t_O_ID = t_O_ID;
}
public void setT_O_CertificationCode(String t_O_CertificationCode) {
	this.t_O_CertificationCode = t_O_CertificationCode;
}
public void setT_O_OrgName(String t_O_OrgName) {
	this.t_O_OrgName = t_O_OrgName;
}
public void setT_O_EmployeeAmount(String t_O_EmployeeAmount) {
	this.t_O_EmployeeAmount = t_O_EmployeeAmount;
}
public void setT_O_Category(String t_O_Category) {
	this.t_O_Category = t_O_Category;
}
public void setT_O_listOrg(String t_O_listOrg) {
	this.t_O_listOrg = t_O_listOrg;
}
public void setT_O_OrgRepresentative(String t_O_OrgRepresentative) {
	this.t_O_OrgRepresentative = t_O_OrgRepresentative;
}
public void setT_O_OrgPayrollBankaccount(String t_O_OrgPayrollBankaccount) {
	this.t_O_OrgPayrollBankaccount = t_O_OrgPayrollBankaccount;
}
public void setT_O_OrgStatus(String t_O_OrgStatus) {
	this.t_O_OrgStatus = t_O_OrgStatus;
}
public void setT_O_SysUpdateDate(Date t_O_SysUpdateDate) {
	this.t_O_SysUpdateDate = t_O_SysUpdateDate;
}
public void setPlatform(String platform) {
	this.platform = platform;
}
public void setRemark(String remark) {
	this.remark = remark;
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
public String getT_O_OrgChinaebiAcc() {
	return t_O_OrgChinaebiAcc;
}
public String getT_O_OrgSandeAcc() {
	return t_O_OrgSandeAcc;
}
public String getT_O_OrgPending() {
	return t_O_OrgPending;
}
public void setT_O_OrgChinaebiAcc(String t_O_OrgChinaebiAcc) {
	this.t_O_OrgChinaebiAcc = t_O_OrgChinaebiAcc;
}
public void setT_O_OrgSandeAcc(String t_O_OrgSandeAcc) {
	this.t_O_OrgSandeAcc = t_O_OrgSandeAcc;
}
public void setT_O_OrgPending(String t_O_OrgPending) {
	this.t_O_OrgPending = t_O_OrgPending;
}

}