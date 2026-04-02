package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class LongestSubstringDintinctCharacters {
    public static void main(String[] args) {
        // k = maximum no of distinct character allowed in substring
        // so repeatation is allowed but no of distinct character should be k

        System.out.println("subarry is:" + distinctSubstring("eceba", 2));

        List<String> result = new ArrayList<>();
        permutation("ABC", new StringBuilder(), new boolean["ABC".length()],result);
        result.stream().forEach(a-> System.out.println(a));

    }
    public  static String distinctSubstring(String string, int distinctCount){
        HashMap<Character, Integer> frequency = new HashMap<>();
        int left = 0;
        int max = 0;
        int bestStart = 0;
        var priorityQueue = new PriorityQueue<Character>((a,b)-> frequency.get(a)-frequency.get(b));

       for (int right = 0; right <string.length(); right++){
           char c = string.charAt(right);
           frequency.put(c, frequency.getOrDefault(c,0)+1);
           while(frequency.size() > distinctCount){

               frequency.put(string.charAt(left), frequency.get(string.charAt(left))-1);
               if (frequency.get(string.charAt(left)) == 0){
                   frequency.remove(string.charAt(left));
               }
               left++;
           }

           if(right-left + 1 > max) {
               max = right - left + 1;
               bestStart = left;
           }
       }
       for(Character pq : frequency.keySet()){
           priorityQueue.add(pq);
           // now tow k elements will be based on priority
           if(priorityQueue.size() >distinctCount) {
               priorityQueue.poll();
           }

       }
       while (!priorityQueue.isEmpty()){
           System.out.println("top frequency:"+ priorityQueue.poll());
       }

       return string.substring(bestStart, bestStart + max);
    }

    public static void permutation(String s, StringBuilder current, boolean[] used, List<String> result){
        if(s.length() == current.length()){
            result.add(current.toString());
            return;
        }

        for(int i = 0; i<s.length();i++){
            if(used[i]){
                continue;
            }

            used[i] = true;
            current.append(s.charAt(i));
            permutation(s, current,used, result);
            used[i] = false;
            current.deleteCharAt(current.length()-1);
        }
    }


}
