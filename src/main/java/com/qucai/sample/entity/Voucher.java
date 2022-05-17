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
   private String creator;

   private Date create_time;

   private String modifier;

   private Date modify_time;
   
   private String platform;

   private String remark;

/* biz info char */
   
   private String t_Voucher_ID;

   private String t_Voucher_Name;
   
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
   
   private String t_Voucher_bkp1;

   private String t_Voucher_bkp2;


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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getT_Voucher_ID() {
        return t_Voucher_ID;
    }

    public void setT_Voucher_ID(String t_Voucher_ID) {
        this.t_Voucher_ID = t_Voucher_ID;
    }

    public String getT_Voucher_Name() {
        return t_Voucher_Name;
    }

    public void setT_Voucher_Name(String t_Voucher_Name) {
        this.t_Voucher_Name = t_Voucher_Name;
    }

    public String getT_Voucher_ProdID() {
        return t_Voucher_ProdCat;
    }

    public void setT_Voucher_ProdID(String t_Voucher_ProdCat) {
        this.t_Voucher_ProdCat = t_Voucher_ProdCat;
    }

    public String getT_Voucher_VendorName() {
        return t_Voucher_VendorName;
    }

    public void setT_Voucher_VendorName(String t_Voucher_VendorName) {
        this.t_Voucher_VendorName = t_Voucher_VendorName;
    }

    public String getT_Voucher_VendorClearNum() {
        return t_Voucher_VendorClearNum;
    }

    public void setT_Voucher_VendorClearNum(String t_Voucher_VendorClearNum) {
        this.t_Voucher_VendorClearNum = t_Voucher_VendorClearNum;
    }

    public String getT_Voucher_3rdprty() {
        return t_Voucher_3rdprty;
    }

    public void setT_Voucher_3rdprty(String t_Voucher_3rdprty) {
        this.t_Voucher_3rdprty = t_Voucher_3rdprty;
    }

    public String getT_Voucher_Pic() {
        return t_Voucher_Pic;
    }

    public void setT_Voucher_Pic(String t_Voucher_Pic) {
        this.t_Voucher_Pic = t_Voucher_Pic;
    }

    public String getT_Voucher_URL() {
        return t_Voucher_URL;
    }

    public void setT_Voucher_URL(String t_Voucher_URL) {
        this.t_Voucher_URL = t_Voucher_URL;
    }

    public BigDecimal getT_Voucher_Interest() {
        return t_Voucher_Interest;
    }

    public void setT_Voucher_Interest(BigDecimal t_Voucher_Interest) {
        this.t_Voucher_Interest = t_Voucher_Interest;
    }

    public BigDecimal getT_Voucher_OverdueInt() {
        return t_Voucher_OverdueInt;
    }

    public void setT_Voucher_OverdueInt(BigDecimal t_Voucher_OverdueInt) {
        this.t_Voucher_OverdueInt = t_Voucher_OverdueInt;
    }

    public BigDecimal getT_Voucher_ServiceFee() {
        return t_Voucher_ServiceFee;
    }

    public void setT_Voucher_ServiceFee(BigDecimal t_Voucher_ServiceFee) {
        this.t_Voucher_ServiceFee = t_Voucher_ServiceFee;
    }

    public BigDecimal getT_Voucher_Poundage() {
        return t_Voucher_Poundage;
    }

    public void setT_Voucher_Poundage(BigDecimal t_Voucher_Poundage) {
        this.t_Voucher_Poundage = t_Voucher_Poundage;
    }

    public BigDecimal getT_Voucher_ETxnAmtLimit() {
        return t_Voucher_ETxnAmtLimit;
    }

    public void setT_Voucher_ETxnAmtLimit(BigDecimal t_Voucher_ETxnAmtLimit) {
        this.t_Voucher_ETxnAmtLimit = t_Voucher_ETxnAmtLimit;
    }

    public String getT_Voucher_TierPoundage() {
        return t_Voucher_TierPoundage;
    }

    public void setT_Voucher_TierPoundage(String t_Voucher_TierPoundage) {
        this.t_Voucher_TierPoundage = t_Voucher_TierPoundage;
    }

    public String getT_Voucher_Status() {
        return t_Voucher_Status;
    }

    public void setT_Voucher_Status(String t_Voucher_Status) {
        this.t_Voucher_Status = t_Voucher_Status;
    }

    public Date getT_Voucher_SysupdateDate() {
        return t_Voucher_SysupdateDate;
    }

    public void setT_Voucher_SysupdateDate(Date t_Voucher_SysupdateDate) {
        this.t_Voucher_SysupdateDate = t_Voucher_SysupdateDate;
    }

    public String getT_Voucher_category() {
        return t_Voucher_category;
    }

    public void setT_Voucher_category(String t_Voucher_category) {
        this.t_Voucher_category = t_Voucher_category;
    }

    public String getT_Voucher_CorpPool() {
        return t_Voucher_CorpPool;
    }

    public void setT_Voucher_CorpPool(String t_Voucher_CorpPool) {
        this.t_Voucher_CorpPool = t_Voucher_CorpPool;
    }

    public String getT_Voucher_PersPool() {
        return t_Voucher_PersPool;
    }

    public void setT_Voucher_PersPool(String t_Voucher_PersPool) {
        this.t_Voucher_PersPool = t_Voucher_PersPool;
    }

    public String getT_Voucher_bkp() {
        return t_Voucher_bkp;
    }

    public void setT_Voucher_bkp(String t_Voucher_bkp) {
        this.t_Voucher_bkp = t_Voucher_bkp;
    }

    public String getT_Voucher_bkp1() {
        return t_Voucher_bkp1;
    }

    public void setT_Voucher_bkp1(String t_Voucher_bkp1) {
        this.t_Voucher_bkp1 = t_Voucher_bkp1;
    }

    public String getT_Voucher_bkp2() {
        return t_Voucher_bkp2;
    }

    public void setT_Voucher_bkp2(String t_Voucher_bkp2) {
        this.t_Voucher_bkp2 = t_Voucher_bkp2;
    }

//----------------------------------------------
   
 
}