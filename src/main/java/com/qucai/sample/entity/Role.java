package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class Role implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3045361020507518012L;

	/**
     * id
     */
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;
    
    /**
     * 所属平台（0：管理端；1：企业端；2：个人端）
     */
    private Integer platform;

    /**
     * 企业id
     */
    private String enterpriseId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * id
     * @return 
     */
    public String getId() {
        return id;
    }

    /**
     * id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 角色名
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 角色名
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 状态
     * @return 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 所属平台
     * @return 
     */
    public Integer getPlatform() {
		return platform;
	}

    /**
     * 所属平台
     * @param platform
     */
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	/**
     * 企业id
     * @return 
     */
	public String getEnterpriseId() {
		return enterpriseId;
	}

	/**
     * 企业id
     * @param enterpriseId
     */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
     * 备注
     * @return 
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 创建人
     * @return 
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 创建时间
     * @return 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改人
     * @return 
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * 修改人
     * @param modifier
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 修改时间
     * @return 
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}