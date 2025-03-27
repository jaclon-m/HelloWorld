package com.jason.java.thread;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2024/11/18 15:51
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "\t 构造方法");
    }
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i = 0 ;i< 10; i++){
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
