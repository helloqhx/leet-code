package com.leetcode.java;

public class Problem1658 {

    /**
     * 总体思想：枚举a和b分别代表的字母个数，比如a从代表0个到到表最大个，b相应的从代表最大个，到代表0个
     * 然后分表遍历pattern和value看是否匹配
     * */
    public boolean patternMatching(String pattern, String value) {
        if (pattern.isEmpty()) return value.isEmpty();

        int aCount = 0, bCount = 0;
        int pLen = pattern.length(), vLen = value.length();
        for (int i = 0; i < pLen; i ++) {
            if (pattern.charAt(i) == 'a') aCount ++;
            else bCount ++;
        }
        if (value.isEmpty()) return aCount == 0 || bCount == 0;

        int maxANum = aCount > 0 ? vLen / aCount : 0;

        if (aCount == 0) {
            // 只包含b
            if (vLen % bCount != 0) return false;
            return matches(pattern, value, 0, vLen / bCount);
        } else if (bCount == 0) {
            // 只包含a
            if (vLen % aCount != 0) return false;
            return matches(pattern, value, vLen / aCount, 0);
        } else {
            for (int i = 0; i <= maxANum; i ++) {
                int bCharLen = vLen - (i * aCount);
                if (bCharLen % bCount == 0) {
                    if (matches(pattern, value, i, bCharLen / bCount)) return true;
                }
            }

            return matches(pattern, value, vLen, 0);
        }
    }

    private boolean matches(String pattern, String value, int aNum, int bNum) {
        int i = 0, j = 0;
        int pLen = pattern.length(), vLen = value.length();
        String aText = aNum > 0 ? null : "", bText = bNum > 0 ? null : "";

        while (i < pLen) {
            if (pattern.charAt(i) == 'a' && aNum > 0) {
                aText = equals(aText, value, j, aNum);
                if (null == aText || (null != bText && aText.equals(bText))) {
                    return false;
                } else {
                    j += aNum;
                }
            } else if (pattern.charAt(i) == 'b' && bNum > 0) {
                bText = equals(bText, value, j, bNum);
                if (null == bText || (null != aText && bText.equals(aText))) {
                    return false;
                } else {
                    j += bNum;
                }
            }
            i ++;
        }

        return j == vLen;
    }

    /**
     * @return null: 表示不匹配
     * */
    private String equals(String target, String value, int index, int len) {
        if (index + len > value.length()) return null;
        String text = value.substring(index, index + len);
        if (null == target) return text;

        return target.equals(text) ? target : null;
    }

    public static void main(String[] args) {
        System.out.println(new Problem1658().patternMatching("bbbbbbbbbbbbbbabbbbb", "ppppppppppppppjsftcleifftfthiehjiheyqkhjfkyfckbtwbelfcgihlrfkrwireflijkjyppppg"));
        System.out.println(new Problem1658().patternMatching("abba", "dogcatcatdog"));
        System.out.println(new Problem1658().patternMatching("abba", "dogcatcatfish"));
        System.out.println(new Problem1658().patternMatching("aaaa", "dogcatcatdog"));
        System.out.println(new Problem1658().patternMatching("abba", "dogdogdogdog"));
    }
}
