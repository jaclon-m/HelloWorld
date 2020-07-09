/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.threadandconcurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname AsyncApplicationWithAnnotation
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/4/10
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplicationWithAnnotation {

    private static final Logger log = LoggerFactory.getLogger(AsyncApplicationWithAnnotation.class);

    /**
     * 自定义异步线程池
     *  如果没有这个方法，则使用默认的线程池
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");//设置线程名称的前缀
        executor.setCorePoolSize(5);//设置核心线程数
        executor.setMaxPoolSize(10);//设置最大线程数

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // .....
            }
        });
        // 使用预定义的异常处理类
        // executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

    public static void main(String[] args) {
        log.info("Start AsyncApplication.. ");
        SpringApplication.run(AsyncApplicationWithAnnotation.class, args);
    }
}
