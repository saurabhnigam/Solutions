/***
First Unique Character in a String

Share
Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

 

Example 1:

Input: s = "leetcode"
Output: 0
Example 2:

Input: s = "loveleetcode"
Output: 2
Example 3:

Input: s = "aabb"
Output: -1
 

Constraints:

1 <= s.length <= 105
s consists of only lowercase English letters.
****/

class GetUniqueCharacter{class Solution {
    public int firstUniqChar(String s) {
        char[] ca = s.toCharArray();
        Map<Character, Integer> f = new HashMap();
        for(int k =0;k<ca.length;k++){
            char c = ca[k];
            if(f.containsKey(new Character(c))){
                f.put(new Character(c),-1);
            }else{
                f.put(new Character(c),k);
            }
        }
                            
        for(int k =0;k<ca.length;k++){
           char c = ca[k];

            if(f.get(new Character(c))>=0){
                return f.get(new Character(c));
        }
        }   
               return -1;
                            
    }
}

