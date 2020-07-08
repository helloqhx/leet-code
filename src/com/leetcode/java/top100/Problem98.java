package com.leetcode.java.top100;

import com.leetcode.java.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem98 {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root;
        int lastValue = Integer.MIN_VALUE;
        while (null != cur || !stack.isEmpty()) {
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            int val = node.val;
            if (val <= lastValue) return false;
            lastValue = val;

            cur = node.right;
        }


        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        return isInRange(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isInRange(TreeNode node, int left, int right) {
        int val = node.val;
        if (val <= left || val >= right) return false;
        TreeNode leftNode = node.left, rightNode = node.right;
        if (null != leftNode) {
            if (!isInRange(leftNode, left, val)) return false;
        }

        if (null != rightNode) {
            if (!isInRange(rightNode, val, right)) return false;
        }

        return true;
    }
}
