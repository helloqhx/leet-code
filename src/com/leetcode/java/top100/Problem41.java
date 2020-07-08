package com.leetcode.java.top100;

public class Problem41 {
    public int firstMissingPositive(int[] nums) {
        int max = nums.length;
        if (max == 0) return 1;
        int i = 0;
        while (i < max) {
            int num = nums[i];

            if (num < 1 || num > max) {
                nums[i] = -1;
                i ++;
            } else if (num == i + 1) {
                i++;
            } else {
                int tmp = nums[num - 1];
                if (tmp == num) {
                    nums[num - 1] = -1;
                    i ++;
                    continue;
                }
                nums[num - 1] = num;
                nums[i] = tmp;
            }
        }

        i = 0;
        for (; i < max; i ++) {
            if (nums[i] == -1) return i + 1;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Problem41().firstMissingPositive(new int[]{1, 1}));
        System.out.println(new Problem41().firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(new Problem41().firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(new Problem41().firstMissingPositive(new int[]{7,8,9,11,12}));
    }

}
