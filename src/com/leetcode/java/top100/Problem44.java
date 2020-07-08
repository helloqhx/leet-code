package com.leetcode.java.top100;

class Problem44 {

    /**
     * 回溯：再碰到*号是，记录pattern和字符串的位置，当发现不能匹配时，回到上次碰到*号的位置
     * 总体意思是：*从匹配0个字符到匹配多个字符一个个试，直到完全匹配
     * */
    public boolean isMatchByBackTrace(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) return true;
        p = preprocessPattern(p);

        if (p.equals("*")) return true;

        int i = 0, j = 0, lenS = s.length(), lenP = p.length();
        int posStarP = -1, posStarS = -1;
        while (i < lenS && j <= lenP) {
            if (j == lenP) {
                if (posStarP >= 0) {
                    j = posStarP;
                    i = posStarS + 1;
                    continue;
                } else {
                    return false;
                }
            }
            char cs = s.charAt(i), cp = p.charAt(j);
            if (cp == '*') {
                posStarP = j;
                posStarS = i;
                j ++;
            } else if (cp == '?' || cp == cs) {
                i ++;
                j ++;
            } else if (posStarP >= 0){
                i = posStarS + 1;
                j = posStarP;
            } else {
                return false;
            }
        }

        if (i < lenS) {
            return p.charAt(lenP - 1) == '*';
        }
        if (j < lenP) {
            return j == lenP -1 && p.charAt(lenP - 1) == '*';
        }

        return true;
    }

    // 去除多余的星号
    private String preprocessPattern(String p) {
        StringBuilder sb = new StringBuilder();
        boolean lastIsStar = false;
        for (int i = 0, len = p.length(); i < len; i ++) {
            char c = p.charAt(i);
            if (c == '*') {
                if (!lastIsStar) {
                    sb.append(c);
                }
                lastIsStar = true;
            } else {
                lastIsStar = false;
                sb.append(c);
            }
        }

        return sb.toString();
    }
    /**
     * dp
     * dp[i][j]表示p中第0到i的模式串是否和s中0到j的子字符串是否匹配
     * 状态转化方程为：
     * 如果p[i]是'*'号，则dp[i][j] = dp[i - 1][j] || dp[i][j -1]
     * 如果不是'*'号，则dp[i][j] = dp[i - 1][j - 1] && (p.charAt(i) == s.charAt(j) || p.charAt(i) == '?')
     * */
    public boolean isMatchByDP(String s, String p) {
        p = preprocessPattern(p);
        int lenS = s.length(), lenP = p.length();
        boolean[][] dp = new boolean[lenP + 1][lenS + 1];

        dp[0][0] = true;
        // p为空，s不为空
        for (int i = 1; i <= lenS; i ++) {
            dp[0][i] = false;
        }
        // s为空, p不为空
        for (int i = 1; i <= lenP; i ++) {
            dp[i][0] = dp[i - 1][0] && p.charAt(i - 1) == '*';
        }

        for (int i = 1; i <= lenP; i ++) {
            for (int j = 1; j <= lenS; j ++) {
                if (p.charAt(i - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?');
                }
            }
        }

        return dp[lenP][lenS];
    }

    public static void main(String[] args) {
//        System.out.println(new Problem44().isMatchByDP("aa", "a"));
//        System.out.println(new Problem44().isMatchByDP("aa", "*"));
//        System.out.println(new Problem44().isMatchByDP("cb", "?a"));
//        System.out.println(new Problem44().isMatchByDP("adceb", "*a*b"));
//        System.out.println(new Problem44().isMatchByDP("acdcb", "a*c?b"));


        System.out.println(new Problem44().isMatchByBackTrace("aa", "a"));
        System.out.println(new Problem44().isMatchByBackTrace("aa", "*"));
        System.out.println(new Problem44().isMatchByBackTrace("cb", "?a"));
        System.out.println(new Problem44().isMatchByBackTrace("adceb", "*a*b"));
        System.out.println(new Problem44().isMatchByBackTrace("acdcb", "a*c?b"));
        System.out.println(new Problem44().isMatchByBackTrace("aaaaa", "****a"));
        System.out.println(new Problem44().isMatchByBackTrace("aaaaa", "a*"));
        System.out.println(new Problem44().isMatchByBackTrace("", "*"));
    }
}
