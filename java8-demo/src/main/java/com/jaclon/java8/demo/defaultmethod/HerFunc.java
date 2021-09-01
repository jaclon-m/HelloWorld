package com.jaclon.java8.demo.defaultmethod;

//@FunctionalInterface
public interface HerFunc {

    //public void getName();

    default void show(){
        System.out.println("this is her function");
    }
    //静态方法
    static void getName(){
        System.out.println("this is static method");
    }
}
