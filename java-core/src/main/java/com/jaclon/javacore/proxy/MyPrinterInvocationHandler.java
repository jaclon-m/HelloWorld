package com.jaclon.javacore.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jaclon
 * @date 2020/9/17
 */
public class MyPrinterInvocationHandler implements InvocationHandler {

    private Object object;

    public MyPrinterInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before print");
        method.invoke(object,args);
        return proxy;
    }
}
