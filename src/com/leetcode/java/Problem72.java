package com.leetcode.java;

public class Problem72 {

    /**
     * dp
     * dp[i][j]: word1[:i] 变换到word2[:j]的最小距离
     * dp[i][j] =
     * if word1[i] == word2[j]: Math.min(dp[i - 1][j - 1], dp[i - 1][j] + 1, dp[i][j - 1] + 1)
     * else Math.min(dp[i - 1 ][j], dp[i - 1][j - 1], dp[i][j - 1]) + 1
     * */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        if (0 == len2) return len1;
        if (0 == len1) return len2;
        int[][] dp = new int[len1 + 1][len2 + 1];

        dp[0][0] = 0;
        for (int i = 1; i <= len1; i ++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= len2; i ++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= len1; i ++) {
            for (int j = 1; j <= len2; j ++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        //"distance"
        //"springbok"
        System.out.println(new Problem72().minDistance("distance", "springbok"));
    }
}
