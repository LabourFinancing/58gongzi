package com.qucai.sample.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.TrManagerRole;

@Repository
public interface TrManagerRoleDao {
    int deleteByPrimaryKey(@Param("managerId") String managerId, @Param("roleId") String roleId);

    int insertSelective(TrManagerRole record);
}