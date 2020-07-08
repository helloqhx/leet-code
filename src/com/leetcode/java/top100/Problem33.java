package com.leetcode.java.top100;

public class Problem33 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;

        return subSearch(nums, 0, len - 1, target);
    }

    private int subSearch(int[] nums, int from, int end, int target) {
        if (end == from) return nums[from] == target ? from : -1;

        int mid = from + (end - from) / 2;
        int numFrom = nums[from], numMid = nums[mid], numMid1 = nums[mid + 1], numEnd = nums[end];

        if ((numFrom <= target && target <= numMid) ||
                (numFrom >= numMid && (target < numMid1 || target > numEnd))) {
            return subSearch(nums, from, mid, target);
        } else {
            return subSearch(nums, mid + 1, end, target);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem33().search(new int[] {4,5,6,7,8,1,2,3}, 8));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 4));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 5));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 6));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 7));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 0));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 1));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 2));
        System.out.println(new Problem33().search(new int[] {4,5,6,7,0,1,2}, 3));
        System.out.println(new Problem33().search(new int[] {5, 1, 3}, 1));
        System.out.println(new Problem33().search(new int[] {5, 1, 3}, 5));
        System.out.println(new Problem33().search(new int[] {5, 1, 3}, 3));
    }
}
