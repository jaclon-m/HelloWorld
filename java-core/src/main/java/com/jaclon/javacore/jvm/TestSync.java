package com.jaclon.javacore.jvm;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/5/12 11:49
 */
public class TestSync {
    public static Integer num = 0;
    public static void main(String[] args) throws InterruptedException {
        TestSync testSync = new TestSync();
        testSync.hello();
        testSync.hello2();
        System.out.println("===============");
    }

    public synchronized int hello(){
        num++;
        if(num < 3){
            hello();
        }
        return 1;
    }

    public boolean hello2() throws InterruptedException {
        synchronized (this){
            Thread.sleep(100);
            return true;
        }
    }

}
