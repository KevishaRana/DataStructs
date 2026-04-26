package org.example;

import java.util.*;

public class FrequencyAcessSlidingWindow {
    public static void main(String[] args) {
        String[][] badgeTimes = {
                {"Paul", "1355"}, {"Jennifer", "1910"}, {"John", "830"},
                {"Paul", "1315"}, {"John", "835"}, {"John", "920"},
                {"John", "840"}, {"Paul", "1405"}, {"John", "930"},
                {"James", "1000"}, {"James", "1005"}, {"James", "1040"}
        };
        // user with more than three times used badge in 60 minutes
        Map<String, List<String>> result = new HashMap<>();
         FrequencySlidingWindowCount(badgeTimes,result);
        result.forEach((k,v)->System.out.println(k+":"+v));
    }

    private static void FrequencySlidingWindowCount(String[][] badgeTimes, Map<String, List<String>> result) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(String[] badge: badgeTimes){
            map.computeIfAbsent(badge[0],k-> new ArrayList<>()).add(Integer.valueOf(badge[1]));
        }
        int left=0;
        for(String name: map.keySet()){
            List<Integer> times = map.get(name);

            Collections.sort(times);
            for(int right =0; right<times.size();right++){
                while(getMinute(times.get(right))- getMinute(times.get(left)) > 60){
                    left++;
                }
                if(right-left+1 >=3){
                    List<String> tempSlide = new ArrayList<>();
                    for(int k= left;k<=right;k++){
                        tempSlide.add(String.valueOf(times.get(k)));
                    }
                    result.put(name,tempSlide);
                    break;
                }

            }

        }


    }
    public static int getMinute(int time){
        return (time/100)*60+(time%100);
    }
}
