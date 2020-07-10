package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

import java.util.*;

public class Problem102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) return Collections.emptyList();
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> resultList = new ArrayList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            int i = 0;
            while (i < size) {
                TreeNode head = queue.poll();
                list.add(head.val);
                if (null != head.left) queue.add(head.left);
                if (null != head.right) queue.add(head.right);

                i ++;
            }
            resultList.add(list);
        }

        return resultList;
    }
}
