package com.ht.test.brave.impl;

import com.ht.test.brave.DService;
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
public class DServiceImpl implements DService {
    @Override
    @SneakyThrows
    public void fun4() {
        log.info("fun4 begin");
        Thread.sleep(250);
        log.info("fun4 end");
    }
}
