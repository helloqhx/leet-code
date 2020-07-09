package com.leetcode.java;

import java.util.*;

public class Problem1690 {

    /**
     * 字典树
     * */
    public int respace1(String[] dictionary, String sentence) {
        int len = sentence.length();
        if (0 == len) return 0;
        int dictSize = dictionary.length;
        if (0 == dictSize) return len;

        Tries root = new Tries();
        for (String word : dictionary) {
            root.putWord(word);
        }

        int[] dp = new int[len + 1];
        dp[0] = 0;

        for (int i = 1; i <= len; i ++) {
            int minCount = dp[i - 1] + 1;
            Tries cur = root;
            for (int j = i - 1; j >= 0; j --) {
                int index = sentence.charAt(j) - 'a';
                if (cur.next[index] == null) break;
                if (cur.next[index].isEnd) {
                    minCount = Math.min(minCount, dp[j]);
                }
                if (minCount == 0) break;

                cur = cur.next[index];
            }

            dp[i] = minCount;
        }

        Utils.printArray(dp);
        return dp[len];
    }

    class Trie {
        public Trie[] next;
        public boolean isEnd;

        public Trie() {
            next = new Trie[26];
            isEnd = false;
        }

        public void insert(String s) {
            Trie curPos = this;

            for (int i = s.length() - 1; i >= 0; --i) {
                int t = s.charAt(i) - 'a';
                if (curPos.next[t] == null) {
                    curPos.next[t] = new Trie();
                }
                curPos = curPos.next[t];
            }
            curPos.isEnd = true;
        }
    }

    /**
     * dp
     * dp[i] 表示 sentence[:i]的最少剩余字符数
     * dp[i] = 以之前字符结尾的所有单词中，
     * */
    public int respace(String[] dictionary, String sentence) {
        int len = sentence.length();
        if (0 == len) return 0;
        int dictSize = dictionary.length;
        if (0 == dictSize) return len;

        Set<Long> dict = new HashSet<>(dictSize);
        for (String word : dictionary) {
            dict.add(hash(word));
        }

        int[] dp = new int[len + 1];
        dp[0] = 0;

        for (int i = 1; i <= len; i ++) {

            long hash = 0;
            int minCount = dp[i - 1] + 1;
            for (int j = i - 1; j >= 0; j --) {
                hash = (hash * 31 + (sentence.charAt(j) - 'a' + 1)) % Integer.MAX_VALUE;
                if (dict.contains(hash)) {
                    minCount = Math.min(minCount, dp[j]);
                }
            }

            dp[i] = minCount;
        }

        return dp[len];
    }

    /**
     * 使用Rabin-Karp算法从后向前计算word的哈希值
     * */
    private long hash(String word) {
        long hash = 0;
        for (int i = word.length() - 1; i >= 0; i --) {
            hash = (31 * hash + (word.charAt(i) - 'a' + 1)) % Integer.MAX_VALUE;
        }

        return hash;
    }

