package com.jason.java.jvm;

import java.util.Random;

/**
 * @author jaclon
 * @date 2019/9/10
 */
public class G1Demo {
    public static void main(String[] args) {
        String str = "jaclon";
        while (true){
            str += str + new Random().nextInt(7777777) + new Random().nextInt(8888888);
            str.intern();
        }
    }
}
