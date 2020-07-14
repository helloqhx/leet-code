package com.leetcode.java.top200;

public class Problem198 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (0 == len) return 0;

        int[] dp1 = new int[len + 1];
        int[] dp2 = new int[len + 1];
        dp1[0] = 0;
        dp2[0] = 0;
        dp1[1] = nums[0];
        dp2[1] = 0;

        for (int i = 2; i <= len; i ++) {
            dp1[i] = nums[i - 1] + Math.max(dp1[i - 2], dp2[i - 2]);
            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]);
        }

        return Math.max(dp1[len], dp2[len]);
    }
}
