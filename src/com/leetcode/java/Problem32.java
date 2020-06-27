package com.leetcode.java;

public class Problem32 {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int len = s.length();
        int maxLen = 0;
        for (int i = 0; i < len; i ++) {
            if (s.charAt(i) == '(') left ++;
            else {
                right ++;
                if (right > left) {
                    left = 0;
                    right = 0;
                } else if (right == left) {
                    maxLen = Math.max(maxLen, 2 * right);
                }
            }
        }

        left = right = 0;
        for (int i = len - 1; i >= 0; i --) {
            if (s.charAt(i) == ')') right ++;
            else {
                left ++;
                if (left > right) {
                    left = 0;
                    right = 0;
                } else if (right == left) {
                    maxLen = Math.max(maxLen, 2 * left);
                }
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {
        System.out.println(new Problem32().longestValidParentheses("()(()"));
        System.out.println(new Problem32().longestValidParentheses("(()"));
        System.out.println(new Problem32().longestValidParentheses(")()())"));
//        System.out.println(new Problem32().longestValidParentheses(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())"));
    }
}
