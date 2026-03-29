package org.example;

public class IslandProblem {
    public static void main(String[] args) {
        // so there are three islands
        int[][] islands = {{1,1,1,0},
                           {1,1,0,0},
                           {0,0,0,1},
                           {1,1,0,0}};
        System.out.println("Number of islands: "+ islandCount(islands));

    }

    private static int islandCount(int[][] islands) {
        int count = 0;
        if(islands.length == 0 || islands == null ){
          return  -1;
        }
//        if (row == islands.length ){
//            return count;
//        }

        for(int i=0; i<islands.length;i++){
            for (int j=0; j<islands[0].length;j++){
                if(islands[i][j] == 1){
                    count++;
                    sinkWholeIsland(islands,i, j);
                }
            }

        }

        return count;
    }

    private static void sinkWholeIsland(int[][] islands, int row, int col) {
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
        sinkWholeIsland(islands,row+1,col);
        sinkWholeIsland(islands, row-1, col);
        sinkWholeIsland(islands, row, col+1);
        sinkWholeIsland(islands, row, col-1);

    }

}
