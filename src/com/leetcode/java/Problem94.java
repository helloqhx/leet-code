package com.leetcode.java;

import java.util.*;

public class Problem94 {

    /**
     * 回溯
     * */
    public List<Integer> inorderTraversal1(TreeNode root) {
        if (null == root) return Collections.emptyList();
        List<Integer> resultList = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }

            TreeNode node = stack.pop();
            resultList.add(node.val);
            curNode = node.right;
        }

        return resultList;
    }

    /**
     * 递归
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (null == root) return Collections.emptyList();
        List<Integer> resultList = new ArrayList<>();

        subTravel(root, resultList);

        return resultList;
    }

    private void subTravel(TreeNode node, List<Integer> resultList) {

        if (node.left != null) {
            subTravel(node.left, resultList);
        }
        resultList.add(node.val);

        if (node.right != null) {
            subTravel(node.right, resultList);
        }
    }
}
