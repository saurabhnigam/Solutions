/****242. Valid Anagram


Share
Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
 

Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?****/

//Time Complexity : O(n^2) as Strig.replace function takes O(n)TC(best of KMP algorithm)
//TC can be improved by using HasMap with char->occurrence count
//Space Complexity : O(1)
class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        for(int i=0;i<s.length();i++){
            if(t==null || t== ""|| t.length()==0){
                return false;
            }else{
               t= t.replaceFirst(""+s.charAt(i),"");
            }
        }
        if(t==null || t== ""|| t.length()==0)
            {
            return true;
        }
        return false;
    }
}
