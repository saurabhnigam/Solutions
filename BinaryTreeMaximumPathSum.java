/*****
124. Binary Tree Maximum Path Sum
Hard

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any non-empty path.

 

Example 1:


Input: root = [1,2,3]
Output: 6
Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
Example 2:


Input: root = [-10,9,20,null,null,15,7]
Output: 42
Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 

Constraints:

The number of nodes in the tree is in the range [1, 3 * 104].
-1000 <= Node.val <= 1000
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
 */

/***
TimeComplexity - O(n) as all nodes are traversed once
Space Complexity O(logn) as at a time only height of tree logn elements can remain in Stack
**/
class BinaryTreeMaximumPathSum {
    private int max = 0;
    public int maxPathSum(TreeNode root) {
        max = root.val;
        pathSum(root);
        return max;
    }

    public int pathSum(TreeNode root) {
        if(root == null){
            return 0;
        }
        int l = pathSum(root.left);
        int r = pathSum(root.right);
        //max with root node with either left or right subtree
        int currMax = Math.max(root.val+l, root.val+r);
        currMax = Math.max(currMax, root.val);
        max = Math.max(max, currMax);
        //max with root node with both child subtree
        max = Math.max(max, l + root.val + r);
        //if self is max val
        max = Math.max(max, root.val);
        return currMax;
    }
}
