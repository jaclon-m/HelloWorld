package com.jason.java.java8.Time; /**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */


import org.joda.time.Days;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname JodaTimeTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2020/2/21
 */
public class JodaTimeTest {

    public static void main(String[] args) {
        //LocalDateTime parse = LocalDateTime.parse("2020-02-12 00:00:00");
        /*LocalDateTime now = LocalDateTime.now();
        LocalDateTime minusWeeks = now.minusWeeks(1);
        System.out.println(now);
        System.out.println(minusWeeks);
        System.out.println(now);*/
//        Date date = new Date();
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));


        test3();

    }

    public static  void  test(){
        LocalDateTime localDateTime = new LocalDateTime(2020,3,07,
                11,13,2);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
        Days days = Days.daysBetween(localDateTime, dateTime);
        System.out.println(days.getDays());
    }

    public static void test1(){
        LocalDateTime localDateTime = new LocalDateTime(2020,3,07,
                11,13,2);

    }

    public static void test2(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.toString("yyyy-MM-dd HH"));

        List<String> list = new ArrayList<>(12);
        list.add("sfs");
        list.add(null);
        System.out.println(list.size() + " ===" + list.get(1));
    }

    public static void test3(){
        LocalDateTime time = LocalDateTime.parse("2020-02-16T12");


        System.out.println(time.toString());
    }

}
