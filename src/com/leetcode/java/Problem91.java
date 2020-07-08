package com.leetcode.java;

public class Problem91 {
    public int numDecodings(String s) {
        int len = s.length();
        if (0 == len) return 0;

        int[] dp = new int[len + 1];
        dp[0] = 0;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        if (len == 1) return dp[1];
        dp[2] = (s.charAt(1) != '0' ? dp[1] : 0) + (s.charAt(0) == '1' || (s.charAt(0) == '2' && (s.charAt(1) >= '0' && s.charAt(1) <= '6')) ? 1 : 0) ;

        for (int i = 3; i <= len; i ++) {
            char c = s.charAt(i - 1);
            int count = c != '0' ? dp[i - 1] : 0;
            if (dp[i - 2] > 0 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && c >= '0' && c <= '6'))) {
                count += dp[i - 2];
            }

            dp[i] = count;
        }

        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(new Problem91().numDecodings("226"));
    }
}
