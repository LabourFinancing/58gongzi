package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class TxnQuery implements Serializable {
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

/* TxnQuery info char */
   private String t_TxnQuery_ID;
   
   private String t_TxnQuery_PID;

   private String t_TxnQuery_Mobil;
   
   private Date t_TxnQuery_Date;

   private String t_TxnQuery_ApprovalStatus;

   private String t_TxnQuery_CompanyName;

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
public String getT_TxnQuery_ID() {
	return t_TxnQuery_ID;
}
public String getT_TxnQuery_PID() {
	return t_TxnQuery_PID;
}

public String getT_TxnQuery_Mobil() {
	return t_TxnQuery_Mobil;
}

public Date getT_TxnQuery_Date() {
	return t_TxnQuery_Date;
}

public String getT_TxnQuery_ApprovalStatus() {
	return t_TxnQuery_ApprovalStatus;
}

public String getT_TxnQuery_CompanyName() {
	return t_TxnQuery_CompanyName;
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
public void setT_TxnQuery_ID(String t_TxnQuery_ID) {
	this.t_TxnQuery_ID = t_TxnQuery_ID;
}
public void setT_TxnQuery_PID(String t_TxnQuery_PID) {
	this.t_TxnQuery_PID = t_TxnQuery_PID;
}

public void setT_TxnQuery_Mobil(String t_TxnQuery_Mobil) {
	this.t_TxnQuery_Mobil = t_TxnQuery_Mobil;
}

public void setT_TxnQuery_Date(Date t_TxnQuery_Date) {
	this.t_TxnQuery_Date = t_TxnQuery_Date;
}

public void setT_TxnQuery_ApprovalStatus(String t_TxnQuery_ApprovalStatus) {
	this.t_TxnQuery_ApprovalStatus = t_TxnQuery_ApprovalStatus;
}

public void setT_TxnQuery_CompanyName(String t_TxnQuery_CompanyName) {
	this.t_TxnQuery_CompanyName = t_TxnQuery_CompanyName;
}

//----------------------------------------------
   
 
}