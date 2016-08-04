package com.ht.test.brave.impl;

import com.ht.test.brave.BService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
@Service
@Slf4j
public class BServiceImpl implements BService {
    @Override
    @SneakyThrows
    public void fun2() {
        log.info("fun2 begin");
        Thread.sleep(250);
        log.info("fun2 end");
    }
}
