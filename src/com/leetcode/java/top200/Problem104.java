package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem104 {

    public int maxDepth(TreeNode root) {
        if (null == root) return 0;

        Queue<TreeNode> stack = new ArrayDeque<>();
        stack.add(root);

        int h = 1;
        while (!stack.isEmpty()) {
            int size = stack.size();
            while (size > 0) {
                TreeNode node = stack.poll();
                if (node.left != null) stack.add(node.left);
                if (node.right != null) stack.add(node.right);

                size --;
            }
            h ++;
        }


        return h;
    }

    public int maxDepth1(TreeNode root) {
        if (null == root) return 0;

        return Math.max(null == root.left ? 0 : maxDepth1(root.left), null == root.right ? 0 : maxDepth1(root.right) ) + 1;
    }
}
