/*****
5. Longest Palindromic Substring

Given a string s, return the longest palindromic substring in s.

 

Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
 

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.

TC: O(n^2)
SC: O(1)
****/

class Solution {
    String maxString = "";
    int max = 1;
    public String longestPalindrome(String s) {
        maxString = s.charAt(0) +"";
        max =1;
        int  start = 0, end = s.length()-1;
        for (int i =0; i<s.length()-1; i++){
            //odd
                lenPalindrome(i, i+1, s);
            //even
                lenPalindrome(i-1, i+1, s);
            
        }
        return maxString;
    }
    
    private void lenPalindrome(int start, int end, String s){
        while(start >=0 && end < s.length() && s.charAt(start) == s.charAt(end)) {

            if((end-start+1) > max && isValid(start, end, s)){
                    max = (end-start+1);
                    maxString= s.substring(start, end+1);
            }  
            start = start-1;
            end = end+1;
                
        }
        
    }
    
    private boolean isValid(int start, int end, String s){
        return !(start < 0 || start >= s.length() || end >= s.length());
    }
    
}
