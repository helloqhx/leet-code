package com.leetcode.java;

public class Tries {
    public Tries[] next;
    public boolean isEnd;

    public Tries() {
        next = new Tries[26];
        isEnd = false;
    }

    /**
     * 逆向的
     * */
    public void putWord(String word) {
        Tries cur = this;
        for (int i = word.length() - 1; i >= 0; i --) {
            int index = word.charAt(i) - 'a';

            if (null == cur.next[index]) {
                cur.next[index] = new Tries();
            }
            cur = cur.next[index];
        }

        cur.isEnd = true;
    }

    public boolean contains(String word) {
        Tries cur = this;
        for (int i = word.length() - 1; i >= 0; i --) {
            int index = word.charAt(i) - 'a';

            if (null == next[index]) return true;
            cur = next[index];
        }

        return cur.isEnd;
    }
}
