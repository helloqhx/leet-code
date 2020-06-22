package com.leetcode.java;

import java.util.Arrays;

public class Problem16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int maxSum = 0, lastAbs = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len - 2; i ++) {

            int minAbs = Integer.MAX_VALUE;
            int targetSum = 0;
            int j = i + 1, k = len - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int diff = sum - target;
                int abs = Math.abs(diff);
                if (abs < minAbs) {
                    minAbs = abs;
                    targetSum = sum;
                }
                if (diff < 0) j ++;
                else if (diff == 0) return target;
                else k --;
            }

            if (minAbs > lastAbs && targetSum > maxSum) {
                break;
            } else {
                if (minAbs < lastAbs) {
                    lastAbs = minAbs;
                    maxSum = targetSum;
                }
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(new Problem16().threeSumClosest(new int[] {-1,2,1,-4}, -2));
    }
}
