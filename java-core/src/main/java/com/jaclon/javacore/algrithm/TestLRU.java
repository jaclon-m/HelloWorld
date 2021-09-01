package com.jaclon.javacore.algrithm;


import java.util.concurrent.ConcurrentHashMap;

public class TestLRU {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 );
        cache.put(1,1);
        cache.put(2,2);
        // 返回  1
        System.out.println(cache.get(1));
        // 该操作会使得密钥 2 作废
        cache.put(3,3);
        // 返回 -1 (未找到)
        System.out.println( cache.get(2));
        // 该操作会使得密钥 1 作废
        cache.put(4,4);
        // 返回 -1 (未找到)
        System.out.println(cache.get(1));
        // 返回  3
        System.out.println(cache.get(3));
        // 返回4
        System.out.println(cache.get(4));
    }
}


class LRUCache {

    private Node head;
    private Node tail;
    private ConcurrentHashMap<Integer,Node> cache = new ConcurrentHashMap<>();
    private int size;
    private int count;

    public LRUCache(int capacity) {
        size = capacity;
        count = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return  -1;

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if(node == null){
            Node newNode = new Node(key,value);
            cache.put(key,newNode);
            addNode(newNode);
            ++count;
            if(count >size){
                tail = popTail();
                cache.remove(tail.key);
                --count;
            }
        }else {
            node.value = value;
            moveToHead(node);
        }

    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node pre = tail.pre;
        removeNode(pre);
        return pre;

    }

    private void removeNode(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
    }

    public void addNode(Node node){
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }
}

class Node{
    int key;
    int value;
    Node pre;
    Node next;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}