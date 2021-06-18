package com.qucai.sample.vo;
//package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @version 1.0 2021-06-14
 */
public class MobileEwalletTXN implements Serializable {

    /* Txn info char */

    private String t_mobileWalletTxn_ID;

    private String t_mobileWalletTxn_QRcode;

    private String t_mobileWalletTxn_Num;

    private String t_mobileWalletTxn_Vendor;

    private String t_mobileWalletTxn_ClearNum;

    private String t_mobileWalletTxn_ClearOrg;

    private String t_mobileWalletTxn_PayerName;

    private String t_mobileWalletTxn_PayerID;

    private String t_mobileWalletTxn_PayerPID;

    private String t_mobileWalletTxn_ReceiverName;

    private String t_mobileWalletTxn_ReceiverID;

    private String t_mobileWalletTxn_ReceiverPID;

    private String t_mobileWalletTxn_Mobile;

    private String t_mobileWalletTxn_TxnCat;

    private String t_mobileWalletTxn_TxnCurrencyType;

    private java.util.Date t_mobileWalletTxn_TxnDate;

    private String t_mobileWalletTxn_ProdName;

    private Integer t_mobileWalletTxn_PayDays;

    private BigDecimal t_mobileWalletTxn_CreditPayCurrentNum;

    private BigDecimal t_mobileWalletTxn_TotTxnAmount;

    private BigDecimal t_mobileWalletTxn_TopupAmt;

    private BigDecimal t_mobileWalletTxn_CryptoTxnAmt;

    private BigDecimal t_mobileWalletTxn_DebitTxnAmt;

    private BigDecimal t_mobileWalletTxn_CreditTxnAmt;

    private BigDecimal t_mobileWalletTxn_CreditBalanceNum;

    private BigDecimal t_mobileWalletTxn_CreditTxnAmtInit;

    private BigDecimal t_mobileWalletTxn_TotalPayAmt;

    private BigDecimal t_mobileWalletTxn_TotallvorchourAmt;

    private Integer t_mobileWalletTxn_TotalInterestDays;

    private Integer t_mobileWalletTxn_TxnCounts;

    private BigDecimal t_mobileWalletTxn_Interest;

    private String t_mobileWalletTxn_ProductName;

    private String t_mobileWalletTxn_ProductID;

    private BigDecimal t_mobileWalletTxn_TotBalance;

    private BigDecimal t_mobileWalletTxn_BalancePrepayNum;

    private Date t_mobileWalletTxn_OverdueRepaymentDate;

    private String t_mobileWalletTxn_PayClear;

    private String t_mobileWalletTxn_Overdue;

    private Integer t_mobileWalletTxn_OverdueDays;

    private BigDecimal t_mobileWalletTxn_RefundAmt;

    private BigDecimal t_mobileWalletTxn_FinancialInterest;

    private BigDecimal t_mobileWalletTxn_ServiceFee;

    private BigDecimal t_mobileWalletTxn_Poundage;

    private BigDecimal t_mobileWalletTxn_TierPoundage;

    private BigDecimal t_mobileWalletTxn_discountamt;

    private BigDecimal t_mobileWalletTxn_ClearanceAmt;

    private BigDecimal t_mobileWalletTxn_InterestMargin;

    private String t_mobileWalletTxn_PayerBankAcc;

    private String t_mobileWalletTxn_ReceiverBankAcc;

    private Date t_mobileWalletTxn_SysUpdateDate;

    private String t_mobileWalletTxn_Paystatus;

    private String t_mobileWalletTxn_SMS;

    private String t_mobileWalletTxn_SMSRec;

    private String t_mobileWalletTxn_type;

    private String t_mobileWalletTxn_Voucher;

    private String t_mobileWalletTxn_Txt2;

    private String t_mobileWalletTxn_Txt3;

    private String t_mobileWalletTxn_Txt4;

    private String t_mobileWalletTxn_Txt5;

    private Date txn_current_time; //

    private Boolean txn_sendpayment_succ;

    private Boolean txn_ret_data;

}