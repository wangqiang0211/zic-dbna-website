package com.cdzic.zicTemplate.service.email;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @creator yaotao
 * @date 2018/8/21 11:06
 * @describe:
 */
@Service
public class MailService {
    private static final Logger LOGGER= LogManager.getLogger(MailService.class);
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送普通文本邮件
     * @param to
     * @param title
     * @param content
     */
    public void sendSimple(String to, String title, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //发送者
        message.setTo(to); //接受者
        message.setSubject(title); //发送标题
        message.setText(content);  //发送内容
        javaMailSender.send(message);
    }

    /**
     * 发送HTML邮箱模板
     * @param emailTitle
     * @param to
     * @param emailSubject
     * @param companyName
     * @param contentTitle
     * @param contentBody
     * @param sender
     * @throws Exception
     */
    public void sendHtmlTemplate(String emailTitle,String to,String emailSubject,String companyName,String contentTitle,
                                 String contentBody,String sender) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        //基本设置.
        helper.setFrom(from, emailTitle);//发送者.
        helper.setTo(to);//接收者.
        helper.setSubject(emailSubject);//邮件主题.

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("companyName", companyName);
        model.put("contentTitle", contentTitle);
        model.put("contentHead", "您好！");//固定格式  不变
        model.put("contentBody", contentBody);
        model.put("sender", sender);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        model.put("sendDate", simpleDateFormat.format(new Date()));
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);

        // 设定去哪里读取相应的ftl模板
        cfg.setClassForTemplateLoading(this.getClass(), "/templates/utils");

        // 在模板文件目录中寻找名称为name的模板文件
        Template template   = cfg.getTemplate("email-template.html");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
        helper.setText(html, true);
//        添加静态资源(邮件companyName 的公司logo)
//        FileSystemResource file = new FileSystemResource(new File("/usr/local/Desktop/head.png"));
//        helper.addInline("companyLogo",file);
        javaMailSender.send(mimeMessage);
    }


}
