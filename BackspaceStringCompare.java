/***Backspace String Compare

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

 

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
 

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.
 

Follow up: Can you solve it in O(n) time and O(1) space? 
***/

class Solution {
    public boolean backspaceCompare(String s, String t) {
        int endS = s.length()-1, endT= t.length()-1;
        int toRemove=0;
        while(endS>=0 || endT >=0){
            while(endS>=0){
                if(s.charAt(endS)=='#'){
                    toRemove++;
                }else if(toRemove>0){
                    toRemove--;
                }else{
                    break;
                }
                endS--;
            } 
            toRemove =0;
             while(endT>=0){
                if(t.charAt(endT)=='#'){
                    toRemove++;
                }else if(toRemove>0){
                    toRemove--;
                }else{
                    break;
                }
                endT--;
            }
            if(endS>=0 && endT>=0 && s.charAt(endS)==t.charAt(endT)){
                endS--;
                endT--;
            }else if(endS == -1 && endT ==-1){
                return true;
            }else{
                return false;
            }
        }
        
        return true;
    }
    

}
