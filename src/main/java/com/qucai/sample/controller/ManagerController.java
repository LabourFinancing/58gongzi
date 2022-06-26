package com.qucai.sample.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.RoleGrant;

@Controller
@RequestMapping(value = "/managerController")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @ModelAttribute
    public Manager get(@RequestParam(required = false) String id) {
        Manager entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = managerService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new Manager();
        }
        return entity;
    }

    @RequestMapping(value = { "managerList", "" })
    public String managerList(Manager manager, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        
        PageParam pp = Tool.genPageParam(request);
        
        PageInfo<Manager> page = managerService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page);

        return "manager/managerList";
    }
    
    @RequestMapping(value = "managerSearchList")
    public String personalInfoSearchList(Manager manager, @RequestParam( defaultValue = "0" ) Integer platform,String real_name,String company_name,
    		String mobile,String personalid,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	
    	if (real_name != "" | mobile != "" || company_name != "" || personalid != "") {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("real_name", real_name);//添加元素
        	paramSearchMap.put("company_name", company_name);//添加元素
        	paramSearchMap.put("mobile", mobile);//添加元素
            paramSearchMap.put("personalid",personalid);

            PageParam pp = Tool.genPageParam(request);  
            PageInfo<Manager> page = managerService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<Manager> page = managerService.findAllList(new HashMap<String, Object>(), pp);
            model.addAttribute("page", page);
        }
       return "manager/managerList";
    }

    @RequestMapping(value = "form")
    public String form(String id, String operationType,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        if (OperationTypeConstant.NEW.equals(operationType)) {
        	if( t_P_Company.equals("ALL")){
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
        	else{
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
            return "manager/managerNewForm";
        }else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Manager manager = managerService.selectByPrimaryKey(id);
        	if( t_P_Company.equals("ALL")){
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
        	else{
        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
        		}
            model.addAttribute("manager", manager);
            return "manager/managerEditForm";
        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "manager/managerViewForm";
        } 
          else {
            return "redirect:/managerController/managerList";
        } 
    }
    
    @RequestMapping(value = "passwordEdit")
    public String form(String operationType,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
  	  String personalID = ShiroSessionUtil.getLoginSession().getTelephone();
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
        Manager manager = managerService.selectByPersonalID(personalID);
        model.addAttribute("manager", manager);
        return "manager/managerPWDReset";

    }
    
    @RequestMapping(value = "managerPWDReset")
    @ResponseBody
    public String form(Manager manager, HttpServletRequest request,String passwordNew, String passwordNewReinput,String password,String telephone,
            HttpServletResponse response, Model model) {

           model.addAttribute("passwordNew",passwordNew);
           model.addAttribute("passwordNewReinput",passwordNewReinput);
           if (passwordNew.equals(passwordNewReinput)){
        	 password = passwordNewReinput;
        	 telephone = ShiroSessionUtil.getLoginSession().getTelephone();
        	 Date modifyTime = new Date();
        	 manager.setPassword(passwordNewReinput);
        	 manager.setModifyTime(modifyTime);
        	 manager.setTelephone(ShiroSessionUtil.getLoginSession().getTelephone());
             managerService.updatePassword(manager);
             return JsonBizTool.genJson(ExRetEnum.UPDATEPWDSUCCESS);
		    }else{
				return JsonBizTool.genJson(ExRetEnum.PASSWORD_FAIL);
		    }
    }
           
    
    @RequestMapping(value = "addManager")
    @ResponseBody
    public String addManager(Manager manager, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        manager.setCreateTime(new Date());
        manager.setId(Tool.uuid());
        managerService.insertSelective(manager);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "SMSManager")
    @ResponseBody
    public String SMSManager(Manager manager, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        manager.setCreateTime(new Date());
        manager.setId(Tool.uuid());
        managerService.insertSelective(manager);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    

    @RequestMapping(value = "deleteManager")
    public String deleteManager(String id, String orgName,HttpServletRequest request,
            HttpServletResponse response, Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("t_O_VendorOrgName",orgName.trim());
        int countOrgUser = managerService.countOrgUser(orgName.trim());
        List<OrganizationInfo> OrganizationInfoList = organizationInfoService.findOrgName(paramMap);
        if(countOrgUser == 1 && OrganizationInfoList.size() > 0) {
            organizationInfoService.deleteByOrgName(orgName.trim());
        }
        managerService.deleteByPrimaryKey(id.trim());
        return "redirect:/managerController/managerList";
    }
    
    @RequestMapping(value = "editManager")
    @ResponseBody
    public String editManager(Manager manager, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        managerService.updateByPrimaryKeySelective(manager);
        
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
    @RequestMapping(value = "grantRolePage")
    public String grantRolePage(String managerId, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<RoleGrant> roleGrantList = roleService.findManagerRoleGrantAllList(managerId);
        model.addAttribute("roleGrantList", roleGrantList);
        model.addAttribute("managerId", managerId);
        return "manager/grantRolePage";
    }
    
    @RequestMapping(value = "grantRole")
    @ResponseBody
    public String grantRole(String managerId, String roleIds,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        managerService.grantRole(managerId, roleIds);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
}
