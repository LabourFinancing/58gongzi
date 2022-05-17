package com.qucai.sample.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Manager;

public interface ManagerService {
    
    int deleteByPrimaryKey(String id);

    int insertSelective(Manager record);
    
	int updateAllCompanyStaffsOn(String t_O_OrgName);
	
	int updateAllCompanyStaffsOff(String t_O_OrgName);
	
	int updatePassword(Manager record);
    
    Manager selectByPrimaryKey(String id);
    
	Manager selectByPersonalID(String personalID);
    
	Manager selectByMobil(String mobile);
    
    int updateByPrimaryKeySelective(Manager record);
    
    Manager ForgotPWDByPrimaryKeySelective(String userName);
    
    PageInfo<Manager> findAllList(Map<String, Object> paramMap, PageParam pp);
    
    List<Manager> findSearchList(Map<String, Object> paramSearchMap);
    
    PageInfo<Manager> findSearchList(PageParam pp,Map<String, Object>paramSearchMap);
    
    Manager selectByNameAndPassword(String userName, String password);
    
    void grantRole(String managerId, String roleIds);
    
    boolean existManagerUserName(String id, String userName);

    int countOrgUser(String orgName);

    int countMCUnique(Map<String, Object> paramMap);
}
