package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Role;
import com.qucai.sample.vo.RoleGrant;


public interface RoleService {
    
    int deleteByPrimaryKey(String id);

    int insertSelective(Role record);
    
    Role selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(Role record);
    
    PageInfo<Role> findAllList(Map<String, Object> paramMap, PageParam pp);
    
	List<Role> findAuthRoleListByManagerId(String managerId);

	List<RoleGrant> findManagerRoleGrantAllList(String managerId);
	
	void grantResource(String roleId, String resourceIds);
	
	boolean existRoleName(String id, String name, Integer platform);

	/**
	 * 查询系统管理员创建的协会、经纪公司账号（目前只有协会账号）拥有的角色
	 * @param paramMap
	 * @return
	 */
	List<RoleGrant> findEntRoleGrantAllList(Map<String, Object> paramMap);

	/**
	 * 根据角色名称查询
	 * @param name
	 * @return
	 */
	Role selectByName(String name);
	
}
