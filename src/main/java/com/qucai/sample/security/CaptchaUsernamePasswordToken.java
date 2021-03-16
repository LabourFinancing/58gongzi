package com.qucai.sample.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 密码验证码TOKEN
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {

    /**
     * 
     */
    private static final long serialVersionUID = -1226988942615110141L;

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken(String username, String password,
            String captcha, boolean rememberMe) {
        super(username, password, rememberMe);
        this.captcha = captcha;
    }

    public CaptchaUsernamePasswordToken() {
        super();
    }
}