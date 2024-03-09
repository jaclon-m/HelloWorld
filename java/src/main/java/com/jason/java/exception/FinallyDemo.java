package com.jason.java.exception;

/**
 * @author jaclon
 * @date 2019/7/23
 * @time 10:54
 */
public class FinallyDemo {

    public static int testFinally(String _int, String _class){
        int x = 1;
        try {

            Integer.valueOf(_int);
            Class.forName(_class);
            return x;
        }catch (ClassNotFoundException e){
            return x = 2;
        }finally {
            x = 3;
        }
    }

    public static void main(String[] args) {
        try {
            /*int demo1 = testFinally("123123","java.lang.reflect.Method");
            System.out.println(demo1);*/
            int demo2 = testFinally("123123","com.edu.abb.c");
            System.out.println(demo2);
//            int demo3 = testFinally("sdfaf","java.lang.reflect.Method");
        } finally {

        }
    }
}
