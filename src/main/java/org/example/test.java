package org.example;

import java.util.*;

public class test {

    public static void main(String[] args) {
        int[][] map = {
                {0,0,'C',0,1},
                {1,0,1,0,0},
                {0,'C',0,1,0},
                {1,1,1,1,1},
                {1,0,'C',0,0}
        };
        Node start = new Node(0,0, 0+","+0);
        List<String> results = new ArrayList<>();
        finder(map, results, start);
        results.stream().forEach(s-> System.out.println(s));
    }

    private static void finder(int[][] map, List<String> results, Node start) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.l][start.r] = true;
        for (int p =0; p< map.length;p++){
            for(int q =0; q< map[0].length;q++){
                if(visited[p][q] == false && map[p][q] != 1 || (p == start.l && q == start.r )){

                    queue.add(new Node(p,q,p+","+q));
                    visited[p][q] = true;

                    while(!queue.isEmpty()){
                        Node current = queue.poll();
                        if(map[current.l][current.r] == 'C'){
                            results.add(new String(current.path));
                        }
                        for(int i=0; i<direction.length;i++){
                            int nr = current.l + direction[i][0];
                            int nc = current.r + direction[i][1];
                            if(nr>=0 && nr<map.length && nc >=0 && nc < map[0].length
                                    && map[nr][nc] != 1 && visited[nr][nc] == false){
                                queue.add(new Node(nr, nc, current.path + "-> "+nr+","+nc));
                                visited[nr][nc] = true;
                            }
                        }
                    }

                }
            }
        }

    }
}
class Node{
    int l,r;
    String path;
    public Node(int l, int r, String path){
        this.l =l;
        this.r = r;
        this.path = path;
    }

}
