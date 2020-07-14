package com.leetcode.java.top200;

public class Problem136 {

    public int singleNumber(int[] nums) {
        int result = nums[0], len = nums.length;

        for (int i = 1; i < len; i ++) {
            result = result ^ nums[i];
        }

        return result;
    }
}
