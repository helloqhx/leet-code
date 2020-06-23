package com.leetcode.java;

public class Problem27 {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        if (0 == len) return 0;
        int i = -1, j = 0;
        while (j < len) {
            if (nums[j] != val) {
                nums[++i] = nums[j];
            }
            j ++;
        }

        return i + 1;
    }
}
