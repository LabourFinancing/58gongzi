package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.entity.ProductMain;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.PersonalTreasuryCtrlService;
import com.qucai.sample.util.DBConnection;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/PersonalTreasuryCtrlController")
public class PersonalTreasuryCtrlController {
    
    private static ProductMain MobileProductMain;


    // 必须把new personalTreasuryCtrl的列进行全面修改, 新建personalTreasuryCtrlService
	
    @Autowired
    private PersonalTreasuryCtrlService personalTreasuryCtrlService; //申明一个对象

    @ModelAttribute
    public PersonalTreasuryCtrl get(@RequestParam(required = false) String t_FProd_ID) {
    	PersonalTreasuryCtrl entity = null;
        if (StringUtils.isNotBlank(t_FProd_ID)) {
            entity = personalTreasuryCtrlService.selectByPrimaryKey(t_FProd_ID);//用PersonalTreasuryCtrlService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new PersonalTreasuryCtrl();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 , 2017/11/14 回家改一下代码 新建产品详细页.
     */
    
    @RequestMapping(value = {"personalTreasuryCtrlList",""})
    public String personalTreasuryCtrlList(PersonalTreasuryCtrl personalTreasuryCtrl, @RequestParam( defaultValue = "0" )  Integer platform,
    		HttpServletRequest request, HttpServletResponse response, Model model) {
    	
        PageParam pp = Tool.genPageParam(request);      
        
        PageInfo<PersonalTreasuryCtrl> page = personalTreasuryCtrlService.findAllList(new HashMap<String, Object>(), pp);
        model.addAttribute("page", page);

    	return "personalTreasuryCtrl/personalTreasuryCtrlList";
    }
  
    /*
     * Search Function
     */
    @RequestMapping(value = "personalTreasuryCtrlSearchList")
    public String PersonalTreasuryCtrlSearchList(PersonalTreasuryCtrl personalTreasuryCtrl, @RequestParam( defaultValue = "0" )  Integer platform,String t_FProd_Name,Date create_time,
    		String remark,HttpServletRequest request, HttpServletResponse response, Model model) {
    	
    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
    	
    	if (t_FProd_Name != "" | create_time != null | remark != "") {
        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
        	paramSearchMap.put("t_FProd_Name", t_FProd_Name);//添加元素
        	paramSearchMap.put("create_time", create_time);//添加元素
        	paramSearchMap.put("remark", remark);//添加元素
            PageParam pp = Tool.genPageParam(request);  
            PageInfo<PersonalTreasuryCtrl> page = personalTreasuryCtrlService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
    	} else {
            PageParam pp = Tool.genPageParam(request);           
            PageInfo<PersonalTreasuryCtrl> page = personalTreasuryCtrlService.findAllList(new HashMap<String, Object>(), pp);
            model.addAttribute("page", page);
        }
		if(0 == platform) {
     		return "personalTreasuryCtrl/personalTreasuryCtrlList";
//    	} else if(1 == platform) {
//    		return "personalTreasuryCtrl/personalTreasuryCtrlEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "personalTreasuryCtrl/personalTreasuryCtrlList";
    	}else {
    		return "personalTreasuryCtrl/personalTreasuryCtrlList";
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
        	return "personalTreasuryCtrl/personalTreasuryCtrlNewForm";
            } else if (OperationTypeConstant.EDIT.equals(operationType)) 
            {
            PersonalTreasuryCtrl personalTreasuryCtrl = personalTreasuryCtrlService.selectByPrimaryKey(t_FProd_ID);
            return "personalTreasuryCtrl/personalTreasuryCtrlEditForm";
          } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            return "personalTreasuryCtrl/personalTreasuryCtrlViewForm";
          } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
              return "personalTreasuryCtrl/personalTreasuryCtrlVerifyList";
          } else {
            return "redirect:/personalTreasuryCtrlController/dashboard";
        }
    }
    
    @RequestMapping(value = "addPersonalTreasuryCtrl")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addPersonalTreasuryCtrl(PersonalTreasuryCtrl personalTreasuryCtrl, HttpServletRequest request,Integer platform,
            HttpServletResponse response, Model model) {
    	model.addAttribute("platform", platform);
    	personalTreasuryCtrl.setCreator(ShiroSessionUtil.getLoginSession().getId());
   	    personalTreasuryCtrl.setCreate_time(new Date());
   	    personalTreasuryCtrl.setT_personalewallet_treasuryctrlID(Tool.uuid());
    	personalTreasuryCtrl.setCreate_time(new Date());
    	personalTreasuryCtrlService.insertSelective(personalTreasuryCtrl);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deletePersonalTreasuryCtrl")
    public String deletePersonalTreasuryCtrl(String t_FProd_ID, Integer platform, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	personalTreasuryCtrlService.deleteByPrimaryKey(t_FProd_ID);
    	model.addAttribute("platform", platform);
        return "redirect:/PersonalTreasuryCtrlController/personalTreasuryCtrlList?platform="+platform;
    }

    
    @RequestMapping(value = "editPersonalTreasuryCtrl")
    @ResponseBody
    public String editPersonalTreasuryCtrl(PersonalTreasuryCtrl personalTreasuryCtrl, HttpServletRequest request,
            HttpServletResponse response, Model model) {
    	personalTreasuryCtrl.setModifier(ShiroSessionUtil.getLoginSession().getId());
    	personalTreasuryCtrl.setModify_time(new Date());
    	personalTreasuryCtrlService.updateByPrimaryKeySelective(personalTreasuryCtrl);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    //Get PersonalTreasuryinfo
    public PersonalTreasuryCtrl findPersonalTreasury(String personalTreasuryCat,Connection conn) throws SQLException {
//        Map<String, Object> mobilePersonalMain1 =  new HashMap<String, Object>();
        PersonalTreasuryCtrl MobilePersonalTreasuryCtrl = new PersonalTreasuryCtrl();
        ResultSet rs = null;

        String sql = "select * from t_personal_treasuryctrl where t_personalewallet_treasuryctrlID = ?";
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, personalTreasuryCat);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayDailyCntLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayDailyCntLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayDailyLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayDailyLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayFee(rs.getBigDecimal("t_personalewallet_treasuryctrlPayFee"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayStat(rs.getString("t_personalewallet_treasuryctrlPayStat"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayTxnLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayTotalLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlBeneDailyCntLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyCntLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlBeneDailyLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlBeneDailyLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlBeneFee(rs.getBigDecimal("t_personalewallet_treasuryctrlBeneFee"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlBeneStat(rs.getString("t_personalewallet_treasuryctrlBeneStat"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlBeneTotalLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayTotalLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlPayTxnLimit(rs.getBigDecimal("t_personalewallet_treasuryctrlPayTxnLimit"));
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlTxt("SQL-findPersonalTreasury-Succ");
            }else{
                MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlTxt("SQL-findPersonalTreasury-ErrorCode");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlstatus("exception");
            MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlTxt("SQL-findPersonalTreasury-ErrorCode");
            MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlTxt2(String.valueOf(e.getSQLState()));
            MobilePersonalTreasuryCtrl.setT_personalewallet_treasuryctrlTxt3(String.valueOf(e.getCause()));
            conn.close();
            return MobilePersonalTreasuryCtrl;
        } finally {
            return MobilePersonalTreasuryCtrl;
        }
    }

}
