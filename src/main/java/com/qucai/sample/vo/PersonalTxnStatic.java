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
public class PersonalTxnStatic implements Serializable {

   private static final long serialVersionUID = 1;
   
   private String t_Txn_ClearDue_ID;
   
   private String t_Txn_Static_PerName;
   
   private String t_Txn_Static_PerID;
   
   private String t_Txn_Static_PerCreditCard;
   
   private BigDecimal t_Txn_Static_TotTxnAmt;
   
   private BigDecimal t_Txn_Static_TotTxnActAmt;
   
   private BigDecimal t_Txn_Static_TotInterest;
   
   private BigDecimal t_Txn_Static_TotServiceFee;
   
   private BigDecimal t_Txn_Static_TotPoundageFee;
   
   private BigDecimal t_Txn_Static_TotTierFee;
   
   private BigDecimal t_Txn_Static_TotChargeFee;
   
   private Integer t_Txn_Static_TotTxnCount;

    private BigDecimal t_Txn_Static_CurrentCredit;

   private BigDecimal t_Txn_Static_CurrentBalance;
   
   private String t_Txn_Static_Company;
   
   private String t_Txn_Static_VendorCompany;
   
   private Date t_Txn_Static_BeginDate;
   
   private Date t_Txn_Static_EndDate;

public String getT_Txn_ClearDue_ID() {
	return t_Txn_ClearDue_ID;
}

public void setT_Txn_ClearDue_ID(String t_Txn_ClearDue_ID) {
	this.t_Txn_ClearDue_ID = t_Txn_ClearDue_ID;
}

public String getT_Txn_Static_PerName() {
	return t_Txn_Static_PerName;
}

public void setT_Txn_Static_PerName(String t_Txn_Static_PerName) {
	this.t_Txn_Static_PerName = t_Txn_Static_PerName;
}

public String getT_Txn_Static_PerID() {
	return t_Txn_Static_PerID;
}

public void setT_Txn_Static_PerID(String t_Txn_Static_PerID) {
	this.t_Txn_Static_PerID = t_Txn_Static_PerID;
}

public String getT_Txn_Static_PerCreditCard() {
	return t_Txn_Static_PerCreditCard;
}

public void setT_Txn_Static_PerCreditCard(String t_Txn_Static_PerCreditCard) {
	this.t_Txn_Static_PerCreditCard = t_Txn_Static_PerCreditCard;
}

public BigDecimal getT_Txn_Static_TotTxnAmt() {
	return t_Txn_Static_TotTxnAmt;
}

public void setT_Txn_Static_TotTxnAmt(BigDecimal t_Txn_Static_TotTxnAmt) {
	this.t_Txn_Static_TotTxnAmt = t_Txn_Static_TotTxnAmt;
}

public BigDecimal getT_Txn_Static_TotTxnActAmt() {
	return t_Txn_Static_TotTxnActAmt;
}

public void setT_Txn_Static_TotTxnActAmt(BigDecimal t_Txn_Static_TotTxnActAmt) {
	this.t_Txn_Static_TotTxnActAmt = t_Txn_Static_TotTxnActAmt;
}

public BigDecimal getT_Txn_Static_TotInterest() {
	return t_Txn_Static_TotInterest;
}

public void setT_Txn_Static_TotInterest(BigDecimal t_Txn_Static_TotInterest) {
	this.t_Txn_Static_TotInterest = t_Txn_Static_TotInterest;
}

public BigDecimal getT_Txn_Static_TotServiceFee() {
	return t_Txn_Static_TotServiceFee;
}

public void setT_Txn_Static_TotServiceFee(BigDecimal t_Txn_Static_TotServiceFee) {
	this.t_Txn_Static_TotServiceFee = t_Txn_Static_TotServiceFee;
}

public BigDecimal getT_Txn_Static_TotPoundageFee() {
	return t_Txn_Static_TotPoundageFee;
}

public void setT_Txn_Static_TotPoundageFee(
		BigDecimal t_Txn_Static_TotPoundageFee) {
	this.t_Txn_Static_TotPoundageFee = t_Txn_Static_TotPoundageFee;
}

public BigDecimal getT_Txn_Static_TotTierFee() {
	return t_Txn_Static_TotTierFee;
}

public void setT_Txn_Static_TotTierFee(BigDecimal t_Txn_Static_TotTierFee) {
	this.t_Txn_Static_TotTierFee = t_Txn_Static_TotTierFee;
}

public BigDecimal getT_Txn_Static_TotChargeFee() {
	return t_Txn_Static_TotChargeFee;
}

public void setT_Txn_Static_TotChargeFee(BigDecimal t_Txn_Static_TotChargeFee) {
	this.t_Txn_Static_TotChargeFee = t_Txn_Static_TotChargeFee;
}

public Integer getT_Txn_Static_TotTxnCount() {
	return t_Txn_Static_TotTxnCount;
}

public void setT_Txn_Static_TotTxnCount(Integer t_Txn_Static_TotTxnCount) {
	this.t_Txn_Static_TotTxnCount = t_Txn_Static_TotTxnCount;
}

public String getT_Txn_Static_Company() {
	return t_Txn_Static_Company;
}

public void setT_Txn_Static_Company(String t_Txn_Static_Company) {
	this.t_Txn_Static_Company = t_Txn_Static_Company;
}

public String getT_Txn_Static_VendorCompany() {
	return t_Txn_Static_VendorCompany;
}

public void setT_Txn_Static_VendorCompany(String t_Txn_Static_VendorCompany) {
	this.t_Txn_Static_VendorCompany = t_Txn_Static_VendorCompany;
}

public Date getT_Txn_Static_BeginDate() {
	return t_Txn_Static_BeginDate;
}

public void setT_Txn_Static_BeginDate(Date t_Txn_Static_BeginDate) {
	this.t_Txn_Static_BeginDate = t_Txn_Static_BeginDate;
}

public Date getT_Txn_Static_EndDate() {
	return t_Txn_Static_EndDate;
}

public void setT_Txn_Static_EndDate(Date t_Txn_Static_EndDate) {
	this.t_Txn_Static_EndDate = t_Txn_Static_EndDate;
}


    public BigDecimal getT_Txn_Static_CurrentCredit() {
        return t_Txn_Static_CurrentCredit;
    }

    public void setT_Txn_Static_CurrentCredit(BigDecimal t_Txn_Static_CurrentCredit) {
        this.t_Txn_Static_CurrentCredit = t_Txn_Static_CurrentCredit;
    }

    public BigDecimal getT_Txn_Static_CurrentBalance() {
        return t_Txn_Static_CurrentBalance;
    }

    public void setT_Txn_Static_CurrentBalance(BigDecimal t_Txn_Static_CurrentBalance) {
        this.t_Txn_Static_CurrentBalance = t_Txn_Static_CurrentBalance;
    }

}