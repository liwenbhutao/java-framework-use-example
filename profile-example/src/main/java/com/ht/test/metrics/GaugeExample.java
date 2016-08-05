package com.ht.test.metrics;

import com.codahale.metrics.MetricRegistry;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class GaugeExample {
    private static final QueueManager q = new QueueManager(MetricsUtil.metrics, "queue");

    public static void main(final String[] args) {
        MetricsUtil.startReport();
        MetricRegistry.name(QueueManager.class, "jobs", "1size");
        for (int i = 0; i < 10; i++) {
            q.getQueue().add(1);
        }
        MetricsUtil.wait5Seconds();
    }
}
