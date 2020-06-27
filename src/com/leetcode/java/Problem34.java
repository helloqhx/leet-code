package com.leetcode.java;

public class Problem34 {
    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return new int[]{-1, -1};


        return new int[]{subSearchLeft(nums, 0, len - 1, target),
                subSearchRight(nums, 0, len - 1, target)};
    }

    private int subSearchLeft(int[] nums, int from, int end, int target) {
        if (from == end) {
            int num = nums[from];
            if (num == target) {
                return from;
            } else {
                return -1;
            }
        }

        int numFrom = nums[from], numEnd = nums[end];
        if (from + 1 == end) {
            if (numFrom == target) return from;
            else if (numEnd == target) return end;
            else return -1;
        }

        int mid = from + (end - from) / 2;
        int numMid = nums[mid];
        if (target >= numFrom && target <= numMid) {
            return subSearchLeft(nums, from, mid, target);
        } else {
            return subSearchLeft(nums, mid + 1, end, target);
        }
    }

    private int subSearchRight(int[] nums, int from, int end, int target) {
        if (from == end) {
            int num = nums[from];
            if (num == target) {
                return from;
            } else {
                return -1;
            }
        }

        int numFrom = nums[from], numEnd = nums[end];
        if (from + 1 == end) {
            if (numEnd == target) return end;
            else if (numFrom == target) return from;
            else return -1;
        }

        int mid = from + (end - from) / 2;
        int numMid = nums[mid];
        if (target >= numMid && target <= numEnd) {
            return subSearchRight(nums, mid, end, target);
        } else {
            return subSearchRight(nums, from, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        //[1,2,3,3,3,3,4,5,9]
        //3
        int[] result = new Problem34().searchRange(new int[]{1,2,3,3,3,3,4,5,9}, 3);
        System.out.println(result[0] + ", " + result[1]);
    }
}
