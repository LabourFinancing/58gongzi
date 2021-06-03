package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class ProductMain implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1876948347463745808L;


    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     *
     * @return
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 平台
     */
    private String platform;
    /**
     * 产品ID
     */
    private String t_Product_ID;

    /**
     * 产品名
     */
    private String t_Product_SeriesID;

    /**
     * 产品利率
     */
    private BigDecimal t_Product_PID;

    /**
     * 产品超期利率
     */
    private BigDecimal t_Product_Name;

    /**
     * 服务费(融资成本)
     */
    private BigDecimal t_Product_Cat;

    /**
     * 平台使用费(笔)
     */
    private BigDecimal t_Product_Vendor;

    /**
     * 单笔限额
     */
    private BigDecimal t_Product_Company;

    /**
     * 区间平台使用费(笔)
     */
    private String t_Product_3rdPartyService;


    /**
     * 产品状态
     */
    private String t_Product_FOrg;
    /**
     * 产品更新时间
     */
    private Date t_Product_Alias;

    private String t_Product_Site;

    private String t_Product_ServiceFss;

    private String t_Product_TierPoundage;

    private String t_Product_Status;

    private String t_Product_Cleardays;

    private String t_Product_category;

    private String t_Product_CorpPool;

    private String t_Product_PersPool;

    private String t_Product_Txt;

    private String t_Product_Txt1;

    private String t_Product_Txt2;

    private Date t_Product_SysupdateDate;


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getT_Product_ID() {
        return t_Product_ID;
    }

    public void setT_Product_ID(String t_Product_ID) {
        this.t_Product_ID = t_Product_ID;
    }

    public String getT_Product_SeriesID() {
        return t_Product_SeriesID;
    }

    public void setT_Product_SeriesID(String t_Product_SeriesID) {
        this.t_Product_SeriesID = t_Product_SeriesID;
    }

    public BigDecimal getT_Product_PID() {
        return t_Product_PID;
    }

    public void setT_Product_PID(BigDecimal t_Product_PID) {
        this.t_Product_PID = t_Product_PID;
    }

    public BigDecimal getT_Product_Name() {
        return t_Product_Name;
    }

    public void setT_Product_Name(BigDecimal t_Product_Name) {
        this.t_Product_Name = t_Product_Name;
    }

    public BigDecimal getT_Product_Cat() {
        return t_Product_Cat;
    }

    public void setT_Product_Cat(BigDecimal t_Product_Cat) {
        this.t_Product_Cat = t_Product_Cat;
    }

    public BigDecimal getT_Product_Vendor() {
        return t_Product_Vendor;
    }

    public void setT_Product_Vendor(BigDecimal t_Product_Vendor) {
        this.t_Product_Vendor = t_Product_Vendor;
    }

    public BigDecimal getT_Product_Company() {
        return t_Product_Company;
    }

    public void setT_Product_Company(BigDecimal t_Product_Company) {
        this.t_Product_Company = t_Product_Company;
    }

    public String getT_Product_3rdPartyService() {
        return t_Product_3rdPartyService;
    }

    public void setT_Product_3rdPartyService(String t_Product_3rdPartyService) {
        this.t_Product_3rdPartyService = t_Product_3rdPartyService;
    }

    public String getT_Product_FOrg() {
        return t_Product_FOrg;
    }

    public void setT_Product_FOrg(String t_Product_FOrg) {
        this.t_Product_FOrg = t_Product_FOrg;
    }

    public Date getT_Product_Alias() {
        return t_Product_Alias;
    }

    public void setT_Product_Alias(Date t_Product_Alias) {
        this.t_Product_Alias = t_Product_Alias;
    }

    public String getT_Product_Site() {
        return t_Product_Site;
    }

    public void setT_Product_Site(String t_Product_Site) {
        this.t_Product_Site = t_Product_Site;
    }

    public String getT_Product_ServiceFss() {
        return t_Product_ServiceFss;
    }

    public void setT_Product_ServiceFss(String t_Product_ServiceFss) {
        this.t_Product_ServiceFss = t_Product_ServiceFss;
    }

    public String getT_Product_TierPoundage() {
        return t_Product_TierPoundage;
    }

    public void setT_Product_TierPoundage(String t_Product_TierPoundage) {
        this.t_Product_TierPoundage = t_Product_TierPoundage;
    }

    public String getT_Product_Status() {
        return t_Product_Status;
    }

    public void setT_Product_Status(String t_Product_Status) {
        this.t_Product_Status = t_Product_Status;
    }

    public String getT_Product_Cleardays() {
        return t_Product_Cleardays;
    }

    public void setT_Product_Cleardays(String t_Product_Cleardays) {
        this.t_Product_Cleardays = t_Product_Cleardays;
    }

    public String getT_Product_category() {
        return t_Product_category;
    }

    public void setT_Product_category(String t_Product_category) {
        this.t_Product_category = t_Product_category;
    }

    public String getT_Product_CorpPool() {
        return t_Product_CorpPool;
    }

    public void setT_Product_CorpPool(String t_Product_CorpPool) {
        this.t_Product_CorpPool = t_Product_CorpPool;
    }

    public String getT_Product_PersPool() {
        return t_Product_PersPool;
    }

    public void setT_Product_PersPool(String t_Product_PersPool) {
        this.t_Product_PersPool = t_Product_PersPool;
    }

    public String getT_Product_Txt() {
        return t_Product_Txt;
    }

    public void setT_Product_Txt(String t_Product_Txt) {
        this.t_Product_Txt = t_Product_Txt;
    }

    public String getT_Product_Txt1() {
        return t_Product_Txt1;
    }

    public void setT_Product_Txt1(String t_Product_Txt1) {
        this.t_Product_Txt1 = t_Product_Txt1;
    }

    public String getT_Product_Txt2() {
        return t_Product_Txt2;
    }

    public void setT_Product_Txt2(String t_Product_Txt2) {
        this.t_Product_Txt2 = t_Product_Txt2;
    }

    public Date getT_Product_SysupdateDate() {
        return t_Product_SysupdateDate;
    }

    public void setT_Product_SysupdateDate(Date t_Product_SysupdateDate) {
        this.t_Product_SysupdateDate = t_Product_SysupdateDate;
    }

//----------------------------------------------


}