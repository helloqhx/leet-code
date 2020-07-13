package com.leetcode.java.top200;

import com.leetcode.java.Utils;

import java.util.Arrays;

public class Problem174 {

    /**
     * dp，从右下向左上遍历
     * dp[i][j]表示到节点(i,j)所需要的最小初始值
     * dp[i][j] = Math.max((Math.min(dp[i + 1][j], dp[i][j + 1]) - value[i][j]), 1)
     *
     * 因为最小初始值必须为1，所以，需要和1做比较，取较大值
     * */
    public int calculateMinimumHP(int[][] dungeon) {
        int row = dungeon.length;
        if (0 == row) return 1;
        int col = dungeon[0].length;
        if (0 == col) return 1;

        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i <= row; i ++) {
            dp[i][col] = Integer.MAX_VALUE;
        }
        for (int i = 0; i <= col; i ++) {
            dp[row][i] = Integer.MAX_VALUE;
        }

        dp[row][col - 1] = 1;
        dp[row -1][col] = 1;
        for (int i = row - 1; i >= 0; i --) {
            for (int j = col - 1; j >= 0; j --) {
                dp[i][j] = Math.max(Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 1);
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println(new Problem174().calculateMinimumHP(new int[][] {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        }));
    }
}
