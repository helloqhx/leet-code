package com.leetcode.java.top200;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new Node(0, -1);
        this.tail = new Node(0, -1);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (null == node) return -1;

        // 在该位置删除该节点
        remove(node);

        // 把该节点放到头
        insertToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (null == node) {
            // 插入新节点
            if (map.size() >= capacity) {
                // 删除末尾节点
                Node tailNode = tail.prev;
                remove(tailNode);
                map.remove(tailNode.key);
            }

            node = new Node(key, value);
            insertToHead(node);
            map.put(key, node);
        } else {
            // 覆盖旧的数据
            node.value = value;

            // 在该位置删除该节点
            remove(node);

            // 把该节点放到头
            insertToHead(node);
        }
    }

    public void print() {
        Node node = head;
        while (node != null) {
            System.out.print("[" + node.key + ":" + node.value + "]");
            node = node.next;
        }
        System.out.println();
    }

    // 把某个节点从队中删除
    private void remove(Node node) {
        Node prevNode = node.prev, nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // 添加到队头
    private void insertToHead(Node node) {
        Node nextNode = head.next;

        node.next = nextNode;
        node.prev = head;

        nextNode.prev = node;
        head.next = node;
    }

    private static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.print();

        System.out.println(cache.get(1));
        cache.print();
        cache.put(3, 3);
        cache.print();
        System.out.println(cache.get(2));
        cache.put(4, 4);
        cache.print();
        System.out.println(cache.get(1));
        cache.print();
        System.out.println(cache.get(3));
        cache.print();
        System.out.println(cache.get(4));
        cache.print();
    }
}
