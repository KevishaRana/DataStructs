package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainAnalysis {
    public static void main(String[] args) {
        String[] input = {"900 google.com", "60 mail.google.com", "10 mobile.sports.yahoo.com"};
        Map<String, Integer> result = calculateDomainCounts(input);

        result.forEach((k, v) -> System.out.println(v + " " + k));
    }

    private static Map<String, Integer> calculateDomainCounts(String[] inputs) {
        Map<String, Integer> result = new HashMap<>();
//        for(String input: inputs){
//            String[] split = input.split("\\s+");
//            result.put(split[1], result.getOrDefault(split[1],0)+Integer.parseInt(split[0]));
//            int dotIndex = 0;
//            String temp = split[1];
//            while(true){
//                dotIndex = temp.indexOf(".");
//                if(dotIndex == -1){
//                    break;
//                }
//                temp = temp.substring(dotIndex+1);
//                result.put(temp, result.getOrDefault(temp,0)+Integer.parseInt(split[0]));
//
//            }
//        }

        Pattern p = Pattern.compile("\\s*(\\d+)\\s*([\\w.]+)\\s*,?\\s*");
        for(String input: inputs){
            Matcher matcher = p.matcher(input);
            while(matcher.find()){
                int val = Integer.parseInt(matcher.group(1));
                String temp = matcher.group(2);
                result.put(temp, result.getOrDefault(temp,0)+val);
                int dotIndex =0;
                while(true){
                    dotIndex = temp.indexOf(".");
                    if(dotIndex == -1){
                        break;
                    }
                    temp = temp.substring(dotIndex+1);
                    result.put(temp, result.getOrDefault(temp,0)+val);
                }
            }

        }

        return result;
    }
}
