package com.ht.test.metrics;

import com.codahale.metrics.Histogram;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class HistogramsExample {
    private static final Histogram responseSizes = MetricsUtil.metrics.histogram(name(String.class, "response-sizes"));

    public static void handleRequest(final String str) {
        responseSizes.update(str.length());
    }

    public static void main(final String[] args) {
        MetricsUtil.startReport();
        for (int i = 0; i < 100; i++) {
            handleRequest(RandomStringUtils.random(100).substring(0, RandomUtils.nextInt(1, 100)));
        }
        MetricsUtil.wait5Seconds();
    }
}
