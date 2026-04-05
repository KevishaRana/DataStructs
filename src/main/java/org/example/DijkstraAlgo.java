package org.example;

import java.util.*;

public class DijkstraAlgo {
    public static void main(String[] args) {
     // imagine we are optimizing a payment routing system at a bank.
    // I want to find the minimum latency (time) from the starting server to all other servers.
     // The InputNumber of Servers: 5 (Numbered 0 to 4)
     // Starting Server: 0
    // Edges ([source, target, weight]):int[][] edges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}, {3, 4, 3}};

        int[][] edges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}, {3, 4, 3}};
        int minimumLatency = minimumLatencyCount(edges, 0, 5, 2);
        System.out.println("Minimum Latency: "+ minimumLatency);
    }


//    class Edge {
//        int target;
//        int weight;
//        Edge(int t, int w) { this.target = t; this.weight = w; }
//    }
//
//    // Much cleaner Map definition:
//    Map<Integer, List<Edge>> adj = new HashMap<>();
//
//for (int[] edge : edges) {
//        adj.computeIfAbsent(edge[0], k -> new ArrayList<>())
//                .add(new Edge(edge[1], edge[2]));
//    }

    private static int minimumLatencyCount(int[][] edges, int start, int vertexes ,int find) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        int[] distance = new int[vertexes+1];
        for (int i=0; i<distance.length;i++){
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i =0; i<edges.length;i++){
            adj.computeIfAbsent(edges[i][0], k-> new ArrayList<>()).add(new int[]{edges[i][1],edges[i][2]});
        }
        //int[] = edge, total weight
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a,b) -> a[1]-b[1]);

        distance[start] = 0;
        priorityQueue.add(new int[]{start, 0}); // [Destination, TotalWeight]

        while (!priorityQueue.isEmpty()){
            int[] current = priorityQueue.poll();
            int currentNode = current[0];
            int currentWeight = current[1];
            // no need to go further if distance is larger
            if(currentWeight > distance[currentNode]){
                continue;
            }

            for(int currentEdges[] : adj.getOrDefault(currentNode, new ArrayList<>())){
                int currChildNode = currentEdges[0];
                int currChildWeight = currentEdges[1];
                if(currChildWeight + distance[currentNode] < distance[currChildNode]){
                    distance[currChildNode] = currChildWeight+ distance[currentNode];
                    priorityQueue.add(new int[]{currChildNode,distance[currChildNode]});
                }
            }

        }


        return  distance[find];
    }

}
