package com.leetcode.java.top100;

public class Problem58 {
    public int lengthOfLastWord(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int i = len - 1;
        while (i >= 0 && s.charAt(i) == ' ') i --;

        int maxLen = 0;
        for (; i >= 0; i --) {
            char c = s.charAt(i);
            if (c == ' ') break;
            maxLen ++;
        }

        return maxLen;
    }
}
