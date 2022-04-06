package com.wg.basics.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

/**
 * topic策略配置
 */
public class TopicConfig {
    public static final String TOPIC_NAME = "topic";
    /**
     * 定义一个topic交换机
     */
    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_NAME,true,false);
    }
    /**
     * 定义多个队列
     *
     */
    @Bean
    Queue xiaomi(){
        return new Queue("xiaomi");
    }
    @Bean
    Queue huawei(){
        return new Queue("huawei");
    }
    @Bean
    Queue phone(){
        return new Queue("phone");
    }
    /**
     * 绑定交换机和队列
     * routingKey:参数后面加.#表示凡是以参数开头的routingKey,都将被路由到相应的队列。
     */
    @Bean
    Binding xiaomiBind(){
        return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");
    }
    @Bean
    Binding huaweiBind(){
        return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
    }
    @Bean
    Binding phoneBind(){
        return BindingBuilder.bind(phone()).to(topicExchange()).with("phone.#");
    }

}
