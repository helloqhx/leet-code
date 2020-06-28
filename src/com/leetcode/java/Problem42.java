package com.leetcode.java;

public class Problem42 {
    public int trap(int[] height) {
        int len = height.length;
        if (len == 0) return 0;
        int[] stack = new int[len];
        int top = -1;

        stack[++top] = 0;
        int index = 1, sum = 0;
        while (index < len) {
            int h = height[index];

            int topIndex = stack[top];
            int topH = height[topIndex];

            if (h > topH) {
                while (true) {
                    topIndex = stack[top];
                    topH = height[topIndex];
                    top --;

                    if (top < 0) break;

                    int from = stack[top];
                    int fromH = height[from];
                    int minH = Math.min(fromH, h);

                    sum += (minH - topH) * (index - from - 1);
                    if (fromH > h) break;
                }
            }
            stack[++ top] = index;
            index ++;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Problem42().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
