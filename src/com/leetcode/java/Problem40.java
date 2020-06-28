package com.leetcode.java;

import java.util.*;

public class Problem40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        subSum(candidates, 0, target, new Stack<>(), list);

        return list;
    }

    private void subSum(int[] candidates, int fromIndex, int target, Stack<Integer> stack, List<List<Integer>> resultList) {
        int len = candidates.length;
        if (fromIndex >= len || target <= 0 || target < candidates[fromIndex]) return;
        for (int i = fromIndex; i < len; i ++) {
            int c = candidates[i];
            if (i > fromIndex && c == candidates[i - 1]) continue;
            int num = target - c;
            if (num == 0) {
                stack.push(c);
                resultList.add(new ArrayList<>(stack));
                stack.pop();
                break;
            }

            if (i >= len - 1 || num < candidates[i + 1]) continue;
            stack.push(c);
            subSum(candidates, i + 1, num, stack, resultList);
            stack.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem40().combinationSum2(new int[] {10,1,2,7,6,1,5}, 8));
        System.out.println(new Problem40().combinationSum2(new int[] {2,5,2,1,2}, 5));
    }
}
