/*****

22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 

Example 1:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:

Input: n = 1
Output: ["()"]
 

Constraints:

1 <= n <= 8
  
  TC : O(2^n)
  SC: O(n)
  ****/

class GenerateParentheses {
    List<String> possibleComb = new ArrayList();
    public List<String> generateParenthesis(int n) {
        possibleComb = new ArrayList();
        explore(1,0, "(", n);
        return possibleComb;
    }
    
    private void explore(int openCount, int closeCount, String visited, int n){  //
        if(closeCount == openCount && visited.length() == 2*n){
            possibleComb.add(visited);
            return;
        }

        if(openCount < n){
            explore(openCount+1, closeCount, visited +'(', n);
        }
        if(closeCount < openCount || openCount == n ){
            explore(openCount, closeCount+1, visited +')', n);
        }
    }
}
