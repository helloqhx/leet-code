package com.leetcode.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem56 {

    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) return new int[0][];
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>(len);

        int[] curInterval = intervals[0];
        int i = 1;
        for (; i < len; i ++) {
            int[] interval = intervals[i];
            if (interval[0] <= curInterval[1]) {
                // 合并
                curInterval = new int[]{curInterval[0], Math.max(curInterval[1], interval[1])};
            } else {
                list.add(curInterval);
                curInterval = interval;
            }
        }
        list.add(curInterval);

        return list.toArray(new int[0][]);
    }
}
