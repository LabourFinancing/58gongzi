package com.qucai.sample.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PaymentBatchPayUploadStatusDao;
import com.qucai.sample.entity.PaymentBatchPayUploadStatus;
import com.qucai.sample.service.PaymentBatchPayUploadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("PaymentBatchPayUploadStatusService")
@Transactional	
public class PaymentBatchPayUploadStatusServiceImpl implements PaymentBatchPayUploadStatusService {

    @Autowired
    private PaymentBatchPayUploadStatusDao PaymentBatchPayUploadStatusDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
   @Override
   public int deleteByPrimaryKey(String t_batch_perslUploadStatus_id) {
       return PaymentBatchPayUploadStatusDao.deleteByPrimaryKey(t_batch_perslUploadStatus_id);
   }
//    public int deleteByPrimaryKey(String t_P_id) {
//     return PaymentBatchPayUploadStatusDao.deleteByPrimaryKey(t_P_id);
//    }   

    public PaymentBatchPayUploadStatus selectByPrimaryKey(String t_batch_perslUploadId) {
        return PaymentBatchPayUploadStatusDao.selectByPrimaryKey(t_batch_perslUploadId);
    }

    @Override
    public int updateByPrimaryKeySelective(PaymentBatchPayUploadStatus record) {
        return PaymentBatchPayUploadStatusDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get) {
        return PaymentBatchPayUploadStatusDao.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
    }
    @Override
    public int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get) {
        return PaymentBatchPayUploadStatusDao.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
    }

    @Override
    public List<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap) {
        return PaymentBatchPayUploadStatusDao.findAllList(paramMap);
    }
    
    @Override
    public List<PaymentBatchPayUploadStatus> findSearchList(Map<String, Object> paramSearchMap) {
        return PaymentBatchPayUploadStatusDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public int insertSelective(Map<String, Object> paramSearchMap) {
        return PaymentBatchPayUploadStatusDao.insertSelective(paramSearchMap);
    }
    
    @Override
    public PageInfo<PaymentBatchPayUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PaymentBatchPayUploadStatus> list = PaymentBatchPayUploadStatusDao.findAllList(paramMap);
        return new PageInfo<PaymentBatchPayUploadStatus>(list);
    }
    
    @Override
    public PageInfo<PaymentBatchPayUploadStatus> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<PaymentBatchPayUploadStatus> list = PaymentBatchPayUploadStatusDao.findSearchList(paramSearchMap);
        return new PageInfo<PaymentBatchPayUploadStatus>(list);
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
