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

import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.entity.Resource;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ResourceService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;

@Controller
@RequestMapping(value = "/resourceController")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute
    public Resource get(@RequestParam(required = false) String id) {
        Resource entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = resourceService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new Resource();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源
     */
    @RequestMapping(value = "resourceList")
    public String resourceList(Resource resource, @RequestParam( defaultValue = "0" ) Integer platform, 
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	Map<String, Object> paraMap = new HashMap<String, Object>();
    	paraMap.put("platform", platform);
    	List<Resource> resourceList = resourceService.findTreetableList(paraMap);
		model.addAttribute("resourceList", resourceList);
		model.addAttribute("platform", platform);
    	if(0 == platform) {
    		return "resource/resourceList";
    	} else if(1 == platform) {
    		return "resource/resourceEntList";
    	} else if(2 == platform) {
    		//个人端，暂时不考虑
    		return "resource/resourceList";
    	}else {
    		return "resource/resourceList";
    	}
    }
    
    @RequestMapping(value = "form")
    public String form(String id, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	model.addAttribute("platform", platform);
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("typeEnd", 1);
        paramMap.put("platform", platform);
        List<Resource> parentResourceList = resourceService.findTreetableList(paramMap);
        model.addAttribute("parentResourceList", parentResourceList);
        
        if (OperationTypeConstant.NEW.equals(operationType)) {
            return "resource/resourceNewForm";
        } else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Resource resource = resourceService.selectByPrimaryKey(id);
            model.addAttribute("resource", resource);
            return "resource/resourceEditForm";
        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "resource/resourceViewForm";
        } else {
            return "redirect:/resourceController/resourceList?platform=" + platform;
        }
    }
    
    @RequestMapping(value = "addResource")
    @ResponseBody
    public String addResource(Resource resource, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        resource.setCreator(ShiroSessionUtil.getLoginSession().getId());
        resource.setCreateTime(new Date());
        resource.setId(Tool.uuid());
        resourceService.insertSelective(resource);
        
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteResource")
    public String deleteResource(String id, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        resourceService.deleteByPrimaryKey(id);
        return "redirect:/resourceController/resourceList?platform="+platform;
    }
    
    @RequestMapping(value = "editResource")
    @ResponseBody
    public String editResource(Resource resource, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        resource.setModifier(ShiroSessionUtil.getLoginSession().getId());
        resource.setModifyTime(new Date());
        resourceService.updateByPrimaryKeySelective(resource);
        
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
}
