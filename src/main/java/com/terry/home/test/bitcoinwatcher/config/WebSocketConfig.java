package com.terry.home.test.bitcoinwatcher.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 啟用一個訊息代理並設定訊息發送目地的前綴路徑，前端會訂閱此 Topic
        registry.enableSimpleBroker("/topic");
        // 設定導向至訊息處理器的前綴路徑
        registry.setApplicationDestinationPrefixes("/send");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 註冊一個給 Client 連至 WebSocket Server 的節點 (websocket endpoint)
        registry.addEndpoint("/bitcoin").setAllowedOrigins("*") // http://localhost:8081
            .withSockJS();
    }

}
