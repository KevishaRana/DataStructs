package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrumpSuitCard {
    public static void main(String[] args) {
        String[] cardPlayed  = {"S10", "S12", "S5", "S2"};
        String trumpSuit = "H";
 //       String result = Winnner(cardPlayed, trumpSuit);
//        System.out.println("result is:"+result);
        System.out.println("result is:"+Winnner(new String[]{"C10", "C13","H2", "H5"}, "H"));
    }

    private static String Winnner(String[] cardPlayed, String trumpSuit) {
        Pattern p = Pattern.compile("\\s*(\\w)(\\d+),?");
        int max = Integer.MIN_VALUE;
        String result = "";
        boolean containSuit = false;
        for(String card: cardPlayed){
            Matcher matcher = p.matcher(card);
            while(matcher.find()){
                System.out.println("test:"+matcher.group(1));

                if(trumpSuit.equals(matcher.group(1))){
                    containSuit = true;
                    int current = Integer.parseInt(matcher.group(2));
                    System.out.println("cur:"+current+",max:"+max);
                    if(current > max && result.contains(trumpSuit)){
                        max = current;
                        result = matcher.group(0);
                    }else if(!result.contains(trumpSuit)){
                        max = current;
                        result = matcher.group(0);
                    }
                }else{
                    if(containSuit == false){
                        int current = Integer.parseInt(matcher.group(2));
                        if(current > max){
                            max = current;
                            result = matcher.group(0);
                        }
                    }
                }
            }

        }
      return result;
    }
}
