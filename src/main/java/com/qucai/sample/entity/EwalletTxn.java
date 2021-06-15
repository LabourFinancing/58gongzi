package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class EwalletTxn implements Serializable {
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
   
   private String t_WalletTxn_ID;

   private String t_WalletTxn_QRcode;
   
   private String t_WalletTxn_Num;

   private String t_WalletTxn_Vendor;

   private String t_WalletTxn_ClearNum;

   private String t_WalletTxn_ClearOrg;
	
   private String t_WalletTxn_PayerName;

   private String t_WalletTxn_PayerID;
   
   private String t_WalletTxn_PayerPID;

   private String t_WalletTxn_ReceiverName;

   private String t_WalletTxn_ReceiverID;

   private String t_WalletTxn_ReceiverPID;
   
   private String t_WalletTxn_Mobile;

   private String t_WalletTxn_TxnCat;

   private String t_WalletTxn_TxnCurrencyType;

   private Date t_WalletTxn_TxnDate;
	
   private String t_WalletTxn_ProdName;

   private Integer t_WalletTxn_PayDays;
	
   private BigDecimal t_WalletTxn_CreditPayCurrentNum;

   private BigDecimal t_WalletTxn_TotTxnAmount;
   
   private BigDecimal t_WalletTxn_TopupAmt;

   private BigDecimal t_WalletTxn_CryptoTxnAmt;

   private BigDecimal t_WalletTxn_DebitTxnAmt;

   private BigDecimal t_WalletTxn_CreditTxnAmt;
   
   private BigDecimal t_WalletTxn_CreditBalanceNum;

   private BigDecimal t_WalletTxn_CreditTxnAmtInit;

   private BigDecimal t_WalletTxn_TotalPayAmt;

   private BigDecimal t_WalletTxn_TotallvorchourAmt;
	
   private Integer t_WalletTxn_TotalInterestDays;

   private Integer t_WalletTxn_TxnCounts;
   
   private BigDecimal t_WalletTxn_Interest;

   private String t_WalletTxn_PaymentAccattr;
	
   private String t_WalletTxn_RefundAccNo;

   private BigDecimal t_WalletTxn_TotBalance;

    private BigDecimal t_WalletTxn_BalancePrepayNum;

    private Date t_WalletTxn_OverdueRepaymentDate;

    private String t_WalletTxn_PayClear;

    private String t_WalletTxn_Overdue;

    private Integer t_WalletTxn_OverdueDays;

    private BigDecimal t_WalletTxn_RefundAmt;

    private BigDecimal t_WalletTxn_FinancialInterest;

    private BigDecimal t_WalletTxn_ServiceFee;

    private BigDecimal t_WalletTxn_Poundage;

    private BigDecimal t_WalletTxn_TierPoundage;

    private BigDecimal t_WalletTxn_discountamt;

    private BigDecimal t_WalletTxn_ClearanceAmt;

    private BigDecimal t_WalletTxn_InterestMargin;

    private String t_WalletTxn_PayerBankAcc;

    private String t_WalletTxn_ReceiverBankAcc;

    private Date t_WalletTxn_TxnTimeout;

    private String t_WalletTxn_Paystatus;

    private String t_WalletTxn_SMS;

    private String t_WalletTxn_SMSRec;

    private String t_WalletTxn_type;

    private String t_WalletTxn_Voucher;

    private String t_WalletTxn_Txt2;

    private String t_WalletTxn_Txt3;

    private String t_WalletTxn_Txt4;

    private String t_WalletTxn_Txt5;


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

    public String getT_WalletTxn_ID() {
        return t_WalletTxn_ID;
    }

    public void setT_WalletTxn_ID(String t_WalletTxn_ID) {
        this.t_WalletTxn_ID = t_WalletTxn_ID;
    }

    public String getT_WalletTxn_QRcode() {
        return t_WalletTxn_QRcode;
    }

    public void setT_WalletTxn_QRcode(String t_WalletTxn_QRcode) {
        this.t_WalletTxn_QRcode = t_WalletTxn_QRcode;
    }

    public String getT_WalletTxn_Num() {
        return t_WalletTxn_Num;
    }

    public void setT_WalletTxn_Num(String t_WalletTxn_Num) {
        this.t_WalletTxn_Num = t_WalletTxn_Num;
    }

    public String getT_WalletTxn_Vendor() {
        return t_WalletTxn_Vendor;
    }

    public void setT_WalletTxn_Vendor(String t_WalletTxn_Vendor) {
        this.t_WalletTxn_Vendor = t_WalletTxn_Vendor;
    }

    public String getT_WalletTxn_ClearNum() {
        return t_WalletTxn_ClearNum;
    }

    public void setT_WalletTxn_ClearNum(String t_WalletTxn_ClearNum) {
        this.t_WalletTxn_ClearNum = t_WalletTxn_ClearNum;
    }

    public String getT_WalletTxn_ClearOrg() {
        return t_WalletTxn_ClearOrg;
    }

    public void setT_WalletTxn_ClearOrg(String t_WalletTxn_ClearOrg) {
        this.t_WalletTxn_ClearOrg = t_WalletTxn_ClearOrg;
    }

    public String getT_WalletTxn_PayerName() {
        return t_WalletTxn_PayerName;
    }

    public void setT_WalletTxn_PayerName(String t_WalletTxn_PayerName) {
        this.t_WalletTxn_PayerName = t_WalletTxn_PayerName;
    }

    public String getT_WalletTxn_PayerID() {
        return t_WalletTxn_PayerID;
    }

    public void setT_WalletTxn_PayerID(String t_WalletTxn_PayerID) {
        this.t_WalletTxn_PayerID = t_WalletTxn_PayerID;
    }

    public String getT_WalletTxn_PayerPID() {
        return t_WalletTxn_PayerPID;
    }

    public void setT_WalletTxn_PayerPID(String t_WalletTxn_PayerPID) {
        this.t_WalletTxn_PayerPID = t_WalletTxn_PayerPID;
    }

    public String getT_WalletTxn_ReceiverName() {
        return t_WalletTxn_ReceiverName;
    }

    public void setT_WalletTxn_ReceiverName(String t_WalletTxn_ReceiverName) {
        this.t_WalletTxn_ReceiverName = t_WalletTxn_ReceiverName;
    }

    public String getT_WalletTxn_ReceiverID() {
        return t_WalletTxn_ReceiverID;
    }

    public void setT_WalletTxn_ReceiverID(String t_WalletTxn_ReceiverID) {
        this.t_WalletTxn_ReceiverID = t_WalletTxn_ReceiverID;
    }

    public String getT_WalletTxn_ReceiverPID() {
        return t_WalletTxn_ReceiverPID;
    }

    public void setT_WalletTxn_ReceiverPID(String t_WalletTxn_ReceiverPID) {
        this.t_WalletTxn_ReceiverPID = t_WalletTxn_ReceiverPID;
    }

    public String getT_WalletTxn_Mobile() {
        return t_WalletTxn_Mobile;
    }

    public void setT_WalletTxn_Mobile(String t_WalletTxn_Mobile) {
        this.t_WalletTxn_Mobile = t_WalletTxn_Mobile;
    }

    public String getT_WalletTxn_TxnCat() {
        return t_WalletTxn_TxnCat;
    }

    public void setT_WalletTxn_TxnCat(String t_WalletTxn_TxnCat) {
        this.t_WalletTxn_TxnCat = t_WalletTxn_TxnCat;
    }

    public String getT_WalletTxn_TxnCurrencyType() {
        return t_WalletTxn_TxnCurrencyType;
    }

    public void setT_WalletTxn_TxnCurrencyType(String t_WalletTxn_TxnCurrencyType) {
        this.t_WalletTxn_TxnCurrencyType = t_WalletTxn_TxnCurrencyType;
    }

    public Date getT_WalletTxn_TxnDate() {
        return t_WalletTxn_TxnDate;
    }

    public void setT_WalletTxn_TxnDate(Date t_WalletTxn_TxnDate) {
        this.t_WalletTxn_TxnDate = t_WalletTxn_TxnDate;
    }

    public String getT_WalletTxn_ProdName() {
        return t_WalletTxn_ProdName;
    }

    public void setT_WalletTxn_ProdName(String t_WalletTxn_ProdName) {
        this.t_WalletTxn_ProdName = t_WalletTxn_ProdName;
    }

    public Integer getT_WalletTxn_PayDays() {
        return t_WalletTxn_PayDays;
    }

    public void setT_WalletTxn_PayDays(Integer t_WalletTxn_PayDays) {
        this.t_WalletTxn_PayDays = t_WalletTxn_PayDays;
    }

    public BigDecimal getT_WalletTxn_CreditPayCurrentNum() {
        return t_WalletTxn_CreditPayCurrentNum;
    }

    public void setT_WalletTxn_CreditPayCurrentNum(BigDecimal t_WalletTxn_CreditPayCurrentNum) {
        this.t_WalletTxn_CreditPayCurrentNum = t_WalletTxn_CreditPayCurrentNum;
    }

    public BigDecimal getT_WalletTxn_TotTxnAmount() {
        return t_WalletTxn_TotTxnAmount;
    }

    public void setT_WalletTxn_TotTxnAmount(BigDecimal t_WalletTxn_TotTxnAmount) {
        this.t_WalletTxn_TotTxnAmount = t_WalletTxn_TotTxnAmount;
    }

    public BigDecimal getT_WalletTxn_TopupAmt() {
        return t_WalletTxn_TopupAmt;
    }

    public void setT_WalletTxn_TopupAmt(BigDecimal t_WalletTxn_TopupAmt) {
        this.t_WalletTxn_TopupAmt = t_WalletTxn_TopupAmt;
    }

    public BigDecimal getT_WalletTxn_CryptoTxnAmt() {
        return t_WalletTxn_CryptoTxnAmt;
    }

    public void setT_WalletTxn_CryptoTxnAmt(BigDecimal t_WalletTxn_CryptoTxnAmt) {
        this.t_WalletTxn_CryptoTxnAmt = t_WalletTxn_CryptoTxnAmt;
    }

    public BigDecimal getT_WalletTxn_DebitTxnAmt() {
        return t_WalletTxn_DebitTxnAmt;
    }

    public void setT_WalletTxn_DebitTxnAmt(BigDecimal t_WalletTxn_DebitTxnAmt) {
        this.t_WalletTxn_DebitTxnAmt = t_WalletTxn_DebitTxnAmt;
    }

    public BigDecimal getT_WalletTxn_CreditTxnAmt() {
        return t_WalletTxn_CreditTxnAmt;
    }

    public void setT_WalletTxn_CreditTxnAmt(BigDecimal t_WalletTxn_CreditTxnAmt) {
        this.t_WalletTxn_CreditTxnAmt = t_WalletTxn_CreditTxnAmt;
    }

    public BigDecimal getT_WalletTxn_CreditBalanceNum() {
        return t_WalletTxn_CreditBalanceNum;
    }

    public void setT_WalletTxn_CreditBalanceNum(BigDecimal t_WalletTxn_CreditBalanceNum) {
        this.t_WalletTxn_CreditBalanceNum = t_WalletTxn_CreditBalanceNum;
    }

    public BigDecimal getT_WalletTxn_CreditTxnAmtInit() {
        return t_WalletTxn_CreditTxnAmtInit;
    }

    public void setT_WalletTxn_CreditTxnAmtInit(BigDecimal t_WalletTxn_CreditTxnAmtInit) {
        this.t_WalletTxn_CreditTxnAmtInit = t_WalletTxn_CreditTxnAmtInit;
    }

    public BigDecimal gett_WalletTxn_TotalPayAmt() {
        return t_WalletTxn_TotalPayAmt;
    }

    public void sett_WalletTxn_TotalPayAmt(BigDecimal t_WalletTxn_TotalPayAmt) {
        this.t_WalletTxn_TotalPayAmt = t_WalletTxn_TotalPayAmt;
    }

    public BigDecimal getT_WalletTxn_TotallvorchourAmt() {
        return t_WalletTxn_TotallvorchourAmt;
    }

    public void setT_WalletTxn_TotallvorchourAmt(BigDecimal t_WalletTxn_TotallvorchourAmt) {
        this.t_WalletTxn_TotallvorchourAmt = t_WalletTxn_TotallvorchourAmt;
    }

    public Integer getT_WalletTxn_TotalInterestDays() {
        return t_WalletTxn_TotalInterestDays;
    }

    public void setT_WalletTxn_TotalInterestDays(Integer t_WalletTxn_TotalInterestDays) {
        this.t_WalletTxn_TotalInterestDays = t_WalletTxn_TotalInterestDays;
    }

    public Integer getT_WalletTxn_TxnCounts() {
        return t_WalletTxn_TxnCounts;
    }

    public void setT_WalletTxn_TxnCounts(Integer t_WalletTxn_TxnCounts) {
        this.t_WalletTxn_TxnCounts = t_WalletTxn_TxnCounts;
    }

    public BigDecimal getT_WalletTxn_Interest() {
        return t_WalletTxn_Interest;
    }

    public void setT_WalletTxn_Interest(BigDecimal t_WalletTxn_Interest) {
        this.t_WalletTxn_Interest = t_WalletTxn_Interest;
    }

    public String gett_WalletTxn_PaymentAccattr() {
        return t_WalletTxn_PaymentAccattr;
    }

    public void sett_WalletTxn_PaymentAccattr(String t_WalletTxn_PaymentAccattr) {
        this.t_WalletTxn_PaymentAccattr = t_WalletTxn_PaymentAccattr;
    }

    public String gett_WalletTxn_RefundAccNo() {
        return t_WalletTxn_RefundAccNo;
    }

    public void sett_WalletTxn_RefundAccNo(String t_WalletTxn_RefundAccNo) {
        this.t_WalletTxn_RefundAccNo = t_WalletTxn_RefundAccNo;
    }

    public BigDecimal gett_WalletTxn_TotBalance() {
        return t_WalletTxn_TotBalance;
    }

    public void sett_WalletTxn_TotBalance(BigDecimal t_WalletTxn_TotBalance) {
        this.t_WalletTxn_TotBalance = t_WalletTxn_TotBalance;
    }

    public BigDecimal gett_WalletTxn_BalancePrepayNum() {
        return t_WalletTxn_BalancePrepayNum;
    }

    public void sett_WalletTxn_BalancePrepayNum(BigDecimal t_WalletTxn_BalancePrepayNum) {
        this.t_WalletTxn_BalancePrepayNum = t_WalletTxn_BalancePrepayNum;
    }

    public Date getT_WalletTxn_OverdueRepaymentDate() {
        return t_WalletTxn_OverdueRepaymentDate;
    }

    public void setT_WalletTxn_OverdueRepaymentDate(Date t_WalletTxn_OverdueRepaymentDate) {
        this.t_WalletTxn_OverdueRepaymentDate = t_WalletTxn_OverdueRepaymentDate;
    }

    public String getT_WalletTxn_PayClear() {
        return t_WalletTxn_PayClear;
    }

    public void setT_WalletTxn_PayClear(String t_WalletTxn_PayClear) {
        this.t_WalletTxn_PayClear = t_WalletTxn_PayClear;
    }

    public String getT_WalletTxn_Overdue() {
        return t_WalletTxn_Overdue;
    }

    public void setT_WalletTxn_Overdue(String t_WalletTxn_Overdue) {
        this.t_WalletTxn_Overdue = t_WalletTxn_Overdue;
    }

    public Integer getT_WalletTxn_OverdueDays() {
        return t_WalletTxn_OverdueDays;
    }

    public void setT_WalletTxn_OverdueDays(Integer t_WalletTxn_OverdueDays) {
        this.t_WalletTxn_OverdueDays = t_WalletTxn_OverdueDays;
    }

    public BigDecimal gett_WalletTxn_RefundAmt() {
        return t_WalletTxn_RefundAmt;
    }

    public void sett_WalletTxn_RefundAmt(BigDecimal t_WalletTxn_RefundAmt) {
        this.t_WalletTxn_RefundAmt = t_WalletTxn_RefundAmt;
    }

    public BigDecimal getT_WalletTxn_FinancialInterest() {
        return t_WalletTxn_FinancialInterest;
    }

    public void setT_WalletTxn_FinancialInterest(BigDecimal t_WalletTxn_FinancialInterest) {
        this.t_WalletTxn_FinancialInterest = t_WalletTxn_FinancialInterest;
    }

    public BigDecimal getT_WalletTxn_ServiceFee() {
        return t_WalletTxn_ServiceFee;
    }

    public void setT_WalletTxn_ServiceFee(BigDecimal t_WalletTxn_ServiceFee) {
        this.t_WalletTxn_ServiceFee = t_WalletTxn_ServiceFee;
    }

    public BigDecimal getT_WalletTxn_Poundage() {
        return t_WalletTxn_Poundage;
    }

    public void setT_WalletTxn_Poundage(BigDecimal t_WalletTxn_Poundage) {
        this.t_WalletTxn_Poundage = t_WalletTxn_Poundage;
    }

    public BigDecimal getT_WalletTxn_TierPoundage() {
        return t_WalletTxn_TierPoundage;
    }

    public void setT_WalletTxn_TierPoundage(BigDecimal t_WalletTxn_TierPoundage) {
        this.t_WalletTxn_TierPoundage = t_WalletTxn_TierPoundage;
    }

    public BigDecimal getT_WalletTxn_discountamt() {
        return t_WalletTxn_discountamt;
    }

    public void setT_WalletTxn_discountamt(BigDecimal t_WalletTxn_discountamt) {
        this.t_WalletTxn_discountamt = t_WalletTxn_discountamt;
    }

    public BigDecimal gett_WalletTxn_ClearanceAmt() {
        return t_WalletTxn_ClearanceAmt;
    }

    public void sett_WalletTxn_ClearanceAmt(BigDecimal t_WalletTxn_ClearanceAmt) {
        this.t_WalletTxn_ClearanceAmt = t_WalletTxn_ClearanceAmt;
    }

    public BigDecimal getT_WalletTxn_InterestMargin() {
        return t_WalletTxn_InterestMargin;
    }

    public void setT_WalletTxn_InterestMargin(BigDecimal t_WalletTxn_InterestMargin) {
        this.t_WalletTxn_InterestMargin = t_WalletTxn_InterestMargin;
    }

    public String gett_WalletTxn_PayerBankAcc() {
        return t_WalletTxn_PayerBankAcc;
    }

    public void sett_WalletTxn_PayerBankAcc(String t_WalletTxn_PayerBankAcc) {
        this.t_WalletTxn_PayerBankAcc = t_WalletTxn_PayerBankAcc;
    }

    public String gett_WalletTxn_ReceiverBankAcc() {
        return t_WalletTxn_ReceiverBankAcc;
    }

    public void sett_WalletTxn_ReceiverBankAcc(String t_WalletTxn_ReceiverBankAcc) {
        this.t_WalletTxn_ReceiverBankAcc = t_WalletTxn_ReceiverBankAcc;
    }

    public Date gett_WalletTxn_TxnTimeout() {
        return t_WalletTxn_TxnTimeout;
    }

    public void sett_WalletTxn_TxnTimeout(Date t_WalletTxn_TxnTimeout) {
        this.t_WalletTxn_TxnTimeout = t_WalletTxn_TxnTimeout;
    }

    public String getT_WalletTxn_Paystatus() {
        return t_WalletTxn_Paystatus;
    }

    public void setT_WalletTxn_Paystatus(String t_WalletTxn_Paystatus) {
        this.t_WalletTxn_Paystatus = t_WalletTxn_Paystatus;
    }

    public String getT_WalletTxn_SMS() {
        return t_WalletTxn_SMS;
    }

    public void setT_WalletTxn_SMS(String t_WalletTxn_SMS) {
        this.t_WalletTxn_SMS = t_WalletTxn_SMS;
    }

    public String getT_WalletTxn_SMSRec() {
        return t_WalletTxn_SMSRec;
    }

    public void setT_WalletTxn_SMSRec(String t_WalletTxn_SMSRec) {
        this.t_WalletTxn_SMSRec = t_WalletTxn_SMSRec;
    }

    public String getT_WalletTxn_type() {
        return t_WalletTxn_type;
    }

    public void setT_WalletTxn_type(String t_WalletTxn_type) {
        this.t_WalletTxn_type = t_WalletTxn_type;
    }

    public String gett_WalletTxn_Voucher() {
        return t_WalletTxn_Voucher;
    }

    public void sett_WalletTxn_Voucher(String t_WalletTxn_Voucher) {
        this.t_WalletTxn_Voucher = t_WalletTxn_Voucher;
    }

    public String getT_WalletTxn_Txt2() {
        return t_WalletTxn_Txt2;
    }

    public void setT_WalletTxn_Txt2(String t_WalletTxn_Txt2) {
        this.t_WalletTxn_Txt2 = t_WalletTxn_Txt2;
    }

    public String getT_WalletTxn_Txt3() {
        return t_WalletTxn_Txt3;
    }

    public void setT_WalletTxn_Txt3(String t_WalletTxn_Txt3) {
        this.t_WalletTxn_Txt3 = t_WalletTxn_Txt3;
    }

    public String getT_WalletTxn_Txt4() {
        return t_WalletTxn_Txt4;
    }

    public void setT_WalletTxn_Txt4(String t_WalletTxn_Txt4) {
        this.t_WalletTxn_Txt4 = t_WalletTxn_Txt4;
    }

    public String getT_WalletTxn_Txt5() {
        return t_WalletTxn_Txt5;
    }

    public void setT_WalletTxn_Txt5(String t_WalletTxn_Txt5) {
        this.t_WalletTxn_Txt5 = t_WalletTxn_Txt5;
    }

}