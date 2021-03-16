package com.qucai.sample.dao;

import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.PasswordReset;

@Repository
public interface PasswordResetDao {
    int insertSelective(PasswordReset record);
    
    PasswordReset selectByPrimaryKey(String prt_id);
    //企业端的资源
//  List<FinanceProductGrant> findEntFinanceProductGrantAllList(String roleId);
}