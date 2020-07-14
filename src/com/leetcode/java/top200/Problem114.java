package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

public class Problem114 {
    public void flatten(TreeNode root) {
        if (null == root) return;
        peeTraverse(root);
    }


    private TreeNode peeTraverse(TreeNode root) {
        TreeNode nextNode = root, left = root.left, right = root.right;
        if (null != left) {
            root.right = left;
            nextNode = peeTraverse(left);
            root.left = null;
        }
        if (null != right) {
            nextNode.right = right;
            nextNode = peeTraverse(right);
        }

        return nextNode;
    }
}
