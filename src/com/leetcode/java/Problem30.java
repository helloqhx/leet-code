package com.leetcode.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem30 {
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> map = new HashMap<>(words.length);
        for (String word : words) {
            int count = map.getOrDefault(word, 0);
            map.put(word, count + 1);
        }

        int startIndex = 0;
        int len = words[0].length();
        List<Integer> resultList = new ArrayList<>();
        while (startIndex < len) {
            find(s, startIndex, map, len, words.length, resultList);
            startIndex ++;
        }

        return resultList;
    }

    private void find(String s, int fromIndex, Map<String, Integer> map, int wordLen, int sum, List<Integer> resultList) {
        Map<String, Integer> curMap = new HashMap<>(sum);
        int i = fromIndex;
        int len = s.length(), max = len - wordLen * sum;
        while (i < max) {

            int j = 0;
            curMap.clear();
            while (j < sum) {
                String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                Integer targetCount = map.get(word);
                if (null == targetCount) {
                    i += j * wordLen;
                    break;
                } else {
                    int cnt = curMap.getOrDefault(word, 0);
                    cnt ++;
                    if (cnt > targetCount) {
                        i += wordLen;
                        break;
                    } else {
                        curMap.put(word, cnt);
                    }
                }
                j ++;
            }

            if (j == sum) {
                resultList.add(i);
                i += wordLen * sum;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem30().findSubstring("barfoothefoobarman", new String[] {"foo","bar"}));
    }
}
