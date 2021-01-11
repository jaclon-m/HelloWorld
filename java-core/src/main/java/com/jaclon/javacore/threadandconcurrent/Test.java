package com.jaclon.javacore.threadandconcurrent;

import sun.misc.Unsafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jaclon
 * @date 2019/9/12
 */
public class Test {
    public static String objA = "strA";
    public static String objB = "strB";
    public static void main(String[] args){
        Thread a = new Thread(new Lock1());
        Thread b = new Thread(new Lock2());
        a.start();
        b.start();
    }
}
class Lock1 implements Runnable{
    @Override
    public void run(){
        try{
            while(true){
                synchronized(Test.objA){
                    System.out.println("Lock1 lock strA");
                    Thread.sleep(3000);
                    synchronized(Test.objB){
                        System.out.println("Lock1 lock strB");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
class Lock2 implements Runnable{
    @Override
    public void run(){
        try{
            while(true){
                synchronized(Test.objB){
                    System.out.println("Lock2 lock strB");
                    Thread.sleep(3000);
                    synchronized(Test.objA){
                        System.out.println("Lock2 lock strA");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
