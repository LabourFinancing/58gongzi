package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.Resource;
import com.qucai.sample.vo.ResourceGrant;

@Repository
public interface ResourceDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> findAllList(Map<String, Object> paramMap);
    
    List<ResourceGrant> findManagerResourceGrantAllList(@Param("roleId") String roleId, @Param("platform") Integer platform);
    
    String findResourceChildId(String id);
    
    int deleteByPrimaryKeyStr(String idStr);
    
    List<Resource> findAuthResourceListByManagerId(String managerId);
    
    int existResourceName(@Param("id") String id, @Param("name") String name, @Param("platform") Integer platform);
    
    //企业端的资源
    List<ResourceGrant> findEntResourceGrantAllList(String roleId);
}