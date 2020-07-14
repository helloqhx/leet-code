package com.leetcode.java.top200;

import com.leetcode.java.ListNode;

public class Problem141 {
    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) return false;

        ListNode curNode = head, nextNode = head.next;
        while (null != curNode && null != nextNode) {
            if (curNode == nextNode) return true;

            // 走一步
            curNode = curNode.next;

            // 走两步
            nextNode = nextNode.next;
            if (null == nextNode) return false;
            nextNode = nextNode.next;
        }

        return false;
    }
}
