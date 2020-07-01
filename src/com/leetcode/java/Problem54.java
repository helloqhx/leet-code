package com.leetcode.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        if (0 == row) return Collections.emptyList();
        int col = matrix[0].length;
        if (0 == col) return Collections.emptyList();

        int len = row * col;
        List<Integer> resultList = new ArrayList<>(len);
        int k = 0, circle = Math.min(row, col) / 2;
        int i = 0, j = 0;
        while (k < circle) {
            // 左->右
            for (; j < col - k; j ++) {
                resultList.add(matrix[i][j]);
            }
            j --;
            i ++;
            // 上->下
            for (; i < row - k; i ++) {
                resultList.add(matrix[i][j]);
            }
            i --;
            j --;
            // 右->左
            for (; j >= k; j --) {
                resultList.add(matrix[i][j]);
            }
            j ++;
            i --;
            // 下->上
            for (; i > k; i --) {
                resultList.add(matrix[i][j]);
            }
            i ++;

            k ++;
            j ++;
        }

        if (resultList.size() < len) {
            if (row < col) {
                // 左->右
                for (; j < col - k; j ++) {
                    resultList.add(matrix[i][j]);
                }
            } else {
                // 上->下
                for (; i < row - k; i ++) {
                    resultList.add(matrix[i][j]);
                }
            }
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(new Problem54().spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12},
                {13, 14, 15}
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}
        }));
    }
}
