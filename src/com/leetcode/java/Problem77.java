package com.leetcode.java;

import java.util.*;

public class Problem77 {
    public List<List<Integer>> combine(int n, int k) {
        if (k == 0) return Collections.emptyList();
        boolean[] used = new boolean[n + 1];

        List<List<Integer>> resultList = new ArrayList<>();
        Deque<Integer> tmpList = new ArrayDeque<>(k);

        subCombine(n, k, 1, used, tmpList, resultList);

        return resultList;
    }

    private void subCombine(int n, int k, int fromIndex, boolean[] used, Deque<Integer> tmpList, List<List<Integer>> resultList) {
        if ((n - fromIndex + 1 +  tmpList.size()) < k) return;
        for (int i = fromIndex; i <= n; i ++) {
            if (n - i + 1 + tmpList.size() < k) break;
            if (!used[i]) {
                tmpList.push(i);
                used[i] = true;
                if (tmpList.size() == k) {
                    resultList.add(new ArrayList<>(tmpList));
                } else {
                    subCombine(n, k, i + 1, used, tmpList, resultList);
                }
                used[i] = false;
                tmpList.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem77().combine(4, 2));
    }
}
