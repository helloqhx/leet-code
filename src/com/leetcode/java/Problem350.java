package com.leetcode.java;

import java.util.*;

public class Problem350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        int[] sourceNums, targetNums;
        if (len1 < len2) {
            sourceNums = nums1;
            targetNums = nums2;
        } else {
            sourceNums = nums2;
            targetNums = nums1;
        }

        int len = sourceNums.length;
        Map<Integer, Integer> map = new HashMap<>(len);
        for (int num : sourceNums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }

        int[] result = new int[len];
        int i = 0;
        for (int num : targetNums) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                result[i ++] = num;
                map.put(num, count - 1);
            }
        }


        return Arrays.copyOf(result, i);
    }
}
