package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PaymentBatchPayUpload implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */

/* biz info char */
	
   private String batch_payment_batchID;

	private String batch_payment_fprodName;
   
   private String batch_payment_ewalletcat;

   private String batch_payment_ProdCat;

   private String batch_payment_PayrollName;

   private String batch_payment_Name;
   
   private String batch_payment_PID;
   
   private String batch_payment_company;
   
   private String batch_payment_vendorcompany;

   private String batch_payment_mobile;

   private String batch_payment_prevbatchID;
	
   private BigDecimal batch_payment_amt;
   
   private BigDecimal batch_payment_personalbalAmt;

   private BigDecimal batch_payment_serviceFee;
   
   private BigDecimal batch_payment_prevbalance;

   private String batch_payment_category;
   
   private String batch_paymentClearID;

   private String batch_paymentOrg;
   
   private String batch_payment_remark;

	private String batch_payment_comment;

	private String batch_payment_personalPool;

	private String batch_payment_OrgPool;

	private String batch_payment_bankcard;
   
   private Date batch_payment_starttime;

	private Date batch_payment_endtime;

	private String batch_payment_status;

	private Date batch_createtime;

	public String getBatch_payment_batchID() {
		return this.batch_payment_batchID;
	}

	public void setBatch_payment_batchID(final String batch_payment_batchID) {
		this.batch_payment_batchID = batch_payment_batchID;
	}

	public String getBatch_payment_fprodName() {
		return this.batch_payment_fprodName;
	}

	public void setBatch_payment_fprodName(final String batch_payment_fprodName) {
		this.batch_payment_fprodName = batch_payment_fprodName;
	}

	public String getBatch_payment_ewalletcat() {
		return this.batch_payment_ewalletcat;
	}

	public void setBatch_payment_ewalletcat(final String batch_payment_ewalletcat) {
		this.batch_payment_ewalletcat = batch_payment_ewalletcat;
	}

	public String getBatch_payment_ProdCat() {
		return this.batch_payment_ProdCat;
	}

	public void setBatch_payment_ProdCat(final String batch_payment_ProdCat) {
		this.batch_payment_ProdCat = batch_payment_ProdCat;
	}

	public String getBatch_payment_PayrollName() {
		return this.batch_payment_PayrollName;
	}

	public void setBatch_payment_PayrollName(final String batch_payment_PayrollName) {
		this.batch_payment_PayrollName = batch_payment_PayrollName;
	}

	public String getBatch_payment_Name() {
		return this.batch_payment_Name;
	}

	public void setBatch_payment_Name(final String batch_payment_Name) {
		this.batch_payment_Name = batch_payment_Name;
	}

	public String getBatch_payment_PID() {
		return this.batch_payment_PID;
	}

	public void setBatch_payment_PID(final String batch_payment_PID) {
		this.batch_payment_PID = batch_payment_PID;
	}

	public String getBatch_payment_company() {
		return this.batch_payment_company;
	}

	public void setBatch_payment_company(final String batch_payment_company) {
		this.batch_payment_company = batch_payment_company;
	}

	public String getBatch_payment_vendorcompany() {
		return this.batch_payment_vendorcompany;
	}

	public void setBatch_payment_vendorcompany(final String batch_payment_vendorcompany) {
		this.batch_payment_vendorcompany = batch_payment_vendorcompany;
	}

	public String getBatch_payment_mobile() {
		return this.batch_payment_mobile;
	}

	public void setBatch_payment_mobile(final String batch_payment_mobile) {
		this.batch_payment_mobile = batch_payment_mobile;
	}

	public String getBatch_payment_prevbatchID() {
		return this.batch_payment_prevbatchID;
	}

	public void setBatch_payment_prevbatchID(final String batch_payment_prevbatchID) {
		this.batch_payment_prevbatchID = batch_payment_prevbatchID;
	}

	public BigDecimal getBatch_payment_amt() {
		return this.batch_payment_amt;
	}

	public void setBatch_payment_amt(final BigDecimal batch_payment_amt) {
		this.batch_payment_amt = batch_payment_amt;
	}

	public BigDecimal getBatch_payment_personalbalAmt() {
		return this.batch_payment_personalbalAmt;
	}

	public void setBatch_payment_personalbalAmt(final BigDecimal batch_payment_personalbalAmt) {
		this.batch_payment_personalbalAmt = batch_payment_personalbalAmt;
	}

	public BigDecimal getBatch_payment_serviceFee() {
		return this.batch_payment_serviceFee;
	}

	public void setBatch_payment_serviceFee(final BigDecimal batch_payment_serviceFee) {
		this.batch_payment_serviceFee = batch_payment_serviceFee;
	}

	public BigDecimal getBatch_payment_prevbalance() {
		return this.batch_payment_prevbalance;
	}

	public void setBatch_payment_prevbalance(final BigDecimal batch_payment_prevbalance) {
		this.batch_payment_prevbalance = batch_payment_prevbalance;
	}

	public String getBatch_payment_category() {
		return this.batch_payment_category;
	}

	public void setBatch_payment_category(final String batch_payment_category) {
		this.batch_payment_category = batch_payment_category;
	}

	public String getBatch_paymentClearID() {
		return this.batch_paymentClearID;
	}

	public void setBatch_paymentClearID(final String batch_paymentClearID) {
		this.batch_paymentClearID = batch_paymentClearID;
	}

	public String getBatch_paymentOrg() {
		return this.batch_paymentOrg;
	}

	public void setBatch_paymentOrg(final String batch_paymentOrg) {
		this.batch_paymentOrg = batch_paymentOrg;
	}

	public String getBatch_payment_remark() {
		return this.batch_payment_remark;
	}

	public void setBatch_payment_remark(final String batch_payment_remark) {
		this.batch_payment_remark = batch_payment_remark;
	}

	public String getBatch_payment_comment() {
		return this.batch_payment_comment;
	}

	public void setBatch_payment_comment(final String batch_payment_comment) {
		this.batch_payment_comment = batch_payment_comment;
	}

	public String getBatch_payment_personalPool() {
		return this.batch_payment_personalPool;
	}

	public void setBatch_payment_personalPool(final String batch_payment_personalPool) {
		this.batch_payment_personalPool = batch_payment_personalPool;
	}

	public String getBatch_payment_OrgPool() {
		return this.batch_payment_OrgPool;
	}

	public void setBatch_payment_OrgPool(final String batch_payment_OrgPool) {
		this.batch_payment_OrgPool = batch_payment_OrgPool;
	}

	public String getBatch_payment_bankcard() {
		return this.batch_payment_bankcard;
	}

	public void setBatch_payment_bankcard(final String batch_payment_bankcard) {
		this.batch_payment_bankcard = batch_payment_bankcard;
	}

	public Date getBatch_payment_starttime() {
		return this.batch_payment_starttime;
	}

	public void setBatch_payment_starttime(final Date batch_payment_starttime) {
		this.batch_payment_starttime = batch_payment_starttime;
	}

	public Date getBatch_payment_endtime() {
		return this.batch_payment_endtime;
	}

	public void setBatch_payment_endtime(final Date batch_payment_endtime) {
		this.batch_payment_endtime = batch_payment_endtime;
	}

	public String getBatch_payment_status() {
		return this.batch_payment_status;
	}

	public void setBatch_payment_status(final String batch_payment_status) {
		this.batch_payment_status = batch_payment_status;
	}

	public Date getBatch_createtime() {
		return this.batch_createtime;
	}

	public void setBatch_createtime(final Date batch_createtime) {
		this.batch_createtime = batch_createtime;
	}
}