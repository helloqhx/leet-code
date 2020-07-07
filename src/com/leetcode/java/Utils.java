package com.leetcode.java;

public class Utils {

    public static void printArray(int[] nums) {
        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
