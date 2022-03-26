package com.wg.basics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 简单邮件的发送
 */
@Component
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 使用SimpleMailMessage封装邮箱
     * @param from 发送人
     * @param to    接收人
     * @param cc    抄送人
     * @param subject 邮箱主题
     * @param content   邮箱内容
     */
    public void simpleMailSend(String from,String to,String cc,String subject,String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        javaMailSender.send(simpleMailMessage);
    }
}
