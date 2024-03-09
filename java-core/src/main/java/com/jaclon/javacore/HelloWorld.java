package com.jaclon.javacore;

import java.util.*;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2022/11/30 15:16
 */
public class HelloWorld {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }
        Arrays.sort(arr);
        for(long i : arr){
            System.out.print(i + " ");
        }*/
        test3();

    }

    public void test1(){
        Thread t = new Thread(){
            @Override
            public void  run(){
                System.out.println("1111");
            }
        };
        t.start();
    }

    public void test2(){
        Runnable r = new Runnable(){
            @Override
            public void run(){
                System.out.println("22222");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    public static void test3(){
        List<Long> list = new ArrayList<>();
        list.add(8L);
        Long a = null;
        list.add(a);
        System.out.println("=====");
    }
}
