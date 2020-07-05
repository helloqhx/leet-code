package com.leetcode.java;

public class Problem74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        if (row == 0) return false;
        int col = matrix[0].length;
        if (col == 0) return false;


        // 先找到行
        int left = 0, right = row - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int midNum = matrix[mid][col - 1];
            if (target > midNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);
        int targetRow = left;
        left = 0;
        right = col - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            int midNum = matrix[targetRow][mid];

            if (target > midNum) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(left);

        return matrix[targetRow][left] == target;
    }

    public static void main(String[] args) {
        System.out.println(new Problem74().searchMatrix(new int[][] {
//                {1,   3,  5,  7},
//                {10, 11, 16, 20},
//                {23, 30, 34, 50}
                {1, 2}
        }, 2));
    }
}
