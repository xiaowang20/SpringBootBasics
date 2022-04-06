package com.wg.basics.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * topic消费者
 */
@Component
public class TopicReceiver {
    @RabbitListener(queues = "xiaomi")
    public void xiaomiReceiver(String msg){
        System.out.println("topicReceiver1:"+ msg);
    }
    @RabbitListener(queues = "huawei")
    public void huaweiReceiver(String msg){
        System.out.println("topicReceiver2:"+ msg);
    }
    @RabbitListener(queues = "xiaomi")
    public void phoneReceiver(String msg){
        System.out.println("topicReceiver3:"+ msg);
    }
}
