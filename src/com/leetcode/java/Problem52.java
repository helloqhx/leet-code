package com.leetcode.java;

public class Problem52 {
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                board[i][j] = '.';
            }
        }

        return solve(board, n, 0);
    }

    private int solve(char[][] board, int n, int row) {
        int count = 0;
        for (int i = 0; i < n; i ++) {
            if (check(board, n, row, i)) {
                board[row][i] = 'Q';
                if (row == n - 1) {
                    count ++;
                } else {
                    count += solve(board, n, row + 1);
                }
                board[row][i] = '.';
            }
        }

        return count;
    }

    private boolean check(char[][] board, int n, int i, int j) {
        for (int k = 0; k < n; k ++) {
            if (k == i) continue;
            if (board[k][j] == 'Q') return false;
        }

        for (int k = 0; k < n; k ++) {
            if (k == j) continue;
            if (board[i][k] == 'Q') return false;
        }

        int cnt = 1;
        while (i + cnt < n && j + cnt < n) {
            if (board[i + cnt][j + cnt] == 'Q') return false;
            cnt ++;
        }
        cnt = 1;
        while (i - cnt >= 0 && j - cnt >= 0) {
            if (board[i - cnt][j- cnt] == 'Q') return false;
            cnt ++;
        }
        cnt = 1;
        while (i - cnt >= 0 && j + cnt < n) {
            if (board[i - cnt][j + cnt] == 'Q') return false;
            cnt ++;
        }
        cnt = 1;
        while (i + cnt < n && j - cnt >= 0) {
            if (board[i + cnt][j - cnt] == 'Q') return false;
            cnt ++;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Problem52().totalNQueens(5));
    }
}
