package com.leetcode.java.top100;

import java.util.*;

public class Problem78 {
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        if (0 == len) return Collections.emptyList();


        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i <= len; i ++) {
            combine(nums, len, i, resultList);
        }

        return resultList;
    }


    private void combine(int[] nums, int n, int k, List<List<Integer>> resultList) {
        if (k == 0) {
            resultList.add(Collections.emptyList());
            return;
        }

        boolean[] used = new boolean[n];
        Deque<Integer> tmpList = new ArrayDeque<>(k);

        subCombine(nums, n, k, 0, used, tmpList, resultList);
    }

    private void subCombine(int[] nums, int n,  int k, int fromIndex, boolean[] used, Deque<Integer> tmpList,
                            List<List<Integer>> resultList) {
        if ((n - fromIndex + 1 +  tmpList.size()) < k) return;
        for (int i = fromIndex; i < n; i ++) {
            if (n - i + 1 + tmpList.size() < k) break;
            if (!used[i]) {
                tmpList.push(nums[i]);
                used[i] = true;
                if (tmpList.size() == k) {
                    resultList.add(new ArrayList<>(tmpList));
                } else {
                    subCombine(nums, n, k, i + 1, used, tmpList, resultList);
                }
                used[i] = false;
                tmpList.pop();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem78().subsets(new int[] {1, 2,3}));
    }
}
