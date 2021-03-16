package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class Paymentvendormgt implements Serializable {
    /**
	 * 
	 */
  private static long serialVersionUID = -1876948347463745808L;
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
   
   private String t_Pymt_ID;

   private String t_Pymt_CertificationCode;
   
   private String t_Pymt_Name;

   private String t_Pymt_OrgInfo;

   private String t_Pymt_API;

   private String t_Pymt_Status;

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

public String getT_Pymt_ID() {
	return t_Pymt_ID;
}

public String getT_Pymt_CertificationCode() {
	return t_Pymt_CertificationCode;
}

public String getT_Pymt_Name() {
	return t_Pymt_Name;
}

public String getT_Pymt_OrgInfo() {
	return t_Pymt_OrgInfo;
}

public String getT_Pymt_API() {
	return t_Pymt_API;
}

public String getT_Pymt_Status() {
	return t_Pymt_Status;
}

public static void setSerialversionuid(long serialversionuid) {
	serialVersionUID = serialversionuid;
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

public void setT_Pymt_ID(String t_Pymt_ID) {
	this.t_Pymt_ID = t_Pymt_ID;
}

public void setT_Pymt_CertificationCode(String t_Pymt_CertificationCode) {
	this.t_Pymt_CertificationCode = t_Pymt_CertificationCode;
}

public void setT_Pymt_Name(String t_Pymt_Name) {
	this.t_Pymt_Name = t_Pymt_Name;
}

public void setT_Pymt_OrgInfo(String t_Pymt_OrgInfo) {
	this.t_Pymt_OrgInfo = t_Pymt_OrgInfo;
}

public void setT_Pymt_API(String t_Pymt_API) {
	this.t_Pymt_API = t_Pymt_API;
}

public void setT_Pymt_Status(String t_Pymt_Status) {
	this.t_Pymt_Status = t_Pymt_Status;
}
	

}