package com.jaclon.mistakesOfBuz.productionready;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/8/12 15:02
 */
@Component
public class ThreadPoolInfoContributor implements InfoContributor {
    private static Map threadPoolInfo(ThreadPoolExecutor threadPool) {
        Map<String, Object> info = new HashMap<>();
        info.put("poolSize", threadPool.getPoolSize());
        info.put("corePoolSize", threadPool.getCorePoolSize());
        info.put("largestPoolSize", threadPool.getLargestPoolSize());
        info.put("maximumPoolSize", threadPool.getMaximumPoolSize());
        info.put("completedTaskCount", threadPool.getCompletedTaskCount());
        return info;
    }

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("demoThreadPool", threadPoolInfo(ThreadPoolProvider.getDemoThreadPool()));
        builder.withDetail("ioThreadPool", threadPoolInfo(ThreadPoolProvider.getIOThreadPool()));
    }
}
