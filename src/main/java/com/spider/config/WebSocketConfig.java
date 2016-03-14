package com.spider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * websocket配置类
 *
 * @author wsy
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/spider-web");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/hello").withSockJS();
    }

}