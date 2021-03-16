package com.qucai.sample.entity;
//package com.qucai.sample.entity;

import java.io.Serializable;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class StaffPrepayApplicationPayment implements Serializable {
    /**
	 * 
	 */
   private static final long serialVersionUID = -1876948347463745808L;
   /**
    * 创建人
    */
	private String ID;
	
	private String orderCode;
	
	private String version;
	
	private String productId;
	
	private String tranTime;
	
	private String name;
	
	private String company;
	
	private String certType;
	
	private String certNo;
	
	private String returnPic;
	
	private String accNo;
	
	private String accName; 
	
	private String accAttr;
	
	private String accType;
	
	private String phone; 
	
	private String currencyCode;
	
	private String tranAmt;
	
	private String RCcode;
	
	private String remark;
	
	private String reqReserved;

	public String getID() {
		return ID;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getVersion() {
		return version;
	}

	public String getProductId() {
		return productId;
	}

	public String getTranTime() {
		return tranTime;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	public String getCertType() {
		return certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public String getReturnPic() {
		return returnPic;
	}

	public String getAccNo() {
		return accNo;
	}

	public String getAccName() {
		return accName;
	}

	public String getAccAttr() {
		return accAttr;
	}

	public String getAccType() {
		return accType;
	}

	public String getPhone() {
		return phone;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public String getRCcode() {
		return RCcode;
	}

	public String getRemark() {
		return remark;
	}

	public String getReqReserved() {
		return reqReserved;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setTranTime(String tranTime) {
		this.tranTime = tranTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public void setReturnPic(String returnPic) {
		this.returnPic = returnPic;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public void setAccAttr(String accAttr) {
		this.accAttr = accAttr;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public void setRCcode(String rCcode) {
		RCcode = rCcode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setReqReserved(String reqReserved) {
		this.reqReserved = reqReserved;
	}


}