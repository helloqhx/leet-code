package com.leetcode.java.top300;

public class Problem215 {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        if (0 == len) return -1;

        return subFind(nums, 0, len - 1, k);
    }

    private int subFind(int[] nums, int from, int end, int k) {
        if (from == end) return nums[from];

        int pIndex = (end - from) / 2 + from;
        int pivot = nums[pIndex];

        swap(nums, pIndex, end);
        int i = from - 1, j = from;
        while (j < end) {
            if (nums[j] < pivot) {
                swap(nums, ++i, j);
            }
            j ++;
        }
        swap(nums, i + 1, end);

        int len = end - i;
        if (len > k) {
            return subFind(nums, i + 2, end, k);
        } else if (len == k) {
            return pivot;
        } else {
            return subFind(nums, from, i, k - len);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Problem215().findKthLargest(new int[]{2, 1}, 1));
        System.out.println(new Problem215().findKthLargest(new int[]{3,2,1,5,6,4}, 2));
        System.out.println(new Problem215().findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

}
