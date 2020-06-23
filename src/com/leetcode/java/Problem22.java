package com.leetcode.java;

import java.util.*;

public class Problem22 {

    public List<String> generateParenthesisByDfs(int n) {
        List<String> list = new ArrayList<>();
        dfs(n, n, list, "");
        return list;
    }

    public List<String> generateParenthesis(int n) {
        List<String>[] map = new List[n + 1];
        map[0] = Collections.singletonList("");
        map[1] = Collections.singletonList("()");

        return genText(n, map);
    }

    private List<String> genText(int n, List[] map) {
        List<String> result = map[n];
        if (null != result) return result;

        result = new ArrayList<>();
        int i = 0;
        while (i < n) {
            List<String> subList = genText(i, map);
            int leftN = (n - 1) - i;
            List<String> leftList = genText(leftN, map);

            for (String inText : subList) {
                for (String leftText : leftList) {
                    result.add("(" + inText + ")" + leftText);
                }
            }
            i ++;
        }

        map[n] = result;
        return result;
    }

    private void dfs(int left, int right, List<String> resultList, String curText) {
        if (left == 0 && right == 0) {
            resultList.add(curText);
            return;
        }

        if (left > 0) {
            dfs(left - 1, right, resultList, curText + "(");
        }

        if (right > 0 && right > left) {
            dfs(left, right - 1, resultList, curText + ")");
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Problem22().generateParenthesis(3));
        System.out.println(new Problem22().generateParenthesisByDfs(3));
    }
}
