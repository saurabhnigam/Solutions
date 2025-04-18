/****
Decode Variations
A letter can be encoded to a number in the following way:

'A' -> '1', 'B' -> '2', 'C' -> '3', ..., 'Z' -> '26'
A message is a string of uppercase letters, and it is encoded first using this scheme. For example, 'AZB' -> '1262'

Given a string of digits S from 0-9 representing an encoded message, return the number of ways to decode it.

Examples:

input:  S = '1262'
output: 3
explanation: There are 3 messages that encode to '1262': 'AZB', 'ABFB', and 'LFB'.
Constraints:

[time limit] 5000ms

[input] string S

1 ≤ S.length ≤ 12
[output] integer
***/

import java.io.*;
import java.util.*;

class Solution {

/**
1+x+x^2+x^3
     Root    1
    1    12  2
   2  26    26 2^2
6       2    2^3
2    



       R
    1        12
   2 26        6 
                2
  6    2
2

height =n
2^(n+1)
   **/ 
    



    //1262
    //01   
    //out 1 12 2 
    //1 2 6 2   
    //12 6 2
    //1 26 2
    // I am here just network problems kepp implementing


	static int decodeVariations(String S) {
        char[] charDigits = S.toCharArray();

        return backtrack(0,charDigits);
        


	}

    private static int backtrack(int i, char[] charDigits){
        if(i==(charDigits.length-1)){
            return 1;
        }
        if(i>(charDigits.length-1)){
            return 0;
        }
        int paths =0;
        //use current digit
        if(Integer.valueOf(""+charDigits[i]) >0){
            paths += backtrack(i+1, charDigits);
            }
        
        //append next digit
        // I am following dont add comments
        
        if((i+1)<(charDigits.length) && Integer.valueOf(""+charDigits[i]+charDigits[i+1]+"") <= 26){
            paths += backtrack(i+2, charDigits);
        }
        return paths;
    }

	public static void main(String[] args) {
	
	}
}


