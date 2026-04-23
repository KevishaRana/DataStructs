package org.example;

import java.util.*;

public class GenerationGraph {
    public static void main(String[] args) {
        int[][] parentChildPairs = {
                {1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7},
                {4, 5}, {4, 8}, {8, 9}
        };
        findPairs(parentChildPairs);
        findAncestors(parentChildPairs, 3, 8);
        findAncestors(parentChildPairs, 6, 7);
        findEarliestAncestor( 6, -1);
    }

    private static void findEarliestAncestor( int i, int result) {
        System.out.println("Earliest Ancestor of "+i+" is:"+result);
    }

    private static void findAncestors(int[][] parentChildPairs, int p1, int p2) {
        Map<Integer, List<Integer>> childFrequency = new HashMap<>();
        for(int i =0; i<parentChildPairs.length;i++){
            int child = parentChildPairs[i][1];
            int parent = parentChildPairs[i][0];
            childFrequency.computeIfAbsent(child, k-> new ArrayList<>()).add(parent);
            if(!childFrequency.containsKey(parent)){
                childFrequency.put(parent, new ArrayList<>());
            }
        }
        Set<Integer> pair1 = getAllAncestors(childFrequency,p1);
        Set<Integer> pair2 = getAllAncestors(childFrequency, p2);

        for(int set1 : pair1){
            if(pair2.contains(set1)){
                System.out.println(p1+" and "+p2+" has common ancestor");
                return;
            }
        }
        System.out.println(p1+" and "+p2+" has no Common ancestor");
    }

    private static Set<Integer> getAllAncestors(Map<Integer, List<Integer>> childFrequency, int p1) {
        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> result = new HashSet<>();
        int earliest = -1;
        if(childFrequency.containsKey(p1)){
            queue.addAll(childFrequency.get(p1));
        }
        while(!queue.isEmpty()){
            for(int i =0; i<queue.size();i++){
            int current = queue.poll();
            result.add(current);
            if(childFrequency.containsKey(current)) {
                for (int c : childFrequency.get(current)) {
                    queue.add(c);
                    earliest = c;
                }
            }

            }
        }
        findEarliestAncestor(p1,earliest);
        return  result;
    }

    private static void findPairs(int[][] parentChildPairs) {
        Map<Integer, Integer> childFrequency = new HashMap<>();
        for(int i =0; i<parentChildPairs.length;i++){
            int child = parentChildPairs[i][1];
            int parent = parentChildPairs[i][0];
            childFrequency.put(child,childFrequency.getOrDefault(child,0)+1 );
            if(!childFrequency.containsKey(parent)){
                childFrequency.put(parent, 0);
            }
        }
        List<Integer> zeroParent = new ArrayList<>();
        List<Integer> moreParent = new ArrayList<>();

        for(int key: childFrequency.keySet()){
            if(childFrequency.get(key) == 0){
                zeroParent.add(key);
            }
            else{
                moreParent.add(key);
            }
        }

        zeroParent.forEach(s-> System.out.print(s+", "));
        System.out.println();
        moreParent.forEach(s-> System.out.print(s+", "));
    }
}
