package com.wg.basics;

import com.wg.basics.service.AttachmentMailService;
import com.wg.basics.service.ImgMailService;
import com.wg.basics.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class Test16ApplicationTests {

//    @Autowired
//    MailService mailService;

//    @Autowired
//    AttachmentMailService attachmentMailService;

    @Autowired
    ImgMailService imgMailService;
//    @Test
//    void contextLoads() {
//        mailService.simpleMailSend("2418937428@qq.com",
//                "2118943179@qq.com",
//                "1111111111.@qq.com","邮箱主题","邮箱的内容：你好！");
//    }
//    @Test
//    void send(){
//        attachmentMailService.simpleMailSend("2418937428@qq.com","2118943179@qq.com"
//        ,"邮箱主题","邮箱的内容：你好！",new File("E:\\邮件附件.docx"));
//    }

    @Test
    public void sendImg(){
        imgMailService.sendImgMail("2418937428@qq.com",
                "2118943179@qq.com","邮箱主题",
                "<div>带有图片资源的邮件"
                        + "这是图片1：<div><img src='cid:p01'></div>"
                        + "这是图片2：<div><img src='cid:p02'></div>"
                        +"</div>",
                        new String[]{"C:\\Users\\yoga\\Desktop\\img\\lyf.jpeg","C:\\Users\\yoga\\Desktop\\img\\lyf1.jpeg"},
                        new String[]{"p01","p02"}
        );
    }
}
