package com.leetcode.java.top200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        int minLen = Integer.MAX_VALUE, maxLen = Integer.MIN_VALUE;
        int wordSize = wordDict.size();
        Map<String, Boolean> map = new HashMap<>(wordSize);
        for (String word : wordDict) {
            int len = word.length();
            if (len < minLen) minLen = len;
            if (len > maxLen) maxLen = len;

            map.put(word, true);
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i ++) {
            if (i < minLen) dp[i] = false;
            int j = minLen;

            boolean result = false;
            while (j <= maxLen && i - j >= 0) {
                String word = s.substring(i - j, i);
                if (dp[i - j] && map.containsKey(word)) {
                    result = true;
                    break;
                }
                j ++;
            }
            dp[i] = result;
        }

        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(new Problem139().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new Problem139().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(new Problem139().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
