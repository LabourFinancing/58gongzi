package com.qucai.sample.smss.src.example.json;

import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.qucai.sample.smss.src.com.dahantc.api.sms.json.JSONHttpClient;

public class HttpJsonExample {
	private static final Logger LOG = Logger.getLogger(HttpJsonExample.class);
	private static String clientid = "b03fe1";// 用户名（必填）
	private static String password = "gf123456";// 密码（必填）
	public static String smstype = "4"; // 短信签名（必填）
	public static String sign = "【高孚科技】"; // 短信签名（必填）
	public static String subcode = ""; // 子号码（可选）
	public static String msgid = UUID.randomUUID().toString().replace("-", ""); // 短信id，查询短信状态报告时需要，（可选）
	public static String ContentTitle = "尊敬的"; // 发送预支短信验证码投信息
	public static String ContentTitle1 = "薪酬福利系统的验证码: "; // 发送预支短信验证码投信息
	public static String ContentTailer = ", 工作人员不会索取,请勿泄露。（15分钟有效）"; // 发送预支短信验证码投信息
	public static String extend = "00";
	public static String uid = "00";
	public static String SMSPWDhead = "您所使用的";
	public static String PasswordResetContent = ",密码为: ";
	public static String PasswordResethead = "尊敬的员工，您所使用的";
	public static String PasswordResethead1 = "薪酬福利系统用户名为: ";
	public static String PasswordResetTail = ",请妥善保管好您的用户名密码!";
	
	public static String PaidTile = "您本次预支本月工资数: ";
	public static String PaidMsg1 = "收取服务费: ";
	public static String PaidAct = "实际到付: ";
	public static String PaidMsg2 = "本月剩余可预支工资数: ";
	public static String PaidMsg3 = "其余的工资数将在本月发工资日当天汇入您的工资卡。";
	
