/*****169. Majority Element
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

 

Example 1:

Input: nums = [3,2,3]
Output: 3
Example 2:

Input: nums = [2,2,1,1,1,2,2]
Output: 2
 

Constraints:

n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109
 

Follow-up: Could you solve the problem in linear time and in O(1) space?****/
class MjorityElement{
//Moore's Voting Algorithm
      public int majorityElement(int[] a) {
          //Cancel every other int by another int so that only remaining is amjority bcoz majority elem will kill any other elem bcoz i ti ocurring more than n/2 times
          int majorityElem = a[0];
          int counter = 0;
          int size = a.length;
          for(int i =0;i<size;i++){
              if(majorityElem ==  a[i]){
                  counter++;
              }else{
                  counter--;
                  if(counter == 0){
                      counter = 1;
                      majorityElem = a[i];
                  }
              }
          }
          //now only remaining will be maority elem
          return majorityElem;
      }
    }
