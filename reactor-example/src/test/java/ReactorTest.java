import com.ht.test.reactor.disruptor.LogEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.core.dispatch.RingBufferDispatcher;
import reactor.fn.Consumer;
import reactor.jarjar.com.lmax.disruptor.BlockingWaitStrategy;
import reactor.jarjar.com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static reactor.bus.selector.Selectors.$;

/**
 * Created by hutao on 16/5/11.
 * 下午3:48
 */
@Slf4j
public class ReactorTest {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Test
    public void testName() throws Exception {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final EventBus bus = EventBus.create(new Environment(), new RingBufferDispatcher("a",
                2048, throwable -> {
            log.error("bus error", throwable);
        },
                ProducerType.MULTI, // Single producer
                new BlockingWaitStrategy()));
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final ExecutorService executorService1 = Executors.newFixedThreadPool(20);
        final CountDownLatch countDownLatch = new CountDownLatch(200000);
        bus.on($("a"), new Consumer<Event<LogEvent>>() {
            @Override
            public void accept(final Event<LogEvent> event) {
                executorService1.submit(new Task(event) {
                    @Override
                    public void run() {
                        super.run();
                        countDownLatch.countDown();
                    }
                });
            }
        });
        for (int i = 0; i < 20; i++) {
            executorService.submit((Runnable) () -> {
                for (int j = 0; j < 10000; j++) {
                    bus.notify("a", Event.wrap(new LogEvent(RandomStringUtils.randomAlphabetic(10))));
                }
            });
        }

        countDownLatch.await();
        stopWatch.stop();
        log.info("@@@@@@@@@take time:{}", stopWatch.toString());
        executorService.shutdown();
        executorService1.shutdown();
    }

    @AllArgsConstructor
    public class Task implements Runnable {
        private final Event<LogEvent> event;

        @Override
        public void run() {
            log.info("######{}", count.addAndGet(1));
            log.info("thread[id={},name={}] handle event:{}", Thread.currentThread().getId(), Thread.currentThread().getName(), event.getData());
            System.out.println("Event: " + event);
        }
    }
}
