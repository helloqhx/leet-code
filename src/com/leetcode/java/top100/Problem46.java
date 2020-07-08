package com.leetcode.java.top100;

import java.util.*;

public class Problem46 {

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (0 == len) return Collections.emptyList();
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>(len);
        List<List<Integer>> resultList = new ArrayList<>();

        subPermute(nums, path, resultList, used);

        return resultList;
    }

    private void subPermute(int[] nums, List<Integer> path, List<List<Integer>> resultList, boolean[] used) {
        for (int i = 0; i < nums.length; i ++) {
            int num = nums[i];
            if (!used[i]) {
                path.add(num);
                used[i] = true;
                if (path.size() == nums.length) {
                    resultList.add(new ArrayList<>(path));
                } else {
                    subPermute(nums, path, resultList, used);
                }
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem46().permute(new int[] {1,2,3}));
    }
}
