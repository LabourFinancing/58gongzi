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
import com.qucai.sample.dao.RoleDao;
import com.qucai.sample.dao.TrManagerRoleDao;
import com.qucai.sample.dao.TrRoleResourceDao;
import com.qucai.sample.entity.Role;
import com.qucai.sample.entity.TrRoleResource;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.vo.RoleGrant;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private TrManagerRoleDao trManagerRoleDao;
    
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        trManagerRoleDao.deleteByPrimaryKey(null, id);
        trRoleResourceDao.deleteByPrimaryKey(null, id);
        return roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Role record) {
        return roleDao.insertSelective(record);
    }

    @Override
    public Role selectByPrimaryKey(String id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Role> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<Role> list = roleDao.findAllList(paramMap);
        return new PageInfo<Role>(list);
    }

    @Override
    public List<Role> findAuthRoleListByManagerId(String managerId) {
        return roleDao.findAuthRoleListByManagerId(managerId);
    }

    @Override
    public List<RoleGrant> findManagerRoleGrantAllList(String managerId) {
        return roleDao.findManagerRoleGrantAllList(managerId);
    }

    @Override
    public void grantResource(String roleId, String resourceIds) {
        trRoleResourceDao.deleteByPrimaryKey(null, roleId);
        if (StringUtils.isNotBlank(resourceIds)) {
            TrRoleResource trr = new TrRoleResource();
            trr.setRoleId(roleId);
            for (String resourceId : resourceIds.split(",")) {
                trr.setResourceId(resourceId);
                trRoleResourceDao.insertSelective(trr);
            }
        }
        
    }

    @Override
    public boolean existRoleName(String id, String name, Integer platform) {
        return roleDao.existRoleName(id, name, platform) == 1;
    }

    @Override
	public List<RoleGrant> findEntRoleGrantAllList(Map<String, Object> paramMap) {
		return roleDao.findEntRoleGrantAllList(paramMap);
	}

	@Override
	public Role selectByName(String name) {
		return roleDao.selectByName(name);
	}

}
