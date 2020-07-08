package com.leetcode.java.top100;

import com.leetcode.java.ListNode;

public class Problem61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || 0 == k || head.next == null) return head;

        // 现将链表组成一个环
        int cnt = 1;
        ListNode curNode = head;
        while (curNode.next != null) {
            curNode = curNode.next;
            cnt ++;
        }
        curNode.next = head;

        // 寻找新的链表尾
        int i = 0;
        while (i < (cnt - (k % cnt))) {
            curNode = curNode.next;

            i ++;
        }

        ListNode newHead = curNode.next;
        curNode.next = null;

        return newHead;
    }
}
