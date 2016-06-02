package com.ht.test.spring.boot.simple.support;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by hutao on 16/6/2.
 * 上午12:12
 */
@Service
@Slf4j
public class HelloServiceImpl implements com.ht.test.spring.boot.simple.support.HelloService {
    @Setter
    private String str;

    @Override
    public void hello() {
        log.info("hello world" + str);
    }
}
