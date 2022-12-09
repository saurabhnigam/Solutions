/****
15. 3Sum
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

 

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
 

Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
*****/
class 3Sum {
    public List<List<Integer>> threeSum(int[] nums) { //O(n^2)
        Set<String> resSet = new HashSet();
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList();
        for(int i =0; i<nums.length; i++){
            twoSum(i, nums, result, resSet);
        }
        return result;
    }

    private void twoSum(int i , int[] nums, List<List<Integer>> res, Set<String> resSet ){ //O(n)
        Map<Integer, Integer> freqMap = new HashMap();
        int target = -1*nums[i];
        for(int j=i; j<nums.length; j++){
            if(i==j){
                continue;
            }
            int freq = freqMap.getOrDefault(nums[j], 0);
            freqMap.put(nums[j], freq+1);
        }

        for(int j = i; j<nums.length-1; j++){
            if(i==j){
                continue;
            }
            decrement(freqMap, nums[j]);
            if(freqMap.containsKey(target-nums[j]) && resSet.add(nums[i]+"_"+nums[j]+"_"+(target-nums[j]))){
                decrement(freqMap, target-nums[j]);
                List<Integer> list = new ArrayList();
                list.add(nums[i]);
                list.add(nums[j]);
                list.add(target-nums[j]);
                res.add(list);
            }
        }
    }

    private void decrement(Map<Integer, Integer> freqMap, int key){
        int freq = freqMap.getOrDefault(key, 0);
        if((freq-1) > 0){
            freqMap.put(key, freq-1);
        }else{
            freqMap.remove(key);
        }
    }
}
