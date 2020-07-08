package com.leetcode.java.top100;

class Problem14 {

    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if (len == 0) return "";

        String targetStr = strs[0];
        int targetLen = targetStr.length();
        for (int i = 1; i < len; i ++) {
            String curStr = strs[i];

            int j = 0, k = 0;
            while (j < targetLen && k < curStr.length() && targetStr.charAt(j) == curStr.charAt(k)) {
                j ++;
                k ++;
            }
            targetLen = j;
            if (targetLen == 0) return "";
        }

        return targetStr.substring(0, targetLen);
    }

    public static void main(String[] args) {
        System.out.println(new Problem14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(new Problem14().longestCommonPrefix(new String[]{"dog","racecar","car"}));
    }
}
