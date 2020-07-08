package com.leetcode.java.top100;

public class Problem36 {
    public boolean isValidSudoku(char[][] board) {

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                char c = board[i][j];
                if (c != '.') {
                    if (!isValid(board, c, i, j)) return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, char target, int i, int j) {
        int k = i + 1;
        while (k < 9) {
            char c = board[k][j];
            if (c == target) return false;
            k ++;
        }

        k = j + 1;
        while (k < 9) {
            char c = board[i][k];
            if (c == target) return false;
            k ++;
        }

        int minI = i / 3 * 3;
        int minJ = j / 3 * 3;

        for (int m = minI; m < minI + 3; m ++) {
            for (int n = minJ; n < minJ + 3; n ++) {
                if (m == i && n == j) continue;
                char c = board[m][n];
                if (c == target) return false;
            }
        }

        return true;
    }

}
