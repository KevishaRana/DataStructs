package org.example;

import java.util.*;

public class backtracking {
    public static void main(String[] args) {
        int result = powerRecursion(3,4);
        System.out.println("power Recursion 3^4: "+result);
        // no of ways in matrix
        System.out.println("no. of ways in matrix 3x4: "+numberOfWaysInMatrix(3,4));
        //is palindrom by recursion
        String palindromtest= "abcdcba";
        System.out.println("isPalindrom abcdcba: "+isPalindrom(palindromtest, 0,palindromtest.length()-1));

        palindromtest= "abcda";
        System.out.println("isPalindrom abcda: "+isPalindrom(palindromtest, 0,palindromtest.length()-1));

        //subsets
        subsets("ABC", "",0);

        //permutation
        permutation("ABC", 0, 2);
        permutationWithLimit("ABC", 0, 2, 2);

        //combination
        combination("ABC","", 0, "ABC".length(), 2);
        //n-Q
        arrangeQueen(new int[4][4], 0,4);

        // target-subsets
        int array[] = {1,4,5,8,3,4,1,2,7,4,2,3,6,1,2,1,3};
        Arrays.sort(array);
        targetSubsets(array,9,0,0,new ArrayList<>());

        //rat maze
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        System.out.println("Maze Track: "+ratMazeTrack(maze, maze.length));

        //word search
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(exist(board, "ABCCED")); // true
        System.out.println(exist(board, "SEE"));    // true
        System.out.println(exist(board, "ABCB"));   // false

        //phone number
        String digits = "23";
        List<String> combos = letterCombinations(digits);
        System.out.println(combos);

    }
    static int powerRecursion(int number, int power){
        if (power == 0){
            return 1;
        }
        int result =1;
       result = number * powerRecursion(number, power-1);
       return result;
    }

    //number of ways in an array
    static int numberOfWaysInMatrix(int n, int m){
        if(n == 1 || m == 1){
            return 1;
        }
        return numberOfWaysInMatrix(n-1,m)+ numberOfWaysInMatrix(n,m-1);
    }
    //palindrom check by recursion
    static boolean isPalindrom(String s, int left, int right){
        if(left>=right){
            return true;
        }
        if(s.charAt(left) != s.charAt(right)){
            return  false;
        }else{
          return  isPalindrom(s, left+1,right-1);
        }
    }
    //subset/subsequence of given string
    static void subsets(String s, String current, int index){
        if(index == s.length()){
            System.out.println("substring: "+current);
            return;
        }
        // we can do by for loop as weel , and we don't need to write exclude part the
        //cause for loop wil be exclude part
        // 2. The loop starts from the current index to the end
//        for (int i = index; i < s.length(); i++) {
//
//            // INCLUDE: Add the character at the current loop position i
//            // RECURSE: Move to i + 1 (the next available characters)
//            subsets(s, current + s.charAt(i), i + 1);
//
//            // EXCLUDE: This happens automatically!
//            // When this recursive call finishes, the loop moves to i + 1.
//            // It effectively "skips" the character at i and tries starting with i + 1.
//        }


        //add value ABC, BC
        subsets(s,current+s.charAt(index),index+1);
        //dont add , A, B, C
        subsets(s, current,index+1);
    }

    //permutation
//    The "Funda":
//    First Swap: "Let's try this change."
//    Recursion: "Explore everything with this change."
    // ABC, BCA...
//    Second Swap: "Put it back the way I found it so I can try something else."

    static void permutation(String s, int left, int right){
        if (left == right){
            System.out.println("Permutation: "+s);
            return;
        }
        // for loop to get all combinations
        for (int i = left; i<=right; i++){
            //swap a with a, a with b , a with c and return as string is immutable
            s = swap(s, left, i);
            // increase index to reach to left = right
            permutation(s, left+1, right);
            //swap back for finding other combinations
            s = swap(s, left, i);
        }

    }
    static String swap(String s, int left, int right){
        char[] sequence = s.toCharArray();
        char temp = sequence[left];
        sequence[left] = sequence[right];
        sequence[right] = temp;
        return String.valueOf(sequence);
    }

