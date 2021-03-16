//package com.qucai.sample.smss.src.example.json;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.UUID;
//
//import javax.swing.text.AbstractDocument.Content;
//
//import org.apache.log4j.Logger;
//
//import cn.com.sand.online.agent.service.sdk.ConfigurationManager;
//import cn.com.sand.online.agent.service.sdk.DynamicPropertyHelper;
//import cn.com.sand.online.agent.service.sdk.HttpUtil;
//
//import com.alibaba.fastjson.JSONObject;
//import com.qucai.sample.entity.StaffPrepayApplicationPayment;
//import com.qucai.sample.smss.src.com.dahantc.api.sms.json.JSONHttpClient;
//import com.qucai.sample.vo.StaffPrepayApplicationNew;
//
//public class HttpJsonExampleYun{
//		public static String genRequest(StaffPrepayApplicationNew staffPrepayApplicationNew) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("clientid:", "b003c0");
//			jsonObject.put("password:", "6918d0046aab6a1ee290f751e02bd0b2");
//		    jsonObject.put("mobile:", "13800138000;13800138001;19800138002;19800138003");
//		    jsonObject.put("smstype:", "4");
//            jsonObject.put("content:", "【云之讯】您的验证码为：1234");
//            jsonObject.put("sendtime:", "2016-11-11 09:00:00");
//            jsonObject.put("extend:", "00");
//            jsonObject.put("uid:", "00");
//			
//			System.out.println("json:"+ jsonObject.toJSONString());
//			return jsonObject.toJSONString();
//		}
//		public static String SMSReCode(StaffPrepayApplicationNew staffPrepayApplicationNew){
//			
////			private static String account = "dh88481";// 用户名（必填）
////			private static String password = "9xRp9aDY";// 密码（必填）
////			private static String phone = "15821356455"; // 手机号码（必填,多条以英文逗号隔开）
////			public static String sign = "【长河人资】"; // 短信签名（必填）
////			public static String sign = "【周伯通】"; // 短信签名（必填）
////			public static String subcode = ""; // 子号码（可选）
////			public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
////			public static String sendtime = ""; // 定时发送时间（可选）
////			public static String ContentTitle = "尊敬的员工:您的校验码: "; // 发送预支短信验证码投信息
////			public static String ContentTailer = ", 工作人员不会索取,请勿泄露。"; // 发送预支短信验证码投信息
//			
//			String SMSJobCat = null,SMScodeInit = null,SMScode= null,phone = null,sign= null;
//			
////			String subsign = sMSCompanyName.trim();
////			phone = sMSMobile;
//			
//		    Content content;
//			
//			SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	
//			
//			SMScode =  SMScodeInit.substring(0,SMScodeInit.indexOf("."));
//			
//			StringBuffer ss =  new StringBuffer();
//			
//			
//			//装载配置
//			ConfigurationManager.loadProperties(new String[] { "dsfpconfig"});
//			
//			//设置商户号
//			String merchId = "18554095";
//			
//			//读取配置中公共URL
//			String url =  "https://api.ucpaas.com/sms-partner/access/b003c0/sendsms";
//			//拼接本交易URL
//			url += "agentpay";
//			//创建http辅助工具
//			HttpUtil httpUtil= new HttpUtil();
//			//通过辅助工具发送交易请求，并获取响应报文
//			String data = httpUtil.post(url,genRequest(staffPrepayApplicationNew));
//			System.out.println("retData:" + data);
//			return data;
//		}
//	}
//
//
