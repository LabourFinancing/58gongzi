package com.qucai.sample.freemarker.shrio;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 * Freemarker tag that renders the tag body if the current user known to the
 * system, either from a successful login attempt (not necessarily during the
 * current session) or from 'RememberMe' services.
 *
 * <p>
 * <b>Note:</b> This is <em>less</em> restrictive than the
 * <code>AuthenticatedTag</code> since it only assumes the user is who they say
 * they are, either via a current session login <em>or</em> via Remember Me
 * services, which makes no guarantee the user is who they say they are. The
 * <code>AuthenticatedTag</code> however guarantees that the current user has
 * logged in <em>during their current session</em>, proving they really are who
 * they say they are.
 *
 * <p>
 * The logically opposite tag of this one is the
 * {@link org.apache.shiro.web.tags.GuestTag}.
 *
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.UserTag}
 * </p>
 */
public class UserTag extends SecureTag {
	private static Logger logger = LoggerFactory
			.getLogger("UserTag");

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body)
            throws IOException, TemplateException {
        if (getSubject() != null && getSubject().getPrincipal() != null) {
        	logger.debug("Subject has known identity (aka 'principal'). Tag body will be evaluated.");
            renderBody(env, body);
        } else {
        	logger.debug("Subject does not exist or have a known identity (aka 'principal'). Tag body will not be evaluated.");
        }
    }
}
