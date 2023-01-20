/*******
2. Add Two Numbers
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

Example 1:


Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:

Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:

Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 

Constraints:

The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
******/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

//TC O(max(l1,l2))
//SC O(max(l1,l2))
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int cf =0;
        ListNode l3 = null;
        ListNode merged = null;
        while(l1 != null || l2 != null){
            int l1Val = 0, l2Val =0;
            if(l1 != null){
                l1Val = l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                l2Val =l2.val;
                l2 = l2.next;
            }
            
            int sum = l1Val+l2Val+cf;
            if(sum > 9){
               sum = sum % 10;
               cf = 1; 
            }else{
                cf =0;
            }
            //if first node 
            if(l3 == null){
                l3 = new ListNode(sum);
                merged = l3;
            }else{
                l3.next = new ListNode(sum);
                l3 = l3.next;
            }
            

        }

      //1 extra node for carry forward
        if(cf > 0){
            l3.next = new ListNode(cf);
        }
        return merged;
    }
}
