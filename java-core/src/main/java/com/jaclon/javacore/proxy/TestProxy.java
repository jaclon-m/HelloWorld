/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname TestProxy
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/6/22
 */
public class TestProxy {
    public static void main(String[] args) {
        Hello hInterface = new HumanHello();
        HelloHandler helloHandler = new HelloHandler(hInterface);
        Hello hello = (Hello)Proxy.newProxyInstance(hInterface.getClass().getClassLoader(),hInterface.getClass().getInterfaces(),helloHandler);
        //Hello hello = (Hello)Proxy.newProxyInstance(HumanHello.class.getClassLoader(),HumanHello.class.getInterfaces(),helloHandler);
        hello.morning("bob");
    }
}
