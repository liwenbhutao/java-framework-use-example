package com.ht.test.reactor.disruptor;

import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hutao on 16/5/10.
 * 下午4:01
 */
@Slf4j
public class LogEventHandler implements WorkHandler<LogEvent> {
    private final static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void onEvent(final LogEvent event) {
        log.info("##########:{}", count.addAndGet(1));
        log.info("thread[id={},name={}] handle event:{}", Thread.currentThread().getId(), Thread.currentThread().getName(), event);
        System.out.println("Event: " + event);
    }
}
