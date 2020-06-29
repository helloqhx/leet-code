package com.leetcode.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem46 {

    public List<List<Integer>> permute(int[] nums) {
        Stack<Integer> path = new Stack<>();
        List<List<Integer>> resultList = new ArrayList<>();

        subPermute(nums, 0, path, resultList);

        return resultList;
    }

    private void subPermute(int[] nums, int fromIndex, Stack<Integer> path, List<List<Integer>> resultList) {
        for (int i = fromIndex; i < nums.length; i ++) {
            path.push(nums[])
        }
    }
}
