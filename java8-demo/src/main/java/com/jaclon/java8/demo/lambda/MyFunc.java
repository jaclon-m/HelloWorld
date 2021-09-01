package com.jaclon.java8.demo.lambda;

@FunctionalInterface
public interface MyFunc<T> {

    T getValue(T t);
}
