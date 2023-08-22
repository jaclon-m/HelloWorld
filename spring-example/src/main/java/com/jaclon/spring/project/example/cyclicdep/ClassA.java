package com.jaclon.spring.project.example.cyclicdep;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2023/7/18 17:22
 */
public class ClassA {
    private ClassB classB;

    public ClassB getClassB() {
        return classB;
    }

    public void setClassB(ClassB classB) {
        System.out.println("creating classB");
        this.classB = classB;
    }
}