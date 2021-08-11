/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-gateway-demo
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2018-4-25 下午8:15:41
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2018-4-25        Initailized
 */
package com.qucai.sample.sandunionpay.src.cn.com.sandpay.gateway.demo;

import org.apache.commons.lang.StringUtils; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qucai.sample.sandunionpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandunionpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;
import com.alibaba.fastjson.JSONObject;

/**
 * 产品：杉德线上支付<br>
 * 交易：统一下单接口-银联sdk<br>
 * 日期： 2018-04<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class OrderCreateDemo {

	public static  Logger logger = LoggerFactory.getLogger(OrderCreateDemo.class);
	
	public JSONObject header = new JSONObject();
	public JSONObject body=new JSONObject();
	
	public void setHeader() {
		
		header.put("version", DemoBase.version);			//版本号
		header.put("method", DemoBase.ORDERPAY);			//接口名称:统一下单
		header.put("mid", SDKConfig.getConfig().getMid());	//商户号
		String plMid=SDKConfig.getConfig().getPlMid();		//平台商户号
		if(plMid!=null && StringUtils.isNotEmpty(plMid)) {  //平台商户存在时接入
			header.put("accessType", "2");					//接入类型设置为平台商户接入
			header.put("plMid", plMid);
		}else {
			header.put("accessType", "1");					//接入类型设置为平台商户接入												//接入类型设置为普通商户接入
		}		
		header.put("channelType", "07");					//渠道类型：07-互联网   08-移动端
		header.put("reqTime", DemoBase.getCurrentTime());	//请求时间	
		header.put("productId", "00000030");				//产品编码
		
		
	};
	
	
	public void setBody() {

		body.put("orderCode", DemoBase.getOrderCode());                           //商户订单号
		body.put("totalAmount", "000000000001");                                  //订单金额
		body.put("subject", "话费充值");                                             //订单标题
		body.put("body", "用户购买话费0.01");                                         //订单描述 
		body.put("txnTimeOut", DemoBase.getNextDayTime());                        //订单超时时间
		body.put("clientIp", "192.168.22.55");                                    //客户端IP
		body.put("limitPay", "");                                                 //限定支付方式	送1-限定不能使用贷记卡送	4-限定不能使用花呗	送5-限定不能使用贷记卡+花呗
		body.put("notifyUrl", "http://127.0.0.1/notify");                         //异步通知地址  
		body.put("frontUrl", "http://127.0.0.1/frontNotify");                     //前台通知地址
		body.put("storeId", "");                                                  //商户门店编号
		body.put("terminalId", "");                                               //商户终端编号
		body.put("operatorId", "");                                               //操作员编号
		body.put("clearCycle", "");                                               //清算模式
		body.put("royaltyInfo", "");                                              //分账信息
		body.put("riskRateInfo", "");                                             //风控信息域
		body.put("bizExtendParams", "");                                          //业务扩展参数
		body.put("merchExtendParams", "");                                        //商户扩展参数
		body.put("extend", "");                                                   //扩展域
		body.put("accsplitInfo", "");                                             //分账域		
		body.put("payMode", "sand_upsdk");										  //支付模式
		
	};
	
	
	public static void main(String[] args) throws Exception {
		
		OrderCreateDemo demo=new OrderCreateDemo();
		String reqAddr="/order/pay";   //接口报文规范中获取
		
		//加载配置文件
		SDKConfig.getConfig().loadPropertiesFromSrc();
		//加载证书
		CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
		//设置报文头
		demo.setHeader();
		//设置报文体
		demo.setBody();
		
		JSONObject resp=DemoBase.requestServer(demo.header, demo.body, reqAddr);
		
		if(resp.getJSONObject("head").getString("respCode").equals("000000")) {
			logger.info("下单成功");
			logger.info("生成的支付凭证为："+resp.getJSONObject("body").getString("credential"));
		}


	}
}
