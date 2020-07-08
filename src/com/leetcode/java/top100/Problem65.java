package com.leetcode.java.top100;

public class Problem65 {
    public boolean isNumber(String s) {
        if (s.isEmpty()) return true;
        s = s.trim();
        if (s.isEmpty()) return false;

        boolean hasNumber = false, hasDot = false, hasE = false;
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '.') {
                if (hasDot || hasE) return false;
                hasDot = true;
            } else if (isDigit(c)) {
                hasNumber = true;
            } else if (c == 'e') {
                if (!hasNumber || hasE) return false;
                hasE = true;
                hasNumber = false;
            } else if (c == '-' || c == '+') {
                if (i != 0 && !(s.charAt(i - 1) == 'e')) return false;
            } else {
                return false;
            }
        }

        return hasNumber;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        Problem65 s = new Problem65();
        //"0" => true
        //" 0.1 " => true
        //"abc" => false
        //"1 a" => false
        //"2e10" => true
        //"" => true
        //" 1e" => false
        //"e3" => false
        //" 6e-1" => true
        //" 99e2.5 " => false
        //"53.5e93" => true
        //" --6 " => false
        //"-+3" => false
        //"95a54e53" => false
//        System.out.println(s.isNumber("0"));
//        System.out.println(s.isNumber(" 0.1 "));
//        System.out.println(s.isNumber("abc"));
//        System.out.println(s.isNumber("1 a"));
//        System.out.println(s.isNumber("2e10"));
//        System.out.println(s.isNumber(" -90e3   "));
//        System.out.println(s.isNumber(""));
//        System.out.println(s.isNumber(" 1e"));
//        System.out.println(s.isNumber("e3"));
        System.out.println(s.isNumber(" 6e-1"));
//        System.out.println(s.isNumber(" 99e2.5 "));
//        System.out.println(s.isNumber("53.5e93"));
//        System.out.println(s.isNumber(" --6 "));
//        System.out.println(s.isNumber("-+3"));
//        System.out.println(s.isNumber("95a54e53"));

    }
}
