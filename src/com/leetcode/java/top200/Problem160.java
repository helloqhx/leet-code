package com.leetcode.java.top200;

import com.leetcode.java.ListNode;

public class Problem160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode p1 = headA, p2 = headB;

        while (null != p1) {
            p1 = p1.next;
            lenA ++;
        }

        while (null != p2) {
            p2 = p2.next;
            lenB ++;
        }

        if (lenA > lenB) {
            int k = lenA - lenB;
            while (k > 0) {
                headA = headA.next;
                k --;
            }
        } else if (lenB > lenA) {
            int k = lenB - lenA;
            while (k > 0) {
                headB = headB.next;
                k --;
            }
        }

        while (null != headA && null != headB) {
            if (headA == headB) return headA;

            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }
}
