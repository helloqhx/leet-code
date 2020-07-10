package com.leetcode.java;

public class Problem309 {

    /**
     * dp
     * dp[i] 表示以i结尾的最大利润
     * dp[i] =
     * 1. 如果第i天为冷冻期 = dp[i - 1]
     * 2. 如果第i天为卖出的那天，则寻找最大的买入的天的，假设为j, max (dp[j - 2] + prices[i] - prices[j])
     * */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[] dp = new int[len + 2];

        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 0;
        dp[3] = Math.max(0, prices[1] - prices[0]);

        for (int i = 4; i <= len + 1; i ++) {

            // 当天不交易
            int maxP = dp[i - 1];

            // 当天交易卖出，从头开始查找最大可能的利润
            for (int j = 2; j < i; j ++) {
                // 第j天买入
                maxP = Math.max(maxP, dp[j - 2] + prices[i - 2] - prices[j - 2]);
            }
            dp[i] = maxP;
        }

        return dp[len + 1];
    }

    /**
     * dp
     * dp1[i] 在i当前天手上还有股票时的最大收益
     * dp2[i] 在i当天卖出股票时的最大收益
     * dp3[i] 在i当天没有操作，并且手上没有股票时的最大收益，即处于冷冻期
     *
     * dp1[i] = max(dp1[i - 1], dp2[i - 1] - prices[i])
     * dp2[i] = dp1[i - 1] + prices[i]
     * dp3[i] = max(dp3[i - 1], dp2[i])
     *
     * 每一次的计算都只和前面一次的有关，所以只需要用一个变量即可，不需要一个数组
     * */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;

        int dp1 = -prices[0], dp2 = 0, dp3 = 0;

        for (int i = 1; i < len; i ++) {
            int tp1 = dp1, tp2 = dp2;
            dp1 = Math.max(dp1, dp3 - prices[i]);
            dp2 = tp1 + prices[i];
            dp3 = Math.max(dp3, tp2);
        }

        return Math.max(dp2, dp3);
    }

    public static void main(String[] args) {
        System.out.println(new Problem309().maxProfit1(new int[]{1,2,3,0,2}));
    }
}
