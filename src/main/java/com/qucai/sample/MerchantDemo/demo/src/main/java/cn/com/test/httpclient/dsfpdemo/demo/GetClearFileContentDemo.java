package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class GetClearFileContentDemo{

	private static String transCode = "CFCT";	//对账单申请
	
	public static String genRequest() {
		JSONObject jsonObject = new JSONObject();	
		jsonObject.put("version", "01");
		jsonObject.put("clearDate", "20170207");
		jsonObject.put("busiType", "2");		
		jsonObject.put("fileType", "1");

		
		System.out.println("json:"+ jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	public static void main(String args[]) throws Exception{
		
		//装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
		//设置商户号
//		String merchId = "990000000000006";
		String merchId = "S2135052";
		
		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		//拼接本交易URL
		url += "getClearFileContent";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest());
		System.out.println("retData:" + data);
	}
}
