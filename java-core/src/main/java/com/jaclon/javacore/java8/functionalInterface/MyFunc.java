package com.jaclon.javacore.java8.functionalInterface;

@FunctionalInterface
public interface MyFunc<T> {
    T getValue(T t);
}
