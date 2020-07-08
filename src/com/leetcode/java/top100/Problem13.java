package com.leetcode.java.top100;

public class Problem13 {
    public int romanToInt(String s) {
        int len = s.length();
        int sum = 0;
        for (int i = len - 1; i >= 0;) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    sum += 1;
                    i --;
                    break;
                case 'V':
                    if (i > 0 && s.charAt(i - 1) == 'I') {
                        sum += 4;
                        i -= 2;
                    } else {
                        sum += 5;
                        i --;
                    }
                    break;
                case 'X':
                    if (i > 0 && s.charAt(i - 1) == 'I') {
                        sum += 9;
                        i -= 2;
                    } else {
                        sum += 10;
                        i --;
                    }
                    break;
                case 'L':
                    if (i > 0 && s.charAt(i - 1) == 'X') {
                        sum += 40;
                        i -= 2;
                    } else {
                        sum += 50;
                        i --;
                    }
                    break;
                case 'C':
                    if (i > 0 && s.charAt(i - 1) == 'X') {
                        sum += 90;
                        i -= 2;
                    } else {
                        sum += 100;
                        i --;
                    }
                    break;
                case 'D':
                    if (i > 0 && s.charAt(i - 1) == 'C') {
                        sum += 400;
                        i -= 2;
                    } else {
                        sum += 500;
                        i --;
                    }break;
                case 'M':
                    if (i > 0 && s.charAt(i - 1) == 'C') {
                        sum += 900;
                        i -= 2;
                    } else {
                        sum += 1000;
                        i --;
                    }
                    break;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Problem13().romanToInt("III"));
        System.out.println(new Problem13().romanToInt("IV"));
        System.out.println(new Problem13().romanToInt("IX"));
        System.out.println(new Problem13().romanToInt("LVIII"));
        System.out.println(new Problem13().romanToInt("MCMXCIV"));
    }
}
