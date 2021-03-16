package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.ConfigDao;
import com.qucai.sample.entity.Config;
import com.qucai.sample.service.ConfigService;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigDao configDao;

    @Override
    public int deleteByPrimaryKey(String id) {
        return configDao.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Config record) {
        return configDao.insertSelective(record);
    }

    @Override
    public Config selectByPrimaryKey(String id) {
        return configDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Config record) {
        return configDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Config> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<Config> list = configDao.findAllList(paramMap);
        return new PageInfo<Config>(list);
    }

}
