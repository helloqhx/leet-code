package com.leetcode.java.top100;

import java.util.ArrayList;
import java.util.List;

public class Problem57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int len = intervals.length;
        if (0 == len) return new int[][]{
                {newInterval[0], newInterval[1]}
        };

        List<int[]> list = new ArrayList<>(len + 1);

        boolean inserted = false;
        for (int i = 0; i < len; i ++) {
            int[] interval = intervals[i];
            if (inserted) {
                list.add(interval);
                continue;
            }

            int curFrom = interval[0], curEnd = interval[1];

            int from = newInterval[0], end = newInterval[1];

            if (end < curFrom) {
                list.add(newInterval);
                list.add(interval);
                inserted = true;
            } else if (from > curEnd) {
                list.add(interval);
            }  else if (end <= curEnd) {
                list.add(new int[] {Math.min(from, curFrom), curEnd});
                inserted = true;
            } else {
                newInterval = new int[] {Math.min(from, curFrom), end};
            }
        }

        if (!inserted) {
            list.add(newInterval);
        }

        return list.toArray(new int[0][]);
    }
}
