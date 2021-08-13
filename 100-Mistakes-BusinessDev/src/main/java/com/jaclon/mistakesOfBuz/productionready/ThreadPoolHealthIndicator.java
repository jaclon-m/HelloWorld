package com.jaclon.mistakesOfBuz.productionready;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/12 14:49
 */
public class ThreadPoolHealthIndicator implements HealthIndicator {
    private ThreadPoolExecutor threadPool;

    public ThreadPoolHealthIndicator(ThreadPoolExecutor threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public Health health() {
        Map<String, Integer> detail = new HashMap<>();
        detail.put("queue_size", threadPool.getQueue().size());
        detail.put("queue_remaining", threadPool.getQueue().remainingCapacity());
        if (threadPool.getQueue().remainingCapacity() > 0) {
            return Health.up().withDetails(detail).build();
        } else {
            return Health.down().withDetails(detail).build();
        }
    }
}
