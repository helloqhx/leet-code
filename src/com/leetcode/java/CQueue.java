package com.leetcode.java;

import java.util.Stack;

/**
 * Problem 1521
 * 用两个栈来实现队列
 * */
public class CQueue {
    // 负责入
    private Stack<Integer> stack1;
    // 负责出
    private Stack<Integer> stack2;

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            // 将stack1 中的所有pop后，push到stack2中
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) return -1;
        return stack2.pop();
    }

}
