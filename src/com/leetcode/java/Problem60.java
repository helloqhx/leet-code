package com.leetcode.java;

public class Problem60 {
    public String getPermutation(int n, int k) {
        // 1! 2! ... 8!
        int[] multiply = new int[] {0, 1, 2, 6, 24, 120, 720, 5040, 40320};

        boolean[] used = new boolean[n];

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int index = k / multiply[n - 1];
            System.out.println(index);
            int num = getNumber(used, index);
            System.out.println(num + 1);
            sb.append(num + 1);
            used[num] = true;

            k = k - index * multiply[n - 1];
            n --;
        }

        return sb.toString();
    }

    private int getNumber(boolean[] used, int index) {
        int i = 0, cnt = 0;
        while (i < used.length && cnt <= index) {
            if (!used[i]) cnt++;
            i ++;
        }

        return i;
    }

    public static void main(String[] args) {
        System.out.println(new Problem60().getPermutation(3, 3));
    }

}
