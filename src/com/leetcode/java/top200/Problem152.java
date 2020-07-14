package com.leetcode.java.top200;

public class Problem152 {

    /**
     * dp
     * dp[i]以第i个元素结尾的最大连续子数组的乘积
     *
     * */
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];

        int[] maxDp = new int[len], minDp = new int[len];
        maxDp[0] = nums[0];
        minDp[0] = nums[0];

        int max = maxDp[0];
        for (int i = 1; i < len; i ++) {
            int num = nums[i];
            maxDp[i] = Math.max(num, Math.max(num * maxDp[i - 1], num * minDp[i - 1]));
            minDp[i] = Math.min(num, Math.min(num * maxDp[i - 1], num * minDp[i - 1]));

            max = Math.max(max, maxDp[i]);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Problem152().maxProduct(new int[] {2,-1,1,1}));
    }
}
