package com.leetcode.java;


/**
 * 最小堆实现
 * 堆是一个完全二叉树
 * 使用数组即可实现，其中 pos(left) = pos(i) * 2 + 1, pos(right) = pos(i) * 2 + 2
 * */
public class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap(int size) {
        heap = new int[size];
    }

    public MinHeap(int[] initArray) {
        heap = initArray;
    }

    /**
     * 最小堆化：将所有非叶子节点下沉
     * */
    public void minHeapify() {
        for (int i = (size >>> 1) - 1; i >= 0; i --) {
            down(i);
        }
    }

    public void offer(int value) {
        heap[size - 1] = value;
        up(size - 1);
    }

    public void poll() {
        int value = heap[0];
        heap[0] = heap[size - 1];
        down(0);
    }

    /**
     * 下沉某个节点
     * */
    private void down(int i) {
        int len = size >>> 1;
        int value = heap[i];
        while (i < len) {
            int l = i >> 1 + 1, r = l + 1;
            if (l >= len) break;
            int min = l;
            if (r < len && heap[r] < heap[l]) min = r;

            if (heap[min] >= heap[i]) break;

            value = heap[min];
            heap[min] = heap[i];
            i = min;
        }
        heap[i] = value;
    }

    /**
     * 上浮某个节点
     * */
    private void up(int i) {
        int p = (i - 1) >> 2;
        int value = heap[i];
        while (i > 0 && heap[i] < heap[p]) {
            value = heap[p];
            heap[p] = heap[i];
            i = p;
        }

        heap[i] = value;
    }
}
