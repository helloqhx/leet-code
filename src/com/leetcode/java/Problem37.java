package com.leetcode.java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Problem37 {

    public void solveSudoku(char[][] board) {
        int[][] stack = new int[81][2];
        LinkedList<Character>[] availNum = new LinkedList[81];

        int cnt = 0;
        boolean backtrace = false;
        int i = 0, j = 0;
        while (i < 9) {
            i = 0;
            while (j < 9) {
                char c = board[i][j];
                if (c == '.') {
                    LinkedList<Character> availNums = getAvailNums(board, i, j);
                    if (availNums.size() == 0) {
                        int[] lastStep = stack[cnt - 1];
                        i = lastStep[0];
                        j = lastStep[1];
                        cnt --;
                        backtrace = true;
                    } else {
                        char nextC = availNums.get(0);
                        availNums.remove(0);
                        board[i][j] = nextC;
                        stack[cnt] = new int[]{i, j};
                        availNum[cnt] = availNums;

                        j ++;
                        cnt ++;
                        backtrace = false;
                    }
                } else {
                    if (backtrace) {
                        LinkedList<Character> availNums = availNum[cnt];
                        if (availNums.size() == 0) {
                            board[i][j] = '.';
                            int[] lastStep = stack[cnt - 1];
                            i = lastStep[0];
                            j = lastStep[1];
                            cnt --;
                            backtrace = true;
                        } else {
                            char nextC = availNums.get(0);
                            availNums.remove(0);
                            board[i][j] = nextC;
                            j ++;
                            cnt ++;
                            backtrace = false;
                        }
                    } else {
                        j ++;
                        backtrace = false;
                    }
                }
            }
            i ++;
        }
    }


    private LinkedList<Character> getAvailNums(char[][] board, int i, int j) {
        Set<Character> set = new HashSet<>(9);

        for (int k = 0; k < 9; k ++) {
            char c = board[i][k];
            if ('.' != c) {
                set.add(c);
            }
        }

        for (int k = 0; k < 9; k ++) {
            char c = board[k][j];
            if ('.' != c) {
                set.add(c);
            }
        }

        i = i / 3 * 3;
        j = j / 3 * 3;
        for (int m = i; m < i + 3; m ++) {
            for (int n = j; n < j + 3; n ++) {
                char c = board[m][n];
                if ('.' != c) {
                    set.add(c);
                }
            }
        }

        LinkedList<Character> list = new LinkedList<>();
        for (char k = '1'; k <= '9'; k ++) {
            if (!set.contains(k)) {
                list.add(k);
            }
        }

        return list;
    }
}
