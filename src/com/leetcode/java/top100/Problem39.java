package com.leetcode.java.top100;

import java.util.*;

public class Problem39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return subSum(candidates, 0, target);
    }

    private List<List<Integer>> subSum(int[] candidates, int fromIndex, int target) {
        int len = candidates.length;
        if (fromIndex >= len || target <= 0 || target < candidates[fromIndex]) return Collections.emptyList();
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = fromIndex; i < len; i ++) {
            int c = candidates[i];
            int num = target - c;
            if (num == 0) resultList.add(Collections.singletonList(c));
            else if (num > 0) {
                List<List<Integer>> subList = subSum(candidates, i, num);
                if (subList.size() > 0) {
                    for (List<Integer> l : subList) {
                        List<Integer> nl = new ArrayList<>();
                        nl.add(c);
                        nl.addAll(l);

                        resultList.add(nl);
                    }
                }
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(new Problem39().combinationSum(new int[] {2, 3, 6, 7}, 7));
    }

}
