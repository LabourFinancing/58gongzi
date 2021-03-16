package com.qucai.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qucai.sample.dao.PasswordResetDao;
import com.qucai.sample.entity.PasswordReset;
import com.qucai.sample.service.PasswordResetService;


@Service("PasswordResetService")
@Transactional	
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private PasswordResetDao passwordResetDao;

    @Override
    public int insertSelective(PasswordReset record) {
        return passwordResetDao.insertSelective(record);
    }
    @Override
    public PasswordReset selectByPrimaryKey(String prt_id) {
        return passwordResetDao.selectByPrimaryKey(prt_id);
    }
}