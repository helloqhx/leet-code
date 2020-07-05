package com.leetcode.java;

public class Problem1143 {
    /**
     * dp
     * dp[i][j] 表示 text1[:i] 和 text2[:j] 的最长公共子序列的长度
     *
     * dp[i][j] =
     *
     * */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i ++) {
            dp[i][0] = 0;
        }

        for (int i = 0; i <= len2; i ++) {
            dp[0][i] = 0;
        }

        for(int i = 1; i <= len1; i ++) {
            for (int j = 1; j <= len2; j ++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, Math.max(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(new Problem1143().longestCommonSubsequence("abc", "def"));
    }
}
