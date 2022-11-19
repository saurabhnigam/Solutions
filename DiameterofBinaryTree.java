/******
543. Diameter of Binary Tree

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

 

Example 1:


Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
Example 2:

Input: root = [1,2]
Output: 1
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100

*****/


/**
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
 
 
 TC : O(V+E) since this is a DFS
 SC : O(V) Stack size = height of tree . Height could be V
 */
class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        if(root == null)
            return 0;
        explore(root);
        return max;
    }
    
    private int explore(TreeNode node){
        if(node == null){
            return 0;
        }else{
            int l = explore(node.left);
            int r = explore(node.right);
            max = Math.max(l+r , max);
            return Math.max(r,l)+1;
        }
    }
}
