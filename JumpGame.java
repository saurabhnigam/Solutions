/****
55. Jump Game

Companies
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
***/

class JumpGame {
    public boolean canJump(int[] a) {
        boolean[] d = new boolean[a.length];
        int n= a.length;
        for(int i =n-1;i>=0;i--){ // O(n^2)
            if(i==n-1){
                d[i] = true;
            }else if(a[i]>=n-1-i){
                d[i] = true;
            }else{
              //find last position where I can reach which can reach end
                for(int j =i;j<=(i+a[i]);j++){ //O(n)
                    if(d[j]){
                        d[i] = true;
                        break;
                    }
                }
            }
        }
        return d[0];
    }
    
}
