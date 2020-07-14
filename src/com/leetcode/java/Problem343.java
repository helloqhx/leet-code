package com.leetcode.java;

public class Problem343 {


    /**
     * 数学：将n尽可能等分为3时能达到最大值
     * */
    public int integerBreak2(int n) {
        if (n <= 2) return 1;
        if (n == 3) return 2;

        int a = n / 3, b = n - 3 * a;
        if (b == 0) return (int)Math.pow(3, a);
        else if (b == 1) return ((int)Math.pow(3, a - 1) * 2 * 2);  // 将一个3和1拆分为两个2
        else return (int)Math.pow(3, a) * 2;
    }

    /**
     * dp
     * dp[i] = max(dp[i- 1] * 1, dp[i - 2] * 2, dp[i - 3]  * 3 ...)
     * */
    public int integerBreak1(int n) {
        if (n <= 2) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i ++) {
            int max = 1;
            for (int j = 1; j <= i - 1; j ++) {
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }

        return dp[n];
    }

    /**
     * 递归
     * */
    public int integerBreak(int n) {
        int maxNum = 1;
        Integer[][] memo = new Integer[n + 1][n];
        for (int i = 2; i < n; i ++) {
            maxNum = Math.max(maxNum, intBreak(n, i, memo));
        }

        return maxNum;
    }

    /**
     *  将数字n拆成k段
     * */
    private int intBreak(int n, int k, Integer[][] memo) {
        if (k == 1) return n;
        if (null != memo[n][k]) return memo[n][k];

        int maxNum = 1;
        for (int i = 1; i <= n / k; i ++) {
            maxNum = Math.max(maxNum, i * intBreak(n - i, k - 1, memo));
        }

        memo[n][k] = maxNum;

        return maxNum;
    }

    public static void  main(String[] args) {
        System.out.println(new Problem343().integerBreak2(40));
    }
}
