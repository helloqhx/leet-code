package com.leetcode.java;

public class Problem1666 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (0 == k) return new int[0];
        if (shorter == longer) {
            return new int[]{k * shorter};
        } else {
            int[] result = new int[k + 1];
            for (int i = 0; i <= k; i ++) {
                result[i] = i * longer + (k - i) * shorter;
            }

            return result;
        }
    }
}
