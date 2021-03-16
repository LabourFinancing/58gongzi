package com.qucai.sample.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.qucai.sample.entity.StaffPrepayApplicationList;
import com.qucai.sample.entity.TxnQuery;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.TxnQueryService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@Controller
@RequestMapping(value = "/PrepayApplicationListController")
public class PrepayApplicationListController {

	
	//  新建txnQueryService
	
    @Autowired
    private TxnQueryService txnQueryService; //申明一个对象

    @ModelAttribute
    public TxnQuery get(@RequestParam(required = false) String t_TxnQuery_ID,String operationType,TxnQuery entity,TxnQuery txnQuery,StaffPrepayApplicationList staffprepayApplicationList,HttpServletRequest request,
            String t_TxnQuery_PID,String t_TxnQuery_Mobil,Date t_TxnQuery_Date,HttpServletResponse response,Model model) {
    	model.addAttribute("operationType", operationType); 
    	if (operationType!= null) {
    		if (operationType.equals("edit")){
    		txnQuery = txnQueryService.selectByPrimaryKey(t_TxnQuery_ID);
    		txnQuery.setModifier(ShiroSessionUtil.getLoginSession().getId());
    		txnQuery.setModify_time(new Date());
    		txnQuery.setT_TxnQuery_ApprovalStatus("APPROVED");
        	txnQueryService.updateByPrimaryKeySelective(txnQuery);
    		} else if(operationType.equals("view")) {
    		model.addAttribute("t_TxnQuery_PID",t_TxnQuery_PID);
    	    model.addAttribute("t_TxnQuery_Mobil",t_TxnQuery_Mobil);
    	    model.addAttribute("t_TxnQuery_Date",t_TxnQuery_Date);
    	    txnQueryService.findSearchList(staffprepayApplicationList);
    		}
    	}else
        if (StringUtils.isNotBlank(t_TxnQuery_ID)) {
            entity = txnQueryService.selectByPrimaryKey(t_TxnQuery_ID); //用txnQueryService对象属性方法去调用findAllList并返回
        }
        if (entity == null) {
            entity = new TxnQuery();
        }
        return entity;
    }


    /**
     *  改动：根据所属平台来确定是哪个平台的资源 , 2017/11/14 回家改一下代码 新建产品详细页.
     */
    
    @RequestMapping(value = {"PrepayApplicationListList"})
    public String PrepayApplicationListList(TxnQuery txnQuery, @RequestParam( defaultValue = "0" )  Integer platform,Date modify_time, String actionflag,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);      
       txnQuery.sett_TxnQuery_CompanyName(ShiroSessionUtil.getLoginSession().getCompany_name());
        
        if (actionflag == "All") {
        PageInfo<TxnQuery> page = txnQueryService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page); 

