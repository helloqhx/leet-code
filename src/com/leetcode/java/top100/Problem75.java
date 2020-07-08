package com.leetcode.java.top100;

import com.leetcode.java.Utils;

public class Problem75 {

    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len <= 1) return;

        int i = 0, j = len - 1;
        while (i < len && nums[i] == 0) i ++;
        while (j >= 0 && nums[j] == 2) j --;

        i --;
        j ++;
        if (i >= j - 1) return;
        int k = i + 1;
        while (k < j) {
            if (nums[k] == 0) {
                i ++;
                Utils.swap(nums, i, k);
                while (nums[i] == 0) i ++;
                i --;
                k = i + 1;
            } else if (nums[k] == 2) {
                j --;
                Utils.swap(nums, j, k);
                while (nums[j] == 2) j --;
                j ++;
            } else {
                k ++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,1,2,2,2,2};
        new Problem75().sortColors(nums);
        Utils.printArray(nums);
    }

}
