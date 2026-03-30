package org.example;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class IslandProblem {
    public static void main(String[] args) {
        // so there are three islands
        int[][] islands = {{1,1,1,0},
                           {1,1,0,0},
                           {0,0,0,1},
                           {1,1,0,0}};
        System.out.println("Number of islands: "+ islandCountDFS(islands));
        int[][] islandsBFS = {{1,1,1,0},
                {1,1,0,0},
                {0,0,0,1},
                {1,1,0,0}};

        //count same problem but by BFS
        System.out.println("Number of islands: "+ islandCountBFS(islandsBFS));

    }

    private static int islandCountDFS(int[][] islands) {
        int count = 0;
        if(islands.length == 0 || islands == null ){
          return  -1;
        }
        for(int i=0; i<islands.length;i++){
            for (int j=0; j<islands[0].length;j++){
                if(islands[i][j] == 1){
                    count++;
                    sinkWholeIslandDFS(islands,i, j);
                }
            }

        }

        return count;
    }

    private static void sinkWholeIslandDFS(int[][] islands, int row, int col) {
       // base conditions check -- return
        if(row < 0 || row >= islands.length || col < 0 || col >= islands[0].length ){
            return;
        }
        //pruning
        if(islands[row][col] == 0){
            return;
        }
        //sinnk island
        islands[row][col] = 0;

        // explore neighbour and sink them as well as they all are part of same island.
        sinkWholeIslandDFS(islands,row+1,col);
        sinkWholeIslandDFS(islands, row-1, col);
        sinkWholeIslandDFS(islands, row, col+1);
        sinkWholeIslandDFS(islands, row, col-1);
    }

    public static int islandCountBFS(int[][] islands){
        if(islands == null || islands.length == 0){
            return 0;
        }
        int count =0;
        Queue<int[]> queue = new ArrayDeque<>();
//        int[][] visited = new int[islands.length][islands[0].length];
        // no need it though as in Array inintal value is zero

//        Arrays.stream(visited).forEach(row ->  Arrays.fill(row, 0));
        // we can use boolean too, default value false
        boolean[][] visited = new boolean[islands.length][islands[0].length];

        int[] rowDir = {1,-1,0,0};
        int[] colDir = {0,0,1,-1};
        for (int i=0; i<islands.length;i++ ) {
            for (int j = 0; j < islands[0].length; j++) {
                // if we found new island , not yet vistied
                if (islands[i][j] == 1 && !visited[i][j]) {
                    count++;
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] current = queue.poll();// has row and column. we will explore it
                        // explore all side of current elemenet and make current element 0
                        // as we have 4 directions only

                        for (int k = 0; k < 4; k++) {
                            int newRow = current[0] + rowDir[k];
                            int newCol = current[1] + colDir[k];
                            if (newRow >= 0 && newRow < islands.length && newCol >= 0 && newCol < islands[0].length
                            && islands[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                                queue.add(new int[]{newRow, newCol});
                                visited[newRow][newCol] = true;
                            }

                        }

                    }

                }

            }

        }
        return count;
    }

}
