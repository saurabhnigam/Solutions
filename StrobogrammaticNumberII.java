/***Strobogrammatic Number II

DescriptionSolutionNotesDiscussLeaderboard
Description
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
Find all strobogrammatic numbers that are of length = n.

Example
Example 1:

Input: n = 2, 
Output: ["11","69","88","96"]
Example 2:

Input: n = 1, 
Output: ["0","1","8"]***/

//Time Complexity O(5^(n/2))
//Space COmplexity O(n) for recursion stack
public class StrobogrammaticNumberII {
    /**
     * @param n: the length of strobogrammatic number
     * @return: All strobogrammatic numbers
     */
    
    int[] inputInts = {0,1,8,6,9};
    int[] rotatedInts = {0,1,8,9,6};
    public List<String> findStrobogrammatic(int n) {
        List<String> strobogrammaticList = new ArrayList<>();
        if(n==0){
            strobogrammaticList.add("");
            return strobogrammaticList;
        }
        strob(n-1, 0, 0, n, strobogrammaticList);
        return strobogrammaticList;
    }

    private void strob(int start, int end, int value1, int n, List<String> strobogrammaticList){
        if(start <end){
                strobogrammaticList.add(""+value1);
                return;
        }
        for(int i=0;i<inputInts.length; i++){
            int value = value1;
            if(start == (n-1) && n!=1 && inputInts[i]==0){
                continue;//skip 0 at start of strob String
            }

            if(start != end ){
                value+=(Math.pow(10,start)*inputInts[i])+(Math.pow(10,end)* rotatedInts[i]);
                strob(start-1, end +1, value, n, strobogrammaticList);
            }else if(start == end && inputInts[i] != 6 && inputInts[i]!=9){                
                value+=Math.pow(10,start)*inputInts[i];
                strobogrammaticList.add(""+value);
            }
        }
    }
}
