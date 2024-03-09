package com.jason.java;

/**
 * 变量赋值
 * 只有值传递，没有引用传递
 * @author jaclon
 * @since 2021/9/16 11:42
 */
public class TestVariable {
    public static void main(String[] args) {
        Person person = new Person();
        var(person);
        System.out.println("addr1:" + person);
    }

    public static void var(Person p){
        System.out.println("addr0:" + p);
       Person a = new Person();
       p = a;
        System.out.println("addr:" + p);
    }
}
