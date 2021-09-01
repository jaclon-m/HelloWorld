package com.jaclon.concurrent.temp;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jaclon
 * @date 2019/9/16
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /*CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七颗龙珠，召唤神龙！");
        });*/

        CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

        try{
            for (int i = 0; i < 6; i++) {
                new Thread(() -> {
                    try {
                        System.out.println("收集到第" + Thread.currentThread().getName() + "\t颗龙珠");
                        int j = 1/0;

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }finally {
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }

                },String.valueOf(i+1)).start();
            }
            new Thread(()->{
                try {
                    System.out.println("收集到第 7颗龙珠");
                    int j = 1/0;

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }finally {
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }finally{


        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("集齐七颗龙珠，召唤神龙！");


    }
}
