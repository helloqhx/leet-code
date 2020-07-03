package com.leetcode.java;

public class Problem108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;

        return subBuild(nums, 0, len - 1);
    }

    private TreeNode subBuild(int[] nums, int from, int end) {
        if (from < end) return null;
        if (from == end) return new TreeNode(nums[from]);
        int mid = (from + end) >>> 1;
        TreeNode midNode = new TreeNode(nums[mid]);
        midNode.left = subBuild(nums, from, mid - 1);
        midNode.right = subBuild(nums, mid + 1, end);

        return midNode;
    }
}
