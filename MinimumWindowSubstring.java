/****
76. Minimum Window Substring
Given two strings s and t of lengths m and n respectively, return the minimum window 
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

 

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
 

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 

Follow up: Could you find an algorithm that runs in O(m + n) time?
*****/

class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> m = new HashMap();
        Map<Character, Integer> window = new HashMap();
        //preprocess & create word freq map from target
        for(int i =0;i<t.length();i++){ // O(m)
            int c1 = m.getOrDefault(t.charAt(i), 0);
            m.put(t.charAt(i), c1+1);
            window.put(t.charAt(i), 0);
            
        }
        int count =0;
        int min =s.length()+1;
        int start =0;
        String minS= "";
        for(int end =0; end<s.length(); end++){ // O(2n)
            if(m.containsKey(s.charAt(end))) {
                if(window.get(s.charAt(end))<m.get(s.charAt(end))) {
                    count++;
                }
                window.put(s.charAt(end), window.get(s.charAt(end))+1);
            }

            if(count ==t.length()){
                while(!m.containsKey(s.charAt(start)) || window.get(s.charAt(start))> m.get(s.charAt(start))){ // O(n)
                    window.put(s.charAt(start), window.getOrDefault(s.charAt(start), -1)-1);

                    start++;
                }
                if(end-start+1 < min){
                    
                    min = end- start+1 ;
                    minS = s.substring(start, start+min);
                    
                }
                
                window.put(s.charAt(start), window.get(s.charAt(start))-1);
                start++;
                count--;
            }
        }
        return minS;
    }
}
