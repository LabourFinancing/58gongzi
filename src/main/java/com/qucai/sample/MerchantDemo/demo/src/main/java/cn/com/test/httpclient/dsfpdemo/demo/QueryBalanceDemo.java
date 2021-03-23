package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import cn.com.sand.online.agent.service.sdk.ConfigurationManager;

import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;

public class QueryBalanceDemo{
	private static String transCode = "MBQU";	//余额查询

	public static String genRequest(String merchId) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat ds = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		StringBuffer ps = new StringBuffer();
		String QueryNum = String.valueOf(ps.append(ds.format(new Date()).toString()).append("54095"));
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("orderCode", QueryNum);
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000003");

		jsonObject.put("tranTime", df.format(new Date()));
		System.out.println(jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	public static String BalanceQuery(String merchantId) throws Exception{
		
		//装载配置  		
		StringBuffer headprop = new StringBuffer();
		String dsfpconfig = null;
		dsfpconfig = String.valueOf(headprop.append(merchantId.toString()).append("-").append("dsfpconfig"));
		ConfigurationManager.loadProperties(new String[] {dsfpconfig});
		//设置商户号
//		String merchId = "S2135052";
		String merchId = merchantId;

		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		//拼接本交易URL
		url += "queryBalance";
		String url1 = URLDecoder.decode(url.toString(), "utf-8");  // fix issue
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url1, merchId, transCode, genRequest(merchId));
		System.out.println("retData:" + data);
		return data;
	}
}
