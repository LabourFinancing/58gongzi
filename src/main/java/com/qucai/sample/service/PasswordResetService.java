package com.qucai.sample.service;

import com.qucai.sample.entity.PasswordReset;

public interface PasswordResetService {

    int insertSelective(PasswordReset record);
    
    PasswordReset selectByPrimaryKey(String prt_id);
    
}
