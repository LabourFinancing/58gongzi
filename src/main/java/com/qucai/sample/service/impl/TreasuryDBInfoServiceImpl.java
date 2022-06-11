package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.TreasuryDBInfoDao;
import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.service.TreasuryDBInfoService;

@Service("TreasuryDBInfoService")
@Transactional	
public class TreasuryDBInfoServiceImpl implements TreasuryDBInfoService {

    @Autowired
    private TreasuryDBInfoDao TreasuryDBInfoDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
      

    @Override
    public TreasuryDBInfo selectByPrimaryKey(String t_TreasuryDB_ID) {
        return TreasuryDBInfoDao.selectByPrimaryKey(t_TreasuryDB_ID);
    }
    
    
    @Override
    public TreasuryDBInfo findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName) {
        return TreasuryDBInfoDao.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
    }

    @Override
    public TreasuryDBInfo findOrgTreasuryOrgName(String t_TreasuryDB_OrgName) {
        return TreasuryDBInfoDao.findOrgTreasuryOrgName(t_TreasuryDB_OrgName);
    }
    
    @Override
    public TreasuryDBInfo StatisticOverall(String t_TreasuryDB_OrgName) {
        return TreasuryDBInfoDao.StatisticOverall(t_TreasuryDB_OrgName);
    }


    @Override
    public List<TreasuryDBInfo> findAllList(Map<String, Object> paramMap) {
        return TreasuryDBInfoDao.findAllList(paramMap);
    }
    
    @Override
    public List<TreasuryDBInfo> findSearchList(Map<String, Object> paramMap) {
        return TreasuryDBInfoDao.findSearchList(paramMap);
    }
    
    @Override
    public List<TreasuryDBInfo> findAgencyAllList(Map<String, Object> paramMap) {
        return TreasuryDBInfoDao.findAgencyAllList(paramMap);
    }
    
    @Override
    public PageInfo<TreasuryDBInfo> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryDBInfo> list = TreasuryDBInfoDao.findAllList(paramMap);
        return new PageInfo<TreasuryDBInfo>(list);
    }
    
    @Override
    public PageInfo<TreasuryDBInfo> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<TreasuryDBInfo> list = TreasuryDBInfoDao.findSearchList(paramSearchMap);
        return new PageInfo<TreasuryDBInfo>(list);
    }
    
    @Override
    public PageInfo<TreasuryDBInfo> findAgencyAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryDBInfo> list = TreasuryDBInfoDao.findAgencyAllList(paramMap);
        return new PageInfo<TreasuryDBInfo>(list);
    }

    /*
    public List<FinanceProduct> findTreetableList(Map<String, Object> paramMap) {
        List<FinanceProduct> rList = financeProductDao.findAllList(paramMap);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList(initGroupMap(rList), "");
        }
    }
    
    @Override
    public List<FinanceProductGrant> findGrantTreetableList(String roleId, Integer platform) {
        List<FinanceProductGrant> rList = financeProductDao.findManagerFinanceProductGrantAllList(roleId, platform);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList2(initGroupMap(rList), "");
        }
    }
    
    @Override
    public List<Resource> findAuthResourceListByManagerId(String managerId) {
        List<Resource> rList = resourceDao.findAuthResourceListByManagerId(managerId);
        if(CollectionUtils.isEmpty(rList)) {
        	return null;
        }else {
        	return assembleTreeList(initGroupMap(rList), "");
        }
    }
    
    private <T extends Resource> Map<String, List<T>> initGroupMap(List<T> rList){
        Map<String, List<T>> map = new HashMap<String, List<T>>();
        for (T r : rList) {
            if (StringUtils.isBlank(r.getParentId())) {
                if (map.containsKey("")) {
                    map.get("").add(r);
                } else {
                    List<T> t = new ArrayList<T>();
                    t.add(r);
                    map.put("", t);
                }
                continue;
            } else {
                if (map.containsKey(r.getParentId())) {
                    map.get(r.getParentId()).add(r);
                } else {
                    List<T> t = new ArrayList<T>();
                    t.add(r);
                    map.put(r.getParentId(), t);
                }
            }
        }
        return map;
    }

    private <T extends Resource> List<T> assembleTreeList(Map<String, List<T>> map, String key){
        List<T> rs = new ArrayList<T>();
        for(T r : map.get(key)){
            rs.add(r);
            if(map.containsKey(r.getId())){
                rs.addAll(assembleTreeList(map, r.getId()));
            }
        }
        return rs;
    }
    
    private List<ResourceGrant> assembleTreeList2(Map<String, List<ResourceGrant>> map, String key){
        List<ResourceGrant> rs = new ArrayList<ResourceGrant>();
        for(ResourceGrant r : map.get(key)){
            rs.add(r);
            if(map.containsKey(r.getId())){
                rs.addAll(assembleTreeList2(map, r.getId()));
            } else {
            	r.setIsLeaf(true);
            }
        }
        return rs;
    }
*/
    @Override  
     public boolean existTreasuryDBInfoName(String t_TreasuryDB_OrgName) {
     return TreasuryDBInfoDao.existTreasuryDBInfoName(t_TreasuryDB_OrgName) == 1;
    }
	
    @Override
    public int updateByPrimaryKeySelective(TreasuryDBInfo record) {
        return TreasuryDBInfoDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateCreditStatus(String t_TreasuryDB_OrgName) {
        return TreasuryDBInfoDao.updateCreditStatus(t_TreasuryDB_OrgName);
    }
    
    @Override
    public int updateCreditRefresh(String t_TreasuryDB_OrgName) {
        return TreasuryDBInfoDao.updateCreditRefresh(t_TreasuryDB_OrgName);
    }

    @Override
    public int insertSelective(TreasuryDBInfo record) {
        return TreasuryDBInfoDao.insertSelective(record);
    }
    
    @Override
    public int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance) {
        return TreasuryDBInfoDao.updateByBalanceRefresh(ArrayPaymentBalance);
    }
}
