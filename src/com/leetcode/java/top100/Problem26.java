package com.leetcode.java.top100;

public class Problem26 {

    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return 1;

        int i = 0, j = 1;
        int lastNum = nums[i];
        while (j < len) {
            int num = nums[j];
            if (num != lastNum) {
                lastNum = num;
                nums[++i] = num;
            }
            j ++;
        }

        return i;
    }
}
