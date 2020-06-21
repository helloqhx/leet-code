package com.leetcode.java;

public class Problem19 {

    /**
     * 1->2->3->4->5 删除倒数第2个
     * 1->2->3->5
     * */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prevNode = null, curNode = head;
        while (n > 1) {
            prevNode = curNode;
            curNode = curNode.next;
            n --;
        }
        if (curNode.next == null) {
            // 删除的是头节点
            return head.next;
        }

        ListNode nextHead = head;
        while (null != curNode.next) {
            prevNode = nextHead;
            curNode = curNode.next;
            nextHead = nextHead.next;
        }

        prevNode.next = nextHead.next;

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }
}
