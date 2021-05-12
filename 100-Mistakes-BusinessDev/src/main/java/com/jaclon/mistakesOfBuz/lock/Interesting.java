package com.jaclon.mistakesOfBuz.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

/**
 * 静态成员变量在类对象之间共享
 *
 * @author jaclon
 * @since 2021/5/12 09:32
 */
@Slf4j
public class Interesting {
    static volatile int a = 1;
    static volatile int b = 1;

    public static void main(String[] args) {
        Interesting test = new Interesting();
        Interesting test2 = new Interesting();
        new Thread(()-> test.add()).start();
//        new Thread(()->test.compare()).start();
        new Thread(()->test2.compareRight()).start();
    }
    public synchronized void add() {
        log.info("add start");
        for (int i = 0; i < 1000000; i++) {
            a++;
            b++;
        }
        log.info("add done");
    }

    public void compare() {
        log.info("compare start");
        for (int i = 0; i < 1000000; i++) {
            if (a < b) {
                log.info("a:{},b:{},{}", a, b, a > b);
                //最后的a>b应该始终是false的吗？
            }
        }
        log.info("compare done");
    }

    public synchronized void compareRight() {
        log.info("compare start");
        for (int i = 0; i < 1000000; i++) {
//            Assert.assertTrue(a == b);
            if (a < b) {
                log.info("a:{},b:{},{}", a, b, a > b);
            }
        }
        log.info("compare done");
    }
}
