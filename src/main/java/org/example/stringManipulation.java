package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class stringManipulation {
    public static void main(String[] args) {
        System.out.println("Resuls for Kevisha_2026!  is: "+ stringCleaner("Kevisha_2026!"));
        System.out.println("Resuls for java @ 123  is: "+ stringCleaner("java @ 123"));

        System.out.println("camelcase count for saveChanges is: "+ CamelCaseCounter("saveChanges"));
    System.out.println("camelcase count for save is: "+ CamelCaseCounter("save"));
        System.out.println("camelcase count for findUserById is: "+ CamelCaseCounter("findUserById"));

        System.out.println("hastag count is: "+hashtagCounter("I love #Java and #Regex! #2026 #learning"));

        System.out.println("hastag2 count is: "+hashtagCounter2("I love #Java and #Regex! #2026 #learning"));


    }

    private static boolean stringCleaner(String s) {
        boolean result =false;

        String cleaned = s.replaceAll("[^a-z0-9_]", "");
        if(cleaned.length() <= 10 && cleaned.length() >=5){
            result = true;
        }

        return result;
    }

    private static int CamelCaseCounter(String s){
     //   it will remove capiytal letter
     //   Your array becomes ["", "ave", "hanges" for saveChanges].
     //   String[] container = s.split("[A-Z]");

        // to keep firstLetter, ?= lookahead
        String[] container = s.split("(?=[A-Z])");
        return  container.length;
    }

   // You have a messy string of social media text.
    // You need to find how many hashtags are in the string
   private static int hashtagCounter(String s){
        int counter =0;

       // to keep firstLetter, ?= lookahead
       String[] container = s.split("(?=[#])");
       for(String test : container){
           if(test.startsWith("#")){
               counter++;
           }
       }
       return  counter;
   }

    private static int hashtagCounter2(String s) {
        int counter = 0;

        Pattern pattern =  Pattern.compile("#\\w+");
        Matcher matcher = pattern
                .matcher(s);
        while(matcher.find()){
            counter++;
        }
        return  counter;

    }





    }
