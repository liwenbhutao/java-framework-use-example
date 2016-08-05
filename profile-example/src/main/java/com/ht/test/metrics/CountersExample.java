package com.ht.test.metrics;

import com.codahale.metrics.Counter;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class CountersExample {
    private static final Counter pendingJobs = MetricsUtil.metrics.counter(name(QueueManager.class, "pending-jobs"));

    public static void addJob() {
        pendingJobs.inc();
    }

    public static void takeJob() {
        pendingJobs.dec();
    }
}
