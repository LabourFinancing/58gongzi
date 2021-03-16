package com.qucai.sample.req;

public class ConfigReq {

	/**
     * 键
     */
    private String ckey;

    /**
     * 值
     */
    private String cvalue;

    /**
     * 状态（0：不可用；1：可用）
     */
    private Integer status;

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    
}
