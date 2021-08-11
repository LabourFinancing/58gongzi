package com.qucai.sample.sandunionpay.src.cn.com.sandpay.gateway.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncodingFilter implements Filter {
	/**
	 * 日志对象
	 */
	private final static Logger logger = LoggerFactory
			.getLogger(EncodingFilter.class);

	private String encoding;
	private Map<String, String> params = new HashMap<String, String>();

	// 项目结束时就已经进行销毁
	public void destroy() {
		logger.info("end do the encoding filter!");
		params = null;
		encoding = null;
	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		resp.setContentType("text/html;charset=" + encoding);
		chain.doFilter(req, resp);
	}

	// 项目启动时就已经进行读取
	public void init(FilterConfig config) throws ServletException {
		logger.info("begin do the encoding filter!");
		encoding = config.getInitParameter("encoding");
		for (Enumeration e = config.getInitParameterNames(); e
				.hasMoreElements();) {
			String name = (String) e.nextElement();
			String value = config.getInitParameter(name);
			params.put(name, value);
		}
	}
}