/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.javacore.base;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
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
       test5();

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
