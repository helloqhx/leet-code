package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Problem112 {
    public boolean hasPathSum(TreeNode root, int sum) {

            Deque<TreeNode> stack = new ArrayDeque<>();
            stack.push(root);

            int curSum = root.val;
            TreeNode lastPopNode = null;
            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                if (null != lastPopNode) {
                    if (node.left == lastPopNode) {
                        if (null != node.right) {
                            stack.push(node.right);
                            curSum += node.right.val;
                            lastPopNode = null;
                        } else {
                            lastPopNode = stack.pop();
                            curSum -= lastPopNode.val;
                        }
                    } else {
                        lastPopNode = stack.pop();
                        curSum -= lastPopNode.val;
                    }
                } else {
                    if (null != node.left) {
                        stack.push(node.left);
                        curSum += node.left.val;
                        lastPopNode = null;
                    } else if (null != node.right) {
                        stack.push(node.right);
                        curSum += node.right.val;
                        lastPopNode = null;
                    } else {
                        if (curSum == sum) return true;
                        else {
                            lastPopNode = stack.pop();
                            curSum -= lastPopNode.val;
                        }
                    }
                }
            }

            return false;
    }

    public boolean hasPathSum1(TreeNode root, int sum) {
        if (root.left == null && root.right == null) return sum == root.val;

        sum = sum - root.val;
        return (null != root.left && hasPathSum1(root.left, sum)) || (null != root.right && hasPathSum1(root.right, sum));
    }
}
