package com.test;

import java.util.HashMap;

public class Solution {


    /**
     * 1. Two sum
     * https://leetcode.com/problems/two-sum/
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            Integer requiredNum = (Integer) (target - nums[i]);

            if (indexMap.containsKey(requiredNum)) {
                return new int[]{i, indexMap.get(requiredNum)};
            }

            // if not contain -> put into Map
            indexMap.put(nums[i], i);


        }
        return new int[]{};
    }

    /**
     * 14. Longest Common Prefix
     * https://leetcode.com/problems/longest-common-prefix/
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                System.out.println("index first: " + strs[i].indexOf(prefix));
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

}
