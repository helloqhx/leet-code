package com.leetcode.java.top100;

public class Problem88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        while (i >= 0) {
            nums1[i + n] = nums1[i];
            i --;
        }

        i = n;
        int nextPos = 0, j = 0;
        while (nextPos < m + n) {
            if ((i < m + n && j < n && nums1[i] < nums2[j]) || j >= n) {
                // use nums1
                nums1[nextPos] = nums1[i];
                i ++;
            } else {
                // use nums2
                nums1[nextPos] = nums2[j];
                j ++;
            }
            nextPos ++;
        }
    }
}
