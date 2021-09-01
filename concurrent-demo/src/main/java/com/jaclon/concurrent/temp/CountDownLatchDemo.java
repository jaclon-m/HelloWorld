package com.jaclon.concurrent.temp;

import java.util.concurrent.CountDownLatch;

/**
 * @author jaclon
 * @date 2019/9/16
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        closeDoor();
        countrySix();
    }

    private static void countrySix() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1 ; i <= 6 ; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "国\t被灭");
              }, CountryEnum.forEach(i).getMessage()).start();
        }
        countDownLatch.await();
        System.out.println("秦国一统六国");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t上完自习离开");
                //int j = 1/0;
                countDownLatch.countDown();
              },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长最后离开教室，锁门！");
    }
}
