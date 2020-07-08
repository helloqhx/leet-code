package com.leetcode.java.top100;

public class Problem11 {

    /**
     * 双指针法：
     * 1. 一开始左右指针分别指向最左和最有的边界，假设左右边界的高度分别为x y，距离为t，假设x <= y，则面积为最大为x*t
     * 因为这是最边上上的两个边界，我们只能移动其中一个，如果移动较大的那个，可以证明：无论怎么移动，得到的面积不会超过x*t，因此我们只能移动较小的那个。
     * 这时，问题的规模缩小了一个，继续使用上面的思想即可。
     * */
    public int maxArea(int[] height) {
        int len = height.length;
        int i = 0, j = len - 1;
        int max = 0;
        while (i < j) {
            int left = height[i], right = height[j];
            int area;
            if (left > right) {
                area = (j - i) * right;
                j --;
            } else {
                area = (j - i) * left;
                i ++;
            }
            if (area > max) max = area;
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Problem11().maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }
}
