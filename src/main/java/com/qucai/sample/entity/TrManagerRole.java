package com.qucai.sample.entity;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class TrManagerRole {
    /**
     * 管理人员id
     */
    private String managerId;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 管理人员id
     * @return 
     */
    public String getManagerId() {
        return managerId;
    }

    /**
     * 管理人员id
     * @param managerId
     */
    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    /**
     * 角色id
     * @return 
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 角色id
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}