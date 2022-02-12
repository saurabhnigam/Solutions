/****
Count Complete Tree Nodes
Solution
Given the root of a complete binary tree, return the number of the nodes in the tree.
According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
Design an algorithm that runs in less than O(n) time complexity.
 
Example 1:
Input: root = [1,2,3,4,5,6]
Output: 6
Example 2:
Input: root = []
Output: 0
Example 3:
Input: root = [1]
Output: 1
 
Constraints:
The number of nodes in the tree is in the range [0, 5 * 104].
0 <= Node.val <= 5 * 104
The tree is guaranteed to be complete.
****//**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//O(log(n)^2) solution bcoz halving the tree takes o(log n) steps & each step takes O(2logn) time (beacuse left & riht heih takes O logn time step)
class Solution {
    public int countNodes(TreeNode root) {
        return traverse(root,-1,-1);
    }
    private int traverse(TreeNode node, int left, int right){
        if(node == null){
            return 0;
        }
        if(left<0){
            left = getLeft(node);
        }
        if(right<0){
            right = getRight(node);
        }
        
        if(left==right){
            return (1<<left) -1; //equivalent to Math.pow(2,left) -1
        }else if(left == 0 && right ==0){
            return 1;
        }else{
            int sl = traverse(node.left, left-1, -1);
            int sr= traverse(node.right,-1, right-1);
            return sl+sr+1;
        }
        
        
    }
    
    private int getLeft(TreeNode n){
        if(n == null){
            return 0;
        }else{
            return getLeft(n.left)+1;
        }
    }
    
    private int getRight(TreeNode n){
        if(n == null){
            return 0;
        }else{
            return getRight(n.right)+1;
        }
    }
}
