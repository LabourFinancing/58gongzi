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

public class PaymentRoute {

    public static Map<String, Object> PersonalEwalletCashout(String PaymentSwitch,StaffPrepayApplicationPayment personalApplicationPay) throws Exception {

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
            personalApplicationPay.setCompany(merchantId);
            personalApplicationPay.setVersion("sandpay");
            personalApplicationPay.setReqReserved("sandpay");
            //payto goldmanfuks sandpay pub account for cashout preparation
            JSONObject obj = AgentPayDemo.main(personalApplicationPay,merchantId);  // sandpay
            RCretData = (String) obj.get("respCode"); //  sandpay branch
            remark = (String) obj.get("respDesc"); //  sandpay branch
            retPaymentResult.put("respCode",RCretData);
            retPaymentResult.put("respDesc",remark);
            if(RCretData.equals("4001")){
                personalApplicationPay.setRCcode(RCretData);
                personalApplicationPay.setReturnPic("Other");
                personalApplicationPay.setRemark(remark);
                personalApplicationPay.setCompany(merchantId);
//                staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
                retPaymentResult.put("TxnID",TxnID);
                retPaymentResult.put("errCode","Err");
            }
            return retPaymentResult;
        }else if (PaymentSwitch.equals("shdy")){
            personalApplicationPay.setCompany(merchantId);
            personalApplicationPay.setVersion("Chinaebi");
            personalApplicationPay.setReqReserved("Chinaebi");
            String retData = PayServlet.main(personalApplicationPay,merchantId);  // Chinaebipay
            JSONObject obj = (JSONObject) JSON.parse(retData);
            RCretData = (String) obj.get("transState"); //  Chinaebipay branch
            String RespMsg = (String) obj.get("rspCode"); //  Chinaebipay branch
            remark = (String) obj.get("rspMessage");
            retPaymentResult.put("respCode",RCretData);
            retPaymentResult.put("RespMsg",RespMsg);
            retPaymentResult.put("respDesc",remark);
            if(RespMsg.equalsIgnoreCase("ACM20048")){
                personalApplicationPay.setRCcode(RCretData);
                personalApplicationPay.setReturnPic("Other");
                personalApplicationPay.setRemark(remark);
                personalApplicationPay.setCompany(merchantId);
//                staffPrepayApplicationService.insertPayment(staffPrepayApplicationPay);
                retPaymentResult.put("TxnID",TxnID);
                retPaymentResult.put("errCode","Err");
            }
        }
        //check personal payment limitation
        System.out.println("倒叙排列结果为：" + personalApplicationPay);
        return retPaymentResult;
    }
}
