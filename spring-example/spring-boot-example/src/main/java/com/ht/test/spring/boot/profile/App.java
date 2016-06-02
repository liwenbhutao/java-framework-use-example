package com.ht.test.spring.boot.profile;

import com.ht.test.spring.boot.profile.support.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by hutao on 16/6/1.
 * 下午11:53
 */
@SpringBootApplication
@Slf4j
@ImportResource("spring-application-profile.xml")
@PropertySource("config.properties")
public class App implements CommandLineRunner {
    @Autowired
    private HelloService helloService;

    public static void main(String[] args) {
        //System.setProperty("spring.profiles.active", "profile2");
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(final String... strings) throws Exception {
        helloService.hello();
    }
}
