package com.hehe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author percy.
 * @Date: 2020/10/9
 */
@Service
public class SendMail {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 功能描述：发送带图片的邮件
     *
     * @param to      发送目标邮箱
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param imgPath 图片路径
     * @param imgId   图片id，在img标签里使用
     * @throws MessagingException the messaging exception
     *
     */
    public void sendImageMail(String to, String subject, String content, String imgPath, String imgId) throws MessagingException {
        //创建message
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //发件人
        helper.setFrom(from);
        //收件人
        helper.setTo(to);
        //标题
        helper.setSubject(subject);
        //true指的是html邮件，false指的是普通文本
        helper.setText(content, true);
        //添加图片
        FileSystemResource file = new FileSystemResource(new File(imgPath));
        helper.addInline(imgId, file);
        //发送邮件
        mailSender.send(message);
    }

}
