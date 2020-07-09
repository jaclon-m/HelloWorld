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
    public static void main(String[] args) {
        Test test = new Test();
        test.method1();
        test.method2();
        Unsafe unsafe = Unsafe.getUnsafe();
    }
     int a = 0;
    boolean flag = false;
    public void method1(){
        a = 1;
        flag = true;
    }
    public void method2(){
        if(flag){
            a = a+5;
            System.out.println("===========" + a);
        }
    }

}
