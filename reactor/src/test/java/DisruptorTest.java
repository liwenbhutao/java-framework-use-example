import com.ht.test.reactor.disruptor.LogEvent;
import com.ht.test.reactor.disruptor.LogEventFactory;
import com.ht.test.reactor.disruptor.LogEventHandler;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hutao on 16/5/11.
 * 下午2:55
 */
@Slf4j
public class DisruptorTest {
    @Test
    public void testName() throws Exception {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        Disruptor<LogEvent> disruptor = new Disruptor(new LogEventFactory(),
                2048, Executors.newFixedThreadPool(20),
                ProducerType.MULTI, // Single producer
                new BlockingWaitStrategy());
        final CountDownLatch countDownLatch = new CountDownLatch(200000);
        final List<WorkHandler> handlers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final LogEventHandler handler = new LogEventHandler() {
                @Override
                public void onEvent(LogEvent event) {
                    super.onEvent(event);
                    countDownLatch.countDown();
                }
            };
            handlers.add(handler);
        }
        disruptor.handleEventsWithWorkerPool(handlers.toArray(new WorkHandler[handlers.size()]));
/*
        disruptor.handleEventsWithWorkerPool(handlers.get(0),
                handlers.get(1),
                handlers.get(2),
                handlers.get(3),
                handlers.get(4),
                handlers.get(5),
                handlers.get(6),
                handlers.get(7),
                handlers.get(8),
                handlers.get(9)
        );
*/

        disruptor.start();
        for (int i = 0; i < 20; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    disruptor.publishEvent(new LogEventTranslator());
                }
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        log.info("@@@@@@@@@take time:{}", stopWatch.toString());
        executor.shutdown();
        disruptor.shutdown();


    }

    public class LogEventTranslator implements EventTranslator<LogEvent> {
        @Override
        public void translateTo(final LogEvent event, final long sequence) {
            event.setLog(RandomStringUtils.randomAlphabetic(10));
        }
    }
}
