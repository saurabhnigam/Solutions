/***
42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

 

Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105

TC O(n)
****/

class TrappingRainWater {
    public int trap(int[] height) {
        //find max from left to right
        int[] leftMax = findLeftMax(height);
        //find left max
        int[] rightMax = findRightMax(height); 
        //min r,l
        int[] min = new int[height.length];
        for(int i=0; i< leftMax.length; i++){
            min[i] = Math.min(leftMax[i], rightMax[i]);
        }


        int trappedWater = 0;
        //result
        for(int i =0; i<height.length; i++){
            int water = min[i]-height[i];
            if(water >0){
                trappedWater+=min[i]-height[i];
            }
            
        }
        return trappedWater;
    }

    private int[] findLeftMax(int[] height){
        int[] leftmax = new int[height.length];
        int nextMax = height[0];
        leftmax[0] = height[0];
        for(int i= 1; i<height.length; i++){
            leftmax[i] = nextMax;
            nextMax = Math.max(nextMax, height[i]);
        }
        return leftmax;
    }

    private int[] findRightMax(int[] height){
        int[] rightmax = new int[height.length];
        int nextMax = height[height.length-1];
        rightmax[height.length-1] = height[height.length-1];
        for(int i= height.length-2; i>=0; i--){
            rightmax[i] = nextMax;
            nextMax = Math.max(rightmax[i], height[i]);
        }
        return rightmax;
    }
}
