package com.qucai.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PersonalTreasuryCtrlDao;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.service.PersonalTreasuryCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("PersonalTreasuryCtrlService")
@Transactional
public class PersonalTreasuryCtrlServiceImpl implements PersonalTreasuryCtrlService {

    @Autowired
    private PersonalTreasuryCtrlDao personalTreasuryCtrlDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/

    @Override
    public int deleteByPrimaryKey(String t_FProd_ID) {
        return personalTreasuryCtrlDao.deleteByPrimaryKey(t_FProd_ID);
    }

    @Override
    public int insertSelective(PersonalTreasuryCtrl record) {
        return personalTreasuryCtrlDao.insertSelective(record);
    }

    @Override
    public PersonalTreasuryCtrl selectByPrimaryKey(String t_FProd_ID) {
        return personalTreasuryCtrlDao.selectByPrimaryKey(t_FProd_ID);
    }

    @Override
    public int updateByPrimaryKeySelective(PersonalTreasuryCtrl record) {
        return personalTreasuryCtrlDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<PersonalTreasuryCtrl> findAllList(Map<String, Object> paramMap) {
        return personalTreasuryCtrlDao.findAllList(paramMap);
    }

    @Override
    public List<PersonalTreasuryCtrl> findSearchList(Map<String, Object> paramSearchMap) {
        return personalTreasuryCtrlDao.findSearchList(paramSearchMap);
    }

    @Override
    public PageInfo<PersonalTreasuryCtrl> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalTreasuryCtrl> list = personalTreasuryCtrlDao.findAllList(paramMap);
        return new PageInfo<PersonalTreasuryCtrl>(list);
    }

    @Override
    public PageInfo<PersonalTreasuryCtrl> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalTreasuryCtrl> list = personalTreasuryCtrlDao.findSearchList(paramSearchMap);
        return new PageInfo<PersonalTreasuryCtrl>(list);
    }

    @Override
    public boolean existPersonalTreasuryCtrlName(String t_FProd_ID, String t_FProd_Name, Integer platform) {
        return personalTreasuryCtrlDao.existPersonalTreasuryCtrlName(t_FProd_ID, t_FProd_Name, platform) == 1;
    }

}
