package org.example;

import java.util.*;

public class topologicalSortPrerequisiteCount {
    public static void main(String[] args) {

     // Let's use a standard "College Degree" scenario.
    // Imagine you are a student and these are the rules the registrar gave you.
     // The InputTotal Courses : 6 (Numbered 0 to 5)
    // Prerequisites: [[1, 0], [2, 0], [3, 1], [3, 2], [4, 3], [5, 4]]
        int[][] prerequisites = {{1, 0} ,
                                  {2, 0},
                                  {3, 1},
                                  {3, 2}, {4, 3}, {5, 4}};
        int[] courses = new int[6];
        int count = preCount(prerequisites, courses);
        System.out.println("count is:"+count);

    }

    private static int preCount(int[][] prerequisites, int[] courses) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i =0; i< prerequisites.length;i++){
         //   adj.put(prerequisites[i][1], adj.getOrDefault(prerequisites[i][1], new ArrayList<>()).add(prerequisites[i][0]));

            adj.computeIfAbsent(prerequisites[i][1],k -> new ArrayList<>()).add(prerequisites[i][0]);
            //course [1,0], [3,1] [3,2]
            //couse 1 blocked by 1 boss(0), course 3 blocked by two (1,2).. so to unlock
            // course 1, we jsut need 1 task to finish whears for task 3, we need 3 task to complete
            courses[prerequisites[i][0]] = courses[prerequisites[i][0]]+1;

        }
        // now find course not dependent of anyone.
        // So by doing so we can unlock other courses( for our case course[0] =0,default)
        for(int i =0; i<courses.length;i++){
            if(courses[i] == 0){
                // for us queue = 0 added
                queue.add(i);
            }
        }
        // now BFS approach to deal with neighbours
        // 0 gonna unlock course of year 1,
        // think like non-medical(0) gonna open 1st year courses of b.tech
        int count =0;
        while(!queue.isEmpty()){
            int current = queue.poll();
            count ++;
            if(adj.containsKey(current)){
                for(int course: adj.get(current)){
                    // now it will unlock differnct couses .
                    // but we can only pick if course has no dependecy
                    courses[course]--;
                    if(courses[course] == 0){
                        // dependency finish, it is ready to unlock other courses
                        queue.add(course);
                    }
                }
            }

        }

// if count == number of couses , it means all courses complete,
// if not it means there was circular dependency


        return count == courses.length ? 1:0;
    }


}
