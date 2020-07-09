package com.leetcode.java.top200;

import com.leetcode.java.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        if (len == 0) return null;

        Map<Integer, Integer> map = new HashMap<>(len);
        for (int i = 0; i < len; i ++) {
            map.put(inorder[i], i);
        }

        return subBuild(preorder, 0, len - 1, map, 0, len - 1);
    }

    private TreeNode subBuild(int[] preorder, int preFrom, int preEnd, Map<Integer, Integer> map, int inFrom, int inEnd) {
        int val = preorder[preFrom];

        TreeNode root = new TreeNode(val);
        int i = map.get(val);

        int leftLen = i - inFrom, rightLen = inEnd - i;

        if (leftLen > 0) {
            root.left = subBuild(preorder, preFrom + 1, preFrom + leftLen, map, inFrom, i - 1);
        }

        if (rightLen > 0) {
            root.right = subBuild(preorder, preFrom + leftLen + 1, preEnd, map, i + 1, inEnd);
        }


        return root;
    }
}
