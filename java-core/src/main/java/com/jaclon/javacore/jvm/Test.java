/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.jvm;
/**
 * @Classname Test
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/7/27
 */
public class Test {

    int a, b;

    volatile int v, u;


    void f() {

        int i, j;


        i = a;// load a

        j = b;// load b

        i = v;// load v

        // LoadLoad

        j = u;// load u

        // LoadStore

        a = i;// store a

        b = j;// store b

        // StoreStore

        v = i;// store v

        // StoreStore

        u = j;// store u

        // StoreLoad

        i = u;// load u

        // LoadLoad

        // LoadStore

        j = b;// load b

        a = i;// store a
    }
}
