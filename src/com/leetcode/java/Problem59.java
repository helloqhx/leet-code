package com.leetcode.java;

public class Problem59 {
    public int[][] generateMatrix(int n) {
        int max = n * n;
        int [][] result = new int[n][n];

        int i = 0, j = 0, num = 0;
        int k = 0, depth = n >> 1;
        while (k < depth) {

            for (; j < n - k; j ++) {
                result[i][j] = ++num;
            }

            for (--j, ++i; i < n - k; i ++) {
                result[i][j] = ++num;
            }

            for (--i, --j; j >= k; j --) {
                result[i][j] = ++num;
            }

            for (++j, --i; i > k; i --) {
                result[i][j] = ++num;
            }

            i ++;
            j ++;
            k ++;
        }

        if (num < max) {
            result[depth][depth] = max;
        }

        return result;
    }
}
