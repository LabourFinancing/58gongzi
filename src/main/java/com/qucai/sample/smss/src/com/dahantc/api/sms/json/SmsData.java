package com.qucai.sample.smss.src.com.dahantc.api.sms.json;

import java.util.Date;

public class SmsData {
	
	private String clientid;
	private String password;
	private String mobile;
	private String content;
	private String uid;
	private String smstype;
	private String extend;
	private Date sendtime;

	public SmsData(String clientid, String password, String mobile, String content, String uid, String smstype, String extend, Date sendtime) {
		super();
		this.clientid = clientid;
		this.password = password;
		this.mobile = mobile;
		this.content = content;
		this.uid = uid;
		this.smstype = smstype;
		this.extend = extend;
		this.sendtime = sendtime;
	}

	public SmsData() {
		super();
	}
	public String getClientid() {
		return clientid;
	}

	public String getPassword() {
		return password;
	}

	public String getMobile() {
		return mobile;
	}

	public String getContent() {
		return content;
	}

	public String getUid() {
		return uid;
	}

	public String getSmstype() {
		return smstype;
	}

	public String getExtend() {
		return extend;
	}

	public Date getSendtime() {
		return sendtime;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}

}
