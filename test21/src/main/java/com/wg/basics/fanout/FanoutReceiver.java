package com.wg.basics.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * fanout消费者
 */
@Component
public class FanoutReceiver {
    @RabbitListener(queues = "fanout1")
    public void fanout1(String msg){
        System.out.println("fanoutOne:"+msg);
    }
    @RabbitListener(queues = "fanout2")
    public void fanout2(String msg){
        System.out.println("fanoutTow:"+msg);
    }
}
