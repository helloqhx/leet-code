package com.leetcode.java.top100;

import com.leetcode.java.ListNode;

public class Problem24 {

    public ListNode swapPairs(ListNode head) {
        ListNode h = null, cur = null, next = head;
        while (next != null) {
            ListNode first = next, second = first.next;
            if (null == second) {
                next = null;
                if (null == h) {
                    h = cur = first;
                } else {
                    cur.next = first;
                    cur = cur.next;
                }
            } else {
                next = second.next;
                second.next = first;
                first.next = null;

                if (null == h) {
                    h = second;
                    cur = first;
                } else {
                    cur.next = second;
                    cur = first;
                }
            }
        }

        return h;
    }
}
