//<!--国际化配置
//    <!--1. 语言包及其解析器配置-->
//    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
//        <!--表示多语言配置文件在根路径下，并且是以'messages'开头的文件-->
//        <property name="basenames">
//            <list>
//                <value>i18n.messages</value>
//            </list>
//        </property>
//        <!-- 如果在国际化资源文件中找不到对应代码的信息，就用这个代码作为名称  -->
//        <property name="useCodeAsDefaultMessage" value="true"/>
//    </bean>
//
//    <!--2. 存储区域设置信息：SessionLocaleResolver类通过一个预定义会话名将区域化信息存储在会话中。-->
//    <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
//
//    <!--拦截器配置-->
//    <mvc:interceptors>
//        <mvc:interceptor>
//            <!--语言拦截器，支持国际化-->
//            <mvc:mapping path="/**"/>
//            <bean class="interceptor.LanguageInterceptor">
//                <property name="paramName" value="lang"/>
//            </bean>
//        </mvc:interceptor>
//    </mvc:interceptors>	

//package com.qucai.sample.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.servlet.support.RequestContext;
//import org.springframework.web.servlet.support.RequestContextUtils;
//
//import java.util.ResourceBundle;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.pagehelper.PageInfo;
//import com.qucai.sample.OperationTypeConstant;
//import com.qucai.sample.common.PageParam;
//import com.qucai.sample.entity.FinanceProduct;
//import com.qucai.sample.exception.ExRetEnum;
//import com.qucai.sample.service.FinanceProductService;
//import com.qucai.sample.util.JsonBizTool;
//import com.qucai.sample.util.ShiroSessionUtil;
//import com.qucai.sample.util.Tool;
//
//import controller.BaseController;
//import http.ExecutionContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.support.RequestContextUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Locale;
//
//@Controller
//public class i18nController {
//    private static Logger logger = Logger.getLogger(i18nController.class);
//    
//    @Autowired
//    private UserService userService;
//    
//    
//    /**
//     * 从国际化资源配置文件中根据key获取value  方法一
//     * @param request
//     * @param key
//     * @return
//     */
//    public static String getMessage(HttpServletRequest request, String key){
//        Locale currentLocale = RequestContextUtils.getLocale(request);
//        String lang = currentLocale.getLanguage();
//        ResourceBundle bundle = ResourceBundle.getBundle("messages_"+lang, currentLocale);
//        return bundle.getString(key);
//    }
//    /**
//     * 从国际化资源配置文件中根据key获取value  方法二
//     * @param request
//     * @param key
//     * @return
//     */
//    public static String getMessage2(HttpServletRequest request, String key){
//        RequestContext requestContext = new RequestContext(request);
//        String value = requestContext.getMessage(key);
//        return value;
//    }
//    
//    
//    @RequestMapping("/user")
//    public ModelAndView getUsers(HttpServletRequest request){
//        List<VoUser> userList = userService.getUsers();
//        
//         ModelAndView mv =  new ModelAndView();
//         
//         //从message中获取文字
//         //String showUserInfo = getMessage(request, "showUserInfo");
//         String showUserInfo = getMessage2(request, "showUserInfo");
//         
//         mv.addObject("showUserInfo", showUserInfo);
//         mv.addObject("userList", userList);
//         mv.setViewName("user");
//         return mv;
//    }
//    
//}