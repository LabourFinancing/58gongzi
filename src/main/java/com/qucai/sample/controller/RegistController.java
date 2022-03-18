//package com.qucai.sample.controller;
//
//import com.github.pagehelper.PageInfo;
//import com.qucai.sample.OperationTypeConstant;
//import com.qucai.sample.common.PageParam;
//import com.qucai.sample.entity.Regist;
//import com.qucai.sample.entity.OrganizationInfo;
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.service.RegistService;
//import com.qucai.sample.service.OrganizationInfoService;
//import com.qucai.sample.service.RoleService;
//import com.qucai.sample.util.JsonBizTool;
//import com.qucai.sample.util.ShiroSessionUtil;
//import com.qucai.sample.util.Tool;
//import com.qucai.sample.vo.RoleGrant;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping(value = "/registController")
//public class RegistController {
//
//    @Autowired
//    private RegistService registServiceService;
//    
//    @Autowired
//    private RoleService roleService;
//    
//    @Autowired
//    private OrganizationInfoService organizationInfoService; //申明一个对象
//
//    @ModelAttribute
//    public Regist get(@RequestParam(required = false) String id) {
//        Regist entity = null;
//        if (StringUtils.isNotBlank(id)) {
//            entity = registServiceServiceService.selectByPrimaryKey(id);
//        }
//        if (entity == null) {
//            entity = new Regist();
//        }
//        return entity;
//    }
//
//    @RequestMapping(value = { "managerList", "" })
//    public String managerList(Regist regist, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//        
//        PageParam pp = Tool.genPageParam(request);
//        
//        PageInfo<Regist> page = registService.findAllList(new HashMap<String, Object>(), pp);
//        model.addAttribute("page", page);
//
//        return "manager/managerList";
//    }
//    
//    @RequestMapping(value = "managerSearchList")
//    public String personalInfoSearchList(Regist regist, @RequestParam( defaultValue = "0" ) Integer platform,String real_name,String company_name,
//    		String mobile,HttpServletRequest request, HttpServletResponse response, Model model) {
//    	
//    	model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
//    	
//    	if (real_name != "" | mobile != "" || company_name != "") {
//        	Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
//        	paramSearchMap.put("real_name", real_name);//添加元素
//        	paramSearchMap.put("company_name", company_name);//添加元素
//        	paramSearchMap.put("mobile", mobile);//添加元素
//
//            PageParam pp = Tool.genPageParam(request);  
//            PageInfo<Regist> page = registService.findSearchList(pp, paramSearchMap);
//            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
//    	} else {
//            PageParam pp = Tool.genPageParam(request);           
//            PageInfo<Regist> page = registService.findAllList(new HashMap<String, Object>(), pp);
//            model.addAttribute("page", page);
//        }
//       return "regist/registList";
//    }
//
//    @RequestMapping(value = "form")
//    public String form(String id, String operationType,
//            HttpServletRequest request, HttpServletResponse response,
//            Model model) {
//    	
//    	Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
//        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
//        if (OperationTypeConstant.NEW.equals(operationType)) {
//        	if( t_P_Company.equals("ALL")){
//        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
//    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
//        		}
//        	else{
//        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
//    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
//        		}
//            return "regist/registNewForm";
//        }else if (OperationTypeConstant.EDIT.equals(operationType)) {
//            Regist regist = registServiceService.selectByPrimaryKey(id);
//        	if( t_P_Company.equals("ALL")){
//        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
//    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
//        		}
//        	else{
//        		List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
//    	       	model.addAttribute("OrganizationInfo", OrganizationInfo);
//        		}
//            model.addAttribute("regist", regist);
//            return "regist/registEditForm";
//        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
//            return "regist/registViewForm";
//        } 
//          else {
//            return "redirect:/registController/registList";
//        } 
//    }
//    
//    @RequestMapping(value = "passwordEdit")
//    public String form(String operationType,
//            HttpServletRequest request, HttpServletResponse response,
//            Model model) {
//  	  String personalID = ShiroSessionUtil.getLoginSession().getTelephone();
//    	Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
//        Regist regist = registService.selectByPersonalID(personalID);
//        model.addAttribute("manager", manager);
//        return "manager/managerPWDReset";
//
//    }
//    
//    @RequestMapping(value = "registPWDReset")
//    @ResponseBody
//    public String form(Regist regist, HttpServletRequest request,String passwordNew, String passwordNewReinput,String password,String telephone,
//            HttpServletResponse response, Model model) {
//
//           model.addAttribute("passwordNew",passwordNew);
//           model.addAttribute("passwordNewReinput",passwordNewReinput);
//           if (passwordNew.equals(passwordNewReinput)){
//        	 password = passwordNewReinput;
//        	 telephone = ShiroSessionUtil.getLoginSession().getTelephone();
//        	 Date modifyTime = new Date();
//        	 regist.setPassword(passwordNewReinput);
//        	 regist.setModifyTime(modifyTime);
//        	 regist.setTelephone(ShiroSessionUtil.getLoginSession().getTelephone());
//             registService.updatePassword(regist);
//             return JsonBizTool.genJson(ExRetEnum.UPDATEPWDSUCCESS);
//		    }else{
//				return JsonBizTool.genJson(ExRetEnum.PASSWORD_FAIL);
//		    }
//    }
//           
//    
//    @RequestMapping(value = "addRegist)
//    @ResponseBody
//    public String addRegist(Regist regist, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//        regist.setCreateTime(new Date());
//        regist.setId(Tool.uuid());
//        registService.insertSelective(regist);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//
//    @RequestMapping(value = "SMSRegist")
//    @ResponseBody
//    public String SMSRegist(Regist regist, HttpServletRequest request,
//                             HttpServletResponse response, Model model) {
//        regist.setCreateTime(new Date());
//        regist.setId(Tool.uuid());
//        registService.insertSelective(regist);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//    
//    @RequestMapping(value = "deleteRegist")
//    public String deleteRegist(String id, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//        registService.deleteByPrimaryKey(id);
//        return "redirect:/registController/registList";
//    }
//    
//    @RequestMapping(value = "editRegist")
//    @ResponseBody
//    public String editRegist(Regist regist, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//        managerService.updateByPrimaryKeySelective(regst);
//        
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//    
//    @RequestMapping(value = "grantRolePage")
//    public String grantRolePage(String registId, HttpServletRequest request,
//            HttpServletResponse response, Model model) {
//        List<RoleGrant> roleGrantList = roleService.findRegistRoleGrantAllList(registId);
//        model.addAttribute("roleGrantList", roleGrantList);
//        model.addAttribute("registId", registId);
//        return "regist/grantRolePage";
//    }
//    
//    @RequestMapping(value = "grantRole")
//    @ResponseBody
//    public String grantRole(String registId, String roleIds,
//            HttpServletRequest request, HttpServletResponse response,
//            Model model) {
//        registService.grantRole(registId, roleIds);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//    }
//    
//}
