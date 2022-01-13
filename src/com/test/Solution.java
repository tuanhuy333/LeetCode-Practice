package com.test;

import java.util.*;

public class Solution {


    /**
     * 1. Two sum
     * https://leetcode.com/problems/two-sum/
     * Ex:
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
     * Ex:
     * Input: strs = ["flower","flow","flight"]
     * Output: "fl"
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixHorizontalScanning(String[] strs) {
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

    public String longestCommonPrefixVerticalScanning(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != a) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return prefix;
    }


    /**
     * 1512. Number of Good Pairs
     * https://leetcode.com/problems/number-of-good-pairs/
     * Ex:
     * Input: nums = [1,2,3,1,1,3]
     * Output: 4
     * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
     *
     * @param nums
     * @return
     */
    public int numIdenticalPairs(int[] nums) {
//        int count = 0;
//        for (int i = 0; i < nums.length; i++) {
//            //nums[i]
//            for (int j = i + 1; j < nums.length; j++) {
//                if (i < j && nums[i] == nums[j]) {
//                    count++;
//                }
//
//            }
//        }
        int c = 0, count[] = new int[101];
        for (int item : nums) {
            int fre = count[item]++;
            c += fre;
        }

        return c;
    }


    /**
     * 771. Jewels and Stones
     * https://leetcode.com/problems/jewels-and-stones/
     * Ex:
     * Letters are case sensitive
     * Input: jewels = "aA", stones = "aAAbbbb"
     * Output: 3
     *
     * @param jewels
     * @param stones
     * @return
     */
    public int numJewelsInStones(String jewels, String stones) {
        int count = 0;
        Set setJ = new HashSet();
        for (char item : jewels.toCharArray()) {
            setJ.add(item);
        }
        for (char item : stones.toCharArray()) {
            if (setJ.contains(item))
                count++;
        }
//        for (int i = 0; i < stones.length(); i++) {
//            for (int j = 0; j < jewels.length(); j++) {
//                if(stones.charAt(i) == jewels.charAt(j)){
//                    count++;
//                }
//            }
//
//        }
        return count;
    }


    /**
     * 1365. How Many Numbers Are Smaller Than the Current Number
     * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
     * Ex:
     * Input: nums = [8,1,2,2,3]
     * Output: [4,0,1,1,3]
     * Explanation:
     * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
     * For nums[1]=1 does not exist any smaller number than it.
     * For nums[2]=2 there exist one smaller number than it (1).
     * For nums[3]=2 there exist one smaller number than it (1).
     * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
//        int[] result = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            int count = 0;
//            for (int j = 0; j < nums.length; j++) {
//                if (i == j) continue;
//                if (nums[j] < nums[i]) {
//                    count++;
//                }
//            }
//            result[i] = count;
//        }
//
//        return result;

        // ANOTHER WAY
        // https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/discuss/535421/Java-Clean-HashMap-solution-with-explanation
        Map<Integer,Integer> map = new HashMap<>();
        int copy[] = nums.clone();

        Arrays.sort(copy);

        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(copy[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            copy[i] = map.get(nums[i]);
        }
        return copy;

    }

}
