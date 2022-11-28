/*****
322. Coin Change

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

 

Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0
 

Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
//TC O(n*amount)
//SC O(amount)
******/

class CoinChange {
    Map<Integer, Integer> cache = new HashMap();
    public int coinChange(int[] coins, int amount) { //TC O(n*amount)
        cache = new HashMap();
        
        for(int coin : coins){
            cache.put(coin, 1); // min steps required to reach coin is 1 (base case)
        }
        cache.put(0,0);
        
        return minCoins(coins, amount);
        
    }
    
    private int minCoins(int[] coins, int remaining){
        if(cache.containsKey(remaining) ){
            return cache.get(remaining);
        }else{
            int currMin = Integer.MAX_VALUE;
            boolean minFound = false;
            for(int coin: coins){
                if(remaining >= coin){
                    int placedCoins = minCoins(coins, remaining - coin);
                    if((placedCoins+1) < currMin && placedCoins >= 0){
                        currMin = placedCoins+1;
                        minFound = true;
                    }
                }
            }
            if(!minFound){ // if no value is less than Integer.MAX_VALUE
                cache.put(remaining, -1);
                return -1;
            }else{
                cache.put(remaining, currMin);
            }
            return currMin;
        }
        
    }
}
