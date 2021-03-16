package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PaymentvendormgtDao;
import com.qucai.sample.entity.Paymentvendormgt;
import com.qucai.sample.service.PaymentvendormgtService;

@Service("PaymentvendormgtService")
@Transactional	
public class PaymentvendormgtServiceImpl implements PaymentvendormgtService {

    @Autowired
    private PaymentvendormgtDao PaymentvendormgtDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
      

    @Override
    public Paymentvendormgt selectByPrimaryKey(String t_TreasuryDB_ID) {
        return PaymentvendormgtDao.selectByPrimaryKey(t_TreasuryDB_ID);
    }
    
    
    @Override
    public Paymentvendormgt findOrgTreasuryCurrBalance(String t_Pymt_Name) {
        return PaymentvendormgtDao.findOrgTreasuryCurrBalance(t_Pymt_Name);
    }
    
    @Override
    public Paymentvendormgt StatisticOverall(String t_Pymt_Name) {
        return PaymentvendormgtDao.StatisticOverall(t_Pymt_Name);
    }


    @Override
    public List<Paymentvendormgt> findAllList(Map<String, Object> paramMap) {
        return PaymentvendormgtDao.findAllList(paramMap);
    }
    
    @Override
    public List<Paymentvendormgt> findSearchList(Map<String, Object> paramMap) {
        return PaymentvendormgtDao.findSearchList(paramMap);
    }
    
    @Override
    public List<Paymentvendormgt> findAgencyAllList(Map<String, Object> paramMap) {
        return PaymentvendormgtDao.findAgencyAllList(paramMap);
    }
    
    @Override
    public PageInfo<Paymentvendormgt> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<Paymentvendormgt> list = PaymentvendormgtDao.findAllList(paramMap);
        return new PageInfo<Paymentvendormgt>(list);
    }
    
    @Override
    public PageInfo<Paymentvendormgt> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<Paymentvendormgt> list = PaymentvendormgtDao.findSearchList(paramSearchMap);
        return new PageInfo<Paymentvendormgt>(list);
    }
    
    @Override
    public PageInfo<Paymentvendormgt> findAgencyAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<Paymentvendormgt> list = PaymentvendormgtDao.findAgencyAllList(paramMap);
        return new PageInfo<Paymentvendormgt>(list);
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
     public boolean existPaymentvendormgtName(String t_Pymt_Name) {
     return PaymentvendormgtDao.existPaymentvendormgtName(t_Pymt_Name) == 1;
    }
	
    @Override
    public int updateByPrimaryKeySelective(Paymentvendormgt record) {
        return PaymentvendormgtDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateCreditStatus(String t_Pymt_Name) {
        return PaymentvendormgtDao.updateCreditStatus(t_Pymt_Name);
    }
    
    @Override
    public int updateCreditRefresh(String t_Pymt_Name) {
        return PaymentvendormgtDao.updateCreditRefresh(t_Pymt_Name);
    }

    @Override
    public int insertSelective(Paymentvendormgt record) {
        return PaymentvendormgtDao.insertSelective(record);
    }


}
