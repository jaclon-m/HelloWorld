/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java.reflectandproxy;

import com.jason.java.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Classname Test
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/4/18
 */
public class Test {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class clazz = Person.class;
        Method[] methods = clazz.getDeclaredMethods();
        Person person = new Person();
        for (int i = 0; i < methods.length; i++) {
            if(methods[i].getName().contains("get")){
                Object invoke = methods[i].invoke(person);
                System.out.println(" ===========" + invoke);
            }

        }
    }
}
