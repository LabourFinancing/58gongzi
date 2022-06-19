package com.qucai.sample.controller;

import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.OrganizationProfile;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.req.PaymentInfo;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.OrganizationProfileService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.smss.src.example.json.HttpJsonExample;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.PinYinUtil;
import com.qucai.sample.util.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Controller
@RequestMapping(value = "/registController")
public class RegistController {
    
    @Autowired
    private ManagerService managerService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @Autowired
    private OrganizationProfileService organizationProfileService; //申明一个对象


    @RequestMapping(value = "form")
    @ResponseBody
    public String form(String type,String mobile,String username,String password,String firmcat,String firmName, String firmSelName,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
    	Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> ret = new HashMap<String, Object>();
        String FirmNameFMT = firmName.replace(" ","").trim();
        paramMap.put("FirmNameFMT",firmSelName.trim());
        paramMap.put("mobile",mobile);
    	// judge mobile+companyname is UNIQUE
        int countMCUnique = managerService.countMCUnique(paramMap);
        if(countMCUnique != 0){
            return JsonBizTool.genJson(ExRetEnum.MobileCmpy_Regfail);
        }
        Integer InsertStat = null;
        StringBuffer FirmName = new StringBuffer();
        String FirmNamePingyin = PinYinUtil.cn2py(firmName.replace(" ","").trim().toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        StringBuffer ss = new StringBuffer();
        String FirmNamePY = String.valueOf(ss.append(FirmNamePingyin).append(df.format(new Date()).toString()));
        Manager manager = new Manager();
        String managerid = Tool.uuid();
        Date NewTime =  new Date();
        if (OperationTypeConstant.REGIST.equals(type)) {
            switch (firmcat) {
                case "corp" :
                    manager.setRealName(username.replace(" ","").trim()); // 输入的用户名
                    manager.setUserName(username.replace(" ","").trim()); // 重定义后的登陆用户名
                    manager.setMobile(mobile.trim());
                    manager.setId(managerid);
                    manager.setPassword(password.trim());
                    manager.setTelephone(mobile.trim());
                    manager.setEmail("test@test.com");
                    manager.setStatus(1);
                    manager.setPlatform("5"); // 0 - PC , 1 - mobile, 2 - wechat, 3 -  alipay 4-agent 5-corp
                    manager.setRemark("企业用户，初始设置");
                    manager.setCompany_name(FirmNameFMT);
                    manager.setCreateTime(NewTime);
                    manager.setModifyTime(NewTime);
                    //regist manager
                    InsertStat = managerService.insertSelective(manager);
                    if(InsertStat.equals(1)){
                        OrganizationInfo organizationInfo = new OrganizationInfo();
                        //regist corp
                        String Orgid = Tool.uuid();InsertStat = null;
                        organizationInfo.setT_O_ID(Orgid);
                        organizationInfo.setT_O_CertificationCode(FirmNamePY);
                        organizationInfo.setT_O_OrgName(FirmNameFMT);
                        organizationInfo.setT_O_EmployeeAmount("0");
                        organizationInfo.setT_O_Category("AAA");
                        organizationInfo.setT_O_listOrg("off");
                        organizationInfo.setT_O_OrgRepresentative(username.trim());
                        organizationInfo.setT_O_OrgPayrollBankaccount(PaymentInfo.AllCompanyName);// switch to  杉德支付/电音支付
                        organizationInfo.setT_O_OrgChinaebiAcc(PaymentInfo.AllCompanyChinaebiAcc); // switch to selfown chinaebi if acc setup done
                        organizationInfo.setT_O_OrgSandeAcc(PaymentInfo.AllCompanySandeAcc); // switch to self-own sande if acc setup done
                        organizationInfo.setT_O_OrgStatus("off"); // pending on health and approval to setup and open
                        organizationInfo.setT_O_SysUpdateDate(new Date());
                        organizationInfo.setT_O_OrgPending(FirmNameFMT);
                        organizationInfo.setRemark("新注册企业用户");
                        organizationInfo.setPlatform("5"); // 1 - mobile, 2 - wechat, 3 -  alipay 4-agent 5-corp
                        organizationInfo.setCreator(username.trim());
                        organizationInfo.setCreate_time(NewTime);
                        organizationInfo.setModifier(username.trim());
                        organizationInfo.setModify_time(NewTime);
                        InsertStat = organizationInfoService.insertSelective(organizationInfo);
                        if (InsertStat.equals(1)){
                            //<< new-function grant inital firm role for pending superadmin approval get role granted
                            String roleIds = "c24a966222e24a549be853aa36b7af65"; // grant Corp role within 58gongzi_v2.0 标准企业用户角色 only roleids 多个以'，'连接的字符串
                            //insert OrgProfile
                            OrganizationProfile organizationProfile = new OrganizationProfile();
                            organizationProfile.setT_Profile_ID(Orgid);
                            organizationProfile.setT_Profile_OrgName(FirmNameFMT);
                            organizationProfile.setT_Profile_CertificationCode(FirmNamePY);
                            organizationProfile.setT_Profile_AppStatus("pending");
                            organizationProfileService.insertSelective(organizationProfile);
                            //
                            managerService.grantRole(managerid, roleIds);
                            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
                        }else{
                            managerService.deleteByPrimaryKey(managerid);
                            return JsonBizTool.genJson(ExRetEnum.ORG_Regfail);
                        }
                    }else{
                        return JsonBizTool.genJson(ExRetEnum.Manager_Regfail);
                    }
                case "agent" :
                    manager.setRealName(username.replace(" ","").trim()); // 输入的用户名
                    manager.setUserName(username.replace(" ","").trim()); // 重定义后的登陆用户名
                    manager.setMobile(mobile.trim());
                    manager.setId(managerid);
                    manager.setPassword(password.trim());
                    manager.setTelephone(mobile.trim());
                    manager.setEmail("test@test.com");
                    manager.setStatus(1);
                    manager.setPlatform("4"); // 0 - PC , 1 - mobile, 2 - wechat, 3 -  alipay
                    manager.setRemark("经销商企业用户，初始设置");
                    manager.setCompany_name(FirmNameFMT);
                    manager.setCreateTime(NewTime);
                    manager.setModifyTime(NewTime);
                    //regist manager
                    InsertStat = managerService.insertSelective(manager);
                    if(InsertStat.equals(1)){
                        OrganizationInfo organizationInfo = new OrganizationInfo();
                        //regist corp
                        String Orgid = Tool.uuid();InsertStat = null;
                        organizationInfo.setT_O_ID(Orgid);
                        organizationInfo.setT_O_CertificationCode(FirmNamePY);
                        organizationInfo.setT_O_OrgName(FirmNameFMT);
                        organizationInfo.setT_O_EmployeeAmount("0");
                        organizationInfo.setT_O_Category("AAA");
                        organizationInfo.setT_O_listOrg("on");
                        organizationInfo.setT_O_OrgRepresentative(username.trim());
                        organizationInfo.setT_O_OrgPayrollBankaccount(PaymentInfo.AllCompanyName);// switch to  杉德支付/电银支付
                        organizationInfo.setT_O_OrgChinaebiAcc(PaymentInfo.AllCompanyChinaebiAcc); // switch to selfown chinaebi if acc setup done
                        organizationInfo.setT_O_OrgSandeAcc(PaymentInfo.AllCompanySandeAcc); // switch to self-own sande if acc setup done
                        organizationInfo.setT_O_OrgStatus("off"); // pending on health and approval to setup and open
                        organizationInfo.setT_O_OrgPending(FirmNameFMT);
                        organizationInfo.setT_O_SysUpdateDate(new Date());
                        organizationInfo.setRemark("新注册经销商用户");
                        organizationInfo.setPlatform("4"); // 0 - PC , 1 - mobile, 2 - wechat, 3 -  alipay
                        organizationInfo.setCreator(username.trim());
                        organizationInfo.setCreate_time(NewTime);
                        organizationInfo.setModifier(username.trim());
                        organizationInfo.setModify_time(NewTime);
                        InsertStat = organizationInfoService.insertSelective(organizationInfo);
                        if (InsertStat.equals(1)){
                            //<< new-function grant inital firm role for pending superadmin approval get role granted
                            String roleIds = "3db115f404e44eb5a56648eb2fa690ba"; // grant Corp role within 58gongzi_v2.0 标准经销商用户角色 only roleids 多个以'，'连接的字符串
                            //insert OrgProfile
                            OrganizationProfile organizationProfile = new OrganizationProfile();
                            organizationProfile.setT_Profile_ID(Orgid);
                            organizationProfile.setT_Profile_OrgName(FirmNameFMT);
                            organizationProfile.setT_Profile_CertificationCode(FirmNamePY);
                            organizationProfile.setT_Profile_AppStatus("pending");
                            organizationProfileService.insertSelective(organizationProfile);
                            //
                            managerService.grantRole(managerid, roleIds);
                            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
                        }else{
                            managerService.deleteByPrimaryKey(managerid);
                            return JsonBizTool.genJson(ExRetEnum.ORG_Regfail);
                        }
                    }else{
                        return JsonBizTool.genJson(ExRetEnum.Manager_Regfail,ret);
                    }
                case "registered" :
                    //grant role
                    OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(firmSelName);
                    String roleIds = null;
                    OrganizationInfo organizationInfonew = new OrganizationInfo();
                    if(organizationInfo.getT_O_listOrg().equals("on")){
                        // << new-function add initial role as pending approval level to check status once org presenter approved, switch role to normal
                        organizationInfonew.setT_O_listOrg("on"); // pending on health and approval to setup and open
                        manager.setRemark("经销商用户新注册，初始设置");
                        roleIds = "3db115f404e44eb5a56648eb2fa690ba"; // grant Corp role within 58gongzi_v2.0 标准经销商用户角色 only roleids 多个以'，'连接的字符串
                    }else{
                        organizationInfonew.setT_O_listOrg("off"); // pending on health and approval to setup and open
                        manager.setRemark("企业用户新注册，初始设置");
                        roleIds = "3db115f404e44eb5a56648eb2fa690ba"; // grant Corp role within 58gongzi_v2.0 标准经销商用户角色 only roleids 多个以'，'连接的字符串
                    }
                    manager.setRealName(username.replace(" ","").trim()); // 输入的用户名
                    manager.setUserName(username.replace(" ","").trim()); // 重定义后的登陆用户名
                    manager.setMobile(mobile.replace(" ","").trim());
                    manager.setId(managerid);
                    manager.setPassword(password.trim());
                    manager.setTelephone(mobile.trim());
                    manager.setEmail("test@test.com");
                    manager.setStatus(1);
                    manager.setPlatform(organizationInfo.getPlatform()); // 0 - PC , 1 - mobile, 2 - wechat, 3 -  alipay
                    manager.setCompany_name(firmSelName.toString());
                    manager.setCreateTime(NewTime);
                    manager.setModifyTime(NewTime);
                    //regist manager
                    InsertStat = managerService.insertSelective(manager);
                    if(InsertStat.equals(1)){
                            //get role granted
                            managerService.grantRole(managerid, roleIds);
                            return JsonBizTool.genJson(ExRetEnum.Manager_NewSucc,ret);
                    }else{
                        managerService.deleteByPrimaryKey(managerid);
                        return JsonBizTool.genJson(ExRetEnum.Manager_Regfail,ret);
                    }
                default:
                    return JsonBizTool.genJson(ExRetEnum.Manager_Regfail,ret);
            }
        }else {
            // return to login 
            ret.put("msg","RegInputErr");
            return JsonBizTool.genJson(ExRetEnum.FAIL,ret);
        } 
    }

    @RequestMapping(value = "userNameDupChk")
    @ResponseBody
    public String userNameDupChk(String userName, String platform, HttpServletRequest request,
                                              HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        String id = null;
        Map<String, Object> params = new HashMap<String, Object>();
        String ret = null;
        if(!platform.equals("0") && !platform.equals("1") && !platform.equals("2")) {
            params.put("ret","error_transfer");
            return "false";
        }else {
            boolean rs = managerService.existManagerUserName(id, userName.replace(",","").trim());
            if(rs){
                ret = "1";
                return "false";
            }else{
                ret  = "0";
                return "true";
            }
        }
    }
    
    @RequestMapping(value = "firmNameDupChk")
    @ResponseBody
    public String firmNameDupChk(String firmName, HttpServletRequest request,
                                 HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        String id = null;
        String platform = "1";
        Map<String, Object> params = new HashMap<String, Object>();
        String ret = null;
        if(!platform.equals("0") && !platform.equals("1") && !platform.equals("2")) {
            params.put("ret","error_transfer");
            return "false";
        }else {
            String t_O_ID = Tool.uuid();
            String  t_Profile_OrgName = firmName.replace(",","").trim();
            boolean rs = organizationInfoService.existOrganizationInfoName(id,t_Profile_OrgName,platform);
            if(rs){
                ret = "1";
                return "false";
            }else{
                String orgInfoRegSucc = "success";
                ret  = "0";
                return "true";
            }
        }
    }
    
    @RequestMapping(value = "isChinaMobileLegal")
    @ResponseBody
    public static String isChinaMobileLegal(String mobile,String platform, HttpServletRequest request,
                                            HttpServletResponse response, Model model)
        throws PatternSyntaxException { 
        String regExp = "^((13[0-9])|(15[^4])|(16[5-7])|(17[0-8])|(18[0-9])|(19[0-9])|(14[5-8]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(mobile.replace(",","").trim().toString());
        System.out.println(m.matches());
        if (!m.matches()){
            return "false";
        }else {
            return "true";
        }
    }

    @RequestMapping(value = "firmlistSelect")
    @ResponseBody
    public String firmSelect(String firmName,String Type, HttpServletRequest request,
                            HttpServletResponse response, Model model) {
        OrganizationInfo organizationInfo = new OrganizationInfo();
        String batch_PB_company = null;
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("t_P_VendorEmployeeName",batch_PB_company);
        List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllNameOnly(paramMap);
        rs.put("OrganizationInfo",OrganizationInfo);    
        return JsonBizTool.genJson(rs);
    }

    @RequestMapping(value = "addRegist")
    @ResponseBody
    public String addRegist(Manager manager, HttpServletRequest request,
            HttpServletResponse response, Model model) {
        manager.setCreateTime(new Date());
        manager.setId(Tool.uuid());
        managerService.insertSelective(manager);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "SMSRegist")
    @ResponseBody
    public String SMSRegist(String type,String userName,String mobile,String id, HttpServletRequest request,
                             HttpServletResponse response, Model model) {
        boolean userNamers = false,userMobile = true;
        String SMScodeRec = null;
        userNamers = managerService.existManagerUserName(userName, id);
        if(userNamers){
            String retmsg = "existusername";
            SMScodeRec = "1";
        }
        userMobile = Tool.isChinaPhoneLegal(mobile.replace(" ","").trim().toString());
        if(!userMobile){
            SMScodeRec = "2";
        }
        //企业注册短信发送
        if(!userNamers && userMobile){
//		String SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	// test using		
//		SMScodeRec =  SMScodeInit.substring(0,SMScodeInit.indexOf(".")); // test using
            SMScodeRec = HttpJsonExample.SMSFirmRegCode(type,mobile.replace(" ","").trim()).toString();
            if (SMScodeRec.length() == 6){
                return SMScodeRec;
            }else{
                return SMScodeRec;
            }
        }
        return SMScodeRec;
    }

    @RequestMapping(value = "SMSVerify")
    @ResponseBody
    public String SMSverify(String smscode, HttpServletRequest request,
        HttpServletResponse response, Model model) {
//        manager.setCreateTime(new Date());
//        manager.setId(Tool.uuid());
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "grantRole")
    public String grantRole(String registId, String roleIds,
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
        managerService.grantRole(registId, roleIds);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }
    

}
