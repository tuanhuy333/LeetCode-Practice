package com.test;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String allowed = "abc";
        String[] words = {"a","b","c","ab","ac","bc","abc"};

        int result = solution.countConsistentStrings(allowed,words);
    }
}
