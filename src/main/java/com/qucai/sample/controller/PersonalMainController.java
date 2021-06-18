package com.qucai.sample.controller;

import com.github.pagehelper.PageInfo;
import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.*;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.*;
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
import com.qucai.sample.util.DBConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
@RequestMapping(value = "/PersonalMainController")
public class PersonalMainController {


    // 必须把new financeProduct的列进行全面修改, 新建financeProductService
//    @Autowired
//    private EwalletService ewalletService; //申明一个对象

    @Autowired
    private PersonalMainService personalMainService; //申明一个对象

    @Autowired
    private OrganizationInfoService organizationInfoService;

    @Autowired
    private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

    @Autowired
    private FinanceProductService financeProductService; //申明一个对象

    private Object OrganizationInfo;

    private String RetMobilePay;

    @ModelAttribute
    public PersonalMain get(@RequestParam(required = false) String t_personal_main_id,String t_personal_main_name,String t_personal_main_pid,String t_personal_main_mobile,
                            String t_personal_main_securityret,String t_personal_main_passport,HttpServletRequest request,Model model) {
        PersonalMain entity = null;
        t_personal_main_name = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
        t_personal_main_pid = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
        String pid = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//    	 if (AgencyOrgnization.getT_O_listOrg().equals("off")){
//    		t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//            t_P_VendorEmployeeName = null;
//    	 }else{
//    		t_P_Company = null;
//    		t_P_VendorEmployeeName = ShiroSessionUtil.getLoginSession().getCompany_name().trim();
//    	 }
        if (StringUtils.isNotBlank(t_personal_main_id)) {
            entity = personalMainService.selectByPrimaryKey(t_personal_main_id,pid);//用PersonalMainService对象属性方法去调用t_FProd_ID并返回
            return entity;
        }
        if (entity == null) {
            entity = new PersonalMain();
        }
        return entity;
    }

    /**
     *  改动：根据所属平台来确定是哪个平台的资源 
     */

    @RequestMapping(value = {"personalMainList",""})
    public String personalMainList(PersonalMain personalMain, OrganizationInfo organizationInfo,@RequestParam( defaultValue = "0" )
        String t_personal_main_id,String t_personal_main_name,String t_personal_main_pid,String t_personal_main_mobile,
                                   String t_personal_main_securityret,String t_personal_main_passport,Integer platform,String creditscore_begin,String creditscore_end, String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
                                   HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("t_personal_main_NAME", t_personal_main_name); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_personal_main_PID", t_personal_main_pid); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_personal_main_MOBILE", t_personal_main_mobile); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_personal_main_SECURITYRET", t_personal_main_securityret); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("t_personal_main_PASSPORT", t_personal_main_passport); //key从数据库查询并返回,并索引对应JSP
        model.addAttribute("creditscore_begin", creditscore_begin); //其实信用分
        model.addAttribute("creditscore_end", creditscore_end); //结尾信用分
        model.addAttribute("remark", remark); //key从数据库查询并返回,并索引对应JSP
        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

        if (t_personal_main_name != null | t_personal_main_pid != null | t_personal_main_mobile != null | t_personal_main_securityret != null
            | t_personal_main_passport != null | remark != null | creditscore_begin != null | creditscore_end != null) {
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
            paramSearchMap.put("t_personal_main_name", t_personal_main_name);//添加元素
            paramSearchMap.put("t_personal_main_pid", t_personal_main_pid);//添加元素
            paramSearchMap.put("t_personal_main_mobile", t_personal_main_mobile);//添加元素
            paramSearchMap.put("t_personal_main_securityret", t_personal_main_securityret);//添加元素
            paramSearchMap.put("creditscore_begin", creditscore_begin);//添加元素
            paramSearchMap.put("creditscore_end", creditscore_end);//添加元素
            paramSearchMap.put("remark", remark);//添加元素

            // 根据公司筛选
            if (t_O_OrgName.equals("ALL")){
                paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
            }
            else {
                //Flag on Agency or not
                if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                    paramSearchMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
                    paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
                }else{
                    paramSearchMap.put("t_P_Company", t_P_Company);
                    paramSearchMap.put("t_P_VendorEmployeeName", t_O_OrgName);
                }
                //Agency filter
            }

