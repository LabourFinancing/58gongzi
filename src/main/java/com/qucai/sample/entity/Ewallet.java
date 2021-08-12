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

   private String t_personalewallet_PayRules;

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

   private BigDecimal t_personalewallet_BaselineAdjustment;

   private BigDecimal t_personalewallet_TotDailyAmt;

   private BigDecimal t_personalewallet_DailyInterestRatio;

   private Integer t_personalewallet_DayCntEq0;

   private Integer t_personalewallet_DayCntMorethan0;

    private BigDecimal t_personalewallet_Interest;

    private BigDecimal t_personalewallet_BalaceYesterDay;

    private BigDecimal t_personalewallet_Worth;

    private BigDecimal t_personalewallet_DiscountRate;

    private BigDecimal t_personalewallet_BalanceInterest;

    private Date t_personalewallet_OverdueRepaymentDate;

    private String t_personalewallet_PrepayClear;

    private String t_personalewallet_Overdue;

    private BigDecimal t_personalewallet_TotalPrepayAmt;

    private BigDecimal t_personalewallet_TotalWorthCal;

    private BigDecimal t_personalewallet_ServiceFee;

    private BigDecimal t_personalewallet_Poundage;

    private BigDecimal t_personalewallet_YesterdayWorthCal;

    private BigDecimal t_personalewallet_InterestShareBalance;

    private String t_personalewallet_BankAccName;

    private String t_personalewallet_BankAcc;

    private Date t_personalewallet_SysUpdateDate;

    private Integer t_personalewallet_BalanceCntDays;

    private String t_personalewallet_ewalletAccStatus;

    private String t_personalewallet_SMS;

    private String t_personalewallet_PaymentVersion;

    private BigDecimal t_personalewallet_MarginShare;

    private String t_personalewallet_AccCat;

    private String t_personalewallet_treasuryID;

    private String t_personalewallet_eproposal;

    private String t_personalewallet_DigiAddress;

    private String t_personalewallet_TermPaymentBalance;

    private String t_personalewallet_TermPaymentBal;

    private String t_personalewallet_ApplierMobile;

    public String getT_personalewallet_ApplierMobile() {
        return t_personalewallet_ApplierMobile;
    }

    public void setT_personalewallet_ApplierMobile(String t_personalewallet_ApplierMobile) {
        this.t_personalewallet_ApplierMobile = t_personalewallet_ApplierMobile;
    }

    public String getT_personalewallet_AlipayQRcode() {
        return t_personalewallet_AlipayQRcode;
    }

    public void setT_personalewallet_AlipayQRcode(String t_personalewallet_AlipayQRcode) {
        this.t_personalewallet_AlipayQRcode = t_personalewallet_AlipayQRcode;
    }

    public String getT_personalewallet_WechatQRcode() {
        return t_personalewallet_WechatQRcode;
    }

    public void setT_personalewallet_WechatQRcode(String t_personalewallet_WechatQRcode) {
        this.t_personalewallet_WechatQRcode = t_personalewallet_WechatQRcode;
    }

    public String getT_personalewallet_UnionQRcode() {
        return t_personalewallet_UnionQRcode;
    }

    public void setT_personalewallet_UnionQRcode(String t_personalewallet_UnionQRcode) {
        this.t_personalewallet_UnionQRcode = t_personalewallet_UnionQRcode;
    }

    public String getT_personalewallet_Txt1() {
        return t_personalewallet_Txt1;
    }

    public void setT_personalewallet_Txt1(String t_personalewallet_Txt1) {
        this.t_personalewallet_Txt1 = t_personalewallet_Txt1;
    }

    public String getT_personalewallet_Txt2() {
        return t_personalewallet_Txt2;
    }

    public void setT_personalewallet_Txt2(String t_personalewallet_Txt2) {
        this.t_personalewallet_Txt2 = t_personalewallet_Txt2;
    }

    private String t_personalewallet_AlipayQRcode;

    private String t_personalewallet_WechatQRcode;

    private String t_personalewallet_UnionQRcode;

    private String t_personalewallet_Txt1;

    private String t_personalewallet_Txt2;

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
        return t_personalewallet_PayRules;
    }

    public void setT_personalewallet_Passport(String t_personalewallet_PayRules) {
        this.t_personalewallet_PayRules = t_personalewallet_PayRules;
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
        return t_personalewallet_BaselineAdjustment;
    }

    public void setT_personalewallet_CreditPayAmt(BigDecimal t_personalewallet_BaselineAdjustment) {
        this.t_personalewallet_BaselineAdjustment = t_personalewallet_BaselineAdjustment;
    }

    public BigDecimal getT_personalewallet_CreditPayAmtInit() {
        return t_personalewallet_TotDailyAmt;
    }

    public void setT_personalewallet_CreditPayAmtInit(BigDecimal t_personalewallet_TotDailyAmt) {
        this.t_personalewallet_TotDailyAmt = t_personalewallet_TotDailyAmt;
    }

    public BigDecimal getT_personalewallet_TotalPrepayAmt() {
        return t_personalewallet_DailyInterestRatio;
    }

    public void setT_personalewallet_TotalPrepayAmt(BigDecimal t_personalewallet_DailyInterestRatio) {
        this.t_personalewallet_DailyInterestRatio = t_personalewallet_DailyInterestRatio;
    }

    public Integer getT_personalewallet_TotalInterestDays() {
        return t_personalewallet_DayCntEq0;
    }

    public void setT_personalewallet_TotalInterestDays(Integer t_personalewallet_DayCntEq0) {
        this.t_personalewallet_DayCntEq0 = t_personalewallet_DayCntEq0;
    }

    public Integer getT_personalewallet_PayCounts() {
        return t_personalewallet_DayCntMorethan0;
    }

    public void setT_personalewallet_PayCounts(Integer t_personalewallet_DayCntMorethan0) {
        this.t_personalewallet_DayCntMorethan0 = t_personalewallet_DayCntMorethan0;
    }

    public BigDecimal getT_personalewallet_Interest() {
        return t_personalewallet_Interest;
    }

    public void setT_personalewallet_Interest(BigDecimal t_personalewallet_Interest) {
        this.t_personalewallet_Interest = t_personalewallet_Interest;
    }

    public BigDecimal getT_personalewallet_TotLimit() {
        return t_personalewallet_BalaceYesterDay;
    }

    public void setT_personalewallet_TotLimit(BigDecimal t_personalewallet_BalaceYesterDay) {
        this.t_personalewallet_BalaceYesterDay = t_personalewallet_BalaceYesterDay;
    }

    public BigDecimal getT_personalewallet_Worth() {
        return t_personalewallet_Worth;
    }

    public void setT_personalewallet_Worth(BigDecimal t_personalewallet_Worth) {
        this.t_personalewallet_Worth = t_personalewallet_Worth;
    }

    public BigDecimal getT_personalewallet_DiscountRate() {
        return t_personalewallet_DiscountRate;
    }

    public void setT_personalewallet_DiscountRate(BigDecimal t_personalewallet_DiscountRate) {
        this.t_personalewallet_DiscountRate = t_personalewallet_DiscountRate;
    }

    public BigDecimal getT_personalewallet_BalanceInterest() {
        return t_personalewallet_BalanceInterest;
    }

    public void setT_personalewallet_BalanceInterest(BigDecimal t_personalewallet_BalanceInterest) {
        this.t_personalewallet_BalanceInterest = t_personalewallet_BalanceInterest;
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
        return t_personalewallet_TotalPrepayAmt;
    }

    public void setT_personalewallet_OverdueTotalAmount(BigDecimal t_personalewallet_TotalPrepayAmt) {
        this.t_personalewallet_TotalPrepayAmt = t_personalewallet_TotalPrepayAmt;
    }

    public BigDecimal getT_personalewallet_FinancialInterest() {
        return t_personalewallet_TotalWorthCal;
    }

    public void setT_personalewallet_FinancialInterest(BigDecimal t_personalewallet_TotalWorthCal) {
        this.t_personalewallet_TotalWorthCal = t_personalewallet_TotalWorthCal;
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
        return t_personalewallet_YesterdayWorthCal;
    }

    public void setT_personalewallet_TierPoundage(BigDecimal t_personalewallet_YesterdayWorthCal) {
        this.t_personalewallet_YesterdayWorthCal = t_personalewallet_YesterdayWorthCal;
    }

    public BigDecimal getT_personalewallet_InterestMargin() {
        return t_personalewallet_InterestShareBalance;
    }

    public void setT_personalewallet_InterestMargin(BigDecimal t_personalewallet_InterestShareBalance) {
        this.t_personalewallet_InterestShareBalance = t_personalewallet_InterestShareBalance;
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
        return t_personalewallet_BalanceCntDays;
    }

    public void setT_personalewallet_OverdueDays(Integer t_personalewallet_BalanceCntDays) {
        this.t_personalewallet_BalanceCntDays = t_personalewallet_BalanceCntDays;
    }

    public String getT_personalewallet_Paystatus() {
        return t_personalewallet_ewalletAccStatus;
    }

    public void setT_personalewallet_Paystatus(String t_personalewallet_ewalletAccStatus) {
        this.t_personalewallet_ewalletAccStatus = t_personalewallet_ewalletAccStatus;
    }

    public String getT_personalewallet_SMS() {
        return t_personalewallet_SMS;
    }

    public void setT_personalewallet_SMS(String t_personalewallet_SMS) {
        this.t_personalewallet_SMS = t_personalewallet_SMS;
    }

    public String getT_personalewallet_PaymentVersion() {
        return t_personalewallet_PaymentVersion;
    }

    public void setT_personalewallet_PaymentVersion(String t_personalewallet_PaymentVersion) {
        this.t_personalewallet_PaymentVersion = t_personalewallet_PaymentVersion;
    }

    public BigDecimal getT_personalewallet_bkp() {
        return t_personalewallet_MarginShare;
    }

    public void setT_personalewallet_bkp(BigDecimal t_personalewallet_MarginShare) {
        this.t_personalewallet_MarginShare = t_personalewallet_MarginShare;
    }

    public String getT_personalewallet_AccCat() {
        return t_personalewallet_AccCat;
    }

    public void setT_personalewallet_AccCat(String t_personalewallet_AccCat) {
        this.t_personalewallet_AccCat = t_personalewallet_AccCat;
    }

    public String getT_personalewallet_treasuryID() {
        return t_personalewallet_treasuryID;
    }

    public void setT_personalewallet_treasuryID(String t_personalewallet_treasuryID) {
        this.t_personalewallet_treasuryID = t_personalewallet_treasuryID;
    }

    public String getT_personalewallet_eproposal() {
        return t_personalewallet_eproposal;
    }

    public void setT_personalewallet_eproposal(String t_personalewallet_eproposal) {
        this.t_personalewallet_eproposal = t_personalewallet_eproposal;
    }

    public String getT_personalewallet_DigiAddress() {
        return t_personalewallet_DigiAddress;
    }

    public void setT_personalewallet_DigiAddress(String t_personalewallet_DigiAddress) {
        this.t_personalewallet_DigiAddress = t_personalewallet_DigiAddress;
    }

    public String getT_personalewallet_Txt4() {
        return t_personalewallet_TermPaymentBalance;
    }

    public void setT_personalewallet_Txt4(String t_personalewallet_TermPaymentBalance) {
        this.t_personalewallet_TermPaymentBalance = t_personalewallet_TermPaymentBalance;
    }

    public String getT_personalewallet_Txt5() {
        return t_personalewallet_TermPaymentBal;
    }

    public void setT_personalewallet_Txt5(String t_personalewallet_TermPaymentBal) {
        this.t_personalewallet_TermPaymentBal = t_personalewallet_TermPaymentBal;
    }

}