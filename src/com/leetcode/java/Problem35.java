package com.leetcode.java;

public class Problem35 {

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return 0;

        return subSearch(nums, 0, len - 1, target);
    }

    public int subSearch(int[] nums, int from, int end, int target) {
        int numFrom = nums[from], numEnd = nums[end];
        if (from == end) {
            if (numFrom < target) return from + 1;
            else return from;
        }

        if (from + 1 == end) {
            if (target > numEnd) return end + 1;
            else if (target > numFrom) return end;
            else return from;
        }
        int mid = (end - from) / 2 + from;
        int numMid = nums[mid];
        if (target <= numMid) {
            return subSearch(nums, from, mid, target);
        } else {
            return subSearch(nums, mid + 1, end, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem35().searchInsert(new int[]{1,3,5,6}, 7));
    }
}
