/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jason.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Classname TestBigDecimal
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/3/19
 */
public class TestBigDecimal {

    public static void main(String[] args) {
        test3();
    }
    public static void test3(){
        BigDecimal d = new BigDecimal(5L);
        d.add(new BigDecimal(3L));
        System.out.println("========" + d.toString());
    }

    public static  void test1(){
        BigDecimal bigDecimal = new BigDecimal(1111);
        Double v = bigDecimal.doubleValue();
        int i = v.toString().indexOf(".");
        System.out.println(v.toString().length() -i-1);
        System.out.println(v.toString());

    }

    public static  void test2(){
        double   c=3.154215;

        DecimalFormat myformat=new DecimalFormat("0.00");
        String str = myformat.format(c);
        System.out.println(str);
    }
}
