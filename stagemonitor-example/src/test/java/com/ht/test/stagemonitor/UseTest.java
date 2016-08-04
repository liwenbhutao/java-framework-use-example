package com.ht.test.stagemonitor;

import org.stagemonitor.alerting.annotation.SLA;
import org.stagemonitor.core.Stagemonitor;
import org.stagemonitor.requestmonitor.MonitorRequests;
import org.testng.annotations.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created on 16/7/29.
 *
 * @author hutao
 * @version 1.0
 */
public class UseTest {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private void execute() {
        this.executorService.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                doBatchWork();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @MonitorRequests(requestName = "sdafafsdfa")
    @SLA(errorRateThreshold = 0, metric = SLA.Metric.P95, threshold = 500)
    private void doBatchWork() {
        System.out.println("doing batch work...");
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done with batch work...");

    }

    @Test
    public void testName() throws Exception {
        Stagemonitor.init();
        execute();
        this.executorService.awaitTermination(1000, TimeUnit.HOURS);
    }
}
