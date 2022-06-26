package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class Voucher implements Serializable {
    /**
	 * 
	 */
   private static final long serialVersionUID = -1876948347463745808L;


    /**
    * 创建人
    */


/* biz info char */
   
   private String t_Voucher_ID;

   private String t_Voucher_Name;

    private String t_Voucher_OrgInfo;

   private String t_Voucher_VendorOrgName;
   
   private String t_Voucher_ProdCat;

   private String t_Voucher_VendorName;

   private String t_Voucher_VendorClearNum;

   private String t_Voucher_3rdprty;
	
   private String t_Voucher_Pic;

   private String t_Voucher_URL;
   
   private BigDecimal t_Voucher_Interest;

   private BigDecimal t_Voucher_OverdueInt;

   private BigDecimal t_Voucher_ServiceFee;

   private BigDecimal t_Voucher_Poundage;
   
   private BigDecimal t_Voucher_ETxnAmtLimit;

   private String t_Voucher_TierPoundage;

   private String t_Voucher_Status;

   private Date t_Voucher_SysupdateDate;
	
   private String t_Voucher_category;

   private String t_Voucher_CorpPool;
	
   private String t_Voucher_PersPool;

   private String t_Voucher_bkp;
   
   private Date t_Voucher_StartDate;

   private Date t_Voucher_EndDate;

    private String platform;

    private String creator;

    private Date create_time;

    private String modifier;

    private Date modify_time;

    private String remark;



    public static long getSerialVersionUID() {
        return Voucher.serialVersionUID;
    }

    public String getT_Voucher_ID() {
        return this.t_Voucher_ID;
    }

    public void setT_Voucher_ID(final String t_Voucher_ID) {
        this.t_Voucher_ID = t_Voucher_ID;
    }

    public String getT_Voucher_Name() {
        return this.t_Voucher_Name;
    }

    public void setT_Voucher_Name(final String t_Voucher_Name) {
        this.t_Voucher_Name = t_Voucher_Name;
    }

    public String getT_Voucher_OrgInfo() {
        return this.t_Voucher_OrgInfo;
    }

    public void setT_Voucher_OrgInfo(final String t_Voucher_OrgInfo) {
        this.t_Voucher_OrgInfo = t_Voucher_OrgInfo;
    }

    public String getT_Voucher_VendorOrgName() {
        return this.t_Voucher_VendorOrgName;
    }

    public void setT_Voucher_VendorOrgName(final String t_Voucher_VendorOrgName) {
        this.t_Voucher_VendorOrgName = t_Voucher_VendorOrgName;
    }

    public String getT_Voucher_ProdCat() {
        return this.t_Voucher_ProdCat;
    }

    public void setT_Voucher_ProdCat(final String t_Voucher_ProdCat) {
        this.t_Voucher_ProdCat = t_Voucher_ProdCat;
    }

    public String getT_Voucher_VendorName() {
        return this.t_Voucher_VendorName;
    }

    public void setT_Voucher_VendorName(final String t_Voucher_VendorName) {
        this.t_Voucher_VendorName = t_Voucher_VendorName;
    }

    public String getT_Voucher_VendorClearNum() {
        return this.t_Voucher_VendorClearNum;
    }

    public void setT_Voucher_VendorClearNum(final String t_Voucher_VendorClearNum) {
        this.t_Voucher_VendorClearNum = t_Voucher_VendorClearNum;
    }

    public String getT_Voucher_3rdprty() {
        return this.t_Voucher_3rdprty;
    }

    public void setT_Voucher_3rdprty(final String t_Voucher_3rdprty) {
        this.t_Voucher_3rdprty = t_Voucher_3rdprty;
    }

    public String getT_Voucher_Pic() {
        return this.t_Voucher_Pic;
    }

    public void setT_Voucher_Pic(final String t_Voucher_Pic) {
        this.t_Voucher_Pic = t_Voucher_Pic;
    }

    public String getT_Voucher_URL() {
        return this.t_Voucher_URL;
    }

    public void setT_Voucher_URL(final String t_Voucher_URL) {
        this.t_Voucher_URL = t_Voucher_URL;
    }

    public BigDecimal getT_Voucher_Interest() {
        return this.t_Voucher_Interest;
    }

    public void setT_Voucher_Interest(final BigDecimal t_Voucher_Interest) {
        this.t_Voucher_Interest = t_Voucher_Interest;
    }

    public BigDecimal getT_Voucher_OverdueInt() {
        return this.t_Voucher_OverdueInt;
    }

    public void setT_Voucher_OverdueInt(final BigDecimal t_Voucher_OverdueInt) {
        this.t_Voucher_OverdueInt = t_Voucher_OverdueInt;
    }

    public BigDecimal getT_Voucher_ServiceFee() {
        return this.t_Voucher_ServiceFee;
    }

    public void setT_Voucher_ServiceFee(final BigDecimal t_Voucher_ServiceFee) {
        this.t_Voucher_ServiceFee = t_Voucher_ServiceFee;
    }

    public BigDecimal getT_Voucher_Poundage() {
        return this.t_Voucher_Poundage;
    }

    public void setT_Voucher_Poundage(final BigDecimal t_Voucher_Poundage) {
        this.t_Voucher_Poundage = t_Voucher_Poundage;
    }

    public BigDecimal getT_Voucher_ETxnAmtLimit() {
        return this.t_Voucher_ETxnAmtLimit;
    }

    public void setT_Voucher_ETxnAmtLimit(final BigDecimal t_Voucher_ETxnAmtLimit) {
        this.t_Voucher_ETxnAmtLimit = t_Voucher_ETxnAmtLimit;
    }

    public String getT_Voucher_TierPoundage() {
        return this.t_Voucher_TierPoundage;
    }

    public void setT_Voucher_TierPoundage(final String t_Voucher_TierPoundage) {
        this.t_Voucher_TierPoundage = t_Voucher_TierPoundage;
    }

    public String getT_Voucher_Status() {
        return this.t_Voucher_Status;
    }

    public void setT_Voucher_Status(final String t_Voucher_Status) {
        this.t_Voucher_Status = t_Voucher_Status;
    }

    public Date getT_Voucher_SysupdateDate() {
        return this.t_Voucher_SysupdateDate;
    }

    public void setT_Voucher_SysupdateDate(final Date t_Voucher_SysupdateDate) {
        this.t_Voucher_SysupdateDate = t_Voucher_SysupdateDate;
    }

    public String getT_Voucher_category() {
        return this.t_Voucher_category;
    }

    public void setT_Voucher_category(final String t_Voucher_category) {
        this.t_Voucher_category = t_Voucher_category;
    }

    public String getT_Voucher_CorpPool() {
        return this.t_Voucher_CorpPool;
    }

    public void setT_Voucher_CorpPool(final String t_Voucher_CorpPool) {
        this.t_Voucher_CorpPool = t_Voucher_CorpPool;
    }

    public String getT_Voucher_PersPool() {
        return this.t_Voucher_PersPool;
    }

    public void setT_Voucher_PersPool(final String t_Voucher_PersPool) {
        this.t_Voucher_PersPool = t_Voucher_PersPool;
    }

    public String getT_Voucher_bkp() {
        return this.t_Voucher_bkp;
    }

    public void setT_Voucher_bkp(final String t_Voucher_bkp) {
        this.t_Voucher_bkp = t_Voucher_bkp;
    }

    public Date getT_Voucher_StartDate() {
        return this.t_Voucher_StartDate;
    }

    public void setT_Voucher_StartDate(final Date t_Voucher_StartDate) {
        this.t_Voucher_StartDate = t_Voucher_StartDate;
    }

    public Date getT_Voucher_EndDate() {
        return this.t_Voucher_EndDate;
    }

    public void setT_Voucher_EndDate(final Date t_Voucher_EndDate) {
        this.t_Voucher_EndDate = t_Voucher_EndDate;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(final String platform) {
        this.platform = platform;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }
 
}