package com.leetcode.java.top100;

import com.leetcode.java.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem95 {
    public List<TreeNode> generateTrees(int n) {
        if (0 == n) return Collections.emptyList();

        return subGen(1, n);
    }

    public List<TreeNode> subGen(int from, int end) {
        if (from == end) return Collections.singletonList(new TreeNode(from));

        List<TreeNode> list = new ArrayList<>();
        for (int i = from; i <= end; i ++) {
            if (i > from && i < end) {
                List<TreeNode> leftList = subGen(from, i - 1);
                List<TreeNode> rightList = subGen(i + 1, end);
                for (TreeNode left: leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;

                        list.add(root);
                    }
                }
            } else if (i == from) {
                List<TreeNode> rightList = subGen(i + 1, end);

                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.right = right;

                    list.add(root);
                }
            } else if (i == end) {
                List<TreeNode> leftList = subGen(from, i - 1);
                for (TreeNode left: leftList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;

                    list.add(root);
                }
            }

        }

        return list;
    }
}
