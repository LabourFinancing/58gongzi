package com.qucai.sample.ScoreCard;
import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;

public class OrgEvaluate{
	private static String transCode = "RTPM";	//实时代付
	public static String genRequest(StaffPrepayApplicationPayment staffPrepayApplicationPay) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", staffPrepayApplicationPay.getOrderCode());
		
		jsonObject.put("version", "01");
		jsonObject.put("productId", "00000004");
		jsonObject.put("tranTime", staffPrepayApplicationPay.getTranTime());
		
		jsonObject.put("tranAmt", String.format("%012d", Integer.valueOf(staffPrepayApplicationPay.getTranAmt())*100).toString());
		jsonObject.put("currencyCode", "156");
		

		jsonObject.put("accAttr", "0");
		jsonObject.put("accNo", staffPrepayApplicationPay.getAccNo());
		
		jsonObject.put("accType", "4");
		jsonObject.put("accName", staffPrepayApplicationPay.getAccName());
		
		jsonObject.put("remark", "pay");
		jsonObject.put("reqReserved","全渠道");
		System.out.println("json:"+ jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	public static String agentpay(StaffPrepayApplicationPayment staffPrepayApplicationPay) throws Exception{
		
		//装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
		//设置商户号
//		String merchId = "12432823";
		
		//设置商户号
		String merchId = "18554095";
		
		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		//拼接本交易URL
		url += "agentpay";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		String data = httpUtil.post(url, merchId, transCode, genRequest(staffPrepayApplicationPay));
		System.out.println("retData:" + data);
		return data;
	}
}
