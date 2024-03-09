package com.jason.java.java8.lambda;

@FunctionalInterface
public interface MyFunc<T> {

    T getValue(T t);
}
