package org.example;

import java.security.Key;
import java.security.KeyStore;
import java.util.*;

public class PickingRestaurant {
    public static void main(String[] args) {
        String[][] friendPrefs = {
                {"Alice", "Thai", "Italian", "Mexican"},
                {"Bob", "Thai", "Indian", "Japanese"},
                {"Charlie", "Thai", "French", "Chinese"}
        };
        String[][] restaurants = {
                {"Siam Square", "Thai"},
                {"Pasta Place", "Italian"},
                {"Spice Hut", "Indian"},
                {"Golden Wok", "Thai"}
        };
        Map<String,String> result = new HashMap<>();
       pickRestaurant(friendPrefs,restaurants, result);
       result.forEach((k,v)->System.out.println(k+":"+v));

    }

    private static void pickRestaurant(String[][] friendPrefs, String[][] restaurants, Map<String,String> result) {
        Map<String, List<String>> map = new HashMap<>();
        for(String[] prefs:friendPrefs) {
            for (int i = 1; i < prefs.length; i++) {
                map.computeIfAbsent(prefs[0], k -> new ArrayList<>()).add(prefs[i]);
            }
        }
        List<String> keys = new ArrayList<>();
        for(String key: map.keySet() ){
            keys.add(key);
        }
        Set<String> common = new HashSet<>(map.get(keys.get(0)));
        for(int i =1; i<keys.size();i++){
            String k1 = keys.get(i);
            List<String> others = map.get(k1);
            common.retainAll(others);
        }

        for(String[] restaurant:restaurants){
            if(common.contains(restaurant[1])){
                result.put(restaurant[0],restaurant[1]);
            }
        }
    }
}
