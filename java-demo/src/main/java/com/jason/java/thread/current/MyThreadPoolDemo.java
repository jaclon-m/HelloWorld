package com.jason.java.thread.current;

import java.util.concurrent.*;

/**
 * @author jaclon
 * @date 2019/9/17
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue <>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try{
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }

    }

    public void test(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10)
                , new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0;i< 10;i++){
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }
}
