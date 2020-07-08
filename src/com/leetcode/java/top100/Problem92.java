package com.leetcode.java.top100;

import com.leetcode.java.ListNode;

public class Problem92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (null == head || m == n) return head;

        ListNode curNode = head, prevNode = null;
        int count = 1;
        while (null != curNode && count < m) {
            prevNode = curNode;
            curNode = curNode.next;
            count ++;
        }

        if (null == curNode) return head;
        ListNode startNode = curNode;
        curNode = startNode.next;
        ListNode pNode = startNode, nNode = null;
        while (null != curNode && count < n) {
            nNode = curNode.next;

            curNode.next = pNode;
            pNode = curNode;
            curNode = nNode;;
            count ++;
        }

        startNode.next = nNode;
        if (null != prevNode) {
            prevNode.next = pNode;
            return head;
        } else {
            return pNode;
        }
    }
}
