package com.leetcode.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem93 {
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if (len < 4 || len > 12) return Collections.emptyList();

        List<String> resultList = new ArrayList<>();


        for (int i = 1; i < 4; i ++) {
            // 放置第一个点
            String first = s.substring(0, i);
            if (!isValid(first)) break;
            for (int j = i + 1; j < len && j < i + 4; j ++) {
                // 放置第二个点
                String second = s.substring(i, j);
                if (!isValid(second)) break;
                for (int k = j + 1; k < j + 4; k ++) {
                    // 放置第三个点
                    if (len - k <= 0 || len - k > 6) break;
                    String third = s.substring(j, k), four = s.substring(k);
                    if (isValid(third) && isValid(four)) {
                        resultList.add(first + "." + second + "." + third + "." + four);
                    }
                }
            }
        }

        return resultList;
    }

    private boolean isValid(String text) {
        return text.charAt(0) == '0' ? text.length() == 1 : Integer.parseInt(text) <= 255;
    }
    public static void main(String[] args) {
        System.out.println(new Problem93().restoreIpAddresses("255255255255"));
    }
}
