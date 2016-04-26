
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
        
        ArrayList<Integer> myList = new ArrayList<>();
        
        File input = new File("input.txt");
        
       // read in content of input.txt to an ArrayList 
       try (Scanner readfile = new Scanner(input)){
          
        while(readfile.hasNextInt()){
            myList.add(readfile.nextInt());  
        }
        
        System.out.println(myList.toString()); // comment out 
        
       }catch(FileNotFoundException e){
           System.out.println(e.getMessage()); 
           System.exit(1);  // exit program with error of 1
               }
       
       quicksort(myList, 0, myList.size() - 1); 
       
        System.out.println(myList.toString()); // comment out 
        
        // output sorted ArrayList to output.txt
    }
    
    
    private static int partition(ArrayList myList, int first, int last){
        
       // randomly choose pivot index 
       Random rand = new Random(); 
       int pivotIndex = rand.nextInt((last - first) + 1) + first; 
       System.out.println(pivotIndex);  // comment out 
       
       // set integer value of the pivot object
       // and swap pivot element  and last element 
       // note: .set method returns previos value of specified index 
       // if pivot element is already last element it replaces itself with itself 
       int pivotValue = (int)myList.set(pivotIndex, myList.get(last)); 
       myList.set(last, (Integer)pivotValue); 
        System.out.println(myList.toString()); // comment out 
       
       // adjust pivot index 
       pivotIndex = last; 
       
        int indexFromLeft = first; 
        int indexFromRight = pivotIndex - 1; 
        
        boolean done = false; 
        
        while(!done){
            // starting at the begining of the ArrayList,
            //leave entries less than pivotValue
            // hold index on values greater than pivotValue 
            while((int)myList.get(indexFromLeft) < pivotValue)
                  indexFromLeft++; 
            
            // starting at the end of the ArrayList, 
            // leave entries greater than pivotValue
            // hold index on value greater than pivotValue 
            while((int)myList.get(indexFromRight) > pivotValue 
                    && indexFromRight > 0)
                indexFromRight--; 
            
            // swap values of held indices 
            if(indexFromLeft < indexFromRight){
             
             // note: .set method returns previous value of specified index 
              int leftvalue = (int)myList.set(indexFromLeft, myList.get(indexFromRight));
              myList.set(indexFromRight, (Integer)leftvalue); 
              indexFromLeft++; 
              indexFromRight--; 
                System.out.println(myList.toString()); // comment out
            }
            
            else
                done = true; 
               
        }
             // swap pivotValue with indexFromLeft value 
             // last increment of indeces insures indexFromLeft
             // is greater than pivotValue 
             myList.set(pivotIndex, myList.get(indexFromLeft)); 
             myList.set(indexFromLeft, (Integer)pivotValue); 
             System.out.println(myList.toString());// comment out 
             // reset pivot index 
             pivotIndex = indexFromLeft; 
            return pivotIndex;
        } 
    
    
    private static ArrayList quicksort(ArrayList myList, int first, int last){
        
    if(last - first + 1 < 2){
        System.out.println("less than 2");
    }
    
    else{
        
    int pivotIndex = partition(myList, first, last); 
    
    if(pivotIndex > 1){
    quicksort(myList, first, pivotIndex - 1);
    }
    
    if(pivotIndex < myList.size()-1){
    quicksort(myList, pivotIndex + 1, last); 
    }
    
    }
      return myList;  
    }
    
}
