package com.leetcode.java.top100;

import java.util.ArrayList;
import java.util.List;

public class Problem71 {
    public String simplifyPath(String path) {
        List<String> list = split(path);

        int size = list.size();
        List<String> resultList = new ArrayList<>(size);
        int i = 0, cnt = -1;
        while (i < size) {
            String name = list.get(i);
            if (name.equals("..")) {
                if (cnt >= 0) {
                    resultList.remove(cnt);
                    cnt --;
                }
            } else {
                resultList.add(name);
                cnt ++;
            }
            i ++;
        }

        return join(resultList);
    }

    private List<String> split(String path) {
        int len = path.length();
        int from = 0;
        while (from < len && path.charAt(from) == '/') from ++;

        List<String> list = new ArrayList<>();
        int i = from;

        while (i < len) {
            while (i < len && path.charAt(i) != '/') i ++;
            String text = path.substring(from, i);
            if (!text.equals(".")) {
                list.add(path.substring(from, i));
            }
            while (i < len && path.charAt(i) == '/') i ++;
            from = i;
        }

        return list;
    }

    private String join(List<String> list) {
        int size = list.size();
        if (list.size() == 0) return "/";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i ++) {
            sb.append("/");
            sb.append(list.get(i));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Problem71 p = new Problem71();
        System.out.println(p.simplifyPath("/"));
    }
}
