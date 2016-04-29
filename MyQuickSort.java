package myQuickSort;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/*  
 * Author: Nathaniel Clay Arnold
 * Program 6 - MyQuickSort
 * CSC230-02 Spring 2016
 */

public class MyQuickSort {

    public static void main(String[] args) {

        // instantiate an array list from import 
        ArrayList<Integer> myList = new ArrayList<>();

        // establish inpput file to read 
        File input = new File("input.txt");

        // read in content of input.txt to an ArrayList 
        try (Scanner readfile = new Scanner(input)) {

            while (readfile.hasNextInt()) {
                myList.add(readfile.nextInt());
            }
            // close file 
            readfile.close();

        //System.out.println(myList.toString());  
            // pass array list to quick sort with first and last indices
            quicksort(myList, 0, myList.size() - 1);

            // print sorted array list to output.txt
            try (PrintWriter outputfile = new PrintWriter("output.txt")) {
                for (Integer myList1 : myList) {
                    outputfile.println(myList1);
                }
                // close file 
                outputfile.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);  // exit program with error of 1
        }

       // System.out.println(myList.toString());
    }

    private static int partition(ArrayList myList, int first, int last) {

        // randomly choose pivot index 
        Random rand = new Random();
        int pivotIndex = rand.nextInt((last - first) + 1) + first;
      // System.out.println(pivotIndex);  

        /* 
         set integer value of the pivot object
         swap pivot element  and last element 
         note: .set method returns previos value of specified index 
         if pivot element is already last element it replaces itself with itself 
         */
        int pivotValue = (int) myList.set(pivotIndex, myList.get(last));
        myList.set(last, (Integer) pivotValue);
       // System.out.println(myList.toString());

        // adjust pivot index 
        pivotIndex = last;

        // set index pointers 
        int indexFromLeft = first;
        int indexFromRight = pivotIndex - 1;

        boolean done = false; // loop control 

        while (!done) {
            /*
             starting at the begining of the ArrayList,
             leave entries less than pivotValue
             hold index on values greater than pivotValue 
             */
            while ((int) myList.get(indexFromLeft) < pivotValue) {
                indexFromLeft++;
            }

            /* 
             starting at the end of the ArrayList, 
             leave entries greater than pivotValue
             hold index on value greater than pivotValue 
             */
            while ((int) myList.get(indexFromRight) > pivotValue
                    && indexFromRight > 0) {
                indexFromRight--;
            }

            // swap values of held indices 
            if (indexFromLeft < indexFromRight) {

                // note: .set method returns previous value of specified index 
                int leftvalue = (int) myList.set(indexFromLeft, myList.get(indexFromRight));
                myList.set(indexFromRight, (Integer) leftvalue);
                indexFromLeft++;
                indexFromRight--;
                //System.out.println(myList.toString()); 
            } else {
                done = true;
            }

        }
        /* 
         swap pivotValue with indexFromLeft value 
         last increment of indeces insures indexFromLeft
         is greater than pivotValue 
         */
        myList.set(pivotIndex, myList.get(indexFromLeft));
        myList.set(indexFromLeft, (Integer) pivotValue);
            // System.out.println(myList.toString());

        // reset pivot index 
        pivotIndex = indexFromLeft;
        return pivotIndex;
    }

    private static ArrayList quicksort(ArrayList myList, int first, int last) {

        // need at least two elements to sort
        if (!(last - first + 1 < 2)) {

            // returns final pisition of pivot index 
            int pivotIndex = partition(myList, first, last);

            // if pivotIndex is 0 or 1, nothing to its left needs to be sorted 
            if (pivotIndex > 1) {
                quicksort(myList, first, pivotIndex - 1);
            }

    // if pivotIndex is last or next-to-last element, nothing to its right 
            // needs to be sorted 
            if (pivotIndex < myList.size() - 2) {
                quicksort(myList, pivotIndex + 1, last);
            }

        }
        return myList;
    }

}
