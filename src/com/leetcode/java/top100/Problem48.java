package com.leetcode.java.top100;

public class Problem48 {
    /**
     * (x, y) -> (y, n - 1 - x)
     * */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return;

        int max = n / 2;
        int k = 0;
        while (k < max) {
            int i = k, j = k;
            while (j < n - 1 - k) {
                int cnt = 0;
                int lastNum = matrix[i][j], nextNum;
                while (cnt < 4) {
                    int nextI = j, nextJ = n - 1 - i;
                    nextNum = matrix[nextI][nextJ];
                    matrix[nextI][nextJ] = lastNum;

                    lastNum = nextNum;

                    i = nextI;
                    j = nextJ;
                    cnt ++;
                }
                j ++;
            }
            k ++;
        }
    }
}
