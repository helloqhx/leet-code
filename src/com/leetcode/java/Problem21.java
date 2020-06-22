package com.leetcode.java;

public class Problem21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) return l2;
        if (null == l2) return l1;
        ListNode head = null, cur = null;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (null == head) {
                    head = cur = l1;
                } else {
                    cur.next = l1;
                    cur = cur.next;
                }
                l1 = l1.next;
            } else {
                if (null == head) {
                    head = cur = l2;
                } else {
                    cur.next = l2;
                    cur = cur.next;
                }
                l2 = l2.next;
            }
        }

        cur.next = null == l1 ? l2 : l1;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
