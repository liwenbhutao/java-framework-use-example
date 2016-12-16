package com.ht.test.spring.boot.mvc;

import com.ht.test.spring.boot.mvc.echo.EchoServiceImpl;
import com.ht.test.spring.boot.mvc.echo.EchoWebSocketHandler;
import com.ht.test.spring.boot.mvc.interceptor.AHandshakeInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author hutao <胡涛, hutao@coolqi.com>
 * @version v1.0
 * @project test-spring-boot-example
 * @Description
 * @encoding UTF-8
 * @date 12/15/16
 * @time 7:58 PM
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Configuration
@EnableWebSocket
//@EnableWebSocketMessageBroker  AbstractWebSocketMessageBrokerConfigurer
@Slf4j
public class CustomWebSocketConfiguration implements WebSocketConfigurer {
/*    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket")
                .setHandshakeHandler((request, response, wsHandler, attributes) -> {
                    log.info("doHandshake");
                    return true;
                }).withSockJS();
    }*/

    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler(new EchoWebSocketHandler(new EchoServiceImpl()), "/echo")
                .addInterceptors(new AHandshakeInterceptor()).withSockJS();
    }
}
