/****
66. Plus One
Easy
6K
4.6K
Companies
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

 

Example 1:

Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].
Example 2:

Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
Example 3:

Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].
 

Constraints:

1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
*****/
class PlusOne {
    int cf =0;
    public int[] plusOne(int[] d) {
        cf =0;
        add(d.length -1, d);
        //recreating the array if length needs to be increased
        if(cf ==1){
            int[] n = new int[d.length+1] ;
            n[0] =1;
            for(int i=1; i<n.length;i++){
                n[i] = d[i-1];       
            }
            return n;

        }else{
            return d;
        }
    }
    private void add(int i, int[] a){
        if(a[i] < 9){
            a[i] = a[i]+1;
            
            
        }else if(a[i] ==9){
            if(i>0){
                add(i-1,a);
            }
            a[i] =0;
            if(i== 0){
                cf=1;
            }
            
        }
    }
}
