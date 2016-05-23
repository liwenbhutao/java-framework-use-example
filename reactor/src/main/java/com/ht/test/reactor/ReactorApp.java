package com.ht.test.reactor;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;
import reactor.fn.Consumer;
import reactor.fn.Function;
import reactor.jarjar.com.lmax.disruptor.BlockingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by hutao on 16/5/10.
 * 下午4:14
 */
@Slf4j
public class ReactorApp {
    public void run() {
        Environment.initialize();
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        EventBus bus = EventBus.create(Environment.get(), new RingBufferDispatcher("a",
                8, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {

            }
        },
                ProducerType.SINGLE, // Single producer
                new BlockingWaitStrategy()));

        bus.on($("topic"), (Event<String> ev) -> {
            String s = ev.getData();
            log.info("Got {} on thread {}%n", s, Thread.currentThread());
            countDownLatch.countDown();
        });

        bus.on($("topic"), (Event<String> ev) -> {
            String s = ev.getData();
            log.info("Got {} on thread {}%n", s, Thread.currentThread());
        });


        bus.sendAndReceive("topic", Event.wrap("Hello World!"), event -> {
            log.info("send and receive event:{}", event);
        });

        bus.notify("topic", Event.wrap("Hello World!"));
        bus.notify("topic", Event.wrap("Hello World!"));
        bus.receive($("topic1"), new Function<Event<String>, String>() {
            @Override
            public String apply(Event<String> event) {
                log.info("receive event:{}", event);
                return "afdsaf";
            }
        });
        bus.sendAndReceive("topic1", Event.wrap("send msg"), event -> {
            log.info("send and receive event:{}", event);
            countDownLatch.countDown();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw Throwables.propagate(e);
        }
        Environment.workDispatcher().awaitAndShutdown();
    }
}
