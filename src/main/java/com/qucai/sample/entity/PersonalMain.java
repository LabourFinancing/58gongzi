package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class PersonalMain implements Serializable {
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
   
   private Integer platform;

   private String remark;

/* biz info char */
   
   private String t_personal_main_id;

   private String t_personal_main_name;
   
   private String t_personal_main_pid;

   private String t_personal_main_mobile;

    private String t_personal_main_mobile1;

   private String t_personal_main_contacts;

   private String t_personal_main_realname;
	
   private String t_personal_main_fingerprint;

   private String t_personal_main_facialret;
   
   private String t_personal_main_securityret;

   private String t_personal_main_passport;

    private String t_personal_main_passport1;

   private String t_personal_main_visa;

   private String t_personal_main_visa1;
   
   private String t_personal_main_onlinepaymentcat;

   private String t_personal_main_onlinepayment;

   private String t_personal_main_paymentmethod;

   private String t_personal_main_paymentmethod1;
	
   private String t_personal_main_CNbankcard;

   private String t_personal_main_GLbankcard;
	
   private String t_personal_main_bankacc;

   private String t_personal_main_crypto;
   
   private String t_personal_main_assetcat;

   private String t_personal_main_voucher;

    private Integer t_personal_main_creditscore;

   private String t_personal_main_ewalletcat;
   
   private String t_personal_main_digiasset;

    private String t_personal_main_companylist;

    private String t_personal_main_productCat;

    private String t_personal_main_prodlist;

    private String status;

    private String email;


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getT_personal_main_id() {
        return t_personal_main_id;
    }

    public void setT_personal_main_id(String t_personal_main_id) {
        this.t_personal_main_id = t_personal_main_id;
    }

    public String getT_personal_main_name() {
        return t_personal_main_name;
    }

    public void setT_personal_main_name(String t_personal_main_name) {
        this.t_personal_main_name = t_personal_main_name;
    }

    public String getT_personal_main_pid() {
        return t_personal_main_pid;
    }

    public void setT_personal_main_pid(String t_personal_main_pid) {
        this.t_personal_main_pid = t_personal_main_pid;
    }

    public String getT_personal_main_mobile() {
        return t_personal_main_mobile;
    }

    public void setT_personal_main_mobile(String t_personal_main_mobile) {
        this.t_personal_main_mobile = t_personal_main_mobile;
    }

    public String getT_personal_main_mobile1() {
        return t_personal_main_mobile1;
    }

    public void setT_personal_main_mobile1(String t_personal_main_mobile1) {
        this.t_personal_main_mobile1 = t_personal_main_mobile1;
    }
    public String getT_personal_main_contacts() {
        return t_personal_main_contacts;
    }

    public void setT_personal_main_contacts(String t_personal_main_contacts) {
        this.t_personal_main_contacts = t_personal_main_contacts;
    }

    public String getT_personal_main_realname() {
        return t_personal_main_realname;
    }

    public void setT_personal_main_realname(String t_personal_main_realname) {
        this.t_personal_main_realname = t_personal_main_realname;
    }

    public String getT_personal_main_fingerprint() {
        return t_personal_main_fingerprint;
    }

    public void setT_personal_main_fingerprint(String t_personal_main_fingerprint) {
        this.t_personal_main_fingerprint = t_personal_main_fingerprint;
    }

    public String getT_personal_main_facialret() {
        return t_personal_main_facialret;
    }

    public void setT_personal_main_facialret(String t_personal_main_facialret) {
        this.t_personal_main_facialret = t_personal_main_facialret;
    }

    public String getT_personal_main_securityret() {
        return t_personal_main_securityret;
    }

    public void setT_personal_main_securityret(String t_personal_main_securityret) {
        this.t_personal_main_securityret = t_personal_main_securityret;
    }

    public String getT_personal_main_passport() {
        return t_personal_main_passport;
    }

    public void setT_personal_main_passport(String t_personal_main_passport) {
        this.t_personal_main_passport = t_personal_main_passport;
    }

    public String getT_personal_main_visa() {
        return t_personal_main_visa;
    }

    public void setT_personal_main_visa(String t_personal_main_visa) {
        this.t_personal_main_visa = t_personal_main_visa;
    }

    public String getT_personal_main_visa1() {
        return t_personal_main_visa1;
    }

    public void setT_personal_main_visa1(String t_personal_main_visa1) {
        this.t_personal_main_visa1 = t_personal_main_visa1;
    }

    public String getT_personal_main_onlinepaymentcat() {
        return t_personal_main_onlinepaymentcat;
    }

    public void setT_personal_main_onlinepaymentcat(String t_personal_main_onlinepaymentcat) {
        this.t_personal_main_onlinepaymentcat = t_personal_main_onlinepaymentcat;
    }

    public String getT_personal_main_onlinepayment() {
        return t_personal_main_onlinepayment;
    }

    public void setT_personal_main_onlinepayment(String t_personal_main_onlinepayment) {
        this.t_personal_main_onlinepayment = t_personal_main_onlinepayment;
    }

    public String getT_personal_main_paymentmethod() {
        return t_personal_main_paymentmethod;
    }

    public void setT_personal_main_paymentmethod(String t_personal_main_paymentmethod) {
        this.t_personal_main_paymentmethod = t_personal_main_paymentmethod;
    }

    public String getT_personal_main_paymentmethod1() {
        return t_personal_main_paymentmethod1;
    }

    public void setT_personal_main_paymentmethod1(String t_personal_main_paymentmethod1) {
        this.t_personal_main_paymentmethod1 = t_personal_main_paymentmethod1;
    }

    public String getT_personal_main_CNbankcard() {
        return t_personal_main_CNbankcard;
    }

    public void setT_personal_main_CNbankcard(String t_personal_main_CNbankcard) {
        this.t_personal_main_CNbankcard = t_personal_main_CNbankcard;
    }

    public String getT_personal_main_GLbankcard() {
        return t_personal_main_GLbankcard;
    }

    public void setT_personal_main_GLbankcard(String t_personal_main_GLbankcard) {
        this.t_personal_main_GLbankcard = t_personal_main_GLbankcard;
    }

    public String getT_personal_main_bankacc() {
        return t_personal_main_bankacc;
    }

    public void setT_personal_main_bankacc(String t_personal_main_bankacc) {
        this.t_personal_main_bankacc = t_personal_main_bankacc;
    }

    public String getT_personal_main_crypto() {
        return t_personal_main_crypto;
    }

    public void setT_personal_main_crypto(String t_personal_main_crypto) {
        this.t_personal_main_crypto = t_personal_main_crypto;
    }

    public String getT_personal_main_assetcat() {
        return t_personal_main_assetcat;
    }

    public void setT_personal_main_assetcat(String t_personal_main_assetcat) {
        this.t_personal_main_assetcat = t_personal_main_assetcat;
    }

    public String getT_personal_main_voucher() {
        return t_personal_main_voucher;
    }

    public void setT_personal_main_voucher(String t_personal_main_voucher) {
        this.t_personal_main_voucher = t_personal_main_voucher;
    }

    public Integer getT_personal_main_creditscore() {
        return t_personal_main_creditscore;
    }

    public void setT_personal_main_creditscore(Integer t_personal_main_creditscore) {
        this.t_personal_main_creditscore = t_personal_main_creditscore;
    }

    public String gett_personal_main_ewalletcat() {
        return t_personal_main_ewalletcat;
    }

    public void sett_personal_main_ewalletcat(String t_personal_main_ewalletcat) {
        this.t_personal_main_ewalletcat = t_personal_main_ewalletcat;
    }

    public String getT_personal_main_digiasset() {
        return t_personal_main_digiasset;
    }

    public void setT_personal_main_digiasset(String t_personal_main_digiasset) {
        this.t_personal_main_digiasset = t_personal_main_digiasset;
    }

    public String gett_personal_main_companylist() {
        return t_personal_main_companylist;
    }

    public void sett_personal_main_companylist(String t_personal_main_companylist) {
        this.t_personal_main_companylist = t_personal_main_companylist;
    }

    public String gett_personal_main_productCat() {
        return t_personal_main_productCat;
    }

    public void sett_personal_main_productCat(String t_personal_main_productCat) {
        this.t_personal_main_productCat = t_personal_main_productCat;
    }

    public String gett_personal_main_prodlist() {
        return t_personal_main_prodlist;
    }

    public void sett_personal_main_prodlist(String t_personal_main_prodlist) {
        this.t_personal_main_prodlist = t_personal_main_prodlist;
    }


    public String getT_personal_main_passport1() {
        return t_personal_main_passport1;
    }

    public void setT_personal_main_passport1(String t_personal_main_passport1) {
        this.t_personal_main_passport1 = t_personal_main_passport1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}