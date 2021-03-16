package com.qucai.sample.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.StaffPrepayApplicationDao;
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.vo.StaffPrepayApplicationNew;

@Service("StaffPrepayApplicationService")
@Transactional	
public class StaffPrepayApplicationServiceImpl implements StaffPrepayApplicationService {

    @Autowired
    private StaffPrepayApplicationDao staffPrepayApplicationDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
    @Override   
    public int deleteFailedPayment(String OrderCode) {
        return staffPrepayApplicationDao.deleteFailedPayment(OrderCode);
    }
    
    public int deleteTxnPayment(String t_Txn_PrepayApplierName,String t_Txn_PrepayApplierPID,String t_Txn_Paystatus) {
        return staffPrepayApplicationDao.deleteTxnPayment(t_Txn_PrepayApplierName,t_Txn_PrepayApplierPID,t_Txn_Paystatus);
    }
    
    @Override   
    public int paymentSuccCheck(String OrderCode) {
        return staffPrepayApplicationDao.paymentSuccCheck(OrderCode);
    }
    
    @Override
    public int refreshCredit(String t_TreasuryDB_OrgName_get) {
        return staffPrepayApplicationDao.refreshCredit(t_TreasuryDB_OrgName_get);
    }
    
    @Override
    public int updateFailedPayment(String OrderCode) {
        return staffPrepayApplicationDao.updateFailedPayment(OrderCode);
    }
    
    @Override
    public int updateCreditBalanceAmt(BigDecimal CreditBalanceAmtRefund,String OrderCodeUpdatee) {
        return staffPrepayApplicationDao.updateCreditBalanceAmt(CreditBalanceAmtRefund,OrderCodeUpdatee);
    }
    
    @Override
    public int insertSelective(StaffPrepayApplicationList record) {
        return staffPrepayApplicationDao.insertSelective(record);
    }
    
    @Override
    public StaffPrepayApplicationList FindFailedPayment(String OrderCode) {
    	return staffPrepayApplicationDao.FindFailedPayment(OrderCode);
    }
    
    @Override
    public StaffPrepayApplicationList findPrepayApplierCreditBalance(String SeesionLoginMobil) {
    	return staffPrepayApplicationDao.findPrepayApplierCreditBalance(SeesionLoginMobil);
    }
    
    @Override
    public int insertPayment(StaffPrepayApplicationPayment record) {
    	return staffPrepayApplicationDao.insertPayment(record);
    }
    
    @Override
    public List<StaffPrepayApplicationPayment> findFailedPaymentList(Map<String, Object> paramMap) {
    	 return staffPrepayApplicationDao.findFailedPaymentList(paramMap);
    }
    
    @Override
    public List<StaffPrepayApplicationPayment> findSearchFailedPaymentList(Map<String, Object> paramMap) {
    	 return staffPrepayApplicationDao.findSearchFailedPaymentList(paramMap);
    }    
    
    @Override
    public StaffPrepayApplicationNew findAuthPrepayApplier(String SeesionLoginMobil) {
    	return staffPrepayApplicationDao.findAuthPrepayApplier(SeesionLoginMobil);
    }
    
    @Override
    public StaffPrepayApplicationNew findSelectedByFProdName(String t_FProd_Name) {
    	return staffPrepayApplicationDao.findSelectedByFProdName(t_FProd_Name);
    }
    
//    @Override
//    public StaffPrepayApplicationNew selectAuthFinanceProd(String t_FProd_Name) {
//    	return staffPrepayApplicationDao.selectAuthFinanceProd(t_FProd_Name);
//    }
    @Override
    public StaffPrepayApplicationList findPrepayApplierCredit(String SeesionLoginMobil) {
    	return staffPrepayApplicationDao.findPrepayApplierCredit(SeesionLoginMobil);
    }
    
    @Override
    public List<StaffPrepayApplicationList> findAllList(Map<String, Object> paramMap) {
        return staffPrepayApplicationDao.findAllList(paramMap);
    }
    
    @Override
    public List<StaffPrepayApplicationList> findAllNowList(Map<String, Object> paramMap) {
        return staffPrepayApplicationDao.findAllNowList(paramMap);
    }
    
    @Override
    public List<StaffPrepayApplicationList> findSearchList(Map<String, Object> paramMap) {
        return staffPrepayApplicationDao.findSearchList(paramMap);
    }
    
    @Override
    public List<StaffPrepayApplicationList> findRealTimeSearchList(Map<String, Object> paramMap) {
        return staffPrepayApplicationDao.findRealTimeSearchList(paramMap);
    }
    
    @Override
    public List<StaffPrepayApplicationNew> findAuthFinanceProd(Map<String, Object> paramMap) {
        List<StaffPrepayApplicationNew> StaffPrepayApplicationNew = staffPrepayApplicationDao.findAuthFinanceProd(paramMap);
    	return staffPrepayApplicationDao.findAuthFinanceProd(paramMap);
    }
    
    @Override
    public PageInfo<StaffPrepayApplicationList> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<StaffPrepayApplicationList> list = staffPrepayApplicationDao.findAllList(paramMap);
        return new PageInfo<StaffPrepayApplicationList>(list);
    }
    
    @Override
    public PageInfo<StaffPrepayApplicationList> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<StaffPrepayApplicationList> list = staffPrepayApplicationDao.findSearchList(paramSearchMap);
        return new PageInfo<StaffPrepayApplicationList>(list);
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
     public boolean existStaffPrepayApplicationName(String t_Txn_ID, String t_Txn_PrepayApplierName, Integer platform) {
     return staffPrepayApplicationDao.existStaffPrepayApplicationName(t_Txn_ID, t_Txn_PrepayApplierName, platform) == 1;
    }
    
}
