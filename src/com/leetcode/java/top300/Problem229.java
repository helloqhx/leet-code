package com.leetcode.java.top300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem229 {
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        if (len < 1) return Collections.emptyList();

        int targetA = 0, targetB = 0;
        int counterA = 0, counterB = 0;

        for (int i = 0; i < len; i ++) {
            int num = nums[i];

            if (num == targetA) {
                counterA ++;
            } else if (num == targetB) {
                counterB ++;
            } else if (counterA == 0) {
                targetA = num;
                counterA = 1;
            } else if (counterB == 0) {
                targetB = num;
                counterB = 1;
            } else {
                counterA --;
                counterB --;
            }
        }

        counterA = 0;
        counterB = 0;
        for (int num: nums) {
            if (num == targetA) {
                counterA ++;
            } else if (num == targetB) {
                counterB ++;
            }
        }

        List<Integer> list = new ArrayList<>(2);
        if (counterA > len / 3) {
            list.add(targetA);
        }

        if (counterB > len / 3) {
            list.add(targetB);
        }

        return list;
    }
}
