package com.test;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s = "zpfupfkmsuistzmtkijj";
        String[] arr = {"d","b","c","b","c","a"};
        int[] nums = {1,2,3,2,6,1,4,4,3,4,2,4};

        String result = solution.kthDistinct(arr,2);
    }
}