    // permutation with fix size lets say two AB, AC
    static void permutationWithLimit(String s, int left, int right, int k){
        if (left == right){
            System.out.println("Permutation with limit: "+s.substring(0,k));
            return;
        }
        // for loop to get all combinations
        for (int i = left; i<=right; i++){
            //swap a with a, a with b , a with c and return as string is immutable
            //pick
            s = swap(s, left, i);
            // increase index to reach to left = right
            //carry
            permutationWithLimit(s, left+1, right,k);
            //swap back for finding other combinations
            //backtrack
            s = swap(s, left, i);
        }

    }
//  combination
// no swap, no go back

public static void combination(String s, String curr,int left, int right, int k){
        if(curr.length() == k){
            System.out.println("combination: "+curr.toString());
            return;
        }

        for (int i = left; i<right; i++){
            //pick -> place
            String currNext = curr + s.charAt(i);
            //carry -> move to next
            combination(s,currNext,i+1, right,k);
            // as string holds parent copy, we don't need backtrack.
            // but incase string builder/buffer we do need it
        }
//    private void backtrack(List<String> result, String digits,
//            Map<Character, String> map,
//    int index, StringBuilder current) {
//
//        // Base case
//        if (index == digits.length()) {
//            result.add(current.toString());
//            return;
//        }
//
//        String letters = map.get(digits.charAt(index));
//
//    for (char c : letters.toCharArray()) {
//        current.append(c);              // choose
//        backtrack(result, digits, map, index + 1, current); // explore
//        current.deleteCharAt(current.length() - 1); // un-choose
//    }
}

// N-q Problem
public static boolean arrangeQueen(int[][] board, int row, int queens){
        if (queens == row){
            // to print two d array but in one string
            //[[0, 1, 0, 0], [0, 0, 0, 1], [1, 0, 0, 0], [0, 0, 1, 0]]
           // System.out.println(Arrays.deepToString(board));

            // or can use advance for loop
            for (int[] rowss:board) {
                System.out.println(Arrays.toString(rowss));

            }

            //or by lambda
//            Arrays.stream(board)
//                    .map(Arrays::toString)
//                    .forEach(System.out::println);
            return true;
        }
        for (int col=0; col<queens;col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                if (arrangeQueen(board, row + 1, queens)) {
                    return true;
                }
                board[row][col] = 0;

            }
        }
        return false;
}

    private static boolean isSafe(int[][] board, int row, int col) {
        //check each row for same col
        for(int i=row; i >= 0; i--) {
            if(board[i][col] == 1) {
                return false;
            }
        }
        //go upward left diagonally
        for (int i = row-1, j= col-1; i>=0 && j>=0; i--, j--) {
            if(board[i][j]== 1) {
                return false;
            }
        }
        //go downwards left diagonally
        for (int i= row+1,j=col-1; i<board.length && j>=0; i++,j--) {
            if(board[i][j]== 1) {
                return false;
            }
        }
        return true;
    }

//all sebsets of given sum
public static void targetSubsets(int[] array, int target, int index, int currentSum, List<Integer> list){
        if(currentSum == target){
            System.out.println("subset list:"+list);
            return;
        }
        if(array.length == index || currentSum > target){
            return;
        }
//        //include
//    list.add(array[index]);
//    targetSubsets(array,target,index+1,currentSum+array[index],list);
//
//        //exclude
//    list.remove(list.size() -1);
//    targetSubsets(array, target, index+1,currentSum,list);

    // or by for loop
    for(int i =index; i<array.length;i++){
        //need to sort array for this
        if(i>index && array[i] == array[i-1]){
            //skip if the previous eelment same as next and it has beeen used
            //to prevent duplicte results

            continue;
        }

        list.add(array[i]);
        targetSubsets(array,target,index+1,currentSum+array[i],list);
        list.remove(list.size()-1);
    }
}

// rat maze
public static List<String> ratMazeTrack(int[][] maze, int n) {
    List<String> result= new ArrayList<>();
        if(maze.length == 0 ){
            return result ;
        }
        StringBuilder path = new StringBuilder();
        boolean visited[][] = new boolean[n][n];

        mazeBacktrack(maze, 0, 0,path, result,n, visited);

        return result;
}

    private static void mazeBacktrack(int[][] maze, int row, int col, StringBuilder path, List<String> result, int n, boolean visited[][]) {
        if (row == n-1 && col == n-1){
            result.add(path.toString());
            return;
        }


        //              up, right, down , left
        int[] rowPath = {-1, 0, 1, 0};
        int[] colPath = {0, 1, 0, -1 };
        char[] move ={'U','R', 'D', 'L'};
        for (int i=0; i<rowPath.length;i++){
            // before we used to do row + index, same like that
            int newRow = row + rowPath[i];
            int newCol = col + colPath[i];
            if(isSafeToMove(maze, newRow, newCol, visited, n )){
                //make vistited true
                visited[newRow][newCol]= true;
                path.append(move[i]);
                mazeBacktrack(maze, newRow, newCol,path, result, n , visited);
                path.deleteCharAt(path.length() -1);
                // unmask visted
                visited[newRow][newCol]= false;
            }

        }



        //pick
    }

    private static boolean isSafeToMove(int[][] maze, int row, int col, boolean[][] visited, int n) {
        if(row<0 || col< 0 || row >maze.length-1 ||col>maze.length -1){
            //touching edges or going out of bouundries
            return  false;
        }
        if(maze[row][col] == 0){
            //maxze block
            return false;
        }
        if(visited[row][col]){
            return false;
        }
        return  true;
    }


