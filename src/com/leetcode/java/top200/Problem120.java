package com.leetcode.java.top200;

import java.util.List;

public class Problem120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        int size = triangle.size();
        if (size == 0) return 0;

        int[] dp = new int[size + 1];
        dp[1] = triangle.get(0).get(0);

        for (int i = 2; i <= size; i ++) {
            List<Integer> nums = triangle.get(i - 1);
            int len = nums.size();

            dp[len] = dp[len - 1] + nums.get(len - 1);
            for (int j = len - 1; j > 1; j --) {
                int num = nums.get(j - 1);
                dp[j] = Math.min(dp[j - 1], dp[j]) + num;
            }

            dp[1] = nums.get(0) + dp[1];

        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= size; i ++) {
            min = Math.min(min, dp[i]);
        }

        return min;
    }
}
