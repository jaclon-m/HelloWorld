/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.threadandconcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ExecutorAwait
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/1/8
 */
public class ExecutorAwait {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            Runnable runnable =  new Runnable(){
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": 开始执行。。。");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ": 执行结束。。。");
                }

            };
            executorService.execute(runnable);
        }

        try {
            //executorService.shutdown();
            while (!executorService.awaitTermination(5, TimeUnit.SECONDS)){
                //executorService.shutdownNow();
            }
            System.out.println("所有子线程都执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
