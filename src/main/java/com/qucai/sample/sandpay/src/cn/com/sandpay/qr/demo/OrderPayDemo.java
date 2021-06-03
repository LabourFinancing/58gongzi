package com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo;

import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;

/**
 * 产品：银联聚合码<br>
 * 交易：统一下单并支付接口(被扫)<br>
 * 日期： 2021-01<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class OrderPayDemo {
	
	public static  Logger logger = LoggerFactory.getLogger(OrderPayDemo.class);
	
	JSONObject header = new JSONObject();
	JSONObject body=new JSONObject();
	
	
	public void setHeader() {
		
		header.put("version", DemoBase.version);			//版本号
		header.put("method",DemoBase.QR_ORDERPAY);		    //接口名称:统一下单并支付
//		header.put("productId", DemoBase.PRODUCTID_FANYINLIAN);	//产品编码
        header.put("productId", "00000006");	//产品编码
		header.put("mid", SDKConfig.getConfig().getMid());	//商户号
		String plMid=SDKConfig.getConfig().getPlMid();		//平台商户号
		if(plMid!=null && StringUtils.isNotEmpty(plMid)) {  //平台商户存在时接入
			header.put("accessType", "2");					//接入类型设置为平台商户接入
			header.put("plMid", plMid);
		}else {
			header.put("accessType", "1");					//接入类型设置为普通商户接入
		}		
		header.put("channelType", "07");					//渠道类型：07-互联网   08-移动端
		header.put("reqTime", DemoBase.getCurrentTime());	//请求时间		
	};
	
	
	public void setBody() {		
		body.put("payTool", "0401");						//支付工具: 固定填写0403
		body.put("orderCode", DemoBase.getOrderCode());		//商户订单号
		body.put("scene", "1");								//支付场景 1-条码支付(默认) 2-声波支付
		body.put("authCode", "135058488842509116");		//支付授权码,从支付宝、微信或者云闪付中获取
		body.put("totalAmount","000001100000" );			//订单金额 12位长度，精确到分
		body.put("limitPay","4");							//限定支付方式 送1-限定不能使用贷记卡	送4-限定不能使用花呗	送5-限定不能使用贷记卡+花呗
		body.put("subject", "话费充值");						//订单标题
		body.put("body", "用户购买话费0.01");					//订单描述
		body.put("txnTimeOut",DemoBase.getNextDayTime());	//订单超时时间
		body.put("storeId", "");							//商户门店编号
		body.put("terminalId", "");							//商户终端编号
		body.put("operatorId", "");							//操作员编号
		body.put("clearCycle", "");							//清算模式
		body.put("riskRateInfo", "");						//风控信息域
		body.put("hbFqFlag", "");							//花呗分期标识
		body.put("hbFqNum", "");							//花呗分期期数
		body.put("hbFqSellerPercent", "");					//卖家承担手续费比例
		body.put("notifyUrl", "https://api.58gongzi.com.cn/sandpay-qr-demo/notice");	//异步通知地址
//        body.put("notifyUrl", "https://www.58gongzi.com.cn:8080/sample/oauthController/login?method=QRScanRet");	//异步通知地址
		body.put("bizExtendParams", "");					//业务扩展参数
		body.put("merchExtendParams", "");					//商户扩展参数
		body.put("extend", "");								//扩展域	
	};
	
	
	public static JSONObject main(StaffPrepayApplicationPayment staffPrepayApplicationPay, String merchantId) throws Exception {
		
		OrderPayDemo demo=new OrderPayDemo();
		String reqAddr="/order/pay";   //接口报文规范中获取
		
		//加载配置文件
		SDKConfig.getConfig().loadPropertiesFromSrc(merchantId);
		//加载证书
		CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
		//设置报文头
		demo.setHeader();
		//设置报文体
		demo.setBody();
		
		JSONObject resp=DemoBase.requestServer(demo.header, demo.body, reqAddr);
		
		if(resp.getJSONObject("head").getString("respCode").equals("000000")) {
			logger.info("支付成功");
		}
        return resp;

	}
}
