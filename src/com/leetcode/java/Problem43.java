package com.leetcode.java;

import java.util.ArrayList;
import java.util.List;

public class Problem43 {
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        if (num1.equals("0") || num2.equals("0")) return "0";

        List<String> resultList = new ArrayList<>(len1);
        int maxLen = 0;
        for (int i = len1 - 1; i >= 0; i --) {
            int n1 = num1.charAt(i) - '0';
            int carry = 0;
            StringBuilder sb = new StringBuilder();
            int k = i;
            while (k ++ < len1 - 1) sb.append('0');
            for (int j = len2 - 1; j >= 0; j --) {
                int n2 = num2.charAt(j) - '0';
                int sum = n1 * n2 + carry;

                carry = sum / 10;
                int r = sum % 10;
                sb.append(r);
            }
            if (carry > 0) sb.append(carry);
            String result = sb.toString();
            if (result.length() > maxLen) {
                maxLen = result.length();
            }
            resultList.add(result);
        }

        StringBuilder sb = new StringBuilder();

//        System.out.println(resultList);
        int k = 0, carry = 0;
        while (k < maxLen) {
            for (int i = 0; i < len1; i ++) {
                String text = resultList.get(i);
                if (k > text.length() - 1) continue;

                carry += text.charAt(k) - '0';
            }

            int num = carry % 10;
            carry = carry / 10;
            sb.append(num);
            k ++;
        }

        String result = sb.reverse().toString();
        if (result.isEmpty()) return "0";
        int i = 0;
        while (result.charAt(i) == '0') i ++;

        return result.substring(i);
    }

    public static void main(String[] args) {
        System.out.println(new Problem43().multiply("2", "3"));
        System.out.println(new Problem43().multiply("123", "456"));
        System.out.println(new Problem43().multiply("408", "5"));
    }
}
