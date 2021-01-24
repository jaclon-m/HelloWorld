package com.jaclon.javacore.json;

import com.alibaba.fastjson.JSON;
import com.jaclon.javacore.oop.Person;

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
    }
}
