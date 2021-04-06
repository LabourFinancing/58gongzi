package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PersonalInfoBatchUploadStatusDao;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PersonalInfoBatchUploadStatus;
import com.qucai.sample.service.PersonalInfoBatchUploadStatusService;

@Service("PersonalInfoBatchUploadStatusService")
@Transactional	
public class PersonalInfoBatchUploadStatusServiceImpl implements PersonalInfoBatchUploadStatusService {

    @Autowired
    private PersonalInfoBatchUploadStatusDao PersonalInfoBatchUploadStatusDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
   @Override
   public int deleteByPrimaryKey(String t_batch_perslUploadStatus_id) {
       return PersonalInfoBatchUploadStatusDao.deleteByPrimaryKey(t_batch_perslUploadStatus_id);
   }
//    public int deleteByPrimaryKey(String t_P_id) {
//     return PersonalInfoBatchUploadStatusDao.deleteByPrimaryKey(t_P_id);
//    }   

    public PersonalInfoBatchUploadStatus selectByPrimaryKey(String t_batch_perslUploadId) {
        return PersonalInfoBatchUploadStatusDao.selectByPrimaryKey(t_batch_perslUploadId);
    }

    @Override
    public int updateByPrimaryKeySelective(PersonalInfoBatchUploadStatus record) {
        return PersonalInfoBatchUploadStatusDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get) {
        return PersonalInfoBatchUploadStatusDao.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
    }
    @Override
    public int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get) {
        return PersonalInfoBatchUploadStatusDao.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
    }

    @Override
    public List<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap) {
        return PersonalInfoBatchUploadStatusDao.findAllList(paramMap);
    }
    
    @Override
    public List<PersonalInfoBatchUploadStatus> findSearchList(Map<String, Object> paramSearchMap) {
        return PersonalInfoBatchUploadStatusDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public int insertSelective(Map<String, Object> paramSearchMap) {
        return PersonalInfoBatchUploadStatusDao.insertSelective(paramSearchMap);
    }
    
    @Override
    public PageInfo<PersonalInfoBatchUploadStatus> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalInfoBatchUploadStatus> list = PersonalInfoBatchUploadStatusDao.findAllList(paramMap);
        return new PageInfo<PersonalInfoBatchUploadStatus>(list);
    }
    
    @Override
    public PageInfo<PersonalInfoBatchUploadStatus> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<PersonalInfoBatchUploadStatus> list = PersonalInfoBatchUploadStatusDao.findSearchList(paramSearchMap);
        return new PageInfo<PersonalInfoBatchUploadStatus>(list);
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
