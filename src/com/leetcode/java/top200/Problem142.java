package com.leetcode.java.top200;

import com.leetcode.java.ListNode;

public class Problem142 {
    public ListNode detectCycle(ListNode head) {
        if (null == head || null == head.next) return null;

        // 找到第一个在环上的节点
        ListNode targetNode = null;
        ListNode p1 = head, p2 = head.next;
        while (null != p1 && null != p2) {
            if (p1 == p2) {
                targetNode = p1;
                break;
            }

            p1 = p1.next;

            p2 = p2.next;
            if (null == p2) break;
            p2 = p2.next;
        }

        //  没有找到环
        if (null == targetNode) return null;

        ListNode headNode = head, circleNode = targetNode.next;

        while (headNode != circleNode) {
            headNode = headNode.next;
            circleNode = circleNode.next;
        }

        return headNode;
    }
}
