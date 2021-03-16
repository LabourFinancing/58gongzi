package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.Config;

@Repository
public interface ConfigDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Config record);
    
    List<Config> findAllList(Map<String, Object> paramMap);
}