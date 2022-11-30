/****
246. Strobogrammatic Number

Given a string num which represents an integer, return true if num is a strobogrammatic number.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 

Example 1:

Input: num = "69"
Output: true
Example 2:

Input: num = "88"
Output: true
Example 3:

Input: num = "962"
Output: false
 

Constraints:

1 <= num.length <= 50
num consists of only digits.
num does not contain any leading zeros except for zero itself.
***/

class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap();
        map.put('6', '9');
        map.put('9', '6');
        map.put('1', '1');
        map.put('8', '8');
        map.put('0', '0');
        
        List<Character> singleChars = new ArrayList();
        singleChars.add('0');
        singleChars.add('1');
        singleChars.add('8');
        
        for(int i = 0; i <= (num.length()/2); i++){
            
            if(num.length() %2 != 0 && i == (num.length()/2) && !singleChars.contains(num.charAt(i))){
                return false;
            }else if(!map.containsKey(num.charAt(i)) || map.get(num.charAt(i)) != num.charAt(num.length()-i-1)){
                return false;
            }
        }
        return true;
    }
}
