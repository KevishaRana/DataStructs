package org.example;

import java.util.*;

public class AcademicCurriculum {
    public static void main(String[] args) {
        String[][] prerequisites = {
                {"Data Structures", "Programming 101"},
                {"Algorithms", "Data Structures"},
                {"Advanced Java", "Programming 101"},
                {"Spring Boot", "Advanced Java"},
                {"Spring Boot", "SQL Basics"},
                {"Microservices", "Spring Boot"}
                ,
                // THIS LINE CREATES THE CYCLE:
                {"Programming 101", "Microservices"}
        };

        //int course[] = new int[prerequisites.length];
        Map<String, Integer> course = new HashMap<>();

        boolean isCircularDependent = findDependency(prerequisites, course);
        System.out.println("circular dependent:"+isCircularDependent);
    }

    private static boolean findDependency(String[][] prerequisites, Map<String, Integer> course) {
        Map<String, List<String>> adj = new HashMap<>();
        Queue<String> queue = new PriorityQueue<>();
        for(int i =0; i< prerequisites.length;i++){
            adj.computeIfAbsent(prerequisites[i][1],v -> new ArrayList<>()).add(prerequisites[i][0]);
            course.put(prerequisites[i][0],course.getOrDefault(prerequisites[i][0],0)+1);
            // 3. Ensure the prereq is in the map with 0 if it hasn't been seen as a target yet
            if (!course.containsKey(prerequisites[i][1])) {
                course.put(prerequisites[i][1], 0);
            }
        }
        for(String c: course.keySet()){
            if(course.get(c) == 0){
                queue.add(c);
            }
        }
        int count =0;
        while(!queue.isEmpty()){
            String c = queue.poll();
            count++;
            if(adj.containsKey(c)){
                for(String val : adj.get(c)){
                    course.put(val, course.get(val)-1);
                    if(course.get(val) == 0){
                        queue.add(val);
                    }
                }

            }
        }

        return count == course.size() ?false:true;
    }
}