	public static String SMSReCode(String SMSMobile, String SMSCompanyName) {
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Date sendtime = new Date();
		
		String SMScodeInit = null,SMScode= null,mobile = null,content= null;
		
		String subsign = SMSCompanyName.trim();
		mobile = SMSMobile;
		
		SMScodeInit = String.valueOf(((Math.random()*9+1)*100000));	
		
		SMScode =  SMScodeInit.substring(0,SMScodeInit.indexOf("."));
		
		StringBuffer ss =  new StringBuffer();
		
		content = String.valueOf(ss.append(sign).append(" ").append(ContentTitle).append(SMSPWDhead).append(subsign).append(ContentTitle1).append(SMScode).append(ContentTailer)).replaceAll("null", "").trim();
		
		
		try {		
//			JSONHttpClient jsonHttpClient = new JSONHttpClient("https://api.ucpaas.com/sms-partner/access/b00713/sendsms");
			JSONHttpClient jsonHttpClient = new JSONHttpClient("http://request.ucpaas.com/sms-partner/access/b02er2/sendsms");
			jsonHttpClient.setRetryCount(1);
			String sendhRes = jsonHttpClient.sendSms(clientid, password, mobile,smstype,content, sendtime, extend, uid);
			LOG.info("提交单条普通短信响应：" + sendhRes);

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
			
			String balanceRe = jsonHttpClient.getBalance(clientid, password);
			LOG.info("获取余额响应：" + balanceRe);

		} catch (Exception e) {

			LOG.error("应用异常", e);
			return SMScode;
		}
		
		return SMScode;
	}
	
public static String SuccessPaid(String mobile,String SMSCompanyName,String PaidAmt,String ActPaidAmt,String ServiceFee, String BalanceAmt) {
		
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		// 您已预支
		Date sendtime = new Date();
		
		String SuccPaid = null ,content = null;
		
		String subsign = SMSCompanyName.trim();
		
		StringBuffer ss =  new StringBuffer();
		
		content = String.valueOf(ss.append(sign).append(" ").append(ContentTitle).append(SMSCompanyName).append("的员工,").append(PaidTile).append("￥").append(PaidAmt).append("元").append(",").append(PaidMsg1).append("￥").append(ServiceFee).append("元").append(",").append(PaidAct).append("￥").append(ActPaidAmt).append("元").append(",").append(PaidMsg2).append("￥").append(BalanceAmt).append("元").append(",").append(PaidMsg3)).replaceAll("null", "").trim();
		
		try {		
			JSONHttpClient jsonHttpClient = new JSONHttpClient("http://request.ucpaas.com/sms-partner/access/b02er2/sendsms");
			jsonHttpClient.setRetryCount(1);
			String sendhRes = jsonHttpClient.sendSms(clientid, password, mobile,smstype,content, sendtime, extend, uid);
			LOG.info("提交单条普通短信响应：" + sendhRes);
			String balanceRe = jsonHttpClient.getBalance(clientid, password);
			LOG.info("获取余额响应：" + balanceRe);

		} catch (Exception e) {

			LOG.error("应用异常", e);
			return SuccPaid;
		}
		
		return SuccPaid;
	}
	
public static String PasswordResend(String resendpassword,String mobil,String userName,String CompanyName) {
		
    Date sendtime = new Date();
	String mobile = mobil;
	String Username = userName;
	String subsign = CompanyName;
	String ResendPassword = resendpassword;
	String contentPWDreset = null;
		
		StringBuffer ss =  new StringBuffer();
		
		contentPWDreset = String.valueOf(ss.append(sign).append(",").append(ContentTitle).append(CompanyName).append("的同仁,").append("您所使用的").append(PasswordResethead1).append(Username.trim()).append(PasswordResetContent).append(ResendPassword.trim())).replaceAll("null", "").trim();
		
		try {		
			JSONHttpClient jsonHttpClient = new JSONHttpClient("http://request.ucpaas.com/sms-partner/access/b02er2/sendsms");
			jsonHttpClient.setRetryCount(1);
			String sendhRes = jsonHttpClient.sendSms(clientid, password, mobile,smstype,contentPWDreset, sendtime, extend, uid);
			LOG.info("提交单条普通短信响应：" + sendhRes);
			String balanceRe = jsonHttpClient.getBalance(clientid, password);
			LOG.info("获取余额响应：" + balanceRe);

		} catch (Exception e) {

			LOG.error("应用异常", e);
			return Username;
		}
		
		return Username;
	}

public static String GroupPWDsend(String resendpassword,String mobil,String userName,String CompanyName) {
	
    Date sendtime = new Date();
	String mobile = mobil;
	String Username = userName;
	String subsign = CompanyName;
	String ResendPassword = resendpassword;
	String contentPWDreset = null;
		
		StringBuffer ss =  new StringBuffer();
		
		contentPWDreset = String.valueOf(ss.append(sign).append(",").append(ContentTitle).append(CompanyName).append("的同仁,").append("您所使用的").append(PasswordResethead1).append(Username.trim()).append(PasswordResetContent).append(ResendPassword.trim()).append(PasswordResetTail)).replaceAll("null", "").trim();
		
		try {		
			JSONHttpClient jsonHttpClient = new JSONHttpClient("http://request.ucpaas.com/sms-partner/access/b02er2/sendsms");
			jsonHttpClient.setRetryCount(1);
			String sendhRes = jsonHttpClient.sendSms(clientid, password, mobile,smstype,contentPWDreset, sendtime, extend, uid);
			LOG.info("提交单条普通短信响应：" + sendhRes);
			String balanceRe = jsonHttpClient.getBalance(clientid, password);
			LOG.info("获取余额响应：" + balanceRe);

		} catch (Exception e) {

			LOG.error("应用异常", e);
			return Username;
		}
		
		return Username;
	}

public static String SMSgroupSend(String t_SMS_Detail, String t_P_Mobile, String t_P_Name, String t_P_Company) {
	
    Date sendtime = new Date();
	String mobile = t_P_Mobile;
	String Username = t_P_Name;
	String subsign = t_P_Company;
	String SMSDetail = t_SMS_Detail;
	String contentPWDreset = null;
	String SMStailer = "请您根据自己的实际需求预支，谢谢您";
	
	StringBuffer ss =  new StringBuffer();
	
	contentPWDreset = String.valueOf(ss.append(sign).append(",").append(PasswordResethead).append(Username).append("您所服务的企业").append(subsign).append(SMSDetail).append(SMStailer.trim())).replaceAll("null", "").trim();
	
	try {		
		JSONHttpClient jsonHttpClient = new JSONHttpClient("http://request.ucpaas.com/sms-partner/access/b02er2/sendsms");
		jsonHttpClient.setRetryCount(1);
		String sendhRes = jsonHttpClient.sendSms(clientid, password, mobile,smstype,contentPWDreset, sendtime, extend, uid);
		LOG.info("提交单条普通短信响应：" + sendhRes);
		String balanceRe = jsonHttpClient.getBalance(clientid, password);
		LOG.info("获取余额响应：" + balanceRe);

	} catch (Exception e) {

		LOG.error("应用异常", e);
		return Username;
	}
	
	return Username;
}
}
