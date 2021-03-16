package com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.qucai.sample.daifudemo.src.com.chinaebi.pay.request.Common;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.request.HttpsUtil;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.security.RSASignUtil;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import com.qucai.sample.service.StaffPrepayApplicationService;

//声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障
public class PayServlet extends HttpServlet {

	private static final long serialVersionUID = -514664478497706921L;
	
	@Autowired
	private static StaffPrepayApplicationService staffPrepayApplicationService; // 申明一个对象

	public PayServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public String doPost(StaffPrepayApplicationPayment staffPrepayApplicationPay,HttpServletRequest request, HttpServletResponse response ) 
			throws Exception {
		System.out.println("==================== 统一下单 ====================");
		// 编码设置
		request.setCharacterEncoding(Common.CHAR_SET_UTF8);

		String charset = request.getParameter("charset"); // 字符集
		String version = request.getParameter("version"); // 消息版本号
		String signType = request.getParameter("signType"); // 签名方式
		String service = request.getParameter("service"); // 交易接口
		String transType = request.getParameter("transType"); // 交易类型
		String merchantId = request.getParameter("merchantId"); // 商户号
		String orderId = request.getParameter("orderId"); // 商户订单号
		String orderTime = request.getParameter("orderTime"); // 订单时间
		String transAmt = request.getParameter("transAmt"); // 加交易金额
		String currency = request.getParameter("currency"); // 币种
		String validUnit = request.getParameter("validUnit"); // 订单有效期（非必传）
		String validNum = request.getParameter("validNum"); // 订单有效期数量（非必传）
		String backParam = request.getParameter("backParam"); // 商户私有域
		String offlineNotifyUrl = request.getParameter("offlineNotifyUrl"); // 后台回调地址
		String clientIP = request.getParameter("clientIP"); // IP
		String requestId = request.getParameter("requestId"); // 请求号
		String userName = request.getParameter("userName"); // 账户名称
		String cardType = request.getParameter("cardType"); // 银行卡类型
		String cardNo = request.getParameter("cardNo"); // 卡号
		System.out.println(cardNo);
		String userNo = request.getParameter("userNo"); // 收款方身份证号
		String userType = request.getParameter("userType"); // 收款方证件类型
		String feePayer = request.getParameter("feePayer"); // 手续费扣除方
		String bankCode = request.getParameter("bankCode"); // 银行简称
		String extendInfo = request.getParameter("extendInfo"); // 扩展信息
		// 判断姓名是否中文(判断中文方法仅供参考)
		if (!userName.matches("[\u4E00-\u9FA5]+")) {
			response.setHeader("Content-type", "text/html;charset=" + Common.CHAR_SET_UTF8);
			PrintWriter out = response.getWriter();
			out.print("姓名不是中文,请确认后输入");
			out.flush();
			out.close();
		}
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("charset", charset);
		dataMap.put("version", version);
		dataMap.put("signType", signType);
		dataMap.put("service", service);
		dataMap.put("offlineNotifyUrl", offlineNotifyUrl);
		dataMap.put("clientIP", clientIP);
		dataMap.put("requestId", requestId);
		dataMap.put("merchantId", merchantId);
		dataMap.put("orderId", orderId);
		dataMap.put("orderTime", orderTime);
		dataMap.put("transAmt", transAmt);
		dataMap.put("currency", currency);
		dataMap.put("backParam", backParam);
		dataMap.put("transType", transType);
		dataMap.put("validUnit", validUnit);
		dataMap.put("validNum", validNum);
		dataMap.put("userName", userName);
		dataMap.put("cardType", cardType);
		dataMap.put("cardNo", cardNo);
		dataMap.put("userNo", userNo);
		dataMap.put("userType", userType);
		dataMap.put("feePayer", feePayer);
		dataMap.put("bankCode", bankCode);
		dataMap.put("extendInfo", extendInfo);
		Map<String, Object> requestMap = new HashMap<String, Object>();

		requestMap.putAll(dataMap);
		Set<String> set = dataMap.keySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if ((dataMap.get(key) == null) || (dataMap.get(key) == "")) {
				requestMap.remove(key);
			}
		}

		// 获取签名
		String merchantCertPath = Common.getMerchantCertPath();
		String merchantCertPass = Common.getMerchantCertPwd();
		RSASignUtil util = new RSASignUtil(merchantCertPath, merchantCertPass);
		String sf = util.coverMap2String(requestMap);
		System.out.println("signData	----> " + sf);
		String merchantSign = util.sign(sf, Common.CHAR_SET_GBK);
		requestMap.put("merchantSign", merchantSign);

		// 商户公钥证书
		String merchantCert = util.getCertInfo();
		requestMap.put("merchantCert", merchantCert);
		System.out.println("merchantCert	----> " + merchantCert);

			// 请求支付接口
			String payUrl = Common.getPayUrl();
			System.out.println("payUrl		----> " + payUrl);
			String responseStr = HttpsUtil.doPostWithJson(payUrl, requestMap);
			System.out.println("responseStr	----> " + responseStr);

