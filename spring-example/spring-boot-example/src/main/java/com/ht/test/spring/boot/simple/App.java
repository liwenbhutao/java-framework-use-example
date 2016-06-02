package com.ht.test.spring.boot.simple;

import com.ht.test.spring.boot.simple.support.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by hutao on 16/6/1.
 * 下午11:53
 */
@SpringBootApplication
@Slf4j
@ImportResource("spring-application-simple.xml")
public class App implements CommandLineRunner {
    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(final String... strings) throws Exception {
        helloService.hello();
    }
}
