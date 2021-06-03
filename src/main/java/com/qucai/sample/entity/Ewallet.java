package com.qucai.sample.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class Ewallet implements Serializable {
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

   private String t_personalewallet_ID;

   private String t_personalewallet_ApplierID;

   private String t_personalewallet_ApplierPID;

   private String t_personalewallet_ApplierName;

   private String t_personalewallet_Passport;

   private String t_personalewallet_ScanCode;

   private String t_personalewallet_QRcode;

   private String t_personalewallet_alipayAcc;

   private String t_personalewallet_wechatpayAcc;

   private String t_personalewallet_unionpayAcc;

   private String t_personalewallet_CryptoC;

   private String t_personalewallet_Voucher;

   private String t_personalewallet_VoucherDigi;

   private String t_personalewallet_Creditcard;

   private String t_personalewallet_Debitcard;

   private Integer t_personalewallet_ClearNum;

   private String t_personalewallet_ClearOrg;

   private String t_personalewallet_PayCat;

   private Date t_personalewallet_PayDate;

   private String t_personalewallet_ProdName;

   private Integer t_personalewallet_PayDays;

   private String t_personalewallet_Reciept;

   private BigDecimal t_personalewallet_TotCNYBalance;

   private BigDecimal t_personalewallet_TotFXBalance;

   private BigDecimal t_personalewallet_TotCryptoBalance;

   private BigDecimal t_personalewallet_TotAssetES;

   private BigDecimal t_personalewallet_DebitPayAmt;

   private BigDecimal t_personalewallet_ApplyPayAmount;

   private BigDecimal t_personalewallet_CreditPrepayBalanceNum;

   private BigDecimal t_personalewallet_CreditPayAmt;

   private BigDecimal t_personalewallet_CreditPayAmtInit;

   private BigDecimal t_personalewallet_TotalPrepayAmt;

   private Integer t_personalewallet_TotalInterestDays;

   private Integer t_personalewallet_PayCounts;

    private BigDecimal t_personalewallet_Interest;

    private BigDecimal t_personalewallet_TopupLimit;

    private BigDecimal t_personalewallet_Worth;

    private BigDecimal t_personalewallet_DiscountRate;

    private BigDecimal t_personalewallet_BalanceCreditNum;

    private Date t_personalewallet_OverdueRepaymentDate;

    private String t_personalewallet_PrepayClear;

    private String t_personalewallet_Overdue;

    private BigDecimal t_personalewallet_OverdueTotalAmount;

    private BigDecimal t_personalewallet_FinancialInterest;

    private BigDecimal t_personalewallet_ServiceFee;

    private BigDecimal t_personalewallet_Poundage;

    private BigDecimal t_personalewallet_TierPoundage;

    private BigDecimal t_personalewallet_InterestMargin;

    private String t_personalewallet_BankAccName;

    private String t_personalewallet_BankAcc;

    private Date t_personalewallet_SysUpdateDate;

    private Integer t_personalewallet_OverdueDays;

    private String t_personalewallet_Paystatus;

    private String t_personalewallet_SMS;

    private String t_personalewallet_SMSRec;

    private BigDecimal t_personalewallet_bkp;

    private BigDecimal t_personalewallet_bkp1;

    private String t_personalewallet_treasuryID;

    private String t_personalewallet_treasuryID1;

    private String t_personalewallet_treasuryID2;

    private String t_personalewallet_treasuryID3;

    private String t_personalewallet_treasuryID4;

    private String t_personalewallet_treasuryID5;

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

    public String getT_personalewallet_ID() {
        return t_personalewallet_ID;
    }

    public void setT_personalewallet_ID(String t_personalewallet_ID) {
        this.t_personalewallet_ID = t_personalewallet_ID;
    }

    public String getT_personalewallet_ApplierID() {
        return t_personalewallet_ApplierID;
    }

    public void setT_personalewallet_ApplierID(String t_personalewallet_ApplierID) {
        this.t_personalewallet_ApplierID = t_personalewallet_ApplierID;
    }

    public String getT_personalewallet_ApplierPID() {
        return t_personalewallet_ApplierPID;
    }

    public void setT_personalewallet_ApplierPID(String t_personalewallet_ApplierPID) {
        this.t_personalewallet_ApplierPID = t_personalewallet_ApplierPID;
    }

    public String getT_personalewallet_ApplierName() {
        return t_personalewallet_ApplierName;
    }

    public void setT_personalewallet_ApplierName(String t_personalewallet_ApplierName) {
        this.t_personalewallet_ApplierName = t_personalewallet_ApplierName;
    }

    public String getT_personalewallet_Passport() {
        return t_personalewallet_Passport;
    }

    public void setT_personalewallet_Passport(String t_personalewallet_Passport) {
        this.t_personalewallet_Passport = t_personalewallet_Passport;
    }

    public String getT_personalewallet_ScanCode() {
        return t_personalewallet_ScanCode;
    }

    public void setT_personalewallet_ScanCode(String t_personalewallet_ScanCode) {
        this.t_personalewallet_ScanCode = t_personalewallet_ScanCode;
    }

    public String getT_personalewallet_QRcode() {
        return t_personalewallet_QRcode;
    }

    public void setT_personalewallet_QRcode(String t_personalewallet_QRcode) {
        this.t_personalewallet_QRcode = t_personalewallet_QRcode;
    }

    public String getT_personalewallet_alipayAcc() {
        return t_personalewallet_alipayAcc;
    }

    public void setT_personalewallet_alipayAcc(String t_personalewallet_alipayAcc) {
        this.t_personalewallet_alipayAcc = t_personalewallet_alipayAcc;
    }

    public String getT_personalewallet_wechatpayAcc() {
        return t_personalewallet_wechatpayAcc;
    }

    public void setT_personalewallet_wechatpayAcc(String t_personalewallet_wechatpayAcc) {
        this.t_personalewallet_wechatpayAcc = t_personalewallet_wechatpayAcc;
    }

    public String getT_personalewallet_unionpayAcc() {
        return t_personalewallet_unionpayAcc;
    }

    public void setT_personalewallet_unionpayAcc(String t_personalewallet_unionpayAcc) {
        this.t_personalewallet_unionpayAcc = t_personalewallet_unionpayAcc;
    }

    public String getT_personalewallet_CryptoC() {
        return t_personalewallet_CryptoC;
    }

    public void setT_personalewallet_CryptoC(String t_personalewallet_CryptoC) {
        this.t_personalewallet_CryptoC = t_personalewallet_CryptoC;
    }

    public String getT_personalewallet_Voucher() {
        return t_personalewallet_Voucher;
    }

    public void setT_personalewallet_Voucher(String t_personalewallet_Voucher) {
        this.t_personalewallet_Voucher = t_personalewallet_Voucher;
    }

    public String getT_personalewallet_VoucherDigi() {
        return t_personalewallet_VoucherDigi;
    }

    public void setT_personalewallet_VoucherDigi(String t_personalewallet_VoucherDigi) {
        this.t_personalewallet_VoucherDigi = t_personalewallet_VoucherDigi;
    }

    public String getT_personalewallet_Creditcard() {
        return t_personalewallet_Creditcard;
    }

    public void setT_personalewallet_Creditcard(String t_personalewallet_Creditcard) {
        this.t_personalewallet_Creditcard = t_personalewallet_Creditcard;
    }

    public String getT_personalewallet_Debitcard() {
        return t_personalewallet_Debitcard;
    }

    public void setT_personalewallet_Debitcard(String t_personalewallet_Debitcard) {
        this.t_personalewallet_Debitcard = t_personalewallet_Debitcard;
    }

    public Integer getT_personalewallet_ClearNum() {
        return t_personalewallet_ClearNum;
    }

    public void setT_personalewallet_ClearNum(Integer t_personalewallet_ClearNum) {
        this.t_personalewallet_ClearNum = t_personalewallet_ClearNum;
    }

    public String getT_personalewallet_ClearOrg() {
        return t_personalewallet_ClearOrg;
    }

    public void setT_personalewallet_ClearOrg(String t_personalewallet_ClearOrg) {
        this.t_personalewallet_ClearOrg = t_personalewallet_ClearOrg;
    }

    public String getT_personalewallet_PayCat() {
        return t_personalewallet_PayCat;
    }

    public void setT_personalewallet_PayCat(String t_personalewallet_PayCat) {
        this.t_personalewallet_PayCat = t_personalewallet_PayCat;
    }

    public Date getT_personalewallet_PayDate() {
        return t_personalewallet_PayDate;
    }

    public void setT_personalewallet_PayDate(Date t_personalewallet_PayDate) {
        this.t_personalewallet_PayDate = t_personalewallet_PayDate;
    }

    public String getT_personalewallet_ProdName() {
        return t_personalewallet_ProdName;
    }

    public void setT_personalewallet_ProdName(String t_personalewallet_ProdName) {
        this.t_personalewallet_ProdName = t_personalewallet_ProdName;
    }

    public Integer getT_personalewallet_PayDays() {
        return t_personalewallet_PayDays;
    }

    public void setT_personalewallet_PayDays(Integer t_personalewallet_PayDays) {
        this.t_personalewallet_PayDays = t_personalewallet_PayDays;
    }

    public String getT_personalewallet_Reciept() {
        return t_personalewallet_Reciept;
    }

    public void setT_personalewallet_Reciept(String t_personalewallet_Reciept) {
        this.t_personalewallet_Reciept = t_personalewallet_Reciept;
    }

    public BigDecimal getT_personalewallet_TotCNYBalance() {
        return t_personalewallet_TotCNYBalance;
    }

    public void setT_personalewallet_TotCNYBalance(BigDecimal t_personalewallet_TotCNYBalance) {
        this.t_personalewallet_TotCNYBalance = t_personalewallet_TotCNYBalance;
    }

    public BigDecimal getT_personalewallet_TotFXBalance() {
        return t_personalewallet_TotFXBalance;
    }

    public void setT_personalewallet_TotFXBalance(BigDecimal t_personalewallet_TotFXBalance) {
        this.t_personalewallet_TotFXBalance = t_personalewallet_TotFXBalance;
    }

    public BigDecimal getT_personalewallet_TotCryptoBalance() {
        return t_personalewallet_TotCryptoBalance;
    }

    public void setT_personalewallet_TotCryptoBalance(BigDecimal t_personalewallet_TotCryptoBalance) {
        this.t_personalewallet_TotCryptoBalance = t_personalewallet_TotCryptoBalance;
    }

    public BigDecimal getT_personalewallet_TotAssetES() {
        return t_personalewallet_TotAssetES;
    }

    public void setT_personalewallet_TotAssetES(BigDecimal t_personalewallet_TotAssetES) {
        this.t_personalewallet_TotAssetES = t_personalewallet_TotAssetES;
    }

    public BigDecimal getT_personalewallet_DebitPayAmt() {
        return t_personalewallet_DebitPayAmt;
    }

    public void setT_personalewallet_DebitPayAmt(BigDecimal t_personalewallet_DebitPayAmt) {
        this.t_personalewallet_DebitPayAmt = t_personalewallet_DebitPayAmt;
    }

    public BigDecimal getT_personalewallet_ApplyPayAmount() {
        return t_personalewallet_ApplyPayAmount;
    }

    public void setT_personalewallet_ApplyPayAmount(BigDecimal t_personalewallet_ApplyPayAmount) {
        this.t_personalewallet_ApplyPayAmount = t_personalewallet_ApplyPayAmount;
    }

    public BigDecimal getT_personalewallet_CreditPrepayBalanceNum() {
        return t_personalewallet_CreditPrepayBalanceNum;
    }

    public void setT_personalewallet_CreditPrepayBalanceNum(BigDecimal t_personalewallet_CreditPrepayBalanceNum) {
        this.t_personalewallet_CreditPrepayBalanceNum = t_personalewallet_CreditPrepayBalanceNum;
    }

    public BigDecimal getT_personalewallet_CreditPayAmt() {
        return t_personalewallet_CreditPayAmt;
    }

    public void setT_personalewallet_CreditPayAmt(BigDecimal t_personalewallet_CreditPayAmt) {
        this.t_personalewallet_CreditPayAmt = t_personalewallet_CreditPayAmt;
    }

    public BigDecimal getT_personalewallet_CreditPayAmtInit() {
        return t_personalewallet_CreditPayAmtInit;
    }

    public void setT_personalewallet_CreditPayAmtInit(BigDecimal t_personalewallet_CreditPayAmtInit) {
        this.t_personalewallet_CreditPayAmtInit = t_personalewallet_CreditPayAmtInit;
    }

    public BigDecimal getT_personalewallet_TotalPrepayAmt() {
        return t_personalewallet_TotalPrepayAmt;
    }

    public void setT_personalewallet_TotalPrepayAmt(BigDecimal t_personalewallet_TotalPrepayAmt) {
        this.t_personalewallet_TotalPrepayAmt = t_personalewallet_TotalPrepayAmt;
    }

    public Integer getT_personalewallet_TotalInterestDays() {
        return t_personalewallet_TotalInterestDays;
    }

    public void setT_personalewallet_TotalInterestDays(Integer t_personalewallet_TotalInterestDays) {
        this.t_personalewallet_TotalInterestDays = t_personalewallet_TotalInterestDays;
    }

    public Integer getT_personalewallet_PayCounts() {
        return t_personalewallet_PayCounts;
    }

    public void setT_personalewallet_PayCounts(Integer t_personalewallet_PayCounts) {
        this.t_personalewallet_PayCounts = t_personalewallet_PayCounts;
    }

    public BigDecimal getT_personalewallet_Interest() {
        return t_personalewallet_Interest;
    }

    public void setT_personalewallet_Interest(BigDecimal t_personalewallet_Interest) {
        this.t_personalewallet_Interest = t_personalewallet_Interest;
    }

    public BigDecimal getT_personalewallet_TopupLimit() {
        return t_personalewallet_TopupLimit;
    }

    public void setT_personalewallet_TopupLimit(BigDecimal t_personalewallet_TopupLimit) {
        this.t_personalewallet_TopupLimit = t_personalewallet_TopupLimit;
    }

    public BigDecimal gett_personalewallet_Worth() {
        return t_personalewallet_Worth;
    }

    public void sett_personalewallet_Worth(BigDecimal t_personalewallet_Worth) {
        this.t_personalewallet_Worth = t_personalewallet_Worth;
    }

    public BigDecimal gett_personalewallet_DiscountRate() {
        return t_personalewallet_DiscountRate;
    }

    public void sett_personalewallet_DiscountRate(BigDecimal t_personalewallet_DiscountRate) {
        this.t_personalewallet_DiscountRate = t_personalewallet_DiscountRate;
    }

    public BigDecimal getT_personalewallet_BalanceCreditNum() {
        return t_personalewallet_BalanceCreditNum;
    }

    public void setT_personalewallet_BalanceCreditNum(BigDecimal t_personalewallet_BalanceCreditNum) {
        this.t_personalewallet_BalanceCreditNum = t_personalewallet_BalanceCreditNum;
    }

    public Date getT_personalewallet_OverdueRepaymentDate() {
        return t_personalewallet_OverdueRepaymentDate;
    }

    public void setT_personalewallet_OverdueRepaymentDate(Date t_personalewallet_OverdueRepaymentDate) {
        this.t_personalewallet_OverdueRepaymentDate = t_personalewallet_OverdueRepaymentDate;
    }

    public String getT_personalewallet_PrepayClear() {
        return t_personalewallet_PrepayClear;
    }

    public void setT_personalewallet_PrepayClear(String t_personalewallet_PrepayClear) {
        this.t_personalewallet_PrepayClear = t_personalewallet_PrepayClear;
    }

    public String getT_personalewallet_Overdue() {
        return t_personalewallet_Overdue;
    }

    public void setT_personalewallet_Overdue(String t_personalewallet_Overdue) {
        this.t_personalewallet_Overdue = t_personalewallet_Overdue;
    }

    public BigDecimal getT_personalewallet_OverdueTotalAmount() {
        return t_personalewallet_OverdueTotalAmount;
    }

    public void setT_personalewallet_OverdueTotalAmount(BigDecimal t_personalewallet_OverdueTotalAmount) {
        this.t_personalewallet_OverdueTotalAmount = t_personalewallet_OverdueTotalAmount;
    }

    public BigDecimal getT_personalewallet_FinancialInterest() {
        return t_personalewallet_FinancialInterest;
    }

    public void setT_personalewallet_FinancialInterest(BigDecimal t_personalewallet_FinancialInterest) {
        this.t_personalewallet_FinancialInterest = t_personalewallet_FinancialInterest;
    }

    public BigDecimal getT_personalewallet_ServiceFee() {
        return t_personalewallet_ServiceFee;
    }

    public void setT_personalewallet_ServiceFee(BigDecimal t_personalewallet_ServiceFee) {
        this.t_personalewallet_ServiceFee = t_personalewallet_ServiceFee;
    }

    public BigDecimal getT_personalewallet_Poundage() {
        return t_personalewallet_Poundage;
    }

    public void setT_personalewallet_Poundage(BigDecimal t_personalewallet_Poundage) {
        this.t_personalewallet_Poundage = t_personalewallet_Poundage;
    }

    public BigDecimal getT_personalewallet_TierPoundage() {
        return t_personalewallet_TierPoundage;
    }

    public void setT_personalewallet_TierPoundage(BigDecimal t_personalewallet_TierPoundage) {
        this.t_personalewallet_TierPoundage = t_personalewallet_TierPoundage;
    }

    public BigDecimal getT_personalewallet_InterestMargin() {
        return t_personalewallet_InterestMargin;
    }

    public void setT_personalewallet_InterestMargin(BigDecimal t_personalewallet_InterestMargin) {
        this.t_personalewallet_InterestMargin = t_personalewallet_InterestMargin;
    }

    public String getT_personalewallet_BankAccName() {
        return t_personalewallet_BankAccName;
    }

    public void setT_personalewallet_BankAccName(String t_personalewallet_BankAccName) {
        this.t_personalewallet_BankAccName = t_personalewallet_BankAccName;
    }

    public String getT_personalewallet_BankAcc() {
        return t_personalewallet_BankAcc;
    }

    public void setT_personalewallet_BankAcc(String t_personalewallet_BankAcc) {
        this.t_personalewallet_BankAcc = t_personalewallet_BankAcc;
    }

    public Date getT_personalewallet_SysUpdateDate() {
        return t_personalewallet_SysUpdateDate;
    }

    public void setT_personalewallet_SysUpdateDate(Date t_personalewallet_SysUpdateDate) {
        this.t_personalewallet_SysUpdateDate = t_personalewallet_SysUpdateDate;
    }

    public Integer getT_personalewallet_OverdueDays() {
        return t_personalewallet_OverdueDays;
    }

    public void setT_personalewallet_OverdueDays(Integer t_personalewallet_OverdueDays) {
        this.t_personalewallet_OverdueDays = t_personalewallet_OverdueDays;
    }

    public String getT_personalewallet_Paystatus() {
        return t_personalewallet_Paystatus;
    }

    public void setT_personalewallet_Paystatus(String t_personalewallet_Paystatus) {
        this.t_personalewallet_Paystatus = t_personalewallet_Paystatus;
    }

    public String getT_personalewallet_SMS() {
        return t_personalewallet_SMS;
    }

    public void setT_personalewallet_SMS(String t_personalewallet_SMS) {
        this.t_personalewallet_SMS = t_personalewallet_SMS;
    }

    public String getT_personalewallet_SMSRec() {
        return t_personalewallet_SMSRec;
    }

    public void setT_personalewallet_SMSRec(String t_personalewallet_SMSRec) {
        this.t_personalewallet_SMSRec = t_personalewallet_SMSRec;
    }

    public BigDecimal getT_personalewallet_bkp() {
        return t_personalewallet_bkp;
    }

    public void setT_personalewallet_bkp(BigDecimal t_personalewallet_bkp) {
        this.t_personalewallet_bkp = t_personalewallet_bkp;
    }

    public BigDecimal getT_personalewallet_bkp1() {
        return t_personalewallet_bkp1;
    }

    public void setT_personalewallet_bkp1(BigDecimal t_personalewallet_bkp1) {
        this.t_personalewallet_bkp1 = t_personalewallet_bkp1;
    }

    public String gett_personalewallet_treasuryID() {
        return t_personalewallet_treasuryID;
    }

    public void sett_personalewallet_treasuryID(String t_personalewallet_treasuryID) {
        this.t_personalewallet_treasuryID = t_personalewallet_treasuryID;
    }

    public String gett_personalewallet_treasuryID1() {
        return t_personalewallet_treasuryID1;
    }

    public void sett_personalewallet_treasuryID1(String t_personalewallet_treasuryID1) {
        this.t_personalewallet_treasuryID1 = t_personalewallet_treasuryID1;
    }

    public String gett_personalewallet_treasuryID2() {
        return t_personalewallet_treasuryID2;
    }

    public void sett_personalewallet_treasuryID2(String t_personalewallet_treasuryID2) {
        this.t_personalewallet_treasuryID2 = t_personalewallet_treasuryID2;
    }

    public String gett_personalewallet_treasuryID3() {
        return t_personalewallet_treasuryID3;
    }

    public void sett_personalewallet_treasuryID3(String t_personalewallet_treasuryID3) {
        this.t_personalewallet_treasuryID3 = t_personalewallet_treasuryID3;
    }

    public String gett_personalewallet_treasuryID4() {
        return t_personalewallet_treasuryID4;
    }

    public void sett_personalewallet_treasuryID4(String t_personalewallet_treasuryID4) {
        this.t_personalewallet_treasuryID4 = t_personalewallet_treasuryID4;
    }

    public String gett_personalewallet_treasuryID5() {
        return t_personalewallet_treasuryID5;
    }

    public void sett_personalewallet_treasuryID5(String t_personalewallet_treasuryID5) {
        this.t_personalewallet_treasuryID5 = t_personalewallet_treasuryID5;
    }

}