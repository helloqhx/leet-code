package com.leetcode.java.top200;

import com.leetcode.java.ListNode;

public class Problem148 {


    /**
     * 迭代
     * */
    public ListNode sortList1(ListNode head) {
        if (null == head || head.next == null) return head;
        int size = 0;
        ListNode n = head;
        while (n != null) {
            n = n.next;
            size ++;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int i = 1; i < size; i *= 2) {
            ListNode prevNode = dummy;

            for (int j = 0; j + i < size; j += 2 * i) {
                ListNode first = prevNode.next, second = first;
                int k = 0;
                while (null != second && k < i) {
                    second = second.next;
                    k ++;
                }

                int f = 0, s = 0;
                while (f < i && s < i && second != null) {
                    if (first.val < second.val) {
                        prevNode.next = first;
                        first = first.next;
                        prevNode = prevNode.next;
                        f ++;
                    } else {
                        prevNode.next = second;
                        second = second.next;
                        prevNode = prevNode.next;
                        s ++;
                    }
                }

                while (f < i) {
                    prevNode.next = first;
                    first = first.next;
                    prevNode = prevNode.next;
                    f ++;
                }
                while (s < i && null != second) {
                    prevNode.next = second;
                    second = second.next;
                    prevNode = prevNode.next;
                    s ++;
                }

                prevNode.next = second;
            }
        }

        return dummy.next;
    }

    public ListNode sortList(ListNode head) {


        ListNode slow = head, fast = head.next;
        while (null != fast && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            fast = fast.next;
        }


        ListNode rightNode = slow.next;
        slow.next = null;

        ListNode p1 = sortList(head), p2 = sortList(rightNode);

        ListNode newHead = new ListNode(-1), prevNode = newHead;
        while(p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                prevNode.next = p1;
                p1 = p1.next;
            } else {
                prevNode.next = p2;
                p2 = p2.next;
            }

            prevNode = prevNode.next;
        }

        if (null != p1) {
            prevNode.next= p1;
        }
        if (null != p2) {
            prevNode.next = p2;
        }

        return newHead.next;
    }
}
