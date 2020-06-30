package com.leetcode.java;

public class Problem50 {
    public double myPow(double x, int n) {
        long m = n;
        if (m < 0) {
            return 1d / subPow(x, -m);
        }

        return subPow(x, m);
    }

    private double subPow(double x, long n) {
        double sum = 1.0, curV = x;
        while (n > 0) {
            long r = n % 2;
            if (r == 1) {
                sum *= curV;
            }
            n = n >> 1;
            curV = curV * curV;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Problem50().myPow(2.00000, -2));
    }
}