//knight chesss
private static void knightBacktrack(int n, int row, int col,
                                    int moveCount, boolean[][] visited,
                                    List<String> result, StringBuilder path) {

    // Base case: if we’ve visited all cells (Knight’s Tour)
    if (moveCount == n * n) {
        result.add(path.toString());
        return;
    }

    // All 8 knight moves
    int[] rowMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};
    char[] moveDir = {'A','B','C','D','E','F','G','H'}; // optional symbolic move names

    for (int i = 0; i < 8; i++) {
        int newRow = row + rowMoves[i];
        int newCol = col + colMoves[i];

        if (isSafeToMoveKnight(newRow, newCol, visited, n)) {
            visited[newRow][newCol] = true;      // ✅ mark
            path.append(moveDir[i]);             // optional path tracking

            knightBacktrack(n, newRow, newCol, moveCount + 1, visited, result, path);

            path.deleteCharAt(path.length() - 1); // 🔁 unmark path
            visited[newRow][newCol] = false;      // 🔁 unmark cell
        }
    }
}

// Utility to check bounds and visited
private static boolean isSafeToMoveKnight(int r, int c, boolean[][] visited, int n) {
    return r >= 0 && c >= 0 && r < n && c < n && !visited[r][c];
}

//----- word search-----

public static boolean exist(char[][] board, String word) {
    int rows = board.length;
    int cols = board[0].length;

    // Quick pre-check: character frequency
    if (!canFormWord(board, word)) return false;

    // Try starting from every cell
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            if (backtrack(board, word, i, j, 0)) {
                return true;
            }
        }
    }
    return false;
}

    private static boolean canFormWord(char[][] board, String word) {
        Map<Character, Integer> boardCount = new HashMap<>();
        Map<Character, Integer> wordCount = new HashMap<>();

        for (char[] row : board) {
            for (char c : row) {
                boardCount.put(c, boardCount.getOrDefault(c, 0) + 1);
            }
        }

        for (char c : word.toCharArray()) {
            wordCount.put(c, wordCount.getOrDefault(c, 0) + 1);
        }

        for (char c : wordCount.keySet()) {
            if (boardCount.getOrDefault(c, 0) < wordCount.get(c)) {
                return false;
            }
        }
        return true;
    }

private static boolean backtrack(char[][] board, String word, int row, int col, int index) {
    // If all characters are found
    if (index == word.length()) {
        return true;
    }

    // Check boundaries and character match
    if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
            || board[row][col] != word.charAt(index)) {
        return false;
    }

    // Mark the cell as visited by temporarily changing its value
    char temp = board[row][col];
    board[row][col] = '#';

    // Explore all 4 directions: up, down, left, right
    boolean found = backtrack(board, word, row + 1, col, index + 1) ||
            backtrack(board, word, row - 1, col, index + 1) ||
            backtrack(board, word, row, col + 1, index + 1) ||
            backtrack(board, word, row, col - 1, index + 1);

    // Restore the original value (backtrack)
    board[row][col] = temp;

    return found;
}

//------------------------phone letter combinatiomn----------
   private static final String[] KEYPAD = {
                "",     // 0
                "",     // 1
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv",  // 8
                "wxyz"  // 9
        };

        public static List<String> letterCombinations(String digits) {
            List<String> result = new ArrayList<>();
            if (digits == null || digits.length() == 0) return result;

            backtrack(digits, 0, new StringBuilder(), result);
            return result;
        }

        private static void backtrack(String digits, int index,
                                      StringBuilder path, List<String> result) {

            // Base case: path length equals digits length
            if (index == digits.length()) {
                result.add(path.toString());
                return;
            }

            char digitChar = digits.charAt(index);
            int digit = digitChar - '0';
            String letters = KEYPAD[digit];

            for (int i = 0; i < letters.length(); i++) {
                path.append(letters.charAt(i));        // choose
                backtrack(digits, index + 1, path, result); // explore
                path.deleteCharAt(path.length() - 1);  // unchoose
            }
        }


}
