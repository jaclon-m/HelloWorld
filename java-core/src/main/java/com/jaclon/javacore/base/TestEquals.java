package com.jaclon.javacore.base;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/7/2 11:38
 */
public class TestEquals {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        int c = 5;
        a = b = c;
        System.out.println("a:"+ a+ "/t b:" + b + "/t c:" + c );
    }
}
