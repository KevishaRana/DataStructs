package org.example;

import java.util.Arrays;

public class Random {
    public static void main(String[] args) {
        int[] arr = {1, 1, -5, 3, 7, -2, -5, -6, -7, 2};
        System.out.println("Amax subarray sum of kedans: " + kedansAlgorithmMaxSubArraySum(arr));

        int[] stock = {2, 3, 1, 3, 6, 2, 1};
        System.out.println("buy and sell stocks: " + buyAndSellOneStock(stock));

        System.out.println("buy and sell stocks min so far: " + minSoFar(stock));

        int[] books ={10,20,30,40,50};
        System.out.println("Allocating books Brute Force: " +  allocatioBooksBruteForce(books,0,3));

    }

    public static int kedansAlgorithmMaxSubArraySum(int[] arr) {
        int maxSum = 0;
        int currSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currSum = currSum + arr[i];
            if (currSum > maxSum) {
                maxSum = currSum;
            }
            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }

    public static int buyAndSellOneStock(int[] arr) {
        int[] maxArray = new int[arr.length];
        int max = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (max < arr[i]) {
                max = arr[i];
            }
            maxArray[i] = max;
        }
        System.out.println(Arrays.toString(maxArray));
        int profitMax = 0;
        max = 0;
        for (int i = 0; i < arr.length; i++) {
            profitMax = maxArray[i] - arr[i];
            max = Math.max(profitMax, max);
        }
        return max;
    }

    //min so far another way
    public static int minSoFar(int[] arr) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(arr[i], min);
            int profit = arr[i] - min;
            maxProfit = Math.max(profit, maxProfit);

        }
        return maxProfit;
    }

    public static int allocatioBooksBruteForce(int[] arr, int currIndex, int numberStudents) {

        if(numberStudents >arr.length-currIndex){
            return -1;
        }
        if(numberStudents == 1){
            return totalPages(arr, currIndex, arr.length -1);
        }
        if(arr.length-1 == currIndex){
            return arr[currIndex];
        }
        int minimumNumerPages = Integer.MAX_VALUE;

        // for student i curr =0, next curr 1, next curr 2.....
        for(int nextStudent = currIndex+1; nextStudent <arr.length; nextStudent++) {
            int firstStudentPages = totalPages(arr, currIndex, nextStudent - 1);
            //no. of students will be 1 less as first student has pages
            int nextStudentsCountPages = allocatioBooksBruteForce(arr, nextStudent, numberStudents - 1);

            // FIX 2: Only compare if the path was actually valid (not MAX_VALUE)
            if (nextStudentsCountPages != -1) {
                int heaviestLoad = Math.max(firstStudentPages, nextStudentsCountPages);
                minimumNumerPages = Math.min(minimumNumerPages, heaviestLoad);
            }



        }
        // THE CRITICAL CHECK:
        // If minimumNumerPages is still Integer.MAX_VALUE, it means NO valid
        // split was found in the loop. We must return -1 to the caller.
        return (minimumNumerPages == Integer.MAX_VALUE) ? -1 : minimumNumerPages;

    }

    private static int totalPages(int[] arr, int start, int end) {
        int total =0;
        for (int i =start; i<=end; i++){
            total += arr[i];
        }
        return total;
    }


}


