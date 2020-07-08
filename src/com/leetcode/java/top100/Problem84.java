package com.leetcode.java.top100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem84 {

    /**
     * 总体思想：遍历高度，对于每一个高度，计算以它为最低高度能得到的最大面积，并记录最左和最右的index
     * */
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (0 == len) return 0;

        int[] leftIndex = new int[len], rightIndex = new int[len];

        // 初始化第一个的左右边界
        leftIndex[0] = 0;
        int i = 1;
        while (i < len && heights[i] >= heights[0]) i ++;
        rightIndex[0] = i - 1;

        int maxArea = heights[0] * (rightIndex[0] - leftIndex[0] + 1);

        for (i = 1; i < len; i ++) {
            int prevH = heights[i - 1], curH = heights[i];

            // 向左寻找最左能达到的边界
            int leftMin = i;
            if (prevH >= curH) {
                leftMin = leftIndex[i - 1] - 1;
                while (leftMin >= 0 && heights[leftMin] >= curH) leftMin --;
                leftMin ++;
            }
            leftIndex[i] = leftMin;

            // 向右寻找最大能达到的边界
            int rightMax = i;
            if (prevH >= curH) {
                rightMax = Math.max(rightIndex[i - 1], i) + 1;
                while (rightMax <= len - 1 && heights[rightMax] >= curH) rightMax ++;
                rightMax --;
            } else {
                rightMax = i + 1;
                while (rightMax < rightIndex[i - 1] + 1 && heights[rightMax] >= curH) rightMax ++;
                rightMax --;
            }
            rightIndex[i] = rightMax;

            System.out.println((rightMax - leftMin + 1) * curH);
            maxArea = Math.max(maxArea, (rightMax - leftMin + 1) * curH);
        }

        return maxArea;
    }

    /**
     * 单调栈：对于每一个高度，寻找其左边界和右边界
     * */
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        if (0 == len) return 0;
        int[] left = new int[len], right = new int[len];

        Deque<Integer> stack = new ArrayDeque<>();
        left[0] = 0;
        stack.push(0);
        for (int i = 1; i < len; i ++) {
            int curH = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= curH) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
            stack.push(i);
        }

        stack.clear();
        right[len - 1] = len - 1;
        stack.push(len - 1);
        for (int i = len - 2; i >= 0; i --) {
            int curH = heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= curH) {
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
        System.out.println(new Problem84().largestRectangleArea1(new int[] {2,1,5,6,2,3}));
    }
}
