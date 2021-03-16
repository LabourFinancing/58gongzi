package com.qucai.sample.daifudemo.src.com.chinaebi.pay.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * 公共类
 */
public class Common {
	
	private static Properties properties = null;
	private static String rootPath = null;	
	public static final String CHAR_SET_UTF8 = "UTF-8";
	public static final String CHAR_SET_GBK = "GBK";

	private Common(){}

	static{
		try {
			rootPath = URLDecoder.decode(Common.class.getResource("/").getPath(), CHAR_SET_UTF8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		initProperties();
	}
	
	private static Properties initProperties(){
		if(properties == null){
			// 定义一个properties文件的名字
			String propFile = "url20201123145948.properties";
			// 定义一个properties对象
			properties = new Properties();
			// 读取properties
			InputStream file = Common.class.getClassLoader().getResourceAsStream(propFile);
			// 加载properties文件
			try {
				properties.load(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return properties;
	}
	/**
	 * 接口地址
	 * @return
	 */
	public static String getPayUrl(){
        return getProperties("pay_url");
	}
	/**
	 * 商户证书
	 * @return
	 */
	public static String getMerchantCertPath(){
        return rootPath + getProperties("merchant_cert");
	}
	/**
	 * 商户证书密码
	 * @return
	 */
	public static String getMerchantCertPwd(){
		return getProperties("merchant_cert_pwd");
	}
	/**
	 * 电银公钥证书（测试）
	 * @return
	 */
	public static String getShdyPublicCertTest(){
        return rootPath + getProperties("check_sign_test");
	}
	/**
	 * 电银公钥证书（生产）
	 * @return
	 */
	public static String getShdyPublicCertProd(){
        return rootPath + getProperties("check_sign_prod");
	}
	
	/**
	 * 读取配置参数
	 * @param name
	 * @return
	 */
	public static String getProperties(String name) {
		return properties.getProperty(name);
	}

}
