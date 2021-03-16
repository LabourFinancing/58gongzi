package com.qucai.sample.util;

import java.util.regex.Pattern;
//*************************** 辅助方法三 *****************************************************************
/**
 *   方法说明：
 *              校验上传数据
 *
 *
 * @param orgId     其应该所属的组织
 * @param deptId    所选的部门id
 * @param excelData excel表格数据对象
 * @param mobiles   所有的手机号码 集合   用来判断手机是否注册
 * @param emails    所有的邮箱号码 集合
 * @return
 */

/**
 * 文件操作工具类
 */
public class PersonalInfoDataValidate {


    /*public static void main(String[] args) {
        String mobile = "18412312313";
        String phone = "010-12312312";
        String username = "fdsdfsdj";
        System.out.println(Validator.isUsername(username));
        System.out.println(Validator.isChinese(username));

        String email = "zhangsan@163.com";
        System.out.println("isMobile="+Validator.isMobile(mobile));
        System.out.println("isPhone="+Validator.isPhone(phone));
        String regex = "^zo+$";
        String str = "zozo";
        boolean flag = Validator.testString(regex, str);
        System.out.println(flag);
    }

*/
    public static boolean testString(String regex, String str){
        return Pattern.matches(regex, str);
    }


    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";

    /**
     * 正则表达式：验证固话
     */
    public static final String REGEX_PHONE = "^(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})|(0\\d{3}-\\d{8})$";

    /**
     * 正则表达式：验证qq
     */
    public static final String REGEX_QQ = "^[1-9][0-9]{4,} $";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^[A-Za-z0-9][\\w\\-\\.]{1,12}@([\\w\\\\-]+\\.)+[\\w]{2,3}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
//    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验固话
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        return Pattern.matches(REGEX_PHONE, phone);
    }

    /**
     * 校验qq
     * @param qq
     * @return
     */
    public static boolean isQQ(String qq){
        return Pattern.matches(REGEX_QQ, qq);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
//    public static boolean isUrl(String url) {
//        return Pattern.matches(REGEX_URL, url);
//    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

}