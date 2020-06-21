package com.leetcode.java;

import java.util.*;

public class Problem15 {

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i ++) {
            map.put(nums[i], i);
        }

        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < len - 2; i ++) {
            int a = nums[i];
            int target = -a;

            map.remove(a);
            List<int[]> indexes = twoNum(nums, map, target);
            if (indexes.size() > 0) {
                for (int[] items : indexes) {
                    resultList.add(Arrays.asList(a, items[0], items[1]));
                }

            }
        }

        return resultList;
    }

    private List<int[]> twoNum(int[] nums, Map<Integer, Integer> map, int target) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0, len = nums.length; i < len; i ++) {
            int a = nums[i], b = target - a;
            Integer bIndex = map.get(b);
            if (null == bIndex) continue;
            if (bIndex > i) {
                list.add(new int[]{a, b});
            }
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(new Problem15().threeSum(new int[] {-1, 0, 1, 2, -1, -4}));
    }
}
