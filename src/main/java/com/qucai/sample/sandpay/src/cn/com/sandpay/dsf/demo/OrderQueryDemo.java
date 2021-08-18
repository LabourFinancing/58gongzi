/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-dsf-demo
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2018-4-25 下午5:52:46
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2018-4-25        Initailized
 */
package com.qucai.sample.sandpay.src.cn.com.sandpay.dsf.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * 产品：杉德代收付产品<br>
 * 交易：订单查询<br>
 * 日期： 2021-01<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class OrderQueryDemo { 
	
	public static  Logger logger = LoggerFactory.getLogger(OrderQueryDemo.class);
	
	
	JSONObject request = new JSONObject();
	/** 
	*  组织请求报文          
	*/
	private void setRequest() {
		
		request.put("version", DemoBase.version);                     // 版本号  
		request.put("productId", DemoBase.PRODUCTID_AGENTPAY_TOC);    // 产品ID  
		request.put("tranTime", "20210119092720");           	      // 查询订单的交易时间
		request.put("orderCode", "202101190927208");                  // 要查询的订单号  
		request.put("extend", "");                                    // 扩展域
		
	}

	public static String main(String[] args,String merchantId) throws Exception {
		
		OrderQueryDemo demo = new OrderQueryDemo();
		String retData=null;
		String reqAddr="/queryOrder";   //接口报文规范中获取
		
		//加载配置文件
		SDKConfig.getConfig().loadPropertiesFromSrc(merchantId);
		//加载证书
		CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
		//设置报文
		demo.setRequest();
		
		String merId = SDKConfig.getConfig().getMid(); 			//商户ID
		String plMid = SDKConfig.getConfig().getPlMid();		//平台商户ID
		
		JSONObject resp = DemoBase.requestServer(demo.request, reqAddr, DemoBase.ORDER_QUERY, merId, plMid);
		
		if(resp!=null) {
			logger.info("响应码：["+resp.getString("respCode")+"]");	
			logger.info("响应描述：["+resp.getString("respDesc")+"]");
			logger.info("处理状态：["+resp.getString("resultFlag")+"]");
            retData = "处理状态：["+resp.getString("resultFlag")+"]";
			System.out.println("响应码：["+resp.getString("respCode")+"]");
			System.out.println("响应描述：["+resp.getString("respDesc")+"]");
			System.out.println("处理状态：["+resp.getString("resultFlag")+"]");
		}else {
			logger.error("服务器请求异常！！！");
            retData="服务器请求异常！！！";
		}
		return retData;
	}

	
}
