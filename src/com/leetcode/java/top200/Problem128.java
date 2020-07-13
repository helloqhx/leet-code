package com.leetcode.java.top200;

import java.util.HashSet;
import java.util.Set;

public class Problem128 {
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if (0 == len) return 0;
        if (1 == len) return 1;

        Set<Integer> set = new HashSet<>(len);
        for (int i : nums) {
            set.add(i);
        }

        Set<Integer> visited = new HashSet<>(len);
        int maxLen = 1;
        for (int i = 0; i < len; i ++) {
            int num = nums[i];
            if (visited.contains(num)) continue;

            // 向左扩展
            int left = num - 1;
            while (set.contains(left)) {
                visited.add(left);
                left --;
            }

            int right = num + 1;
            while (set.contains(right)) {
                visited.add(right);
                right ++;
            }

            maxLen = Math.max(maxLen, right - 1 - left);
        }

        return maxLen;
    }
}
