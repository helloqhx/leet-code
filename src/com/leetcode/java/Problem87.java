package com.leetcode.java;

import java.util.HashMap;
import java.util.Map;

public class Problem87 {

    /***
     * 三维dp
     * dp[i][j][k] 表示s1中以i开始长度为k的字符串，是否和s2中以j开始长度为k的字符串是否匹配
     * dp[i][j][k] =
     *  for w: 1 -> k,
     *      dp[i][j][w] && dp[i + w][j + w][k - w]
     *   或
     *      dp[i][j + k - w][w] && dp[i + w][j][k - w]
     * */
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        int len = s1.length();
        boolean[][][] dp = new boolean[len][len][len + 1];


        // 初始化长度为1
        for (int i = 0; i < len; i ++) {
            for (int j = 0; j < len; j ++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for (int w = 2; w <= len; w ++) {
            for (int i = 0; i <= len - w; i ++) {
                for (int j = 0; j <= len - w; j ++) {

                    boolean result = false;
                    for (int k = 1; k < w; k ++) {
                        if ((dp[i][j][k] && dp[i + k][j + k][w - k]) || (dp[i][j + w - k][k] && dp[i + k][j][w - k])) {
                            result = true;
                            break;
                        }
                    }
                    dp[i][j][w] = result;
                }
            }
        }

        return dp[0][0][len];
    }

    /**
     * 递归
     * */
    public boolean isScramble1(String s1, String s2) {
        return subScramble(s1, s2);

    }

    private boolean subScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        Map<Character, Integer> countMap = new HashMap<>();
        int len = s1.length();
        for (int i = 0; i < len; i ++) {
            char c = s1.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < len; i ++) {
            char c = s2.charAt(i);
            int count = countMap.getOrDefault(c, 0);
            if (0 == count) return false;
            countMap.put(c, count - 1);
        }

        for (int k = 1; k < len; k ++) {
            if ((subScramble(s1.substring(0, k), s2.substring(0, k)) && subScramble(s1.substring(k), s2.substring(k))) ||
                    (subScramble(s1.substring(0, k), s2.substring(len - k)) && subScramble(s1.substring(k), s2.substring(0, len - k)))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //""
        //"bbbbabccccbbbabcba"
        System.out.println(new Problem87().isScramble1("ccabcbabcbabbbbcbb", "bbbbabccccbbbabcba"));
    }
}
