package org.example;

import java.util.*;

public class hashMap {
    public static void main(String[] args) {

        int[] arr = {1,1,5,3,7,2,5,6,7,2};
        System.out.println("Array two non repeating values are: "+ Arrays.toString(twoNonRepeatingElements(arr)));
        List<Integer> list = new ArrayList<>(List.of(1,2,3,4,6,7));

        System.out.println("Winner of joseph problem is : "+ josephProblem(list, 3));

    }

    public static int[] twoNonRepeatingElements(int[] arr){
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int aElem : arr){
            map.put(aElem, map.getOrDefault(aElem,0)+1);
        }
        int i =0;
        for( Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue().equals(1)){
                result[i] = entry.getKey();
                i++;
            }
        }

return result;
    }
    /*Josephs problem
    * people are sittimnhg in cirle(n), every time 2nd (Kth) from the start will
    * be shot dead, who will remain last. we can use modulo to make ot round
    */
    public static int josephProblem(List<Integer> arr, int k){
        int start = 0;
        while(arr.size() > 1){
            start = (start + k -1) % arr.size();
            arr.remove(start);
        }
        return arr.get(0);
    }

}
