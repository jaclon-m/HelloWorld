package com.jaclon.concurrent.temp;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author jaclon
 * @date 2019/9/11
 */
public class CollectionNotSafeDemo {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        //List<String> list =  Collections.synchronizedList(new ArrayList <>());
        //List<String> list = new CopyOnWriteArrayList <>();

        for (int i = 0 ; i < 3 ; i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        System.out.println(list.size());
    }
}
