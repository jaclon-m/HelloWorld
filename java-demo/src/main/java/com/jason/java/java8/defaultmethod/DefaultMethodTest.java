package com.jason.java.java8.defaultmethod;


public class DefaultMethodTest implements MyFunc,HerFunc{

    public static void main(String[] args) {

    }

    @Override
    public void show() {
        MyFunc.super.show();
    }
}
