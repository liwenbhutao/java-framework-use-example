package com.ht.test.brave.impl;

import com.ht.test.brave.AService;
import com.ht.test.brave.BService;
import com.ht.test.brave.CService;
import com.ht.test.brave.profile.anotation.BraveAnnotation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
@Service
@Slf4j
public class AServiceImpl implements AService {
    @Autowired
    private BService bService;
    @Autowired
    private CService cService;

    @Override
    @SneakyThrows
    @BraveAnnotation(name = "请求志愿")
    public void fun1() {
        log.info("fun1 begin");
        Thread.sleep(250);
        this.bService.fun2();
        this.cService.fun3();
        log.info("fun1 end");
    }

    @Override
    @SneakyThrows
    @BraveAnnotation(name = "请求志愿2")
    public void echo(final String msg) {
        log.info("echo begin");
        Thread.sleep(250);
        this.bService.fun2();
        this.cService.fun3();
        log.info("echo end");
    }
}
