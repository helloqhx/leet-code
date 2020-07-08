package com.leetcode.java;

import java.util.*;

public class Problem90 {


    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        int len = nums.length;
        if (len == 0) return Collections.emptyList();

        Arrays.sort(nums);
        int maxNum = 1 << len;
        List<List<Integer>> resultList = new ArrayList<>(maxNum);
        for (int i = 0; i < maxNum; i ++) {

            List<Integer> list = new ArrayList<>(len);
            boolean skip = false;
            for (int j = 0; j < len; j ++) {
                if (((i >> j) & 1) == 1) {
                    // 该bit位为1
                    if (j > 0 && nums[j] == nums[j - 1] && (((i >> (j - 1)) & 1) == 0)) {
                        skip = true;
                        break;
                    }

                    list.add(nums[j]);
                }
            }

            if (!skip) {
                resultList.add(list);
            }
        }

        return resultList;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int len = nums.length;
        if (0 == len) return Collections.emptyList();
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();

        resultList.add(Collections.emptyList());

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= len; i ++) {
            stack.clear();
            subset(nums, i, 0, stack, resultList);
        }

        return resultList;
    }


    private void subset(int[] nums, int k, int fromIndex, Deque<Integer> stack, List<List<Integer>> resultList) {
        for (int i = fromIndex; i < nums.length; i ++) {
            if (fromIndex + k - stack.size() > nums.length) break;
            int num = nums[i];
            if (i > fromIndex && nums[i] == nums[i - 1]) continue;
            stack.push(num);
            if (stack.size() == k) {
                resultList.add(new ArrayList<>(stack));
            } else {
                subset(nums, k, i + 1, stack, resultList);
            }
            stack.pop();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem90().subsetsWithDup1(new int[]{4,4,4,1,4}));
    }
}
