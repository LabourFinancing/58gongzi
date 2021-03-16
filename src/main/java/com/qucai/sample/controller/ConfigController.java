package com.qucai.sample.controller;

import java.util.HashMap;

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
import com.qucai.sample.entity.Config;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.req.ConfigReq;
import com.qucai.sample.service.ConfigService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.Tool;

@Controller
@RequestMapping(value = "/configController")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @ModelAttribute
    public Config get(@RequestParam(required = false) String id) {
        Config entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = configService.selectByPrimaryKey(id);
        }
        if (entity == null) {
            entity = new Config();
        }
        return entity;
    }

    @RequestMapping(value = { "configList", "" })
    public String configList(ConfigReq configReq, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        PageParam pp = Tool.genPageParam(request);
        PageInfo<Config> page = configService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page);

        return "config/configList";
    }

    @RequestMapping(value = "form")
    public String form(String id, String operationType,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        if (OperationTypeConstant.NEW.equals(operationType)) {
            return "config/configNewForm";
        } else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Config config = configService.selectByPrimaryKey(id);
            model.addAttribute("config", config);
            return "config/configEditForm";
        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "config/configViewForm";
        } else {
            return "redirect:/configController/configList";
        }
    }
    
    @RequestMapping(value = "addConfig")
    @ResponseBody
    public String addConfig(Config config, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        config.setId(Tool.uuid());
        configService.insertSelective(config);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
    @RequestMapping(value = "deleteConfig")
    public String deleteConfig(String id, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        configService.deleteByPrimaryKey(id);
        return "redirect:/configController/configList";
    }
    
    @RequestMapping(value = "editConfig")
    @ResponseBody
    public String editConfig(Config config, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        configService.updateByPrimaryKeySelective(config);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
}