            PageParam pp = Tool.genPageParam(request);
            PageInfo<PersonalMain> page = personalMainService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象

            //根据公司筛选
            if (t_O_OrgName.equals("ALL")) {
                t_P_Company = null;
            }else {
                //Flag on Agency or not
                if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                    paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
                    paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
                }else{
                    paramMap.put("t_P_Company", t_P_Company);
                    paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
                }
                //Agency filter
            }

            PageParam pp = Tool.genPageParam(request);
            PageInfo<PersonalMain> page = personalMainService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
        return "personalMain/personalMainList";
    }

    /*
     * Search Function
     */
    @RequestMapping(value = "personalMainSearchList")
    public String personalMainSearchList(PersonalMain personalMain,OrganizationInfo organizationInfo, @RequestParam( defaultValue = "0" )
        String t_personal_main_id,String t_personal_main_name,String t_personal_main_pid,String t_personal_main_mobile,
                                         String t_personal_main_securityret,String t_personal_main_passport,Integer platform,String creditscore_begin,String creditscore_end, String SessionCompanyName,String t_P_VendorEmployeeName,String remark,String t_TreasuryDB_OrgName,
                                         HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("platform", platform); //key从数据库查询并返回,并索引对应JSP
        String t_O_OrgName = ShiroSessionUtil.getLoginSession().getCompany_name();
        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        OrganizationInfo AgencyOrgnization = organizationInfoService.selectAgencyName(t_O_OrgName);

        if (t_personal_main_name != null | t_personal_main_pid != null | t_personal_main_mobile != null | t_personal_main_securityret != null
            | t_personal_main_passport != null | remark != null | creditscore_begin != null | creditscore_end != null) {
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();//新建map对象
            paramSearchMap.put("t_personal_main_name", t_personal_main_name);//添加元素
            paramSearchMap.put("t_personal_main_pid", t_personal_main_pid);//添加元素
            paramSearchMap.put("t_personal_main_mobile", t_personal_main_mobile);//添加元素
            paramSearchMap.put("t_personal_main_securityret", t_personal_main_securityret);//添加元素
            paramSearchMap.put("creditscore_begin", creditscore_begin);//添加元素
            paramSearchMap.put("creditscore_end", creditscore_end);//添加元素
            paramSearchMap.put("remark", remark);//添加元素
            if (t_O_OrgName.equals("ALL")){
                paramSearchMap.put("t_P_Company", t_P_Company);//添加元素
            }
            else {
                //Flag on Agency or not
                if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                    paramSearchMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
                    paramSearchMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
                }else{
                    paramSearchMap.put("t_P_Company", t_P_Company);
                    paramSearchMap.put("t_P_VendorEmployeeName", t_O_OrgName);
                }
                //Agency filter
            }
            PageParam pp = Tool.genPageParam(request);
            PageInfo<PersonalMain> page = personalMainService.findSearchList(pp, paramSearchMap);
            model.addAttribute("page", page);//从数据库查询出来的结果用model的方式返回
        } else {
            Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
            //Flag on Agency or not
            if (AgencyOrgnization.getT_O_listOrg().equals("off")){
                paramMap.put("t_P_Company", ShiroSessionUtil.getLoginSession().getCompany_name());
                paramMap.put("t_P_VendorEmployeeName", t_P_VendorEmployeeName);
            }else{
                paramMap.put("t_P_Company", t_P_Company);
                paramMap.put("t_P_VendorEmployeeName", t_O_OrgName);
            }
            //Agency filter
            PageParam pp = Tool.genPageParam(request);
            PageInfo<PersonalMain> page = personalMainService.findAllList(paramMap, pp);
            model.addAttribute("page", page);
        }
        if(0 == platform) {
            return "personalMain/personalMainList";
//    	} else if(1 == platform) {
//    		return "financeProduct/financeProductEntList";
//    	} else if(2 == platform) {
//    		//个人端，暂时不考虑
//    		return "financeProduct/financeProductList";
        }else {
            return "personalMain/personalMainList";
        }
    }


    @RequestMapping(value = "form")
    public String form(PersonalMain personalMain,OrganizationInfo organizationInfo,String t_personal_main_id, String operationType, Integer platform,
                       HttpServletRequest request, HttpServletResponse response,String t_P_Company,String pid,
                       Model model) {
        model.addAttribute("platform", platform);
        Map<String, Object> paramMap = new HashMap<String, Object>();// 申明一个新对象
        paramMap.put("typeEnd", 1);      //给typeEnd对象赋值
        paramMap.put("platform", platform); //给platform,赋值为前台拿进来的值
        t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        if (OperationTypeConstant.NEW.equals(operationType)) { //用OperationTypeConstant函数封装的赋值函数方法判断值是否相等,并调用相应的页面        
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
            FinanceProduct financeProduct;
            if (t_P_Company.equals("ALL")){
                paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
                paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
                paramSearchMap.put("t_O_listOrg", "on");
                List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
                List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
                List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
                model.addAttribute("FinanceProduct", FinanceProduct);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
                model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
            }else {
                paramMap.put("t_P_Company", t_P_Company);//添加元素
                paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
                paramSearchMap.put("t_O_listOrg", "on");
                List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
                List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
                List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
                model.addAttribute("FinanceProduct", FinanceProduct);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
                model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
            }
            return "personalMain/personalMainNewForm";
        } else if (OperationTypeConstant.EDIT.equals(operationType)) {
            Map<String, Object> paramSearchMap = new HashMap<String, Object>();// 申明一个新对象
            FinanceProduct financeProduct;
            if (t_P_Company.equals("ALL")){
                paramSearchMap.put("t_FProd_Name", ""); //input org name into prod name mass search
                paramSearchMap.put("t_O_listOrg", "on");
                List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
                List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
                List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
                model.addAttribute("FinanceProduct", FinanceProduct);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
                model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
            }else {
                paramMap.put("t_P_Company", t_P_Company);//添加元素
                paramSearchMap.put("t_FProd_Name", t_P_Company); //input org name into prod name mass search
                paramSearchMap.put("t_O_listOrg", "on");
                List<OrganizationInfo> OrganizationInfo = organizationInfoService.findOrgName(paramMap);
                List<FinanceProduct> FinanceProduct= financeProductService.findSearchList(paramSearchMap);
                List<OrganizationInfo> OrganizationInfoAgency = organizationInfoService.findOrgNameAgency(paramSearchMap);
                model.addAttribute("FinanceProduct", FinanceProduct);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
                model.addAttribute("OrganizationInfoAgency", OrganizationInfoAgency);
            }
            personalMain = personalMainService.selectByPrimaryKey(t_personal_main_id,pid);
            return "personalMain/personalMainEditForm";
        } else if (OperationTypeConstant.EDITCREDITBALANCE.equals(operationType)) {
            personalMain = personalMainService.selectByPrimaryKey(t_personal_main_id,pid);
            return "personalMain/personalMainEditCredit";
        } else if (OperationTypeConstant.VIEW.equals(operationType)) {
            personalMain = personalMainService.selectByPrimaryKey(t_personal_main_id,pid);
            return "personalMain/personalMainViewForm";
        } else if (OperationTypeConstant.VERIFY.equals(operationType)) {
            return "personalMain/personalMainVerifyList";
        } else {
            return "redirect:/personalMainController/personalMainList";
        }
    }

    @RequestMapping(value = "addPersonalMain")   //当判断页面的行为为add时,返回相应的add页面
    @ResponseBody
    public String addPersonalMain(PersonalMain personalMain, HttpServletRequest request,Integer platform,Date modify_time,
                                  HttpServletResponse response, Model model) {
        model.addAttribute("platform", platform);
        personalMain.setCreator(ShiroSessionUtil.getLoginSession().getId());
        personalMain.setCreate_time(new Date());
        personalMain.setT_personal_main_id(Tool.uuid());
        personalMain.setCreate_time(new Date());

        if (modify_time == null){
            personalMain.setModify_time(new Date());
        };
        personalMainService.insertSelective(personalMain);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "deletePersonalMain")
    public String deletePersonalMain(String t_personal_main_id, Integer platform, HttpServletRequest request,
                                     HttpServletResponse response, Model model) {
        personalMainService.deleteByPrimaryKey(t_personal_main_id);
        model.addAttribute("platform", platform);
        return "redirect:/PersonalMainController/personalMainList?platform="+platform;
    }

    @RequestMapping(value = "creditRefreshPersonalMain")
    public String creditRefreshPersonalMain(String t_personal_main_id, Integer platform, HttpServletRequest request,String pid,
                                            HttpServletResponse response, Model model) {
        PersonalMain personalMain = personalMainService.selectByPrimaryKey(t_personal_main_id,pid);
        String t_Txn_PrepayApplierName = personalMain.getT_personal_main_realname();
        String t_Txn_PrepayApplierPID = personalMain.getT_personal_main_pid();
        String t_Txn_Paystatus = personalMain.getT_personal_main_mobile();
        int retdata = staffPrepayApplicationService.deleteTxnPayment(t_Txn_PrepayApplierName, t_Txn_PrepayApplierPID, t_Txn_Paystatus);
        model.addAttribute("platform", platform);
        return "redirect:/PersonalMainController/personalMainList?platform="+platform;
    }


    @RequestMapping(value = "editPersonalMain")
    @ResponseBody
    public String editPersonalMain(PersonalMain personalMain, HttpServletRequest request,
                                   HttpServletResponse response, Model model) {
        personalMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
        personalMain.setModify_time(new Date());
        String pid = personalMain.getT_personal_main_pid();
        personalMainService.updateByPrimaryKeySelective(personalMain,pid);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
    }

    @RequestMapping(value = "editCreditBalance")
    @ResponseBody
    public String editCreditBalance( HttpServletRequest request, HttpServletResponse response,
                                     PersonalMain personalMain,String t_personal_main_mobile,BigDecimal t_P_NetMonthlyBonusAmount, Model model) {
        personalMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
        personalMain.setModify_time(new Date());
        String OrderCodeUpdate = null;
        BigDecimal CreditBalanceAmtRefund = null;
        StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(t_personal_main_mobile);
        int rs = 0;
        String paymentmethod = "debitcard";
//        if(staffPrepayApplicationCredit != null){
//	        staffPrepayApplicationCredit.setT_Txn_BalanceCreditNum(t_P_NetMonthlyBonusAmount);
//	        staffPrepayApplicationCredit.setT_Txn_PrepayCounts(staffPrepayApplicationCredit.getT_Txn_CreditPrepayBalanceNum().intValue());
//	        staffPrepayApplicationCredit.setT_Txn_CreditPrepayBalanceNum(t_P_NetMonthlyBonusAmount);
//	         paymentmethod = "alipay";
//	        OrderCodeUpdate = staffPrepayApplicationCredit.getT_Txn_Num();
//	        rs = staffPrepayApplicationService.updateCreditBalanceAmt(CreditBalanceAmtRefund, OrderCodeUpdate);
//        }else{
//             paymentmethod = "wechatpay";
//        	personalMain.setT_personal_main_paymentmethod(paymentmethod);
//        	rs = personalMainService.updateByPrimaryKeySelective(personalMain);
//        }

        if(rs==1){
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }else{
            return JsonBizTool.genJson(ExRetEnum.FAIL);
        }
    }


    /*
    移动端我的模块
     */
    public String personalMMobiledashboard(EwalletTxn ewalletTxn) {
        PersonalMain personalMain = null;
        personalMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
        personalMain.setModify_time(new Date());
        String OrderCodeUpdate = null;
        BigDecimal CreditBalanceAmtRefund = null;
        StaffPrepayApplicationList staffPrepayApplicationCredit = staffPrepayApplicationService.findPrepayApplierCredit(OrderCodeUpdate);
        int rs = 0;
        String paymentmethod = "debitcard";
        String retPersonalMainController = "debitcard";
//        if(staffPrepayApplicationCredit != null){
//	        staffPrepayApplicationCredit.setT_Txn_BalanceCreditNum(t_P_NetMonthlyBonusAmount);
//	        staffPrepayApplicationCredit.setT_Txn_PrepayCounts(staffPrepayApplicationCredit.getT_Txn_CreditPrepayBalanceNum().intValue());
//	        staffPrepayApplicationCredit.setT_Txn_CreditPrepayBalanceNum(t_P_NetMonthlyBonusAmount);
//	         paymentmethod = "alipay";
//	        OrderCodeUpdate = staffPrepayApplicationCredit.getT_Txn_Num();
//	        rs = staffPrepayApplicationService.updateCreditBalanceAmt(CreditBalanceAmtRefund, OrderCodeUpdate);
//        }else{
//             paymentmethod = "wechatpay";
//        	personalMain.setT_personal_main_paymentmethod(paymentmethod);
//        	rs = personalMainService.updateByPrimaryKeySelective(personalMain);
//        }

        return RetMobilePay;
    }

    /*
移动端个人新注册
 */
    public String addMobilePersonalMain(String personalMID,String pid,String phone,String facialret) throws SQLException {
        String ewallet = "58ewallet";
        DBConnection dao = new DBConnection();
        Connection conn = dao.getConnection();
        String userid = Tool.uuid();
        String cryptoc = "gfcoin";
        String sql="update t_personal_main a " +
            "set t_personal_main_digi3 = ?," +
            "t_personal_main_pid = ?," +
            "t_personal_main_facialret = ?," +
            "t_personal_main_crypto = ? " +
            "where a.t_personal_main_id = ?";
//        String sql="insert into t_personal_main(t_personal_main_id,t_personal_main_name,t_personal_main_pid,t_personal_main_mobile) values(?,?,?,?)";
        try {
            PreparedStatement ptmt=conn.prepareStatement(sql);
            ptmt.setString(1,userid);
            ptmt.setString(2,pid);
            ptmt.setString(3,facialret);
            ptmt.setString(4,cryptoc);
            ptmt.setString(5,personalMID);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            conn.close();
        }

//        personalMain.setT_personal_main_id(Tool.uuid());
//        personalMain.setT_personal_main_facialret(facialret);
//        personalMain.setModifier(ShiroSessionUtil.getLoginSession().getId());
//        personalMain.setModify_time(new Date());
//        String OrderCodeUpdate = null;
//        BigDecimal CreditBalanceAmtRefund = null;
        int rs = 0;
        String RetNewUserPersonalMain = null;
        if (rs == 1){
            RetNewUserPersonalMain =  "succ";
        }
        String paymentmethod = "debitcard";
        String retPersonalMainController = "debitcard";

        return RetNewUserPersonalMain;
    }

    /*参考 查询
    public EndUser find(EndUser user) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = DbConnection .getConnection();
        EndUser user2 = null;
        String sql = "select * from  user where  username=? and password=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user2 = new EndUser();
                user2.setId(rs.getInt("id"));
                user2.setUsername(rs.getString("username"));
                user2.setPassword(rs.getString("password"));
                user2.setRelname(rs.getString("relname"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            DbConnection.closeDB(conn, pstmt, rs);

        }
        return user2;

    }
    
    Connection conn = null;
        PreparedStatement stmt =  null;
        try{
            conn = JdbcUtil.getConnection();
            stmt = conn.prepareStatement("insert into t1 (id,name) values(?,?)");

            for(int i=0;i<10;i++){
                stmt.setInt(1, i+1);
                stmt.setString(2, "aa"+(i+1));
                stmt.addBatch();//向缓存中加的参数
            }

            int [] ii = stmt.executeBatch();//批处理.每条语句影响的行数

            for(int i:ii)
                System.out.println(i);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtil.release(null, stmt, conn);
        }
     */
}
