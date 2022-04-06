package com.wg.basics.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {
    public static final String DIRECT_NAME = "wg_direct";
    @Bean
    Queue directQueue(){
        return new Queue("hello_direct");
    }

    /**
     * 参数1：默认交换机名字
     * 参数2：重启后是否依然有效
     * 参数3：长时间未使用是否删除
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_NAME,true,false);
    }

    /**
     * 绑定交换机和队列
     * @return
     */
    @Bean
    Binding binding(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }
}
