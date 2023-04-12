/***
33. Search in Rotated Sorted Array
Medium
20.8K
1.2K
company
Bloomberg
company
Amazon
company
Apple
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
 

Constraints:

1 <= nums.length <= 5000
-104 <= nums[i] <= 104
All values of nums are unique.
nums is an ascending array that is possibly rotated.
-104 <= target <= 104

TC - O(logn)
SC O(1)
*****/

class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
    
    int start = 0;
    int end = nums.length-1;
    
    
    
    return search(nums, start, end, target);
  }
  
  private static int search(int[] shiftArr, int start, int end, int num){
    if(start >end){
      return -1;
    }
    
    int mid = (start + end)/2;
    
    System.out.println("start="+start+" mid="+mid+" startValue"+ shiftArr[start]+" end = "+end);
    if(shiftArr[start] == num){
      return start;
    }
    if(shiftArr[mid] == num){
      return mid;
    }
    if(shiftArr[end] == num){
      return end;
    }
    
    //if left is unsorted  right is sorted
    if(shiftArr[start] >= shiftArr[mid] ){
      //search num in right part
      if((num >= shiftArr[mid]) && (num <= shiftArr[end]) ){
        //lookup in right sorted tree
        return search(shiftArr, mid+1, end-1, num);
      }else{
        //look in the left
        return search(shiftArr, start+1, mid-1, num);
      }
    }else {
      //left is sorted
      if(num >= shiftArr[start] && num <= shiftArr[mid] ){
        //lookup in left sorted tree
        return search(shiftArr, start+1, mid-1, num);
      }else{
        //look in the left
        return search(shiftArr, mid+1, end-1, num);
      }
    }
    
  }
}
