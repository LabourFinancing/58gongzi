//package com.qucai.sample.controller;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.struts2.ServletActionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.qucai.sample.entity.HistoricalTxnQuery;
//import com.qucai.sample.service.HistoricalTxnQueryService;
//import com.qucai.sample.util.MyFileUtils;
//import com.qucai.sample.util.ShiroSessionUtil;
//
//
//@Controller
//@RequestMapping(value = "/HistoricalTxnQueryController")
//public class DeploymentController {
//
//	
//	// 必须把new HistoricalTxnQuery的列进行全面修改, 新建HistoricalTxnQueryService
//	
//    @Autowired
//    private HistoricalTxnQueryService historicalTxnQueryService; //申明一个对象
//
//    @ModelAttribute
//    public HistoricalTxnQuery get(@RequestParam(required = false) String t_Txn_ID_his) {
//    	HistoricalTxnQuery entity = null;
//    	 if (StringUtils.isNotBlank(t_Txn_ID_his)) {
//             entity = historicalTxnQueryService.selectByPrimaryKey(t_Txn_ID_his);
//         }
//    	if (entity == null) {
//        entity = new HistoricalTxnQuery();
//    	}
//        return entity;
//    }
//
//    /**
//     *  改动：根据所属平台来确定是哪个平台的资源 
//     */
//    @RequestMapping(value = {"historicalTxnQueryList",""})
//    public String historicalTxnQueryList(HistoricalTxnQuery historicalTxnQuery, @RequestParam( defaultValue = "0" )  Integer platform,String t_P_Company,
//    		HttpServletRequest request, HttpServletResponse response, Model model) {
//    	
//    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
////        PageParam pp = Tool.genPageParam(request);      
//        t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
//    	List<HistoricalTxnQuery> historicalTxnQueryAll = historicalTxnQueryService.findAllList(paramMap) ; 
////        PageInfo<HistoricalTxnQuery> page = historicalTxnQueryService.findAllList(paramMap, pp);
////        model.addAttribute("page", page);
//
//    	return "historicalTxnQuery/historicalTxnQueryList";
//    }
//    
//    @RequestMapping(value = "historicalTxnQuerySearchList")
//    public String historicalTxnQuerySearchList(HistoricalTxnQuery historicalTxnQuery, @RequestParam( defaultValue = "0" )  Integer platform,Date begin_date,Date end_date,
//    		Date begin_date_input,Date end_date_input,String t_P_Company,String t_P_Company_his,String SeesionLoginMobil,String t_Txn_PrepayApplierName_his,
//    	HttpServletRequest request, HttpServletResponse response, Model model) {
//    	
//    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
//    	model.addAttribute("begin_date_input", begin_date);
//    	model.addAttribute("end_date_input", end_date);
//    	model.addAttribute("begin_date", begin_date);
//    	model.addAttribute("end_date", end_date);
//    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
//    	model.addAttribute("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);
//    	model.addAttribute("t_P_Company_his", t_P_Company_his);
//    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
//    	
//    	if (begin_date != null & end_date != null) {
//        	paramSearchMap.put("begin_date", begin_date);//添加元素
//        	paramSearchMap.put("end_date", end_date);//添加元素        	
//        	if(t_P_Company_his != null || t_Txn_PrepayApplierName_his != null) {
//    		    paramSearchMap.put("SeesionLoginMobil", null);
//        	    paramSearchMap.put("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);//添加元素
//        		if (t_P_Company.equals("ALL")) {
//        		    paramSearchMap.put("t_P_Company_his", t_P_Company_his);
//        		}else {
//                	paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
//    		    }
//        	  }else{
//        		SeesionLoginMobil = ShiroSessionUtil.getLoginSession().getMobile();
//              	paramSearchMap.put("SeesionLoginMobil", SeesionLoginMobil);//添加元素
//              	paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
//              	paramSearchMap.put("t_Txn_PrepayApplierName_his", ShiroSessionUtil.getLoginSession().getRealName());//添加元素
//        	  }
//        	}
//        	List<HistoricalTxnQuery> historicalTxnQuerySearch = historicalTxnQueryService.findSearchList(paramSearchMap); 
//
//            model.addAttribute("HistoricalTxnQuery", historicalTxnQuerySearch);//从数据库查询出来的结果用model的方式返回
//    		if(0 == platform) {
//         		return "historicalTxnQuery/historicalTxnQueryList";
//        	}else {
//        		return "historicalTxnQuery/historicalTxnQueryList";
//        	}
//        }
//}
