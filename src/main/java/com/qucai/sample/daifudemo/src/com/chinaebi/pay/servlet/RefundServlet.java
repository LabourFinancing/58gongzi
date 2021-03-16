package com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.qucai.sample.daifudemo.src.com.chinaebi.pay.request.Common;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.request.HttpsUtil;
import com.qucai.sample.daifudemo.src.com.chinaebi.pay.security.RSASignUtil;
//声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障
public class RefundServlet extends HttpServlet {

	private static final long serialVersionUID = 6493232259884679745L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==================== 退款接口 ====================");
		request.setCharacterEncoding(Common.CHAR_SET_UTF8);

		String charset = request.getParameter("charset"); // 字符集
		String version = request.getParameter("version"); // 消息版本号
		String signType = request.getParameter("signType"); // 消息版本号
		String service = request.getParameter("service"); // 交易接口
		String merchantId = request.getParameter("merchantId"); // 商户号
		String orderId = request.getParameter("orderId"); // 订单号
		String refundAmount = request.getParameter("refundAmount"); // 退款金额
		String requestId = request.getParameter("requestId"); // 请求号

		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("charset", charset);
		dataMap.put("version", version);
		dataMap.put("signType", signType);
		dataMap.put("service", service);
		dataMap.put("requestId", requestId);
		dataMap.put("merchantId", merchantId);
		dataMap.put("orderId", orderId);
		dataMap.put("refundAmount", refundAmount);

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

		String merchantCertPath = Common.getMerchantCertPath();
		String merchantCertPass = Common.getMerchantCertPwd();
		RSASignUtil util = new RSASignUtil(merchantCertPath, merchantCertPass);
		String reqData = util.coverMap2String(requestMap);

		String merchantSign = util.sign(reqData, Common.CHAR_SET_GBK);
		String merchantCert = util.getCertInfo();
		requestMap.put("merchantSign", merchantSign);
		requestMap.put("merchantCert", merchantCert);

		// 发起http请求，并获取响应报文
		String payUrl = Common.getPayUrl();
		try {
			System.out.println("payUrl		----> " + payUrl);
			String responseStr = HttpsUtil.doPostWithJson(payUrl, requestMap);
			System.out.println("responseStr	----> " + responseStr);

			String pagePrint = responseStr == null || "".equals(responseStr.trim()) ? "返回异常" : responseStr;
			if (responseStr != null) {
				JSONObject jsonObject = JSONObject.fromObject(responseStr);
				@SuppressWarnings("unchecked")
				TreeMap<String, Object> map = (TreeMap<String, Object>) JSONObject.toBean(jsonObject, TreeMap.class);
				String serverCert = String.valueOf(map.get("serverCert"));
				String serverSign = String.valueOf(map.get("serverSign"));
				String rspCode = String.valueOf(map.get("returnCode"));
            	if(StringUtils.isBlank(rspCode))
            		rspCode = String.valueOf(map.get("rspCode"));
            	
				if ("000000".equals(rspCode)) {
					// 验签
					Boolean verifyResult = verify(util, serverSign, serverCert, map);
					System.out.println("verifyResult	----> " + verifyResult);
					pagePrint += "<br/>验签结果：" + verifyResult;
				} else {
					String rspMessage = String.valueOf(map.get("rspMessage"));
					System.out.println("rspCode	----> " + rspCode);
					System.out.println("rspMessage	----> " + rspMessage);
					pagePrint += "<br/>返回码：" + rspCode;
					pagePrint += "<br/>返回信息：" + rspMessage;
				}
			}

			response.setHeader("Content-type", "text/html;charset=" + Common.CHAR_SET_UTF8);
			PrintWriter out = response.getWriter();
			out.print(pagePrint.replaceAll(",", ",<br/>"));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verify(RSASignUtil util, String serverSign,
			String serverCert, Map<String, Object> dataMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(dataMap);

		// 清除不参与验签的数据
		map.remove("serverCert");
		map.remove("serverSign");

		Set<String> set = dataMap.keySet();
		Iterator<?> iterator = set.iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = (String) dataMap.get(key);
			if (StringUtils.isBlank(value)) {
				map.remove(key);
			}
		}

		String oridata = util.coverMap2String(map);
		System.out.println("verifyData	----> " + oridata);
		return util.verifyByTestCert(oridata, serverSign, Common.CHAR_SET_GBK);
	}

}
