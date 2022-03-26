package com.wg.basics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 发送带有图片的邮件
 */
@Component
public class ImgMailService {
    @Autowired
    JavaMailSender javaMailSender;

    /**
     * 资源路径要与资源id要相等才能发送成功
     * @param from
     * @param to
     * @param subject
     * @param content
     * @param imgPath   资源（图片）路径
     * @param resourceId    资源ID：指的一段HTML
     */
    public void sendImgMail(String from, String to,
                            String subject, String content,
                            String[] imgPath,String[] resourceId){
        if (imgPath.length != resourceId.length){
            System.out.println("发送失败！");
            return;
        }
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true);
            for (int i = 0; i < imgPath.length; i++) {
                FileSystemResource fileSystemResource = new FileSystemResource(new File(imgPath[i]));
                messageHelper.addInline(resourceId[i],fileSystemResource);
            }
            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println("发送失败！");
        }

    }
    }

