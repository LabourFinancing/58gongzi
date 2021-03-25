package com.qucai.sample.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qucai.sample.OperationTypeConstant;
import com.qucai.sample.entity.PasswordReset;
import com.qucai.sample.exception.ExRetEnum;
import com.qucai.sample.service.ManagerService;
import com.qucai.sample.service.PasswordResetService;
import com.qucai.sample.service.RoleService;
import com.qucai.sample.util.JsonBizTool;

//sendEmail jar
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.*;
//import java.util.*;
//import javax.activation.*; 

@Controller
@RequestMapping(value = "/PasswordResetController")
public class PasswordResetController {

	@Autowired
    private PasswordResetService passwordResetService;//申明一个对象
	
    @Autowired
    private ManagerService managerService;
    
    @Autowired
    private RoleService roleService;
    
    @ModelAttribute
    public PasswordReset get(@RequestParam(required = false) String prt_id){ 
    	PasswordReset entity = null;
    	if (StringUtils.isNotBlank(prt_id)) {
            entity = passwordResetService.selectByPrimaryKey(prt_id);//用PersonalInfoService对象属性方法去调用t_FProd_ID并返回
        }
        if (entity == null) {
            entity = new PasswordReset();
        }
        return entity;
    }
          
    
    @RequestMapping(value = "passwordReset")
    @ResponseBody
    public String form(String id, String operationType, Integer platform, 
            HttpServletRequest request, HttpServletResponse response,
            Model model) {
         if (OperationTypeConstant.EDIT.equals(operationType)) 
            {            
        	  PasswordReset passwordReset = passwordResetService.selectByPrimaryKey(id);
              return "PasswordReset/PasswordReset";
            }
           else {
              return JsonBizTool.genJson(ExRetEnum.SUCCESS);
            }
           }
    @RequestMapping(value = {"resetpassword"})
    public String passwordForm(String id, String operationType, Integer platform,
                               HttpServletRequest request, HttpServletResponse response,
                               Model model) {
        if (true||OperationTypeConstant.EDIT.equals(operationType))
        {
            PasswordReset passwordReset = passwordResetService.selectByPrimaryKey(id);
            return "passwordReset/PasswordReset";
        }
        else {
            return JsonBizTool.genJson(ExRetEnum.SUCCESS);
        }
    }
    
//    @SuppressWarnings("null")
//	@RequestMapping("PasswordResetform")
//    @ResponseBody
//    public String PasswordReset(HttpServletRequest request,
//                HttpServletResponse response, Model model) {  
//        Manager manager = null;
//        String password = Tool.genPassword(6);
//        String resendpassword = password;
//        manager.setPassword(resendpassword);
//        String mobile = ShiroSessionUtil.getLoginSession().getMobile();
//        String userName = ShiroSessionUtil.getLoginSession().getRealName();
//        String mobil = mobile;
//        String CompanyName = ShiroSessionUtil.getLoginSession().getCompany_name();
//        String id = ShiroSessionUtil.getLoginSession().getId();
//    	PasswordReset passwordReset = new PasswordReset();    	
//    	passwordReset.setPrt_id(Tool.uuid());
//        passwordReset.setPrt_user_name(ShiroSessionUtil.getLoginSession().getUserName());
//        passwordReset.setPrt_ResetPassword(password);
//        passwordReset.setPrt_real_name(ShiroSessionUtil.getLoginSession().getRealName());
//        passwordReset.setPrt_company_name(ShiroSessionUtil.getLoginSession().getCompany_name());
//        passwordReset.setPrt_mobile(ShiroSessionUtil.getLoginSession().getMobile());
//        passwordReset.setPrt_PersonaID(ShiroSessionUtil.getLoginSession().getId());
//        passwordReset.setPrt_status(1);
//        passwordReset.setCreate_time(new Date());
//        passwordReset.setModify_time(new Date());
//        managerService.updateByPrimaryKeySelective(manager);
//        passwordResetService.insertSelective(passwordReset);
//        return JsonBizTool.genJson(ExRetEnum.SUCCESS);
//}


      
      
//    public class MailUtils {
//
//        private String host = ""; // smtp服务器
//        private String from = ""; // 发件人地址
//        private String to = ""; // 收件人地址
//        private String affix = ""; // 附件地址
//        private String affixName = ""; // 附件名称
//        private String user = ""; // 用户名
//        private String pwd = ""; // 密码
//        private String subject = ""; // 邮件标题
//
//        public void setAddress(String from, String to, String subject) {
//            this.from = from;
//            this.to = to;
//            this.subject = subject;
//        }
//
//        public void setAffix(String affix, String affixName) {
//            this.affix = affix;
//            this.affixName = affixName;
//        }
//
//        public void send(String host, String user, String pwd) {
//            this.host = host;
//            this.user = user;
//            this.pwd = pwd;
//
//            Properties props = new Properties();
//
//            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
//            props.put("mail.smtp.host", host);
//            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
//            props.put("mail.smtp.auth", "true");
//
//            // 用刚刚设置好的props对象构建一个session
//            Session session = Session.getDefaultInstance(props);
//
//            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
//            // 用（你可以在控制台（console)上看到发送邮件的过程）
//            session.setDebug(true);
//
//            // 用session为参数定义消息对象
//            MimeMessage message = new MimeMessage(session);
//            try {
//                // 加载发件人地址
//                message.setFrom(new InternetAddress(from));
//                // 加载收件人地址
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//                // 加载标题
//                message.setSubject(subject);
//
//                // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
//                Multipart multipart = new MimeMultipart();
//
//                // 设置邮件的文本内容
//                BodyPart contentPart = new MimeBodyPart();
//                contentPart.setText("邮件的具体内容在此");
//                multipart.addBodyPart(contentPart);
//                // 添加附件
//                BodyPart messageBodyPart = new MimeBodyPart();
//                DataSource source = new FileDataSource(affix);
//                // 添加附件的内容
//                messageBodyPart.setDataHandler(new DataHandler(source));
//                // 添加附件的标题
//                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//                sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//                messageBodyPart.setFileName("=?GBK?B?"
//                        + enc.encode(affixName.getBytes()) + "?=");
//                multipart.addBodyPart(messageBodyPart);
//
//                // 将multipart对象放到message中
//                message.setContent(multipart);
//                // 保存邮件
//                message.saveChanges();
//                // 发送邮件
//                Transport transport = session.getTransport("smtp");
//                // 连接服务器的邮箱
//                transport.connect(host, user, pwd);
//                // 把邮件发送出去
//                transport.sendMessage(message, message.getAllRecipients());
//                transport.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        public void main(String[] args) {
//            MailUtils cn = new MailUtils();
//            // 设置发件人地址、收件人地址和邮件标题
//            cn.setAddress("m157****0661@163.com", "80818***@qq.com", "一个带附件的JavaMail邮件");
//            // 设置要发送附件的位置和标题
//            cn.setAffix("f:/123.txt", "123.txt");
//            
//            /**
//             * 设置smtp服务器以及邮箱的帐号和密码
//             * 用QQ 邮箱作为发生者不好使 （原因不明）
//             * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
//             * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
//             */
//             // 注意： [授权码和你平时登录的密码是不一样的]
//            cn.send("smtp.163.com", "m157****0661@163.com", "gebilaowang123");
//
//        }
//    }
}


