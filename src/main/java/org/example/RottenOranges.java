package org.example;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        // rotten orange - how fast a bad orange will completly turn grid of good oranges into bad.
        //2 = bad oranges, 1 == good oranges, 0 is empty/ no oranges
        //it will spread with 1 minute per grid

        // As we need to find minimum time, we need to use BFS, cause DFS won't spread simultaneously.
        // so there are three islands
        int[][] basket = {{1,1,1,2},
                           {1,1,0,0},
                           {0,2,0,1},
                           {1,1,0,0}};

        // we have two rotten oranges at (0,3) and (2,1). now from there we need to trun all ones to zero by
        // spreading 2 in all direction\

       System.out.println("Oranges will get rotten in seconds isolated 2,3: "+rottenTimeCount(basket));

        int[][] basket1 = {{1,1,1,2},
                {1,1,0,0},
                {0,2,0,0},
                {1,1,0,0}};

        // we have two rotten oranges at (0,3) and (2,1). now from there we need to trun all ones to zero by
        // spreading 2 in all direction\

        System.out.println("Oranges will get rotten in seconds: "+rottenTimeCount(basket1));

    }

    public static int rottenTimeCount(int[][] basket){
        if(basket == null || basket.length <= 0){
            return  0;
        }
        int count = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        //count to tacke isolated orages which can't be rotten by spread
        int totalFreshOranges = 0;
        boolean[][] visited = new boolean[basket.length][basket[0].length];

        for(int i =0; i<basket.length; i++){
            for(int j=0; j<basket[0].length; j++){
                if(basket[i][j] == 2 && !visited[i][j]){
                    queue.add(new int[]{i,j, count});
                    visited[i][j] = true;
                }
                if(basket[i][j] == 0){
                    visited[i][j] = true;
                }
                if(basket[i][j] ==1){
                    totalFreshOranges++;
                }
            }
        }
        int[] rowDir = {-1,1,0,0};
        int[] colDir = {0,0,1,-1};
        while(!queue.isEmpty()){
            int[] cords = queue.poll();
            count = cords[2];
            for(int k =0; k<rowDir.length;k++){
                int newRow = cords[0] + rowDir[k];
                int newCol = cords[1] + colDir[k];
                if(newRow >= 0 && newRow < basket.length && newCol >= 0 && newCol < basket[0].length
                && basket[newRow][newCol] == 1 && !visited[newRow][newCol] ) {
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol, count+1});
                    totalFreshOranges --;

                }

            }

        }
        if(totalFreshOranges >0){
            return  -1;
        }else{
            return count;
        }
    }
}
