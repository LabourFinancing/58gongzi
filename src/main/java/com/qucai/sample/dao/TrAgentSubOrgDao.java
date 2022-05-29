package com.qucai.sample.dao;

import com.qucai.sample.entity.TrAgentSubOrg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrAgentSubOrgDao {
    int deleteByPrimaryKey(@Param("tr_agentid") String tr_agentid, @Param("tr_suborgid") String tr_suborgid);
    
    int deleteByResourceIdStr(String tragentid);

    int deleteByPrimaryKey(String agentorgid);

    int deleteAgentSubOrg(String tr_agentid);

    void insertAgentSubOrg(String t_o_id, String t_o_id1);
}