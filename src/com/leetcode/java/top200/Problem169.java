package com.leetcode.java.top200;

public class Problem169 {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        if (1 == len) return nums[0];

        int target = nums[0], counter = 1;
        for (int i = 1; i < len; i ++) {
            if (nums[i] == target) {
                counter ++;
            } else {
                counter --;
                if (0 == counter) {
                    target = nums[i];
                    counter = 1;
                }
            }
        }

        return target;
    }
}
