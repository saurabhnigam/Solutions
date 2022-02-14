/****
Evaluate Division

Solution
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

 

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation: 
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
 

Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
***/

class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Integer> map = new HashMap();
        int index =0;
        
        
        for(int i =0;i<equations.size();i++){
            List<String> eq = equations.get(i);
            if(!map.containsKey(eq.get(0))){
                map.put(eq.get(0),index++);
            }
            if(!map.containsKey(eq.get(1))){
                map.put(eq.get(1),index++);
            }
        }
        
        double[][] a = new double[index][index];

        for(int i=0;i<a.length;i++){
            Arrays.fill(a[i],-1);
        }
        for(int i =0; i<equations.size();i++){
             List<String> eq = equations.get(i);
             a[map.get(eq.get(0))][map.get(eq.get(1))] = values[i];
             a[map.get(eq.get(1))][map.get(eq.get(0))] = 1/values[i];

        }
        
        for(int i=0;i<a.length;i++){
            a[i][i] = 1;
        }
        
        System.out.println("a ="+Arrays.deepToString(a));
        double[] results = new double[queries.size()];
        Arrays.fill(results,-1);
        for(int i=0;i<queries.size();i++){
            List<String> q = queries.get(i);
            String d1= q.get(0);
            String d2= q.get(1);
            
            int x1 = map.getOrDefault(d1,-1);
            int x2 = map.getOrDefault(d2,-1);
            
            Set<Integer> traversed = new HashSet();
            traversed.add(x1);
            results[i] =traverse(a, x1, x2, 1, traversed);
        }
        return results;
    }
    
    private double traverse(double[][] a, int x, int target, double val, Set<Integer> traversed){
        if(x == -1 || target ==-1){
                 return -1;
            }else if(a[x][target]>=0){
                return val*a[x][target];
            }else{
                for(int k =0;k<a.length;k++){
                    if(a[x][k] >= 0 &&  traversed.add(k)){
                        double ret = traverse(a, k, target, val*a[x][k], traversed);
                        if(ret >=0){
                            return ret; 
                        }
                    }
                }
            }
        return -1;
    }
    
}
