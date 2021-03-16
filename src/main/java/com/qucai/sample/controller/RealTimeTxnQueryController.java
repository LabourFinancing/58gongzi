package com.qucai.sample.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.ShiroSessionUtil;


@Controller
@RequestMapping(value = "/RealTimeTxnQueryController")
public class RealTimeTxnQueryController {

	
	// 必须把new HistoricalTxnQuery的列进行全面修改, 新建HistoricalTxnQueryService
	
	@Autowired
	private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象


	@ModelAttribute
	public StaffPrepayApplicationList get(
			@RequestParam(required = false) String t_txn_P_Mobil) {
		StaffPrepayApplicationList entity = null;		
		if (entity == null) {
			entity = new StaffPrepayApplicationList();
		}
		return entity;
	}

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */
    @RequestMapping(value = {"realTimeTxnQueryList",""})
    public String realTimeTxnQueryList(StaffPrepayApplicationList staffPrepayApplicationList, @RequestParam( defaultValue = "0" )  Integer platform,String t_P_Company,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	List<StaffPrepayApplicationList> staffPrepayApplicationListAll = staffPrepayApplicationService.findAllNowList(paramMap); 
        model.addAttribute("StaffPrepayApplicationListAll", staffPrepayApplicationListAll);//从数据库查询出来的结果用model的方式返回
    	return "historicalTxnQuery/RealTimeTxnQueryList";
    }
    
    @RequestMapping(value = "realTimeTxnQuerySearchList")
    public String historicalTxnQuerySearchList(StaffPrepayApplicationList staffPrepayApplicationList, @RequestParam( defaultValue = "0" )  Integer platform,Date begin_date,Date end_date,
    		Date begin_date_input,Date end_date_input,String t_Txn_Mobil,String t_P_Company,String t_Txn_Paystatus,String SeesionLoginMobil,String t_Txn_PrepayApplierName,
    	HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_Txn_Paystatus", t_Txn_Paystatus);
    	model.addAttribute("t_Txn_PrepayApplierName", t_Txn_PrepayApplierName);
    	model.addAttribute("t_Txn_Mobil", t_Txn_Mobil);
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
    	   	
        	if(t_P_Company.equals("ALL") || t_Txn_PrepayApplierName != null) {
        		paramSearchMap.put("t_Txn_Paystatus", t_Txn_Paystatus);
        		paramSearchMap.put("SeesionLoginMobil", t_Txn_Mobil);
            	paramSearchMap.put("t_Txn_PrepayApplierName", t_Txn_PrepayApplierName);//添加元素
        	}else {
            	SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
            	paramSearchMap.put("SeesionLoginMobil", ShiroSessionUtil.getLoginSession().getMobile());//添加元素
            	paramSearchMap.put("t_Txn_Paystatus", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
            	paramSearchMap.put("t_Txn_PrepayApplierName", ShiroSessionUtil.getLoginSession().getRealName());//添加元素
        	}
        	List<StaffPrepayApplicationList> staffPrepayApplicationListAll = staffPrepayApplicationService.findRealTimeSearchList(paramSearchMap); 

            model.addAttribute("StaffPrepayApplicationListAll", staffPrepayApplicationListAll);//从数据库查询出来的结果用model的方式返回
    		if(0 == platform) {
         		return "historicalTxnQuery/RealTimeTxnQueryList";
        	}else {
        		return "historicalTxnQuery/RealTimeTxnQueryList";
        	}
        }

}
