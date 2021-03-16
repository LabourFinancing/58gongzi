package com.qucai.sample.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 自定义表单过滤，加入验证码
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request,
            ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        return new CaptchaUsernamePasswordToken(username, password, captcha,
                rememberMe);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request,
            ServletResponse response) throws Exception {
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse res = (HttpServletResponse) response;
        Subject subject = SecurityUtils.getSubject();
        if (!isLoginRequest(request, response)) {
            if (!subject.isAuthenticated()) {
            	res.sendRedirect(req.getContextPath() + "/timeout");
            	return false;
            }
        }
        return super.onAccessDenied(request, response);
    }
}
