/***
Pairs with Specific Difference
Given an array arr of distinct integers and a nonnegative integer k, write a function findPairsWithGivenDifference that returns an array of all pairs [x,y] in arr, such that x - y = k. If no such pairs exist, return an empty array.

Note: the order of the pairs in the output array should maintain the order of the y element in the original array.

Examples:

input:  arr = [0, -1, -2, 2, 1], k = 1
output: [[1, 0], [0, -1], [-1, -2], [2, 1]]

input:  arr = [1, 7, 5, 3, 32, 17, 12], k = 17
output: []
Constraints:

[time limit] 5000ms

[input] array.integer arr

0 ≤ arr.length ≤ 100
[input]integer k

k ≥ 0
[output] array.array.integer

**/

import java.io.*;
import java.util.*;

class PairsWithSpecificDifference {

  static int[][] findPairsWithGivenDifference(int[] arr, int k) {
     //add to Hashmap
     Set<Integer> elements = new HashSet();
     List<int[]>  result = new ArrayList();
     for(int i=0;i<arr.length;i++){
       elements.add(arr[i]);
     }
    
    //traverse & find out possible  value]
    
    for(int i=0; i<arr.length; i++){
      int x = k-arr[i];
      if(elements.contains(y)){
        int[] t = new int[2];
        t[1]=arr[i];
        t[0]=x;
        
        result.add(t);;
      }
    }
    
    int[][] r = new int[result.size()][2];
    for(int l =0; l<result.size();l++){
      r[l]=result.get(l);
    }
    
    return r;
  }

  public static void main(String[] args) {
    int[] arr = {0, -1, -2, 2, 1};
    int k = 1;
      findPairsWithGivenDifference(arr, k);
  }

}
/*
input:  arr = [0, -1, -2, 2, 1], k = 1   
     a[i]-k   -1, -2, -3, 1,  0
  
  
  a[i]-k =y  y should in between i+1 .. n    
  [0,-1], [-1, -2] [2,1][1,0]
  [0,1][1,0]
  
  []
     

Input:
 
[0,-1,-2,2,1], 1
Expected:
 
[[1,0],[0,-1],[-1,-2],[2,1]]
Actual:
 
[[0, -1], [-1, -2], [2, 1], [1, 0]]



x-y =k
x-k =y
x= k-a[i] */
