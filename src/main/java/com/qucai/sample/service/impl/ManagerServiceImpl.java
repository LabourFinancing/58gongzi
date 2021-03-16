package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.ManagerDao;
import com.qucai.sample.dao.TrManagerRoleDao;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.TrManagerRole;
import com.qucai.sample.service.ManagerService;

@Service("ManagerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TrManagerRoleDao trManagerRoleDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        trManagerRoleDao.deleteByPrimaryKey(id, null);
        return managerDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Manager record) {
        return managerDao.insertSelective(record);
    }

    @Override
    public Manager selectByPrimaryKey(String id) {
        return managerDao.selectByPrimaryKey(id);
    }
    
    @Override
    public Manager selectByPersonalID(String personalID) {
        return managerDao.selectByPersonalID(personalID);
    }

    @Override
    public Manager selectByMobil(String mobile) {
        return managerDao.selectByMobil(mobile);
    }
    
    @Override
    public int updatePassword(Manager record) {
        return managerDao.updatePassword(record);
    }
    
    @Override
    public int updateByPrimaryKeySelective(Manager record) {
        return managerDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateAllCompanyStaffsOn(String t_O_OrgName) {
        return managerDao.updateAllCompanyStaffsOn(t_O_OrgName);
    }
    
    @Override
    public int updateAllCompanyStaffsOff(String t_O_OrgName) {
        return managerDao.updateAllCompanyStaffsOff(t_O_OrgName);
    }
    
    @Override
    public Manager ForgotPWDByPrimaryKeySelective(String userName) {
        return managerDao.ForgotPWDByPrimaryKeySelective(userName);
    }

    @Override
    public PageInfo<Manager> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<Manager> list = managerDao.findAllList(paramMap);
        return new PageInfo<Manager>(list);
    }
    
    @Override
    public PageInfo<Manager> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<Manager> list = managerDao.findSearchList(paramSearchMap);
        return new PageInfo<Manager>(list);
    }
    
    @Override
    public List<Manager> findSearchList(Map<String, Object> paramSearchMap) {
        return managerDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public Manager selectByNameAndPassword(String userName, String password) {
        return managerDao.selectByNameAndPassword(userName, password);
    }

    @Override
    public void grantRole(String managerId, String roleIds) {
        trManagerRoleDao.deleteByPrimaryKey(managerId, null);
        if (StringUtils.isNotBlank(roleIds)) {
            TrManagerRole tmr = new TrManagerRole();
            tmr.setManagerId(managerId);
            for (String roleId : roleIds.split(",")) {
                tmr.setRoleId(roleId);
                trManagerRoleDao.insertSelective(tmr);
            }
        }
    }

    @Override
    public boolean existManagerUserName(String id, String userName) {
        return managerDao.existManagerUserName(id, userName) == 1;
    }
}
