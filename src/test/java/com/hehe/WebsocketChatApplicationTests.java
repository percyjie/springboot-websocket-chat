package com.hehe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsocketChatApplicationTests {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SendMail sendMail;

    @Test
    public void contextLoads() {
        String to = "percyjie@gmail.com";
        String subject = "Springboot 发送简单文本邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 邮件</p>";
        mailService.sendSimpleTextMail(to, subject, content);
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String to = "percyjie@gmail.com";
        String subject = "Springboot 发送 HTML 邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 邮件</p>";
        mailService.sendHtmlMail(to, subject, content);
    }


    @Test
    public void sendAttachmentTest() throws MessagingException {
        String to = "percyjie@gmail.com";
        String subject = "Springboot 发送 HTML 附件邮件";
        String content = "<h2>Hi~</h2><p>第一封 Springboot HTML 附件邮件</p>";
        String filePath = "pom.xml";
        mailService.sendAttachmentMail(to, subject, content, filePath);
    }

    @Test
    public void sendImgTest() throws MessagingException {
        String to = "percyjie@gmail.com";
        String subject = "Springboot 发送 HTML 图片邮件";
        String content =
                "<h2>Hi~</h2><p>第一封 Springboot HTML 图片邮件</p><br/><img src='img/1.jpg' />";
        String imgPath = "img/1.jpg";
        Map<String, String> imgMap = new HashMap<>();
        imgMap.put("img01", imgPath);
        imgMap.put("img02", imgPath);
        mailService.sendImgMail(to, subject, content, imgMap);
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        String to = "percyjie@gmail.com";
        String subject = "Springboot 发送 模版邮件";
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("username", "Darcy");
        mailService.sendTemplateMail(to, subject, paramMap, "RegisterSuccess");
    }


    @Test
    public void sendMailWithPic() throws MessagingException {
        sendMail.sendImageMail(
        "percyjie@gmail.com",
                "helloWorld",
                "<h1 style='color:red'>helloWorld</h1><img src='cid:test001'/>",
                "/Users/percy/Desktop/test2.pdf",
                "test001"

        );
    }

}
