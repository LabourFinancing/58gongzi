package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.qucai.sample.entity.Resource;
import com.qucai.sample.vo.ResourceGrant;

public interface ResourceService {
    
    int deleteByPrimaryKey(String id);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resource record);
    
    List<Resource> findAllList(Map<String, Object> paramMap);
    
    List<Resource> findTreetableList(Map<String, Object> paramMap);
    
    List<ResourceGrant> findGrantTreetableList(String roleId, Integer platform);
    
    List<Resource> findAuthResourceListByManagerId(String managerId);
    
    boolean existResourceName(String id, String name, Integer platform);
    
}