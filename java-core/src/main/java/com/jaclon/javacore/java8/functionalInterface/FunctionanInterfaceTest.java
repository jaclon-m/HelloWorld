package com.jaclon.javacore.java8.functionalInterface;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionanInterfaceTest {



    public List<Integer> getNumList(int num, Supplier<Integer>supplier){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i<num;i++){
            Integer n = supplier.get();
            list.add(n);
        }
        return list;
    }
    public void test4(){
        List<Integer> list = getNumList(10,()->(int)(Math.random()*100));
        for(Integer n:list){
            System.out.println(n);
        }
    }

    public  void happy(Double money, Consumer<Double> con){
        con.accept(money);

    }
     
    public void test3(){
        happy(1000.00,m-> System.out.println("李老师喜欢大保健,每次消耗" + m + "元"));

    }
    public static String toUpperCaseString(MyFunc<String> mf,String str){
        return mf.getValue(str);
    }

    public void test2(){
        String newStr = toUpperCaseString(str->str.toUpperCase(),"abccc");
        System.out.println(newStr);
    }

    public void test1(){
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);
        eval(list,n->true);

    }

    public void eval(List<Integer> list, Predicate<Integer>predicate){
        for(Integer n:list){
            if(predicate.test(n)){
                System.out.print(n + " ");
            }
        }
    }
}

