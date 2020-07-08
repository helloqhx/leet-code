package com.leetcode.java.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();
        int i, j, k, l;
        int len = nums.length;
        for (i = 0; i < len - 3; i ++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (j = i + 1; j < len - 2; j ++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (j > (i + 1) && nums[j] == nums[j - 1]) continue;

                k = j + 1;
                l = len - 1;
                while (k < l) {
                    if (k > (j + 1) && nums[k] == nums[k - 1]) {
                        k ++;
                        continue;
                    }
                    if (l < (len - 1) && nums[l] == nums[l + 1]) {
                        l --;
                        continue;
                    }

                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        k ++;
                    } else if (sum < target) {
                        k ++;
                    } else {
                        l --;
                    }
                }
            }
        }

        return list;
    }

    public static void main(String[] args) {
        System.out.println(new Problem18().fourSum(new int[]{-3,-2,-1,0,0,1,2,3}, 0));
    }
}
