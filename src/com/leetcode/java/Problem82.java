package com.leetcode.java;

public class Problem82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) return null;
        int lastValue = head.val, lastCount = 1;
        ListNode curNode = head.next, prevNode = head;
        ListNode newHead = null, newPrev = null;
        while (curNode != null) {
            int value = curNode.val;
            if (value == lastValue) {
                lastCount ++;
            } else {
                if (lastCount == 1) {
                    if (newHead == null) {
                        newHead = prevNode;
                        newPrev = prevNode;
                        newPrev.next = null;
                    } else {
                        newPrev.next = prevNode;
                        newPrev = prevNode;
                    }
                }

                lastValue = value;
                lastCount = 1;
            }

            prevNode = curNode;
            curNode = curNode.next;
        }

        if (null == newHead) {
            if (lastCount > 1) return null;
            else return prevNode;
        }
        if (lastCount == 1) {
            newPrev.next = prevNode;
        } else {
            newPrev.next = null;
        }

        return newHead;
    }
}
