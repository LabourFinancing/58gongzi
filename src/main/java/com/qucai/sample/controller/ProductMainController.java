package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.ProductMain;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ProductMainService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/ProductMainController")
public class ProductMainController {


	// 必须把new ProductMain的列进行全面修改, 新建ProductMainService

    @Autowired
    private ProductMainService ProductMainService; //申明一个对象

    @ModelAttribute
    public ProductMain get(@RequestParam(required = false) String t_Product_ID) {
    	ProductMain entity = null;
        if (StringUtils.isNotBlank(t_Product_ID)) {
            entity = ProductMainService.selectByPrimaryKey(t_Product_ID);//用ProductMainService对象属性方法去调用t_Product_ID并返回
        }
        if (entity == null) {
            entity = new ProductMain();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 , 2017/11/14 回家改一下代码 新建产品详细页.
     */

    @RequestMapping(value = {"ProductMainList"})
    public String ProductMainList(ProductMain ProductMain, @RequestParam( defaultValue = "0" )  Integer platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {

        PageParam pp = Tool.genPageParam(request);      

        PageInfo<ProductMain> page = ProductMainService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page);
        
    	return "productMain/productMainList";
    }

    /*
     * Search Function
     */
    @RequestMapping(value = "ProductMainSearchList")
    public String ProductMainSearchList(ProductMain ProductMain, @RequestParam( defaultValue = "0" )  Integer platform,String t_Product_Name,Date create_time,
    		String remark,HttpServletRequest request, HttpServletResponse response, Model model) {

    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP

    	if (t_Product_Name != "" | create_time != null | remark != "") {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_Product_Name", t_Product_Name);//添加元素
        	paramSearchMap.put("create_time", create_time);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<ProductMain> page = ProductMainService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<ProductMain> page = ProductMainService.findAllList(new HashMap<String, Object>(), pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "productMain/productMainList";
//    	} else if(1 == platform) {
//    		return "productMain/ProductMainEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "productMain/ProductMainList";
    	}else {
    		return "productMain/productMainList";
    	}
    }


    @RequestMapping(value = "form")
    public String form(String t_Product_ID, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
       	  model.addAttribute("platform", platform);
//    	
          Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
          paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
          paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值

         if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面
        	return "productMain/ProductMainNewForm";
            } else if (OperationTypeConstant.EDIT.equals(operationType)) 
            {
            ProductMain ProductMain = ProductMainService.selectByPrimaryKey(t_Product_ID);
            return "productMain/ProductMainEditForm";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "productMain/ProductMainViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "productMain/ProductMainVerifyList";
          } else {
            return "redirect:/ProductMainController/dashboard";
        }
    }

    @RequestMapping(value = "addProductMain")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addProductMain(ProductMain ProductMain, HttpServletRequest request,Integer platform,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
        ProductMain.setCreator(ShiroSessionUtil.getLoginSession().getId());
        ProductMain.setCreate_time(new Date());
        ProductMain.setT_Product_ID(Tool.uuid());
        ProductMain.setT_Product_SysupdateDate(new Date());
    	ProductMainService.insertSelective(ProductMain);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deleteProductMain")
    public String deleteProductMain(String t_Product_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	ProductMainService.deleteByPrimaryKey(t_Product_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/ProductMainController/ProductMainList?platform="+platform;
    }


    @RequestMapping(value = "editProductMain")
    @ResponseBody
    public String editProductMain(ProductMain ProductMain, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        ProductMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
        ProductMain.setModifyTime(new Date());
    	ProductMainService.updateByPrimaryKeySelective(ProductMain);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }   
}
