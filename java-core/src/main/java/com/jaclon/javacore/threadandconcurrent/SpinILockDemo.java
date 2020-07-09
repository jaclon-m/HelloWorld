package com.jaclon.javacore.threadandconcurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author jaclon
 * @date 2019/9/14
 */
public class SpinILockDemo {

    AtomicReference <Thread> atomicReference = new AtomicReference <>();

    public void mylock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getId() + "====mylock()");
        while (!atomicReference.compareAndSet(null, thread)) ;
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getId() + "=======myUnlock()");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinILockDemo spinILockDemo = new SpinILockDemo();

        new Thread(() -> {
            spinILockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinILockDemo.myUnlock();
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinILockDemo.mylock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinILockDemo.myUnlock();
        }, "t2").start();
    }
}