    public static void main(String[] args) {
        System.out.println(new Problem1690().respace1(new String[]{"ioxxoooooxoiooixxoixixxioooxioixixooooiiixoxioioioixooxiiioiioxiiiiiioxoioxixxioxxioxixxxxxooooxioxoiiooiixxixooioxixxixixixooxoixoiiooxiioiiixxxxxixiiioxoxoooooxxoiioixiixxxxioioioxxoooiooiooxoiiixxoxiioiiioioxixxoxioixixxxxixxoxoiixioxioxiooiixoioioxiooooioxiioiooxiioiiixxioooxiiooxioioxxxoiixoxooiooiiixxooooxoioiixoxixxxxxxoxoioxoxioxioxixoooxioxiiioixiixooiixxoxxoiioi","iiiioxo","xixooiooxoxxxioxixxiixxoiiixoioioxxxiiiixooioixoxoiioxxoiooooxxixxoxoxoxioixiioixoxixiiiixioxooooooiioxiixiixxiiooiiooxxxxioixiixxoixxioxxxx","io","oixioiixiiooxooiooioxxixoioioxxioiiioxixoxoooxxoooixoxxixixiiooioiioxxooiixxoioxxiiioxiioooiooiixooxxoxixixioxxxoiixoxxxioxixxioxoxoxoxoixxxxiooioxiiooxoiooxxooixixoixixixiiixioooiiiiixooxiiiioxoxoixxxxixioxioxxxxoixixxiiixxi","oooioxioxxiooiiiooixiioiiiioiioxioxixioxxoxiixxiiooxxioixoooxix","xxoixxoxioiiixioooioooixiixioxxoxoxoxxxxiiiiiixiioiioxoooxxxoioxoiixxooooiiioxiiixiooioixioxiixoxoxiixooxioxoxoiixoooioiiooxiixxoxiixxxoxxxiiooxooixoxoiioxxoiooxixixixxixooiiixxxoooxxooooooxioxoxioixiiiiiiixoiiixooiioxixoioxoxixxxoxxixxoiiioxixxiiixxioxooxoixixioioxiox","oi","ox","xoox","iixixooooxiixixiioxxxxoxxioiiioixixoxxxiioxooxxioiiioxooixooixiooxxxx","xxxxooxoixiiioiioxooooxioxxxxoixixooo","ixxxiioi","xoixxx","iooiixiixiixixxxoxxxooxxooxoxxioxxo","xxioxxoxooooxioxxxxoioxoxxiixixxxixiooxxixiixiooiiooiiioooxxxxxoiooioixxooiioioiixxoioxioixoxxioiixxxoxxooxooooixixxxoxioixoxxoxoxixixiooxiioixiooooiiooooiooixxiixoioxoiioxoixixiixxoioioioiixiiioiooxoxxiiioixixooxioixoiooiiixxxixoixxixiioox","ooxxioixoixxxooooioiioxixiixoxxx","xoxixoioixiooiioixixooioox","oxxoixoxxxoxoxxoxooixooioiioo","ii","xi","oooxiooxooiiixiiiiiixoxoixioxioxixixxxxxooxixoxxixoixxxixxxooooxix","oiioooixxxooixixxoioioxooixxiixooxxoxx","oixooxxoxioioxxoooooixxiiiooxioxixooxoxioxixiooixxioxoxioiixxoooxoiooiixxiiiooxooxixiiixoioxooiixooioxoxxiiioioioxoixxiioixxoxxiixiioooioxiiooxxiiioxixiooooxxioioixxxooxxoiixoiioiooixooxxxooooooooioiioixxoioixxxiiooxooxxxxioixoxioioxxioiiioixxxioxioxxxoixxooxooxooioxoxoooooioxiixoioioooooxioioioxiixxxioxoxooiiioxixiixooxioooioxxiixoiixxixxiixioooiooooooxoxoooixxxooxioooxxxxxxiiioixxioooooxooiooiiioooxxiixoiixixiioioxoxiioxiixixoxoooxioiixiioixiioiooiooxxoiioiiiixioioxxoiioxoxixoiiooiooio","ioxiiooixxxoxioooxoiooixioiioixooxiioxioxiooiioioxxiooxxoixxoixixioiooxioioxooxooiioiioxoixxxooixiioixioixooixxoioxoiixooioixxiioxxoioxoxiioiiioxixiooixioiixoxxooiixoiixxiiioiooiixoiooioxxxxooioxoixiixiixoxoxxoxxxioiixiiioixioxxxxxoxoxiioiooxxxoxixoixxxixixoio","oixi","oi","ooxx","i","oxoiixooxx","xoioooioo","ox","ioixoixiixxxioixiooixxxxoxxxoiioxxiioxxxxixioixooxxiioxooixoooooxxxixixxxioxxoxiioiioioxxiooiiiioixiooxiooiooxooxxoooixxxxiioiixoxoooixxiixiioiiixoxxxxioiiiixoxioxxioioxxxi","oooxo","oiooiioxoxxoiooioooiioxxxooioiixxixixiixxoixxxooxixxoixoioxxoxoiixoxxoxixoxioxiooxiioiixooioioiooiixxxioxxiooxxoiioxioooxoiooxoiixooxiooixxxxooiioixoxiioooiiixxxoiixooixoiiioiixiiiiooxoooxoixxixxoiiooxxixiixoiooioooooiiiioixoixioxxixioxxiiixixxxxioxoxoiiioxixiooioxxixoxxxoxxxxxoixxoxioxoxxooxixoxxixoxoooooxooxioixxxooooxiixxxoixioxiixioxooixxxoxxoiixiioxiiixioxooooiooxoxixxooiooxoxoxxoooxxixoixooooxioxoxxxoxxixoxiixxxxox","i","oooi","xxxxixixxxxoixioioxoiioiioixxixxioiixoxoooiiooooiioxiooxixooooooxxxioxixoiixiiooixoxxxoxiooxiooxoixxiioiiooioixxxxixxxoiiiioooiiioxooxxioiiiooixoioixoxiixixxiioxixioooxiiioooxoioxxxiixiixiiiixxxiioxoooxiooxxxoioxiioooxixoiiooixioiixiioxxoixx","xooiiixxioo","oxiooo","iooxooioiiooioixoxoxxooxoxioxxxoiooxoioxioxooioooixxxixixoiixxixixxoixxoxioxooiixooxxixxoiooxxoxxixxiioxioxoxiiixixiixoxxxxioiiioooxooiiixxoixiiiixoxiixoxoiiiixooxixoxoiixixixooxioiooiioxoxxoxoxoiioxoxxioxooooxoxixxixoxiiixoixxxo","ioxoiiooixxoooioioxoioxooooxiixoiooixixoixxxiixooxixxixxixoxxxxiiixixxxioioxoixxioixixioxixoxxooxoxoxooxxoxixxoxxoioiixooixxoixixoxxooixxoiixxixxooxooixxiixiiiiioiooioixoooxoxxoxixoiixoxiiiiioooiooxxxixoooixxoixooxiiooxxxxioixxxoooiixoixxxixiiiiiio","i","ix","oxxxiixooioooixxxiiooooixoxoixooiiioiioxxxxxixxxoxxxixoiixxxxooixxioxooixiioiixioioioiooxxxxioxxxiooxiixxoox","ooxxoxiioxxxioxoxxxixxixoxxioiiiiox","ooixoxxixxxoxoixxooiixoooxxxixiiiooioooiooixiixixxioiooiooiixoiiixioiiooiiiooioioioxoioiioooiioixooxooiixiixxxixoioxoxoiixoiioooiiioiixxoxixoioioxiixoiooixxxioiixoiiiiixioixooxiixioiioooxoooxxoixixoiiioiixoiixixxxxxoxoxoxxooxoxxoiiiixxiixoiixooiiioooioxioxxxoiixxixoiooxxixixoooxoxxxixooioxixoixxxoxxxoxixooiiixiiiioooxioxxxoioxiixxxiioooxxxiiixxoxxoiixoiixxooxoioxi","oxioiiiooiioxoiooiiioxixxxxoiioxooxxxxxoxooxioiixixoioioxx","xxiiooxiiioxxxoxxxoiixi","oox","xoiiiix","oixxiooxoixooiixxiixxxoiixoxoxoooixoxxxxixoxixxixxioiioixioxxxixoioixioooxoxoiooiooioiiiiixiooixxxoxiioxxoioxio","ixxo","i","xxiixoxxiixixxoixiooooxoooooxxooxoxioooxoxxixxixioixixooixixxxioxxxxxoxooioioxxooixixxoxxxiioooioxixiixiiooioxoxixoiioooioxoiioixxioxixixoxoioixxoiiixiioixoiixooioixxiiiiixoxixxioixoxxiiiooiiixxiioioooxxoioooxixoixxxiiooio","xixooiixi","ioixxxoxiixooxiioxxiixoxxoxoxxxixiiooixixoixixxiixoiixoxoxxixxioooxoiooioxixxoiooxiiixiiioiixxoixiiixxiiixooxoixoxxiooooixoxxxoxiixxxixioooixioiixxoxixiooxoiioiixxixoxiooxxiiooxxxoxoxoioxxxooxoxoxoiooixiiiioiioixxixixixxooxoixxiiooooiiixii","oiixxxoxxixoxoooioxxoioi","oiio","xixxoixoxiixoxiioixoixxiooioxiixixixx","oxoxi","ioxxxx","iixioooooxxxooiixoxxixoxxxiioioixiiioxoixoxoxxioioxxxoixxixxxioxoiiiixxiixixxoxoxoiioxxixixxioiooooixxioioxxxxxoxiiioxiiioxxiioixioiiooxoxioxxxiixooxoxooioxxxoiooioiiiiiooxoooxoiioiixoxoxxiioiioooxiiiiixxxiiiooixxooioxxxx","oooxoooxiixioxx","ioxioooixiioooiiixoxooiooxioxiioooxxoxixioioooooxoiooxxioioxoiixxoioxxixiioixixxxoixixoxixixxixxixooioiioxxoxiixixiooxiiixxioi","ixoixoiooooxxooxixoixooiiiooxoxoxxixxxxxoxiixooxooiixxxixxoxixoxxoioiiioxxxxiooioioooiioxxxxoxoiooixoioixoiixxxiiixxxxoiiiixiioooxxiixxooixxxxoixoixxoxixoxoiioxiixxxi","xo","iooioxxoxxxioxixoooiiioxxiioxxooxoiioiooiioxoxioxoxoixxxoioiiixxxixiiiixiooxoxixxxioxixooxiioxioxxoiiixioioxixi","xooxoxooiooio","ixxoxoxoxoooooxixiooioixoixoxiiooxxoiixxioioxxiioxoooiixoxxxooxiooxoxxxxoxiixoxixixoxoxoxixiixixxiixioioxioooxixoioiixioxooxoxxoxoxiioooixoxxooiiixixooxoioxxxoxoxoixiooxixxxoixoxxxoixxioxoioioxxxxoiixixxoixoxxiixxxxiioixoxxxiiiioooxoxixoixooxiixiioxxxxoiiooioixxii","oxoxiiooioixooxxxoixiixoooxxoxxxxoioxoxxxioxxoiooixioxxixoxxxixiooioxoooiiixxooooioxxxoiixioxixxxooioxiioiixxxoxoxioiioooixxiiiooxooixoxoixiiiixiooiiixoixxoixxooiiooooiiixixoixoixioxioioxioxixoxooiooiixiiiiiiixixxoxxoooxoioixxixxioioxxxixiixxxooxiioxoixxxiiixooiixoiiixoxxxxixxiooiiiooxixxoiooxiooxxiiooooiixxxxiioxix","iiooiooxooxioxxoioioxixxioxxiooxiioiixixioxoixxoxoxxxiiooiiiixoooxxxxiixoioioixxoioiioiioioxoioiooxooioxxxoooioooxoxooxixoixiooxooxxxxxioxxxxoooxoioxxxiioxiiooxxxxiixixixoxioooxoxxooixoxioiioxoioxoixiioxooxxxoiixoixixixxoxiiioixoixxooxiooxxoiiiooooxiixixiixxxiiooiooxoixixoixooxioixioxixoxixoioiioiiixxxooxxxxoiixoiixooiioixioxxoxxxixixxioxiiiixoxoxiixoooioiixioxoioixioxoixxooixixoioiiioiixxiixoooooooxxoxxoxxixiiixxxixoxoixxiioxoiooiixxxooxxoxoixxxoxioiixioo","ixoxixxo","oixxxxixooxoixoixioxooxoiixoiooioixioxoiooooxixooxxiiiiiooixxxoixoixxixxiooioxiioxxoixxixxoioiiooioxooooixxoiiixiooiixoxoxioiiooxxixoxiioixoixxioooiixiiixxoioooxioixxioixxoxxiioioiioxxioxioiooiiiiioxoiiioixxxiiooiiooixxooixoxiiooiooiixxiixioiiiixiooxoioiixxxioo","iio","oioxoxxxxiiiiiixxoixxxiixoxoiooooiooxoxoioixxooxiiixoxooxoixoixixixioiiioxixiixxixxoioixoxixioixixoxioxioioixixoiiiiiixixoxoixixxioooiixooxxoixoixxioioxxoxxioixiooioxioioioioiiooxiixxxoxioxxoioxoooixiioxixio","ioiiiiixoixoooixooooixoooxoiooxxxxixxooxxixixiiioxxioioiooiixoiooooooixxxxoxxooxoioixixoxioxixooixooioxioxiooooxxxixioxiooxioixooxioiiixxooixxxxxxooxoooiiiiooiiiixxixixoixoxioxoixioxxiixixo","xiox","ioixiixxooxiiixooxiioxxoiixoiooxoooioxooixoxo","iiooxxxoxxixxxooixioioooxxixoxioxiiioxooiiiioiooiiooxoi","ioxxxooxxxxiiiiixixixioiixoxooxixxioixiixxiioxxixxoioiixioiioooiiixoioxiooiiiioioxixixoxxooioixioxioioiioiooixoioooiioxxxixoxiixiooxoxixiooiiioxoixxixiiooioxooixooiiioiixiiioooiioxioiiooxiioioooxioxxioixioxoooxooixioiiixooixioxoxixioxoioiiioixioxoioooxioiioixiixioiiixxxxioiiixxiiioixxxiooxooxioxxoxoooxooixxixooixixiiiioiiooixxoxoioxiooioixoxooxooxooixooiixxixixxoioixoioxiiixooxxoxoioixioixiixxiixoixoooxxixxxxoixioo","xiioxoxoiooioooxxioooixiixiooxoixxiiooixxixoiioixoxoxooxxoxiiixixiooiixoooiixooxioxixoxxoiiixooxixxxixiiioxxxiooxxoxioooxxixooiooiioxixoxxxoixxixxixixioxioxixxxoxooxxixoixooooiooxxoxixxixxxixooxoooxxoixxo","ox","oixxiixixxixioooooioixoxxoxoiiixxooxxoioxoooooxoxxiioioxoiiixiixiiioiiiiiioxiiixiiooiioooxioiiiooixxoixoxxoioixxiiixixxioiiioooioio","xxoixiioo","xiooxxixooxxxioxixxoxoooioiixooiixxxiixixxxiixooiixixioixiiiioiixooxiiiiiixxixooooxoooixiiixxixoooxoxixioxixixoiiiiixioiiiixxoiioixxxoixiioxixoxxxixxxiiiixxixxxxoxiooixoxxxoxooxoxooixxooioiiooixioxooxxxoooixoxiixixooxixoioxxxooxoixioiioxxiooxxxxixioooxiooixxxixooiioxoooxiioiixooioixoxiooxixoixxxxxxooixoxox","oxioiioixooioxxoixxi","xixxxiiiiixiixxiioiixooooiixxiixooiioxiiixxoooxxxoxioixxiixxxxoooixixixiioiiooioxoxooioxxoiooxooiixx","ixiioixixiixooxoixixixiixoiooxiiooixxoooixxoxioioixiiiiixoixixxxxxxxioxooxxixoioxixxiiixoxiixooxoixoiiixoixooooixxoioixxixxixoooxoxoxxoooooxoooxoiiiooxixooiiiiioxxoioiixioxoxxioxoxoxoixxixixoxxxoxiixiooxoixixoiixxioxxixxxxoixixixioixxixiiioxooxxxixxoxiiixxoiixoxoioioxxiioixooiixoxioxoxxoiioxooioxoioxixixoxxixoiioiiioxooixiioixxoiioxxioxooiixixxooooxxixxoioioiiiiixxoioioixxioxxxxxoxiooooioxoxiioioxiixiioxxiiiooiiiooxixoxixooooioixiiiixixooxoxxixixixoxixiiixioxoiiixooixixox","xxxoxoiixixxoixioiiiooxxooooxxixxxxoiixiixxiixooiioiiixoooxoiooiiixioxoxixxxioxiiioixxiooxiiixioixooooixixoioxxxxxoioxiioxxixooxxxxoxxxiooxooooxxixxxioxixxioxoxxiooxioxiooixxoiooxiixxxixixoioixooxxiiixxoioxoxioxxxiooioxixoxiiooxxxxoioiiiiioxoxixioiiiiioxoiixiooooioixiiioxxoioiiioxiiioxioooxioxooixiiiiiooixoxiiixxoxoixooixxioxxioooioiixixiooxiixixxoooooixxoioixixxxiioixxoxixixioooioxixioooioooxiixiooooxooxxoxxoxixoixiioiioioxxxxixooxxo","xiiixxixiioooxxixioixoxooxoiooioioioiixooxoxoiioiioixxixxxoooxioioxoiooioiioxixxoxxxxooixiiixxxoioxoxoooxioiioxxixxoooxoixixoxxxoixixxxiiiiioxoixoxiixooxoiiooxxxoxxiooixxxoxioiooiioxxooioooxxoxooioxoxxoxioxoxoiixxxooiixiioiixoixoxiixoxixooooooiiioiixxoioxoxiiioixxooxooioxiioooxiiiiiioiiixoioxixxxioixxooxxoiooxxxxoioiiooooxiixxxioxxxxioiixiixxxxiioiiixxxiioxioooooooioxoiioxooooxoiiiioooxoxioiixooioooooioxoixxoooooiixxixooixxxioiixixixxxoxoxxixiixxxxoixoiooxxiioioxixixixoxioiooixxioiooixxiixoooioii","ixoooi","ioxoxoioooiixxoioxoixiiiiooxxiioxxxoixxioxi","xiioooooioox","oo","o","xooioixx","oixxioooioixooxoixxixixooiiooxooioxixxioxiiixoixixxxoioxxxooxxxooxoiixxxooxioiooxxioxoioioooxoiooxxioixxxxooixiixixoiiioixoooixxxxoxxxoioiooixxoxxiixooixoiioioixxxoixxoixxoxxxoooioxoxixooxoiixooixxxixoii","xioooxxxxoixiooxxixix","o","oxiooxxiixixiixixxiiooixixoiiioxooxioxxiiiioiooxixxoxoiiiooixxoixoiioxiiooiooi","xxxixoxxxxiixoiioooooxxiooixxxiixxiooxxixooxiixooxixxiixixoixixxoio","xiixxiixoxoooxoixiioiioioioioxiooxxoixixoiooxixxixiioooooixoxoo","oixoixxoxioxoxxxoxoioixooiiooxxooiooiiioiioixxoiiixixxioxoxxixiiooxoiioxioiioxoxooixxxxxiixxxxxiioxoxo","iooioxoxoixxxxoioixixoxoixixioxioxoxxxxixoioxxxoooiioiiioixxoxoixoxxiio","xoxi","ixooxooooio","xixoxoxioxoixioixxoxixoxoiioxoxiiiiooxxxooxoxioxxioiiixxoxoxiixxoixioxxoixioixixoxxioxoixoxoooxoxxxioxoooiooioiixixxxixxxxiioiiiooxiooxxixoxooioixxoixxoixoxiooiixooiooixoiixoiioixxioxoioooxoxioiixoxiiioxoiiiixxiiiooioiioixixoooxixxiooioxoxoxxxooooxxiiioioiiixoxioxoioioiiioiiiiooixoixixixoxioxioxiioxioooiiioxxiooxo","oiixoioixooxxixoxoxooixoxiixoixx","iooo","iixxioiooxoioiixxoxxxxoioiioiioooioioiioiooooooixoxooxxiooxxiiooxixxxoiiixiiiixoxoxixxxoiixooioxxxxxooixooiixioixiioxxooxooioooiixooxoiooxoiixxioooooiioixxooiixioioxiooxoiioiooooooooooxxioxoioxxiixoixiooooiooiiiixiiioio","xooxxioxixoiioixxixxxxxooxixxoiooooxiooxiiixooooiioooxixoxxooxoiooixxxxoxxoiooxxoxoiixooooxoiiooxxoxiooxoixoxixiixoxooxioiiixxixoxxxxooioixoixooioiixoxixooioiiiiooxoooxioxxiooioiixiioixiiioxiiooooxixooixoxioiiiioxxxoxxixxoxiixiooooixxiooioxoxoxiiioiooxxooioiixoxoxooiiooiooxxoxxxoxioxiiixxooixxixxioxoixiooiixxooiixoioxxoxioxioiixoxxxixoixxoixioxoxoxxooxxiiiioioxixiiiixiixixioixiiiioxoiiiiooxxoxxoi","xxoi","iiixxixxioxixoooxiioooioioioxoioooxiiooooxixxxixixiooioixixoxxoxooixioixxxoxiixxixoxiii","iioi","oixioxxxoooixoooxio","iooooioxxoxixixixoiioixoiiixxxixoiiiiioxixxioxxoxixoioioiooxxxoixioiixiiiiooixixoixxooxxxoixooiixxxxoxixioxioixooiioxixxioiiixixo","ooxixioxoxxoxoxiiiioxoixoixxiixioxxixxxxxioixoxxxoioiiio","oooxxoioxiooxooiiiooxxxooxoixoxioioioixxiooixooxioixiiiooxxoooxxxxixiooooixxxixoooioixoixooiiix","iiixoxixxoioxxoixxxoixxoxiioixoxioixiixiioixxxoxioooxioioxixioixxoxiooxxixoxixiixiioiixxixoxiioiiixooiiioioxooxoxioooixoxxxxxxxoxooixxixixxioxooxiixxoixioiiooiixixxioioixxoxooxixooxxoixoxiixixioiiioxoxxoxoioixioiiixiiiooxoixxxioxixooxiixioxooioooixixoiiioooiiioioxxoiixoxxoxoxoiooooiooxxooiiiooxoiooiooxxixioxxxxxiiooiiooixxixiooxixiixoxiixxxxxoiixiioxxoxxoiiioooiiioxioxiioiixioioioxoxixxxxxoxxooioooxoiixooxxixoiiiixixoioxxiiixxxx","xxoxoooiioxxoiixoxoxiixxxixoixooxxiiixxoooxx","xiiiixxxiooixxxiixxiooxxioxixixxixoixxooiiooooooiixoxoixxi","oxxioiiixoxxooixxxixioioiiixiiiiixoxxiixxoioxxiixooixoiioxoioooxxxixxxxiooiioxoxxiixiioxiixooiixiioxooooioioxooxoooioiioxxoioxxoiiioxxoooiixoxixixxoixxixoxxixiioioixxiooxioioxiioiioixxxxxixxxooixxxixoooxxioxiixxoooioioiiixooioooooiioioxoxxiioxooxxxioooxoiiiixixooixoooxioxoxoixooxoiiiiixxxxoxoixoiioixixxxx","oioiooiixioxxoixxoxxxoxxiixoxoioxooiooxxooxxo","ixix","iiixiioxxoioiiixxxxoooxoixxoxxxoioxooxxooioxoiixixoooiooxoxxxoiioioixxixxiixoiioxoiixxioxioxooixooioiiiooooiiiioxxxxiioioxixioiixoixioxoioxioxxxoiiioxxxiioxooxoioxxoixoooxioioioioxooioxoxxioxoxoxxixxiooiioxiioooooioixooxiiioxoioiixxxixxiixxoiixixixxixxooooxoxioiioxixiiooxixxxxioooioxooooxxxiixoxoiooixxxooooooixxxoxioooxxxxxxiooooooxoxoxioxxixooxxoioixxiooixoxoxixxxxiioixioixxxoxxxixoxxoxiixxoiiixixixiiioxioixoixixoxooiiiixxxiooioooiooiioxoxioixoiioiixxixoixxxxoxxxiixoixoiooxxoxioiiixxxixxooioxoooxxxooo","i","xxioioxoxoixoxixxoiooiixioiooxooxoxooioiixooixoioooxxioioxixxoixixiioxiixoxioxxixxioxxxixooooioxoxoooixxxxoooxoxxioxiixoiioxxxioxooixixoooxooixxxxxiiioixooixxxxioxoxoxxxxxiiioiioxoixoxoxioxioiixiixoxxxoiioixoixixixxixioiiooixoiiioxooixixooioxxiixxoixixooioxixiiiooxxixxiiiixoxxxooioiixxiixxioxixoioiiiiixoxoxxxoxxioxxixoxxxooixxioxoxoooooxiiioxxxooxxooixiixoooiiiioixxoooxioiiioooxioxox"},
                "oxxoixoxxxoxoxxoxooixooioiiooxoxixxxiioixoixxxiooioxxoxxxioxixoooiiioxxiioxxooxoiioiooiioxoxioxoxoixxxoioiiixxxixiiiixiooxoxixxxioxixooxiioxioxxoiiixioioxixioooxoxooxxioxixoiioixxixxxxxooxixxoiooooxiooxiiixooooiioooxixoxxooxoiooixxxxoxxoiooxxoxoiixooooxoiiooxxoxiooxoixoxixiixoxooxioiiixxixoxxxxooioixoixooioiixoxixooioiiiiooxoooxioxxiooioiixiioixiiioxiiooooxixooixoxioiiiioxxxoxxixxoxiixiooooixxiooioxoxoxiiioiooxxooioiixoxoxooiiooiooxxoxxxoxioxiiixxooixxixxioxoixiooiixxooiixoioxxoxioxioiixoxxxixoixxoixioxoxoxxooxxiiiioioxixiiiixiixixioixiiiioxoiiiiooxxoxxoi"));
    }
}
