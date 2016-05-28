package com.ht.test.reactor;

import com.ht.test.reactor.disruptor.LogEvent;
import com.ht.test.reactor.disruptor.LogEventFactory;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hutao on 16/5/10.
 * 下午3:42
 */
public class DisruptorApp {
    public void run() {
        Executor executor = Executors.newCachedThreadPool();
        Disruptor<LogEvent> disruptor = new Disruptor(new LogEventFactory(),
                8, Executors.newCachedThreadPool(),
                ProducerType.SINGLE, // Single producer
                new BlockingWaitStrategy());
        //disruptor.handleEventsWith(new LogEventHandler());
        disruptor.start();
        disruptor.publishEvent(new EventTranslator<LogEvent>() {
            @Override
            public void translateTo(LogEvent event, long sequence) {
                event.setLog("asdfsff");
            }
        });
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
        disruptor.shutdown();
    }

}
