package com.test;

import java.util.*;

public class Solution {

  /**
   * 1. Two sum https://leetcode.com/problems/two-sum/ Ex: Input: nums = [2,7,11,15], target = 9
   * Output: [0,1] Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
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
        return new int[] {i, indexMap.get(requiredNum)};
      }

      // if not contain -> put into Map
      indexMap.put(nums[i], i);
    }
    return new int[] {};
  }

  /**
   * 14. Longest Common Prefix https://leetcode.com/problems/longest-common-prefix/ Ex: Input: strs
   * = ["flower","flow","flight"] Output: "fl"
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
   * 1512. Number of Good Pairs https://leetcode.com/problems/number-of-good-pairs/ Ex: Input: nums
   * = [1,2,3,1,1,3] Output: 4 Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5)
   * 0-indexed.
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
   * 771. Jewels and Stones https://leetcode.com/problems/jewels-and-stones/ Ex: Letters are case
   * sensitive Input: jewels = "aA", stones = "aAAbbbb" Output: 3
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
      if (setJ.contains(item)) count++;
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
   * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/ Ex: Input:
   * nums = [8,1,2,2,3] Output: [4,0,1,1,3] Explanation: For nums[0]=8 there exist four smaller
   * numbers than it (1, 2, 2 and 3). For nums[1]=1 does not exist any smaller number than it. For
   * nums[2]=2 there exist one smaller number than it (1). For nums[3]=2 there exist one smaller
   * number than it (1). For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
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
    Map<Integer, Integer> map = new HashMap<>();
    int copy[] = nums.clone();

    Arrays.sort(copy);

    for (int i = 0; i < nums.length; i++) {
      map.putIfAbsent(copy[i], i);
    }
    for (int i = 0; i < nums.length; i++) {
      copy[i] = map.get(nums[i]);
    }
    return copy;
  }

  /**
   * 1684. Count the Number of Consistent Strings
   * https://leetcode.com/problems/count-the-number-of-consistent-strings/ Ex: Input: allowed =
   * "ab", words = ["ad","bd","aaab","baa","badab"] Output: 2 Explanation: Strings "aaab" and "baa"
   * are consistent since they only contain characters 'a' and 'b'.
   *
   * <p>Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"] Output: 7 Explanation:
   * All strings are consistent.
   *
   * @param allowed
   * @param words
   * @return
   */
  public int countConsistentStrings(String allowed, String[] words) {

    int count = words.length;
    int[] store = new int[26];

    // frequecy appear
    for (char ch : allowed.toCharArray()) {
      store[ch - 'a']++;
    }

    for (String word : words) {
      for (char ch : word.toCharArray()) {
        if (store[ch - 'a'] <= 0) {
          count--;
          break;
        }
      }
    }
    return count;
  }

