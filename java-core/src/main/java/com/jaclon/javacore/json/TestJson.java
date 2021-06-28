package com.jaclon.javacore.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jaclon.javacore.Test;
import com.jaclon.javacore.oop.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Description
 * @author jaclon
 * @since 2021/1/14 下午3:37
 */
public class TestJson {

    public static void main(String[] args) {
        Person person = new Person();
        person.setAge(12);
        person.setName("zss");
        System.out.println(JSON.toJSONString(person));
        test();
    }

    public static void test(){
        List<String> list  = new ArrayList<>(4);
        list.add("11");
        list.add("12");
        list.add("1ss");
        System.out.println(JSONArray.toJSONString(list));
    }
}
