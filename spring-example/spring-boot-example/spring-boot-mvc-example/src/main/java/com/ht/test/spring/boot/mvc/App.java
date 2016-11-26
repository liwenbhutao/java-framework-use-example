package com.ht.test.spring.boot.mvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2016/11/22.
 *
 * @author hutao
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ht.test.spring.boot.mvc"})
@MapperScan("com.ht.test.spring.boot.mvc.service.impl.dao")
public class App extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(App.class, args);
    }
}
