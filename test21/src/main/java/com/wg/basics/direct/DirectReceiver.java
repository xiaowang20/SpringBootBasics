package com.wg.basics.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class DirectReceiver {

        @RabbitListener(queues = "hello_direct")
        public void receiver(String msg){
            System.out.println("directReceiver:"+msg);
        }
}
