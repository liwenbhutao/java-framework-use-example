package com.ht.test.metrics;

import com.codahale.metrics.Timer;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class TimersExample {
    private static final Timer responses = MetricsUtil.metrics.timer(name(Long.class, "responses"));

    @SneakyThrows
    public static String handleRequest(final long time) {
        final Timer.Context context = responses.time();
        try {
            // etc;
            Thread.sleep(RandomUtils.nextInt(1, 1000));
            return "OK";
        } finally {
            context.stop();
        }
    }

    public static void main(final String[] args) {
        MetricsUtil.startReport();
        for (int i = 0; i < 10; i++) {
            handleRequest(1);
        }
    }
}
