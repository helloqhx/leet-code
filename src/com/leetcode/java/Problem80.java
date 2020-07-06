package com.leetcode.java;

public class Problem80 {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len <= 2) return len;

        int lastNum = nums[0], lastCount = 1;
        int writeIndex = 0;
        for (int i = 1; i < len; i ++) {
            int num = nums[i];
            if (num == lastNum) {
                lastCount ++;
                if (lastCount <= 2) {
                    nums[++writeIndex] = num;
                }
            } else {
                lastNum = num;
                lastCount = 1;
                nums[++writeIndex] = num;
            }
        }

        return writeIndex + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Problem80().removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }
}
