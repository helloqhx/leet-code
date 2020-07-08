package com.leetcode.java.top100;

public class Problem67 {

    public String addBinary(String a, String b) {
        int lenA = a.length(), lenB = b.length();

        int max = Math.max(lenA, lenB);
        boolean hasCarry = false;
        char[] resultList = new char[max + 1];
        int i = lenA - 1, j = lenB - 1, k = max;
        while (i >= 0 || j >= 0) {
            char ac = i >= 0 ? a.charAt(i) : '0', bc = j >= 0 ? b.charAt(j) : '0';
            char resultC;
            if ('1' == ac && '1' == bc) {
                resultC = hasCarry ? '1' : '0';
                hasCarry = true;
            } else if ('0' == ac && '0' == bc) {
                resultC = hasCarry ? '1' : '0';
                hasCarry = false;
            } else {
                resultC = hasCarry ? '0': '1';
            }

            resultList[k] = resultC;
            j --;
            i --;
            k --;
        }

        if (hasCarry) {
            resultList[0] = '1';
            return new String(resultList);
        } else {
            return new String(resultList, 1, resultList.length - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem67().addBinary("11", "1"));
        System.out.println(new Problem67().addBinary("1010", "1011"));
    }
}
