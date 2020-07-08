package com.leetcode.java.top100;

import java.util.ArrayList;
import java.util.List;

public class Problem89 {
    public List<Integer> grayCode(int n) {
        List<Integer> resultList = new ArrayList<>(1 << n);
        resultList.add(0);

        for (int i = 1; i <= n ; i ++) {
            nextList(resultList, i);
        }

        return resultList;
    }



    private void nextList(List<Integer> list, int n) {
        int len = 1 << (n - 1);
        for (int i = len - 1; i >= 0; i --) {
            list.add((1 << (n - 1)) + list.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem89().grayCode(3));
    }
}
