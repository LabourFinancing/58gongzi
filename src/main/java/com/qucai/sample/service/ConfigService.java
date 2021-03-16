package com.qucai.sample.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Config;

public interface ConfigService {
    
    int deleteByPrimaryKey(String id);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(Config record);

    PageInfo<Config> findAllList(Map<String, Object> paramMap, PageParam pp);
}
