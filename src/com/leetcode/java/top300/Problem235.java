package com.leetcode.java.top300;

import com.leetcode.java.TreeNode;

public class Problem235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        int pv = p.val, qv = q.val;
        while (null != cur) {
            int val = cur.val;
            if ((pv < val  && val < qv) || (qv < val && val < pv)) return cur;
            else if (pv == val || qv == val) return cur;
            else if (pv < val && qv < val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        return cur;
    }
}
