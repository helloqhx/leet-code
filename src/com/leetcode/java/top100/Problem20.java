package com.leetcode.java.top100;

public class Problem20 {
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) return true;

        if (len % 2 == 1) return false;

        char[] stack = new char[len];
        int top = -1;
        int i = 0;
        while (i < len) {
            char c = s.charAt(i);
            if (c == ')' || c == ']' || c == '}') {
                if (top < 0) return false;
                char topC = stack[top --];
                switch (c) {
                    case ')': if (topC != '(') return false;
                    break;
                    case ']': if (topC != '[') return false;
                    break;
                    case '}': if (topC != '{') return false;
                }
            } else {
                stack[++top] = c;
            }
            i ++;
        }

        return top < 0;
    }

    public static void main(String[] args) {
        System.out.println(new Problem20().isValid("()"));
        System.out.println(new Problem20().isValid("()[]{}"));
        System.out.println(new Problem20().isValid("(]"));
        System.out.println(new Problem20().isValid("([)]"));
        System.out.println(new Problem20().isValid("{[]}"));
    }
}
