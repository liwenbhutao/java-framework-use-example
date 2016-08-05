package com.ht.test.metrics;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class QueueManager {
    @Getter
    private final Queue<Integer> queue;

    public QueueManager(final MetricRegistry metrics, final String name) {
        this.queue = new ConcurrentLinkedDeque<>();
        metrics.register(MetricRegistry.name(QueueManager.class, name, "size"),
                (Gauge<Integer>) () -> this.queue.size());
    }
}