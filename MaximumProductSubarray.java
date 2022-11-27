/****
152. Maximum Product Subarray

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

TC : O(n)
SC: O(1)
****/

class MaximumProductSubarray {
    public int maxProduct(int[] nums) { //TC O(n)
        int max = nums[0], min = nums[0], overAllMax = nums[0];
        for(int i =1; i<nums.length; i++){
            //if value is negative swap max & min
            int cmin = min, cmax = max;
            if(nums[i] < 0){
                cmin = max;
                cmax = min;
            }
            max = Math.max(nums[i], cmax*nums[i]);
            min = Math.min(nums[i], cmin*nums[i]);
            overAllMax = Math.max(max, overAllMax);
        }
        return overAllMax;
    }
}
