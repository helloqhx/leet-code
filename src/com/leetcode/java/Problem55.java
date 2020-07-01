package com.leetcode.java;

public class Problem55 {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (0 == len) return true;
        if (nums[0] == 0) return len == 1;

        int maxPos = 0;
        int i = 0;
        while (i < len - 1) {
            int distance = nums[i];
            if (distance == 0) {
                if (maxPos <= i) return false;
            } else {
                maxPos = Math.max(maxPos, i + distance);
                if (maxPos >= len - 1) return true;
            }
            i ++;
        }

        return maxPos >= len - 1;
    }

    public boolean canJump1(int[] nums) {
        int len = nums.length;
        if (0 == len) return true;
        if (nums[0] == 0) return len == 1;

        for (int i = 0; i < len - 1; i ++) {
            if (nums[i] == 0) {
                int j = 0;
                for (; j < i; j ++) {
                    if (nums[j] + j > i) break;
                }
                if (j >= i) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Problem55().canJump1(new int[]{2, 0, 0}));
        System.out.println(new Problem55().canJump(new int[]{2, 0, 0}));
    }
}
