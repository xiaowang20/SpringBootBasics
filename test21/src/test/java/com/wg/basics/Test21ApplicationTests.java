package com.wg.basics;

import com.wg.basics.fanout.FanoutConfig;
import com.wg.basics.topic.TopicConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionFactoryBeanConfigurer;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test21ApplicationTests {
    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     * direct策略
     */
    @Test
    void directTest(){
        rabbitTemplate.convertAndSend("hello_direct","wg");
    }

    /**
     * fanout策略
     * 不需要routingKey,只需要指定交换机就可以
     */
    @Test
    void fanoutTest(){
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_NAME,null,"wg");
    }
    @Test
    void topicTest(){
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"xiaomi.new","小米新闻");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"xiaomi.phone","小米手机");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"huawei.new","华为新闻");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"huawei.phone","华为手机");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"phone.new","新闻");
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_NAME,"phone.phone","手机");
    }
}
