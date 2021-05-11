package com.jaclon.spring5.javademo;

import java.util.Observable;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2021/4/20 17:04
 */
public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();
        observerDemo.addObserver((o,arg)-> {
            System.out.println("changed");
        });
        observerDemo.addObserver((o,arg)->{
            System.out.println("手动被观察者通知，准备改变");
        });
        observerDemo.setChanged();
        observerDemo.notifyObservers();
    }
}
