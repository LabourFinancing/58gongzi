package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class CollectionDemo{
	private static String transCode = "RTCO";	//实时代收
	
	public static String genRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", "510000010218");
		
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000002");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		jsonObject.put("tranTime", df.format(new Date()));
		
//		jsonObject.put("timeOut", "20161024120000");
		jsonObject.put("tranAmt", "000000000500");
		jsonObject.put("currencyCode", "156");
		
		jsonObject.put("accAttr", "0");
		jsonObject.put("accType", "4");
//		jsonObject.put("accNo", "6228482830891396812");	//处理中
//		jsonObject.put("accNo", "6228482830891396811");	//失败
//		jsonObject.put("accNo", "6228482830891396810");	//成功

		jsonObject.put("accNo", "6226220209634996");
		jsonObject.put("accName", "蒙世花");
		jsonObject.put("certNo", "35210119770917002X");
		jsonObject.put("cardId", "35210119770917002X");
		jsonObject.put("phone", "15721447947");
		

		jsonObject.put("bankName", "农业银行");
		jsonObject.put("provNo", "350000");
		jsonObject.put("cityNo", "350200");	
		jsonObject.put("certType", "0101");
//		jsonObject.put("bankInsCode", "01030000");
		jsonObject.put("purpose", "collection");
		jsonObject.put("reqReserved", "请求方保留测试1");
		return jsonObject.toJSONString();
	}
	public static void main(String args[]) throws Exception{
		
		//装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
		//设置商户号
		String merchId = "S2135052";
		
		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		//拼接本交易URL
		url += "collection";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest());
		System.out.println("retData:" + data);
	}
	
}
