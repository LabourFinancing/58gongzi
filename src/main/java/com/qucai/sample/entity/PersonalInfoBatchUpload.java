package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PersonalInfoBatchUpload implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */

/* biz info char */
	
   private String batch_PB_ID;
   
   private String batch_PB_batchID;
   
   private String batch_PB_Name;
   
   private String batch_PB_PID;

	private String batch_PB_ProdName;

   private String batch_PB_company;
   
   private String batch_PB_vendorcompany;
   
   private String batch_PB_fprod;
   
   private Integer batch_PB_payrolldate;

   private String batch_PB_creditCard;

   private String batch_PB_mobile;
	
   private BigDecimal batch_PB_credit;
   
   private BigDecimal batch_PB_balance;

   private String batch_PB_clearCategory;
   
   private Date batch_PB_effectDate;

   private Date batch_PB_endDate;
   
   private String batch_PB_flag;

   private String batch_PB_uploadRemark;
   
   private String batch_creator;
   
   private Date batch_createtime;

public String getBatch_PB_batchID() {
	return batch_PB_batchID;
}

public String getBatch_PB_Name() {
	return batch_PB_Name;
}

public String getBatch_PB_PID() {
	return batch_PB_PID;
}

public String getBatch_PB_company() {
	return batch_PB_company;
}

public String getBatch_PB_creditCard() {
	return batch_PB_creditCard;
}

public String getBatch_PB_mobile() {
	return batch_PB_mobile;
}

public BigDecimal getBatch_PB_credit() {
	return batch_PB_credit;
}

public String getBatch_PB_clearCategory() {
	return batch_PB_clearCategory;
}

public Date getBatch_PB_effectDate() {
	return batch_PB_effectDate;
}

public Date getBatch_PB_endDate() {
	return batch_PB_endDate;
}

public String getBatch_PB_uploadRemark() {
	return batch_PB_uploadRemark;
}

public String getBatch_creator() {
	return batch_creator;
}

public Date getBatch_createtime() {
	return batch_createtime;
}

public void setBatch_PB_batchID(String batch_PB_batchID) {
	 this.batch_PB_batchID = batch_PB_batchID;
}

public void setBatch_PB_Name(String batch_PB_Name) {
	this.batch_PB_Name = batch_PB_Name;
}

public void setBatch_PB_PID(String batch_PB_PID) {
	this.batch_PB_PID = batch_PB_PID;
}

public void setBatch_PB_company(String batch_PB_company) {
	this.batch_PB_company = batch_PB_company;
}

public void setBatch_PB_creditCard(String batch_PB_creditCard) {
	this.batch_PB_creditCard = batch_PB_creditCard;
}

public void setBatch_PB_mobile(String batch_PB_mobile) {
	this.batch_PB_mobile = batch_PB_mobile;
}

public void setBatch_PB_credit(BigDecimal batch_PB_credit) {
	this.batch_PB_credit = batch_PB_credit;
}

public void setBatch_PB_clearCategory(String batch_PB_clearCategory) {
	this.batch_PB_clearCategory = batch_PB_clearCategory;
}

public void setBatch_PB_effectDate(Date batch_PB_effectDate) {
	this.batch_PB_effectDate = batch_PB_effectDate;
}

public void setBatch_PB_endDate(Date batch_PB_endDate) {
	this.batch_PB_endDate = batch_PB_endDate;
}

public void setBatch_PB_uploadRemark(String batch_PB_uploadRemark) {
	this.batch_PB_uploadRemark = batch_PB_uploadRemark;
}

public void setBatch_creator(String batch_creator) {
	this.batch_creator = batch_creator;
}

public void setBatch_createtime(Date batch_createtime) {
	this.batch_createtime = batch_createtime;
}

public String getBatch_PB_vendorcompany() {
	return batch_PB_vendorcompany;
}

public void setBatch_PB_vendorcompany(String batch_PB_vendorcompany) {
	this.batch_PB_vendorcompany = batch_PB_vendorcompany;
}

public Integer getBatch_PB_payrolldate() {
	return batch_PB_payrolldate;
}

public void setBatch_PB_payrolldate(Integer batch_PB_payrolldate) {
	this.batch_PB_payrolldate = batch_PB_payrolldate;
}

public String getBatch_PB_ID() {
	return batch_PB_ID;
}

public void setBatch_PB_ID(String batch_PB_ID) {
	this.batch_PB_ID = batch_PB_ID;
}

public String getBatch_PB_fprod() {
	return batch_PB_fprod;
}

public void setBatch_PB_fprod(String batch_PB_fprod) {
	this.batch_PB_fprod = batch_PB_fprod;
}

public BigDecimal getBatch_PB_balance() {
	return batch_PB_balance;
}

public void setBatch_PB_balance(BigDecimal batch_PB_balance) {
	this.batch_PB_balance = batch_PB_balance;
}

public String getBatch_PB_flag() {
	return batch_PB_flag;
}

public void setBatch_PB_flag(String batch_PB_flag) {
	this.batch_PB_flag = batch_PB_flag;
}


	public String getBatch_PB_ProdName() {
		return this.batch_PB_ProdName;
	}

	public void setBatch_PB_ProdName(final String batch_PB_ProdName) {
		this.batch_PB_ProdName = batch_PB_ProdName;
	}


 
}