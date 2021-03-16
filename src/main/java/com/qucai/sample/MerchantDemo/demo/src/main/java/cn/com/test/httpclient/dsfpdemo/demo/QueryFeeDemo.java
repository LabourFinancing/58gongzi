package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;


import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class QueryFeeDemo {
	private static String transCode = "PTFQ";	//平台使用费计算
	private static String genRequest() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", "100000000010");
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000004");
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		jsonObject.put("tranTime", df.format(new Date()));
		jsonObject.put("tranAmt", "000000020000");
		jsonObject.put("currencyCode", "156");
		jsonObject.put("accAttr", "0");
		jsonObject.put("accType", "4");
		jsonObject.put("accNo", "5187180008861234");
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
		url += "queryAgentpayFee";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest());
		System.out.println("retData:" + data);
	}
}
