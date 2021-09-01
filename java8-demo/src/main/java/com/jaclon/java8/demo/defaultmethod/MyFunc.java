package com.jaclon.java8.demo.defaultmethod;

//@FunctionalInterface
public interface MyFunc {

    //默认方法
    default void show(){
        System.out.println("this is MyFuntion method");
    }

    //public void test();

    //静态方法
    static void getName(){
        System.out.println("this is static method");
    }
}

