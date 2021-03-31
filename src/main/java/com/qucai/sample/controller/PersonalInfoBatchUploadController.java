package com.qucai.sample.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.Util;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qucai.sample.entity.FinanceProduct;
import com.qucai.sample.entity.OrganizationInfo;
import com.qucai.sample.entity.PersonalInfo;
import com.qucai.sample.entity.PersonalInfoBatchUpload;
import com.qucai.sample.entity.PersonalInfoBatchUploadStatus;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.FinanceProductService;
import com.qucai.sample.service.OrganizationInfoService;
import com.qucai.sample.service.PersonalInfoBatchUploadService;
import com.qucai.sample.service.PersonalInfoBatchUploadStatusService;
import com.qucai.sample.service.PersonalInfoService;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.IdCardUtil;
import com.qucai.sample.util.JsonBizTool;
import com.qucai.sample.util.ShiroSessionUtil;
import com.qucai.sample.util.Tool;


@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "/PersonalInfoBatchUploadController")
public class PersonalInfoBatchUploadController {


    // 必须把new financeProduct的列进行全面修改, 新建financeProductService

    @Autowired
    private PersonalInfoService personalInfoService; //申明一个对象

    @Autowired
    private OrganizationInfoService organizationInfoService;

    @Autowired
    private FinanceProductService financeProductService; //申明一个对象

    @Autowired
    private StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

    @Autowired
    private PersonalInfoBatchUploadService personalInfoBatchUploadService;

    @Autowired
    private PersonalInfoBatchUploadStatusService personalInfoBatchUploadStatusService;

    private Object OrganizationInfo;

    private static boolean judegExcelEdition(String fileName){
        if (fileName.matches("^.+\\.(?i)(xls)$")){
            return false;
        }else {
            return true;
        }
    }
    //-----------------------------------MVC 的控制器----------------------
    //Controller为：

    @ModelAttribute
    public PersonalInfoBatchUpload get(@RequestParam(required = false) String t_txn_P_Mobil) {
        PersonalInfoBatchUpload entity = null;
        if (entity == null) {
            entity = new PersonalInfoBatchUpload();
        }
        return entity;
    }

    @RequestMapping(value = {"personalInfoBatchUploadNew"})
    public String PersonalInfoBatchUploadNew(HttpServletRequest request,HttpServletResponse response, Model model,String batch_PB_company){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> paramSearchMap = new HashMap<String, Object>();

        batch_PB_company = ShiroSessionUtil.getLoginSession().getCompany_name();
        String t_O_OrgName = batch_PB_company,t_P_Company = batch_PB_company;
        if (batch_PB_company.equalsIgnoreCase("all")){
            List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
            List<PersonalInfoBatchUpload> personalInfoBatchUpload = personalInfoBatchUploadService.SelectAllBatch(paramMap);
            model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
            model.addAttribute("OrganizationInfo", OrganizationInfo);
        }else{
            OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(t_O_OrgName);
            if (organizationInfo.getT_O_listOrg().equalsIgnoreCase("on")){
                paramSearchMap.put(batch_PB_company, batch_PB_company);
                paramSearchMap.put("batch_PB_vendorcompany", organizationInfo.getT_O_OrgPending());
                paramSearchMap.put("t_P_VendorEmployeeName", organizationInfo.getT_O_OrgPending());
                paramMap.put("t_P_VendorEmployeeName",organizationInfo.getT_O_OrgPending());
                List<OrganizationInfo> OrganizationInfo = organizationInfoService.findAllName(paramMap);
                List<PersonalInfoBatchUpload> personalInfoBatchUpload = personalInfoBatchUploadService.SelectCompanyBatch(paramSearchMap);
                model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
                List<PersonalInfo> PersonalInfo = personalInfoService.findSearchCompanyDist(paramSearchMap);
                model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
            }else {
                List<OrganizationInfo> OrganizationInfo = new ArrayList<OrganizationInfo>(); {
                    {this.add(batch_PB_company);
                    }
                };
                paramSearchMap.put("t_FProd_CorpPool", organizationInfo.getT_O_Category());//添加元素
                List<FinanceProduct> FinanceProduct = financeProductService.findSearchList(paramSearchMap);
                model.addAttribute("OrganizationInfo", OrganizationInfo);
                model.addAttribute("FinanceProduct", FinanceProduct);
            }
        }
        model.addAttribute("t_FProd", batch_PB_company);
        model.addAttribute("batch_PB_company", batch_PB_company);



        String flag = "failure";
        return "personalInfoBatchUpload/personalInfoBatchUploadNew";
    }

