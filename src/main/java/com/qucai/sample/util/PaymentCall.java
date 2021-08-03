package com.qucai.sample.util;

import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalTreasuryCtrl;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PaymentCall {

    public static Map<String, Object> Cashout(StaffPrepayApplicationPayment PersonalApplicationPay) {

        Map<String,Object> retPaymentResult = new HashMap<>();
        //check personal payment limitation
        System.out.println("倒叙排列结果为：" + PersonalApplicationPay);
        return retPaymentResult;
    }
}
