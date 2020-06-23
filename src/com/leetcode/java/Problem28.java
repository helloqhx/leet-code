package com.leetcode.java;

public class Problem28 {
    public int strStr(String haystack, String needle) {
        if (haystack.isEmpty()) return needle.isEmpty() ? 0 : -1;
        if (needle.isEmpty()) return 0;

        int i = 0, len = haystack.length(), tLen = needle.length();
        while (i < len) {
            if (i + tLen > len) break;
            if (equals(haystack, needle, i)) return i;
            i ++;
        }

        return -1;
    }

    private boolean equals(String text, String target, int index) {
        for (int i = 0, len = target.length(); i < len; i ++) {
            if (text.charAt(index + i) != target.charAt(i)) return false;
        }

        return true;
    }
}