    private void add(String batch_PB_company) {
        // TODO Auto-generated method stub

    }

    @RequestMapping(value = "personalInfoBatchUploadBatchChange")
    @ResponseBody
    public String personalInfoBatchUploadBatchChange(String t_PIBU_Orgname, HttpServletRequest request,
                                                     HttpServletResponse response, Model model) {
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> paramSearchMap = new HashMap<String, Object>();
        model.addAttribute("t_Org_name", t_PIBU_Orgname);
        String t_O_OrgName = null;
        try {
            t_O_OrgName = URLDecoder.decode(t_PIBU_Orgname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String batch_PB_company = t_O_OrgName;
        if (batch_PB_company.equalsIgnoreCase("all")){
            List<PersonalInfoBatchUpload> personalInfoBatchUpload = personalInfoBatchUploadService.SelectAllBatch(paramMap);
            model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
            rs.put("personalInfoBatchUpload", personalInfoBatchUpload);
        }else{
            OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(t_O_OrgName);
            if (organizationInfo.getT_O_listOrg().equalsIgnoreCase("on")){
                paramSearchMap.put("batch_PB_company", batch_PB_company);
                paramSearchMap.put("batch_PB_vendorcompany", organizationInfo.getT_O_OrgPending());
                List<PersonalInfoBatchUpload> personalInfoBatchUpload = personalInfoBatchUploadService.SelectCompanyBatch(paramSearchMap);
                model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
                rs.put("personalInfoBatchUpload", personalInfoBatchUpload);
                //input Personal into Org
            }else{
                paramSearchMap.put("batch_PB_company", batch_PB_company);
                List<PersonalInfoBatchUpload> personalInfoBatchUpload = personalInfoBatchUploadService.SelectCompanyBatch(paramSearchMap);
                model.addAttribute("personalInfoBatchUpload", personalInfoBatchUpload);
                rs.put("personalInfoBatchUpload", personalInfoBatchUpload);
            }
        }

        rs.put("ret", 0);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
//        return "personalInfoBatchUpload/personalInfoBatchUploadNew";
    }

    @RequestMapping(value = "personalInfoBatchUploadCompanyChange")
    @ResponseBody
    public String CompanyChange(String t_PIBU_Orgname, HttpServletRequest request,
                                HttpServletResponse response, Model model) {
        model.addAttribute("t_Org_name", t_PIBU_Orgname);
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramSearchMap = new HashMap<String, Object>();
        String t_O_OrgName = null;
        try {
            t_O_OrgName = URLDecoder.decode(t_PIBU_Orgname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(t_O_OrgName);
        paramSearchMap.put("t_FProd_CorpPool", organizationInfo.getT_O_Category());//添加元素
        List<FinanceProduct> FinanceProduct = financeProductService.findSearchList(paramSearchMap);
        model.addAttribute("FinanceProduct",FinanceProduct);
        rs.put("FinanceProduct", FinanceProduct);
        rs.put("ret", 0);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
//        return "personalInfoBatchUpload/personalInfoBatchUploadNew";
    }

    // download Excel template  	
    @RequestMapping(value ="personalInfoBatchUploadAdd", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<byte[]> downloadModel(@RequestParam("id") String id,HttpServletRequest req) throws InterruptedException {
//        RedisClient.getInstance().put("progress_"+id,0,60*60);
        ResponseEntity<byte[]> download = personalInfoBatchUploadService.download("files/personalInfoBatchUpload/员工信息表.xlsx", id, req);
//        RedisClient.getInstance().put("progress_"+id,100,60*60);
        return download;
    }

    @RequestMapping(value="personalInfoBatchUploadPullin")
    @ResponseBody
    public String upload(HttpServletRequest request,MultipartFile file,PersonalInfoBatchUpload personalInfoBatchUpload,String t_PIBU_Orgname,String t_FPROD_Name,
                         String payrollDate,String jobcat,Date EffectStartDate,Date EffectEndDate,Date TriggerTime,HttpServletResponse response, Model model) throws UnsupportedEncodingException{
        String CurrentCompany = ShiroSessionUtil.getLoginSession().getCompany_name();

        request.setCharacterEncoding("UTF-8");
        // String a = request.getParameter("message");

        StringBuffer PersonalInfoInputAreaText = new StringBuffer(request.getParameter("PersonalInfoInputArea"));
        String PersonalInfoInputArea = PersonalInfoInputAreaText.toString();
        
        model.addAttribute("TriggerTime", TriggerTime);
        System.out.println(t_FPROD_Name);
        OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(t_PIBU_Orgname);
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = df.format(date).replaceAll(" ","");
        String batch_PB_batchID = null;
        String FProd_name = null;
        Integer insertNum = 0;
        try {
            FProd_name = URLDecoder.decode(t_FPROD_Name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (TriggerTime == null) {
            StringBuffer ss =  new StringBuffer();
            batch_PB_batchID = String.valueOf(ss.append(datestr.substring(0, (datestr.length()))).append("_").append(t_PIBU_Orgname).append("_").append(file.getOriginalFilename()).append("_").append(FProd_name).append("_").append("RT"));
        }else{
            StringBuffer ss =  new StringBuffer();
            batch_PB_batchID = String.valueOf(ss.append(datestr.substring(0, (datestr.length()))).append("_").append(t_PIBU_Orgname).append("_").append(file.getOriginalFilename()).append("_").append(FProd_name).append("_").append("TT"));
        }
        ArrayList<ArrayList<String>> row = new ArrayList<>();
        // Sring result = ps.readExcelFile(file);
        System.out.println("进入员工个人信息文件上传");
        System.out.println(file.getOriginalFilename());
        System.out.print(PersonalInfoInputArea);

        if (PersonalInfoInputArea == null) {
            String filename = file.getOriginalFilename();
            Workbook workbook = null;
            try {
                InputStream Exlfile = file.getInputStream();
                if (judegExcelEdition(filename)) {
                    workbook = new XSSFWorkbook(Exlfile);
                } else {
                    workbook = new HSSFWorkbook(Exlfile);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //获取第一张工作表
            Sheet sheet = workbook.getSheetAt(0);
            //从第二行开始获取 getLastRowNum
            System.out.println("表总共多少行: " + sheet.getLastRowNum());
            System.out.println("实际共多少行: " + sheet.getPhysicalNumberOfRows());
            List<Map<String, Object>> cell = new ArrayList();
            Readxlscells:
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
                Map<String, Object> paramSQLmap = new HashMap<String, Object>();
                Map<String, Object> rs = new HashMap<String, Object>();
                paramSQLmap.put("batch_PB_ID", Tool.uuid());
                paramSQLmap.put("batch_PB_batchID", batch_PB_batchID);
                paramSQLmap.put("batch_PB_company", t_PIBU_Orgname);
                paramSQLmap.put("batch_PB_vendorcompany", organizationInfo.getT_O_OrgPending());
                paramSQLmap.put("batch_PB_payrolldate", payrollDate);
                paramSQLmap.put("batch_PB_effectDate", EffectStartDate);
                paramSQLmap.put("batch_PB_endDate", EffectEndDate);
                paramSQLmap.put("batch_PB_flag", "1");
                paramSQLmap.put("batch_createtime", new Date());
                paramSQLmap.put("batch_PB_fprod", FProd_name);
                paramSQLmap.put("batch_creator", ShiroSessionUtil.getLoginSession().getUserName());
                //循环获取工作表的每一行
                Row sheetRow = sheet.getRow(i);
                //循环获取每一列
                //		ArrayList<String> cell = new ArrayList<>();
                for (int j = 0; j < sheetRow.getPhysicalNumberOfCells(); j++) {
                    if (j == 0 && sheetRow.getCell(j).getStringCellValue().contains(String.valueOf("员工"))) {
                        break;
                    }
                    //		将每一个单元格的值装入列集合
                    switch (j) {
                        case 0:
                            sheetRow.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                            sheetRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println(sheetRow.getCell(j).getStringCellValue());
                            if (sheetRow.getCell(j).getStringCellValue() == null || sheetRow.getCell(j).getStringCellValue().equals("")) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "姓名错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow.getCell(j).getStringCellValue() + "'");
                                break Readxlscells;
                            } else {
                                paramSQLmap.put("batch_PB_Name", sheetRow.getCell(j).getStringCellValue().replaceAll(" ", ""));
                            }
                            break;
                        case 1:
                            sheetRow.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                            sheetRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println(sheetRow.getCell(j).getStringCellValue());
                            boolean IsPIDcard = IdCardUtil.validate18Idcard(sheetRow.getCell(j).getStringCellValue());
                            boolean IDerr = sheetRow.getCell(j).getStringCellValue().contains("E");
                            if (sheetRow.getCell(j).getStringCellValue() == null || !IsPIDcard || IDerr) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "身份证错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow.getCell(j).getStringCellValue().toUpperCase() + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserIdErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_PID", sheetRow.getCell(j).getStringCellValue().replaceAll(" ", ""));
                            }
                            break;
                        case 2:
                            sheetRow.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                            sheetRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println(sheetRow.getCell(j).getStringCellValue());
                            boolean IsBankCard = Tool.checkBankCard(sheetRow.getCell(j).getStringCellValue());
                            boolean BankCarderr = sheetRow.getCell(j).getStringCellValue().contains("E");
                            if (sheetRow.getCell(j).getStringCellValue() == null || !IsBankCard || BankCarderr) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "银行卡错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow.getCell(j).getStringCellValue() + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserDebitCardErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_creditCard", sheetRow.getCell(j).getStringCellValue().replaceAll(" ", "").toUpperCase());
                            }
                            break;
                        case 3:
                            sheetRow.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                            sheetRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println(sheetRow.getCell(j).getStringCellValue());
                            boolean ISChineseMobile = Tool.isChinaPhoneLegal(sheetRow.getCell(j).getStringCellValue());
                            if (sheetRow.getCell(j).getStringCellValue() == null || !ISChineseMobile) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "手机号错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow.getCell(j).getStringCellValue() + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserMobileErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_mobile", sheetRow.getCell(j).getStringCellValue().replaceAll(" ", ""));
                            }
                            break;
                        case 4:
                            sheetRow.getCell(j).setCellType(XSSFCell.CELL_TYPE_STRING);
                            sheetRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                            System.out.println(sheetRow.getCell(j).getStringCellValue());
                            if (sheetRow.getCell(j).getStringCellValue() == null) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "授额错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow.getCell(j).getStringCellValue() + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserCreditLineErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_credit", new BigDecimal(sheetRow.getCell(j).getStringCellValue()));
                                paramSQLmap.put("batch_PB_balance", new BigDecimal(sheetRow.getCell(j).getStringCellValue()));
                            }
                            break;
                    }
                }
                //将装有每一列的集合装入大集合
                if (paramSQLmap.get("batch_PB_PID") != null) {
                    cell.add(paramSQLmap);
                }
            }

            insertNum = personalInfoBatchUploadService.insertCustomerMachineByBatch(cell);

        }else{
            //获取第一张工作表
            //从第二行开始获取 getLastRowNum
            List<Map<String, Object>> cell = new ArrayList();
            String[] personal_col_info = PersonalInfoInputArea.split(";");
            System.out.print(personal_col_info.length);

            for (int i = 0; i < personal_col_info.length; i++) {
                Map<String, Object> paramSQLmap = new HashMap<String, Object>();
                Map<String, Object> rs = new HashMap<String, Object>();
                paramSQLmap.put("batch_PB_ID", Tool.uuid());
                paramSQLmap.put("batch_PB_batchID", batch_PB_batchID);
                paramSQLmap.put("batch_PB_company", t_PIBU_Orgname);
                paramSQLmap.put("batch_PB_vendorcompany", organizationInfo.getT_O_OrgPending());
                paramSQLmap.put("batch_PB_payrolldate", payrollDate);
                paramSQLmap.put("batch_PB_effectDate", EffectStartDate);
                paramSQLmap.put("batch_PB_endDate", EffectEndDate);
                paramSQLmap.put("batch_PB_flag", "1");
                paramSQLmap.put("batch_createtime", new Date());
                paramSQLmap.put("batch_PB_fprod", FProd_name);
                paramSQLmap.put("batch_creator", ShiroSessionUtil.getLoginSession().getUserName());
                //循环获取工作表的每一行
                String[] sheetRow = personal_col_info[i].split(",");
                //循环获取每一列
                //		ArrayList<String> cell = new ArrayList<>();
                if (sheetRow.length != 5) {
                    rs.put("retData", "-1");
                    rs.put("retMsg", "该行数据错误: " + "第" + i+1 + "行-" + sheetRow[i].trim().replace(" ","") + "'");
                    return JsonBizTool.genJson(ExRetEnum.PullinMutiText_Err, rs);
                }
                for (int j = 0; j < sheetRow.length; j++) {
                    if (j == 0 && sheetRow[j].contains(String.valueOf("员工"))) {
                        break;
                    }
                    //		将每一个单元格的值装入列集合
                    switch (j) {
                        case 0:
                            System.out.println(sheetRow[j]);
                            if (sheetRow[j].trim().replace(" ","") == null || sheetRow[j].trim().replace(" ","") .equals("")) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "姓名错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow[j].trim().replace(" ","") + "'");
                                break;
                            } else {
                                paramSQLmap.put("batch_PB_Name", sheetRow[j].trim().replace(" ",""));
                            }
                            break;
                        case 1:
                            System.out.println(sheetRow[j]);
                            boolean IsPIDcard = IdCardUtil.validate18Idcard(sheetRow[j].trim().replace(" ",""));
                            boolean IDerr = sheetRow[j].trim().replace(" ","").contains("E");
                            if (sheetRow[j].trim().replace(" ","") == null || !IsPIDcard || IDerr) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "身份证错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow[j].trim().replace(" ","").toUpperCase() + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserIdErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_PID", sheetRow[j].trim().replace(" ",""));
                            }
                            break;
                        case 2:
                            System.out.println(sheetRow[j]);
                            boolean IsBankCard = Tool.checkBankCard(sheetRow[j].trim().replace(" ",""));
                            boolean BankCarderr = sheetRow[j].trim().replace(" ","").contains("E");
                            if (sheetRow[j].trim().replace(" ","") == null || !IsBankCard || BankCarderr) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "银行卡错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow[j].trim().replace(" ","")+ "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserDebitCardErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_creditCard", sheetRow[j].trim().replace(" ","").toUpperCase());
                            }
                            break;
                        case 3:
                            System.out.println(sheetRow[j]);
                            boolean ISChineseMobile = Tool.isChinaPhoneLegal(sheetRow[j].trim().replace(" ",""));
                            if (sheetRow[j].trim().replace(" ","") == null || !ISChineseMobile) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "手机号错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow[j].trim().replace(" ","") + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserMobileErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_mobile", sheetRow[j].trim().replace(" ",""));
                            }
                            break;
                        case 4:
                            System.out.println(sheetRow[j]);
                            if (sheetRow[j].trim().replace(" ","") == null) {
                                int rowNum = i + 1;
                                int colNum = j + 1;
                                rs.put("retData", "-1");
                                rs.put("retMsg", "授额错误信息: " + "第" + rowNum + "行-" + "第" + colNum + "列 :" + "'" + sheetRow[j].trim().replace(" ","") + "'");
                                return JsonBizTool.genJson(ExRetEnum.Pullin_UserCreditLineErr, rs);
                            } else {
                                paramSQLmap.put("batch_PB_credit", new BigDecimal(sheetRow[j].trim().replace(" ","")));
                                paramSQLmap.put("batch_PB_balance", new BigDecimal(sheetRow[j].trim().replace(" ","")));
                            }
                            break;
                    }
                }
                //将装有每一列的集合装入大集合
                if (paramSQLmap.get("batch_PB_PID") != null) {
                    cell.add(paramSQLmap);
                }
            }

            insertNum = personalInfoBatchUploadService.insertCustomerMachineByBatch(cell);
        }


        if(insertNum != 0){
            //check dup debit card in upload batch
            List<PersonalInfoBatchUpload> retcode = personalInfoBatchUploadService.duplicateDebitCardChk(batch_PB_batchID);
            String errRcsDupDebitCard = null;
            if (retcode.size() != 0 || !retcode.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode.get(i).getBatch_PB_Name()).append("-").append(retcode.get(i).getBatch_PB_creditCard());
                    } else {
                        errRecord.append(",").append("'").append(retcode.get(i).getBatch_PB_Name()).append("-").append(retcode.get(i).getBatch_PB_creditCard()).append("'");
                    }
                }
                errRcsDupDebitCard = new String(errRecord);
            }

            if (errRcsDupDebitCard != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","重复银行卡记录信息: " + errRcsDupDebitCard);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupDebitCardErr, rs);
            }


            // check dup mobile with Manger Table
            List<PersonalInfoBatchUpload> retcode1 = personalInfoBatchUploadService.duplicateMobileChkTmanager(batch_PB_batchID);
            String errRcsDupMobileMgr = null;
            if (retcode1.size() != 0 || !retcode1.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode1.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode1.get(i).getBatch_PB_Name()).append("-").append(retcode1.get(i).getBatch_PB_mobile());
                    } else {
                        errRecord.append(",").append("'").append(retcode1.get(i).getBatch_PB_Name()).append("-").append(retcode1.get(i).getBatch_PB_mobile()).append("'");
                    }
                }
                errRcsDupMobileMgr = new String(errRecord);
            }

            if (errRcsDupMobileMgr != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","与系统主人员信息表重复手机号或一个身份证多个手机号记录信息: " + errRcsDupMobileMgr);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupMgrMobileErr, rs);
            }

            // check dup Mobile with t_personal table
            List<PersonalInfoBatchUpload> retcode2 = personalInfoBatchUploadService.duplicateMobileChkTperson(batch_PB_batchID);
            String errRcsDupMobilePer = null;
            if (retcode2.size() != 0 || !retcode2.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode2.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode2.get(i).getBatch_PB_Name()).append("-").append(retcode2.get(i).getBatch_PB_mobile());
                    } else {
                        errRecord.append(",").append("'").append(retcode2.get(i).getBatch_PB_Name()).append("-").append(retcode2.get(i).getBatch_PB_mobile()).append("'");
                    }
                }
                errRcsDupMobilePer = new String(errRecord);
            }

            if (errRcsDupMobilePer != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","与系统个人信息表重复手机号或一个身份证多个手机号记录信息: " + errRcsDupMobilePer);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupPerMobileErr, rs);
            }

            // check dup personal id with Manager table
            List<PersonalInfoBatchUpload> retcode3 = personalInfoBatchUploadService.duplicatePIDChk(batch_PB_batchID);
            String errRcsDupPID = null;
            if (retcode3.size() != 0 || !retcode3.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode3.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode3.get(i).getBatch_PB_Name()).append("-").append(retcode3.get(i).getBatch_PB_PID());
                    } else {
                        errRecord.append(",").append("'").append(retcode3.get(i).getBatch_PB_Name()).append("-").append(retcode3.get(i).getBatch_PB_PID()).append("'");
                    }
                }
                errRcsDupPID = new String(errRecord);
            }

            if (errRcsDupPID != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","与系统个人信息表重复身份证或一个手机号多个身份证记录信息: " + errRcsDupPID);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupPIDErr, rs);
            }

            //check dup mobile in the batch uploaded
            List<PersonalInfoBatchUpload> retcode4 = personalInfoBatchUploadService.checkDuplicateBatchUploadMobil(batch_PB_batchID);
            String errBatchDupMobile = null;
            if (retcode4.size() != 0 || !retcode4.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode4.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode4.get(i).getBatch_PB_Name()).append("-").append(retcode4.get(i).getBatch_PB_PID());
                    } else {
                        errRecord.append(",").append("'").append(retcode4.get(i).getBatch_PB_Name()).append("-").append(retcode4.get(i).getBatch_PB_PID()).append("'");
                    }
                }
                errBatchDupMobile = new String(errRecord);
            }

            if (errBatchDupMobile != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","上传表中有重复手机号记录信息: " + errBatchDupMobile);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupBatchMobileErr, rs);
            }

            //check dup personal id in the batch uploaded
            List<PersonalInfoBatchUpload> retcode5 = personalInfoBatchUploadService.checkDuplicateBatchUploadPID(batch_PB_batchID);
            String errBatchDupPID = null;
            if (retcode5.size() != 0 || !retcode5.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode5.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode5.get(i).getBatch_PB_Name()).append("-").append(retcode5.get(i).getBatch_PB_PID());
                    } else {
                        errRecord.append(",").append("'").append(retcode5.get(i).getBatch_PB_Name()).append("-").append(retcode5.get(i).getBatch_PB_PID()).append("'");
                    }
                }
                errBatchDupPID = new String(errRecord);
            }

            if (errBatchDupPID != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","上传表中有重复身份证记录信息: " + errBatchDupPID);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupBatchPIDErr, rs);
            }

            //check dup debit card in the batch uploaded
            List<PersonalInfoBatchUpload> retcode6 = personalInfoBatchUploadService.checkDuplicateBatchUploadDebitCard(batch_PB_batchID);
            String errBatchDupDebitCard = null;
            if (retcode6.size() != 0 || !retcode6.isEmpty()){
                StringBuffer errRecord = new StringBuffer();
                for (int i = 0; i < retcode6.size(); i++) {
                    if (i == 0) {
                        errRecord.append("'").append(retcode6.get(i).getBatch_PB_Name()).append("-").append(retcode6.get(i).getBatch_PB_PID());
                    } else {
                        errRecord.append(",").append("'").append(retcode6.get(i).getBatch_PB_Name()).append("-").append(retcode6.get(i).getBatch_PB_PID()).append("'");
                    }
                }
                errBatchDupDebitCard = new String(errRecord);
            }

            if (errBatchDupDebitCard != null) {
                Map<String, Object> rs = new HashMap<String, Object>();
                int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
                rs.put("retData","-1");
                rs.put("retMsg","上传表中有重复银行卡记录信息: " + errBatchDupDebitCard);
                return JsonBizTool.genJson(ExRetEnum.Pullin_FailDupBatchPIDErr, rs);
            }
        }


        StringBuffer ss = new StringBuffer();
        String succNum = null;

        if (insertNum != 0) {
            succNum = String.valueOf(ss.append("总共成功上传条数: ").append(Integer.toString(insertNum)));
        }else{
            succNum = String.valueOf(ss.append("插入失败，请检查更正数据后再上传"));
        }

        return succNum;
    }



    @RequestMapping(value = "personalInfoBatchUpdateSub")
    @ResponseBody
    public String PersonalInfoBatchUpdateSub(HttpServletRequest request,PersonalInfoBatchUpload personalInfoBatchUpload,String PersonalInfoInputArea,
                                             String batch_PB_batchID,String t_PIBU_Orgname,String t_FPROD_Name,String payrollDate,String jobcat,Date EffectStartDate,Date EffectEndDate,
                                             Date TriggerTime,HttpServletResponse response, Model model) throws UnsupportedEncodingException{
        String CurrentCompany = ShiroSessionUtil.getLoginSession().getCompany_name();
        OrganizationInfo organizationInfo = organizationInfoService.selectAgencyName(CurrentCompany);
        model.addAttribute("TriggerTime", EffectStartDate);
        model.addAttribute("EffectStartDate", EffectStartDate);
        model.addAttribute("EffectEndDate", EffectEndDate);
        System.out.println(t_FPROD_Name);
        model.addAttribute("t_Org_name", t_PIBU_Orgname);
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> paramSearchMap = new HashMap<String, Object>();
        String t_O_OrgName = null;
        try {
            t_O_OrgName = URLDecoder.decode(t_PIBU_Orgname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        paramMap.put("batch_PB_batchID",personalInfoBatchUpload.getBatch_PB_batchID());
        paramMap.put("t_P_VendorCompany", organizationInfo.getT_O_OrgPending());//添加元素
        paramMap.put("t_P_Company",t_PIBU_Orgname);
        paramMap.put("payrollDate",payrollDate);
        paramMap.put("jobcat",jobcat);
        //假设这是一个service类的片段
        int insertMangerNum = 0;
        int insertBatchPersonalNum = 0;

        try{
            insertMangerNum = personalInfoBatchUploadService.insertManagerInfo(paramMap);
            //出现异常
        } catch (Exception e) {
            e.printStackTrace();
            //设置手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        if(insertMangerNum != 0){

            try{
                int insertTrManagerTxnNum = personalInfoBatchUploadService.insertRoleTrManagerInfoTxn(paramMap);
                //出现异常
            } catch (Exception e) {
                e.printStackTrace();
                //设置手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            try{
                int insertTrManagerTxnViewNum = personalInfoBatchUploadService.insertRoleTrManagerInfoTxnview(paramMap);
                //出现异常
            } catch (Exception e) {
                e.printStackTrace();
                //设置手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }

            try{
                insertBatchPersonalNum = personalInfoBatchUploadService.insertBatchPersonalInfo(paramMap);
                //出现异常
            } catch (Exception e) {
                e.printStackTrace();
                //设置手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }

        try {
            batch_PB_batchID = URLDecoder.decode(personalInfoBatchUpload.getBatch_PB_batchID(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        paramMap.put("batch_PB_batchID", batch_PB_batchID);
        paramMap.put("t_batch_EffectStartDate", EffectStartDate);
        paramMap.put("t_batch_EffectEndDate", EffectEndDate);
        paramMap.put("creator", ShiroSessionUtil.getLoginSession().getId());

        int insertSelectiveNum = personalInfoBatchUploadStatusService.insertSelective(paramMap);

        if(insertBatchPersonalNum == insertMangerNum && insertSelectiveNum != 0) {
            rs.put("insertMangerNum",insertMangerNum);
            rs.put("retMsg","成功添加一批: " + "其中新增人员数: " + insertMangerNum);
            return JsonBizTool.genJson(ExRetEnum.Pullin_SuccNewPer, rs);
        }else if(insertBatchPersonalNum != insertMangerNum && insertBatchPersonalNum != 0 ){
            String insertErr = null;
            StringBuffer sb = new StringBuffer();
            int deleteUpload = personalInfoBatchUploadService.deleteByPrimaryKey(batch_PB_batchID);
            insertErr = String.valueOf(sb.append("上传住信息表数:").append(insertMangerNum).append(",").append("上传个人信息表数:").append(insertBatchPersonalNum));
            rs.put("retData","-1");
            rs.put("retMsg","重复银行卡记录信息: " + insertErr);
            return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
        }
        return "personalInfoBatchUpload/personalInfoBatchUploadNew";

    }



    // CLose PersonalBatchStatus	
    @RequestMapping(value ="personalInfoBatcStatusClose")
    @ResponseBody
    public String PersonalInfoBatcStatusClose(String t_PIBU_Orgname, HttpServletRequest request,String batch_PB_batchID,
                                              HttpServletResponse response, Model model) {
        model.addAttribute("t_Org_name", t_PIBU_Orgname);
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String t_O_OrgName = null;
        try {
            t_O_OrgName = URLDecoder.decode(t_PIBU_Orgname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        int updateBatchPersonalStatusCloseNum = personalInfoBatchUploadService.updateBatchPersonalStatusClose(paramMap);

        rs.put("ret", 0);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
//    return "personalInfoBatchUpload/personalInfoBatchUploadNew";
    }


    @RequestMapping(value ="personalInfoBatcStatusOpenRefresh")
    @ResponseBody
    public String personalInfoBatcStatusOpenRefresh(String t_PIBU_Orgname, HttpServletRequest request,String batch_PB_batchID,String operationType,
                                                    HttpServletResponse response, Model model) {
        model.addAttribute("t_Org_name", t_PIBU_Orgname);
        Map<String, Object> rs = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String t_O_OrgName = null;
        try {
            t_O_OrgName = URLDecoder.decode(t_PIBU_Orgname,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int updateBatchPersonalTxnClearingNum = personalInfoBatchUploadService.updateBatchPersonalTxnClearing(paramMap);

        int deleteByRefreshBatchPersonalCreditNum = personalInfoBatchUploadService.deleteByRefreshBatchPersonalCredit(paramMap);

        rs.put("ret", 0);
        return JsonBizTool.genJson(ExRetEnum.SUCCESS, rs);
//    return "personalInfoBatchUpload/personalInfoBatchUploadNew";
    }

}