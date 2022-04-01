/***5. Longest Palindromic Substring

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
s consist of only digits and English letters.***/

//Time Complexity - O(n^2)
//Space Complexity - O(1)

class LongestPalindromicSubstring {
    int max =0;
    String maxS= null;
    String s1=null;
    int n=0;
    public String longestPalindrome(String s) {
        if(s.length() >0){
            maxS =s.charAt(0)+""; 
            max =1;
        }
        s1=s;
        int l =0, r= l+2;
        
        //odd
        while(r<s.length()){
            checkP(l,r);
            l++;
            r++;
        }
        l =0;
        r= l+1;
        //even
        while(r<s.length()){
            checkP(l,r);
            l++;
            r++;
        }
        return maxS;
    }
    
    private void checkP(int l , int r){
        int n = s1.length();
        while(l>=0 && r<n && l<=r){
            if(s1.charAt(l)==s1.charAt(r)){
                int c = r-l+1;
                if(max < c){
                    max = c;
                    maxS = s1.substring(l,r+1);
                }
                l--;
                r++;
            }else{
                break;
            }
        }
    }

    
    
}
