package com.leetcode.java.top100;

class Problem10 {


    // 递归
    public boolean isMatchByRecurse(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        int lenS = s.length(), lenP = p.length();
        Boolean[][] memo = new Boolean[lenS + 1][lenP + 1];

        return subMatch(s, 0, p, 0, memo);
    }

    private boolean subMatch(String s, int sIndex, String p, int pIndex, Boolean[][] memo) {
        if (null != memo[sIndex][pIndex]) return memo[sIndex][pIndex];

        boolean result;
        if (p.isEmpty()) result = s.isEmpty();
        else {
            boolean matched = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
            if (p.length() >= 2 && p.charAt(1) == '*') {
                result = (matched && subMatch(s.substring(1), sIndex + 1, p, pIndex, memo))
                        || subMatch(s, sIndex, p.substring(2), pIndex + 2, memo);
            } else {
                result = matched && subMatch(s.substring(1), sIndex + 1, p.substring(1), pIndex + 1, memo);
            }
        }

        memo[sIndex][pIndex] = result;
        return result;
    }



    /**
     * dp
     * dp[i][j] 表示：s中0到i的子字符串是否和p中0到j段匹配
     * 状态转化方程:
     * dp[i][j] =
     * 1. 如果p.charAt(j)的字符为'**，则 dp[i][j] = dp[i][j - 2] || ((s.charAt(i) == p.charAt(j - 1)) && dp[i - 1][j])
     * 2. 如果不为'*'，则dp[i][j] = dp[i - 1][j - 1] && s.charAt(i) == p.charAt(j)
     * */
    public boolean isMatchByDP(String s, String p) {
        int lenS = s.length(), lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];

        // 初始化
        // 两个都为空的情况
        dp[0][0] = true;
        // s为空，p不为空的情况
        for (int i = 1; i <= lenP; i ++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            } else {
                dp[0][i] = false;
            }
        }
        // p为空，s不为空的情况
        for (int i = 1; i <= lenS; i ++) {
            dp[i][0] = false;
        }
        for (int i = 1; i <= lenS; i ++) {
            for(int j = 1; j <= lenP; j ++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || ((s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }

        return dp[lenS][lenP];
    }

    public static void main(String[] args) {
//        System.out.println(new Problem10().isMatchByDP("aa", "a*"));
        System.out.println(new Problem10().isMatchByRecurse("aab", "c*a*b"));
    }
}
