package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

public class Problem124 {

    /**
     * 总体思路：后序遍历每个节点，计算把该节点包含在内的路径的最大值
     *
     * 首先，对于每个节点，在以它为根的子树中，计算以它为起点到子树中某个节点的的最大值
     * 第二步，维护一个全局的最大值，计算最大的路径和。对于每个节点计算包含该节点的路径最大值：如果子树中以每个节点的最大值为正，则加入，否则丢弃
     * */
    public int maxPathSum(TreeNode root) {
        TreeNode resultNode = new TreeNode(Integer.MIN_VALUE);

        getMaxValue(root, resultNode);

        return resultNode.val;
    }

    /**
     * 计算以该节点为起点的最大路径值
     * */
    private int getMaxValue(TreeNode root, TreeNode resultNode) {
        TreeNode leftNode = root.left, rightNode = root.right;
        int leftMax = null == leftNode ? 0 : getMaxValue(leftNode, resultNode),
                rightMax = null == rightNode ? 0 : getMaxValue(rightNode, resultNode);

        int v = root.val;
        int maxValue = v;
        if (leftMax > 0) maxValue += leftMax;
        if (rightMax > 0) maxValue += rightMax;
        if (maxValue > resultNode.val) resultNode.val = maxValue;

        return Math.max(v, Math.max(leftMax + v, rightMax + v));
    }
}
