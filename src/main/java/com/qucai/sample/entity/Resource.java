package com.qucai.sample.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @version 1.0 2016-08-09
 */
public class Resource implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1876948347463745808L;

	/**
     * id
     */
    private String id;

    /**
     * 父资源id
     */
    private String parentId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 链接
     */
    private String link;

    /**
     * 排序索引
     */
    private Integer idx;

    /**
     * 是否显示（0：否；1：是）
     */
    private Integer isShow;

    /**
     * shiro权限代码
     */
    private String shiroPermission;

    /**
     * 所属平台（0：管理后台；1：企业端；2：个人端web）
     */
    private Integer platform;
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
     * 父资源id
     * @return 
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父资源id
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 资源名称
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * 资源名称
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 类型
     * @return 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 链接
     * @return 
     */
    public String getLink() {
        return link;
    }

    /**
     * 链接
     * @param link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 排序索引
     * @return 
     */
    public Integer getIdx() {
        return idx;
    }

    /**
     * 排序索引
     * @param idx
     */
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    /**
     * 是否显示（0：否；1：是）
     * @return 
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * 是否显示（0：否；1：是）
     * @param isShow
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * shiro权限代码
     * @return 
     */
    public String getShiroPermission() {
        return shiroPermission;
    }

    /**
     * shiro权限代码
     * @param shiroPermission
     */
    public void setShiroPermission(String shiroPermission) {
        this.shiroPermission = shiroPermission;
    }
    
    /**
     * 所属平台（0：管理后台；1：企业端；2：个人端web）
     * @return 
     */
    public Integer getPlatform() {
		return platform;
	}

    /**
     * 所属平台（0：管理后台；1：企业端；2：个人端web）
     * @param platform
     */
	public void setPlatform(Integer platform) {
		this.platform = platform;
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