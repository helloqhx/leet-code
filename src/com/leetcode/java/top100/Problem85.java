package com.leetcode.java.top100;

import com.leetcode.java.Utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Problem85 {

    /**
     * 动态规划：对于每个点，维护三个变量，left height right
     * 对于每一行可以根据上一行的来计算
     * */
    public int maximalRectangle1(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        if (col == 0) return 0;

        int[] left = new int[col], height = new int[col], right = new int[col];
        Arrays.fill(right, col - 1);

        int maxArea = 0;
        for (int i = 0; i < row; i ++) {
            // 计算height
            for (int j = 0; j < col; j ++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }

            // 计算left
            int curLeft = 0;
            for (int j = 0; j < col; j ++) {
                if (matrix[i][j] == '0') {
                    left[j] = 0;
                    curLeft = j + 1;
                } else {
                    left[j] = Math.max(curLeft, left[j]);
                }
            }

            int curRight = col - 1;
            for (int j = col - 1; j >= 0; j --) {
                if (matrix[i][j] == '0') {
                    right[j] = col - 1;
                    curRight = j - 1;
                } else {
                    right[j] = Math.min(curRight, right[j]);
                }
            }

            for (int j = 0; j < col; j ++) {
                maxArea = Math.max(maxArea, (right[j] - left[j] + 1) * height[j]);
            }
        }

        return maxArea;
    }

    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) return 0;
        int col = matrix[0].length;
        if (col == 0) return 0;

        int max = 0;
        int[] heights = new int[col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                if (matrix[i][j] == '0') heights[j] = 0;
                else heights[j] = heights[j] + 1;
            }

            Utils.printArray(heights);
            max = Math.max(max, maxArea(heights));
        }

        return max;
    }

    /**
     * 参考Problem84
     * 单调栈
     * */
    private int maxArea(int[] heights) {
        int len = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();

        int[] left = new int[len], right = new int[len];

        stack.push(0);
        left[0] = 0;
        for (int i = 1; i < len; i ++) {
            int h = heights[i];
            if (h == 0) {
                left[i] = i;
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && heights[stack.peek()] >= h) {
                stack.pop();
            }

            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        stack.clear();
        right[len - 1] = len - 1;
        stack.push(len - 1);
        for (int i = len - 2; i >=0; i --) {
            int h = heights[i];
            if (h == 0) {
                right[i] = i;
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && heights[stack.peek()] >= h) {
                stack.pop();
            }

            right[i] = stack.isEmpty() ? len - 1 : stack.peek() - 1;
            stack.push(i);
        }

        int maxArea = 0;
        for (int i = 0; i < len; i ++) {
            maxArea = Math.max(maxArea, (right[i] - left[i] + 1) * heights[i]);
        }

        return maxArea;
    }
    
    public static void main(String[] args) {
        Problem85 p = new Problem85();
        System.out.println(p.maximalRectangle1(new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}
