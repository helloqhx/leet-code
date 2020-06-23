package com.leetcode.java;

public class Problem25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        ListNode nextHead = head, nextTail = dummy;
        while (null != nextHead) {
            ListNode[] result = subReverse(nextTail, nextHead, k);
            nextTail = result[0];
            nextHead = result[1];
        }

        return dummy.next;
    }

    /**
     * @param cur: 上一次反转的最后一个节点
     * @param next: 开始节点
     * @return 返回这次的最后一个节点和下一次开始的节点，如果不足k个，则下一次开始的节点是null
     * */
    private ListNode[] subReverse(ListNode cur, ListNode next, int k) {
        ListNode prev = null, c = next, tail = next;

        while (null != c && k > 0) {
            ListNode nn = c.next;
            if (null == prev) {
                prev = c;
            } else {
                c.next = prev;
                prev = c;
            }
            c = nn;
            k --;
        }

        if (k > 0) {
            prev = doReverse(prev);
        } else {
            tail.next = null;
        }

        cur.next = prev;
        return new ListNode[]{tail, c};
    }

    private ListNode doReverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (null != cur) {
            ListNode next = cur.next;
            if (null == prev) {
                prev = cur;
            } else {
                cur.next = prev;
                prev = cur;
            }

            cur = next;
        }

        return prev;
    }
}
