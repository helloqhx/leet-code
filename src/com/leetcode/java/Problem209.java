package com.leetcode.java;

import java.util.Arrays;
import java.util.Comparator;

public class Problem209 {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        int curSum = 0, curLen = 0, minLen = Integer.MAX_VALUE;

        for (int i = 0; i < len; i ++) {
            int num = nums[i];

            curSum += num;
            curLen += 1;

            if (curSum >= s) {
                if (curLen < minLen) {
                    minLen = curLen;
                    if (minLen == 1) return 1;
                }
                int j = i - curLen;
                while (curSum >= s) {
                    j ++;
                    curLen --;
                    curSum -= nums[j];
                }

                if (curLen + 1 < minLen) {
                    minLen = curLen + 1;
                    if (minLen == 1) return 1;
                }
            }
        }

        if (minLen == Integer.MAX_VALUE) return 0;

        return minLen;
    }

    public int minSubArrayLenByBinarySearch(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;

        // 计算从0到i节点的和
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i ++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i <= len - 1; i ++) {
            int index = Arrays.binarySearch(sums, i, len, sums[i] + s - nums[i]);
            if (index < 0) {
                index = -index - 1;
                if (index < len && (index - i + 1) < minLen) {
                    minLen = index - i + 1;
                }
            } else {
                if (index - i  + 1 < minLen) {
                    minLen = index - i + 1;
                }
            }
        }

        if (minLen == Integer.MAX_VALUE) return 0;

        return minLen;
    }

    public static void main(String[] args) {
        System.out.println(new Problem209().minSubArrayLenByBinarySearch(11, new int[] {1,2,3,4,5}));
//        System.out.println(new Problem209().minSubArrayLenByBinarySearch(7, new int[] {2,3,1,2,4,3}));
//        System.out.println(new Problem209().minSubArrayLenByBinarySearch(7, new int[] {2, 3, 1}));
//        System.out.println(new Problem209().minSubArrayLenByBinarySearch(7, new int[] {2, 3, 1, 2, 4, 3, 7}));
//        System.out.println(new Problem209().minSubArrayLenByBinarySearch(7, new int[] {7, 4, 3, 1, 2, 4, 3}));
    }
}
