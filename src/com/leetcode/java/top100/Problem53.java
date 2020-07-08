package com.leetcode.java.top100;

public class Problem53 {

    /**
     * dp
     * dp[i] 表示以i结尾的最大子数组和，遍历到结束，更新最大值即可
     * dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] ? nums[i]
     * */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (0 == len) return 0;

        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i ++) {
            int num = nums[i];
            dp[i] =  dp[i - 1] > 0 ? dp[i - 1] + num : num;
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public int maxSubArrayByDiv(int[] nums) {
        return subSearch(nums, 0, nums.length - 1).max;
    }

    private Result subSearch(int[] nums, int l, int r) {
        if (l == r) return new Result(nums[l], nums[r], nums[l], nums[r]);

        int mid = (l + r) >> 1;
        Result leftResult = subSearch(nums, l, mid), rightResult = subSearch(nums, mid + 1, r);

        return new Result(
                Math.max(leftResult.lSum, leftResult.sum + rightResult.lSum),
                Math.max(rightResult.rSum, rightResult.sum + leftResult.rSum),
                leftResult.sum + rightResult.sum,
                Math.max(Math.max(leftResult.max, rightResult.max), leftResult.rSum + rightResult.lSum)
        );
    }


    private static class Result {
        // 包含左端点的最大和
        int lSum;
        // 包含右端点的最大和
        int rSum;
        // 区间总和
        int sum;
        // 区间最大子序列和
        int max;

        Result(int lSum, int rSum, int sum, int max) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.sum = sum;
            this.max = max;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Problem53().maxSubArray(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(new Problem53().maxSubArrayByDiv(new int[] {-2,1,-3,4,-1,2,1,-5,4}));
    }

}
