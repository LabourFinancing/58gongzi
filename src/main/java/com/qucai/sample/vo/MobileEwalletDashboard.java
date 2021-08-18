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
public class MobileEwalletDashboard implements Serializable {

    /* Txn info char */
    private String ret;

    private String t_mobilePersonalEwallet_TxnID;
    
    private String t_mobilePersonalEwallet_OrderCode;

    private String t_mobilePersonalEwallet_TxnCat;

    private String t_mobilePersonalEwallet_PayerEwalletID;

    private String t_mobilePersonalEwallet_PayerPID;
    
    private String t_mobilePersonalEwallet_PayerMobile;

    private String t_mobilePersonalEwallet_PayerEwalletAddress;

    private String t_mobilePersonalEwallet_PayerName;

    private BigDecimal t_mobilePersonalEwallet_PayerOriginTotCNYBalance;

    private BigDecimal t_mobilePersonalEwallet_PayerTotCNYBalance;

    private String t_mobilePersonalEwallet_ReceiverEwalletID;

    private String t_mobilePersonalEwallet_ReceiverPID;

    private String t_mobilePersonalEwallet_ReceiverName;

    private String t_mobilePersonalEwallet_ReceiverMobile;

    private String t_mobilePersonalEwallet_ReceiverEwalletAddress;
    
    private BigDecimal t_mobilePersonalEwallet_ReceiverOriginTotCNYBalance;

    private BigDecimal t_mobilePersonalEwallet_ReceiverTotCNYBalance;

    private String t_mobilePersonalEwallet_ID;

    private String t_mobilePersonalEwallet_ApplierID;

    private String t_mobilePersonalEwallet_ApplierPID;

    private String t_mobilePersonalEwallet_ApplierName;

    private String t_mobilePersonalEwallet_Passport;

    private String t_mobilePersonalEwallet_ScanCode;

    private String t_mobilePersonalEwallet_QRcode;

    private String t_mobilePersonalEwallet_alipayAcc;

    private String t_mobilePersonalEwallet_wechatpayAcc;

    private String t_mobilePersonalEwallet_unionpayAcc;

    private String t_mobilePersonalEwallet_CryptoC;

    private String t_mobilePersonalEwallet_Voucher;

    private String t_mobilePersonalEwallet_VoucherDigi;

    private String t_mobilePersonalEwallet_Creditcard;

    private String t_mobilePersonalEwallet_Debitcard;

    private String t_mobilePersonalEwallet_ClearNum;

    private String t_mobilePersonalEwallet_ClearOrg;

    private String t_mobilePersonalEwallet_PayCat;

    private Date t_mobilePersonalEwallet_PayDate;

    private String t_mobilePersonalEwallet_ProdName;

    private String t_mobilePersonalEwallet_ProdType;

    private Integer t_mobilePersonalEwallet_PayDays;

    private String t_mobilePersonalEwallet_Reciept;

    private BigDecimal t_mobilePersonalEwallet_TotCNYBalance;

    private BigDecimal t_mobilePersonalEwallet_TotFXBalance;

    private BigDecimal t_mobilePersonalEwallet_TotCryptoBalance;

    private BigDecimal t_mobilePersonalEwallet_TotAssetES;

    private BigDecimal t_mobilePersonalEwallet_DebitPayAmt;

    private BigDecimal t_mobilePersonalEwallet_ApplyPayAmount;
 
    private BigDecimal t_mobilePersonalEwallet_CreditPrepayBalanceNum;

    private BigDecimal t_mobilePersonalEwallet_CreditPayAmt;

    private BigDecimal t_mobilePersonalEwallet_CreditPayAmtInit;

    private BigDecimal t_mobilePersonalEwallet_TotalPrepayAmt;

    private Integer t_mobilePersonalEwallet_TotalInterestDays;

    private Integer t_mobilePersonalEwallet_PayCounts;

    private BigDecimal t_mobilePersonalEwallet_Interest;

    private BigDecimal t_mobilePersonalEwallet_TotLimit;

    private BigDecimal t_mobilePersonalEwallet_Worth;

    private BigDecimal t_mobilePersonalEwallet_DiscountRate;

    private BigDecimal t_mobilePersonalEwallet_BalanceInterest;

    private java.util.Date t_mobilePersonalEwallet_OverdueRepaymentDate;

    private String t_mobilePersonalEwallet_PrepayClear;

    private String t_mobilePersonalEwallet_Overdue;

    private BigDecimal t_mobilePersonalEwallet_OverdueTotalAmount;

    private BigDecimal t_mobilePersonalEwallet_FinancialInterest;

    private BigDecimal t_mobilePersonalEwallet_ServiceFee;

    private BigDecimal t_mobilePersonalEwallet_Poundage;