    	return "prepayApplicationList/prepayApplicationListList";
    	  
        } else if(actionflag == "add") {
        	
        	model.addAttribute("platform", platform);
        	txnQuery.setCreator(ShiroSessionUtil.getLoginSession().getId());
        	txnQuery.setCreate_time(new Date());
        	txnQuery.setModifier(Tool.uuid());
        	txnQuery.setModify_time(new Date());
        	txnQuery.setT_TxnQuery_Date(new Date());
        	if (modify_time == null){
        		txnQuery.setModify_time(new Date());
        	};
        	txnQueryService.insertSelective(txnQuery);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
         }
        
        PageInfo<TxnQuery> page = txnQueryService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page); 
		return "prepayApplicationList/prepayApplicationListList";
        }
    
    @RequestMapping(value = "ActionVerify")
    @ResponseBody
    public String ActionVerify(PrepayApplicationListController prepayApplicationList, TxnQuery txnQuery,String t_P_id, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,String t_TxnQuery_ID, String t_TxnQuery_ApprovalStatus,
            String t_TxnQuery_Mobil,Date modify_time,Date t_TxnQuery_Date,
            Model model) {
       	  model.addAttribute("platform", platform);
//    	

          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
;          
//        List<FinanceProduct> financeProductList = financeProductService.findAllList(paramMap); //上数据库查询的list树的结果,查询结果赋值与parentfinanceProductList
//        model.addAttribute("financeProductList", financeProductList); //返回到页面上
        
          if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面
        	model.addAttribute("platform", platform);
        	paramMap.put("t_TxnQuery_Date",t_TxnQuery_Date);
        	paramMap.put("t_TxnQuery_Mobil",t_TxnQuery_Mobil);
        	
            txnQuery.sett_TxnQuery_CompanyName(ShiroSessionUtil.getLoginSession().getCompany_name());
         	txnQuery.setCreator(ShiroSessionUtil.getLoginSession().getId());
         	txnQuery.setCreate_time(new Date());
         	txnQuery.setModifier(Tool.uuid());
         	txnQuery.setModify_time(new Date());
         	txnQuery.setPlatform("1");
         	txnQuery.setRemark("提交申请");
         	if (t_TxnQuery_Mobil.equals(null)) {
         		txnQuery.setT_TxnQuery_Mobil("");
         	}else {
         		txnQuery.setT_TxnQuery_Mobil(t_TxnQuery_Mobil);
         	}
         	txnQuery.setT_TxnQuery_ApprovalStatus("WFA");
         	txnQuery.setT_TxnQuery_Date(t_TxnQuery_Date);
         	txnQuery.setModify_time(new Date());
         	txnQuery.setT_TxnQuery_ID(Tool.uuid());
         	
        	txnQueryService.insertSelective(txnQuery);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            
            } else if (OperationTypeConstant.EDIT.equals(operationType)) 
                {
            	model.addAttribute("platform", platform);
            	txnQuery = txnQueryService.selectByPrimaryKey(t_TxnQuery_ID);
                txnQuery.setModifier(ShiroSessionUtil.getLoginSession().getId());
            	txnQuery.setModify_time(new Date());
             	txnQuery.setT_TxnQuery_ApprovalStatus("APPROVED");
            	txnQueryService.updateByPrimaryKeySelective(txnQuery);
                return JsonBizTool.genJson(ExRetEnum.SUCCESS);
          } else if (OperationTypeConstant.VIEW.equals(operationType) && t_TxnQuery_ApprovalStatus == "APPROVED") {
            return "prepayApplicationList/prepayApplicationView";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "prepayApplicationList/prepayApplicationListVerifyList";
          } else {
            return "redirect:/prepayApplicationListController/prepayApplicationListList";
        }
    }
    
    @RequestMapping(value = "editPrepayApplicationList")
	@ResponseBody
    public String editPrepayApplicationList(TxnQuery txnQuery,String t_TxnQuery_ID, Integer platform, String operationType,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	txnQuery = txnQueryService.selectByPrimaryKey(t_TxnQuery_ID);
        txnQuery.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	txnQuery.setModify_time(new Date());
     	txnQuery.setT_TxnQuery_ApprovalStatus("APPROVED");
    	txnQueryService.updateByPrimaryKeySelective(txnQuery);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    
    
    @RequestMapping(value = "deletePrepayApplicationList")
    @ResponseBody
    public String deleteFinanceProduct(String t_TxnQuery_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	txnQueryService.deleteByPrimaryKey(t_TxnQuery_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/PrepayApplicationListController/prepayApplicationListList?platform="+platform;
    }
    
    @RequestMapping(value = "viewPrepayApplicationList")
    @ResponseBody
    public String viewPrepayApplicationList(StaffPrepayApplicationList staffprepayApplicationList,TxnQuery txnQuery, HttpServletRequest request,
            String t_TxnQuery_ID,String t_TxnQuery_PID,String t_TxnQuery_Mobil,Date t_TxnQuery_Date,
    		HttpServletResponse response, Model model) {
    	model.addAttribute("t_TxnQuery_PID",t_TxnQuery_PID);
    	model.addAttribute("t_TxnQuery_Mobil",t_TxnQuery_Mobil);
    	model.addAttribute("t_TxnQuery_Date",t_TxnQuery_Date);
    	txnQueryService.findSearchList(staffprepayApplicationList);
    	return "prepayApplicationList/prepayApplicationListList";
    }  
}
