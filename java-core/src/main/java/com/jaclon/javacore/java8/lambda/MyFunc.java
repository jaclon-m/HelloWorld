package com.jaclon.javacore.java8.lambda;

@FunctionalInterface
public interface MyFunc<T> {

    T getValue(T t);
}
