package com.leetcode.java.top100;

public class Problem73 {

    public void setZeroes(int[][] matrix) {
        int row = matrix.length, col = matrix[0].length;

        boolean firstRowHasZero = false, firstColHasZero = false;
        for (int i = 0; i < row; i ++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        for (int j = 0; j < col; j ++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        for (int i = 1; i < row; i ++) {
            for (int j = 1; j < col; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < row; i ++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < col; j ++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < col; j ++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < row; i ++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstColHasZero) {
            for (int i = 0; i < row; i ++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRowHasZero) {
            for (int j = 0; j < col; j ++) {
                matrix[0][j] = 0;
            }
        }
    }
}
