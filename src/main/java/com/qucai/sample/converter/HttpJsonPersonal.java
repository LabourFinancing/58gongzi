package com.qucai.sample.converter;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

public class HttpJsonPersonal {
	private static final Logger LOG = Logger.getLogger(HttpJsonPersonal.class);	
	
//	private static String account = "dh88481";// 用户名（必填）
//	private static String password = "9xRp9aDY";// 密码（必填）
	private static String clientid = "WHOSYOURDADDY";// 用户名（必填）
	private static String password = "WHOSYOURDADDY";// 密码（必填）
	public static String smstype = "4"; // 短信签名（必填）
	public static String sign = "【长河人资】"; // 短信签名（必填）
	public static String subcode = ""; // 子号码（可选）
	public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
	public static String ContentTitle = "尊敬的员工:您的验证码: "; // 发送预支短信验证码投信息
	public static String ContentTailer = ", 工作人员不会索取,请勿泄露。（30分钟有效）"; // 发送预支短信验证码投信息
	public static String extend = "00";
	public static String uid = "00";

	
	public static String SMSReCode(String sMSMobile, String sMSCompanyName) {
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date sendtime = new Date();
		
		String SMScodeInit = null,SMScode= null,mobile = null,sign= null,content= null;
		
		String subsign = sMSCompanyName.trim();
		mobile = sMSMobile;
		
		SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	
		
		SMScode =  SMScodeInit.substring(0,SMScodeInit.indexOf("."));
		
		StringBuffer ss =  new StringBuffer();
		
		content = String.valueOf(ss.append(sign).append("【长河人资】【").append(subsign).append("】").append(ContentTitle).append(SMScode).append(ContentTailer)).replaceAll("null", "").trim();
		
		
		try {		
			JSONHttpClientYKY jsonHttpClientYKY = new JSONHttpClientYKY("http://s.apjmall.com/sal/index.php?s=/addon/OAExtern/OAExtern/SyncToken");
			jsonHttpClientYKY.setRetryCount(1);
			String getData = jsonHttpClientYKY.sendSms(clientid, password, mobile,smstype,content, sendtime, extend, uid);
			LOG.info("提交单条普通短信响应：" + getData);
			//
			// List<SmsData> list = new ArrayList<SmsData>();
			// list.add(new
			// SmsData("11111111,15711666133,15711777134,1738786465,44554545",
			// content, msgid, sign, subcode, sendtime));
			// list.add(new SmsData("15711616131", content, msgid, sign,
			// subcode, sendtime));
			// String sendBatchRes = jsonHttpClient.sendBatchSms(account,
			// password, list);
			// LOG.info("提交批量短信响应：" + sendBatchRes);
			//
			// String reportRes = jsonHttpClient.getReport(account, password);
			// LOG.info("获取状态报告响应：" + reportRes);
			//
			// String smsRes = jsonHttpClient.getSms(account, password);
			// LOG.info("获取上行短信响应：" + smsRes);
			
//			String balanceRe = jsonHttpClient.getBalance(clientid, password);
//			LOG.info("获取余额响应：" + balanceRe);

		} catch (Exception e) {

			LOG.error("应用异常", e);
			return SMScode;
		}
		
		return SMScode;
	}
}
