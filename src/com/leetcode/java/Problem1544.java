package com.leetcode.java;

public class Problem1544 {

    public int cuttingRope(int n) {
        if (n <= 2) return 1;
        if (n == 3) return 2;

        int a = n / 3, b = n - 3 * a;
        Long[] memo = new Long[a + 1];
        if (b == 0) return (int)(pow(a, memo) % 1000000007);
        else if (b == 1) {
            return (int) ((pow(a - 1, memo) * 4) % 1000000007);
        }
        else {
            return (int) (((pow(a, memo) % 1000000007) * 2) % 1000000007);
        }
    }

    // 计算3的n次方，并按照1e9取模
    private long pow(int k, Long[] memo) {
        if (k == 0) return 1;
        if (k == 1) return 3;
        if (null != memo[k]) return memo[k];
        int half = k / 2, left = k - half * 2;

        long halfV = pow(half, memo);

        memo[k] = (((halfV * halfV) % 1000000007) * (left == 0 ? 1 : 3)) % 1000000007;

        return memo[k];
    }

    public static void main(String[] args) {
        System.out.println(new Problem1544().cuttingRope(4));
    }
}
