package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import java.text.SimpleDateFormat;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class QueryOrderDemo{
	private static String transCode = "ODQU";	//订单查询
	private static Object put;
	
	public static String genRequest(String OrderCode,String TranTime) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", OrderCode);
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000004");
		jsonObject.put("tranTime", TranTime);	
		
		System.out.println("QueryOrder Result: ");
		System.out.println(jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	public static String QueryOrder(String OrderCode,String TranTime) throws Exception{
		
		// 装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig" });
		// 设置商户号
		String merchId = "S2135052";

		// 读取配置中公共URL
		String url = DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		// 拼接本交易URL
		url += "queryOrder";
		// 创建http辅助工具
		HttpUtil httpUtil = new HttpUtil();
		// 通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest(OrderCode,TranTime));
		System.out.println("retData:" + data);
		return data;
	}
}
