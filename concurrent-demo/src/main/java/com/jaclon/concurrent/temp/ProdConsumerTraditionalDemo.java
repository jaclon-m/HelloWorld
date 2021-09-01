package com.jaclon.concurrent.temp;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程口诀
 * 线程  操作（方法）  资源类
 * 判断  干活    通知
 * 防止虚假唤醒
 * @author jaclon
 * @date 2019/9/16
 */
public class ProdConsumerTraditionalDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.increment();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    shareData.decrement();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}


class ShareData{
    private int  num  = 0;
    private  Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {

        lock.lock();
        try{
            while (num != 0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
           lock.unlock();
        }

    }

    public void decrement() throws InterruptedException {

        lock.lock();
        try{
            while (num == 0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
