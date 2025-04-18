/****
TC O(mn)
SC O(1)
Matrix Spiral Copy
Given a 2D array (matrix) inputMatrix of integers, create a function spiralCopy that copies inputMatrix’s values into a 1D array in a spiral order, clockwise. Your function then should return that array. Analyze the time and space complexities of your solution.

Example:

input:  inputMatrix  = [ [1,    2,   3,  4,    5],
                         [6,    7,   8,  9,   10],
                         [11,  12,  13,  14,  15],
                         [16,  17,  18,  19,  20] ]

output: [1, 2, 3, 4, 5, 10, 15, 20, 19, 18, 17, 16, 11, 6, 7, 8, 9, 14, 13, 12]
See the illustration below to understand better what a clockwise spiral order looks like.

Spiral matrix

Constraints:

[time limit] 5000ms

[input] array.array.integer inputMatrix

1 ≤ inputMatrix[0].length ≤ 100
1 ≤ inputMatrix.length ≤ 100
[output] array.integer
***/


import java.io.*;
import java.util.*;

class MatrixSpiralCopy {

  static int[] spiralCopy(int[][] inputMatrix) {
    // your code goes here

    int mr = inputMatrix.length; //vertical
    int nc = inputMatrix[0].length; // horizontal

    int[] res = new int[mr*nc];

    int r=0;
    int c=0;
    int resIndex =0;

    //populate first element
    if(mr > 0 && nc >0){
      res[resIndex++] = inputMatrix[r][c];
    }

    for(int iter =0 ; iter< mr ;iter++){
        System.out.println(inputMatrix[r][c]);

        //keep moving right till n-iter 
        while((c+1)<(nc-iter) && r<mr){
            c++;
            res[resIndex++] = inputMatrix[r][c];
        }   
        if(resIndex == (mr*nc)){
            //base case
            return res;
        }
        //keep moving down till m-iter
        while((r+1)<(mr-iter) && c<nc && r<mr){
            r++;
            res[resIndex++] = inputMatrix[r][c];
            //System.out.println(inputMatrix[x][y]);

        }
        if(resIndex == (mr*nc)){
            //base case
            return res;
        }
        //keep moving left till iter
        while((c-1)>=iter && c<nc && r<mr){
            c--;
            res[resIndex++] = inputMatrix[r][c];
            //System.out.println(inputMatrix[x][y]);

        }
        if(resIndex == (mr*nc)){
            //base case
            return res;
        }
        //keep moving up til y-- iter+1
        while((r-1)>=(iter+1) && c<nc && r<mr){
            r--;
            res[resIndex++] = inputMatrix[r][c];
            //System.out.println(inputMatrix[x][y]);

        }  
        if(resIndex == (mr*nc )){
            //base case
            return res;
        }
        
    }
    return res;
  }

  public static void main(String[] args) {
    // debug your code below
    int[][] inputMatrix = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    int[] result = spiralCopy(inputMatrix);
    for (int num : result) {
        System.out.print(num + " ");
    }
  }
}


/***
bool visited[][] 

for(iter 0 to m){
    keep moving right till n-iter 
    while(x<(n-iter)){
        x++
    }   
    keep moving down till m-iter
    keep moving left till iter

    keep moving up til y-- iter+1
}



iter =0

for(int i=0; i<m; i++){

    for(int j=0; j<n ; j++){


    }
}
**/
