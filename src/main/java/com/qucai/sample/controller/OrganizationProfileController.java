package com.qucai.sample.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qucai.sample.entity.TreasuryDBInfo;
import com.qucai.sample.service.TreasuryDBInfoService;
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
import com.qucai.sample.entity.OrganizationProfile;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationProfileService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping(value = "/OrganizationProfileController")
public class OrganizationProfileController {


	// 必须把new financeProduct的列进行全面修改, 新建financeProductService

    @Autowired
    private OrganizationProfileService organizationProfileService; //申明一个对象

    @Autowired
    private ManagerService managerService; //申明一个对象

    @Autowired
    private TreasuryDBInfoService treasuryDBInfoService; //申明一个对象

    @ModelAttribute
    public OrganizationProfile get(@RequestParam(required = false) String t_Profile_ID) {
    	OrganizationProfile entity = null;
    	String t_Profile_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        if (StringUtils.isNotBlank(t_Profile_OrgName)) {
            entity = organizationProfileService.selectAgencyName(t_Profile_OrgName);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new OrganizationProfile();
        }
        return entity;
    }

    @RequestMapping(value = {"organizationProfileList",""})
    public String organizationProfileList(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  String platform,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_M_Company = ShiroSessionUtil.getLoginSession().getCompany_name();

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象s
        PageParam pp = Tool.genPageParam(request);
        PageInfo<OrganizationProfile> page = organizationProfileService.findAllList(paramMap, pp);
        model.addAttribute("page", page);

        return "organizationProfile/organizationProfileList";
    }

    @RequestMapping(value = "organizationProfileSearchList")
    public String organizationInfoSearchList(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  Integer platform,String t_Profile_Qualification,String t_Profile_OrgName,
                                             String t_Profile_AppStatus,String t_Profile_OrgType, String remark,Date create_time,HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_M_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_Profile_AppStatus", t_Profile_AppStatus);
        model.addAttribute("t_Profile_Qualification",t_Profile_Qualification);
        model.addAttribute("t_Profile_OrgType",t_Profile_OrgType);
        model.addAttribute("t_Profile_OrgName",t_Profile_OrgName);
        model.addAttribute("remark",remark);
        model.addAttribute("create_time",create_time);

        if (t_Profile_Qualification != "" | t_Profile_OrgName !=  "" | t_Profile_OrgType != "" | t_Profile_AppStatus != "" | remark != "" | create_time != null ) {
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
            paramSearchMap.put("t_Profile_OrgName", t_Profile_OrgName);//添加元素
            paramSearchMap.put("t_Profile_Qualification", t_Profile_Qualification);//添加元素
            paramSearchMap.put("t_Profile_AppStatus", t_Profile_AppStatus);//添加元素
            paramSearchMap.put("t_Profile_OrgType", t_Profile_OrgType);//添加元素
            paramSearchMap.put("remark", remark);//添加元素
            paramSearchMap.put("create_time", create_time);//添加元素
            PageParam pp = Tool.genPageParam(request);
            if(ShiroSessionUtil.getLoginSession().getPlatform().equals("4")){
                paramSearchMap.put("t_O_VendorOrgName",t_M_Company);
            }
            PageInfo<OrganizationProfile> page = organizationProfileService.findSearchList(pp, paramSearchMap); //  << new-function
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
        } else {
            PageParam pp = Tool.genPageParam(request);
            Map<String, Object> paramMap  = new HashMap<String, Object>();
            paramMap.put("t_M_Company",t_M_Company);
            if(ShiroSessionUtil.getLoginSession().getPlatform().equals("4")){
                paramMap.put("t_M_Company",null);
                paramMap.put("t_O_VendorOrgName",t_M_Company);
            }
            else if(ShiroSessionUtil.getLoginSession().getPlatform().equals("5")){
                paramMap.put("t_M_Company",t_M_Company);
                paramMap.put("t_O_VendorOrgName",null);
            }
            PageInfo<OrganizationProfile> page = organizationProfileService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
        if(0 == platform) {
            return "organizationProfile/organizationProfileList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
        }else {
            return "organizationProfile/organizationProfileList";
        }
    }

    @RequestMapping(value = {"form",""})
    public String form(String t_Profile_ID, String operationType, Integer platform,
                       HttpServletRequest request, HttpServletResponse response,
                       Model model) {
        model.addAttribute("platform", platform);
//
        Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
        paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
//        List<FinanceProduct> financeProductList = financeProductService.findAllList(paramMap); //上数据库查询的list树的结果,查询结果赋值与parentfinanceProductList
//        model.addAttribute("financeProductList", financeProductList); //返回到页面上

        if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面
            return "organizationProfile/organizationProfileNewForm";
        } else if (OperationTypeConstant.EDIT.equals(operationType))
        {
            OrganizationProfile organizationprofile = organizationProfileService.selectByPrimaryKey(t_Profile_ID);
            return "organizationProfile/organizationProfileEditForm";
        } else {
            return "organizationProfile/organizationProfileList";
        }
    }

