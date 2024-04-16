package com.jason.java.reflectandproxy;

/**
 * @author jaclon
 * @date 2020/9/17
 */
public class Printer implements IPrinter {

    @Override
    public void myPrint(String name) {
        System.out.println(" Printer: My name is" + name);
    }
}
