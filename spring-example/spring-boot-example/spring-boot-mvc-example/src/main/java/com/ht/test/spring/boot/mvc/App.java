package com.ht.test.spring.boot.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2016/11/22.
 * SpringBootServletInitializer
 *
 * @author hutao
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ht.test.spring.boot.mvc", "com.ht.common.spring.util"})
@MapperScan("com.ht.test.spring.boot.mvc.service.impl.dao")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class App /*extends SpringBootServletInitializer*/ {
    /*@Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }*/

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }

/*
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        return new TomcatEmbeddedServletContainerFactory();
    }
*/

/*
    @Override
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        System.out.println();
    }
*/
}
