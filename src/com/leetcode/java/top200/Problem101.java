package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem101 {

    // 递归
    public boolean isSymmetric(TreeNode root) {
        if (null == root) return true;
        return subSymmetric(root.left, root.right);
    }

    private boolean subSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) return true;
        if (null == left || null == right) return false;
        if (left.val != right.val) return false;

        return subSymmetric(left.left, right.right) && subSymmetric(left.right, right.left);
    }


    public boolean isSymmetric1(TreeNode node) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(node);
        queue.addLast(node);

        while (!queue.isEmpty()) {
            TreeNode left = queue.pollFirst(), right = queue.pollFirst();
            if (null == left && null == right) continue;
            if ((null == left || null == right) || (left.val != right.val)) return false;

            queue.addLast(left.left);
            queue.addLast(right.right);
            queue.addLast(left.right);
            queue.addLast(right.left);
        }

        return true;
    }
}
