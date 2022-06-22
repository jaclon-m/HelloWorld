/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.base;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname StringTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/12/12
 */
public class StringTest {

    public static void main(String[] args) {
       /* String str = "123sdfd";
        String s = StringUtils.replace(str,"1","A");
        System.out.println(str+ "========" + s);
        //test3();*/
        System.out.println("==========");
        test11();
    }


    public static void test11(){
        try {
            //String dateTime = "2022-06-22T02:38:25.713778Z";
            String dateTime = "2022-06-22T02:38:25";
            //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formatter.parse(dateTime);
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(formatter2.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public static void test10(){
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(format);
        String format2 = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        System.out.println(format2);
    }
    public static String test9(){
        String startDate = "2022-03-15 10:55:01";
        String endDate = "2022-03-15 10:53:56";
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = df.parse(endDate);
            Date before = df.parse(startDate);

            long l = now.getTime() - before.getTime();
            long day = l/(24*60*60*1000);
            long hour = (l/(60*60*1000) - day*24);
            long min = ((l/(60*1000)) - day*24*60 - hour*60);
            long s = (l/1000 - day*24*60*60 - hour*60*60 - min*60);

            String shour = hour < 10 ? "0" + hour : hour + "";
            String smin = min < 10 ? "0" + min : min + "";
            String ss = s < 10 ? "0" + s : s + "";

            return day < 1 ? shour + ":" + smin + ":" + ss : day + " days " + shour + ":" + smin + ":" + ss;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void test8(){
        String s = StringUtils.joinWith(",", 1, 3, 6);
        System.out.println("======" + s);
    }
    public static void test7(){
        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append("dealId").append("_").append(str).append("_").append("12121");
        System.out.println(sb);
    }
    public static void test6(){
        String a = "";
        String b = "";
        System.out.println(a.equals(b));
    }
    public static void test5(){
        String str = "hello";
        String str2 = "hello";
        System.out.println(str == str2);
    }

    public void test4(){
        String ids = "1,2,3";
        String[] split = StringUtils.split(ids, ",");
        List<String> list = Arrays.asList(split);
    }


    public static void test3(){
        String str = "sfsdfsdf111";
        String s = StringUtils.remove(str, "s");
        System.out.println(str+ "============" + s);
    }
    public static void  test1(){
        String str1 = "2019-12-11T00:00:08.000Z";
        String str2 = "2019-12-11T00:03:16.000Z";
        System.out.println(str1.compareTo(str2));
    }

    public static void test2(){
        String lineToHump = lineToHump("f_parent_no_leader");
        System.out.println(lineToHump);// fParentNoLeader
        System.out.println(humpToLine(lineToHump));// f_parent_no_leader
        System.out.println(humpToLine2(lineToHump));// f_parent_no_leader
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /** 下划线转驼峰 */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /** 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)}) */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /** 驼峰转下划线,效率比上面高 */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
