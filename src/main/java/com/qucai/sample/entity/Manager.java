package com.qucai.sample.entity;

import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class Manager {
    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 公司名称
     */
    private String company_name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 座机
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 平台
     */
    private String platform;
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date modifyTime;

	public String getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getRealName() {
		return realName;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}