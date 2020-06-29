package com.leetcode.java;

public class Problem38 {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String text = "1";
        int i = 1;
        while (i < n) {
            int len = text.length();
            int j = 0;
            StringBuilder sb = new StringBuilder();
            while (j < len) {
                char c = text.charAt(j);
                int cnt = 1;
                int k = j + 1;
                while (k < len && text.charAt(k) == c) {
                    k ++;
                    cnt ++;
                }
                sb.append(cnt);
                sb.append(c);
                j = k;
            }
            text = sb.toString();
            i ++;
        }

        return text;
    }

    public static void main(String[] args) {
        System.out.println(new Problem38().countAndSay(3));
    }
}
