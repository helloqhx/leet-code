package com.leetcode.java;

public class Problem31 {

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len < 2) return;
        if (len == 2) {
            swap(nums, 0, 1);
            return;
        }
        int i = len - 2;
        while (i >= 0) {
            int num = nums[i], right = nums[i + 1];
            if (num < right) {
                int j = i + 1;
                while (j < len && num < nums[j]) j ++;
                swap(nums, i, j - 1);
                reverse(nums, i + 1);

                return;
            }
            i --;
        }
        reverse(nums, 0);
    }

    private void reverse(int[] nums, int fromIndex) {
        int len = nums.length;
        int i = fromIndex, j = len - 1;
        while (i < j) {
            swap(nums, i, j);
            i ++;
            j --;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1};
        new Problem31().nextPermutation(nums);
        for (int i = 0; i < nums.length; i ++) {
            System.out.print(nums[i] + " ");
        }
    }
}
