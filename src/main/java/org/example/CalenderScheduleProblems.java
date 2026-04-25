package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class CalenderScheduleProblems {
    public static void main(String[] args) {
        //1. check if meeting overlap exist or noty
      //  System.out.println("Is meetins overlap: "+checkMeetingOverlap(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
     //   System.out.println("Is meetins overlap: "+checkMeetingOverlap(new int[][]{{7, 10}, {2, 4}}));

       // Consolidating overlapping meetings into single blocks of time.
    //    int[][] result = consolidatingOverlap(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15,18}});
     //   Arrays.stream(result).forEach((a) -> System.out.println(a[0]+","+a[1]));
     //   int result = meetingRoomsRequired(new int[][]{{1, 30}, {5, 10},{15,20}});
     //   System.out.println("no of rooms:"+result);
     //   int tabs = TabsRequiredToWateringGarden(new int[]{3, 4, 1, 1, 0, 0}, 6);
     //   System.out.println("tabs Required:"+tabs);
        //[[1, 3], [6, 9]] , [4, 5]
        int[][] result = IntervalInsertion(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4,8});
        Arrays.stream(result).forEach((a) -> System.out.println(a[0]+","+a[1]));
    }

    private static int[][] IntervalInsertion(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        //int current[] = intervals[0];
        int i =0;
        int l = intervals.length;
        while (i<l && intervals[i][1] < newInterval[0]){
            result.add(intervals[i]);
            i++;
        }
        while(i<l && intervals[i][0]< newInterval[1]){
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        while(i<l){
            result.add(intervals[i]);
            i++;
        }

       // result.add(current);



        return result.toArray(new int[result.size()][]);
    }

    public static int TabsRequiredToWateringGarden( int[] ranges,int n) {
        // 1. Pre-processing: Create a jump array
        // maxReach[i] stores the furthest right point we can reach starting at i
        int[] maxReach = new int[n + 1];

        for (int i = 0; i < ranges.length; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(n, i + ranges[i]);

            // At the start point, store the furthest end point possible
            maxReach[start] = Math.max(maxReach[start], end);
        }

        // 2. Greedy Jump Logic
        int taps = 0;
        int currentEnd = 0;
        int nextEnd = 0;

        for (int i = 0; i < n; i++) {
            // Update the furthest we could possibly reach as we move along
            nextEnd = Math.max(nextEnd, maxReach[i]);

            // If we are stuck (can't move past the current index)
            if (nextEnd <= i) return -1;

            // If we've reached the end of the current tap's reach
            if (i == currentEnd) {
                taps++;
                currentEnd = nextEnd;
            }
        }

        return taps;
    }

    private static int meetingRoomsRequired(int[][] intervals) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Arrays.sort(intervals, (a,b)-> Integer.compare(a[0],b[0]));
        int current[] = intervals[0];
        queue.offer(current[1]);
        for(int i =1; i<intervals.length;i++){
            int next[] = intervals[i];
            if(next[0]>= queue.peek() ){
                queue.poll();
            }
            queue.offer(next[1]);
        }

       return  queue.size();
    }

    private static int[][] consolidatingOverlap(int[][] intervals) {
        List<int[]> result = new ArrayList<>();
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0],b[0]));
        int[] current = intervals[0];
        result.add(current);
        for(int i =1; i<intervals.length;i++){
            int next[] = intervals[i];
            if(next[0] < current[1]){
                // why math.max, case -> [[1, 10], [2, 5]].
                current[1] = Math.max(current[1], next[1]);
            } else{
                current = next;
                result.add(current);
            }
        }
        return  result.toArray(new int[result.size()][]);
    }

    private static boolean checkMeetingOverlap(int[][] intervals) {
       // boolean result = true;
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        int current[] = intervals[0];
        for(int i=1;i<intervals.length;i++){
            int[] next = intervals[i];
            if(next[0] < current[1]){
                return true;
            }else{
                current = next;
            }
        }

        return false;
    }
}
