package com.leetcode.java;

import java.util.*;

public class Problem37 {

    public void solveSudoku(char[][] board) {
        int[][] stack = new int[81][2];
        LinkedList<Character>[] availNum = new LinkedList[81];

        int cnt = 0;
        boolean backtrace = false;
        int i = 0, j = 0;
        while (i < 9) {
            j = 0;
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


    public void solveSudokuByRecurse(char[][] board) {
        subSolve(board, 0, 0);
    }

    private boolean subSolve(char[][] board, int row, int col) {
        char c = board[row][col];
        if (c == '.') {
            boolean solved = false;
            for (char i = '1'; i <= '9'; i ++) {
                if (isValid(board, row, col, i)) {
                    board[row][col] = i;
                    if (solveNext(board, row, col)) {
                        solved = true;
                        break;
                    }
                }
            }

            if (!solved) {
                board[row][col] = '.';
                return false;
            } else {
                return true;
            }
        } else {
            return solveNext(board, row, col);
        }
    }

    private boolean solveNext(char[][] board, int row, int col) {
        if (col < 8) return subSolve(board, row, col + 1);
        else if (row < 8) return subSolve(board, row + 1, 0);
        else return true;
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

    private boolean isValid(char[][] board, int i, int j, char tc) {

        for (int k = 0; k < 9; k ++) {
            char c = board[i][k];
            if (tc == c) {
                return false;
            }
        }

        for (int k = 0; k < 9; k ++) {
            char c = board[k][j];
            if (tc == c) {
                return false;
            }
        }

        i = i / 3 * 3;
        j = j / 3 * 3;
        for (int m = i; m < i + 3; m ++) {
            for (int n = j; n < j + 3; n ++) {
                char c = board[m][n];
                if (tc == c) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        new Problem37().solveSudokuByRecurse(board);
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                System.out.print(board[i][j] +  " ");
            }
            System.out.print("\n");
        }
    }
}
