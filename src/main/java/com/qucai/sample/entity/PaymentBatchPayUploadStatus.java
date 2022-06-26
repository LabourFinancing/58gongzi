package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PaymentBatchPayUploadStatus implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */

/* biz info char */
	
   private String t_batch_paymentUploadId;
   
   private String t_batch_paymentUploadStatus_id;

   private String t_batch_paymenetUploadStatus_batchid;
   
   private Integer t_batch_paymentUploadPer_num;

   private String t_batch_paymentUploadEffectStatus;
   
   private String t_batch_paymentUploadClearStatus;
   
   private String t_batch_paymentcompany;
   
   private String t_batch_paymentvendorCompany;

   private String t_batch_paymentProdName;

   private BigDecimal t_batch_paymentpayroll_tot;
	
   private BigDecimal t_batch_paymentpayrollCredit_tot;

   private String t_batch_paymentremark;
   
   private String t_batch_paymentclearInfo;

   private String t_batch_paymentcategory;

   private String t_batch_paymentcomment;
   
   private String t_batch_paymentprogress;
   
   private Date t_batch_paymentEffectStartDate;
   
   private Date t_batch_paymentEffectEndDate;
   
   private Date modify_time;
   
   private String modifier;
   
   private Date create_time;
   
   private String creator;


	public static long getSerialVersionUID() {
		return PaymentBatchPayUploadStatus.serialVersionUID;
	}

	public String getT_batch_paymentUploadId() {
		return this.t_batch_paymentUploadId;
	}

	public void setT_batch_paymentUploadId(final String t_batch_paymentUploadId) {
		this.t_batch_paymentUploadId = t_batch_paymentUploadId;
	}

	public String getT_batch_paymentUploadStatus_id() {
		return this.t_batch_paymentUploadStatus_id;
	}

	public void setT_batch_paymentUploadStatus_id(final String t_batch_paymentUploadStatus_id) {
		this.t_batch_paymentUploadStatus_id = t_batch_paymentUploadStatus_id;
	}

	public String getT_batch_paymenetUploadStatus_batchid() {
		return this.t_batch_paymenetUploadStatus_batchid;
	}

	public void setT_batch_paymenetUploadStatus_batchid(final String t_batch_paymenetUploadStatus_batchid) {
		this.t_batch_paymenetUploadStatus_batchid = t_batch_paymenetUploadStatus_batchid;
	}

	public Integer getT_batch_paymentUploadPer_num() {
		return this.t_batch_paymentUploadPer_num;
	}

	public void setT_batch_paymentUploadPer_num(final Integer t_batch_paymentUploadPer_num) {
		this.t_batch_paymentUploadPer_num = t_batch_paymentUploadPer_num;
	}

	public String getT_batch_paymentUploadEffectStatus() {
		return this.t_batch_paymentUploadEffectStatus;
	}

	public void setT_batch_paymentUploadEffectStatus(final String t_batch_paymentUploadEffectStatus) {
		this.t_batch_paymentUploadEffectStatus = t_batch_paymentUploadEffectStatus;
	}

	public String getT_batch_paymentUploadClearStatus() {
		return this.t_batch_paymentUploadClearStatus;
	}

	public void setT_batch_paymentUploadClearStatus(final String t_batch_paymentUploadClearStatus) {
		this.t_batch_paymentUploadClearStatus = t_batch_paymentUploadClearStatus;
	}

	public String getT_batch_paymentcompany() {
		return this.t_batch_paymentcompany;
	}

	public void setT_batch_paymentcompany(final String t_batch_paymentcompany) {
		this.t_batch_paymentcompany = t_batch_paymentcompany;
	}

	public String getT_batch_paymentvendorCompany() {
		return this.t_batch_paymentvendorCompany;
	}

	public void setT_batch_paymentvendorCompany(final String t_batch_paymentvendorCompany) {
		this.t_batch_paymentvendorCompany = t_batch_paymentvendorCompany;
	}

	public String getT_batch_paymentProdName() {
		return this.t_batch_paymentProdName;
	}

	public void setT_batch_paymentProdName(final String t_batch_paymentProdName) {
		this.t_batch_paymentProdName = t_batch_paymentProdName;
	}

	public BigDecimal getT_batch_paymentpayroll_tot() {
		return this.t_batch_paymentpayroll_tot;
	}

	public void setT_batch_paymentpayroll_tot(final BigDecimal t_batch_paymentpayroll_tot) {
		this.t_batch_paymentpayroll_tot = t_batch_paymentpayroll_tot;
	}

	public BigDecimal getT_batch_paymentpayrollCredit_tot() {
		return this.t_batch_paymentpayrollCredit_tot;
	}

	public void setT_batch_paymentpayrollCredit_tot(final BigDecimal t_batch_paymentpayrollCredit_tot) {
		this.t_batch_paymentpayrollCredit_tot = t_batch_paymentpayrollCredit_tot;
	}

	public String getT_batch_paymentremark() {
		return this.t_batch_paymentremark;
	}

	public void setT_batch_paymentremark(final String t_batch_paymentremark) {
		this.t_batch_paymentremark = t_batch_paymentremark;
	}

	public String getT_batch_paymentclearInfo() {
		return this.t_batch_paymentclearInfo;
	}

	public void setT_batch_paymentclearInfo(final String t_batch_paymentclearInfo) {
		this.t_batch_paymentclearInfo = t_batch_paymentclearInfo;
	}

	public String getT_batch_paymentcategory() {
		return this.t_batch_paymentcategory;
	}

	public void setT_batch_paymentcategory(final String t_batch_paymentcategory) {
		this.t_batch_paymentcategory = t_batch_paymentcategory;
	}

	public String getT_batch_paymentcomment() {
		return this.t_batch_paymentcomment;
	}

	public void setT_batch_paymentcomment(final String t_batch_paymentcomment) {
		this.t_batch_paymentcomment = t_batch_paymentcomment;
	}

	public String getT_batch_paymentprogress() {
		return this.t_batch_paymentprogress;
	}

	public void setT_batch_paymentprogress(final String t_batch_paymentprogress) {
		this.t_batch_paymentprogress = t_batch_paymentprogress;
	}

	public Date getT_batch_paymentEffectStartDate() {
		return this.t_batch_paymentEffectStartDate;
	}

	public void setT_batch_paymentEffectStartDate(final Date t_batch_paymentEffectStartDate) {
		this.t_batch_paymentEffectStartDate = t_batch_paymentEffectStartDate;
	}

	public Date getT_batch_paymentEffectEndDate() {
		return this.t_batch_paymentEffectEndDate;
	}

	public void setT_batch_paymentEffectEndDate(final Date t_batch_paymentEffectEndDate) {
		this.t_batch_paymentEffectEndDate = t_batch_paymentEffectEndDate;
	}

	public Date getModify_time() {
		return this.modify_time;
	}

	public void setModify_time(final Date modify_time) {
		this.modify_time = modify_time;
	}

	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(final String modifier) {
		this.modifier = modifier;
	}

	public Date getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(final Date create_time) {
		this.create_time = create_time;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(final String creator) {
		this.creator = creator;
	}


}