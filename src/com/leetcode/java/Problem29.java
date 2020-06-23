package com.leetcode.java;

public class Problem29 {
    public int divide(int dividend, int divisor) {
        boolean isPositive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
        long result = subDiv(Math.abs((long) dividend), Math.abs((long) divisor));

        if (result == Math.abs((long) Integer.MIN_VALUE) && !isPositive) return Integer.MIN_VALUE;
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;

        int rs = (int) result;
        return isPositive ? rs : -rs;
    }

    private long subDiv(long dividend, long divisor) {
        if (dividend < divisor) return 0;
        if (dividend == divisor) return 1;
        long result = 1, lastResult = 1;
        long num = divisor, lastNum = num;
        while (num <= dividend) {
            lastResult = result;
            lastNum = num;
            num += num;
            result += result;
        }

        result -= lastResult;
        dividend -= lastNum;

        return result + subDiv(dividend, divisor);
    }

    public static void main(String[] args) {
        System.out.println(new Problem29().divide(Integer.MIN_VALUE, 1));
    }
}
