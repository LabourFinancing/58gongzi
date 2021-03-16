package com.qucai.sample.MerchantDemo.demo.src.main.java.cn.com.test.httpclient.dsfpdemo.demo;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
import cn.com.sand.online.agent.service.sdk.HttpUtil;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.service.StaffPrepayApplicationService;
import com.qucai.sample.util.ShiroSessionUtil;

public class AgentPayDemo{
	
	@Autowired
	private static StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象
	
	private static String transCode = "RTPM";	//实时代付
	public static String genRequest(StaffPrepayApplicationPayment staffPrepayApplicationPay) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("orderCode", staffPrepayApplicationPay.getOrderCode());
		
		jsonObject.put("version", "01");	
		jsonObject.put("productId", "00000004");
		jsonObject.put("tranTime", staffPrepayApplicationPay.getTranTime());
		
		Integer transactionAMT = Integer.valueOf(staffPrepayApplicationPay.getTranAmt())*100; //转型
		
		jsonObject.put("tranAmt", String.format("%012d", transactionAMT));
		jsonObject.put("currencyCode", "156");

		jsonObject.put("accAttr", "0");
		jsonObject.put("accNo", staffPrepayApplicationPay.getAccNo().replace(" ",""));
		
		jsonObject.put("accType", "4");
		jsonObject.put("accName", staffPrepayApplicationPay.getAccName());
		
		jsonObject.put("remark", "pay");
		jsonObject.put("reqReserved","全渠道");
		jsonObject.put("extend", "工资");
		
		System.out.println("json:"+ jsonObject.toJSONString());
		return jsonObject.toJSONString();
	}
	public static String agentpay(StaffPrepayApplicationPayment staffPrepayApplicationPay,String merchantId) throws Exception{
		
		//装载配置
		ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
		
		//设置商户号
		String merchId = merchantId;
		Map<String, Object> rs = new HashMap<String, Object>();
		
		//读取配置中公共URL
		String url =  DynamicPropertyHelper.getStringProperty("dsfp.url", "").get();
		String VerifySMS = staffPrepayApplicationPay.getRemark();
		//拼接本交易URL
		url += "agentpay";
		//创建http辅助工具
		HttpUtil httpUtil= new HttpUtil();
		//通过辅助工具发送交易请求，并获取响应报文
		ShiroSessionUtil.getLoginSession().setRemark(VerifySMS);
		ShiroSessionUtil.getLoginSession().setModifyTime(new Date());
		String data = httpUtil.post(url, merchId, transCode, genRequest(staffPrepayApplicationPay));
		System.out.println("Paid SMS:");
		System.out.println(VerifySMS);
		System.out.println("retData:" + data);
		return data;
	}
}
