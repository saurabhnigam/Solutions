/****7. Reverse Integer

Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

 

Example 1:

Input: x = 123
Output: 321
Example 2:

Input: x = -123
Output: -321
Example 3:

Input: x = 120
Output: 21
 

Constraints:

-231 <= x <= 231 - 1
TC(O (radix of x))
*****/
class ReverseInteger {
    public int reverse(int x) {  // TC O(radix of x)
        long y = 0;
        boolean isNegative = (x < 0);
        try{
            while(Math.abs(x)>0){
                y= (10*y) + (Math.abs(x) % 10);
                x= x/10;
                if(y > Integer.MAX_VALUE && y < Integer.MIN_VALUE){
                    return 0;
            }
            }
            if(isNegative){
                y= (-1*y);
            }
            if(y <= Integer.MAX_VALUE && y>= Integer.MIN_VALUE){
                return (int)y;
            }else{
                return 0;
            }
            
        }catch(Exception e){
            return 0;
        }
        
    }
}
