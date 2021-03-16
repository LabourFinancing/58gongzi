package com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * 产品：杉德代收付产品<br>
 * 交易：凭证申请<br>
 * 日期： 2021-01<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class GetVoucherContent {

	public static  Logger logger = LoggerFactory.getLogger(GetVoucherContent.class);

	JSONObject request = new JSONObject();
	
	/** 
	*    组织请求报文      
	*/
	void setRequest() {
		request.put("version", DemoBase.version);
		request.put("productId", DemoBase.PRODUCTID_AGENTPAY_TOC);
		request.put("tranTime", "20210119091849");
		request.put("orderCode", "202101190918492");
		request.put("voucherType", "1");
		request.put("fileType", "1");
	}
	
	public static void main(String[] args,String merchantId) throws Exception { 
		
		GetVoucherContent demo = new GetVoucherContent();
		String reqAddr="/getVoucherContent";   //接口报文规范中获取
		
		//加载配置文件
		SDKConfig.getConfig().loadPropertiesFromSrc(merchantId);
		//加载证书
		CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
		//设置报文
		demo.setRequest();
		
		String merId = SDKConfig.getConfig().getMid(); 			//商户ID
		String plMid = SDKConfig.getConfig().getPlMid();		//平台商户ID
		
		JSONObject resp = DemoBase.requestServer(demo.request, reqAddr, DemoBase.GET_VOUCHER_CONTENT, merId, plMid);
		
		if(resp!=null) {
			logger.info("响应码：["+resp.getString("respCode")+"]");	
			logger.info("响应描述：["+resp.getString("respDesc")+"]");
			logger.info("内容(文件下载链接)：["+resp.getString("content")+"]");
			
			System.out.println("响应码：["+resp.getString("respCode")+"]");
			System.out.println("响应描述：["+resp.getString("respDesc")+"]");
			System.out.println("内容(凭证下载链接)：["+resp.getString("content")+"]");
		}else {
			logger.error("服务器请求异常！！！");	
		}
	}

}

