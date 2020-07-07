package com.leetcode.java;

public class Problem86 {
    public ListNode partition(ListNode head, int x) {
        if (null == head) return null;
        ListNode leftHead = null, leftPrev = null,
                rightHead = null, rightPrev = null;

        ListNode curNode = head;
        while (curNode != null) {
            if (curNode.val < x) {
                if (leftHead == null) {
                    leftHead = curNode;
                    leftPrev = curNode;
                } else {
                    leftPrev.next = curNode;
                    leftPrev = curNode;
                }
            } else {
                if (rightHead == null) {
                    rightHead= curNode;
                    rightPrev = curNode;
                } else {
                    rightPrev.next = curNode;
                    rightPrev = curNode;
                }
            }

            curNode = curNode.next;
        }

        if (leftHead == null) {
            rightPrev.next = null;
            return rightHead;
        } else {
            if (null != rightPrev) {
                rightPrev.next = null;
            }
            leftPrev.next = rightHead;
            return leftHead;
        }
    }
}
