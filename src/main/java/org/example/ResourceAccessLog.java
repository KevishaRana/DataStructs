package org.example;

import java.util.HashSet;
import java.util.Set;

public class ResourceAccessLog {
    public static void main(String[] args) {
        int[][] board = {
                {1, 2, 3},
                {2, 3, 1},
                {3, 1, 2}
        };
       System.out.println("is this valid: "+isValidd(board));
        int[][] board9 = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
       boolean isValid = isSubValid(board9);
        System.out.println("is this valid9 : "+isValid);

        char[][] grid = {
                {'c', 'a', 't'},
                {'a', 't', 'e'},
                {'t', 'e', 's'}
        };
        String word = "cat";
        boolean result = findWord(grid, word);
        System.out.println("is wordd  find : "+result);

    }

    private static boolean findWord(char[][] grid, String word) {
        if(grid.length ==0 || grid == null){
            return true;
        }
        for(int i=0;i<grid.length;i++){
            for(int j=0;j< grid.length;j++){
                if(wordFound(grid,word,i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean wordFound(char[][] grid, String word, int i, int j, int index) {
        if(i>= grid.length|| j>= grid[0].length || grid[i][j] != word.charAt(index)){
            return false;
        }
        if(index == word.length()-1){
            return true;
        }
        boolean left = wordFound(grid,word,i+1, j, index+1);
        boolean right = wordFound(grid,word,i, j+1, index+1);
        return left||right;
    }

    private static boolean isSubValid(int[][] board9) {
        int boxSize = 3;
        // 1. These two loops jump to the top-left corner of each 3x3 box
        for (int rowOffset = 0; rowOffset < 9; rowOffset += boxSize) {
            for (int colOffset = 0; colOffset < 9; colOffset += boxSize) {

                // 2. These two loops check the 9 numbers inside that specific box
                Set<Integer> boxSet = new HashSet<>();
                for (int r = 0; r < boxSize; r++) {
                    for (int c = 0; c < boxSize; c++) {
                        int val = board9[rowOffset + r][colOffset + c];

                        if (val != 0 && !boxSet.add(val)) {
                            return false; // Duplicate found in the 3x3 box
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isValidd(int[][] board) {
        for(int i=0; i<board.length;i++){
            Set<Integer> set = new HashSet<>();
            for(int row =0; row<board.length;row++){
                int val = board[i][row];
                if(val<1||val>board.length|| !set.add(val)){
                    return false;
                }
            }
        }
        for(int i=0; i<board.length;i++){
            Set<Integer> set = new HashSet<>();
            for(int row =0; row<board.length;row++){
                int val = board[row][i];
                if(val<1||val>board.length|| !set.add(val)){
                    return false;
                }
            }
        }
        return true;
    }
}
