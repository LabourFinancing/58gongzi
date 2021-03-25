package com.qucai.sample.controller;

import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/OrganizationDashboardController")
public class OrganizationDashboardController {


	// 必须把new financeProduct的列进行全面修改, 新建financeProductService

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @Autowired
    private ManagerService managerService; //申明一个对象

    @ModelAttribute
    public OrganizationInfo get(@RequestParam(required = false) String t_O_ID) {
    	OrganizationInfo entity = null;
        if (StringUtils.isNotBlank(t_O_ID)) {
            entity = organizationInfoService.selectByPrimaryKey(t_O_ID);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new OrganizationInfo();
        }
        return entity;
    }

    @RequestMapping(value = {"dashboard",""})
    public String showOrganizationInfo(OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )  Integer platform,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);
        
        OrganizationInfo organizationInfoDetail = organizationInfoService.selectAgencyName(ShiroSessionUtil.getLoginSession().getCompany_name());
        model.addAttribute("organizationInfoDetail", organizationInfoDetail);

        return "organizationDashboard/userCenter";
    }
}
