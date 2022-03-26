package com.wg.basics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 添加附件文档
 */
@Component
public class AttachmentMailService {
    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 使用javaMailSender创建邮件
     * 提供Helper简化操作
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param file  相对简单邮件发送，去除了抄送者，添加File:发送附件文档
     */
    public void simpleMailSend(String from, String to, String subject, String content, File file){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            messageHelper.addAttachment(file.getName(),file);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
