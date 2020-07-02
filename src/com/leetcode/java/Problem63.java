package com.leetcode.java;

public class Problem63 {

    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0], right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = (right + left) >> 1;
            int cnt = count(matrix, n, mid);

            if (cnt >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 获取小于等于max的元素个数
     * 从左下角向右上搜索
     * */
    private int count(int[][] matrix, int n, int max) {
        int cnt = 0;

        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            int num = matrix[i][j];
            if (num <= max) {
                j ++;
                cnt += i + 1;
            } else {
                i --;
            }
        }

        return cnt;
    }
}
