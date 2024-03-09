package com.jason.java.thread.current;

import java.util.concurrent.*;

/**
 * 线程池的四种实现方式
 * @author jaclon
 * @date 2019/9/26
 */
public class FourMethodsOfThread {

    public static void main(String[] args) {
        /*//1. 继承Thread
        Thread t1 = new MyThread();
        t1.start();
        t1.interrupt();
        //2.实现Runnable
        Thread t2 = new Thread(new MyRunnable());
        t2.start();*/

        //3.使用Callable和FutureTask
        FutureTask <Integer> task = new FutureTask <>(new MyCallable());
        new Thread(task).start();
        try {
            Integer result = task.get();
            System.out.println("=============");
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //4. 使用线程池
        /*ExecutorService threadPool = Executors.newFixedThreadPool(5);
        MyRunnable r1 = new MyRunnable();
        for (int i = 0; i < 10; i++) {
            threadPool.submit(r1);
        }
        threadPool.shutdown();*/
    }

    // 线程池结合Callable
    public void poolDemo(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 启动线程
        Future<String> stringFuture = threadPool.submit( new Callable< String >( ) {
            @Override
            public String call ( ) throws Exception {
                return "nihao";
            }
        } );
        try {
            String result = stringFuture.get();
            System.out.println(result);
            System.out.println("===========");
        } catch ( InterruptedException e ) {
            e.printStackTrace( );
        } catch ( ExecutionException e ) {
            e.printStackTrace( );
        }
    }
}

class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("myThread");
    }

}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("myRunnable");
    }
}

class MyCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000*3);
        System.out.println("myCallable");
        return 0;
    }
}
