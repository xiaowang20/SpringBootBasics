package com.wg.basics.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout策略配置
 */
@Configuration
public class FanoutConfig {
    public static final String FANOUT_NAME = "fanout";
    /**
     * 创建两个队列
     *
     */
    @Bean
    Queue queueOne(){
        return new Queue("hello_fanout1");
    }
    @Bean
    Queue queueTow(){
        return new Queue("hello_fanout2");
    }

    /**
     * 只需要创建一个Fanout交换机
     */
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_NAME,true,false);
    }
    /**
     * 绑定队列与交换机
     * 不需要routingKey
     */
    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }
    @Bean
    Binding bindingTow(){
        return BindingBuilder.bind(queueTow()).to(fanoutExchange());
    }
}
