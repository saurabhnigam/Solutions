/***
394. Decode String
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
***/

class DecodeString {
    public String decodeString(String s) {  //TC O(Length of Output String) as we need to repeat for max_digit times SC O(n)
        Stack<String> stack = new Stack();
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c != ']'){
                stack.push(String.valueOf(c));  //O(1)
            }else{
                StringBuilder curSB = new StringBuilder();
                while(!stack.isEmpty() && !stack.peek().equals("[")){
                    curSB.append(stack.pop());
                }
                stack.pop(); //pop '['
                
                //find the number
                StringBuilder digitStr = new StringBuilder();
                while(!stack.isEmpty() && isNumeric(stack.peek())){
                    digitStr.append(stack.pop());
                }
                int d = Integer.valueOf(digitStr.reverse().toString());
                String curString = curSB.toString();
                StringBuilder newSB = new StringBuilder();
                //repeat curString d times
                for(int k =0; k<d; k++){
                    newSB.append(curString);
                }
                stack.push(newSB.toString());
            }
        }
        
        StringBuilder finalSB = new StringBuilder();
        while(!stack.isEmpty()){
            finalSB.append(stack.pop());
        }
        
        return finalSB.reverse().toString();
        
    }
    
    private boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
