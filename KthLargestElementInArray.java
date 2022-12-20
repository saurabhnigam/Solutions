/***
215. Kth Largest Element in an Array

Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

You must solve it in O(n) time complexity.

 

Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
 

Constraints:

1 <= k <= nums.length <= 105
-104 <= nums[i] <= 104

https://stackoverflow.com/questions/70025649/heapify-vs-heap-sort-vs-construct-heap
****/

class KthLargestElementInArray {
    //TC O(n + klogn)
    public int findKthLargest(int[] nums, int k) {
        // n-1th index parent ((n-1)-1)/2
        int lastNonLeaf = nums.length/2 -1;
        for(int i =lastNonLeaf ; i>=0; i--){ // O(n)
            heapify(nums, nums.length, i); 
        }

        //remove elements
        for(int i=nums.length-1; i>=(nums.length-k) ; i--){ //O(klogn)
            remove(nums, i);
        }

        // nums is sorted in ascending
        return nums[nums.length-k];
    }

    private void heapify(int[] nums, int n ,int i){ // O(n)
        int largest = i; //assume i is largest
        int leftChild = 2*i+1;
        int rightChild = 2*i+2;

        if(leftChild < n && nums[leftChild] > nums[largest]){
            largest = leftChild;
        }
        if(rightChild < n && nums[rightChild] > nums[largest]){
            largest = rightChild;
        }

        if(largest != i){
            //swap i & largest
            int temp = nums[i];
            nums[i] = nums[largest];
            nums[largest] = temp;
            //heapify the replaced item(left or right subtree) further down 
            //sift down op https://stackoverflow.com/questions/70025649/heapify-vs-heap-sort-vs-construct-heap
            heapify(nums, n, largest);
        }

    }

    private void remove(int[] nums, int i){  //O(logn)
        //move top most element at 0 to last
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            heapify(nums, i , 0); // this will traverse to tree height logn
    }
}
