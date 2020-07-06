package com.leetcode.java;

public class Problem81 {
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return false;

        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int numLeft = nums[left], numMid = nums[mid], numRight = nums[right];

            if (numMid == target) return true;

            if (numMid > numRight) {
                if (target >= numLeft && target < numMid) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (numMid == numRight) {
                right --;
            } else {
                if (target > numMid && target <= numRight) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return nums[left] == target;
    }

    public static void main(String[] args) {
//        System.out.println(new Problem81().search(new int[]{3, 1, 1}, 3));
//        System.out.println(new Problem81().search(new int[]{3, 1, 1}, 2));
//        System.out.println(new Problem81().search(new int[]{3, 1, 1}, 1));
//        System.out.println(new Problem81().search(new int[]{3, 1, 1}, 4));
//        System.out.println(new Problem81().search(new int[]{3, 1, 1}, 0));
//        System.out.println(new Problem81().search(new int[]{1, 3, 1,1,1}, 3));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 0));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 1));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 2));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 3));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 4));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 5));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 6));
//        System.out.println(new Problem81().search(new int[]{2,5,6,0,0,1,2}, 7));
        System.out.println(new Problem81().search(new int[]{4,5,6,7,0,1,2}, 1));
    }
}
