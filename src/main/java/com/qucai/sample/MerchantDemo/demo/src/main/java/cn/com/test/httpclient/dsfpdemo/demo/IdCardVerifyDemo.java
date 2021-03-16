package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;

public class IdCardVerifyDemo{
	private static String transCode = "RNPA";	//身份证认证

	public static String genRequest(StaffPrepayApplicationPayment staffPrepayApplicationPay) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", staffPrepayApplicationPay.getOrderCode());
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000003");
		jsonObject.put("tranTime",staffPrepayApplicationPay.getTranTime());
		jsonObject.put("name", staffPrepayApplicationPay.getName());
		jsonObject.put("certType", 	"0101");
		jsonObject.put("certNo", staffPrepayApplicationPay.getCertNo());
		jsonObject.put("returnPic", "1");
		return jsonObject.toJSONString();
	}
	
	public String IdCardVerify(StaffPrepayApplicationPayment staffPrepayApplicationPay) throws Exception{
		
		//装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
		//设置商户号
//		String merchId = "12432823";
		
		//设置商户号
		String merchId = "S2135052";
		
//		System.out.println(genRequest());
		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		//拼接本交易URL
		url += "idCardVerify";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest(staffPrepayApplicationPay));
		System.out.println("retData:" + data);
		return data;
	}
}
