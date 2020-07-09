/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.java8.instance;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Classname UniqueWithJ8
 * @Description java8去重
 *
 * @author jaclon
 * @date 2019/12/23
 */
public class UniqueWithJ8 {

    public static void main(String[] args) {
        test1();
    }

    public static void  test1(){
        /*List<User> list = new ArrayList<>(8);
        list.add(new User("zs", 16));
        list.add(new User("lisi", 8));
        list.add(new User("wangwu", 12));
        list.add(new User("zs", 14));
        List<User> collect = list.stream().distinct().collect(Collectors.toList());
        //list.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::getName)),ArrayList::new));
        Map<String, User> collect1 = list.stream().collect(Collectors.toMap(User::getName, Function.identity(), (a1, a2) -> a1.getAge().compareTo(a2.getAge()) > 0 ? a1 : a2));
        System.out.println(collect1);*/

    }
}
