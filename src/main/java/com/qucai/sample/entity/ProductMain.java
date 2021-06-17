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
    private Date create_time;
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
    private String t_Product_Name;

    /**
     * 服务费(融资成本)
     */
    private String t_Product_EwalletProdCat;

    /**
     * 平台使用费(笔)
     */
    private String t_Product_PayrollProdCat;

    /**
     * 单笔限额
     */
    private String t_Product_SalaryAdvCat;

    /**
     * 区间平台使用费(笔)
     */
    private String t_Product_VoucherProdCat;


    /**
     * 产品状态
     */
    private String t_Product_TermIncProdCat;
    /**
     * 产品更新时间
     */
    private String t_Product_DigiProdCat;

    private String t_Product_CryptoProdCat;

    private BigDecimal t_Product_ServiceFss;

    private String t_Product_TierPoundage;

    private String t_Product_Status;

    private String t_Product_Cleardays;

    private String t_Product_EwalletProdCategory;

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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date Create_time) {
        this.create_time = create_time;
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

    public String getT_Product_Name() {
        return t_Product_Name;
    }

    public void setT_Product_Name(String t_Product_Name) {
        this.t_Product_Name = t_Product_Name;
    }

    public String gett_Product_EwalletProdCat() {
        return t_Product_EwalletProdCat;
    }

    public void sett_Product_EwalletProdCat(String t_Product_EwalletProdCat) {
        this.t_Product_EwalletProdCat = t_Product_EwalletProdCat;
    }

    public String gett_Product_PayrollProdCat() {
        return t_Product_PayrollProdCat;
    }

    public void sett_Product_PayrollProdCat(String t_Product_PayrollProdCat) {
        this.t_Product_PayrollProdCat = t_Product_PayrollProdCat;
    }

    public String gett_Product_SalaryAdvCat() {
        return t_Product_SalaryAdvCat;
    }

    public void sett_Product_SalaryAdvCat(String t_Product_SalaryAdvCat) {
        this.t_Product_SalaryAdvCat = t_Product_SalaryAdvCat;
    }

    public String gett_Product_VoucherProdCat() {
        return t_Product_VoucherProdCat;
    }

    public void sett_Product_VoucherProdCat(String t_Product_VoucherProdCat) {
        this.t_Product_VoucherProdCat = t_Product_VoucherProdCat;
    }

    public String gett_Product_TermIncProdCat() {
        return t_Product_TermIncProdCat;
    }

    public void sett_Product_TermIncProdCat(String t_Product_TermIncProdCat) {
        this.t_Product_TermIncProdCat = t_Product_TermIncProdCat;
    }

    public String gett_Product_DigiProdCat() {
        return t_Product_DigiProdCat;
    }

    public void sett_Product_DigiProdCat(String t_Product_DigiProdCat) {
        this.t_Product_DigiProdCat = t_Product_DigiProdCat;
    }

    public String gett_Product_CryptoProdCat() {
        return t_Product_CryptoProdCat;
    }

    public void sett_Product_CryptoProdCat(String t_Product_CryptoProdCat) {
        this.t_Product_CryptoProdCat = t_Product_CryptoProdCat;
    }

    public BigDecimal getT_Product_ServiceFss() {
        return t_Product_ServiceFss;
    }

    public void setT_Product_ServiceFss(BigDecimal t_Product_ServiceFss) {
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

    public String gett_Product_EwalletProdCategory() {
        return t_Product_EwalletProdCategory;
    }

    public void sett_Product_EwalletProdCategory(String t_Product_EwalletProdCategory) {
        this.t_Product_EwalletProdCategory = t_Product_EwalletProdCategory;
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