package com.leetcode.java;

public class Problem96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        if (n < 2) return 1;
        dp[2] = 2;

        for (int k = 3; k <= n; k ++) {
            int cnt = 0;
            for (int i = 0; i < k; i ++) {
                cnt += dp[i] * dp[k - 1 - i];
            }
            dp[k] = cnt;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Problem96().numTrees(3));
    }
}
