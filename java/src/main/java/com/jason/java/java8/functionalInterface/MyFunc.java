package com.jason.java.java8.functionalInterface;

@FunctionalInterface
public interface MyFunc<T> {
    T getValue(T t);
}