			String pagePrint = responseStr == null || "".equals(responseStr.trim()) ? "返回异常" : responseStr;
			if (responseStr != null && !"".equals(responseStr.trim())) {
				JSONObject jsonObject = JSONObject.fromObject(responseStr);
				@SuppressWarnings("unchecked")
				TreeMap<String, Object> map = (TreeMap<String, Object>) JSONObject.toBean(jsonObject, TreeMap.class);
				String serverCert = String.valueOf(map.get("serverCert"));
				String serverSign = String.valueOf(map.get("serverSign"));
				String rspCode = String.valueOf(map.get("rspCode"));

				if ("000000".equals(rspCode)) {
					// 清除不参与验签的数据
					map.remove("serverCert");
					map.remove("serverSign");
					// 验签
					String oridata = util.coverMap2String(map);
					System.out.println("verifyData	----> " + oridata);
					/*
					 * Boolean verifyResult = util.verifyByTestCert(oridata,
					 * serverSign, Common.CHAR_SET_GBK);
					 */
					Boolean verifyResult = util.verify(oridata, serverSign, serverCert, Common.CHAR_SET_GBK);
					System.out.println("verifyResult	----> " + verifyResult);
					pagePrint += "<br/>验签 结果" + verifyResult;
				} else {
					String rspMessage = String.valueOf(map.get("rspMessage"));
					System.out.println("rspCode		----> " + rspCode);
					System.out.println("rspMessage	----> " + rspMessage);
					pagePrint += "<br/>返回code码" + rspCode;
					pagePrint += "<br/>返回信结果信息" + rspMessage;
				}
			}

			response.setHeader("Content-type", "text/html;charset=" + Common.CHAR_SET_UTF8);
			PrintWriter out = response.getWriter();
			out.print(pagePrint.replaceAll(",", ",<br/>"));
			out.flush();
			out.close();
			
			return responseStr;
	}

	public static String main(StaffPrepayApplicationPayment staffPrepayApplicationPay,String merchantId)  throws Exception{
		ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String, String>();
		concurrentHashMap.put("", "");
		System.out.println(concurrentHashMap);
		System.currentTimeMillis();
		String orderId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String orderTime = new SimpleDateFormat("yyyyMMdd").format(new Date());
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("charset", "00");
		dataMap.put("version", "1.0");
		dataMap.put("signType", "RSA");
		dataMap.put("service", "AgencyPay");
		dataMap.put("offlineNotifyUrl", "http://27.115.96.2:8099/Insure/WeChatPayCallback");
		dataMap.put("clientIP", "127.0.0.1");
		dataMap.put("requestId", staffPrepayApplicationPay.getOrderCode());
		dataMap.put("merchantId", merchantId);
		dataMap.put("orderId", orderId);
		dataMap.put("orderTime", orderTime);
		Integer transactionAMT = Integer.valueOf(staffPrepayApplicationPay.getTranAmt())*100; 
		dataMap.put("transAmt", String.valueOf(transactionAMT));
		dataMap.put("currency", "CNY");
		dataMap.put("backParam", "保留数据");
		dataMap.put("transType", "AGENCY_PAY");
		dataMap.put("validUnit", "");
		dataMap.put("validNum", "");
		dataMap.put("userName", staffPrepayApplicationPay.getAccName());
		dataMap.put("cardType", "0");
		dataMap.put("cardNo", staffPrepayApplicationPay.getAccNo().replace(" ",""));
		dataMap.put("userNo", staffPrepayApplicationPay.getCertNo());
		dataMap.put("userType", "01");
		dataMap.put("feePayer", "00");
		dataMap.put("bankCode", "");//PABC
		dataMap.put("extendInfo", "代发工资");
		Map<String, Object> requestMap = new HashMap<String, Object>();

		requestMap.putAll(dataMap);
		Set<String> set = dataMap.keySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if ((dataMap.get(key) == null) || (dataMap.get(key) == "")) {
				requestMap.remove(key);
			}
		}

		// 获取签名
		String merchantCertPath = Common.getMerchantCertPath();
		String merchantCertPass = Common.getMerchantCertPwd();
		RSASignUtil util = new RSASignUtil(merchantCertPath, merchantCertPass);
		String sf = util.coverMap2String(requestMap);
		System.out.println("signData	----> " + sf);
		String merchantSign = util.sign(sf, Common.CHAR_SET_GBK);
		requestMap.put("merchantSign", merchantSign);

		// 商户公钥证书
		String merchantCert = util.getCertInfo();
		requestMap.put("merchantCert", merchantCert);
		System.out.println("merchantCert	----> " + merchantCert);

			// 请求支付接口
			String payUrl = Common.getPayUrl();
			System.out.println("payUrl		----> " + payUrl);
			String responseStr = HttpsUtil.doPostWithJson(payUrl, requestMap);
			System.out.println("responseStr	----> " + responseStr);

			String pagePrint = responseStr == null || "".equals(responseStr.trim()) ? "返回异常" : responseStr;
			if (responseStr != null && !"".equals(responseStr.trim())) {
				JSONObject jsonObject = JSONObject.fromObject(responseStr);
				@SuppressWarnings("unchecked")
				TreeMap<String, Object> map = (TreeMap<String, Object>) JSONObject.toBean(jsonObject, TreeMap.class);
				String serverCert = String.valueOf(map.get("serverCert"));
				String serverSign = String.valueOf(map.get("serverSign"));
				String rspCode = String.valueOf(map.get("rspCode"));

				if ("000000".equals(rspCode)) {
					// 清除不参与验签的数据
					map.remove("serverCert");
					map.remove("serverSign");
					// 验签
					String oridata = util.coverMap2String(map);
					System.out.println("verifyData	----> " + oridata);
					/*
					 * Boolean verifyResult = util.verifyByTestCert(oridata,
					 * serverSign, Common.CHAR_SET_GBK);
					 */
					Boolean verifyResult = util.verify(oridata, serverSign, serverCert, Common.CHAR_SET_GBK);
					System.out.println("verifyResult	----> " + verifyResult);
					pagePrint += "<br/>验签 结果" + verifyResult;
				} else {
					String rspMessage = String.valueOf(map.get("rspMessage"));
					System.out.println("rspCode		----> " + rspCode);
					System.out.println("rspMessage	----> " + rspMessage);
					pagePrint += "<br/>返回code码" + rspCode;
					pagePrint += "<br/>返回信结果信息" + rspMessage;
				}
			}
		return responseStr;
	}
}