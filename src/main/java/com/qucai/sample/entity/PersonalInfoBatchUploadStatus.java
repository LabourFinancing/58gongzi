package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PersonalInfoBatchUploadStatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */

/* biz info char */
	
   private String t_batch_perslUploadId;
   
   private String t_batch_perslUploadStatus_id;
   
   private String t_batch_perslUploadStatus_batchid;
   
   private Integer t_batch_perslUploadPer_num;

   private String t_batch_perslUploadEffectStatus;
   
   private String t_batch_perslUploadClearStatus;
   
   private String t_batch_company;
   
   private String t_batch_vendorCompany;

   private String t_batch_persProdName;

   private BigDecimal t_batch_payroll_tot;
	
   private BigDecimal t_batch_payrollCredit_tot;

   private String t_batch_remark;
   
   private String t_batch_clearInfo;

   private String t_batch_category;

   private String t_batch_comment;
   
   private String t_batch_progress;
   
   private Date t_batch_EffectStartDate;
   
   private Date t_batch_EffectEndDate;
   
   private Date modify_time;
   
   private String modifier;
   
   private Date create_time;
   
   private String creator;

public String getT_batch_perslUploadId() {
	return t_batch_perslUploadId;
}

public void setT_batch_perslUploadId(String t_batch_perslUploadId) {
	this.t_batch_perslUploadId = t_batch_perslUploadId;
}

public String getT_batch_perslUploadStatus_id() {
	return t_batch_perslUploadStatus_id;
}

public void setT_batch_perslUploadStatus_id(String t_batch_perslUploadStatus_id) {
	this.t_batch_perslUploadStatus_id = t_batch_perslUploadStatus_id;
}

public String getT_batch_perslUploadStatus_batchid() {
	return t_batch_perslUploadStatus_batchid;
}

public void setT_batch_perslUploadStatus_batchid(
		String t_batch_perslUploadStatus_batchid) {
	this.t_batch_perslUploadStatus_batchid = t_batch_perslUploadStatus_batchid;
}

public Integer getT_batch_perslUploadPer_num() {
	return t_batch_perslUploadPer_num;
}

public void setT_batch_perslUploadPer_num(Integer t_batch_perslUploadPer_num) {
	this.t_batch_perslUploadPer_num = t_batch_perslUploadPer_num;
}

public String getT_batch_perslUploadEffectStatus() {
	return t_batch_perslUploadEffectStatus;
}

public void setT_batch_perslUploadEffectStatus(
		String t_batch_perslUploadEffectStatus) {
	this.t_batch_perslUploadEffectStatus = t_batch_perslUploadEffectStatus;
}

public String getT_batch_perslUploadClearStatus() {
	return t_batch_perslUploadClearStatus;
}

public void setT_batch_perslUploadClearStatus(
		String t_batch_perslUploadClearStatus) {
	this.t_batch_perslUploadClearStatus = t_batch_perslUploadClearStatus;
}

public String getT_batch_company() {
	return t_batch_company;
}

public void setT_batch_company(String t_batch_company) {
	this.t_batch_company = t_batch_company;
}

public String getT_batch_vendorCompany() {
	return t_batch_vendorCompany;
}

public void setT_batch_vendorCompany(String t_batch_vendorCompany) {
	this.t_batch_vendorCompany = t_batch_vendorCompany;
}

public String getT_batch_persProdName() {
	return t_batch_persProdName;
}

public void setT_batch_persProdName(String t_batch_persProdName) {
	this.t_batch_persProdName = t_batch_persProdName;
}

public BigDecimal getT_batch_payroll_tot() {
	return t_batch_payroll_tot;
}

public void setT_batch_payroll_tot(BigDecimal t_batch_payroll_tot) {
	this.t_batch_payroll_tot = t_batch_payroll_tot;
}

public BigDecimal getT_batch_payrollCredit_tot() {
	return t_batch_payrollCredit_tot;
}

public void setT_batch_payrollCredit_tot(BigDecimal t_batch_payrollCredit_tot) {
	this.t_batch_payrollCredit_tot = t_batch_payrollCredit_tot;
}

public String getT_batch_remark() {
	return t_batch_remark;
}

public void setT_batch_remark(String t_batch_remark) {
	this.t_batch_remark = t_batch_remark;
}

public String getT_batch_clearInfo() {
	return t_batch_clearInfo;
}

public void setT_batch_clearInfo(String t_batch_clearInfo) {
	this.t_batch_clearInfo = t_batch_clearInfo;
}

public String getT_batch_category() {
	return t_batch_category;
}

public void setT_batch_category(String t_batch_category) {
	this.t_batch_category = t_batch_category;
}

public String getT_batch_comment() {
	return t_batch_comment;
}

public void setT_batch_comment(String t_batch_comment) {
	this.t_batch_comment = t_batch_comment;
}

public String getT_batch_progress() {
	return t_batch_progress;
}

public void setT_batch_progress(String t_batch_progress) {
	this.t_batch_progress = t_batch_progress;
}

public Date getT_batch_EffectStartDate() {
	return t_batch_EffectStartDate;
}

public void setT_batch_EffectStartDate(Date t_batch_EffectStartDate) {
	this.t_batch_EffectStartDate = t_batch_EffectStartDate;
}

public Date getT_batch_EffectEndDate() {
	return t_batch_EffectEndDate;
}

public void setT_batch_EffectEndDate(Date t_batch_EffectEndDate) {
	this.t_batch_EffectEndDate = t_batch_EffectEndDate;
}

public Date getModify_time() {
	return modify_time;
}

public void setModify_time(Date modify_time) {
	this.modify_time = modify_time;
}

public String getModifier() {
	return modifier;
}

public void setModifier(String modifier) {
	this.modifier = modifier;
}

public Date getCreate_time() {
	return create_time;
}

public void setCreate_time(Date create_time) {
	this.create_time = create_time;
}

public String getCreator() {
	return creator;
}

public void setCreator(String creator) {
	this.creator = creator;
}

 
}