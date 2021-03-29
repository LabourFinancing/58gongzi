package com.qucai.sample.controller;

import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.Paymentvendormgt;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.HistoricalTxnQueryService;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.vo.CompanyTxnStatic;
import com.qucai.sample.vo.CompanyTxnAmtWlyStatic;
import com.qucai.sample.vo.CompanyTxnCntWlyStatic;
import com.qucai.sample.util.Tool;
import com.qucai.sample.vo.PersonalTxnStatic;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/OrganizationDashboardController")
public class OrganizationDashboardController {


    // 必须把new financeProduct的列进行全面修改, 新建financeProductService

    @Autowired
    private OrganizationInfoService organizationInfoService; //申明一个对象

    @Autowired
    private HistoricalTxnQueryService historicalTxnQueryService;

    @Autowired
    private ManagerService managerService; //申明一个对象

    @ModelAttribute
    public OrganizationInfo get(@RequestParam(required = false) String t_O_ID) {
        OrganizationInfo entity = null;
        if (StringUtils.isNotBlank(t_O_ID)) {
            entity = organizationInfoService.selectByPrimaryKey(t_O_ID);//用FinanceProductService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new OrganizationInfo();
        }
        return entity;
    }

    @RequestMapping(value = {"dashboard"})
    public String showOrganizationInfo(OrganizationInfo organizationInfo, @RequestParam(defaultValue = "0") Integer platform,
                                       HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {

        Map<String, Object> paramMap = new HashMap<String, Object>();//新建map对象
        Map<String, Object> rs = new HashMap<String, Object>();

        Calendar cale = Calendar.getInstance();

//        String t_P_Company = ShiroSessionUtil.getLoginSession().getCompany_name();
        String t_P_Company = "美团买菜";
        paramMap.put("t_P_Company", "美团买菜");
        String errBatchDupDebitCard = null;
        CompanyTxnStatic companyTxnStatic = null;
        CompanyTxnCntWlyStatic companyTxnCntWlyStatic = null;
//        CompanyTxnStatic companyTxnStaticAmtDaily = historicalTxnQueryService.SearchCompanyTxnStaticAmtWly(paramMap);
//        CompanyTxnStatic companyTxnStaticAmtCount = historicalTxnQueryService.SearchCompanyTxnStatic(paramMap);

        List<CompanyTxnStatic> companyTxnStaticRet = historicalTxnQueryService.SearchCompanyTxnStaticAmtDaily(paramMap);
        List<CompanyTxnAmtWlyStatic> CompanyTxnAmtWlyStaticRet = historicalTxnQueryService.SearchCompanyTxnStaticAmtWly(paramMap);

        String StaticInfo = null;
        String StaticInfoDaily = null;
        if (CompanyTxnAmtWlyStaticRet.size() != 0 || !CompanyTxnAmtWlyStaticRet.isEmpty()) {
            StringBuffer errRecord = new StringBuffer();
            for (int i = 0; i < CompanyTxnAmtWlyStaticRet.size(); i++) {
                if (i == 0) {
                    errRecord.append("'").append("day").append(i).append(":").append(CompanyTxnAmtWlyStaticRet.get(i).getT_CTxnAmt_Static_day1()).append("-");
                } else {
                    errRecord.append(",").append("'").append("day").append(i).append(":").append(CompanyTxnAmtWlyStaticRet.get(i).getT_CTxnAmt_Static_day1()).append("-");
                }
            }
            StaticInfo = new String(errRecord);
        }

        if (companyTxnStaticRet.size() == 1) {
            StringBuffer errRecord1 = new StringBuffer();
                    errRecord1.append("'").append("T_CTxn_Static_CompanyName:").append(companyTxnStaticRet.get(0).getT_CTxn_Static_CompanyName()).append("-").append("T_CTxn_Static_CompanyNameID:").append(companyTxnStaticRet.get(0).getT_CTxn_Static_CompanyNameID()
                    ).append("-").append("Txn_Static_TotTxnAmtDaily:").append(companyTxnStaticRet.get(0).getT_CTxn_Static_TotTxnAmtDaily()).append("-").append("Txn_Static_TotTxnCountDaily:").append(companyTxnStaticRet.get(0).getT_CTxn_Static_TotTxnCountDaily())
                        .append("-").append("T_CTxn_Succ_Txn():").append(companyTxnStaticRet.get(0).getT_CTxn_Succ_Txn()).append("-").append("T_CTxn_Fail_Txn():").append(companyTxnStaticRet.get(0).getT_CTxn_Fail_Txn());
                
                StaticInfoDaily = new String(errRecord1);
        }
        model.addAttribute("StaticInfo",StaticInfo);
        model.addAttribute("StaticInfoDaily",StaticInfoDaily);
        return "organizationDashboard/userCenter";
    }
}
