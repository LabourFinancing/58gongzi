package com.qucai.sample.vo;
//package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class CompanyTxnStatic implements Serializable {

   private static final long serialVersionUID = 1;

    private String t_CTxn_Static_CompanyName;

    private String t_CTxn_Static_CompanyNameID;
   
   private BigDecimal t_CTxn_Static_TotTxnAmtDaily;
   
   private int t_CTxn_Static_TotTxnCountDaily;
   
   private BigDecimal t_CTxn_WIP_Txn;
   
   private BigDecimal t_CTxn_Succ_Txn;
   
   private BigDecimal t_CTxn_TopupAmtDaily;
   
   private BigDecimal t_CTxn_Fail_Txn;


    public String getT_CTxn_Static_CompanyName() {
        return t_CTxn_Static_CompanyName;
    }

    public void setT_CTxn_Static_CompanyName(String t_CTxn_Static_CompanyName) {
        this.t_CTxn_Static_CompanyName = t_CTxn_Static_CompanyName;
    }

    public String getT_CTxn_Static_CompanyNameID() {
        return t_CTxn_Static_CompanyNameID;
    }

    public void setT_CTxn_Static_CompanyNameID(String t_CTxn_Static_CompanyNameID) {
        this.t_CTxn_Static_CompanyNameID = t_CTxn_Static_CompanyNameID;
    }

    public BigDecimal getT_CTxn_Static_TotTxnAmtDaily() {
        return t_CTxn_Static_TotTxnAmtDaily;
    }

    public void setT_CTxn_Static_TotTxnAmtDaily(BigDecimal t_CTxn_Static_TotTxnAmtDaily) {
        this.t_CTxn_Static_TotTxnAmtDaily = t_CTxn_Static_TotTxnAmtDaily;
    }

    public int getT_CTxn_Static_TotTxnCountDaily() {
        return t_CTxn_Static_TotTxnCountDaily;
    }

    public void setT_CTxn_Static_TotTxnCountDaily(int t_CTxn_Static_TotTxnCountDaily) {
        this.t_CTxn_Static_TotTxnCountDaily = t_CTxn_Static_TotTxnCountDaily;
    }

    public BigDecimal getT_CTxn_WIP_Txn() {
        return t_CTxn_WIP_Txn;
    }

    public void setT_CTxn_WIP_Txn(BigDecimal t_CTxn_WIP_Txn) {
        this.t_CTxn_WIP_Txn = t_CTxn_WIP_Txn;
    }

    public BigDecimal getT_CTxn_Succ_Txn() {
        return t_CTxn_Succ_Txn;
    }

    public void setT_CTxn_Succ_Txn(BigDecimal t_CTxn_Succ_Txn) {
        this.t_CTxn_Succ_Txn = t_CTxn_Succ_Txn;
    }

    public BigDecimal getT_CTxn_TopupAmtDaily() {
        return t_CTxn_TopupAmtDaily;
    }

    public void setT_CTxn_TopupAmtDaily(BigDecimal t_CTxn_TopupAmtDaily) {
        this.t_CTxn_TopupAmtDaily = t_CTxn_TopupAmtDaily;
    }

    public BigDecimal getT_CTxn_Fail_Txn() {
        return t_CTxn_Fail_Txn;
    }

    public void setT_CTxn_Fail_Txn(BigDecimal t_CTxn_Fail_Txn) {
        this.t_CTxn_Fail_Txn = t_CTxn_Fail_Txn;
    }

}