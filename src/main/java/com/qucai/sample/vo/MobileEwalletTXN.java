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

    private String t_mobileWalletTxn_productType; //payment product type

    private String t_mobileWalletTxn_Txt3;

    private String t_mobileWalletTxn_Txt4;

    private String t_mobileWalletTxn_Txt5;
    
    private Date txn_current_time; //

    private Boolean txn_sendpayment_succ;

    private Boolean txn_ret_data;

    public String getT_mobileWalletTxn_ID() {
        return t_mobileWalletTxn_ID;
    }

    public void setT_mobileWalletTxn_ID(String t_mobileWalletTxn_ID) {
        this.t_mobileWalletTxn_ID = t_mobileWalletTxn_ID;
    }

    public String getT_mobileWalletTxn_QRcode() {
        return t_mobileWalletTxn_QRcode;
    }

    public void setT_mobileWalletTxn_QRcode(String t_mobileWalletTxn_QRcode) {
        this.t_mobileWalletTxn_QRcode = t_mobileWalletTxn_QRcode;
    }

    public String getT_mobileWalletTxn_Num() {
        return t_mobileWalletTxn_Num;
    }

    public void setT_mobileWalletTxn_Num(String t_mobileWalletTxn_Num) {
        this.t_mobileWalletTxn_Num = t_mobileWalletTxn_Num;
    }

    public String getT_mobileWalletTxn_Vendor() {
        return t_mobileWalletTxn_Vendor;
    }

    public void setT_mobileWalletTxn_Vendor(String t_mobileWalletTxn_Vendor) {
        this.t_mobileWalletTxn_Vendor = t_mobileWalletTxn_Vendor;
    }

    public String getT_mobileWalletTxn_ClearNum() {
        return t_mobileWalletTxn_ClearNum;
    }

    public void setT_mobileWalletTxn_ClearNum(String t_mobileWalletTxn_ClearNum) {
        this.t_mobileWalletTxn_ClearNum = t_mobileWalletTxn_ClearNum;
    }

    public String getT_mobileWalletTxn_ClearOrg() {
        return t_mobileWalletTxn_ClearOrg;
    }

    public void setT_mobileWalletTxn_ClearOrg(String t_mobileWalletTxn_ClearOrg) {
        this.t_mobileWalletTxn_ClearOrg = t_mobileWalletTxn_ClearOrg;
    }

    public String getT_mobileWalletTxn_PayerName() {
        return t_mobileWalletTxn_PayerName;
    }

    public void setT_mobileWalletTxn_PayerName(String t_mobileWalletTxn_PayerName) {
        this.t_mobileWalletTxn_PayerName = t_mobileWalletTxn_PayerName;
    }

    public String getT_mobileWalletTxn_PayerID() {
        return t_mobileWalletTxn_PayerID;
    }

    public void setT_mobileWalletTxn_PayerID(String t_mobileWalletTxn_PayerID) {
        this.t_mobileWalletTxn_PayerID = t_mobileWalletTxn_PayerID;
    }

    public String getT_mobileWalletTxn_PayerPID() {
        return t_mobileWalletTxn_PayerPID;
    }

    public void setT_mobileWalletTxn_PayerPID(String t_mobileWalletTxn_PayerPID) {
        this.t_mobileWalletTxn_PayerPID = t_mobileWalletTxn_PayerPID;
    }

    public String getT_mobileWalletTxn_ReceiverName() {
        return t_mobileWalletTxn_ReceiverName;
    }

    public void setT_mobileWalletTxn_ReceiverName(String t_mobileWalletTxn_ReceiverName) {
        this.t_mobileWalletTxn_ReceiverName = t_mobileWalletTxn_ReceiverName;
    }

    public String getT_mobileWalletTxn_ReceiverID() {
        return t_mobileWalletTxn_ReceiverID;
    }

    public void setT_mobileWalletTxn_ReceiverID(String t_mobileWalletTxn_ReceiverID) {
        this.t_mobileWalletTxn_ReceiverID = t_mobileWalletTxn_ReceiverID;
    }

    public String getT_mobileWalletTxn_ReceiverPID() {
        return t_mobileWalletTxn_ReceiverPID;
    }

    public void setT_mobileWalletTxn_ReceiverPID(String t_mobileWalletTxn_ReceiverPID) {
        this.t_mobileWalletTxn_ReceiverPID = t_mobileWalletTxn_ReceiverPID;
    }

    public String getT_mobileWalletTxn_Mobile() {
        return t_mobileWalletTxn_Mobile;
    }

    public void setT_mobileWalletTxn_Mobile(String t_mobileWalletTxn_Mobile) {
        this.t_mobileWalletTxn_Mobile = t_mobileWalletTxn_Mobile;
    }

    public String getT_mobileWalletTxn_TxnCat() {
        return t_mobileWalletTxn_TxnCat;
    }

    public void setT_mobileWalletTxn_TxnCat(String t_mobileWalletTxn_TxnCat) {
        this.t_mobileWalletTxn_TxnCat = t_mobileWalletTxn_TxnCat;
    }

    public String getT_mobileWalletTxn_TxnCurrencyType() {
        return t_mobileWalletTxn_TxnCurrencyType;
    }

    public void setT_mobileWalletTxn_TxnCurrencyType(String t_mobileWalletTxn_TxnCurrencyType) {
        this.t_mobileWalletTxn_TxnCurrencyType = t_mobileWalletTxn_TxnCurrencyType;
    }

    public Date getT_mobileWalletTxn_TxnDate() {
        return t_mobileWalletTxn_TxnDate;
    }

    public void setT_mobileWalletTxn_TxnDate(Date t_mobileWalletTxn_TxnDate) {
        this.t_mobileWalletTxn_TxnDate = t_mobileWalletTxn_TxnDate;
    }

    public String getT_mobileWalletTxn_ProdName() {
        return t_mobileWalletTxn_ProdName;
    }

    public void setT_mobileWalletTxn_ProdName(String t_mobileWalletTxn_ProdName) {
        this.t_mobileWalletTxn_ProdName = t_mobileWalletTxn_ProdName;
    }

    public Integer getT_mobileWalletTxn_PayDays() {
        return t_mobileWalletTxn_PayDays;
    }

    public void setT_mobileWalletTxn_PayDays(Integer t_mobileWalletTxn_PayDays) {
        this.t_mobileWalletTxn_PayDays = t_mobileWalletTxn_PayDays;
    }

    public BigDecimal getT_mobileWalletTxn_CreditPayCurrentNum() {
        return t_mobileWalletTxn_CreditPayCurrentNum;
    }

    public void setT_mobileWalletTxn_CreditPayCurrentNum(BigDecimal t_mobileWalletTxn_CreditPayCurrentNum) {
        this.t_mobileWalletTxn_CreditPayCurrentNum = t_mobileWalletTxn_CreditPayCurrentNum;
    }

    public BigDecimal getT_mobileWalletTxn_TotTxnAmount() {
        return t_mobileWalletTxn_TotTxnAmount;
    }

    public void setT_mobileWalletTxn_TotTxnAmount(BigDecimal t_mobileWalletTxn_TotTxnAmount) {
        this.t_mobileWalletTxn_TotTxnAmount = t_mobileWalletTxn_TotTxnAmount;
    }

    public BigDecimal getT_mobileWalletTxn_TopupAmt() {
        return t_mobileWalletTxn_TopupAmt;
    }

    public void setT_mobileWalletTxn_TopupAmt(BigDecimal t_mobileWalletTxn_TopupAmt) {
        this.t_mobileWalletTxn_TopupAmt = t_mobileWalletTxn_TopupAmt;
    }

    public BigDecimal getT_mobileWalletTxn_CryptoTxnAmt() {
        return t_mobileWalletTxn_CryptoTxnAmt;
    }

    public void setT_mobileWalletTxn_CryptoTxnAmt(BigDecimal t_mobileWalletTxn_CryptoTxnAmt) {
        this.t_mobileWalletTxn_CryptoTxnAmt = t_mobileWalletTxn_CryptoTxnAmt;
    }

    public BigDecimal getT_mobileWalletTxn_DebitTxnAmt() {
        return t_mobileWalletTxn_DebitTxnAmt;
    }

    public void setT_mobileWalletTxn_DebitTxnAmt(BigDecimal t_mobileWalletTxn_DebitTxnAmt) {
        this.t_mobileWalletTxn_DebitTxnAmt = t_mobileWalletTxn_DebitTxnAmt;
    }

    public BigDecimal getT_mobileWalletTxn_CreditTxnAmt() {
        return t_mobileWalletTxn_CreditTxnAmt;
    }

    public void setT_mobileWalletTxn_CreditTxnAmt(BigDecimal t_mobileWalletTxn_CreditTxnAmt) {
        this.t_mobileWalletTxn_CreditTxnAmt = t_mobileWalletTxn_CreditTxnAmt;
    }

    public BigDecimal getT_mobileWalletTxn_CreditBalanceNum() {
        return t_mobileWalletTxn_CreditBalanceNum;
    }

    public void setT_mobileWalletTxn_CreditBalanceNum(BigDecimal t_mobileWalletTxn_CreditBalanceNum) {
        this.t_mobileWalletTxn_CreditBalanceNum = t_mobileWalletTxn_CreditBalanceNum;
    }

    public BigDecimal getT_mobileWalletTxn_CreditTxnAmtInit() {
        return t_mobileWalletTxn_CreditTxnAmtInit;
    }

    public void setT_mobileWalletTxn_CreditTxnAmtInit(BigDecimal t_mobileWalletTxn_CreditTxnAmtInit) {
        this.t_mobileWalletTxn_CreditTxnAmtInit = t_mobileWalletTxn_CreditTxnAmtInit;
    }

    public BigDecimal getT_mobileWalletTxn_TotalPayAmt() {
        return t_mobileWalletTxn_TotalPayAmt;
    }

    public void setT_mobileWalletTxn_TotalPayAmt(BigDecimal t_mobileWalletTxn_TotalPayAmt) {
        this.t_mobileWalletTxn_TotalPayAmt = t_mobileWalletTxn_TotalPayAmt;
    }

    public BigDecimal getT_mobileWalletTxn_TotallvorchourAmt() {
        return t_mobileWalletTxn_TotallvorchourAmt;
    }

    public void setT_mobileWalletTxn_TotallvorchourAmt(BigDecimal t_mobileWalletTxn_TotallvorchourAmt) {
        this.t_mobileWalletTxn_TotallvorchourAmt = t_mobileWalletTxn_TotallvorchourAmt;
    }

    public Integer getT_mobileWalletTxn_TotalInterestDays() {
        return t_mobileWalletTxn_TotalInterestDays;
    }

    public void setT_mobileWalletTxn_TotalInterestDays(Integer t_mobileWalletTxn_TotalInterestDays) {
        this.t_mobileWalletTxn_TotalInterestDays = t_mobileWalletTxn_TotalInterestDays;
    }

    public Integer getT_mobileWalletTxn_TxnCounts() {
        return t_mobileWalletTxn_TxnCounts;
    }

    public void setT_mobileWalletTxn_TxnCounts(Integer t_mobileWalletTxn_TxnCounts) {
        this.t_mobileWalletTxn_TxnCounts = t_mobileWalletTxn_TxnCounts;
    }

    public BigDecimal getT_mobileWalletTxn_Interest() {
        return t_mobileWalletTxn_Interest;
    }

    public void setT_mobileWalletTxn_Interest(BigDecimal t_mobileWalletTxn_Interest) {
        this.t_mobileWalletTxn_Interest = t_mobileWalletTxn_Interest;
    }

    public String getT_mobileWalletTxn_ProductName() {
        return t_mobileWalletTxn_ProductName;
    }

    public void setT_mobileWalletTxn_ProductName(String t_mobileWalletTxn_ProductName) {
        this.t_mobileWalletTxn_ProductName = t_mobileWalletTxn_ProductName;
    }

    public String getT_mobileWalletTxn_ProductID() {
        return t_mobileWalletTxn_ProductID;
    }

    public void setT_mobileWalletTxn_ProductID(String t_mobileWalletTxn_ProductID) {
        this.t_mobileWalletTxn_ProductID = t_mobileWalletTxn_ProductID;
    }

    public BigDecimal getT_mobileWalletTxn_TotBalance() {
        return t_mobileWalletTxn_TotBalance;
    }

    public void setT_mobileWalletTxn_TotBalance(BigDecimal t_mobileWalletTxn_TotBalance) {
        this.t_mobileWalletTxn_TotBalance = t_mobileWalletTxn_TotBalance;
    }

    public BigDecimal getT_mobileWalletTxn_BalancePrepayNum() {
        return t_mobileWalletTxn_BalancePrepayNum;
    }

    public void setT_mobileWalletTxn_BalancePrepayNum(BigDecimal t_mobileWalletTxn_BalancePrepayNum) {
        this.t_mobileWalletTxn_BalancePrepayNum = t_mobileWalletTxn_BalancePrepayNum;
    }

    public Date getT_mobileWalletTxn_OverdueRepaymentDate() {
        return t_mobileWalletTxn_OverdueRepaymentDate;
    }

    public void setT_mobileWalletTxn_OverdueRepaymentDate(Date t_mobileWalletTxn_OverdueRepaymentDate) {
        this.t_mobileWalletTxn_OverdueRepaymentDate = t_mobileWalletTxn_OverdueRepaymentDate;
    }

    public String getT_mobileWalletTxn_PayClear() {
        return t_mobileWalletTxn_PayClear;
    }

    public void setT_mobileWalletTxn_PayClear(String t_mobileWalletTxn_PayClear) {
        this.t_mobileWalletTxn_PayClear = t_mobileWalletTxn_PayClear;
    }

    public String getT_mobileWalletTxn_Overdue() {
        return t_mobileWalletTxn_Overdue;
    }

    public void setT_mobileWalletTxn_Overdue(String t_mobileWalletTxn_Overdue) {
        this.t_mobileWalletTxn_Overdue = t_mobileWalletTxn_Overdue;
    }

    public Integer getT_mobileWalletTxn_OverdueDays() {
        return t_mobileWalletTxn_OverdueDays;
    }

    public void setT_mobileWalletTxn_OverdueDays(Integer t_mobileWalletTxn_OverdueDays) {
        this.t_mobileWalletTxn_OverdueDays = t_mobileWalletTxn_OverdueDays;
    }

    public BigDecimal getT_mobileWalletTxn_RefundAmt() {
        return t_mobileWalletTxn_RefundAmt;
    }

    public void setT_mobileWalletTxn_RefundAmt(BigDecimal t_mobileWalletTxn_RefundAmt) {
        this.t_mobileWalletTxn_RefundAmt = t_mobileWalletTxn_RefundAmt;
    }

    public BigDecimal getT_mobileWalletTxn_FinancialInterest() {
        return t_mobileWalletTxn_FinancialInterest;
    }

    public void setT_mobileWalletTxn_FinancialInterest(BigDecimal t_mobileWalletTxn_FinancialInterest) {
        this.t_mobileWalletTxn_FinancialInterest = t_mobileWalletTxn_FinancialInterest;
    }

    public BigDecimal getT_mobileWalletTxn_ServiceFee() {
        return t_mobileWalletTxn_ServiceFee;
    }

    public void setT_mobileWalletTxn_ServiceFee(BigDecimal t_mobileWalletTxn_ServiceFee) {
        this.t_mobileWalletTxn_ServiceFee = t_mobileWalletTxn_ServiceFee;
    }

    public BigDecimal getT_mobileWalletTxn_Poundage() {
        return t_mobileWalletTxn_Poundage;
    }

    public void setT_mobileWalletTxn_Poundage(BigDecimal t_mobileWalletTxn_Poundage) {
        this.t_mobileWalletTxn_Poundage = t_mobileWalletTxn_Poundage;
    }

    public BigDecimal getT_mobileWalletTxn_TierPoundage() {
        return t_mobileWalletTxn_TierPoundage;
    }

    public void setT_mobileWalletTxn_TierPoundage(BigDecimal t_mobileWalletTxn_TierPoundage) {
        this.t_mobileWalletTxn_TierPoundage = t_mobileWalletTxn_TierPoundage;
    }

    public BigDecimal getT_mobileWalletTxn_discountamt() {
        return t_mobileWalletTxn_discountamt;
    }

    public void setT_mobileWalletTxn_discountamt(BigDecimal t_mobileWalletTxn_discountamt) {
        this.t_mobileWalletTxn_discountamt = t_mobileWalletTxn_discountamt;
    }

    public BigDecimal getT_mobileWalletTxn_ClearanceAmt() {
        return t_mobileWalletTxn_ClearanceAmt;
    }

    public void setT_mobileWalletTxn_ClearanceAmt(BigDecimal t_mobileWalletTxn_ClearanceAmt) {
        this.t_mobileWalletTxn_ClearanceAmt = t_mobileWalletTxn_ClearanceAmt;
    }

    public BigDecimal getT_mobileWalletTxn_InterestMargin() {
        return t_mobileWalletTxn_InterestMargin;
    }

    public void setT_mobileWalletTxn_InterestMargin(BigDecimal t_mobileWalletTxn_InterestMargin) {
        this.t_mobileWalletTxn_InterestMargin = t_mobileWalletTxn_InterestMargin;
    }

    public String getT_mobileWalletTxn_PayerBankAcc() {
        return t_mobileWalletTxn_PayerBankAcc;
    }

    public void setT_mobileWalletTxn_PayerBankAcc(String t_mobileWalletTxn_PayerBankAcc) {
        this.t_mobileWalletTxn_PayerBankAcc = t_mobileWalletTxn_PayerBankAcc;
    }

    public String getT_mobileWalletTxn_ReceiverBankAcc() {
        return t_mobileWalletTxn_ReceiverBankAcc;
    }

    public void setT_mobileWalletTxn_ReceiverBankAcc(String t_mobileWalletTxn_ReceiverBankAcc) {
        this.t_mobileWalletTxn_ReceiverBankAcc = t_mobileWalletTxn_ReceiverBankAcc;
    }

    public Date getT_mobileWalletTxn_SysUpdateDate() {
        return t_mobileWalletTxn_SysUpdateDate;
    }

    public void setT_mobileWalletTxn_SysUpdateDate(Date t_mobileWalletTxn_SysUpdateDate) {
        this.t_mobileWalletTxn_SysUpdateDate = t_mobileWalletTxn_SysUpdateDate;
    }

    public String getT_mobileWalletTxn_Paystatus() {
        return t_mobileWalletTxn_Paystatus;
    }

    public void setT_mobileWalletTxn_Paystatus(String t_mobileWalletTxn_Paystatus) {
        this.t_mobileWalletTxn_Paystatus = t_mobileWalletTxn_Paystatus;
    }

    public String getT_mobileWalletTxn_SMS() {
        return t_mobileWalletTxn_SMS;
    }

    public void setT_mobileWalletTxn_SMS(String t_mobileWalletTxn_SMS) {
        this.t_mobileWalletTxn_SMS = t_mobileWalletTxn_SMS;
    }

    public String getT_mobileWalletTxn_SMSRec() {
        return t_mobileWalletTxn_SMSRec;
    }

    public void setT_mobileWalletTxn_SMSRec(String t_mobileWalletTxn_SMSRec) {
        this.t_mobileWalletTxn_SMSRec = t_mobileWalletTxn_SMSRec;
    }

    public String getT_mobileWalletTxn_type() {
        return t_mobileWalletTxn_type;
    }

    public void setT_mobileWalletTxn_type(String t_mobileWalletTxn_type) {
        this.t_mobileWalletTxn_type = t_mobileWalletTxn_type;
    }

    public String getT_mobileWalletTxn_Voucher() {
        return t_mobileWalletTxn_Voucher;
    }

    public void setT_mobileWalletTxn_Voucher(String t_mobileWalletTxn_Voucher) {
        this.t_mobileWalletTxn_Voucher = t_mobileWalletTxn_Voucher;
    }

    public String getT_mobileWalletTxn_productType() {
        return t_mobileWalletTxn_productType;
    }

    public void setT_mobileWalletTxn_productType(String t_mobileWalletTxn_productType) {
        this.t_mobileWalletTxn_productType = t_mobileWalletTxn_productType;
    }

    public String getT_mobileWalletTxn_Txt3() {
        return t_mobileWalletTxn_Txt3;
    }

    public void setT_mobileWalletTxn_Txt3(String t_mobileWalletTxn_Txt3) {
        this.t_mobileWalletTxn_Txt3 = t_mobileWalletTxn_Txt3;
    }

    public String getT_mobileWalletTxn_Txt4() {
        return t_mobileWalletTxn_Txt4;
    }

    public void setT_mobileWalletTxn_Txt4(String t_mobileWalletTxn_Txt4) {
        this.t_mobileWalletTxn_Txt4 = t_mobileWalletTxn_Txt4;
    }

    public String getT_mobileWalletTxn_Txt5() {
        return t_mobileWalletTxn_Txt5;
    }

    public void setT_mobileWalletTxn_Txt5(String t_mobileWalletTxn_Txt5) {
        this.t_mobileWalletTxn_Txt5 = t_mobileWalletTxn_Txt5;
    }

    public Date getTxn_current_time() {
        return txn_current_time;
    }

    public void setTxn_current_time(Date txn_current_time) {
        this.txn_current_time = txn_current_time;
    }

    public Boolean getTxn_sendpayment_succ() {
        return txn_sendpayment_succ;
    }

    public void setTxn_sendpayment_succ(Boolean txn_sendpayment_succ) {
        this.txn_sendpayment_succ = txn_sendpayment_succ;
    }

    public Boolean getTxn_ret_data() {
        return txn_ret_data;
    }

    public void setTxn_ret_data(Boolean txn_ret_data) {
        this.txn_ret_data = txn_ret_data;
    }

}