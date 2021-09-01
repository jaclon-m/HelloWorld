package com.jaclon.java8.demo.lambda;

import java.util.TreeSet;

public class LambdaTest {
    public static void main(String[] args) {
        /*System.out.println("HelloWorld");
        ClassLoader classLoader = HelloWorld.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }*/

        Runnable r1 = ()-> System.out.println("this is lambda,wow!");
        Thread thread = new Thread(r1);
        thread.start();

       /* TreeSet<String> ts = new TreeSet <>(new Comparator <String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(),o1.length());
            }
        });*/

        TreeSet<String> ts = new TreeSet <>((o1, o2)->Integer.compare(o1.length(),o2.length()));

        ts.add("sfsdfs");
        ts.add("qqq");
        for(String s :ts){
            System.out.println(s);
        }


        String newStr =  toUpperCaseString((str)->str.toUpperCase(),"aba");
        System.out.println(newStr);

    }

    public static String toUpperCaseString(MyFunc<String> mf, String str){
        return mf.getValue(str);
    }
}
