/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-cashier-webgateway
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2016-12-28 上午10:49:46
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2016-12-28        Initailized
 */
package com.qucai.sample.sandpay.src.cn.com.sandpay.qr.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.alibaba.fastjson.JSONObject;
import com.qucai.sample.entity.StaffPrepayApplicationPayment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.CertUtil;
import com.qucai.sample.sandpay.src.cn.com.sandpay.cashier.sdk.SDKConfig;

/**
 * @author pan.xl
 *
 */
public abstract class ContextLoadListener implements ServletContextListener {
	
	private static final Logger logger = LoggerFactory.getLogger(ContextLoadListener.class);

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public static void contextInitialized(ServletContextEvent arg0, String merchantId) {
        logger.info("加载证书...");
		// 加载证书
		try {
			//加载配置文件
			SDKConfig.getConfig().loadPropertiesFromSrc(merchantId);
			//加载证书
			CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
