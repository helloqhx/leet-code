package com.leetcode.java;

import java.util.HashSet;
import java.util.Set;

public class Problem1591 {

    public ListNode removeDuplicateNodes(ListNode head) {
        if (null == head) return null;
        Set<Integer> set = new HashSet<>();

        ListNode prev = null, cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                cur = cur.next;
            } else {
                set.add(cur.val);
                if (null != prev) {
                    prev.next = cur;
                }

                prev = cur;
                cur = cur.next;
            }
        }
        prev.next = null;

        return head;
    }
}
