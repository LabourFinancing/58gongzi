package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.Role;
import com.qucai.sample.vo.RoleGrant;

@Repository
public interface RoleDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);
    
    List<Role> findAllList(Map<String, Object> paramMap);
    
    List<Role> findAuthRoleListByManagerId(String managerId);
    
    List<RoleGrant> findManagerRoleGrantAllList(String managerId);
    
    int existRoleName(@Param("id") String id, @Param("name") String name, @Param("platform") Integer platform);
    
    /**
     * 查询协会、
     * @param paramMap
     * @return
     */
    public List<RoleGrant> findEntRoleGrantAllList(Map<String, Object> paramMap);

	/**
	 * @param name
	 * @return
	 */
	Role selectByName(String name);
}