/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author jaclon
 * @Classname Test
 * @Description TODO
 * @date 2020/5/14
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(8);
        for (int i = 0; i < 10000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map.put(UUID.randomUUID().toString().substring(0,7), "");
                }
            },String.valueOf(i)).start();
        }
        Thread.sleep(1000);

        System.out.println(map.size());
    }
}
