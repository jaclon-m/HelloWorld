package com.jason.java.thread.current;

import org.springframework.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jaclon
 * @date 2019/9/16
 */
public class ProdConsumerBlockQueueDemo {

    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t生产线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=======================================");
        System.out.println("时间到，活动停止");
        myResource.stop();
    }
}

class MyResource{
    private volatile boolean flag = true;
    AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue <String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (flag){
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName() + "\t插入队列" +data + "成功");
            }else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" +data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "FLAG=flase停止生产");
    }

    public void myConsumer() throws InterruptedException {

        String retValue;

        while (flag){
            retValue = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(StringUtils.isEmpty(retValue)){
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t超过两秒没有取到产品，消费结束");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列消费产品" + retValue + "成功");
        }
    }
    public void stop(){
        flag = false;
    }
}
