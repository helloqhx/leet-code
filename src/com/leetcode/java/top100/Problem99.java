package com.leetcode.java.top100;

import com.leetcode.java.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem99 {
    public void recoverTree(TreeNode root) {
        if (null == root) return;
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = root, prev = null;
        TreeNode x = null, y = null;
        while (null != cur || !stack.isEmpty()) {
            while (null != cur) {
                stack.push(cur);
                cur = cur.left;
            }

            TreeNode node = stack.pop();
            if (null != prev && node.val > prev.val) {
                y = node;
                if (x == null) x = prev;
                else break;
            }
            prev = node;
            cur = node.right;
        }

        if (null != x && null != y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }

    /**
     * morris 遍历，O(1)空间
     * */
    public void recoverTree1(TreeNode root) {
        TreeNode cur = root, prev = null;
        TreeNode x = null, y = null;
        while (cur != null) {
            if (cur.left == null) {
                if (null != prev && prev.val > cur.val) {
                    y = prev;
                    if (x == null) x = cur;
                }
                prev = cur;
                cur = cur.right;
            } else {
                TreeNode preNode = getPredecessor(cur);
                if (preNode.right == cur) {
                    // 之前设置过了，得清除一下
                    preNode.right = null;
                    if (null != prev && prev.val > cur.val) {
                        y = prev;
                        if (x == null) x = cur;
                    }
                    prev = cur;
                    cur = cur.right;
                } else {
                    preNode.right = cur;
                    cur = cur.left;
                }
            }
        }

        if (null != x && null != y) {
            int tmp = x.val;
            x.val = y.val;
            y.val = tmp;
        }
    }


    /**
     * 获取该节点中序遍历的前驱节点
     * */
    private TreeNode getPredecessor(TreeNode node) {
        if (node.left != null) {
            TreeNode cur = node.left;
            while (null != cur.right && cur.right != node) {
                cur = cur.right;
            }

            return cur;
        }

        return null;
    }

}
