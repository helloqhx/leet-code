package com.leetcode.java;

public class Problem69 {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;

        int left = 1, right = x;
        while (left < right - 1) {
            int mid = (int)(((long)left + right) >> 1);
            if ((long)mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 牛顿法
     * */
    public int mySqrt1(int x) {
        
    }

    public static void main(String[] args) {
        System.out.println(new Problem69().mySqrt(2147483647));
    }
}
