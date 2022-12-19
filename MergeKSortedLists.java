/****
23. Merge k Sorted Lists

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.

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
class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }
        ListNode mergedNodeHead = lists[0];
        for(int i=1; i< lists.length; i++){
                mergedNodeHead = merge(mergedNodeHead, lists[i]);
        }

        return mergedNodeHead;
    }

    private ListNode merge(ListNode l1, ListNode l2){
        ListNode mergedNodeHead = null;
        ListNode currHead = mergedNodeHead;

        //if any one is empty return other
        if(l1 == null && l2 != null){
            return l2;
        }else if(l1 != null && l2 == null){
            return l1;
        }
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                currHead = generateNode(currHead, l1);
                l1 = l1.next;
            }else{
                currHead= generateNode(currHead, l2);
                l2 = l2.next;
            }

            //if merged node has no head
            if(mergedNodeHead == null){
                mergedNodeHead = currHead;
            }
            if(currHead != null && currHead.next != null){
                currHead = currHead.next;
            }
        }

        //add all remaining entries of l1 to merged node's curr head
        if(l1 != null && currHead != null){
            currHead.next = l1;
        }
        
        //add all remaining entries of l1 to merged node's curr head
        if(l2 != null && currHead != null){
            currHead.next = l2;
        }

        return mergedNodeHead;
    }

    private ListNode generateNode(ListNode parent, ListNode child){
        if(parent == null){
                parent = new ListNode(child.val);
        }else{
            parent.next = new ListNode(child.val);
        }
        return parent;
    }
}
