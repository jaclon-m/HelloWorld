/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.thread.current;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Classname AsyncDemo
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/4/10
 */
@Component
public class AsyncDemo {

    private static final Logger log = LoggerFactory.getLogger(AsyncDemo.class);

    /**
     * 最简单的异步调用，返回值为void
     */
    @Async
    public void asyncInvokeSimplest() {
        log.info("asyncSimplest");
    }

    /**
     * 带参数的异步调用 异步方法可以传入参数
     *
     * @param s
     */
    @Async
    public void asyncInvokeWithParameter(String s) {
        log.info("asyncInvokeWithParameter, parementer={}", s);
    }

    /**
     * 异常调用返回Future
     *
     * @param i
     * @return
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) {
        log.info("asyncInvokeReturnFuture, parementer={}", i);
        Future<String> future;
        try {
            Thread.sleep(1000 * 1);
            future = new AsyncResult<String>("success:" + i);
        } catch (InterruptedException e) {
            future = new AsyncResult<String>("error");
        }
        return future;
    }
}
