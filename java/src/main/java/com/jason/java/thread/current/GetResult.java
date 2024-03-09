/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.thread.current;

import com.jaclon.concurrent.base.Employee;
import org.springframework.beans.BeanUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Classname GetResult
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/1/8
 */
public class GetResult {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Employee employee = new Employee();
        for (int i = 0; i < 4; i++) {

            executorService.submit(()->{
                Employee emp = new Employee(1, "ss", 12, 3000D);
                BeanUtils.copyProperties(emp,employee);
                System.out.println(employee + "###########");
            });
        }
        Thread.sleep(1000);
        System.out.println(employee + "@@@@@@@@@@@@@@");

    }
}
