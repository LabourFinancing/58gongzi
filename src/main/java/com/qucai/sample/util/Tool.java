package com.qucai.sample.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import com.qucai.sample.common.PageParam;

public class Tool {

    public static String StringSeq(String StrInput) {
        //字符串倒叙排列

        //方法一：字符串转字节数组toCharArray，再for循环遍历
        String str = StrInput;

        char[] ch=str.toCharArray();
        String strNew ="";
        for(int i = ch.length - 1;i>=0;i--){
            strNew +=ch[i];
        }
        System.out.println("字符串"+str+"倒叙排列结果为："+strNew);
        return strNew;
    }

	public static PageParam genPageParam(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum") == null ? "1"
				: request.getParameter("pageNum");
		String pageSize = request.getParameter("pageSize") == null ? "10"
				: request.getParameter("pageSize");
		PageParam pp = new PageParam();
		pp.setPageNum(Integer.parseInt(pageNum));
		pp.setPageSize(Integer.parseInt(pageSize));
		return pp;
	}

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String genPassword(int length) {
		String rs = "";
		Random random = new Random();
		// 参数8，表示生成8位随机数
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				rs += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				rs += String.valueOf(random.nextInt(10));
			}
		}
		return rs;
	}
	
	public static BigDecimal gent_FProd_TierPoundage(String t_FProd_TierPoundage,BigDecimal t_Txn_ApplyPrepayAmount) {
		BigDecimal calc_TierPoundage = null,calc_TierPoundage_initial = null;
		double[][] Tier_details;
        if ( t_FProd_TierPoundage.equals("") || t_FProd_TierPoundage.equals(null) ){
			calc_TierPoundage = new BigDecimal(0);
		}else{
    		 String[] arr = t_FProd_TierPoundage.split(";");
			 Tier_details = new double[arr.length][];
		    	Integer big_arr = arr.length - 1;
			    for(int i=0; i<arr.length; i++) {
			        String[] sSecond = arr[i].split(",");
				    Tier_details[i] = new double[sSecond.length];
				    for(int j=0;j<sSecond.length;j++){
				    	Tier_details[i][j] = Double.parseDouble(sSecond[j]);
				    }
				    if (Tier_details[i][0] > t_Txn_ApplyPrepayAmount.doubleValue() ){
				    	calc_TierPoundage = new BigDecimal(0);
				    }else if ( Tier_details[i][0] < t_Txn_ApplyPrepayAmount.doubleValue() && Tier_details[i][1] >= t_Txn_ApplyPrepayAmount.doubleValue()){
				        calc_TierPoundage_initial = new BigDecimal(Tier_details[i][2]);
				        calc_TierPoundage = compareNumber(calc_TierPoundage_initial,t_Txn_ApplyPrepayAmount);		        	
				    	break;
				    }else if ( i == big_arr ){
				    	if (Tier_details[big_arr][1] < t_Txn_ApplyPrepayAmount.doubleValue()){
					        calc_TierPoundage_initial = new BigDecimal(Tier_details[i][2]);
					        calc_TierPoundage = compareNumber(calc_TierPoundage_initial,t_Txn_ApplyPrepayAmount);	
					    	break;
				    	}else{
				    		System.out.println("Tier Poundge Fee is out of range");
				    		break;
				    	}
				    }
			      }
	    }
		return calc_TierPoundage;
	}
	
	public static boolean isChinaPhoneLegal(String str)  
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(16[5-7])|(17[0-8])|(18[0-9])|(19[0-9])|(14[5-8]))\\d{8}$"; 
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
	
	public class CheckBankCard {
	    /*
	    校验过程： 
	    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。 
	    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。 
	    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。       
	    */   
	        /** 
	        * 校验银行卡卡号 
	        */  
	       public boolean checkBankCard(String bankCard) {  
	                if(bankCard.length() < 15 || bankCard.length() > 19) {
	                    return false;
	                }
	                char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));  
	                if(bit == 'N'){  
	                    return false;  
	                }  
	                return bankCard.charAt(bankCard.length() - 1) == bit;  
	       }  

	       /** 
	        * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
	        * @param nonCheckCodeBankCard 
	        * @return 
	        */  
	       public char getBankCardCheckCode(String nonCheckCodeBankCard){  
	           if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0  
	                   || !nonCheckCodeBankCard.matches("\\d+")) {  
	               //如果传的不是数据返回N  
	               return 'N';  
	           }  
	           char[] chs = nonCheckCodeBankCard.trim().toCharArray();  
	           int luhmSum = 0;  
	           for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {  
	               int k = chs[i] - '0';  
	               if(j % 2 == 0) {  
	                   k *= 2;  
	                   k = k / 10 + k % 10;  
	               }  
	               luhmSum += k;             
	           }  
	           return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');  
	       } 
	
	}
	
	public static BigDecimal compareNumber(BigDecimal calc_TierPoundage_initial, BigDecimal t_Txn_ApplyPrepayAmount){
		BigDecimal calc_TierPoundage = null;
        if (!"".equals(calc_TierPoundage_initial) && calc_TierPoundage_initial != null){
            if (new BigDecimal(calc_TierPoundage_initial.intValue()).compareTo(calc_TierPoundage_initial)==0){
                //整数
            	calc_TierPoundage = calc_TierPoundage_initial;
            }else {
                //小数
            	calc_TierPoundage = t_Txn_ApplyPrepayAmount.multiply(calc_TierPoundage_initial).setScale(2, BigDecimal.ROUND_UP);
            }
        }
        return calc_TierPoundage;
    }
	

	public static String PayId() {
		SimpleDateFormat PayIdHead = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		double PayRadom = ((Math.random()*9+1)*100);
		String PayNum = String.valueOf(PayRadom).substring(0,String.valueOf(PayRadom).indexOf("."));
		StringBuffer ps = new StringBuffer();
		String PayId = String.valueOf(ps.append(PayIdHead.format(new Date()).toString()).append(PayNum));
		return PayId;
	}
	
	public static String BrowserFilter(HttpServletRequest request) {
		String userName = ShiroSessionUtil.getLoginSession().getUserName();
		 String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",  
                 "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod", "oppo", 
                 "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma", "vivo",
                 "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",  
                 "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",  
                 "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",  
                 "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",  
                 "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",  
                 "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",  
                 "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",  
                 "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",  
                 "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",  
                 "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",  
                 "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",  
                 "Googlebot-Mobile" };  
         String host = null;
		if (request.getHeader("User-Agent") != null) {  
             String agent=request.getHeader("User-Agent");  
             for (String mobileAgent : mobileAgents) {  
                 if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {  
                     if (userName.contains("admin")) {
                         host = "P";
                     } else {
                         host = "M" ;
                         break;
                     }
                   }
                }
             }
		if (host == null){  // fix it later
			host = "P";
		}
		return host;    
	}
	
	/** 
	   * 校验银行卡卡号 
	   * 
	   * @param cardId 
	   * @return 
	   */ 
	  public static boolean checkBankCard(String cardId) { 
	    char bit = getBankCardCheckCode(cardId 
	        .substring(0, cardId.length() - 1)); 
	    return cardId.charAt(cardId.length() - 1) == bit; 
	  } 
	  /** 
	   * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位 
	   * 
	   * @param nonCheckCodeCardId 
	   * @return 
	   */ 
	  public static char getBankCardCheckCode(String nonCheckCodeCardId) { 
	    int cardLenth = nonCheckCodeCardId.trim().length(); 
	    if (nonCheckCodeCardId == null || cardLenth == 0 
	        || !nonCheckCodeCardId.matches("\\d+")) { 
//	      throw new IllegalArgumentException("不是银行卡的卡号!"); 
	      char err = (char) 0;
		  return err;
	    } 
	    char[] chs = nonCheckCodeCardId.trim().toCharArray(); 
	    int luhmSum = 0; 
	    for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) { 
	      int k = chs[i] - '0'; 
	      if (j % 2 == 0) { 
	        k *= 2; 
	        k = k / 10 + k % 10; 
	      } 
	      luhmSum += k; 
	    } 
	    return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0'); 
	  } 
}
