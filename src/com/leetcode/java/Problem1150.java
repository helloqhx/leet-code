package com.leetcode.java;

public class Problem1150 {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length, half = len / 2;
        int t = nums[0], counter = 1;
        for (int i = 1; i < len; i ++) {
            int num = nums[i];
            if (num == t) {
                counter ++;
                if (counter > half) {
                    return target == t;
                }
            } else {
                t = num;
                counter = 1;
            }
        }

        return false;
    }
}
