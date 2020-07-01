package com.leetcode.java;

public class Problem718 {

    /**
     * 滑动窗口
     * 固定A中i和B中的j位置，开始对比，寻找当前固定位置下的最大长度，如果大于当前最大长度，则更新
     * 如果当前的最大长度，已经大于A和B的根据某个位置对齐的公共长度，则不需要继续比了
     * */
    public int findLength(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        if (0 == lenA || 0 == lenB) return 0;

        // 对齐0位置的
        int fromA = 0, fromB = 0;
        int maxLen = subFind(A, B, fromA, fromB);

        // 对齐A的每一个位置和B的第0个位置
        for (int i = 1; i < lenA; i ++) {
            if (lenA - i < maxLen) {
                // 最大可能长度已经小于已经找到的最大长度了
                break;
            }
            int subMaxLen = subFind(A, B, i, 0);
            if (subMaxLen > maxLen) maxLen = subMaxLen;
        }

        // 对齐B的每一位置和A的第0个位置
        for (int i = 1; i < lenB; i ++) {
            if (lenB - i < maxLen) {
                // 最大可能长度已经小于已经找到的最大长度了
                break;
            }
            int subMaxLen = subFind(A, B, 0, i);
            if (subMaxLen > maxLen) maxLen = subMaxLen;
        }

        return maxLen;
    }

    /**
     * dp
     * 从后向前扫描：dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
     * */
    public int findLengthByDP(int[] A, int[] B) {
        int maxLen = 0, lenA = A.length, lenB = B.length;

        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = lenA -1 ; i >= 0; i --) {
            for (int j = lenB - 1; j >= 0; j --) {
                dp[i][j] = A[i] == B[j] ? 1 + dp[i + 1][j + 1] : 0;
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return maxLen;
    }

    private int subFind(int[] A, int[] B, int fromA, int fromB) {
        int maxLen = 0, curLen = 0;

        int i = fromA, j = fromB;
        int lenA = A.length, lenB = B.length;
        while (i < lenA && j < lenB) {
            if (A[i] == B[j]) {
                curLen ++;
            } else {
                if (curLen > maxLen) maxLen = curLen;
                curLen = 0;
            }

            i ++;
            j ++;
        }

        if (curLen > maxLen) maxLen = curLen;

        return maxLen;
    }

    public static void main(String[] args) {
//        System.out.println(new Problem718().findLength(new int[] {1,2,3,2,1}, new int[] {3,2,1,4,7}));
        System.out.println(new Problem718().findLengthByDP(new int[] {1,2,3,2,1}, new int[] {3,2,1,4,7}));
    }
}
