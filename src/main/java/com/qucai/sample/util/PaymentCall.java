package com.qucai.sample.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.common.PageParam;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet.PayServlet;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo.AgentPayDemo;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PaymentCall {

    public static Map<String, Object> PersonalEwalletCashout(String PaymentSwitch,StaffPrepayApplicationPayment PersonalApplicationPay) throws Exception {

        Map<String,Object> retPaymentResult = new HashMap<>();
        String RCretData = null,remark = null,TxnID = Tool.PayId();
        String merchantId = null;
        switch (PaymentSwitch) {
            case "shsd" :
                merchantId = "S2135052";
                break;
            case "shdy" :
                merchantId = "872684173615000";
                break;
            default:
                merchantId = "S2135052";
                break;
        }

        if (PaymentSwitch.equals("shsd")){
            PersonalApplicationPay.setCompany(merchantId);
            PersonalApplicationPay.setVersion("sandpay");
            PersonalApplicationPay.setReqReserved("sandpay");
            //payto goldmanfuks sandpay pub account for cashout preparation
            JSONObject obj = AgentPayDemo.main(PersonalApplicationPay,merchantId);  // sandpay
            RCretData = (String) obj.get("respCode"); //  sandpay branch
            remark = (String) obj.get("respDesc"); //  sandpay branch
            retPaymentResult.put("respCode",RCretData);
            retPaymentResult.put("respDesc",remark);
            if(RCretData.equals("4001")){
                PersonalApplicationPay.setRCcode(RCretData);
                PersonalApplicationPay.setReturnPic("Other");
                PersonalApplicationPay.setRemark(remark);
                PersonalApplicationPay.setCompany(merchantId);
//                staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
                System.out.println("Err txn log:");
                System.out.println(TxnID);
                return retPaymentResult;
            }
        }else if (PaymentSwitch.equals("shdy")){
            PersonalApplicationPay.setCompany(merchantId);
            PersonalApplicationPay.setVersion("Chinaebi");
            PersonalApplicationPay.setReqReserved("Chinaebi");
            String retData = PayServlet.main(PersonalApplicationPay,merchantId);  // Chinaebipay
            JSONObject obj = (JSONObject) JSON.parse(retData);
            RCretData = (String) obj.get("transState"); //  Chinaebipay branch
            String RespMsg = (String) obj.get("rspCode"); //  Chinaebipay branch
            remark = (String) obj.get("rspMessage");
            retPaymentResult.put("respCode",RCretData);
            retPaymentResult.put("RespMsg",RespMsg);
            retPaymentResult.put("respDesc",remark);
            if(RespMsg.equalsIgnoreCase("ACM20048")){
                PersonalApplicationPay.setRCcode(RCretData);
                PersonalApplicationPay.setReturnPic("Other");
                PersonalApplicationPay.setRemark(remark);
                PersonalApplicationPay.setCompany(merchantId);
//                staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
                System.out.println("Err txn log:");
                System.out.println(TxnID);
                return retPaymentResult;
            }
        }
        //check personal payment limitation
        System.out.println("倒叙排列结果为：" + PersonalApplicationPay);
        return retPaymentResult;
    }
}
