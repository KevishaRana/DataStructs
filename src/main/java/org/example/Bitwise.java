package org.example;

public class Bitwise {
    public static void main(String[] args) {
        
        int a =12;
//
//        The result of a >> n is:  result = {a}/{2^n}
        int result = a >> 2; // divide by 2 two times

        System.out.println("a>>2:"+ result);

      result = 16 >> 3; // divide by 2 two times

        System.out.println("16 >> 3:"+ result);
        result = a%2; // numer reaminder

        System.out.println("a%2:"+ result);
        
        result = a&1; // last bit & 1 12& 1 = 0 as 12 is even, last bit 0

        System.out.println("a&1:"+ result);
        
        //most imp find unique
        //works only with even cancel , like 6 5s, one 3 -> 5s will cancel each other
        int arr[]={5,5,5,3,5,5,5};
        int b = 0;
        for (int i=0; i<arr.length;i++){
            b = b ^ arr[i];
        }
        System.out.println("a^B:"+ b);


        ///  even odd by bit
        if((a & 1) ==0){
            System.out.println(" even");
        }else{
            System.out.println(" odd");
        }
                
    }

}
