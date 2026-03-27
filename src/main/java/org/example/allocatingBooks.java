package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class allocatingBooks {
    public static void main(String[] args) {
        int[] arr = {10,20,30,40,50};
       System.out.println("min books so far brute: "+ bruteForceContinityLevel1(arr,0,3));
//        int[] list = new int[3];
//        divideWithoutContinuity(arr, 0, 3, list);
//        System.out.println(Arrays.toString(list));

    }
    public static int bruteForceContinityLevel1(int[] books, int index, int numberStudents){
        //invalid case
        int minBooks = Integer.MAX_VALUE;
        if(numberStudents > books.length-index){
            return  -1;
        }
        if (numberStudents == 1){
            return  Sumofpages(books, index, books.length-1);
        }
        if(books.length-1 == index){
            return books[index];
        }
        for(int nextStudent = index +1; nextStudent < books.length; nextStudent ++){
            int firstStudent = Sumofpages(books,index, nextStudent-1);
            int nextStudents = bruteForceContinityLevel1(books, nextStudent,numberStudents-1);

            //only go further if recursin was successfull
            if (nextStudents != -1){
                //with this split, what was max pages a student got as next pages will give no of pages
                //all remaining student got

                int maxSoFar = Math.max(firstStudent,nextStudents);
                //was that split good than other one whre we could get min value
                minBooks = Math.min(maxSoFar, minBooks);
            }


        }

        return  minBooks;
    }
    public static int Sumofpages(int[] books, int start, int end){
        int sum =0;
        for(int i= start; i<=end; i++){
            sum += books[i];
        }
        return  sum;
    }

    public static int advanceBinarySearchContinuityLevel2(int[] books, int numberStudents){
        int maxPages =0;
        int minPages =0;
        for(int book:books){
            minPages = Math.max(minPages, book);
            maxPages += book;
        }
        int result = maxPages;
        //binary search
        while(minPages <= maxPages){
            int mid = minPages +(maxPages-minPages)/2;
            if(isSolving(books, numberStudents, mid)){
                result = mid;
                maxPages = mid -1;
            }else{
                minPages = mid +1;
            }
        }
        return result;
    }

    private static boolean isSolving(int[] books, int numberStudents, int maxPages) {
        // now we need to give books to student 1 first equal to maxpages and gonna see if its getting
        //equally distribute among num,berStudents
        //we will give first student maxpages book
        // so in start we have first student without any book.
        int startSudentno =1;
        int totalPages =0;
        for(int pages : books){
            if(totalPages + pages <= maxPages){
                totalPages += pages;
            } else {
startSudentno ++;
totalPages = pages;
            }
        }
return startSudentno <= numberStudents;
    }


}
