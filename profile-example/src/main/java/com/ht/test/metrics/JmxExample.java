package com.ht.test.metrics;

import com.codahale.metrics.JmxReporter;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
public class JmxExample {
    final static JmxReporter reporter = JmxReporter.forRegistry(MetricsUtil.metrics).build();

    public static void main(final String[] args) {
        reporter.start();


    }
}
