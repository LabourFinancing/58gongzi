package com.qucai.sample.controller;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.AmtQueryServlet;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.entity.TreasuryInfo;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.service.TreasuryDBInfoService;
import com.qucai.sample.service.TreasuryInfoService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@Controller
@RequestMapping(value = "/TreasuryInfoController")
public class TreasuryInfoController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private TreasuryInfoService treasuryInfoService; //申明一个对象
	
    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象
    
    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象
    
    @Autowired 
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @ModelAttribute
    public TreasuryInfo get(@RequestParam(required = false) String t_Treasury_ID) {
    	TreasuryInfo entity = null;
        if (StringUtils.isNotBlank(t_Treasury_ID)) {
            entity = treasuryInfoService.selectByPrimaryKey(t_Treasury_ID);//用TreasuryInfoService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new TreasuryInfo();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */
    
    @RequestMapping(value = {"treasuryInfoPaymentList",""})
    public String treasuryInfoList(TreasuryInfo treasuryInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_Treasury_ID,String t_Treasury_OrgName,
    		String SessionCompanyName, HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);
        
        paramMap.put("t_Treasury_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());//添加元素
        t_Treasury_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        PageInfo<TreasuryInfo> page = treasuryInfoService.findAllList(paramMap, pp);
        model.addAttribute("page", page);

    	return "treasuryInfo/treasuryInfoPaymentList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "treasuryInfoPaymentSearchList")
    public String treasuryInfoSearchList(TreasuryInfo treasuryInfo, @RequestParam( defaultValue = "0" )  Integer platform,String t_Treasury_ID,String t_Treasury_OrgName,
    		String t_Treasury_Remark,Date create_time,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	
    	if (t_Treasury_OrgName != "" | t_Treasury_Remark != "" | create_time != null) {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_Treasury_OrgName", t_Treasury_OrgName);//添加元素	
        	paramSearchMap.put("t_Treasury_Remark", t_Treasury_Remark);//添加元素
        	paramSearchMap.put("create_time", create_time);//添加元素
        	if (t_Treasury_OrgName != "ALL"){
            	paramSearchMap.put("t_Treasury_OrgName", t_Treasury_OrgName);//添加元素
        	}
            	else {
            		paramSearchMap.put("t_Treasury_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());
        	}
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<TreasuryInfo> page = treasuryInfoService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
    		Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
    		paramMap.put("t_Treasury_OrgName", ShiroSessionUtil.getLoginSession().getCompany_name());
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<TreasuryInfo> page = treasuryInfoService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "treasuryInfo/treasuryInfoPaymentList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "treasuryInfo/treasuryInfoPaymentList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(TreasuryInfo treasuryInfo,OrganizationInfo organizationInfo,String t_Treasury_ID, String operationType, Integer platform,
            HttpServletRequest request, HttpServletResponse response,String t_Treasury_OrgName,
            Model model) {
       	  model.addAttribute("platform", platform);
//    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
//        List<FinanceProduct> financeProductList = financeProductService.findAllList(paramMap); //上数据库查询的list树的结果,查询结果赋值与parentfinanceProductList
//        model.addAttribute("financeProductList", financeProductList); //返回到页面上
        
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        	 
        	 treasuryInfo.setT_Treasury_OrgName(ShiroSessionUtil.getLoginSession().getCompany_name());
        	 List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
        	 model.addAttribute("OrganizationInfo", OrganizationInfo);
        	return "treasuryInfo/treasuryInfoNewForm";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "treasuryInfo/treasuryInfoViewForm";
          } else if (OperationTypeConstant.EDIT.equals(operationType)) {
              return "treasuryInfo/treasuryInfoEditForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "treasuryInfo/treasuryInfoViewList";	
          } else {
            return "treasuryInfo/treasuryInfoList";	
        }
    }
    
	@RequestMapping(value = "addTreasuryPaymentInfo")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addTreasuryInfo(TreasuryInfo treasuryInfo,TreasuryDBInfo treasuryDBInfoNew,HttpServletRequest request,String platform,Date modify_time,
    		String t_TreasuryDB_ID, String t_TreasuryDB_OrgName, BigDecimal t_TreasuryDB_Balance,HttpServletResponse response, Model model) throws Exception {
    	model.addAttribute("platform", platform);
    	treasuryInfo.setCreator(ShiroSessionUtil.getLoginSession().getId());
    	treasuryInfo.setCreate_time(new Date());
    	treasuryInfo.setT_Treasury_ID(Tool.uuid());
    	if (modify_time == null){
    		treasuryInfo.setModify_time(new Date());
    	};
//check existing TreasuryDBInfoService OrgName or not
    	int rs_PaymentNew = treasuryInfoService.insertSelective(treasuryInfo);
    	
//更新所有企业总授信   	
    	if (rs_PaymentNew == 1){
//初始化TresuryDBinfo
        	TreasuryDBInfo entity = null;
        	entity = new TreasuryDBInfo();
    		  t_TreasuryDB_OrgName = treasuryInfo.getT_Treasury_OrgName();
    		      	OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_TreasuryDB_OrgName);
		String merchantId = AgencyOrgnization.getT_O_OrgPayrollBankaccount();
    	String RCretData = null;
		BigDecimal ebibalance = null;
    	if (merchantId != null){
    		   String retData = AmtQueryServlet.main(merchantId);
    		   System.out.println("Query Chinaebipay String:");
    		   System.out.println(retData);
    		   JSONObject obj = (JSONObject) JSON.parse(retData);
    		   String balanceQuery = (String) obj.get("transAmt"); //  Chinaebipay branch
    		   ebibalance = (new BigDecimal(balanceQuery)).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_DOWN);
    		   System.out.println("Query Chinaebipay balance:");
    		   System.out.println(balanceQuery);
    		   System.out.println("BigDecimal balance:");
    		   System.out.println(ebibalance);
    	}
              boolean rs = treasuryDBInfoService.existTreasuryDBInfoName(t_TreasuryDB_OrgName);
              
           if(rs){
        	   // update existing org statistic
        	   TreasuryDBInfo treasuryDBInfoUpdate = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName);
        	   BigDecimal tTreasuryDBBalance = treasuryDBInfoUpdate.getT_TreasuryDB_Balance().add(treasuryInfo.getT_Treasury_PayAmount());
        	   treasuryDBInfoUpdate.setT_TreasuryDB_Balance(tTreasuryDBBalance);    
        	   treasuryDBInfoUpdate.setPlatform(merchantId);
        	   treasuryDBInfoUpdate.setT_TreasuryDB_BoPRatio(ebibalance);    
        	   treasuryDBInfoUpdate.setModifier(ShiroSessionUtil.getLoginSession().getId());    
        	   treasuryDBInfoUpdate.setModify_time(new Date());  
               int UpdateStatistic = treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdate);
               if (UpdateStatistic == 1) {         	   
            	   String t_TreasuryDB_OrgName_Overall = "ALL";
            	   TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName_Overall);
            	   BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().add(treasuryInfo.getT_Treasury_PayAmount());
            	   treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
            	   treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
                   return JsonBizTool.genJson(ExRetEnum.SUCCESS);
               }
              } 
              else {
            // add new org into treasuryDB
               treasuryDBInfoNew.setCreator(ShiroSessionUtil.getLoginSession().getId());
               treasuryDBInfoNew.setT_TreasuryDB_ID(Tool.uuid());
               treasuryDBInfoNew.setModifier(ShiroSessionUtil.getLoginSession().getId());
               treasuryDBInfoNew.setT_TreasuryDB_Balance(treasuryInfo.getT_Treasury_PayAmount());
               treasuryDBInfoNew.setT_TreasuryDB_TotAmtMth(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_BoPRatio(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_PoPRatio(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_RiskMargin(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_Prooffund(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_TotAmtDaily(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_TotAmtDailyFail(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_TotAmtDailySucc(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_Fee(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_OverdueInt(BigDecimal.valueOf(0.00));
               treasuryDBInfoNew.setT_TreasuryDB_Comment("支付更新");
               treasuryDBInfoNew.setPlatform(platform);
               treasuryDBInfoNew.setRemark("");	
               treasuryDBInfoNew.setCreate_time(new Date());
               treasuryDBInfoNew.setModify_time(new Date());
               treasuryDBInfoNew.setT_TreasuryDB_OrgName(treasuryInfo.getT_Treasury_OrgName());
               treasuryDBInfoNew.setT_TreasuryDB_Comment("新企业充值，自动添加");
               int AddNewPay = treasuryDBInfoService.insertSelective(treasuryDBInfoNew);
               
               if (AddNewPay == 1) {
            	   String t_TreasuryDB_OrgName_Overall = "ALL";
            	   TreasuryDBInfo treasuryDBInfoUpdateOverall = treasuryDBInfoService.findOrgTreasuryCurrBalance(t_TreasuryDB_OrgName_Overall);
            	   BigDecimal tTreasuryDBBalanceOverall = treasuryDBInfoUpdateOverall.getT_TreasuryDB_Balance().add(treasuryInfo.getT_Treasury_PayAmount());
            	   treasuryDBInfoUpdateOverall.setT_TreasuryDB_Balance(tTreasuryDBBalanceOverall);
            	   treasuryDBInfoService.updateByPrimaryKeySelective(treasuryDBInfoUpdateOverall);
                   return JsonBizTool.genJson(ExRetEnum.SUCCESS);
               }
              }
    	}
          else {
              return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
    @RequestMapping(value = "deleteTreasuryInfo")
    public String deletetreasuryInfo(String t_Treasury_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	treasuryInfoService.deleteByPrimaryKey(t_Treasury_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/TreasuryInfoController/treasuryInfoPaymentList?platform="+platform;
    }
    
    @RequestMapping(value = "editTreasuryInfo")
    @ResponseBody
    public String edittreasuryInfo(TreasuryInfo treasuryInfo, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model ) {
    	treasuryInfo.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	treasuryInfo.setModify_time(new Date());
    	treasuryInfoService.updateByPrimaryKey(treasuryInfo);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
}
