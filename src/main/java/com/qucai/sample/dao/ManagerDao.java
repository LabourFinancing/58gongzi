package com.qucai.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qucai.sample.entity.Manager;

@Repository
public interface ManagerDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(String id);
    
	Manager selectByPersonalID(String personalID);
    
    Manager selectByMobil(String mobile);

    int updateByPrimaryKeySelective(Manager record);
    
    int updateAllCompanyStaffsOn(String t_O_OrgName);
    
    int updateAllCompanyStaffsOff(String t_O_OrgName);
    
	int updatePassword(Manager record);
    
    Manager ForgotPWDByPrimaryKeySelective(@Param("userName") String userName);
    
    List<Manager> findAllList(Map<String, Object> paramMap);
    
    List<Manager> findSearchList(Map<String, Object> paramSearchMap);
    
    Manager selectByNameAndPassword(@Param("userName") String userName, @Param("password") String password);
    
    int existManagerUserName(@Param("id") String id, @Param("userName") String userName);
    
}