/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.thread;

import com.jason.java.thread.current.FourMethodsOfThread;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author jaclon
 * @Classname Test
 * @Description hashMap 并发修改问题
 * @date 2020/5/14
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        test();
    }

    public static void test0() throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(8);
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(UUID.randomUUID().toString().substring(0,7), "");
                }
            },String.valueOf(i)).start();
        }
        Thread.sleep(1000);

        System.out.println(map.size());
    }

    public static void test(){
        MyRunable threadA = new MyRunable("ThreadA");
        threadA.start();
        Thread threadB = new ThreadB("threadB");
        threadB.start();
    }
}

class MyRunable implements Runnable{
    private Thread thread;
    private String threadName;

    public MyRunable(String threadName){
        this.threadName = threadName;
        thread = new Thread(this,threadName);
    }
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println(threadName + ":" + i);
        }
    }
    public void start(){
        thread.start();
    }
}

class ThreadB extends Thread{
    private String threadName;

    public ThreadB(String threadName){
        super(threadName);
        this.threadName = threadName;
    }

    public void run(){
        for(int i =0;i<100;i++){
            System.out.println(threadName + ":"+i);
        }
    }
}
