package com.qucai.sample.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qucai.sample.vo.PersonalTxnStatic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.entity.HistoricalTxnQuery;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.HistoricalTxnQueryService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.JsonTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@Controller
@RequestMapping(value = "/HistoricalTxnQueryController")
public class HistoricalTxnQueryController<HisTxnSelectedIDs> {


    // 必须把new HistoricalTxnQuery的列进行全面修改, 新建HistoricalTxnQueryService

    @Autowired
    private HistoricalTxnQueryService historicalTxnQueryService; //申明一个对象
    
    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象
    
    
    @Autowired
    private StaffPrepayApplicationService staffPrepayApplicationService; //申明一个对象


    @ModelAttribute
    public HistoricalTxnQuery get(@RequestParam(required = false) String t_Txn_ID_his) {
        HistoricalTxnQuery entity = null;
        PersonalTxnStatic personalTxnStatic = null;
        if (StringUtils.isNotBlank(t_Txn_ID_his)) {
            entity = historicalTxnQueryService.selectByPrimaryKey(t_Txn_ID_his);
        }
        if (entity == null) {
            entity = new HistoricalTxnQuery();
            new PersonalTxnStatic();
        }
        return entity;
    }

    /**
     * 改动：根据所属平台来确定是哪个平台的资源
     */
    @RequestMapping(value = {"historicalTxnQueryList", ""})
    public String historicalTxnQueryList(HistoricalTxnQuery historicalTxnQuery, @RequestParam(defaultValue = "0") Integer platform, String t_P_Company,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {

//        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
//        PageParam pp = Tool.genPageParam(request);      
//        t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
//        List<HistoricalTxnQuery> historicalTxnQueryAll = historicalTxnQueryService.findAllList(paramMap);
//        PageInfo<HistoricalTxnQuery> page = historicalTxnQueryService.findAllList(paramMap, pp);
//        model.addAttribute("page", page);
        String host = Tool.BrowserFilter(request);
        if (host.equals("P")){
            return "historicalTxnQuery/historicalTxnQueryList";	
        }else{
        	return "historicalTxnQuery/historicalTxnQueryListMobile";
        }

    }
    
    @RequestMapping(value = "historicalTxnQuerySearchList")
    public String historicalTxnQuerySearchList(HistoricalTxnQuery historicalTxnQuery, @RequestParam( defaultValue = "0" )  Integer platform, String startTime,String endTime,
    		Date begin_date,Date end_date,Date begin_date_input,Date end_date_input,String t_P_Company,String t_P_Company_his,String SeesionLoginMobil,
    		String t_Txn_PrepayApplierName_his,String t_Txn_PrepayClear_his,String t_Txn_ProdName_his,
    		String t_TreasuryDB_OrgName,String t_P_VendorEmployeeName_his,String t_O_OrgName,
    	HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	
    	model.addAttribute("startTime", startTime);
    	model.addAttribute("endTime", endTime);

    	if (startTime != null & endTime != null) {
    		
        	begin_date = new SimpleDateFormat("yyyy/MM/dd").parse(startTime);
            end_date = new SimpleDateFormat("yyyy/MM/dd").parse(endTime);
        	
        	model.addAttribute("begin_date_input", begin_date);
        	model.addAttribute("end_date_input", end_date);
        	model.addAttribute("begin_date", startTime);
        	model.addAttribute("end_date", endTime);
    	}else {
        	model.addAttribute("begin_date_input", begin_date);
        	model.addAttribute("end_date_input", end_date);
        	model.addAttribute("begin_date", begin_date);
        	model.addAttribute("end_date", end_date);
    	}
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);
    	model.addAttribute("t_P_Company_his", t_P_Company_his);
    	model.addAttribute("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
    	model.addAttribute("t_Txn_PrepayClear_his",t_Txn_PrepayClear_his);
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
    	
    	if (begin_date != null & end_date != null) {
        	paramSearchMap.put("begin_date", begin_date);//添加元素
        	paramSearchMap.put("end_date", end_date);//添加元素        	
        	if(t_P_Company_his != null || t_Txn_PrepayApplierName_his != null || t_P_VendorEmployeeName_his != null || t_Txn_PrepayClear_his != null || t_Txn_ProdName_his != null) {
    		    paramSearchMap.put("SeesionLoginMobil", null);
        	    paramSearchMap.put("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);//添加元素
        		if (t_P_Company.equals("ALL")) {
        		    paramSearchMap.put("t_P_Company_his", t_P_Company_his);
              		paramSearchMap.put("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
              		paramSearchMap.put("t_Txn_PrepayClear_his",t_Txn_PrepayClear_his);
              		paramSearchMap.put("t_Txn_ProdName_his",t_Txn_ProdName_his);
        		}else {
        			 OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
        		        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                    	  paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
                  		  paramSearchMap.put("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
                  		  paramSearchMap.put("t_Txn_ProdName_his",t_Txn_ProdName_his);
        		        }else {
            		        paramSearchMap.put("t_P_VendorEmployeeName_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
                        	paramSearchMap.put("t_P_Company_his", t_P_Company_his);//添加元素//添加元素
                      		paramSearchMap.put("t_Txn_ProdName_his",t_Txn_ProdName_his);
        		        }
    		    }
        	  }else{
        		String SessionPID = ShiroSessionUtil.getLoginSession().getTelephone();
              	paramSearchMap.put("SessionPID", SessionPID);//添加元素
              	paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
              	paramSearchMap.put("t_Txn_PrepayApplierName_his", ShiroSessionUtil.getLoginSession().getRealName());//添加元素
        	  }
        	}
    	
    	    List<HistoricalTxnQuery> historicalTxnQuerySearch = historicalTxnQueryService.findSearchList(paramSearchMap); 
            model.addAttribute("HistoricalTxnQuery", historicalTxnQuerySearch);//从数据库查询出来的结果用model的方式返回
            return "historicalTxnQuery/historicalTxnQueryList";	

        }
    
    @RequestMapping(value = "historicalTxnQuerySearchListMobile")
    @ResponseBody
    public String historicalTxnQuerySearchListMobile(HistoricalTxnQuery historicalTxnQuery, @RequestParam( defaultValue = "0" )  Integer platform, Integer pages, Integer sizes,String startTime,String endTime,
    		Date begin_date,Date end_date,Date begin_date_input,Date end_date_input,String t_P_Company,String t_P_Company_his,String SeesionLoginMobil,
    		String t_Txn_PrepayApplierName_his,String t_Txn_PrepayClear_his,
    		String t_TreasuryDB_OrgName,String t_P_VendorEmployeeName_his,String t_O_OrgName,
    	HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
    	t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name().trim(); 
    	t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
    	
    	model.addAttribute("startTime", startTime);
    	model.addAttribute("endTime", endTime);
    	model.addAttribute("pages", pages);
    	model.addAttribute("sizes", sizes);
    	System.out.println(startTime);
    	System.out.println(endTime);

    	if (startTime != null & endTime != null) {
    		
        	begin_date = new SimpleDateFormat("yyyy/MM/dd").parse(startTime);
            end_date = new SimpleDateFormat("yyyy/MM/dd").parse(endTime);
        	
        	model.addAttribute("begin_date_input", begin_date);
        	model.addAttribute("end_date_input", end_date);
        	model.addAttribute("begin_date", startTime);
        	model.addAttribute("end_date", endTime);
    	}else {
        	model.addAttribute("begin_date_input", begin_date);
        	model.addAttribute("end_date_input", end_date);
        	model.addAttribute("begin_date", begin_date);
        	model.addAttribute("end_date", end_date);
    	}
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	model.addAttribute("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);
    	model.addAttribute("t_P_Company_his", t_P_Company_his);
    	model.addAttribute("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
    	model.addAttribute("t_Txn_PrepayClear_his",t_Txn_PrepayClear_his);
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
    	
    	if (begin_date != null & end_date != null) {
        	paramSearchMap.put("begin_date", begin_date);//添加元素
        	paramSearchMap.put("end_date", end_date);//添加元素        	
        	if(t_P_Company_his != null || t_Txn_PrepayApplierName_his != null || t_P_VendorEmployeeName_his != null || t_Txn_PrepayClear_his != null) {
    		    paramSearchMap.put("SeesionLoginMobil", null);
        	    paramSearchMap.put("t_Txn_PrepayApplierName_his", t_Txn_PrepayApplierName_his);//添加元素
        		if (t_P_Company.equals("ALL")) {
        		    paramSearchMap.put("t_P_Company_his", t_P_Company_his);
              		paramSearchMap.put("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
              		paramSearchMap.put("t_Txn_PrepayClear_his",t_Txn_PrepayClear_his);
        		}else {
        			 OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);
        		        if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                    	  paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
                  		  paramSearchMap.put("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
        		        }else {
            		        paramSearchMap.put("t_P_VendorEmployeeName_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
                        	paramSearchMap.put("t_P_Company_his", t_P_Company_his);//添加元素//添加元素
        		        }
    		    }
        	  }else{
        		String SessionPID = ShiroSessionUtil.getLoginSession().getTelephone();
              	paramSearchMap.put("SessionPID", SessionPID);//添加元素
              	paramSearchMap.put("t_P_Company_his", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素//添加元素
              	paramSearchMap.put("t_Txn_PrepayApplierName_his", ShiroSessionUtil.getLoginSession().getRealName());//添加元素
        	  }
        	}
    	
    	    List<HistoricalTxnQuery> historicalTxnQuerySearch = historicalTxnQueryService.findSearchList(paramSearchMap); 
            return JsonTool.genByFastJson(historicalTxnQuerySearch);
        }
    
    @RequestMapping(value = "form")
    public String TxnDetails(@RequestParam(required = false) String t_Txn_ID_his,HttpServletRequest request,HttpServletResponse response,
    		HistoricalTxnQuery historicalTxnQuery,Model model) throws IOException{
    	historicalTxnQuery = historicalTxnQueryService.selectByPrimaryKey(t_Txn_ID_his);
    	model.addAttribute("historicalTxnQuery",historicalTxnQuery);
        return "historicalTxnQuery/txnListDetail";
     }
    
    @RequestMapping(value = "ClearPayList")
    @ResponseBody
    public String ClearPrepay(HttpServletRequest request,HttpServletResponse response
    	) throws IOException{
    	
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
    	
    	String updater = ShiroSessionUtil.getLoginSession().getUserName();
    	String[] HisTxnSelectedIDs = request.getParameterValues("HisTxnSelectedIDs");
    	
        	int RS = historicalTxnQueryService.ClearHisTxnPay(HisTxnSelectedIDs); 

    		if(RS == 0) {
			      return JsonBizTool.genJson(ExRetEnum.CLEAR_FAILED);
        	}else {
			      return JsonBizTool.genJson(ExRetEnum.CLEAR_SUCCESS);
        	}
        }
    
    @RequestMapping(value = "DuePayList")
    @ResponseBody
    public String DuePrepay(
    	HttpServletRequest request, HttpServletResponse response, Model model) {
    	String updater = ShiroSessionUtil.getLoginSession().getUserName();
    	String[] HisTxnSelectedIDs = request.getParameterValues("HisTxnSelectedIDs");

        	int RS = historicalTxnQueryService.DueHisTxnPay(HisTxnSelectedIDs); 

    		if(RS == 0) {
			      return JsonBizTool.genJson(ExRetEnum.DUE_FAILED);
        	}else {
			      return JsonBizTool.genJson(ExRetEnum.DUE_SUCCESS);
        	}
        }
    
    @RequestMapping(value = {"exceptTxnList", ""})
    public String exceptTxnList(StaffPrepayApplicationPayment staffPrepayApplicationPayment, @RequestParam(defaultValue = "0") Integer platform, String company,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        company = ShiroSessionUtil.getLoginSession().getCompany_name();
        if (company.equals("ALL")){
        	paramMap.put("company", null);
        }else{
        	paramMap.put("company", company);
        }
    	List<StaffPrepayApplicationPayment> StaffPrepayApplicationPaymentFailed = staffPrepayApplicationService.findFailedPaymentList(paramMap);

        model.addAttribute("StaffPrepayApplicationPaymentFailed", StaffPrepayApplicationPaymentFailed);//从数据库查询出来的结果用model的方式返回
        
        return "historicalTxnQuery/exceptTxnList";
    }
    
    @RequestMapping(value = "exceptTxnSearchListSearchList")
    public String exceptTxnSearchListSearchList(StaffPrepayApplicationPayment staffPrepayApplicationPayment, @RequestParam( defaultValue = "0" ) String name,String company,
    	HttpServletRequest request, HttpServletResponse response, Model model) {
        
    	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
    	    if (name != "") {
        	    paramSearchMap.put("name", name);
    	    }else{
    	    	paramSearchMap.put("name", null);//添加元素
    	    }
    	    if (company != "") {
    	    	paramSearchMap.put("company", company);
    	    }else{
            	paramSearchMap.put("company", null);	
    	    }
        	List<StaffPrepayApplicationPayment> StaffPrepayApplicationPaymentFailed = staffPrepayApplicationService.findSearchFailedPaymentList(paramSearchMap);

            model.addAttribute("StaffPrepayApplicationPaymentFailed", StaffPrepayApplicationPaymentFailed);//从数据库查询出来的结果用model的方式返回
        	return "historicalTxnQuery/exceptTxnList";
        	
        }
        
        
    @RequestMapping(value = "personalTxnStaticSearchList")
    public String exceptTxnList(@RequestParam( defaultValue = "0" )
        Integer platform, Integer pages, Integer sizes,String startTime,String endTime,
                                Date begin_date,Date end_date,String t_P_Company_his,
                                String t_Txn_PrepayApplierName_his,String t_Txn_PrepayClear_his,String t_Txn_ProdName_his,
                                String t_TreasuryDB_OrgName,String t_P_VendorEmployeeName_his,String t_O_OrgName,
                                HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        new PersonalTxnStatic();
        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        String company = ShiroSessionUtil.getLoginSession().getCompany_name();

        paramMap.put("begin_date",begin_date);
        paramMap.put("end_date",end_date);
        if (company.equals("ALL")){
            if(t_P_Company_his != null){
                paramMap.put("t_P_Company_his", t_P_Company_his);
            }
            paramMap.put("t_P_VendorEmployeeName_his",t_P_VendorEmployeeName_his);
            paramMap.put("t_Txn_PrepayClear_his",t_Txn_PrepayClear_his);
            paramMap.put("t_Txn_PrepayApplierName_his",t_Txn_PrepayApplierName_his);
            paramMap.put("t_Txn_ProdName_his",t_Txn_ProdName_his);
        }else{
            paramMap.put("t_P_Company_his", company);
        }
        
        List<PersonalTxnStatic> PersonalTxnStaticList = historicalTxnQueryService.SearchPersonalTxnStatic(paramMap);

        model.addAttribute("PersonalTxnStaticList", PersonalTxnStaticList);//从数据库查询出来的结果用model的方式返回
        return "historicalTxnQuery/personalTxnStatics";
    }
}
