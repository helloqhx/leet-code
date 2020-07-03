package com.leetcode.java;

public class Problem66 {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        boolean hasCarry = true;
        for (int i = len - 1; i >= 0; i --) {
            int sum = digits[i] + (hasCarry ? 1 : 0);
            hasCarry = sum > 10;
            digits[i] = sum % 10;

            if (!hasCarry) break;
        }

        if (hasCarry) {
            int[] result = new int[len + 1];
            System.arraycopy(digits, 0, result, 1, len);
            result[0] = 1;
            return result;
        } else {
            return digits;
        }
    }
}
