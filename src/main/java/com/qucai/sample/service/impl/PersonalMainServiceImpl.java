package com.qucai.sample.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.dao.PersonalMainDao;
import com.qucai.sample.entity.PersonalMain;
import com.qucai.sample.service.PersonalMainService;

@Service("PersonalMainService")
@Transactional
public class PersonalMainServiceImpl implements PersonalMainService {

    @Autowired
    private PersonalMainDao personalMainDao;

    /*
        @Autowired
        private TrRoleResourceDao trRoleResourceDao;
    */
    @Override
    public int deleteByPrimaryKey(String t_personal_main_id) {
        return personalMainDao.deleteByPrimaryKey(t_personal_main_id);
    }


    @Override
    public int insertSelective(PersonalMain record) {
        return personalMainDao.insertSelective(record);
    }

    public PersonalMain selectByPrimaryKey(String t_personal_main_id,String pid) {
        return personalMainDao.selectByPrimaryKey(t_personal_main_id,pid);
    }

    @Override
    public int updateByPrimaryKeySelective(PersonalMain record,String pid) {
        return personalMainDao.updateByPrimaryKeySelective(record,pid);
    }
//
//    @Override
//    public int updateCompanyStaffsCreditOn(String t_TreasuryDB_OrgName_get) {
//        return personalMainDao.updateCompanyStaffsCreditOn(t_TreasuryDB_OrgName_get);
//    }
//    
//    @Override
//    public int updateCompanyStaffsCreditOff(String t_TreasuryDB_OrgName_get) {
//        return personalMainDao.updateCompanyStaffsCreditOff(t_TreasuryDB_OrgName_get);
//    }


//    @Override
//    public List<PersonalMain> findSubCompany(Map<String, Object> paramMap) {
//        return personalMainDao.findSubCompany(paramMap);
//    }
//
//    @Override
//    public List<PersonalMain> findAgencyCompany(Map<String, Object> paramMap) {
//        return personalMainDao.findAgencyCompany(paramMap);
//    }

    @Override
    public List<PersonalMain> findAllList(Map<String, Object> paramMap) {
        return personalMainDao.findAllList(paramMap);
    }

    @Override
    public List<PersonalMain> findSearchList(Map<String, Object> paramSearchMap) {
        return personalMainDao.findSearchList(paramSearchMap);
    }

    @Override
    public List<PersonalMain> findSearchCompanyDist(Map<String, Object> paramSearchMap) {
        return personalMainDao.findSearchCompanyDist(paramSearchMap);
    }

    @Override
    public PageInfo<PersonalMain> findAllList(Map<String, Object> paramMap, PageParam pp) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalMain> list = personalMainDao.findAllList(paramMap);
        return new PageInfo<PersonalMain>(list);
    }

    @Override
    public PageInfo<PersonalMain> findSearchList(PageParam pp, Map<String, Object>paramSearchMap) {
        PageHelper.startPage(pp.getPageNum(), pp.getPageSize(), true, true);
        List<PersonalMain> list = personalMainDao.findSearchList(paramSearchMap);
        return new PageInfo<PersonalMain>(list);
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
    public boolean existPersonalMainName(String t_personal_main_id,String t_personal_main_pid) {
        return personalMainDao.existPersonalMainName(t_personal_main_id,t_personal_main_pid) == 1;
    }

    @Override
    public boolean checkPersonalMainMobil(String t_personal_main_id, String t_personal_main_mobile) {
        return personalMainDao.checkPersonalMainMobil(t_personal_main_id,t_personal_main_mobile) == 1;
    }

}
