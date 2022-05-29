package com.qucai.sample.entity;

/**
 * 
 * 
 * @version 1.0 2016-08-05
 */
public class TrAgentSubOrg {
    /**
     *
     *
     */
    public String getAgentid() {
        return this.agentid;
    }

    public void setAgentid(final String agentid) {
        this.agentid = agentid;
    }

    public String getSuborgid() {
        return this.suborgid;
    }

    public void setSuborgid(final String suborgid) {
        this.suborgid = suborgid;
    }

    /**
     * 经销商企业id
     */
    private String agentid;

    /**
     * 企业id
     */
    private String suborgid;


}