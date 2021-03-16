package com.qucai.sample.entity;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class Config {
    /**
     * id
     */
    private String id;

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
    
    /**
     * 备注
     */
    private String remark;

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
     * 键
     * @return 
     */
    public String getCkey() {
        return ckey;
    }

    /**
     * 键
     * @param ckey
     */
    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    /**
     * 值
     * @return 
     */
    public String getCvalue() {
        return cvalue;
    }

    /**
     * 值
     * @param cvalue
     */
    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
    }

    /**
     * 状态（0：不可用；1：可用）
     * @return 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0：不可用；1：可用）
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}