package com.leetcode.java.top200;

public class MinStack {

    private int[] stack;
    private int[] minStack;
    private int top;
    private int capacity;

    public MinStack() {
        capacity = 16;
        stack = new int[capacity];
        minStack = new int[capacity];
        top = -1;
    }

    public void push(int x) {
        if (top == capacity - 1) {
            resize();
        }

        int min = top >= 0 ? minStack[top]: Integer.MAX_VALUE;
        ++top;
        stack[top] = x;
        minStack[top] = Math.min(x, min);
    }

    public void pop() {
        -- top;
    }

    public int top() {
        return stack[top];
    }

    public int getMin() {
        return minStack[top];
    }

    private void resize() {
        capacity = capacity * 2;
        int[] newStack = new int[capacity];
        int[] newMinStack = new int[capacity];
        System.arraycopy(stack, 0, newStack, 0, top + 1);
        System.arraycopy(minStack, 0, newMinStack, 0, top + 1);
        stack = newStack;
        minStack = newMinStack;
    }

}
