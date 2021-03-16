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
import com.qucai.sample.entity.Role;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ResourceService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.ResourceGrant;

@Controller
@RequestMapping(value = "/roleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute
    public Role get(@RequestParam(required = false) String id) {
        Role entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = roleService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new Role();
        }
        return entity;
    }

    @RequestMapping(value = "roleList")
    public String roleList(Role role, @RequestParam( defaultValue = "0" ) Integer platform, 
    		HttpServletRequest request, HttpServletResponse response, Model model) {
        PageParam pp = Tool.genPageParam(request);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("platform", platform);
        PageInfo<Role> page = roleService.findAllList(paramMap, pp);
        model.addAttribute("page", page);
        model.addAttribute("platform", platform);
        if(0 == platform) {
    		return "role/roleList";
    	} else if(1 == platform) {
    		return "role/roleEntList";
    	} else if(2 == platform) {
    		//个人端，暂时不考虑
    		return "role/roleList";
    	}else {
    		return "role/roleList";
    	}
    }

    @RequestMapping(value = "form")
    public String form(String id, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	model.addAttribute("platform", platform);
    	
        if (OperationTypeConstant.NEW.equals(operationType)) {
            return "role/roleNewForm";
        } else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Role role = roleService.selectByPrimaryKey(id);
            model.addAttribute("role", role);
            return "role/roleEditForm";
        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "role/roleViewForm";
        } else {
            return "redirect:/roleController/roleList?platform=" + platform;
        }
    }

    @RequestMapping(value = "addRole")
    @ResponseBody
    public String addRole(Role role, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        role.setCreator(ShiroSessionUtil.getLoginSession().getId());
        role.setCreateTime(new Date());
        role.setId(Tool.uuid());
        roleService.insertSelective(role);

        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteRole")
    public String deleteRole(String id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        roleService.deleteByPrimaryKey(id);
        return "redirect:/roleController/roleList?platform=" + platform;
    }

    @RequestMapping(value = "editRole")
    @ResponseBody
    public String editRole(Role role, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        role.setModifier(ShiroSessionUtil.getLoginSession().getId());
        role.setModifyTime(new Date());
        roleService.updateByPrimaryKeySelective(role);

        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "grantResourcePage")
    public String grantResourcePage(String roleId, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        List<ResourceGrant> resourceGrantList = resourceService
                .findGrantTreetableList(roleId, platform);
        model.addAttribute("resourceGrantList", resourceGrantList);
        model.addAttribute("roleId", roleId);
        return "role/grantResourcePage";
    }

    @RequestMapping(value = "grantResource")
    @ResponseBody
    public String grantResource(String roleId, String resourceIds,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        roleService.grantResource(roleId, resourceIds);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

}
