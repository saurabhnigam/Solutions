/****
20. Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:

Input: s = "()"
Output: true
Example 2:

Input: s = "()[]{}"
Output: true
Example 3:

Input: s = "(]"
Output: false
 

Constraints:

1 <= s.length <= 104
s consists of parentheses only '()[]{}'.

TC : O(n)
SC: O(1)
*****/
class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> brackets = new Stack();
        Map<Character, Character> bracketMap = new HashMap();
        bracketMap.put('}', '{');
        bracketMap.put(')', '(');
        bracketMap.put(']', '[');

        for(char bracket : s.toCharArray()){
            if(bracketMap.containsKey(bracket) && !brackets.isEmpty() && brackets.peek() == bracketMap.get(bracket)){
                //if top most element's closing bracket is matching then pop'
                brackets.pop();       
            }else{
                //push all open brackets
                brackets.push(bracket);
            }
        }
        //if all elements are popped then this is avalid  parentehses
        return brackets.size() == 0;
    }
}
