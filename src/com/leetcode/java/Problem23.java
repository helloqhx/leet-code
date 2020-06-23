package com.leetcode.java;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem23 {

    // 优先级队列
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(len, Comparator.comparingInt(o -> o.val));

        for (int i = 0; i < len; i ++) {
            if (null != lists[i]) {
                queue.offer(lists[i]);
            }
        }
        if (queue.size() == 0) return null;

        ListNode head = null, cur = null;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            if (head == null) {
                head = cur = node;
            } else {
                cur.next = node;
                cur = node;
            }
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return head;
    }


    // 分治
    public ListNode mergeKListsByDivAndMerge(ListNode[] lists) {
        int len = lists.length;
        if (len == 0) return null;
        if (len == 1) return lists[0];

        return subMerge(lists, 0, len - 1);
    }

    public ListNode subMerge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        if (left + 1 == right) return mergeTwo(lists[left], lists[right]);

        int middle = (left + right) / 2;
        ListNode leftNode = subMerge(lists, left, middle), rightNode = subMerge(lists, middle + 1, right);

        return mergeTwo(leftNode, rightNode);
    }

    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        ListNode head = null, cur = null;
        while (null != l1 || null != l2) {
            ListNode node;
            if (null == l1 || l1.val > l2.val) {
                node = l2;
                l2 = l2.next;
            } else {
                node = l1;
                l1 = l1.next;
            }
            if (null == head) {
                head = cur = node;
            } else {
                cur.next = node;
                cur = node;
            }
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
