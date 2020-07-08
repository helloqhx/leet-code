package com.leetcode.java.top100;

import java.util.*;

public class Problem76 {
    public String minWindow(String s, String t) {
        int lenS = s.length(), lenT = t.length();
        if (lenS == 0) return "";

        Map<Character, Integer> countMap = new HashMap<>(lenT);
        for (int i = 0;  i < lenT; i ++) {
            char c = t.charAt(i);
            int count = countMap.getOrDefault(c, 0);
            count ++;
            countMap.put(c, count);
        }

        // 记录字符在上面列表中的index
        Map<Character, List<Integer>> map = new HashMap<>();
        boolean[] used = new boolean[lenS];
        int minLen = Integer.MAX_VALUE;
        String minText = "";

        int fromIndex = -1;
        int sum = 0;
        for (int i = 0; i < lenS; i++) {
            char c = s.charAt(i);
            Integer count = countMap.get(c);
            if (null == count) continue;

            if (fromIndex == -1) fromIndex = i;
            List<Integer> indexList = map.get(c);
            if (null == indexList) {
                indexList = new LinkedList<>();
                map.put(c, indexList);
            } else if (indexList.size() == count){
                int idx = indexList.get(0);
                used[idx] = false;
                indexList.remove(0);
                sum --;

                while (fromIndex < i && !used[fromIndex]) fromIndex ++;
            }

            sum ++;
            indexList.add(i);
            used[i] = true;

            if (sum == lenT) {
                int len = i - fromIndex + 1;
                if (len < minLen) {
                    minLen = len;
                    minText = s.substring(fromIndex, i + 1);
                }
            }
        }


        return minText;
    }

    public static void main(String[] args) {
        System.out.println(new Problem76().minWindow("a", "a"));
    }
}
