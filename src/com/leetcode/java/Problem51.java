package com.leetcode.java;

import java.util.ArrayList;
import java.util.List;

public class Problem51 {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                board[i][j] = '.';
            }
        }

        List<List<String>> resultList = new ArrayList<>();
        solve(board, n, 0, resultList);

        return resultList;
    }

    private void solve(char[][] board, int n, int row, List<List<String>> resultList) {
        for (int i = 0; i < n; i ++) {
            if (check(board, n, row, i)) {
                board[row][i] = 'Q';
                if (row == n - 1) {
                    addToList(board, n, resultList);
                } else {
                    solve(board, n, row + 1, resultList);
                }
                board[row][i] = '.';
            }
        }
    }

    private void addToList(char[][] board, int n, List<List<String>> resultList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i ++) {
            list.add(new String(board[i]));
        }

        resultList.add(list);
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
        System.out.println(new Problem51().solveNQueens(4));
    }
}
