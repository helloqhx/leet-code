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
        if (x == 0) return 0;
        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(xi - x0) < 1e-7) break;

            x0 = xi;
        }

        return (int)Math.floor(x0);
    }

    public static void main(String[] args) {
        System.out.println(new Problem69().mySqrt1(2147483647));
    }
}
