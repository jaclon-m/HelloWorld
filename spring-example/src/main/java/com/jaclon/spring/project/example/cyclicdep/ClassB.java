package com.jaclon.spring.project.example.cyclicdep;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/7/18 17:23
 */
public class ClassB {
    private ClassA classA;

    public ClassA getClassA() {
        return classA;
    }

    public void setClassA(ClassA classA) {
        System.out.println("creating classA");
        this.classA = classA;
    }
}