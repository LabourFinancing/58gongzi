package com.qucai.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.TreasuryDBMainDao;
import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.entity.TreasuryDBMain;
import com.qucai.sample.service.TreasuryDBMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("TreasuryDBMainService")
@Transactional
public class TreasuryDBMainServiceImpl implements TreasuryDBMainService {

    @Autowired
    private TreasuryDBMainDao TreasuryDBMainDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/

    @Override
    public TreasuryDBMain selectByPrimaryKey(String t_TreasuryDB_Main_ID) {
        return TreasuryDBMainDao.selectByPrimaryKey(t_TreasuryDB_Main_ID);
    }

    @Override
    public TreasuryDBMain findTreasuryPlatformAcc(String salaryAdvanceEwalletAcc) {
        return TreasuryDBMainDao.findTreasuryPlatformAcc(salaryAdvanceEwalletAcc);
    }


    /*
    @Override
    public TreasuryDBMain findOrgTreasuryCurrBalance(String t_TreasuryDB_OrgName) {
        return TreasuryDBMainDao.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
    }

    @Override
    public TreasuryDBMain StatisticOverall(String t_TreasuryDB_OrgName) {
        return TreasuryDBMainDao.StatisticOverall(t_TreasuryDB_OrgName);
    }
    */

    @Override
    public List<TreasuryDBMain> findAllList(Map<String, Object> paramMap) {
        return TreasuryDBMainDao.findAllList(paramMap);
    }

    @Override
    public List<TreasuryDBMain> findSearchList(Map<String, Object> paramMap) {
        return TreasuryDBMainDao.findSearchList(paramMap);
    }

    @Override
    public List<TreasuryDBMain> findAgencyAllList(Map<String, Object> paramMap) {
        return TreasuryDBMainDao.findAgencyAllList(paramMap);
    }

    @Override
    public PageInfo<TreasuryDBMain> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryDBMain> list = TreasuryDBMainDao.findAllList(paramMap);
        return new PageInfo<TreasuryDBMain>(list);
    }

    @Override
    public PageInfo<TreasuryDBMain> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryDBMain> list = TreasuryDBMainDao.findSearchList(paramSearchMap);
        return new PageInfo<TreasuryDBMain>(list);
    }

    @Override
    public PageInfo<TreasuryDBMain> findAgencyAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TreasuryDBMain> list = TreasuryDBMainDao.findAgencyAllList(paramMap);
        return new PageInfo<TreasuryDBMain>(list);
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
    public boolean existTreasuryDBMainName(String t_TreasuryDB_OrgName) {
        return TreasuryDBMainDao.existTreasuryDBMainName(t_TreasuryDB_OrgName) == 1;
    }

    @Override
    public int insertSelective(TreasuryDBMain record) {
        return TreasuryDBMainDao.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(TreasuryDBMain record) {
    return TreasuryDBMainDao.updateByPrimaryKeySelective(record);
    }
    /*
@Override
public int updateCreditStatus(String t_TreasuryDB_OrgName) {
return TreasuryDBMainDao.updateCreditStatus(t_TreasuryDB_OrgName);
}

@Override
public int updateCreditRefresh(String t_TreasuryDB_OrgName) {
return TreasuryDBMainDao.updateCreditRefresh(t_TreasuryDB_OrgName);
}


    
    @Override
    public int updateByBalanceRefresh(List<Map<String, Object>> ArrayPaymentBalance) {
        return TreasuryDBMainDao.updateByBalanceRefresh(ArrayPaymentBalance);
    }
     */
}
