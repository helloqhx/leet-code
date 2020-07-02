package com.leetcode.java;

public class Problem60 {

    /**
     * 固定一个数之后，后面的排列有 (n-1)! 种
     * 数学意义：一个数可以转换为阶乘表示
     * */
    public String getPermutation(int n, int k) {
        // 0! 1! 2! ... 8!
        int[] multiply = new int[] {1, 1, 2, 6, 24, 120, 720, 5040, 40320};

        boolean[] used = new boolean[n + 1];
        used[0] = true;

        // 计数从0开始
        --k;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int index = k / multiply[n - 1];
            k = k - index * multiply[n - 1];
            int num = getNumber(used, index);
            sb.append(num);
            n --;
        }

        return sb.toString();
    }

    private int getNumber(boolean[] used, int index) {
        int i = 0, cnt = 0;
        while (i < used.length && cnt < index + 1) {
            if (!used[i]) cnt++;
            i ++;
        }

        used[i - 1] = true;
        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(new Problem60().getPermutation(2, 1));
    }

}
