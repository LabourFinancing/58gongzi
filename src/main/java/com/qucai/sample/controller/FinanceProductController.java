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
import com.qucai.sample.entity.FinanceProduct;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.FinanceProductService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;

/**
 * @author Spear Yao .
 */

@Controller
@RequestMapping(value = "/FinanceProductController")
public class FinanceProductController {

	
	// 必须把new financeProduct的列进行全面修改, 新建financeProductService
	
    @Autowired
    private FinanceProductService financeProductService; //申明一个对象

    @ModelAttribute
    public FinanceProduct get(@RequestParam(required = false) String t_FProd_ID) {
    	FinanceProduct entity = null;
        if (StringUtils.isNotBlank(t_FProd_ID)) {
            entity = financeProductService.selectByPrimaryKey(t_FProd_ID);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new FinanceProduct();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 , 2017/11/14 回家改一下代码 新建产品详细页.
     */
    
    @RequestMapping(value = {"financeProductList",""})
    public String financeProductList(FinanceProduct financeProduct, @RequestParam( defaultValue = "0" )  Integer platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
        PageParam pp = Tool.genPageParam(request);      
        
        PageInfo<FinanceProduct> page = financeProductService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page);
        

    	return "financeProduct/financeProductList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "financeProductSearchList")
    public String financeProductSearchList(FinanceProduct financeProduct, @RequestParam( defaultValue = "0" )  Integer platform,String t_FProd_Name,Date create_time,
    		String remark,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	
    	if (t_FProd_Name != "" | create_time != null | remark != "") {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_FProd_Name", t_FProd_Name);//添加元素
        	paramSearchMap.put("create_time", create_time);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<FinanceProduct> page = financeProductService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<FinanceProduct> page = financeProductService.findAllList(new HashMap<String, Object>(), pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "financeProduct/financeProductList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
    	}else {
    		return "financeProduct/financeProductList";
    	}
    }
    
    
    @RequestMapping(value = "form")
    public String form(String t_FProd_ID, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
       	  model.addAttribute("platform", platform);
//    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
          
         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面
        	return "financeProduct/financeProductNewForm";
            } else if (OperationTypeConstant.EDIT.equals(operationType)) 
            {
            FinanceProduct financeProduct = financeProductService.selectByPrimaryKey(t_FProd_ID);
            return "financeProduct/financeProductEditForm";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "financeProduct/financeProductViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "financeProduct/financeProductVerifyList";
          } else {
            return "redirect:/financeProductController/dashboard";
        }
    }
    
    @RequestMapping(value = "addFinanceProduct")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addFinanceProduct(FinanceProduct financeProduct, HttpServletRequest request,Integer platform,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	financeProduct.setCreator(ShiroSessionUtil.getLoginSession().getId());
   	    financeProduct.setCreate_time(new Date());
   	    financeProduct.setT_FProd_ID(Tool.uuid());
    	financeProduct.setT_FProd_SysupdateDate(new Date());
    	financeProductService.insertSelective(financeProduct);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteFinanceProduct")
    public String deleteFinanceProduct(String t_FProd_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	financeProductService.deleteByPrimaryKey(t_FProd_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/FinanceProductController/financeProductList?platform="+platform;
    }

    
    @RequestMapping(value = "editFinanceProduct")
    @ResponseBody
    public String editFinanceProduct(FinanceProduct financeProduct, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	financeProduct.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	financeProduct.setModify_time(new Date());
    	financeProductService.updateByPrimaryKeySelective(financeProduct);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }   
}