  /**
   * 66. Plus One https://leetcode.com/problems/plus-one/
   *
   * <p>Input: digits = [1,2,3] Output: [1,2,4] Explanation: The array represents the integer 123.
   * Incrementing by one gives 123 + 1 = 124. Thus, the result should be [1,2,4].
   *
   * @param digits
   * @return
   */
  public int[] plusOne(int[] digits) {

    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] != 9) {
        digits[i]++;
        return digits; // chặn trên
      }
      digits[i] = 0;
    }

    int[] newDigits = new int[digits.length + 1];
    newDigits[0] = 1;

    return newDigits;
  }

  /**
   * 1832. Check if the Sentence Is Pangram
   * https://leetcode.com/problems/check-if-the-sentence-is-pangram/
   *
   * <p>A pangram is a sentence where every letter of the English alphabet appears at least once.
   *
   * <p>Given a string sentence containing only lowercase English letters, return true if sentence
   * is a pangram, or false otherwise.
   *
   * <p>Ex: Input: sentence = "thequickbrownfoxjumpsoverthelazydog" Output: true Explanation:
   * sentence contains at least one of every letter of the English alphabet.
   *
   * @param sentence
   * @return
   */
  public boolean checkIfPangram(String sentence) {
    //        Set<Character> characterSet = new HashSet<>();
    //
    //        for (Character character : sentence.toCharArray()) {
    //            characterSet.add(character);
    //        }
    //        return characterSet.size() == 26; // 26 character in English

    boolean[] letters = new boolean[26];

    for (char c : sentence.toCharArray()) {
      letters[c - 'a'] = true;
    }

    // find any letter that not exist
    for (boolean existLetter : letters) {
      if (!existLetter) return false;
    }

    return true;
  }

  /**
   * 2103. Rings and Rods https://leetcode.com/problems/rings-and-rods/
   *
   * <p>Ex: Input: rings = "B0B6G0R6R0R6G9" Output: 1 Explanation: - The rod labeled 0 holds 3 rings
   * with all colors: red, green, and blue. - The rod labeled 6 holds 3 rings, but it only has red
   * and blue. - The rod labeled 9 holds only a green ring. Thus, the number of rods with all three
   * colors is 1.
   *
   * @param rings
   * @return
   */
  public int countPoints(String rings) {
    //        int count = 0;
    //
    //        for (int i = 0; i <= 9; i++) {
    //            Set set = new HashSet(); // reset
    //            for (int j = 1; j < rings.length(); j += 2) {
    //                String c = String.valueOf(rings.charAt(j));  // index rod
    //                if (c.equals(i + "")) {
    //                    set.add(rings.charAt(j - 1));  // add color's ring (previous character)
    //                    rings = rings.replace(rings.charAt(j - 1) + rings.charAt(j) + "", ""); //
    // remove ring which color added
    //                }
    //            }
    //            if (set.size() == 3) {
    //                count++;
    //            }
    //        }
    //
    //
    //        return count;

    boolean[] red = new boolean[10];
    boolean[] green = new boolean[10];
    boolean[] blue = new boolean[10];
    for (int i = 0; i < rings.length(); i += 2) {
      char color = rings.charAt(i);
      // int num = rings.charAt(i + 1) - '0';  char to int
      int num = Integer.parseInt(String.valueOf(rings.charAt(i + 1)));
      if (color == 'R') {
        red[num] = true;
      } else if (color == 'G') {
        green[num] = true;
      } else {
        blue[num] = true;
      }
    }

    int numRings = 0;
    for (int i = 0; i < 10; i++) {
      if (red[i] && green[i] && blue[i]) {
        numRings++;
      }
    }
    return numRings;
  }

  /**
   * 1370. Increasing Decreasing String https://leetcode.com/problems/increasing-decreasing-string/
   *
   * <p>Pick the smallest character from s and append it to the result. Pick the smallest character
   * from s which is greater than the last appended character to the result and append it. Repeat
   * step 2 until you cannot pick more characters. Pick the largest character from s and append it
   * to the result. Pick the largest character from s which is smaller than the last appended
   * character to the result and append it. Repeat step 5 until you cannot pick more characters.
   * Repeat the steps from 1 to 6 until you pick all characters from s.
   *
   * @param s
   * @return
   */
  public String sortString(String s) {
    int[] freq = new int[26];

    for (char character : s.toCharArray()) {
      freq[character - 'a']++;
    }
    StringBuilder output = new StringBuilder();

    while (output.length() < s.length()) {
      for (int i = 0; i < 26; i++) {
        if (freq[i] != 0) {
          output.append((char) (i + 'a'));
          freq[i]--;
        }
      }
      for (int i = 25; i >= 0; i--) {
        if (freq[i] != 0) {
          output.append((char) (i + 'a'));
          freq[i]--;
        }
      }
    }

    return output.toString();
  }

  /**
   * 1436. Destination City https://leetcode.com/problems/destination-city/
   *
   * @param paths
   * @return
   */
  public String destCity(List<List<String>> paths) {
    //        if (paths == null || paths.size() == 0) return "";
    //        Map<String, String> map = new HashMap<>();
    //        for (List<String> path : paths) {
    //            map.put(path.get(0), path.get(1));
    //        }
    //        for (String city : map.values()) {
    //            if (!map.containsKey(city)) {
    //                return city;
    //            }
    //        }
    //        return "";

    HashSet<String> set = new HashSet<>();
    for (List<String> path : paths) {
      set.add(path.get(0));
    }
    for (List<String> path : paths) {
      if (!set.contains(path.get(1))) return path.get(1);
    }
    return "";
  }

  /**
   * 1941. Check if All Characters Have Equal Number of Occurrences
   * https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
   *
   * <p>Given a string s, return true if s is a good string, or false otherwise.
   *
   * <p>A string s is good if all the characters that appear in s have the same number of
   * occurrences (i.e., the same frequency).
   *
   * <p>Ex:
   *
   * <p>Input: s = "abacbc" Output: true
   *
   * <p>Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2
   * times in s.
   *
   * @param s
   * @return
   */
  public boolean areOccurrencesEqual(String s) {

    int[] freq = new int[26];
    Set<Character> set = new HashSet();
    // frequency occur and insert set character
    for (char character : s.toCharArray()) {
      freq[character - 'a']++;
      set.add(character);
    }

    if (set.size() != 1) {
      for (Character c : set) {
        if (freq[c - 'a']
            != freq[set.stream().findFirst().get() - 'a']) { // character occur not same other
          return false;
        }
      }
    }

    return true;
  }

  /**
   * 1748. Sum of Unique Elements
   *
   * <p>https://leetcode.com/problems/sum-of-unique-elements/
   *
   * <p>You are given an integer array nums. The unique elements of an array are the elements that
   * appear exactly once in the array.
   *
   * <p>Return the sum of all the unique elements of nums.
   *
   * <p>Input: nums = [1,2,3,2] Output: 4
   *
   * <p>Explanation: The unique elements are [1,3], and the sum is 4.
   *
   * @param nums
   * @return
   */
  public int sumOfUnique(int[] nums) {
    int sum = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
      if (map.get(nums[i]) == 1) sum += nums[i];
      else if (map.get(nums[i]) == 2) sum -= nums[i];
    }
    return sum;
  }

  /**
   * 1742. Maximum Number of Balls in a Box
   * https://leetcode.com/problems/maximum-number-of-balls-in-a-box/
   *
   * <p>Ex: Input: lowLimit = 1, highLimit = 10
   *
   * <p>Output: 2
   *
   * <p>Explanation:
   *
   * <p>Box Number: 1 2 3 4 5 6 7 8 9 10 11 ...
   *
   * <p>Ball Count: 2 1 1 1 1 1 1 1 1 0 0 ... Box 1 has the most number of balls with 2 balls.
   *
   * @param lowLimit
   * @param highLimit
   * @return
   */
  public int countBalls(int lowLimit, int highLimit) {
    Map<Integer, Integer> map = new HashMap<>();
    int count = Integer.MIN_VALUE;
    for (int i = lowLimit; i <= highLimit; i++) {
      int value = 0;
      int temp = i;
      // sum number in number
      while (temp != 0) {
        value += temp % 10;
        temp /= 10;
      }
      // freq occur
      map.put(value, map.getOrDefault(value, 0) + 1);
      // count
      count = map.get(value) > count ? map.get(value) : count;
    }
    return count;
  }

  /**
   * 2053. Kth Distinct String in an Array
   * https://leetcode.com/problems/kth-distinct-string-in-an-array/ Ex:
   *
   * <p>Input: arr = ["d","b","c","b","c","a"], k = 2
   *
   * <p>Output: "a"
   *
   * <p>Explanation:
   *
   * <p>The only distinct strings in arr are "d" and "a".
   *
   * <p>"d" appears 1st, so it is the 1st distinct string.
   *
   * <p>"a" appears 2nd, so it is the 2nd distinct string.
   *
   * <p>Since k == 2, "a" is returned.
   *
   * @param arr
   * @param k
   * @return
   */
  public String kthDistinct(String[] arr, int k) {
//    Map<String, Integer> mapFreq = new HashMap<>();
//    List<String> listString = new ArrayList<String>(Arrays.asList(arr));
//    for (String item : arr) {
//      mapFreq.put(item, mapFreq.getOrDefault(item, 0) + 1);
//      // after add Map
//      if (mapFreq.get(item) == 2) {
//        listString.removeIf(s -> s.equals(item));
//      }
//    }
//    if(k > listString.size()){
//      return "";
//    }
//
//    return listString.get(k - 1);

    Map<String, Integer> mapFreq = new HashMap<>();
    for (String s : arr) {
      mapFreq.put(s, mapFreq.getOrDefault(s, 0) + 1);
    }
    for (String s: arr){
      if (mapFreq.get(s) == 1){
        k--;
        if (k == 0)
          return s;
      }
    }
    return "";
  }
}
