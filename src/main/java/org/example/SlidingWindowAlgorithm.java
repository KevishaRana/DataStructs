package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlidingWindowAlgorithm {
    public static void main(String[] args) {
        int[] test = new int[]{1, 2, 3, 4, 6, 8, 4, 7, 11, 9, 5};
        int target = 10;

        List<int[]> result = getAllSubarrays(test, target);

        for (int[] arr : result) {
            // Use Arrays.toString to avoid IndexOutOfBounds for different lengths
            System.out.println(Arrays.toString(arr));
        }


    }
    public static List<int[]> getAllSubarrays(int[] nums, int k) {
        List<int[]> result = new ArrayList<>();
        int windowSum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];

            // For "All", we check the sum.
            // Note: This logic only works perfectly for positive integers!
            while (windowSum >= k) {
                if (windowSum == k) {
                    result.add(Arrays.copyOfRange(nums, left, right + 1));
                }
                windowSum -= nums[left];
                left++;
            }
        }
        return result;
    }

}
