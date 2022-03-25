package com.wg.basics.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * 配置WebSocket
 * 注：
 *  1。实现WebSocketMessageBrokerConfigurer
 *  2.重写configureMessageBroker
 *  3.重写registerStompEndpoints
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat").withSockJS();//配置前缀为chat的endpoint,开启socketJs
    }

    /**
     * 消息--->消息代理broker--->client
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/topic");//设置消息代理前缀
    registry.setApplicationDestinationPrefixes("/app");//设置多个前缀，可以交给@MessageMapping方法处理
    }
}
