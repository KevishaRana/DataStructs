package org.example;

import java.util.ArrayList;
import java.util.List;

public class generateAngleBraces {
    public static void main(String[] args) {
        List<String> results = new ArrayList<>();
        results= generateAngleBracketSequences(3);
        results.stream().forEach(data -> System.out.println(data));
    }

    public static List<String> generateAngleBracketSequences(int n) {
        // Write your code here
        List<String> results = new ArrayList<>();
        generate(n,0,0, new StringBuilder(), results);
        return new ArrayList<>(results);
    }

    public static  void generate(int max, int open, int close, StringBuilder current, List<String> results){
        if(current.length() == max*2){
            results.add(current.toString());
            return;
        }
        //< case
        if(open < max){
            current.append("<");
            generate(max, open+1, close, current, results);
            current.setLength(current.length() -1);

        }
        if(close < open){
            current.append(">");
            generate(max, open, close+1, current, results);
            current.setLength(current.length() -1);
        }

    }

}
