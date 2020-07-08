package com.leetcode.java.top100;

public class Problem97 {
    public boolean isInterleave2(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 + l2 != l3) return false;

        boolean[] dp = new boolean[l2 + 1];

        for (int i = 0; i <= l1; i ++) {
            for (int j = 0; j <= l2; j ++) {

                if (i == 0 && j == 0) dp[j] = true;
                else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
                } else if (j == 0){
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i - 1);
                } else {
                    dp[j] = s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j] || s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1];
                }
            }
        }

        return dp[l2];
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 + l2 != l3) return false;

        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= l1; i ++) {
            dp[i][0] = isEqual(s1, s3, i);
        }
        for (int i = 1; i <= l2; i ++) {
            dp[0][i] = isEqual(s2, s3, i);
        }

        for (int i = 1; i <= l1; i ++) {
            for (int j = 1; j <= l2; j ++) {

                int k = i + j;
                dp[i][j] = s1.charAt(i - 1) == s3.charAt(k - 1) && dp[i - 1][j] || s2.charAt(j - 1) == s3.charAt(k - 1) && dp[i][j - 1];
            }
        }

        return dp[l1][l2];
    }

    private boolean isEqual(String text1, String text2, int len) {
        for (int i = 0; i < len; i ++) {
            if (text1.charAt(i) != text2.charAt(i)) return false;
        }

        return true;
    }

    public boolean isInterleave1(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l1 + l2 != l3) return false;

        return sub(s1, s2, s3, 0, 0, 0);
    }

    private boolean sub(String s1, String s2, String s3, int i, int j, int k) {
        if (k == s3.length()) return true;
        char c = s3.charAt(k);

        return (i < s1.length() && s1.charAt(i) == c && sub(s1, s2, s3, i + 1, j, k + 1))
                || (j < s2.length() && s2.charAt(j) == c && sub(s1, s2, s3, i, j + 1, k + 1));
    }

    public static void main(String[] args) {
        System.out.println(new Problem97().isInterleave2("aabcc", "dbbca", "aadbbcbcac"));
    }
}
