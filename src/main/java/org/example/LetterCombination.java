package org.example;

import java.util.*;

public class LetterCombination {

    public static void main(String[] args) {
        List<String> sortedList = new ArrayList<>();
        sortedList = minTasksToCancelForNoConflict("23");
        sortedList.stream().forEach(s -> System
                .out.println(s));
    }

    public static List<String> minTasksToCancelForNoConflict(String digits) {
        // Write your code here
        Map<Integer,Character[]> alphaD = new HashMap<Integer,Character[]>();
        alphaD.put(0, new Character[]{'0'});
        alphaD.put(1, new Character[]{'1'});
        alphaD.put(2, new Character[]{'a','b','c'});
        alphaD.put(3, new Character[]{'d','e','f'});
        alphaD.put(4, new Character[]{'g','h','i'});
        alphaD.put(5, new Character[]{'j','k','l'});
        alphaD.put(6, new Character[]{'m','n','o'});
        alphaD.put(7, new Character[]{'p','q','r','s'});
        alphaD.put(8, new Character[]{'t','u','v'});
        alphaD.put(9, new Character[]{'w','x','y','z'});
        Set<String> set = new TreeSet<>();
        generateSeq(alphaD, new StringBuilder(), 0, set, digits);
        List<String> sortedList = new ArrayList<>(set);
        return sortedList;
    }

    public static void generateSeq(Map<Integer,Character[]> alphaD, StringBuilder sBuilder, int index, Set<String> results, String digits){
        if (digits == null || digits.isEmpty()) return;
        if(index == digits.length()){
            if (sBuilder.length() > 0) {
                results.add(sBuilder.toString());
            }
            return;
        }
        int digit = digits.charAt(index) -'0';
        Character[] alphas = alphaD.get(digit);
        for(char c: alphas){
            sBuilder.append(c);
            generateSeq(alphaD, sBuilder, index+1, results, digits);
            sBuilder.deleteCharAt(sBuilder.length()-1);
        }
    }
}
