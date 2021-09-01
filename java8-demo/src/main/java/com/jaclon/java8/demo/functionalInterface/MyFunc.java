package com.jaclon.java8.demo.functionalInterface;

@FunctionalInterface
public interface MyFunc<T> {
    T getValue(T t);
}
