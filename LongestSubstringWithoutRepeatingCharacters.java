/***
3. Longest Substring Without Repeating Characters

Given a string s, find the length of the longest 
substring
 without repeating characters.

 

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.

TC :O(n)
***/

class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> h = new HashMap();
        int max =0;
        for(int i =0; i<s.length();i++){
            char c = s.charAt(i);
            if(h.containsKey(c)){
                
                i = h.get(c);
                h.clear();
            }else{
                h.put(c, i);
            }
            max = Math.max(max, h.size());
        }
        return max;
    }
}
