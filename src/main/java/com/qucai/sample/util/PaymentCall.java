package com.qucai.sample.util;

import com.qucai.sample.common.PageParam;
import com.qucai.sample.entity.PersonalTreasuryCtrl;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PaymentCall {

    public static Map<String, Object> Cashout(Map<String, Object> StrInput) {

        Map<String,Object> retPaymentResult = new HashMap<>();
        //check personal payment limitation
        System.out.println("倒叙排列结果为：" + StrInput);
        return retPaymentResult;
    }
//
//    public static PageParam genPageParam(HttpServletRequest request) {
//        String pageNum = request.getParameter("pageNum") == null ? "1"
//            : request.getParameter("pageNum");
//        String pageSize = request.getParameter("pageSize") == null ? "10"
//            : request.getParameter("pageSize");
//        PageParam pp = new PageParam();
//        pp.setPageNum(Integer.parseInt(pageNum));
//        pp.setPageSize(Integer.parseInt(pageSize));
//        return pp;
//    }
//
//    public static String uuid() {
//        return UUID.randomUUID().toString().replaceAll("-", "");
//    }
//
//    public static String genPassword(int length) {
//        String rs = "";
//        Random random = new Random();
//        // 参数8，表示生成8位随机数
//        for (int i = 0; i < length; i++) {
//            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
//            // 输出字母还是数字
//            if ("char".equalsIgnoreCase(charOrNum)) {
//                // 输出是大写字母还是小写字母
//                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
//                rs += (char) (random.nextInt(26) + temp);
//            } else if ("num".equalsIgnoreCase(charOrNum)) {
//                rs += String.valueOf(random.nextInt(10));
//            }
//        }
//        return rs;
//    }
//
//    public static BigDecimal gent_PeronalTreasuryEst(String t_FProd_TierPoundage,BigDecimal t_Txn_ApplyPrepayAmount) {
//        BigDecimal calc_TierPoundage = null,calc_TierPoundage_initial = null;
//        double[][] Tier_details;
//        if ( t_FProd_TierPoundage.equals("") || t_FProd_TierPoundage.equals(null) ){
//            calc_TierPoundage = new BigDecimal(0);
//        }else{
//            String[] arr = t_FProd_TierPoundage.split(";");
//            Tier_details = new double[arr.length][];
//            Integer big_arr = arr.length - 1;
//            for(int i=0; i<arr.length; i++) {
//                String[] sSecond = arr[i].split(",");
//                Tier_details[i] = new double[sSecond.length];
//                for(int j=0;j<sSecond.length;j++){
//                    Tier_details[i][j] = Double.parseDouble(sSecond[j]);
//                }
//                if (Tier_details[i][0] > t_Txn_ApplyPrepayAmount.doubleValue() ){
//                    calc_TierPoundage = new BigDecimal(0);
//                }else if ( Tier_details[i][0] < t_Txn_ApplyPrepayAmount.doubleValue() && Tier_details[i][1] >= t_Txn_ApplyPrepayAmount.doubleValue()){
//                    calc_TierPoundage_initial = new BigDecimal(Tier_details[i][2]);
//                    calc_TierPoundage = compareNumber(calc_TierPoundage_initial,t_Txn_ApplyPrepayAmount);
//                    break;
//                }else if ( i == big_arr ){
//                    if (Tier_details[big_arr][1] < t_Txn_ApplyPrepayAmount.doubleValue()){
//                        calc_TierPoundage_initial = new BigDecimal(Tier_details[i][2]);
//                        calc_TierPoundage = compareNumber(calc_TierPoundage_initial,t_Txn_ApplyPrepayAmount);
//                        break;
//                    }else{
//                        System.out.println("Tier Poundge Fee is out of range");
//                        break;
//                    }
//                }
//            }
//        }
//        return calc_TierPoundage;
//    }
//
//    public static boolean isChinaPhoneLegal(String str)
//        throws PatternSyntaxException {
//        String regExp = "^((13[0-9])|(15[^4])|(16[5-7])|(17[0-8])|(18[0-9])|(19[0-9])|(14[5-8]))\\d{8}$";
//        Pattern p = Pattern.compile(regExp);
//        Matcher m = p.matcher(str);
//        return m.matches();
//    }
//
//    public class CheckBankCard {
//	    /*
//	    校验过程： 
//	    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。 
//	    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。 
//	    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。       
//	    */
//        /**
//         * 校验银行卡卡号 
//         */
//        public boolean checkBankCard(String bankCard) {
//            if(bankCard.length() < 15 || bankCard.length() > 19) {
//                return false;
//            }
//            char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
//            if(bit == 'N'){
//                return false;
//            }
//            return bankCard.charAt(bankCard.length() - 1) == bit;
//        }
//
//        /**
//         * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
//         * @param nonCheckCodeBankCard
//         * @return
//         */
//        public char getBankCardCheckCode(String nonCheckCodeBankCard){
//            if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
//                || !nonCheckCodeBankCard.matches("\\d+")) {
//                //如果传的不是数据返回N  
//                return 'N';
//            }
//            char[] chs = nonCheckCodeBankCard.trim().toCharArray();
//            int luhmSum = 0;
//            for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
//                int k = chs[i] - '0';
//                if(j % 2 == 0) {
//                    k *= 2;
//                    k = k / 10 + k % 10;
//                }
//                luhmSum += k;
//            }
//            return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
//        }
//
//    }
//
//    public static BigDecimal compareNumber(BigDecimal calc_TierPoundage_initial, BigDecimal t_Txn_ApplyPrepayAmount){
//        BigDecimal calc_TierPoundage = null;
//        if (!"".equals(calc_TierPoundage_initial) && calc_TierPoundage_initial != null){
//            if (new BigDecimal(calc_TierPoundage_initial.intValue()).compareTo(calc_TierPoundage_initial)==0){
//                //整数
//                calc_TierPoundage = calc_TierPoundage_initial;
//            }else {
//                //小数
//                calc_TierPoundage = t_Txn_ApplyPrepayAmount.multiply(calc_TierPoundage_initial).setScale(2, BigDecimal.ROUND_UP);
//            }
//        }
//        return calc_TierPoundage;
//    }
//
//
//    public static String PayId() {
//        SimpleDateFormat PayIdHead = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        double PayRadom = ((Math.random()*9+1)*100);
//        String PayNum = String.valueOf(PayRadom).substring(0,String.valueOf(PayRadom).indexOf("."));
//        StringBuffer ps = new StringBuffer();
//        String PayId = String.valueOf(ps.append(PayIdHead.format(new Date()).toString()).append(PayNum));
//        return PayId;
//    }
//
//    public static Map<String, Object> PersonalTreasuryChk(PersonalTreasuryCtrl MobilePersonalTreasuryCtrl){
//        Map<String,Object> PersonalTreasuryChk = new HashMap<String, Object>();
//
//        return PersonalTreasuryChk;
//    }
    
}
