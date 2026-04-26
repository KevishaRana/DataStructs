package org.example;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

public class PassageTracker {
    public static void main(String[] args) {
        String[][] entries = {
                {"Alice", "10:00", "enter"},
                {"Alice", "10:30", "exit"},
                {"Bob", "11:00", "enter"},
                {"Alice", "12:00", "enter"},
                {"Alice", "12:15", "exit"}
        };
        Map<String, TimeCheck> result = new HashMap<>();
        CalculateTime(result, entries);
        result.forEach((k,v)->System.out.println(k+","+v.maxTime));


        //shared passage pattern
        String[][] logs = {
                {"Alice", "Room 1"}, {"Alice", "Room 2"}, {"Alice", "Room 3"},
                {"Bob", "Room 1"}, {"Bob", "Room 2"}, {"Bob", "Room 4"}
        };
        Map<String, Set<String>> shared = new HashMap<>();
        SharedPassagePattern(shared,logs);
        shared.forEach((k,v)->System.out.println(k+":"+v));

        // performance Tracker
        String[][] badgeRecords = {
                {"Martha",   "exit"},
                {"Paul",    "enter"},
                {"Martha",  "enter"},
                {"Steve",   "enter"},
                {"Martha",   "exit"},
                {"Jennifer", "enter"},
                {"Paul",     "enter"},
                {"Jennifer", "exit"}
        };
        Map<String,List<String>> records = new HashMap<>();
        PerformanceTracker(badgeRecords, records);
        records .forEach((k,v)->System.out.println(k+":"+v));



    }

    private static void PerformanceTracker(String[][] badgeRecords,Map<String, List<String>> records) {
        List<String> missingEntry = new ArrayList<>();
        List<String> missingExit = new ArrayList<>();
        List<String> inside = new ArrayList<>();
        for(int i =0; i<badgeRecords.length;i++){
            String badge[] = badgeRecords[i];
            if(badge[1].equals("enter")){
                if(inside.contains(badge[0])){
                    missingExit.add(badge[0]);
                }else{
                    inside.add(badge[0]);
                }
            }
            else if(badge[1].equals("exit")){
                if(inside.contains(badge[0])){
                    inside.remove(badge[0]);
                }else{
                    missingEntry.add(badge[0]);
                }
            }

        }
        missingExit.addAll(inside);
        records.put("MissingEntries",missingEntry);
        records.put("MissingExit",missingExit);
    }

    private static void SharedPassagePattern(Map<String, Set<String>> shared, String[][] logs) {
        Map<String, Set<String>> map = new HashMap<>();
        for(int i=0; i<logs.length;i++ ){
            String log[] = logs[i];
            map.computeIfAbsent(log[0],k-> new HashSet<>()).add(log[1]);
        }
        List<String> keys = new ArrayList<>(map.keySet());
        for(int i =0;i<keys.size();i++ ){
            for (int j=0; j<keys.size();j++ ){
                String k1 = keys.get(i);
                String k2 = keys.get(j);
                Set<String> common = new HashSet<>(map.get(k1));
                common.retainAll(map.get(k2));
                shared.put(k1+","+k2 ,common);

            }

        }


    }

    static class TimeCheck{
        String startTime;
        int maxTime;
        TimeCheck(String startTime,int maxTime){
            this.startTime = startTime;
            this.maxTime= maxTime;
        }
    }


    private static void CalculateTime(Map<String, TimeCheck> result, String[][] entries) {
        for (int i=0; i<entries.length;i++) {
            String[] temp = entries[i];
            String time = temp[1]+":00";
            if(temp[2].equals("enter")){
                result.computeIfAbsent(temp[0], k-> new TimeCheck(time,0)).startTime =time;
            }else if(temp[2].equals("exit")){
                TimeCheck user = result.get(temp[0]);
                if (user != null) {
                    long diffence = Time.valueOf(time).getTime() - Time.valueOf(user.startTime).getTime();
                    int timeD = (int)diffence/(1000 *60);
                    int max = Math.max(timeD, user.maxTime);
                    user.maxTime = max;
                    result.put(temp[0],user);
                }


            }
        }
    }
}