    private BigDecimal t_mobilePersonalEwallet_TierPoundage;

    private BigDecimal t_mobilePersonalEwallet_InterestMargin;

    private String t_mobilePersonalEwallet_BankAccName;

    private String t_mobilePersonalEwallet_BankAcc;

    private Date t_mobilePersonalEwallet_SysUpdateDate;

    private Integer t_mobilePersonalEwallet_OverdueDays;

    private String t_mobilePersonalEwallet_Paystatus;

    private String t_mobilePersonalEwallet_SMS;

    private String t_MobilePersonalewallet_PaymentVersion;

    private String t_MobilePersonalewallet_PaymentType;

    private String t_mobilePersonalEwallet_bkp;

    private String t_MobilePersonalewallet_AccCat;

    private String t_mobilePersonalEwallet_treasuryID;

    private String t_mobilePersonalEwallet_eproposal;

    private BigDecimal t_mobilePersonalEwallet_TxnAmount;

    private String t_mobilePersonalEwallet_ErrCode;

    private String t_mobilePersonalEwallet_ErrMsg;

    public String getT_mobilePersonalEwallet_OrderCode() {
        return t_mobilePersonalEwallet_OrderCode;
    }

    public void setT_mobilePersonalEwallet_OrderCode(String t_mobilePersonalEwallet_OrderCode) {
        this.t_mobilePersonalEwallet_OrderCode = t_mobilePersonalEwallet_OrderCode;
    }

    public String getT_mobilePersonalEwallet_PayerEwalletID() {
        return t_mobilePersonalEwallet_PayerEwalletID;
    }

    public void setT_mobilePersonalEwallet_PayerEwalletID(String t_mobilePersonalEwallet_PayerEwalletID) {
        this.t_mobilePersonalEwallet_PayerEwalletID = t_mobilePersonalEwallet_PayerEwalletID;
    }

    public String getT_MobilePersonalewallet_PaymentType() {
        return t_MobilePersonalewallet_PaymentType;
    }

    public void setT_MobilePersonalewallet_PaymentType(String t_MobilePersonalewallet_PaymentType) {
        this.t_MobilePersonalewallet_PaymentType = t_MobilePersonalewallet_PaymentType;
    }

    public String getT_mobilePersonalEwallet_ReceiverEwalletID() {
        return t_mobilePersonalEwallet_ReceiverEwalletID;
    }

    public void setT_mobilePersonalEwallet_ReceiverEwalletID(String t_mobilePersonalEwallet_ReceiverEwalletID) {
        this.t_mobilePersonalEwallet_ReceiverEwalletID = t_mobilePersonalEwallet_ReceiverEwalletID;
    }
    
    public String getT_mobilePersonalEwallet_TxnID() {
        return t_mobilePersonalEwallet_TxnID;
    }

    public void setT_mobilePersonalEwallet_TxnID(String t_mobilePersonalEwallet_TxnID) {
        this.t_mobilePersonalEwallet_TxnID = t_mobilePersonalEwallet_TxnID;
    }

    public String getT_mobilePersonalEwallet_TxnCat() {
        return t_mobilePersonalEwallet_TxnCat;
    }

