package com.leetcode.java.top200;

public class Problem121 {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i ++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(new Problem121().maxProfit(new int[] {7,1,5,3,6,4}));
    }
}
