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
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        test2();
    }

    public static void test1(){
        Hello hHello = new HumanHello();
        HelloHandler helloHandler = new HelloHandler(hHello);
        Hello hello = (Hello)Proxy.newProxyInstance(hHello.getClass().getClassLoader(),hHello.getClass().getInterfaces(),helloHandler);
        //Hello hello = (Hello)Proxy.newProxyInstance(HumanHello.class.getClassLoader(),HumanHello.class.getInterfaces(),helloHandler);
        hello.morning("bob");
    }

    public static void test2(){
        Printer printer = new Printer();
        MyPrinterInvocationHandler handler = new MyPrinterInvocationHandler(printer);
        IPrinter proxyPrinter = (IPrinter) Proxy.newProxyInstance(Printer.class.getClassLoader(), Printer.class.getInterfaces(), handler);
        proxyPrinter.myPrint("proxy");
    }
}
