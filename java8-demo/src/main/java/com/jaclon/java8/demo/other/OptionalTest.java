package com.jaclon.java8.demo.other;


import com.jaclon.java8.demo.base.Employee;

import java.util.Optional;

public class OptionalTest {

    public void test3(){
        Optional<Employee> op = Optional.of(new Employee(101, "张三", 18, 9999.99));

        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());

        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

    public void test2(){
        Optional<Employee> op = Optional.ofNullable(new Employee());

        if(op.isPresent()){
            System.out.println(op.get());
        }

        Employee emp = op.orElse(new Employee("张三"));
        System.out.println(emp);

        Employee emp2 = op.orElseGet(() -> new Employee());
        System.out.println(emp2);
    }

    public void  test1(){
        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());
       /* Optional<Employee> op1 = Optional.empty();
        System.out.println(op1.get());*/
        Optional<Employee> op2 = Optional.ofNullable(null);
        System.out.println(op2.get());
    }
}