    @RequestMapping(value = {"info"})
    public String showOrganizationProfile(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  Integer platform,
                                       HttpServletRequest request, HttpServletResponse response, Model model) {

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        PageParam pp = Tool.genPageParam(request);
        
        OrganizationProfile organizationProfileDetail = organizationProfileService.selectAgencyName(ShiroSessionUtil.getLoginSession().getCompany_name());
        model.addAttribute("organizationProfileDetail", organizationProfileDetail);

        return "organizationProfile/userInformation";
    }

    @RequestMapping(value = {"Editinfo"})
    @ResponseBody
    public String EditOrganizationProfile(OrganizationProfile organizationProfile, @RequestParam( defaultValue = "0" )  Integer platform,
                                       String t_Profile_StatusRptRetAddress,String t_Profile_OrgType,String t_Profile_Mobile,String t_Profile_Email,String t_Profile_Address,String t_Profile_Contact,
                                       String t_Profile_PostRet,String t_Profile_CurrentAddress,
                                          HttpServletRequest request, HttpServletResponse response, Model model) {
        String t_Profile_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        organizationProfile.setT_Profile_StatusRptRetAddress(t_Profile_StatusRptRetAddress);
        organizationProfile.setT_Profile_OrgType(t_Profile_OrgType);
        organizationProfile.setT_Profile_Address(t_Profile_Address);
        organizationProfile.setT_Profile_Contact(t_Profile_Contact);
        organizationProfile.setT_Profile_Mobile(t_Profile_Mobile);
        organizationProfile.setT_Profile_Email(t_Profile_Email);
        organizationProfile.setT_Profile_PostRet(t_Profile_PostRet);
        
        model.addAttribute("organizationProfile",organizationProfile);
        model.addAttribute("t_Profile_StatusRptRetAddress",t_Profile_StatusRptRetAddress);
        model.addAttribute("t_Profile_OrgType",t_Profile_OrgType);
        model.addAttribute("t_Profile_Mobile",t_Profile_Mobile);
        model.addAttribute("t_Profile_Email",t_Profile_Email);
        model.addAttribute("t_Profile_Address",t_Profile_Address);
        model.addAttribute("t_Profile_Contact",t_Profile_Contact);
        model.addAttribute("t_Profile_PostRet",t_Profile_PostRet);
        model.addAttribute("t_Profile_CurrentAddress",t_Profile_CurrentAddress);
        int organizationProfileDetail = organizationProfileService.updateByPrimaryKeySelective(organizationProfile);
        model.addAttribute("organizationProfileDetail", organizationProfileDetail);
        if (organizationProfileDetail == 1) {
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
            return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }


    @RequestMapping("OrgCertImgUpload")
    @ResponseBody
    public String uploadfiles(@RequestParam("t_Profile_AgentCmpyName") MultipartFile t_Profile_AgentCmpyName, HttpServletRequest request,HttpServletResponse response,  String t_O_CertificationCode) throws IOException {
        Map<String,Object> ret = new HashMap<String,Object>();
        System.out.println("springmvc 方式 :" + t_Profile_AgentCmpyName);
        System.out.println("certcode :"+t_O_CertificationCode);
        // 指定文件上传的位置
        String path = request.getSession().getServletContext().getRealPath("/files/certpics");
        //判断该路径是否存在
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        if (!file.exists()) {
            // 不存在就创建路径
            file.mkdirs();
        }
        String originalfileName = t_Profile_AgentCmpyName.getOriginalFilename();
        String tailer = null;
        if(originalfileName.contains(".png")) {
             tailer = ".png";
        }
        if(originalfileName.contains(".jpg")) {
            tailer = ".jpg";
        }
        if(originalfileName.contains(".jpeg")) {
            tailer = ".jpeg";
        }

        String fileName = t_O_CertificationCode + "_certimg" + tailer;
        try {
            t_Profile_AgentCmpyName.transferTo(new File(path,fileName));
            ret.put("retcode","succ");
        } catch (IOException e) {
            e.printStackTrace();
            ret.put("retcode","fail");
        }
        return JsonBizTool.genJson(ExRetEnum.OrgBalGetSucc,ret);
    }

    //Check Complete org head info - treasury , msg , news
    @RequestMapping("checkAgentAccBalance")
    @ResponseBody
    public String checkAgentAccBalance(HttpServletRequest request, HttpServletResponse response, String t_Treasury_PayAmount,String platform) {
        Map<String, Object> ret = new HashMap<String, Object>();//
        String t_TreasuryDB_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        TreasuryDBInfo treasuryDBOrgInfo = treasuryDBInfoService.findOrgTreasuryOrgName(t_TreasuryDB_OrgName);
        if(treasuryDBOrgInfo!=null) {
            ret.put("OrgCurrBal", treasuryDBOrgInfo.getT_TreasuryDB_Balance());
        }else{
            ret.put("OrgCurrBal", "0.00");
        }
        return JsonBizTool.genJson(ExRetEnum.OrgBalGetSucc,ret);
    }
}
