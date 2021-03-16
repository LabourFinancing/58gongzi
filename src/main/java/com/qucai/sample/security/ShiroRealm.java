package com.qucai.sample.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qucai.sample.entity.Manager;
import com.qucai.sample.entity.Resource;
import com.qucai.sample.entity.Role;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.ResourceService;
import com.qucai.sample.service.RoleService;

public class ShiroRealm extends AuthorizingRealm {
    
    /**
     * session中的用户
     */
    public static final String SESSION_MANAGER = "SESSION_MANAGER";
    
    /**
     * session中的用户角色信息
     */
    public static final String SESSION_MANAGER_ROLE = "SESSION_MANAGER_ROLE";

    /**
     * session中的用户资源信息
     */
    public static final String SESSION_MANAGER_RESOURCE = "SESSION_MANAGER_RESOURCE";

    
    
	private static final String OR_OPERATOR = " or ";
	private static final String AND_OPERATOR = " and ";
	private static final String NOT_OPERATOR = "not ";
	
	@Autowired
    private ManagerService managerService;
	@Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;

	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
        
        String managerId = (String) principalCollection.getPrimaryPrincipal();
        List<Role> roleList = roleService.findAuthRoleListByManagerId(managerId);
        if (roleList != null && !roleList.isEmpty()) {
            for (Role r : roleList) {
                sai.addRole(r.getName());
            }
        }
        
        List<Resource> resList = (List<Resource>) SecurityUtils.getSubject().getSession().getAttribute(ShiroRealm.SESSION_MANAGER_RESOURCE);
        if (resList != null && !resList.isEmpty()) {
            for (Resource resource : resList) {
                if (StringUtils.isNotBlank(resource.getShiroPermission())) {
                    sai.addStringPermission(resource.getShiroPermission());
                }
            }
        }
        
		return sai;
	}

	/**
	 * 登陆验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//	    if (doCaptchaValidate(token)) {
            CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authenticationToken;
            String userName = token.getUsername();
            String password = String.valueOf(token.getPassword());
			
			Manager manager = managerService.selectByNameAndPassword(userName, password);
			if (manager != null) {
			    SecurityUtils.getSubject().getSession().setAttribute(SESSION_MANAGER, manager);
			    
			    List<Resource> resList = resourceService.findAuthResourceListByManagerId(manager.getId());
		        SecurityUtils.getSubject().getSession().setAttribute(SESSION_MANAGER_RESOURCE, resList);
		        
				return new SimpleAuthenticationInfo(manager.getId(), password, getName());
			}
//		}
		return null;
	}

	/**
	 * 验证码校验
	 * 
	 * @param token
	 */
	protected boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
		// SpringMVC获取request的方式
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String captcha = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())) {
			throw new IncorrectCaptchaException("验证码错误!");
		}
		return true;

	}

	/**
	 * 更新用户授权信息缓存
	 */
	protected void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	/**
	 * 扩展支持or and not 关键词 不支持and or混用
	 * 
	 * @param principals
	 * @param permission
	 * @return
	 */
	@Override
	public boolean isPermitted(PrincipalCollection principals, String permission) {
		if (permission.contains(OR_OPERATOR)) {
			String[] permissions = permission.split(OR_OPERATOR);
			for (String orPermission : permissions) {
				if (isPermittedWithNotOperator(principals, orPermission)) {
					return true;
				}
			}
			return false;
		} else if (permission.contains(AND_OPERATOR)) {
			String[] permissions = permission.split(AND_OPERATOR);
			for (String orPermission : permissions) {
				if (!isPermittedWithNotOperator(principals, orPermission)) {
					return false;
				}
			}
			return true;
		} else {
			return isPermittedWithNotOperator(principals, permission);
		}
	}

	private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
		if (permission.startsWith(NOT_OPERATOR)) {
			return !super.isPermitted(principals, permission.substring(NOT_OPERATOR.length()));
		} else {
			return super.isPermitted(principals, permission);
		}
	}

}
