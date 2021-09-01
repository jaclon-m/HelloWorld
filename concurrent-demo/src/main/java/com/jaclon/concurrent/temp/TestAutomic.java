package com.jaclon.concurrent.temp;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jaclon
 * @date 2019/7/13
 * @time 8:57
 */
public class TestAutomic {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        AutomicDemo automicDemo = new AutomicDemo();
        for(int i = 0;i<50;i++){
            new Thread(automicDemo).start();
        }
    }
}

class AutomicDemo implements Runnable{

    private volatile int serialNumber = 0;

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getSerialNumber() {
        return serialNumber++;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }
}
