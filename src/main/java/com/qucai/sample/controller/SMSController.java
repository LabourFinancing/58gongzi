//package com.qucai.sample.controller;
////package example.json;
//import java.io.UnsupportedEncodingException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.qucai.sample.service.FinanceProductService;
//import com.qucai.sample.service.ManagerService;
//import com.qucai.sample.service.OrganizationInfoService;
//import com.qucai.sample.service.PersonalInfoService;
//import com.qucai.sample.service.ResourceService;
//import com.qucai.sample.service.RoleService;
//import com.qucai.sample.service.StaffPrepayApplicationService;
//import java.beans.Encoder;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import org.apache.log4j.Logger;
//import com.dahantc.api.sms.json.JSONHttpClient;
//import com.dahantc.api.sms.json.SmsData;
//
//@Controller
//@RequestMapping(value = "SMSverify")   //验证短信码
//@ResponseBody
//public String SMSverify(String SMSCode,HttpServletRequest request,
//        HttpServletResponse response,Model model){
//        model.addAttribute("SMSCode",SMSCode);
//    	
//    	boolean rs = staffPrepayApplicationService.verifySMS(SMSCode);
//    			if (rs = false){
//    				return "false";
//    			} else {
//    			return "true";
//    			}	
//    } 
//
//	public class HttpJsonExample {
//		private static final Logger LOG = Logger.getLogger(HttpJsonExample.class);
//		private static String account = "dh****";// 用户名（必填）
//		private static String password = "******";// 密码（必填,明文）
//		private static String phone = "139********"; // 手机号码（必填,多条以英文逗号隔开）
//		public static String sign = "【****】"; // 短信签名（必填）
//		public static String subcode = ""; // 子号码（可选）
//		public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
//		public static String sendtime = ""; // 定时发送时间（可选）
//
//		public static void main(String[] args) {
//			try {
//				String content = "您的验证码是:123456";// 短信内容（必填）
//
//				JSONHttpClient jsonHttpClient = new JSONHttpClient("http://www.dh3t.com");
//				jsonHttpClient.setRetryCount(1);
//				String sendhRes = jsonHttpClient.sendSms(account, password, phone, content, sign, subcode);
//				LOG.info("提交单条普通短信响应：" + sendhRes);
//
//				//
//				// List<SmsData> list = new ArrayList<SmsData>();
//				// list.add(new
//				// SmsData("11111111,15711666133,1738786465,44554545",
//				// content, msgid, sign, subcode, sendtime));
//				// list.add(new SmsData("15711777134", content, msgid, sign,
//				// subcode, sendtime));
//				// String sendBatchRes = jsonHttpClient.sendBatchSms(account,
//				// password, list);
//				// LOG.info("提交批量短信响应：" + sendBatchRes);
//				//
//				// String reportRes = jsonHttpClient.getReport(account, password);
//				// LOG.info("获取状态报告响应：" + reportRes);
//				//
//				// String smsRes = jsonHttpClient.getSms(account, password);
//				// LOG.info("获取上行短信响应：" + smsRes);
//				//
//				// String balanceRes = jsonHttpClient.getBalance(account, password);
//				// LOG.info("获取余额短信响应：" + balanceRes);
//
//			} catch (Exception e) {
//
//				LOG.error("应用异常", e);
//			}
//
//		}
//	}
//
