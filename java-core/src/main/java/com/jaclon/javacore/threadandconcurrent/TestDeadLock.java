package com.jaclon.javacore.threadandconcurrent;

/**
 * @author jaclon
 * @date 2019/9/12
 */
public class TestDeadLock {
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
                synchronized(TestDeadLock.objA){
                    System.out.println("Lock1 lock strA");
                    Thread.sleep(3000);
                    synchronized(TestDeadLock.objB){
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
                synchronized(TestDeadLock.objB){
                    System.out.println("Lock2 lock strB");
                    Thread.sleep(3000);
                    synchronized(TestDeadLock.objA){
                        System.out.println("Lock2 lock strA");
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
