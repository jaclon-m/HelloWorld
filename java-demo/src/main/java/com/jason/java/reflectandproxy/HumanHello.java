/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.reflectandproxy;
/**
 * @Classname HumanHello
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/6/22
 */
public class HumanHello implements Hello {
    @Override
    public void morning(String name) {
        System.out.println("Human Hello");
    }
}
