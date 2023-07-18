package com.jaclon.javacore.algrithm;

import java.util.*;

/**
 * TODO Description
 *
 * @author jaclon
 * @since 2022/11/29 09:08
 */
public class Sorts {

    public static void bubbleSort(int[] a, int n){
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        TreeSet<Long> ts = new TreeSet<>(Comparator.reverseOrder());
        for (int i = n; i >0; i--) {
            boolean flag = false;
            for (int j = 0; j < i - 1; j++) {
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void main(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] query = new int[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(query[1]);
        queue.offer(query[2]);
    }
}
