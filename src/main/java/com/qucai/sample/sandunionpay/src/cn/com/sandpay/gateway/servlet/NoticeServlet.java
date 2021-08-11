/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-internetbank-demo
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2018-4-27 下午4:36:58
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2018-4-27        Initailized
 */
package com.qucai.sample.sandunionpay.src.cn.com.sandpay.gateway.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import com.qucai.sample.sandunionpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandunionpay.src.cn.com.sandpay.cashier.sdk.CryptoUtil;


/**
 *
 * @ClassName ：OrderCreateServlet
 * @author : pxl
 * @Date : 2018-4-27 下午4:36:58
 * @version 2.0.0
 *
 */
public class NoticeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3184535180522475944L;
	
	/**
	 * 日志对象
	 */
	private final static Logger logger = LoggerFactory.getLogger(NoticeServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String data=req.getParameter("data");
		String sign=req.getParameter("sign");
		logger.info("接收到后台通知数据："+data);
		logger.info("接收到后台通知签名："+sign);
		// 验证签名
		boolean valid;
		try {
			valid = CryptoUtil.verifyDigitalSign(data.getBytes("utf-8"), Base64.decodeBase64(sign),
					CertUtil.getPublicKey(), "SHA1WithRSA");
			if (!valid) {
				logger.error("verify sign fail.");
				logger.error("签名字符串(data)为："+ data);
				logger.error("签名值(sign)为："+ sign);
			}else {
				logger.info("verify sign success");
				JSONObject dataJson = JSONObject.parseObject(data);
				if (dataJson != null) {
					logger.info("后台通知业务数据为：" + JSONObject.toJSONString(dataJson, true));
				} else {
					logger.error("通知数据异常！！！");
				}
			}			

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		

	}
	
}
