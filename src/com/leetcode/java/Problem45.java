package com.leetcode.java;

/**
 *
 * */
public class Problem45 {

    /**
     * dp
     * 单调递增 if (i < j) -> step[i] <= step[j]
     * */
    public int jump(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int[] step = new int[len];
        for (int i = 0; i < len; i ++) {
            step[i] = i;
        }

        for (int i = 1; i < len; i ++) {
            for (int j = 0; j < i; j ++) {
                if (j + nums[j] >= i) {
                    // 因为递增性，从小到大找到第一个能到达的就可以了
                    step[i] = Math.min(step[i], step[j] + 1);
                    break;
                }
            }
        }

        return step[len - 1];
    }

    /**
     * 贪心
     * 从左向右遍历，寻找从当前位置可以跳到的最大位置，并更新
     * 遍历时，如果到达上次能够跳到的最大位置，则更新步数
     * 其实就是，step是按照最终跳跃到终点的最优路径更新的
     * */
    public int jumpByGreedy(int[] nums) {
        // 总步数
        int steps = 0;

        // 当前可以跳到的最大位置
        int maxPos = 0;

        // 跳到上次最大位置的节点位置
        int nextPos = 0;
        for (int i = 0; i < nums.length - 1; i ++) {
            // 试图更新当前可以到达的最大位置
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == nextPos) {
                nextPos = maxPos;
                steps ++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        System.out.println(new Problem45().jumpByGreedy(new int[]{2,3,1,1,4}));
        System.out.println(new Problem45().jumpByGreedy(new int[]{1,2,1,1,1}));
    }
}
