package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPathsSoourceToTarget {
    public static void main(String[] args) {
        // 0 ->1 -> 3 -> 5
        // | \2 ->  /4  ->5
        // 5
        int[][] graph = {

                {1,2,5},
                {2,3},
                {4},
                {4,5},
                {5},
                {}
        };
        // so sorce is node 0, from where we can go to 1,2,5
        // 5 is destination node -> so ways can be |5,| 1,,3,5| 2,4,5
       // int[] current = new int[graph.length];
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        findAllPaths(graph,0, current, result);
        result.forEach(path->{
                System.out.println("Path: " + path);
});
    }

    private static void findAllPaths(int[][] graph, int node, List<Integer> current, List<List<Integer>> result) {
        if(node == graph.length-1) {
            result.add(new ArrayList(current));
            return;
        }
        for(int val: graph[node]){
            current.add(val);
            findAllPaths(graph, val, current, result);
            current.remove(current.size()-1);
        }

    }
}
