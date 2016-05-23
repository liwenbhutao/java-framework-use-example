import com.google.common.util.concurrent.SettableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.SettableListenableFuture;
import org.testng.annotations.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hutao on 16/5/10.
 * 下午5:48
 */
@Slf4j
public class A {
    @Test
    public void testName() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final SettableListenableFuture<String> future = new SettableListenableFuture<>();
        new Thread(() -> {
            try {
                log.info("start thread");
                log.info("get future obj:{}", future.get(1000, TimeUnit.MILLISECONDS));
                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
        future.set("dsafdfaf");
        countDownLatch.await();

    }

    @Test
    public void testName2() throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final SettableFuture<String> future = SettableFuture.create();

        new Thread(() -> {
            try {
                log.info("start thread");
                log.info("get future obj:{}", future.get());
                countDownLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        future.set("dsafdfaf");
        countDownLatch.await();
    }

    @Test
    public void testName3() throws Exception {

    }
}
