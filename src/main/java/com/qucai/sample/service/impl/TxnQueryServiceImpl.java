package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.TxnQueryDao;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.TxnQuery;
import com.qucai.sample.service.TxnQueryService;

@Service("PrepayApplicationListService")
@Transactional	
public class TxnQueryServiceImpl implements TxnQueryService {

    @Autowired
    private TxnQueryDao txnQueryDao;
    
    
/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
   @Override
   public int deleteByPrimaryKey(String t_TxnQuery_ID) {
       return txnQueryDao.deleteByPrimaryKey(t_TxnQuery_ID);
   }
       
    @Override
    public int insertSelective(TxnQuery record) {
        return txnQueryDao.insertSelective(record);
    }

    @Override
    public TxnQuery selectByPrimaryKey(String t_TxnQuery_ID) {
        return txnQueryDao.selectByPrimaryKey(t_TxnQuery_ID);
    }

    @Override
    public int updateByPrimaryKeySelective(TxnQuery record) {
        return txnQueryDao.updateByPrimaryKeySelective(record);
    }

    @Override
    public int findSearchList(StaffPrepayApplicationList record) {
        return txnQueryDao.findSearchList(record);
    }
    
    @Override
    public List<TxnQuery> findAllList(Map<String, Object> paramMap) {
        return txnQueryDao.findAllList(paramMap);
    }
    
    @Override
    public PageInfo<TxnQuery> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<TxnQuery> list = txnQueryDao.findAllList(paramMap);
        return new PageInfo<TxnQuery>(list);
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
    
}
