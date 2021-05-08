/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-internetbank-demo
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2018-4-27 下午4:24:11
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2018-4-27        Initailized
 */
package com.qucai.sample.sandpay.src.cn.com.sandpay.qr.demo;

import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;

import com.alibaba.fastjson.JSONObject;

/**
 * 产品：银联聚合码<br>
 * 交易：对账单下载接口<br>
 * 日期： 2021-01<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class ClearFileDownloadDemo {
	
	public static  Logger logger = LoggerFactory.getLogger(ClearFileDownloadDemo.class);
	
	JSONObject header = new JSONObject();
	JSONObject body=new JSONObject();
	
	public void setHeader() {
		header.put("version", DemoBase.clearfileversion);	//版本号
		header.put("method", DemoBase.QR_CLEARFILEDOWNLOAD);//接口名称:对账单下载
		header.put("productId", DemoBase.PRODUCTID_YINLIAN);//产品编码
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
		body.put("clearDate", "20210119");         //交易日期
		body.put("fileType", "1");                 //文件返回类型
		body.put("extend", "");                    //扩展域
		
	};
	
	
	public static JSONObject main(StaffPrepayApplicationPayment staffPrepayApplicationPay, String merchantId) throws Exception {
		
		ClearFileDownloadDemo demo=new ClearFileDownloadDemo();
		String reqAddr="/clearfile/download";   //接口报文规范中获取
		
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
			logger.info("申请对账文件成功");
			logger.info("文件下载链接为："+resp.getJSONObject("body").getString("content"));
		}
        return resp;
	}
}
