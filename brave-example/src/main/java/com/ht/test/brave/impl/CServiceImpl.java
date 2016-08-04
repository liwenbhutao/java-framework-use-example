package com.ht.test.brave.impl;

import com.ht.test.brave.CService;
import com.ht.test.brave.DService;
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
public class CServiceImpl implements CService {
    @Autowired
    private DService dService;

    @Override
    @SneakyThrows
    public void fun3() {
        log.info("fun3 begin");
        Thread.sleep(250);
        this.dService.fun4();
        log.info("fun3 end");
    }
}
