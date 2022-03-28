/***
17. Letter Combinations of a Phone Number

Share
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
****/


//Time COmplexity 4^N
//Space Complexity 4^N
class LetterCombinationsofPhoneNumber {
    Map<String, List<String>> cache = new HashMap();
    Map<Character, List<String>> keymap = new HashMap();
    
    public List<String> letterCombinations(String digits) {
        keymap.put('2', Arrays.asList("a","b","c"));
        keymap.put('3', Arrays.asList("d","e","f"));
        keymap.put('4', Arrays.asList("g","h","i"));
        keymap.put('5', Arrays.asList("j","k","l"));
        keymap.put('6', Arrays.asList("m","n","o"));
        keymap.put('7', Arrays.asList("p","q","r","s"));
        keymap.put('8', Arrays.asList("t","u","v"));
        keymap.put('9', Arrays.asList("w","x","y","z"));
        
        return findCombinations(0, digits);
    }
    
    private List<String> findCombinations(int k , String digits){
        String remaining = digits.substring(k);
        
        if(cache.containsKey(remaining)){
            return cache.get(remaining);
        }
        List<String> combList = new ArrayList();
        
        if(k<digits.length()){
            char currKey = digits.charAt(k);
            for(String key: keymap.get(currKey)){
                List<String> combinations = findCombinations(k+1, digits);
                if(combinations.isEmpty()){
                    return keymap.get(currKey);
                }
                for(String combination:combinations){
                    combList.add(key+combination);
                }
                
            }
        }
        cache.put(remaining, combList);
        
        return combList;
    }
}
