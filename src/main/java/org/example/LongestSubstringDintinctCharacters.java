package org.example;

import java.util.HashMap;

public class LongestSubstringDintinctCharacters {
    public static void main(String[] args) {
        // k = maximum no of distinct character allowed in substring
        // so repeatation is allowed but no of distinct character should be k

        System.out.println("subarry is:" + distinctSubstring("eceba", 2));

    }
    public  static String distinctSubstring(String string, int distinctCount){
        HashMap<Character, Integer> frequency = new HashMap<>();
        int left = 0;
        int max = 0;
        int bestStart = 0;

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

       return string.substring(bestStart, bestStart + max);
    }


}
