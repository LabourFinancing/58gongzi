package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PersonalInfoDao;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.service.PersonalInfoService;

@Service("PersonalInfoService")
@Transactional	
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private PersonalInfoDao personalInfoDao;

/*
    @Autowired
    private TrRoleResourceDao trRoleResourceDao;
*/
   @Override
   public int deleteByPrimaryKey(String t_P_id) {
       return personalInfoDao.deleteByPrimaryKey(t_P_id);
   }
       
    @Override
    public int insertSelective(PersonalInfo record) {
        return personalInfoDao.insertSelective(record);
    }

    @Override
    public PersonalInfo selectByPrimaryKey(String t_P_id) {
        return personalInfoDao.selectByPrimaryKey(t_P_id);
    }


    @Override
    public PersonalInfo findPrepayApplierCreditBalance(Map<String, Object> paramSearchMap) {
        return personalInfoDao.findPrepayApplierCreditBalance(paramSearchMap);
    }

    @Override
    public int updateByPrimaryKeySelective(PersonalInfo record) {
        return personalInfoDao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get) {
        return personalInfoDao.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
    }
    @Override
    public int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get) {
        return personalInfoDao.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
    }
    
    @Override
    public List<PersonalInfo> findSubCompany(Map<String, Object> paramMap) {
        return personalInfoDao.findSubCompany(paramMap);
    }
    
    @Override
    public List<PersonalInfo> findAgencyCompany(Map<String, Object> paramMap) {
        return personalInfoDao.findAgencyCompany(paramMap);
    }

    @Override
    public boolean updatePrepayApplierCreditBalance(Map<String, Object> paramSearchMap) {
        return true;
    }

    @Override
    public List<PersonalInfo> findAllList(Map<String, Object> paramMap) {
        return personalInfoDao.findAllList(paramMap);
    }
    
    @Override
    public List<PersonalInfo> findSearchList(Map<String, Object> paramSearchMap) {
        return personalInfoDao.findSearchList(paramSearchMap);
    }
    
    @Override
    public List<PersonalInfo> findSearchCompanyDist(Map<String, Object> paramSearchMap) {
        return personalInfoDao.findSearchCompanyDist(paramSearchMap);
    }
    
    @Override
    public PageInfo<PersonalInfo> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalInfo> list = personalInfoDao.findAllList(paramMap);
        return new PageInfo<PersonalInfo>(list);
    }
    
    @Override
    public PageInfo<PersonalInfo> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
		List<PersonalInfo> list = personalInfoDao.findSearchList(paramSearchMap);
        return new PageInfo<PersonalInfo>(list);
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
     public boolean existPersonalInfoName(String t_P_id,String t_P_PID) {
     return personalInfoDao.existPersonalInfoName(t_P_id,t_P_PID) == 1;
    }
    
    @Override
    public boolean checkPersonalInfoMobil(String t_P_id,String t_P_Mobil) {
    return personalInfoDao.checkPersonalInfoMobil(t_P_id,t_P_Mobil) == 1;
   }
    
}
