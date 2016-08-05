package com.ht.test.metrics;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Map;

/**
 * Created on 16/8/5.
 *
 * @author hutao
 * @version 1.0
 */
@Slf4j
public class HealthChecksExample {
    final static HealthCheckRegistry healthChecks = new HealthCheckRegistry();

    public static class DatabaseHealthCheck extends HealthCheck {
        @Override
        public HealthCheck.Result check() throws Exception {
            if (RandomUtils.nextInt(0, 100) % 2 == 0) {
                log.info("ok");
                return HealthCheck.Result.healthy();
            } else {
                log.info("false");
                return HealthCheck.Result.unhealthy("Cannot connect to ");
            }
        }
    }

    @SneakyThrows
    public static void main(final String[] args) {
        MetricsUtil.startReport();
        healthChecks.register("postgres", new DatabaseHealthCheck());
        for (int i = 0; i < 10; i++) {
            printHealthCheckResult(healthChecks.runHealthChecks());
        }
        MetricsUtil.wait5Seconds();
    }

    private static void printHealthCheckResult(final Map<String, HealthCheck.Result> results) {
        for (final Map.Entry<String, HealthCheck.Result> entry : results.entrySet()) {
            if (entry.getValue().isHealthy()) {
                System.out.println(entry.getKey() + " is healthy");
            } else {
                System.err.println(entry.getKey() + " is UNHEALTHY: " + entry.getValue().getMessage());
                final Throwable e = entry.getValue().getError();
                if (e != null) {
                    e.printStackTrace();
                }
            }
        }
    }
}
