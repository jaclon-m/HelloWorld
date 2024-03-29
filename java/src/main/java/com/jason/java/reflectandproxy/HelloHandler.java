/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.reflectandproxy;

import org.omg.CORBA.portable.InvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Classname HelloHandler
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/6/22
 */
public class HelloHandler implements InvocationHandler {
    private Object object;

    public HelloHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("beofore HelloHanler");
        method.invoke(object,args);
        return null;
    }
}
