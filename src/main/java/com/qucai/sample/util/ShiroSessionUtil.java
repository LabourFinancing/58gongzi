package com.qucai.sample.util;

import org.apache.shiro.SecurityUtils;

import com.qucai.sample.entity.Manager;
import com.qucai.sample.security.ShiroRealm;

public class ShiroSessionUtil {

    public static Manager getLoginSession() {
        return (Manager) SecurityUtils.getSubject().getSession()
                .getAttribute(ShiroRealm.SESSION_MANAGER);
    }

}


