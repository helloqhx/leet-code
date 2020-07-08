package com.leetcode.java.top100;

public class Problem12 {

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int times;
            if (num >= 1000) {
                times = num / 1000;
                copy(sb, 'M', times);
                num = num - times * 1000;
            } else if (num >= 100) {
                times = num / 100;
                build(sb, times, 'C', 'D', 'M');
                num = num - times * 100;
            } else if (num >= 10) {
                times = num / 10;
                build(sb, times, 'X', 'L', 'C');
                num = num - times * 10;
            } else {
                times = num;
                build(sb, times, 'I', 'V', 'X');
                break;
            }
        }

        return sb.toString();
    }

    private void build(StringBuilder sb, int num, char oneC, char fiveC, char tenC) {
        switch (num) {
            case 1:
                sb.append(oneC);
                break;
            case 2:
            case 3:
                copy(sb, oneC, num);
                break;
            case 4:
                sb.append(oneC);
                sb.append(fiveC);
                break;
            case 5:
                sb.append(fiveC);
                break;
            case 6:
            case 7:
            case 8:
                sb.append(fiveC);
                copy(sb, oneC, num - 5);
                break;
            case 9:
                sb.append(oneC);
                sb.append(tenC);
                break;
        }
    }


    private void copy(StringBuilder sb, char c, int times) {
        while (times > 0) {
            sb.append(c);
            times --;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Problem12().intToRoman(3));
        System.out.println(new Problem12().intToRoman(4));
        System.out.println(new Problem12().intToRoman(9));
        System.out.println(new Problem12().intToRoman(58));
        System.out.println(new Problem12().intToRoman(1994));
    }
}
