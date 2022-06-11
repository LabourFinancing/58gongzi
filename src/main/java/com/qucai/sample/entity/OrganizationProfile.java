package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class OrganizationProfile implements Serializable {
    /**
	 */
   private static final long serialVersionUID = -1876948347463745808L;
   
    /**
    */
   private String t_Profile_ID;
    /**
    */
   private String t_Profile_CertificationCode;
   /**
    */
   private String t_Profile_OrgName;
   
   /*
    */
   private String t_Profile_Qualification;
	
   /**
    */
   private String t_Profile_QualificationStatus;

   /**
    */
   private String t_Profile_Mobile;
   /**
    */
   private String t_Profile_Contact;

   /**
    */
   private String t_Profile_Email;
   /**
    */
   private String t_Profile_Address;
   /**
    */
   private String t_Profile_PostRet;

   /**
    */
   private String t_Profile_OrgType;
   /**
    */
   private String t_Profile_StatusRptRetAddress;

   /**
    */
   private String t_Profile_CurrentAddress;
    /**
     */
    private String t_Profile_IPaddr;
    /**
     */
    private String t_Profile_APIAcc;
    /**
     */
    private String t_Profile_APIPWD;
    /**
     */
    private String t_Profile_APIret;
    /**
    */
   private String t_Profile_AgentCmpyName;
    /**
     */
    private String t_Profile_AppStatus;
    /**
     */
    private String t_Profile_RetHTTPget;
    /**
     */
    private String t_Profile_RegulatorReq;
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

    public static long getSerialVersionUID() {
        return OrganizationProfile.serialVersionUID;
    }

    public String getT_Profile_ID() {
        return this.t_Profile_ID;
    }

    public void setT_Profile_ID(final String t_Profile_ID) {
        this.t_Profile_ID = t_Profile_ID;
    }

    public String getT_Profile_CertificationCode() {
        return this.t_Profile_CertificationCode;
    }

    public void setT_Profile_CertificationCode(final String t_Profile_CertificationCode) {
        this.t_Profile_CertificationCode = t_Profile_CertificationCode;
    }

    public String getT_Profile_OrgName() {
        return this.t_Profile_OrgName;
    }

    public void setT_Profile_OrgName(final String t_Profile_OrgName) {
        this.t_Profile_OrgName = t_Profile_OrgName;
    }

    public String getT_Profile_Qualification() {
        return this.t_Profile_Qualification;
    }

    public void setT_Profile_Qualification(final String t_Profile_Qualification) {
        this.t_Profile_Qualification = t_Profile_Qualification;
    }

    public String getT_Profile_QualificationStatus() {
        return this.t_Profile_QualificationStatus;
    }

    public void setT_Profile_QualificationStatus(final String t_Profile_QualificationStatus) {
        this.t_Profile_QualificationStatus = t_Profile_QualificationStatus;
    }

    public String getT_Profile_Mobile() {
        return this.t_Profile_Mobile;
    }

    public void setT_Profile_Mobile(final String t_Profile_Mobile) {
        this.t_Profile_Mobile = t_Profile_Mobile;
    }

    public String getT_Profile_Contact() {
        return this.t_Profile_Contact;
    }

    public void setT_Profile_Contact(final String t_Profile_Contact) {
        this.t_Profile_Contact = t_Profile_Contact;
    }

    public String getT_Profile_Email() {
        return this.t_Profile_Email;
    }

    public void setT_Profile_Email(final String t_Profile_Email) {
        this.t_Profile_Email = t_Profile_Email;
    }

    public String getT_Profile_Address() {
        return this.t_Profile_Address;
    }

    public void setT_Profile_Address(final String t_Profile_Address) {
        this.t_Profile_Address = t_Profile_Address;
    }

    public String getT_Profile_PostRet() {
        return this.t_Profile_PostRet;
    }

    public void setT_Profile_PostRet(final String t_Profile_PostRet) {
        this.t_Profile_PostRet = t_Profile_PostRet;
    }

    public String getT_Profile_OrgType() {
        return this.t_Profile_OrgType;
    }

    public void setT_Profile_OrgType(final String t_Profile_OrgType) {
        this.t_Profile_OrgType = t_Profile_OrgType;
    }

    public String getT_Profile_StatusRptRetAddress() {
        return this.t_Profile_StatusRptRetAddress;
    }

    public void setT_Profile_StatusRptRetAddress(final String t_Profile_StatusRptRetAddress) {
        this.t_Profile_StatusRptRetAddress = t_Profile_StatusRptRetAddress;
    }

    public String getT_Profile_CurrentAddress() {
        return this.t_Profile_CurrentAddress;
    }

    public void setT_Profile_CurrentAddress(final String t_Profile_CurrentAddress) {
        this.t_Profile_CurrentAddress = t_Profile_CurrentAddress;
    }

    public String getT_Profile_IPaddr() {
        return this.t_Profile_IPaddr;
    }

    public void setT_Profile_IPaddr(final String t_Profile_IPaddr) {
        this.t_Profile_IPaddr = t_Profile_IPaddr;
    }

    public String getT_Profile_APIAcc() {
        return this.t_Profile_APIAcc;
    }

    public void setT_Profile_APIAcc(final String t_Profile_APIAcc) {
        this.t_Profile_APIAcc = t_Profile_APIAcc;
    }

    public String getT_Profile_APIPWD() {
        return this.t_Profile_APIPWD;
    }

    public void setT_Profile_APIPWD(final String t_Profile_APIPWD) {
        this.t_Profile_APIPWD = t_Profile_APIPWD;
    }

    public String getT_Profile_APIret() {
        return this.t_Profile_APIret;
    }

    public void setT_Profile_APIret(final String t_Profile_APIret) {
        this.t_Profile_APIret = t_Profile_APIret;
    }

    public String getT_Profile_AgentCmpyName() {
        return this.t_Profile_AgentCmpyName;
    }

    public void setT_Profile_AgentCmpyName(final String t_Profile_AgentCmpyName) {
        this.t_Profile_AgentCmpyName = t_Profile_AgentCmpyName;
    }

    public String getT_Profile_AppStatus() {
        return this.t_Profile_AppStatus;
    }

    public void setT_Profile_AppStatus(final String t_Profile_AppStatus) {
        this.t_Profile_AppStatus = t_Profile_AppStatus;
    }

    public String getT_Profile_RetHTTPget() {
        return this.t_Profile_RetHTTPget;
    }

    public void setT_Profile_RetHTTPget(final String t_Profile_RetHTTPget) {
        this.t_Profile_RetHTTPget = t_Profile_RetHTTPget;
    }

    public String getT_Profile_RegulatorReq() {
        return this.t_Profile_RegulatorReq;
    }

    public void setT_Profile_RegulatorReq(final String t_Profile_RegulatorReq) {
        this.t_Profile_RegulatorReq = t_Profile_RegulatorReq;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(final String platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(final String creator) {
        this.creator = creator;
    }

    public Date getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(final Date create_time) {
        this.create_time = create_time;
    }

    public String getModifier() {
        return this.modifier;
    }

    public void setModifier(final String modifier) {
        this.modifier = modifier;
    }

    public Date getModify_time() {
        return this.modify_time;
    }

    public void setModify_time(final Date modify_time) {
        this.modify_time = modify_time;
    }

    /**
    */
   private Date modify_time;

}