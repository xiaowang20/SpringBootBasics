package com.wg.basics.controller;


import com.wg.basics.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

    @MessageMapping("/hello")//先到走/app/hello,处理完后，再走SendTo路径
    @SendTo("/topic/greetings")//交给broker代理
    public Message message(Message message)throws Throwable{
        return message;
    }
}
