package com.ht.test.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.concurrent.TimeUnit;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
@UtilityClass
public class MetricsUtil {
    public static final MetricRegistry metrics = new MetricRegistry();

    public static void startReport() {
        final ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    @SneakyThrows
    public static void wait5Seconds() {
        Thread.sleep(5 * 1000);
    }
}
