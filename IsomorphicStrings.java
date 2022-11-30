/*****
205. Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

 

Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true
 

Constraints:

1 <= s.length <= 5 * 104
t.length == s.length
s and t consist of any valid ascii character.

TC O(n)

****/
class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> charMap = new HashMap(); //mapping
        Set<Character> charValues = new HashSet(); //track which values have been mapped
        for(int i =0; i<s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            
            if(charMap.containsKey(a)){
                if(charMap.get(a)!= b){
                    return false; //mapping changed
                }
            }else{
                if(!charValues.add(b)){
                    return false;
                }
                charMap.put(a,b);
            }
        }
        return true;
    }
}
