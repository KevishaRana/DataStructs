package org.example;

import java.util.ArrayList;
import java.util.List;

public class dynamicProgramming {
    public static void main(String[] args) {
        System.out.println("Fabonnaci series of 4: "+fabonnacii(4));

        System.out.println("stepCount for stairs of 10: "+stepCount(10));

        //count ways to spend a coins
        int[] coins = {1, 2, 3};
        int amount = 4;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        int totalWays = countWays(coins, amount, 0, result, current);
        System.out.println("Total ways: " + totalWays);
        System.out.println("Combinations:");
        for (List<Integer> combo : result) {
            System.out.println(combo);
        }

        //max path count
        int[][] grid = {
                {5,3,2},
                {1,9,1},
                {0,2,3}
        };

        System.out.println("Max Path Sum: " + maxPathCount(grid, 0, 0));


    }

    public static int fabonnacii(int n){
        if (n==0) return 0;
        if(n==1) return 1;
        return fabonnacii(n-1) + fabonnacii(n-2);
    }

    public static int stepCount(int steps){
        //p[erson can take 1 step or two steps at a time
        if(steps ==1) return 1;
        if(steps == 2) return  2;
        return stepCount(steps-1)+stepCount(steps-2);
    }

    //coin use problem
//    coins = [1,2,3], amount = 4
//
//    Ways:
//            1+1+1+1
//            1+1+2
//            1+3
//            2+2
//
//    Total = 4 ways

    public static int countWays(int[] coins, int amount, int index, List<List<Integer>> list, List<Integer> current){
        if(index >= coins.length  ||amount < 0){
            System.out.println("invalid amount");
            return 0;
        }
        // Found a combination
        if(amount == 0){
            list.add(new ArrayList<>(current));
            return 1;
        }
        int count =0;
        //add coin
        current.add(coins[index]);
        // smae index cause can use 1 mulitple timess 1+1+1..
        count += countWays(coins, amount-coins[index],index,list,current);
        current.remove(current.size() -1);

        //skip coin
        count += countWays(coins, amount,index+1,list,current);

        return count;
    }

    public static int maxPathCount(int[][] grid, int row, int col){

        //if we reach at last return grid
        if (row== grid.length-1 && col == grid[0].length-1 ){
            return grid[row][col];
        }
        //invalid
        if (row > grid.length-1 || col >grid[0].length-1 ){
            return Integer.MIN_VALUE;
        }
        // every value has two ways , either go right, or go down |---
        int right = maxPathCount(grid, row+1, col);
        int down = maxPathCount(grid, row, col+1);


        // now whichever has higest value, wee need to take that path
        //so current value + max among both paths
        return grid[row][col] + Math.max(right,down);
    }

}
