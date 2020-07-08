package com.leetcode.java.top100;

public class Problem79 {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        if (0 == row) return false;
        int col = board[0].length;
        if (0 == col) return false;

        return exist0(board, word);
    }

    private boolean exist0(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] used = new boolean[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (board[i][j] == word.charAt(0)) {
                    used[i][j] = true;
                    if (check(board, word, i, j, used, 1)) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }

        return false;
    }

    private boolean check(char[][] board, String word, int i, int j, boolean[][] used, int wordIndex) {
        if (wordIndex >= word.length()) return true;
        int row = board.length, col = board[0].length;

        // 检查上面的节点
        if (i > 0 && checkNext(board, word, i - 1, j, used, wordIndex)) {
            return true;
        }

        // 下
        if (i < row - 1 && checkNext(board, word, i + 1, j, used, wordIndex)) {
            return true;
        }

        // 左
        if (j > 0 && checkNext(board, word, i, j - 1, used, wordIndex)) {
            return true;
        }

        // 右
        if (j < col - 1 && checkNext(board, word, i, j + 1, used, wordIndex)) {
            return true;
        }

        return false;
    }

    private boolean checkNext(char[][] board, String word, int nextI, int nextJ, boolean[][] used, int wordIndex) {
        if (!used[nextI][nextJ] && board[nextI][nextJ] == word.charAt(wordIndex)) {
            used[nextI][nextJ] = true;
            if (check(board, word, nextI, nextJ, used, wordIndex + 1)) {
                return true;
            }
            used[nextI][nextJ] = false;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Problem79().exist(new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
//                {'a', 'a'}
        }, "aaa"));
    }
}
