package com.qucai.sample.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.TrRoleResource;

@Repository
public interface TrRoleResourceDao {
    int deleteByPrimaryKey(@Param("resourceId") String resourceId, @Param("roleId") String roleId);

    int insertSelective(TrRoleResource record);
    
    int deleteByResourceIdStr(String resourceIdStr);
}