    public void setT_mobilePersonalEwallet_TxnCat(String t_mobilePersonalEwallet_TxnCat) {
        this.t_mobilePersonalEwallet_TxnCat = t_mobilePersonalEwallet_TxnCat;
    }
    
    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }


    public String getT_mobilePersonalEwallet_PayerMobile() {
        return t_mobilePersonalEwallet_PayerMobile;
    }

    public void setT_mobilePersonalEwallet_PayerMobile(String t_mobilePersonalEwallet_PayerMobile) {
        this.t_mobilePersonalEwallet_PayerMobile = t_mobilePersonalEwallet_PayerMobile;
    }

    public String getT_mobilePersonalEwallet_PayerEwalletAddress() {
        return t_mobilePersonalEwallet_PayerEwalletAddress;
    }

    public void setT_mobilePersonalEwallet_PayerEwalletAddress(String t_mobilePersonalEwallet_PayerEwalletAddress) {
        this.t_mobilePersonalEwallet_PayerEwalletAddress = t_mobilePersonalEwallet_PayerEwalletAddress;
    }

    public String getT_mobilePersonalEwallet_ReceiverMobile() {
        return t_mobilePersonalEwallet_ReceiverMobile;
    }

    public void setT_mobilePersonalEwallet_ReceiverMobile(String t_mobilePersonalEwallet_ReceiverMobile) {
        this.t_mobilePersonalEwallet_ReceiverMobile = t_mobilePersonalEwallet_ReceiverMobile;
    }

    public String getT_mobilePersonalEwallet_ReceiverEwalletAddress() {
        return t_mobilePersonalEwallet_ReceiverEwalletAddress;
    }

    public void setT_mobilePersonalEwallet_ReceiverEwalletAddress(String t_mobilePersonalEwallet_ReceiverEwalletAddress) {
        this.t_mobilePersonalEwallet_ReceiverEwalletAddress = t_mobilePersonalEwallet_ReceiverEwalletAddress;
    }

    public BigDecimal getT_mobilePersonalEwallet_PayerOriginTotCNYBalance() {
        return t_mobilePersonalEwallet_PayerOriginTotCNYBalance;
    }

    public void setT_mobilePersonalEwallet_PayerOriginTotCNYBalance(BigDecimal t_mobilePersonalEwallet_PayerOriginTotCNYBalance) {
        this.t_mobilePersonalEwallet_PayerOriginTotCNYBalance = t_mobilePersonalEwallet_PayerOriginTotCNYBalance;
    }

    public BigDecimal getT_mobilePersonalEwallet_ReceiverOriginTotCNYBalance() {
        return t_mobilePersonalEwallet_ReceiverOriginTotCNYBalance;
    }

    public void setT_mobilePersonalEwallet_ReceiverOriginTotCNYBalance(BigDecimal t_mobilePersonalEwallet_ReceiverOriginTotCNYBalance) {
        this.t_mobilePersonalEwallet_ReceiverOriginTotCNYBalance = t_mobilePersonalEwallet_ReceiverOriginTotCNYBalance;
    }

    public String getT_mobilePersonalEwallet_PayerPID() {
        return t_mobilePersonalEwallet_PayerPID;
    }

    public void setT_mobilePersonalEwallet_PayerPID(String t_mobilePersonalEwallet_PayerPID) {
        this.t_mobilePersonalEwallet_PayerPID = t_mobilePersonalEwallet_PayerPID;
    }

    public String getT_mobilePersonalEwallet_PayerName() {
        return t_mobilePersonalEwallet_PayerName;
    }

    public void setT_mobilePersonalEwallet_PayerName(String t_mobilePersonalEwallet_PayerName) {
        this.t_mobilePersonalEwallet_PayerName = t_mobilePersonalEwallet_PayerName;
    }

    public BigDecimal getT_mobilePersonalEwallet_PayerTotCNYBalance() {
        return t_mobilePersonalEwallet_PayerTotCNYBalance;
    }

    public void setT_mobilePersonalEwallet_PayerTotCNYBalance(BigDecimal t_mobilePersonalEwallet_PayerTotCNYBalance) {
        this.t_mobilePersonalEwallet_PayerTotCNYBalance = t_mobilePersonalEwallet_PayerTotCNYBalance;
    }

    public String getT_mobilePersonalEwallet_ReceiverPID() {
        return t_mobilePersonalEwallet_ReceiverPID;
    }

    public void setT_mobilePersonalEwallet_ReceiverPID(String t_mobilePersonalEwallet_ReceiverPID) {
        this.t_mobilePersonalEwallet_ReceiverPID = t_mobilePersonalEwallet_ReceiverPID;
    }

    public String getT_mobilePersonalEwallet_ReceiverName() {
        return t_mobilePersonalEwallet_ReceiverName;
    }

    public void setT_mobilePersonalEwallet_ReceiverName(String t_mobilePersonalEwallet_ReceiverName) {
        this.t_mobilePersonalEwallet_ReceiverName = t_mobilePersonalEwallet_ReceiverName;
    }

    public BigDecimal getT_mobilePersonalEwallet_ReceiverTotCNYBalance() {
        return t_mobilePersonalEwallet_ReceiverTotCNYBalance;
    }

    public void setT_mobilePersonalEwallet_ReceiverTotCNYBalance(BigDecimal t_mobilePersonalEwallet_ReceiverTotCNYBalance) {
        this.t_mobilePersonalEwallet_ReceiverTotCNYBalance = t_mobilePersonalEwallet_ReceiverTotCNYBalance;
    }

    public String getT_mobilePersonalEwallet_ID() {
        return t_mobilePersonalEwallet_ID;
    }

    public void setT_mobilePersonalEwallet_ID(String t_mobilePersonalEwallet_ID) {
        this.t_mobilePersonalEwallet_ID = t_mobilePersonalEwallet_ID;
    }

    public String getT_mobilePersonalEwallet_ApplierID() {
        return t_mobilePersonalEwallet_ApplierID;
    }

    public void setT_mobilePersonalEwallet_ApplierID(String t_mobilePersonalEwallet_ApplierID) {
        this.t_mobilePersonalEwallet_ApplierID = t_mobilePersonalEwallet_ApplierID;
    }

    public String getT_mobilePersonalEwallet_ApplierPID() {
        return t_mobilePersonalEwallet_ApplierPID;
    }

    public void setT_mobilePersonalEwallet_ApplierPID(String t_mobilePersonalEwallet_ApplierPID) {
        this.t_mobilePersonalEwallet_ApplierPID = t_mobilePersonalEwallet_ApplierPID;
    }

    public String getT_mobilePersonalEwallet_ApplierName() {
        return t_mobilePersonalEwallet_ApplierName;
    }

    public void setT_mobilePersonalEwallet_ApplierName(String t_mobilePersonalEwallet_ApplierName) {
        this.t_mobilePersonalEwallet_ApplierName = t_mobilePersonalEwallet_ApplierName;
    }

    public String getT_mobilePersonalEwallet_Passport() {
        return t_mobilePersonalEwallet_Passport;
    }

    public void setT_mobilePersonalEwallet_Passport(String t_mobilePersonalEwallet_Passport) {
        this.t_mobilePersonalEwallet_Passport = t_mobilePersonalEwallet_Passport;
    }

    public String getT_mobilePersonalEwallet_ScanCode() {
        return t_mobilePersonalEwallet_ScanCode;
    }

    public void setT_mobilePersonalEwallet_ScanCode(String t_mobilePersonalEwallet_ScanCode) {
        this.t_mobilePersonalEwallet_ScanCode = t_mobilePersonalEwallet_ScanCode;
    }

    public String getT_mobilePersonalEwallet_QRcode() {
        return t_mobilePersonalEwallet_QRcode;
    }

    public void setT_mobilePersonalEwallet_QRcode(String t_mobilePersonalEwallet_QRcode) {
        this.t_mobilePersonalEwallet_QRcode = t_mobilePersonalEwallet_QRcode;
    }

    public String getT_mobilePersonalEwallet_alipayAcc() {
        return t_mobilePersonalEwallet_alipayAcc;
    }

    public void setT_mobilePersonalEwallet_alipayAcc(String t_mobilePersonalEwallet_alipayAcc) {
        this.t_mobilePersonalEwallet_alipayAcc = t_mobilePersonalEwallet_alipayAcc;
    }

    public String getT_mobilePersonalEwallet_wechatpayAcc() {
        return t_mobilePersonalEwallet_wechatpayAcc;
    }

    public void setT_mobilePersonalEwallet_wechatpayAcc(String t_mobilePersonalEwallet_wechatpayAcc) {
        this.t_mobilePersonalEwallet_wechatpayAcc = t_mobilePersonalEwallet_wechatpayAcc;
    }

    public String getT_mobilePersonalEwallet_unionpayAcc() {
        return t_mobilePersonalEwallet_unionpayAcc;
    }

    public void setT_mobilePersonalEwallet_unionpayAcc(String t_mobilePersonalEwallet_unionpayAcc) {
        this.t_mobilePersonalEwallet_unionpayAcc = t_mobilePersonalEwallet_unionpayAcc;
    }

    public String getT_mobilePersonalEwallet_CryptoC() {
        return t_mobilePersonalEwallet_CryptoC;
    }

    public void setT_mobilePersonalEwallet_CryptoC(String t_mobilePersonalEwallet_CryptoC) {
        this.t_mobilePersonalEwallet_CryptoC = t_mobilePersonalEwallet_CryptoC;
    }

    public String getT_mobilePersonalEwallet_Voucher() {
        return t_mobilePersonalEwallet_Voucher;
    }

    public void setT_mobilePersonalEwallet_Voucher(String t_mobilePersonalEwallet_Voucher) {
        this.t_mobilePersonalEwallet_Voucher = t_mobilePersonalEwallet_Voucher;
    }

    public String getT_mobilePersonalEwallet_VoucherDigi() {
        return t_mobilePersonalEwallet_VoucherDigi;
    }

    public void setT_mobilePersonalEwallet_VoucherDigi(String t_mobilePersonalEwallet_VoucherDigi) {
        this.t_mobilePersonalEwallet_VoucherDigi = t_mobilePersonalEwallet_VoucherDigi;
    }

    public String getT_mobilePersonalEwallet_Creditcard() {
        return t_mobilePersonalEwallet_Creditcard;
    }

    public void setT_mobilePersonalEwallet_Creditcard(String t_mobilePersonalEwallet_Creditcard) {
        this.t_mobilePersonalEwallet_Creditcard = t_mobilePersonalEwallet_Creditcard;
    }

    public String getT_mobilePersonalEwallet_Debitcard() {
        return t_mobilePersonalEwallet_Debitcard;
    }

    public void setT_mobilePersonalEwallet_Debitcard(String t_mobilePersonalEwallet_Debitcard) {
        this.t_mobilePersonalEwallet_Debitcard = t_mobilePersonalEwallet_Debitcard;
    }

    public String getT_mobilePersonalEwallet_ClearNum() {
        return t_mobilePersonalEwallet_ClearNum;
    }

    public void setT_mobilePersonalEwallet_ClearNum(String t_mobilePersonalEwallet_ClearNum) {
        this.t_mobilePersonalEwallet_ClearNum = t_mobilePersonalEwallet_ClearNum;
    }

    public String getT_mobilePersonalEwallet_ClearOrg() {
        return t_mobilePersonalEwallet_ClearOrg;
    }

    public void setT_mobilePersonalEwallet_ClearOrg(String t_mobilePersonalEwallet_ClearOrg) {
        this.t_mobilePersonalEwallet_ClearOrg = t_mobilePersonalEwallet_ClearOrg;
    }

    public String getT_mobilePersonalEwallet_PayCat() {
        return t_mobilePersonalEwallet_PayCat;
    }

    public void setT_mobilePersonalEwallet_PayCat(String t_mobilePersonalEwallet_PayCat) {
        this.t_mobilePersonalEwallet_PayCat = t_mobilePersonalEwallet_PayCat;
    }

    public Date getT_mobilePersonalEwallet_PayDate() {
        return t_mobilePersonalEwallet_PayDate;
    }

    public void setT_mobilePersonalEwallet_PayDate(Date t_mobilePersonalEwallet_PayDate) {
        this.t_mobilePersonalEwallet_PayDate = t_mobilePersonalEwallet_PayDate;
    }

    public String getT_mobilePersonalEwallet_ProdName() {
        return t_mobilePersonalEwallet_ProdName;
    }

    public void setT_mobilePersonalEwallet_ProdName(String t_mobilePersonalEwallet_ProdName) {
        this.t_mobilePersonalEwallet_ProdName = t_mobilePersonalEwallet_ProdName;
    }

    public String getT_mobilePersonalEwallet_ProdType() {
        return t_mobilePersonalEwallet_ProdType;
    }

    public void setT_mobilePersonalEwallet_ProdType(String t_mobilePersonalEwallet_ProdType) {
        this.t_mobilePersonalEwallet_ProdType = t_mobilePersonalEwallet_ProdType;
    }

    public Integer getT_mobilePersonalEwallet_PayDays() {
        return t_mobilePersonalEwallet_PayDays;
    }

    public void setT_mobilePersonalEwallet_PayDays(Integer t_mobilePersonalEwallet_PayDays) {
        this.t_mobilePersonalEwallet_PayDays = t_mobilePersonalEwallet_PayDays;
    }

    public String getT_mobilePersonalEwallet_Reciept() {
        return t_mobilePersonalEwallet_Reciept;
    }

    public void setT_mobilePersonalEwallet_Reciept(String t_mobilePersonalEwallet_Reciept) {
        this.t_mobilePersonalEwallet_Reciept = t_mobilePersonalEwallet_Reciept;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotCNYBalance() {
        return t_mobilePersonalEwallet_TotCNYBalance;
    }

    public void setT_mobilePersonalEwallet_TotCNYBalance(BigDecimal t_mobilePersonalEwallet_TotCNYBalance) {
        this.t_mobilePersonalEwallet_TotCNYBalance = t_mobilePersonalEwallet_TotCNYBalance;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotFXBalance() {
        return t_mobilePersonalEwallet_TotFXBalance;
    }

    public void setT_mobilePersonalEwallet_TotFXBalance(BigDecimal t_mobilePersonalEwallet_TotFXBalance) {
        this.t_mobilePersonalEwallet_TotFXBalance = t_mobilePersonalEwallet_TotFXBalance;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotCryptoBalance() {
        return t_mobilePersonalEwallet_TotCryptoBalance;
    }

    public void setT_mobilePersonalEwallet_TotCryptoBalance(BigDecimal t_mobilePersonalEwallet_TotCryptoBalance) {
        this.t_mobilePersonalEwallet_TotCryptoBalance = t_mobilePersonalEwallet_TotCryptoBalance;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotAssetES() {
        return t_mobilePersonalEwallet_TotAssetES;
    }

    public void setT_mobilePersonalEwallet_TotAssetES(BigDecimal t_mobilePersonalEwallet_TotAssetES) {
        this.t_mobilePersonalEwallet_TotAssetES = t_mobilePersonalEwallet_TotAssetES;
    }

    public BigDecimal getT_mobilePersonalEwallet_DebitPayAmt() {
        return t_mobilePersonalEwallet_DebitPayAmt;
    }

    public void setT_mobilePersonalEwallet_DebitPayAmt(BigDecimal t_mobilePersonalEwallet_DebitPayAmt) {
        this.t_mobilePersonalEwallet_DebitPayAmt = t_mobilePersonalEwallet_DebitPayAmt;
    }

    public BigDecimal getT_mobilePersonalEwallet_ApplyPayAmount() {
        return t_mobilePersonalEwallet_ApplyPayAmount;
    }

    public void setT_mobilePersonalEwallet_ApplyPayAmount(BigDecimal t_mobilePersonalEwallet_ApplyPayAmount) {
        this.t_mobilePersonalEwallet_ApplyPayAmount = t_mobilePersonalEwallet_ApplyPayAmount;
    }

    public BigDecimal getT_mobilePersonalEwallet_CreditPrepayBalanceNum() {
        return t_mobilePersonalEwallet_CreditPrepayBalanceNum;
    }

    public void setT_mobilePersonalEwallet_CreditPrepayBalanceNum(BigDecimal t_mobilePersonalEwallet_CreditPrepayBalanceNum) {
        this.t_mobilePersonalEwallet_CreditPrepayBalanceNum = t_mobilePersonalEwallet_CreditPrepayBalanceNum;
    }

    public BigDecimal getT_mobilePersonalEwallet_CreditPayAmt() {
        return t_mobilePersonalEwallet_CreditPayAmt;
    }

    public void setT_mobilePersonalEwallet_CreditPayAmt(BigDecimal t_mobilePersonalEwallet_CreditPayAmt) {
        this.t_mobilePersonalEwallet_CreditPayAmt = t_mobilePersonalEwallet_CreditPayAmt;
    }

    public BigDecimal getT_mobilePersonalEwallet_CreditPayAmtInit() {
        return t_mobilePersonalEwallet_CreditPayAmtInit;
    }

    public void setT_mobilePersonalEwallet_CreditPayAmtInit(BigDecimal t_mobilePersonalEwallet_CreditPayAmtInit) {
        this.t_mobilePersonalEwallet_CreditPayAmtInit = t_mobilePersonalEwallet_CreditPayAmtInit;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotalPrepayAmt() {
        return t_mobilePersonalEwallet_TotalPrepayAmt;
    }

    public void setT_mobilePersonalEwallet_TotalPrepayAmt(BigDecimal t_mobilePersonalEwallet_TotalPrepayAmt) {
        this.t_mobilePersonalEwallet_TotalPrepayAmt = t_mobilePersonalEwallet_TotalPrepayAmt;
    }

    public Integer getT_mobilePersonalEwallet_TotalInterestDays() {
        return t_mobilePersonalEwallet_TotalInterestDays;
    }

    public void setT_mobilePersonalEwallet_TotalInterestDays(Integer t_mobilePersonalEwallet_TotalInterestDays) {
        this.t_mobilePersonalEwallet_TotalInterestDays = t_mobilePersonalEwallet_TotalInterestDays;
    }

    public Integer getT_mobilePersonalEwallet_PayCounts() {
        return t_mobilePersonalEwallet_PayCounts;
    }

    public void setT_mobilePersonalEwallet_PayCounts(Integer t_mobilePersonalEwallet_PayCounts) {
        this.t_mobilePersonalEwallet_PayCounts = t_mobilePersonalEwallet_PayCounts;
    }

    public BigDecimal getT_mobilePersonalEwallet_Interest() {
        return t_mobilePersonalEwallet_Interest;
    }

    public void setT_mobilePersonalEwallet_Interest(BigDecimal t_mobilePersonalEwallet_Interest) {
        this.t_mobilePersonalEwallet_Interest = t_mobilePersonalEwallet_Interest;
    }

    public BigDecimal getT_mobilePersonalEwallet_TotLimit() {
        return t_mobilePersonalEwallet_TotLimit;
    }

    public void setT_mobilePersonalEwallet_TotLimit(BigDecimal t_mobilePersonalEwallet_TotLimit) {
        this.t_mobilePersonalEwallet_TotLimit = t_mobilePersonalEwallet_TotLimit;
    }

    public BigDecimal getT_mobilePersonalEwallet_Worth() {
        return t_mobilePersonalEwallet_Worth;
    }

    public void setT_mobilePersonalEwallet_Worth(BigDecimal t_mobilePersonalEwallet_Worth) {
        this.t_mobilePersonalEwallet_Worth = t_mobilePersonalEwallet_Worth;
    }

    public BigDecimal getT_mobilePersonalEwallet_DiscountRate() {
        return t_mobilePersonalEwallet_DiscountRate;
    }

    public void setT_mobilePersonalEwallet_DiscountRate(BigDecimal t_mobilePersonalEwallet_DiscountRate) {
        this.t_mobilePersonalEwallet_DiscountRate = t_mobilePersonalEwallet_DiscountRate;
    }

    public BigDecimal getT_mobilePersonalEwallet_BalanceInterest() {
        return t_mobilePersonalEwallet_BalanceInterest;
    }

    public void setT_mobilePersonalEwallet_BalanceInterest(BigDecimal t_mobilePersonalEwallet_BalanceInterest) {
        this.t_mobilePersonalEwallet_BalanceInterest = t_mobilePersonalEwallet_BalanceInterest;
    }

    public Date getT_mobilePersonalEwallet_OverdueRepaymentDate() {
        return t_mobilePersonalEwallet_OverdueRepaymentDate;
    }

    public void setT_mobilePersonalEwallet_OverdueRepaymentDate(Date t_mobilePersonalEwallet_OverdueRepaymentDate) {
        this.t_mobilePersonalEwallet_OverdueRepaymentDate = t_mobilePersonalEwallet_OverdueRepaymentDate;
    }

    public String getT_mobilePersonalEwallet_PrepayClear() {
        return t_mobilePersonalEwallet_PrepayClear;
    }

    public void setT_mobilePersonalEwallet_PrepayClear(String t_mobilePersonalEwallet_PrepayClear) {
        this.t_mobilePersonalEwallet_PrepayClear = t_mobilePersonalEwallet_PrepayClear;
    }

    public String getT_mobilePersonalEwallet_Overdue() {
        return t_mobilePersonalEwallet_Overdue;
    }

    public void setT_mobilePersonalEwallet_Overdue(String t_mobilePersonalEwallet_Overdue) {
        this.t_mobilePersonalEwallet_Overdue = t_mobilePersonalEwallet_Overdue;
    }

    public BigDecimal getT_mobilePersonalEwallet_OverdueTotalAmount() {
        return t_mobilePersonalEwallet_OverdueTotalAmount;
    }

    public void setT_mobilePersonalEwallet_OverdueTotalAmount(BigDecimal t_mobilePersonalEwallet_OverdueTotalAmount) {
        this.t_mobilePersonalEwallet_OverdueTotalAmount = t_mobilePersonalEwallet_OverdueTotalAmount;
    }

    public BigDecimal getT_mobilePersonalEwallet_FinancialInterest() {
        return t_mobilePersonalEwallet_FinancialInterest;
    }

    public void setT_mobilePersonalEwallet_FinancialInterest(BigDecimal t_mobilePersonalEwallet_FinancialInterest) {
        this.t_mobilePersonalEwallet_FinancialInterest = t_mobilePersonalEwallet_FinancialInterest;
    }

    public BigDecimal getT_mobilePersonalEwallet_ServiceFee() {
        return t_mobilePersonalEwallet_ServiceFee;
    }

    public void setT_mobilePersonalEwallet_ServiceFee(BigDecimal t_mobilePersonalEwallet_ServiceFee) {
        this.t_mobilePersonalEwallet_ServiceFee = t_mobilePersonalEwallet_ServiceFee;
    }

    public BigDecimal getT_mobilePersonalEwallet_Poundage() {
        return t_mobilePersonalEwallet_Poundage;
    }

    public void setT_mobilePersonalEwallet_Poundage(BigDecimal t_mobilePersonalEwallet_Poundage) {
        this.t_mobilePersonalEwallet_Poundage = t_mobilePersonalEwallet_Poundage;
    }

    public BigDecimal getT_mobilePersonalEwallet_TierPoundage() {
        return t_mobilePersonalEwallet_TierPoundage;
    }

    public void setT_mobilePersonalEwallet_TierPoundage(BigDecimal t_mobilePersonalEwallet_TierPoundage) {
        this.t_mobilePersonalEwallet_TierPoundage = t_mobilePersonalEwallet_TierPoundage;
    }

    public BigDecimal getT_mobilePersonalEwallet_InterestMargin() {
        return t_mobilePersonalEwallet_InterestMargin;
    }

    public void setT_mobilePersonalEwallet_InterestMargin(BigDecimal t_mobilePersonalEwallet_InterestMargin) {
        this.t_mobilePersonalEwallet_InterestMargin = t_mobilePersonalEwallet_InterestMargin;
    }

    public String getT_mobilePersonalEwallet_BankAccName() {
        return t_mobilePersonalEwallet_BankAccName;
    }

    public void setT_mobilePersonalEwallet_BankAccName(String t_mobilePersonalEwallet_BankAccName) {
        this.t_mobilePersonalEwallet_BankAccName = t_mobilePersonalEwallet_BankAccName;
    }

    public String getT_mobilePersonalEwallet_BankAcc() {
        return t_mobilePersonalEwallet_BankAcc;
    }

    public void setT_mobilePersonalEwallet_BankAcc(String t_mobilePersonalEwallet_BankAcc) {
        this.t_mobilePersonalEwallet_BankAcc = t_mobilePersonalEwallet_BankAcc;
    }

    public Date getT_mobilePersonalEwallet_SysUpdateDate() {
        return t_mobilePersonalEwallet_SysUpdateDate;
    }

    public void setT_mobilePersonalEwallet_SysUpdateDate(Date t_mobilePersonalEwallet_SysUpdateDate) {
        this.t_mobilePersonalEwallet_SysUpdateDate = t_mobilePersonalEwallet_SysUpdateDate;
    }

    public Integer getT_mobilePersonalEwallet_OverdueDays() {
        return t_mobilePersonalEwallet_OverdueDays;
    }

    public void setT_mobilePersonalEwallet_OverdueDays(Integer t_mobilePersonalEwallet_OverdueDays) {
        this.t_mobilePersonalEwallet_OverdueDays = t_mobilePersonalEwallet_OverdueDays;
    }

    public String getT_mobilePersonalEwallet_Paystatus() {
        return t_mobilePersonalEwallet_Paystatus;
    }

    public void setT_mobilePersonalEwallet_Paystatus(String t_mobilePersonalEwallet_Paystatus) {
        this.t_mobilePersonalEwallet_Paystatus = t_mobilePersonalEwallet_Paystatus;
    }

    public String getT_mobilePersonalEwallet_SMS() {
        return t_mobilePersonalEwallet_SMS;
    }

    public void setT_mobilePersonalEwallet_SMS(String t_mobilePersonalEwallet_SMS) {
        this.t_mobilePersonalEwallet_SMS = t_mobilePersonalEwallet_SMS;
    }

    public String getT_MobilePersonalewallet_PaymentVersion() {
        return t_MobilePersonalewallet_PaymentVersion;
    }

    public void setT_MobilePersonalewallet_PaymentVersion(String t_MobilePersonalewallet_PaymentVersion) {
        this.t_MobilePersonalewallet_PaymentVersion = t_MobilePersonalewallet_PaymentVersion;
    }

    public String getT_mobilePersonalEwallet_bkp() {
        return t_mobilePersonalEwallet_bkp;
    }

    public void setT_mobilePersonalEwallet_bkp(String t_mobilePersonalEwallet_bkp) {
        this.t_mobilePersonalEwallet_bkp = t_mobilePersonalEwallet_bkp;
    }

    public String getT_MobilePersonalewallet_AccCat() {
        return t_MobilePersonalewallet_AccCat;
    }

    public void setT_MobilePersonalewallet_AccCat(String t_MobilePersonalewallet_AccCat) {
        this.t_MobilePersonalewallet_AccCat = t_MobilePersonalewallet_AccCat;
    }

    public String getT_mobilePersonalEwallet_treasuryID() {
        return t_mobilePersonalEwallet_treasuryID;
    }

    public void setT_mobilePersonalEwallet_treasuryID(String t_mobilePersonalEwallet_treasuryID) {
        this.t_mobilePersonalEwallet_treasuryID = t_mobilePersonalEwallet_treasuryID;
    }

    public String getT_mobilePersonalEwallet_eproposal() {
        return t_mobilePersonalEwallet_eproposal;
    }

    public void setT_mobilePersonalEwallet_eproposal(String t_mobilePersonalEwallet_eproposal) {
        this.t_mobilePersonalEwallet_eproposal = t_mobilePersonalEwallet_eproposal;
    }

    public BigDecimal getT_mobilePersonalEwallet_TxnAmount() {
        return t_mobilePersonalEwallet_TxnAmount;
    }

    public void setT_mobilePersonalEwallet_TxnAmount(BigDecimal t_mobilePersonalEwallet_TxnAmount) {
        this.t_mobilePersonalEwallet_TxnAmount = t_mobilePersonalEwallet_TxnAmount;
    }

    public String getT_mobilePersonalEwallet_ErrCode() {
        return t_mobilePersonalEwallet_ErrCode;
    }

    public void setT_mobilePersonalEwallet_ErrCode(String t_mobilePersonalEwallet_ErrCode) {
        this.t_mobilePersonalEwallet_ErrCode = t_mobilePersonalEwallet_ErrCode;
    }

    public String getT_mobilePersonalEwallet_ErrMsg() {
        return t_mobilePersonalEwallet_ErrMsg;
    }

    public void setT_mobilePersonalEwallet_ErrMsg(String t_mobilePersonalEwallet_ErrMsg) {
        this.t_mobilePersonalEwallet_ErrMsg = t_mobilePersonalEwallet_ErrMsg;
    }

}