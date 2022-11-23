/****
247. Strobogrammatic Number II

Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

 

Example 1:

Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]
 

Constraints:

1 <= n <= 14
TC: 5^(2(N/2)) beacuse max 5 child per node & 2 for loops in each node so 5^2 & height of tree will be N/2 as we populate start& end at one time
*****/

class StrobogrammaticNumberII2ndAttempt{
    int[][] strobs = {
        {1,1}, 
        {8,8}, 
        {0,0}, 
        {6,9}, 
        {9,6}};
    int target = 0;
    public List<String> findStrobogrammatic(int n){
        target = n;
        return explore(n);
    }
    public List<String> explore(int n) {
        if(n <= 0){
            return Arrays.asList("");
        }else if(n ==1){
            List<String> list = Arrays.asList("1", "8", "0");
            return list;
        }
        
        List<String> strobsOfN = explore(n-2);
        List<String> created = new ArrayList();

        for(int k = 0 ; k<strobs.length; k++){
            for(int i = 0; i<strobsOfN.size(); i++){
                if(target == n && strobs[k][0] == 0){
                    continue; //skip 0 at the start
                }else{
                    created.add(strobs[k][0]+""+strobsOfN.get(i) + strobs[k][1]);
                }
            }
        }
        return created;
    }
    
    
}
