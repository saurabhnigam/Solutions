/****329. Longest Increasing Path in a Matrix
Hard

7427

111

Add to List

Share
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).

 

Example 1:


Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:


Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
Example 3:

Input: matrix = [[1]]
Output: 1
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
0 <= matrix[i][j] <= 231 - 1

TC O(mn)
SC O(mn)
***/
class LongestIncreasingPathinaMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        Map<String, Integer> memo = new HashMap();
        int maxLength = 1;
        for(int i =0; i< matrix.length; i++){
            for(int j =0; j<matrix[0].length; j++){
                maxLength = Math.max(maxLength, explore(i, j, memo, matrix));
            }
        }
        
        return maxLength;
    }
    
    private int explore(int i , int j , Map<String, Integer> memo, int[][] matrix){
        int currMax = 1;
        String key = i+"_"+j;
        if(memo.containsKey(key)){
            return memo.get(key);
        }else{
            if(i< (matrix.length-1) && matrix[i+1][j] > matrix[i][j]){
                int res = explore(i+1, j, memo, matrix);
                currMax = Math.max(res+1, currMax);
            } 
            if(i>0 && matrix[i-1][j] > matrix[i][j]){
                int res = explore(i-1, j, memo, matrix);
                currMax = Math.max(res+1, currMax);
            } 
            if(j< (matrix[0].length-1) && matrix[i][j+1] > matrix[i][j]){
                int res = explore(i, j+1, memo, matrix);
                currMax = Math.max(res+1, currMax);
            } 
            if(j>0 && matrix[i][j-1] > matrix[i][j]){
                int res = explore(i, j-1, memo, matrix);
                currMax = Math.max(res+1, currMax);
            }
        }
        memo.put(key, currMax);
        return currMax;
    }
    
}
