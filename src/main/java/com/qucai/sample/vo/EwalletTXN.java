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
public class EwalletTXN implements Serializable {

   //    private static final long serialVersionUID = 1;
   
   private String t_Txn_ClearDue_ID;
   private static final long serialVersionUID = -1876948347463745808L;
   
   private String t_Txn_Static_Clear_his;
   
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

}