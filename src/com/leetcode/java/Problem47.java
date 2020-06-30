package com.leetcode.java;

import java.util.*;

public class Problem47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (0 == len) return Collections.emptyList();
        List<Integer> path = new ArrayList<>(len);
        List<List<Integer>> resultList = new ArrayList<>();

        subPermute(nums, 0, path, resultList);

        return resultList;
    }

    private void subPermute(int[] nums, int fromIndex, List<Integer> path, List<List<Integer>> resultList) {
        Set<Integer> useSet = new HashSet<>();
        for (int i = fromIndex; i < nums.length; i ++) {
            int num = nums[i];
            if (useSet.contains(num)) continue;

            path.add(num);
            if (path.size() == nums.length) {
                resultList.add(new ArrayList<>(path));
            } else {
                swap(nums, fromIndex, i);
                subPermute(nums, fromIndex + 1, path, resultList);
                swap(nums, fromIndex, i);
            }
            path.remove(path.size() - 1);
            useSet.add(num);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Problem47().permuteUnique(new int[] {1,0,0,3}));
    }
}
