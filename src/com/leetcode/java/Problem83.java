package com.leetcode.java;

public class Problem83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) return null;

        ListNode prevNode = head, curNode = head.next;
        while (curNode != null) {
            if (curNode.val != prevNode.val) {
                prevNode.next = curNode;
                prevNode= curNode;
            }

            curNode = curNode.next;
        }

        prevNode.next = null;

        return head;
    }
}
