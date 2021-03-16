package com.qucai.sample.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.qucai.sample.freemarker.shrio.ShiroTags;

import freemarker.template.TemplateException;

public class BaseFreeMarkerConfigurer extends FreeMarkerConfigurer {

	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		this.getConfiguration().setSharedVariable("dateFormatFun", new DateFormatModel());
		this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}
