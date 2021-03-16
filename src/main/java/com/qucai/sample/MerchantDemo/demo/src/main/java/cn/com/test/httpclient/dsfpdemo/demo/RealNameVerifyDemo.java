package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;

public class RealNameVerifyDemo{
	private static String transCode = "RNAU";	//银行卡实名认证

	public static String genRequest(StaffPrepayApplicationPayment staffPrepayApplicationPay) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode",staffPrepayApplicationPay.getOrderCode());
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000003");
		jsonObject.put("tranTime", staffPrepayApplicationPay.getTranTime());
		jsonObject.put("accAttr", "0");
		jsonObject.put("accType", "4");

		jsonObject.put("accNo", staffPrepayApplicationPay.getAccNo());
		jsonObject.put("accName", staffPrepayApplicationPay.getName());
		jsonObject.put("certType", "0101");
		jsonObject.put("certNo", staffPrepayApplicationPay.getCertNo());
		jsonObject.put("phone", staffPrepayApplicationPay.getPhone());				

		return jsonObject.toJSONString();
	}
	
	public String RealNameChk(StaffPrepayApplicationPayment staffPrepayApplicationPay) throws Exception{
		
		// 装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig" });
		
		//设置商户号
		String merchId = "S2135052";

		// 读取配置中公共URL
		String url = DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
//		System.out.println(genRequest());
		// 拼接本交易URL
		url += "realNameVerify";
		// 创建http辅助工具
		HttpUtil httpUtil = new HttpUtil();
		// 通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest(staffPrepayApplicationPay));
		System.out.println("retData:" + data);
		return data;
	}
}
