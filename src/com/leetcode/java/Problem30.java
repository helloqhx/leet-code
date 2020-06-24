package com.leetcode.java;

import java.util.*;

public class Problem30 {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s.isEmpty()) return Collections.emptyList();
        if (words.length == 0) return Collections.emptyList();
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
        int i = fromIndex, j = 0;
        int len = s.length(), max = len - wordLen * sum;
        while (i <= max) {
            String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
            System.out.println(i + ": " + j);
            System.out.println(curMap);
            System.out.println(word);
            Integer targetCount = map.get(word);
            if (null == targetCount) {
                i += (j + 1) * wordLen;
                j = 0;
                curMap.clear();
            } else {
                int cnt = curMap.getOrDefault(word, 0);
                cnt ++;
                if (cnt > targetCount) {
                    int k = 0;
                    while (k < j) {
                        String w = s.substring(i + k * wordLen, i + (k + 1) * wordLen);
                        int c = curMap.getOrDefault(w, 0);
                        curMap.put(w, c - 1);
                        if (word.equals(w)) break;

                        k ++;
                    }
                    j -= k + 1;
                    i += (k + 1) * wordLen;
                } else if (cnt == targetCount) {
                    j ++;
                    curMap.put(word, cnt);
                    if (j == sum) {
                        resultList.add(i);
                        String firstWord = s.substring(i, i + wordLen);
                        int c = curMap.getOrDefault(firstWord, 0);
                        curMap.put(firstWord, c - 1);
                        i += wordLen;
                        j -= 1;
                    }
                } else {
                    curMap.put(word, cnt);
                    j ++;
                }
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(new Problem30().findSubstring("aaa", new String[] {"a","a"}));
//        System.out.println(new Problem30().findSubstring("foobarfoobar", new String[] {"foo","bar"}));
//        System.out.println(new Problem30().findSubstring("barfoothefoobarman", new String[] {"foo","bar"}));
//        System.out.println(new Problem30().findSubstring("wordgoodgoodgoodbestword", new String[] {"word","good","best","word"}));
//        System.out.println(new Problem30().findSubstring("barfoofoobarthefoobarman", new String[] {"bar","foo","the"}));
//        System.out.println(new Problem30().findSubstring("wordgoodgoodgoodbestword", new String[] {"word","good","best","good"}));
    }
}
