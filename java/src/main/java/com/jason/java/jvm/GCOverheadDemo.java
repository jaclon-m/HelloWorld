package com.jason.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jaclon
 * @date 2019/9/11
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList <>();
        int i = 0;

        try {
            while (true){
                list.add(String.valueOf(i++).intern());
            }
        } catch (Throwable e) {
            System.out.println("=========================================" + i);
            e.printStackTrace();
            throw e;
        }

    }
}
