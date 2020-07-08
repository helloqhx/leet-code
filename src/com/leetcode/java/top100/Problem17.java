package com.leetcode.java.top100;

import java.util.*;

public class Problem17 {
    private static Map<Character, char[]> map = new HashMap<>(8);
    static {
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return Collections.emptyList();
        int len = digits.length();
        int maxSize = 1;
        char[][] cMap = new char[len][];
        for (int i = 0; i < len; i ++) {
            char c = digits.charAt(i);
            char[] cs = map.get(c);
            cMap[i] = cs;
            maxSize *= cs.length;
        }

        List<String> resultList = new ArrayList<>(maxSize);
        int i = 0;
        char[] line = new char[len];
        while (i < maxSize) {
            int j = len - 1;
            int div = 1;
            while (j >= 0) {
                char[] t = cMap[j];
                int l = t.length;
                char c = t[(i / div) % l];
                div = div * l;
                line[j] = c;
                j --;
            }
            resultList.add(new String(line));
            i ++;
        }

        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(new Problem17().letterCombinations("234"));
    }
}
