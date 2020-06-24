package com.leetcode.java;

public class Problem32 {
    public int longestValidParentheses(String s) {
        int len = s.length();
        if (len < 2) return 0;

        Boolean[][] cache = new Boolean[len][];
        for (int i = 0; i < len; i ++) {
            cache[i] = new Boolean[len];
        }

        return subLongest(s, 0, len - 1, cache);
    }

    private int subLongest(String s, int fromIndex, int endIndex, Boolean[][] cache) {
        if (fromIndex >= endIndex) return 0;
        int i = fromIndex, j = endIndex;
        while (i < j && s.charAt(i) != '(') i ++;
        while (j > i && s.charAt(j) != ')') j --;
        if (i == j) return 0;
        if (isValid(s, i, j, cache)) return j - i + 1;
        else {
            return Math.max(subLongest(s, i + 1, j, cache), subLongest(s, i, j - 1,  cache));
        }
    }

    private boolean isValid(String s, int from, int end, Boolean[][] cache) {
        if (from == end) return true;
        if (null != cache[from][end]) return cache[from][end];

        boolean result = false;
        int len = end - from + 1;
        if (len % 2 == 0) {
            char fromC = s.charAt(from), endC = s.charAt(end);

            if (fromC == '(' && endC == ')') {
                if (len == 2) result = true;
                else {
                    if (isValid(s, from + 1, end - 1, cache)) result = true;
                    else {
                        int i = 1;
                        while (i + from <= end - 2) {
                            if (isValid(s, from, from + i, cache) && isValid(s, from + i + 1, end, cache)) {
                                result = true;
                                break;
                            }
                            i += 2;
                        }
                    }
                }
            }
        }

        cache[from][end] = result;
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(new Problem32().longestValidParentheses("()(()"));
//        System.out.println(new Problem32().longestValidParentheses("(()"));
//        System.out.println(new Problem32().longestValidParentheses(")()())"));
        System.out.println(new Problem32().longestValidParentheses(")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())"));
    }
}
