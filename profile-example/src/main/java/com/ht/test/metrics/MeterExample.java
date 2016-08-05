package com.ht.test.metrics;

import com.codahale.metrics.Meter;

public class MeterExample {
    public static void main(final String[] args) {
        MetricsUtil.startReport();
        final Meter requests = MetricsUtil.metrics.meter("requests");
        final Meter requests1 = MetricsUtil.metrics.meter("requests1");
        requests.mark();
        requests1.mark();
        MetricsUtil.wait5Seconds();
    }
}
