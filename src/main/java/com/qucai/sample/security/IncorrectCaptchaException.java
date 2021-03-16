package com.qucai.sample.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 自定义验证码异常
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = -206482417738196949L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }

}
