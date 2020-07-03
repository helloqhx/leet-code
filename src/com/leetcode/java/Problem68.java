package com.leetcode.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int len = words.length;
        if (0 == len) return Collections.emptyList();
        if (1 == len) return Collections.singletonList(makeLastLine(words, 0, 1, words[0].length(), maxWidth));

        List<String> resultList = new ArrayList<>();
        int i = 0;
        int wordLen = 0, wordSize = 0;
        while (i < len) {
            String word = words[i];
            int wLen = word.length();
            if (wordLen + wLen + wordSize > maxWidth) {
                String line = makeLine(words, i - wordSize, wordSize, wordLen, maxWidth);
                resultList.add(line);
                wordLen = wLen;
                wordSize = 1;
            } else {
                wordLen += word.length();
                wordSize ++;
            }

            i ++;
        }

        if (wordSize > 0) {
            resultList.add(makeLastLine(words, i - wordSize, wordSize, wordLen, maxWidth));
        }

        return resultList;
    }


    private String makeLastLine(String[] words, int from, int len, int wordLen, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - 1; i ++) {
            sb.append(words[from + i]);
            sb.append(" ");
        }

        sb.append(words[from + len - 1]);

        int whiteBlankCount = maxWidth - wordLen - (len - 1);
        while (whiteBlankCount > 0) {
            sb.append(" ");
            whiteBlankCount --;
        }

        return sb.toString();
    }

    private String makeLine(String[] words, int from, int len, int wordLen, int maxWidth) {
        int sumBlankCount = maxWidth - wordLen;

        int avgBlankCount = len > 1 ? sumBlankCount / (len - 1) : 0,
                leftBlankCount = len > 1 ? sumBlankCount % (len - 1) : sumBlankCount;
        StringBuilder sb = new StringBuilder();
        sb.append(words[from]);

        int i = 1;
        while (i < len) {
            int blankCount = avgBlankCount + (leftBlankCount > 0 ? 1 : 0);
            int k = 0;
            while (k < blankCount) {
                sb.append(" ");
                k ++;
            }

            sb.append(words[from + i]);
            i ++;
            leftBlankCount --;
        }

        while (leftBlankCount > 0) {
            sb.append(" ");
            leftBlankCount --;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem68 p = new Problem68();
        System.out.println(p.fullJustify(new String[]{"a"}, 2));
    }

}
