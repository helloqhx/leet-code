package com.leetcode.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem315 {


    /**
     * 总体思想：使用归并排序，在排序过程中统计小的个数
     * */
    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        if (0 == len) return Collections.emptyList();
        Node[] nodes = new Node[len];
        List<Integer> resultList = new ArrayList<>(len);
        for (int i = 0; i < len; i ++) {
            nodes[i] = new Node(i, nums[i]);
            resultList.add(0);
        }

        partitionAndMerge(nodes, 0, len - 1, resultList);

        return resultList;
    }

    private void partitionAndMerge(Node[] nodes, int from, int end, List<Integer> resultList) {
        if (from == end) return;

        int mid = (from + end) >> 1;
        partitionAndMerge(nodes, from, mid, resultList);
        partitionAndMerge(nodes, mid + 1, end, resultList);

        // 归并并计数
        Node[] tmp = new Node[end - from + 1];
        int i = from, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            if (nodes[i].num <= nodes[j].num) {
                int index = nodes[i].index;
                int count = resultList.get(index);
                count += j - mid - 1;
                resultList.set(index, count);
                tmp[k] = nodes[i];
                i ++;
            } else {
                tmp[k] = nodes[j];
                j ++;
            }
            k ++;
        }

        while (i <= mid) {
            int index = nodes[i].index;
            int count = resultList.get(index);
            count += j - mid - 1;
            resultList.set(index, count);

            tmp[k] = nodes[i];
            i ++;
            k ++;
        }

        while (j <= end) {
            tmp[k] = nodes[j];
            j ++;
            k ++;
        }

        for (i = from; i <= end; i ++) {
            nodes[i] = tmp[i - from];
        }
    }

    private static class Node {
        // 在数组中原来的位置
        int index;
        // 数字本身
        int num;

        Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem315().countSmaller(new int[]{5,2,6,1}));
    }
}
